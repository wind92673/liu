package edu.nuc.light_system.service.impl;

import edu.nuc.light_system.entity.po.GroupInfo;
import edu.nuc.light_system.entity.query.GroupInfoQuery;
import edu.nuc.light_system.entity.query.SimplePage;
import edu.nuc.light_system.entity.vo.PaginationResultVO;
import edu.nuc.light_system.enums.PageSize;
import edu.nuc.light_system.mappers.GroupInfoMapper;
import edu.nuc.light_system.service.GroupInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:分组表Service
 * @author:Liu Wenhao
 * @date:2026/04/09
 */
@Service("groupInfoService")
public class GroupInfoServiceImpl implements GroupInfoService{

	@Resource
	private GroupInfoMapper<GroupInfo, GroupInfoQuery> groupInfoMapper;

	/**
	 * 根据条件查询列表
	 */
	public List<GroupInfo> findListByParam(GroupInfoQuery query) {
		return this.groupInfoMapper.selectList(query);
	}
	/**
	 * 根据条件查询数量
	 */
	public Integer findCountByParam(GroupInfoQuery query) {
		return this.groupInfoMapper.selectCount(query);
	}
	/**
	 * 分页查询
	 */
	public PaginationResultVO<GroupInfo> findListByPage(GroupInfoQuery query) {
		Integer count = this.findCountByParam(query);
		Integer pageSize = query.getPageSize() == null ? PageSize.SIZE15.getSize() : query.getPageSize();
		SimplePage page = new SimplePage(query.getPageNo(), count, pageSize);
		query.setSimplePage(page);
		List<GroupInfo> list = this.findListByParam(query);
		PaginationResultVO<GroupInfo> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
		return result;
	}
	/**
	 * 新增
	 */
	public Integer add(GroupInfo bean) {
		return this.groupInfoMapper.insert(bean);
	}
	/**
	 * 批量新增
	 */
	public Integer addBatch(List<GroupInfo> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.groupInfoMapper.insertBatch(listBean);
	}
	/**
	 * 批量新增或修改
	 */
	public Integer addOrUpdateBatch(List<GroupInfo> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.groupInfoMapper.insertOrUpdateBatch(listBean);
	}
	/**
	 * 根据GroupId查询
	 */
	public GroupInfo getGroupInfoByGroupId(String groupId) {
		return this.groupInfoMapper.selectByGroupId(groupId);
	}
	/**
	 * 根据GroupId更新
	 */
	public Integer updateGroupInfoByGroupId(GroupInfo bean, String groupId) {
		return this.groupInfoMapper.updateByGroupId(bean,groupId);
	}
	/**
	 * 根据GroupId删除
	 */
	public Integer deleteGroupInfoByGroupId(String groupId) {
		return this.groupInfoMapper.deleteByGroupId(groupId);
	}
}