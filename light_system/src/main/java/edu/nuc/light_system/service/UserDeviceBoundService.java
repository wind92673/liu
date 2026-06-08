package edu.nuc.light_system.service;

import edu.nuc.light_system.entity.vo.PaginationResultVO;
import edu.nuc.light_system.entity.po.UserDeviceBound;
import edu.nuc.light_system.entity.query.UserDeviceBoundQuery;

import java.util.List;
/**
 * @Description:用户设备关系表Service
 * @author:Liu Wenhao
 * @date:2026/04/09
 */
public interface UserDeviceBoundService{

	/**
	 * 根据条件查询列表
	 */
	List<UserDeviceBound> findListByParam(UserDeviceBoundQuery query);

	/**
	 * 根据数量查询列表
	 */
	Integer findCountByParam(UserDeviceBoundQuery query);

	/**
	 * 分页查询
	 */
	PaginationResultVO<UserDeviceBound> findListByPage(UserDeviceBoundQuery query);

	/**
	 * 新增
	 */
	Integer add(UserDeviceBound bean);

	/**
	 * 批量新增
	 */
	Integer addBatch(List<UserDeviceBound> listBean);

	/**
	 * 批量新增或修改
	 */
	Integer addOrUpdateBatch(List<UserDeviceBound> listBean);

	/**
	 * 根据Id查询
	 */
	UserDeviceBound getUserDeviceBoundById(Integer id);

	/**
	 * 根据Id更新
	 */
	Integer updateUserDeviceBoundById(UserDeviceBound bean, Integer id);

	/**
	 * 根据Id删除
	 */
	Integer deleteUserDeviceBoundById(Integer id);

}