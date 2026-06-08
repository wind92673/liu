package edu.nuc.light_system.controller;

import edu.nuc.light_system.entity.po.GroupInfo;
import edu.nuc.light_system.entity.query.GroupInfoQuery;
import edu.nuc.light_system.entity.vo.ResponseVO;
import edu.nuc.light_system.service.GroupInfoService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:分组表Service
 * @author:Liu Wenhao
 * @date:2026/04/09
 */
@RestController
@RequestMapping("/groupInfo")
public class GroupInfoController extends ABaseController {

	@Resource
	private GroupInfoService groupInfoService;

	@RequestMapping("loadDataList")
	public ResponseVO loadDataList(GroupInfoQuery query) {
		return getSuccessResponseVO(groupInfoService.findListByPage(query));
	}

	/**
	 * 新增
	 */
	@RequestMapping("add")
	public ResponseVO add(GroupInfo bean) {
		this.groupInfoService.add(bean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 批量新增
	 */
	@RequestMapping("addBatch")
	public ResponseVO addBatch(@RequestBody List<GroupInfo> listBean) {
		this.groupInfoService.addBatch(listBean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 批量新增或修改
	 */
	@RequestMapping("addOrUpdateBatch")
	public ResponseVO addOrUpdateBatch(@RequestBody List<GroupInfo> listBean) {
		this.groupInfoService.addOrUpdateBatch(listBean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据GroupId查询
	 */
	@RequestMapping("getGroupInfoByGroupId")
	public ResponseVO getGroupInfoByGroupId(String groupId) {
		return getSuccessResponseVO(this.groupInfoService.getGroupInfoByGroupId(groupId));
	}

	/**
	 * 根据GroupId更新
	 */
	@RequestMapping("updateGroupInfoByGroupId")
	public ResponseVO updateGroupInfoByGroupId(GroupInfo bean, String groupId) {
		this.groupInfoService.updateGroupInfoByGroupId(bean,groupId);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据GroupId删除
	 */
	@RequestMapping("deleteGroupInfoByGroupId")
	public ResponseVO deleteGroupInfoByGroupId(String groupId) {
		this.groupInfoService.deleteGroupInfoByGroupId(groupId);
		return getSuccessResponseVO(null);
	}

}