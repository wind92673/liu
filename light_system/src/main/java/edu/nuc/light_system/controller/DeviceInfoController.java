package edu.nuc.light_system.controller;

import edu.nuc.light_system.entity.po.DeviceInfo;
import edu.nuc.light_system.entity.query.DeviceInfoQuery;
import edu.nuc.light_system.entity.vo.ResponseVO;
import edu.nuc.light_system.service.DeviceInfoService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:设备信息表Service
 * @author:Liu Wenhao
 * @date:2026/04/13
 */
@RestController
@RequestMapping("/deviceInfo")
public class DeviceInfoController extends ABaseController {

	@Resource
	private DeviceInfoService deviceInfoService;

	@RequestMapping("loadDataList")
	public ResponseVO loadDataList(DeviceInfoQuery query) {
		return getSuccessResponseVO(deviceInfoService.findListByPage(query));
	}

	/**
	 * 新增
	 */
	@RequestMapping("add")
	public ResponseVO add(DeviceInfo bean) {
		this.deviceInfoService.add(bean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 批量新增
	 */
	@RequestMapping("addBatch")
	public ResponseVO addBatch(@RequestBody List<DeviceInfo> listBean) {
		this.deviceInfoService.addBatch(listBean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 批量新增或修改
	 */
	@RequestMapping("addOrUpdateBatch")
	public ResponseVO addOrUpdateBatch(@RequestBody List<DeviceInfo> listBean) {
		this.deviceInfoService.addOrUpdateBatch(listBean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据DeviceId查询
	 */
	@RequestMapping("getDeviceInfoByDeviceId")
	public ResponseVO getDeviceInfoByDeviceId(String deviceId) {
		return getSuccessResponseVO(this.deviceInfoService.getDeviceInfoByDeviceId(deviceId));
	}

	/**
	 * 根据DeviceId更新
	 */
	@RequestMapping("updateDeviceInfoByDeviceId")
	public ResponseVO updateDeviceInfoByDeviceId(DeviceInfo bean, String deviceId) {
		this.deviceInfoService.updateDeviceInfoByDeviceId(bean,deviceId);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据DeviceId更新运行状态
	 */
	@RequestMapping("updateDeviceModeByDeviceId")
	public ResponseVO updateDeviceModeByDeviceId(Integer mode, String deviceId) {
		this.deviceInfoService.updateDeviceModeByDeviceId(mode,deviceId);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据DeviceId更新设备亮度
	 */
	@RequestMapping("updateDeviceBrightByDeviceId")
	public ResponseVO updateDeviceBrightByDeviceId(Integer bright, String deviceId) {
		this.deviceInfoService.updateDeviceBrightByDeviceId(bright,deviceId);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据DeviceId同步设备时间,同步为当前时间
	 */
	@RequestMapping("syncDeviceTimeNowByDeviceId")
	public ResponseVO syncDeviceTimeNowByDeviceId(String deviceId) {
		this.deviceInfoService.syncDeviceTimeNowByDeviceId(deviceId);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据DeviceId同步设备时间,同步为设置时间
	 */
	@RequestMapping("syncDeviceTimeByDeviceId")
	public ResponseVO syncDeviceTimeByDeviceId(String deviceId,String currentTime) {
		this.deviceInfoService.syncDeviceTimeByDeviceId(deviceId, currentTime);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据DeviceId删除
	 */
	@RequestMapping("deleteDeviceInfoByDeviceId")
	public ResponseVO deleteDeviceInfoByDeviceId(String deviceId) {
		this.deviceInfoService.deleteDeviceInfoByDeviceId(deviceId);
		return getSuccessResponseVO(null);
	}

	/**
	 * 统计在线设备数量
	 */
	@RequestMapping("countOnline")
	public ResponseVO countOnline() {
		Map<String, Integer> result = new HashMap<>();
		result.put("count", this.deviceInfoService.countOnline());
		return getSuccessResponseVO(result);
	}

}