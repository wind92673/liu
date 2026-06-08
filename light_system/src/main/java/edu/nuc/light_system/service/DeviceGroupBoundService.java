package edu.nuc.light_system.service;

import edu.nuc.light_system.entity.vo.PaginationResultVO;
import edu.nuc.light_system.entity.po.DeviceGroupBound;
import edu.nuc.light_system.entity.query.DeviceGroupBoundQuery;

import java.util.List;
/**
 * @Description:设备分组表Service
 * @author:Liu Wenhao
 * @date:2026/04/09
 */
public interface DeviceGroupBoundService{

	/**
	 * 根据条件查询列表
	 */
	List<DeviceGroupBound> findListByParam(DeviceGroupBoundQuery query);

	/**
	 * 根据数量查询列表
	 */
	Integer findCountByParam(DeviceGroupBoundQuery query);

	/**
	 * 分页查询
	 */
	PaginationResultVO<DeviceGroupBound> findListByPage(DeviceGroupBoundQuery query);

	/**
	 * 新增
	 */
	Integer add(DeviceGroupBound bean);

	/**
	 * 批量新增
	 */
	Integer addBatch(List<DeviceGroupBound> listBean);

	/**
	 * 批量新增或修改
	 */
	Integer addOrUpdateBatch(List<DeviceGroupBound> listBean);

	/**
	 * 根据Id查询
	 */
	DeviceGroupBound getDeviceGroupBoundById(Integer id);

	/**
	 * 根据Id更新
	 */
	Integer updateDeviceGroupBoundById(DeviceGroupBound bean, Integer id);

	/**
	 * 根据Id删除
	 */
	Integer deleteDeviceGroupBoundById(Integer id);

}