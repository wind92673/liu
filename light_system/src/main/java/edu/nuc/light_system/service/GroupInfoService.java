package edu.nuc.light_system.service;

import edu.nuc.light_system.entity.vo.PaginationResultVO;
import edu.nuc.light_system.entity.po.GroupInfo;
import edu.nuc.light_system.entity.query.GroupInfoQuery;

import java.util.List;
/**
 * @Description:分组表Service
 * @author:Liu Wenhao
 * @date:2026/04/09
 */
public interface GroupInfoService{

	/**
	 * 根据条件查询列表
	 */
	List<GroupInfo> findListByParam(GroupInfoQuery query);

	/**
	 * 根据数量查询列表
	 */
	Integer findCountByParam(GroupInfoQuery query);

	/**
	 * 分页查询
	 */
	PaginationResultVO<GroupInfo> findListByPage(GroupInfoQuery query);

	/**
	 * 新增
	 */
	Integer add(GroupInfo bean);

	/**
	 * 批量新增
	 */
	Integer addBatch(List<GroupInfo> listBean);

	/**
	 * 批量新增或修改
	 */
	Integer addOrUpdateBatch(List<GroupInfo> listBean);

	/**
	 * 根据GroupId查询
	 */
	GroupInfo getGroupInfoByGroupId(String groupId);

	/**
	 * 根据GroupId更新
	 */
	Integer updateGroupInfoByGroupId(GroupInfo bean, String groupId);

	/**
	 * 根据GroupId删除
	 */
	Integer deleteGroupInfoByGroupId(String groupId);

}