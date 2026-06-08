package edu.nuc.light_system.service;

import edu.nuc.light_system.entity.po.SaveInfo;
import edu.nuc.light_system.entity.vo.PaginationResultVO;
import edu.nuc.light_system.entity.po.DeviceInfo;
import edu.nuc.light_system.entity.query.DeviceInfoQuery;

import java.util.Date;
import java.util.List;
/**
 * @Description:设备信息表Service
 * @author:Liu Wenhao
 * @date:2026/04/13
 */
public interface DeviceInfoService{

	/**
	 * 根据条件查询列表
	 */
	List<DeviceInfo> findListByParam(DeviceInfoQuery query);

	/**
	 * 根据数量查询列表
	 */
	Integer findCountByParam(DeviceInfoQuery query);

	/**
	 * 分页查询
	 */
	PaginationResultVO<DeviceInfo> findListByPage(DeviceInfoQuery query);

	/**
	 * 新增
	 */
	Integer add(DeviceInfo bean);

	/**
	 * 批量新增
	 */
	Integer addBatch(List<DeviceInfo> listBean);

	/**
	 * 批量新增或修改
	 */
	Integer addOrUpdateBatch(List<DeviceInfo> listBean);

	/**
	 * 根据DeviceId查询
	 */
	DeviceInfo getDeviceInfoByDeviceId(String deviceId);

	/**
	 * 根据DeviceId更新
	 */
	Integer updateDeviceInfoByDeviceId(DeviceInfo bean, String deviceId);

	/**
	 * 根据DeviceId删除
	 */
	Integer deleteDeviceInfoByDeviceId(String deviceId);

	/**
	 * 设备上线消息处理
	 */
	void handleDeviceOnline(String deviceId);

	void updateDeviceModeByDeviceId(Integer mode, String deviceId);

	void updateDeviceBrightByDeviceId(Integer bright, String deviceId);

	void syncDeviceTimeNowByDeviceId(String deviceId);

	void syncDeviceTimeByDeviceId(String deviceId, String currentTime);

	/**
	 * 统计在线设备数量
	 */
	Integer countOnline();

}