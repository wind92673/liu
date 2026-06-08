package edu.nuc.light_system.service.impl;

import edu.nuc.light_system.entity.po.DeviceInfo;
import edu.nuc.light_system.entity.query.DeviceInfoQuery;
import edu.nuc.light_system.entity.query.SimplePage;
import edu.nuc.light_system.entity.vo.PaginationResultVO;
import edu.nuc.light_system.enums.DeviceModeEnum;
import edu.nuc.light_system.enums.DeviceOnlineStateEnum;
import edu.nuc.light_system.enums.PageSize;
import edu.nuc.light_system.exception.BusinessException;
import edu.nuc.light_system.mappers.DeviceInfoMapper;
import edu.nuc.light_system.mqtt.MqttPublisher;
import edu.nuc.light_system.service.DeviceInfoService;
import edu.nuc.light_system.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @Description:设备信息表Service
 * @author:Liu Wenhao
 * @date:2026/04/13
 */
@Service("deviceInfoService")
public class DeviceInfoServiceImpl implements DeviceInfoService{

	private static final Logger logger = LoggerFactory.getLogger(DeviceInfoServiceImpl.class);

	@Resource
	private DeviceInfoMapper<DeviceInfo, DeviceInfoQuery> deviceInfoMapper;

	@Resource
	private MqttPublisher mqttPublisher;

	/**
	 * 根据条件查询列表
	 */
	public List<DeviceInfo> findListByParam(DeviceInfoQuery query) {
		return this.deviceInfoMapper.selectList(query);
	}
	/**
	 * 根据条件查询数量
	 */
	public Integer findCountByParam(DeviceInfoQuery query) {
		return this.deviceInfoMapper.selectCount(query);
	}
	/**
	 * 分页查询
	 */
	public PaginationResultVO<DeviceInfo> findListByPage(DeviceInfoQuery query) {
		Integer count = this.findCountByParam(query);
		Integer pageSize = query.getPageSize() == null ? PageSize.SIZE15.getSize() : query.getPageSize();
		SimplePage page = new SimplePage(query.getPageNo(), count, pageSize);
		query.setSimplePage(page);
		List<DeviceInfo> list = this.findListByParam(query);
		PaginationResultVO<DeviceInfo> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
		return result;
	}
	/**
	 * 新增
	 */
	public Integer add(DeviceInfo bean) {
		return this.deviceInfoMapper.insert(bean);
	}
	/**
	 * 批量新增
	 */
	public Integer addBatch(List<DeviceInfo> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.deviceInfoMapper.insertBatch(listBean);
	}
	/**
	 * 批量新增或修改
	 */
	public Integer addOrUpdateBatch(List<DeviceInfo> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.deviceInfoMapper.insertOrUpdateBatch(listBean);
	}
	/**
	 * 根据DeviceId查询
	 */
	public DeviceInfo getDeviceInfoByDeviceId(String deviceId) {
		return this.deviceInfoMapper.selectByDeviceId(deviceId);
	}
	/**
	 * 根据DeviceId更新
	 */
	@Transactional(rollbackFor = Exception.class)
	public Integer updateDeviceInfoByDeviceId(DeviceInfo bean, String deviceId) {
		boolean needPublishConfig = bean.getLightThreshold() != null
				|| bean.getReportInterval() != null
				|| bean.getDayStart() != null
				|| bean.getDayEnd() != null;

		if (needPublishConfig) {
			mqttPublisher.publishConfigOrder(
					deviceId,
					bean.getLightThreshold(),
					bean.getReportInterval(),
					bean.getDayStart(),
					bean.getDayEnd()
			);
		}

		if (bean.getSystemMode() != null) {
			mqttPublisher.publishModeOrder(deviceId, bean.getSystemMode());
		}

		return this.deviceInfoMapper.updateByDeviceId(bean, deviceId);
	}


	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateDeviceModeByDeviceId(Integer mode, String deviceId) {
		if (mode == null) {
			throw new BusinessException("模式不能为空");
		}
		mqttPublisher.publishModeOrder(deviceId, mode);
		DeviceInfo deviceInfo = new DeviceInfo();
		deviceInfo.setDeviceId(deviceId);
		deviceInfo.setSystemMode(mode);

		this.deviceInfoMapper.updateByDeviceId(deviceInfo,deviceId);
	}

	@Override
	public void updateDeviceBrightByDeviceId(Integer bright, String deviceId) {
		if (bright == null) {
			throw new BusinessException("亮度不能为空");
		}
		if (!Objects.equals(getDeviceInfoByDeviceId(deviceId).getSystemMode(), DeviceModeEnum.MANUAL.getCode())) {
			throw new BusinessException("自动模式不支持更改亮度，请设置为手动模式");
		}

		mqttPublisher.publishBrightOrder(deviceId, bright);
	}

	@Override
	public void syncDeviceTimeNowByDeviceId(String deviceId) {
		mqttPublisher.publishSyncOrderNow(deviceId);
	}

	@Override
	public void syncDeviceTimeByDeviceId(String deviceId, String currentTime) {
		mqttPublisher.publishSyncOrder(deviceId, currentTime);
	}

	/**
	 * 根据DeviceId删除
	 */
	public Integer deleteDeviceInfoByDeviceId(String deviceId) {
		return this.deviceInfoMapper.deleteByDeviceId(deviceId);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void handleDeviceOnline(String deviceId) {
		logger.info("处理设备上线: {}", deviceId);

		DeviceInfo deviceInfo = this.deviceInfoMapper.selectByDeviceId(deviceId);

		if (deviceInfo == null) {
			//TODO:这里的创建新设备应该为设备重新分配新的deviceId,并下发给设备，可以重新写一个创建新设备的方法
			logger.info("设备不存在，创建设备记录: {}", deviceId);
			deviceInfo = new DeviceInfo();
			deviceInfo.setDeviceId(deviceId);
			deviceInfo.setOnline(DeviceOnlineStateEnum.ONLINE.getState());
			deviceInfo.setSystemMode(DeviceModeEnum.AUTO.getCode());
			deviceInfo.setLightThreshold(400);
			deviceInfo.setDayStart(6);
			deviceInfo.setDayEnd(18);
			deviceInfo.setReportInterval(10);
			this.deviceInfoMapper.insert(deviceInfo);
		} else {
			logger.info("更新设备在线状态: {}", deviceId);
			DeviceInfo updateInfo = new DeviceInfo();
			updateInfo.setOnline(DeviceOnlineStateEnum.ONLINE.getState());
			this.deviceInfoMapper.updateByDeviceId(updateInfo, deviceId);
		}

		// 设备上线处理完成后，立即发送时间同步命令
		mqttPublisher.publishSyncOrderNow(deviceId);
		logger.info("设备上线处理完成: {}", deviceId);
	}

	@Override
	public Integer countOnline() {
		DeviceInfoQuery query = new DeviceInfoQuery();
		query.setOnline(DeviceOnlineStateEnum.ONLINE.getState());
		return this.deviceInfoMapper.selectCount(query);
	}
}