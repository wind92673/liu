package edu.nuc.light_system.controller;

import edu.nuc.light_system.entity.po.UserInfo;
import edu.nuc.light_system.entity.query.UserInfoQuery;
import edu.nuc.light_system.entity.vo.ResponseVO;
import edu.nuc.light_system.service.UserInfoService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:用户信息表Service
 * @author:Liu Wenhao
 * @date:2026/04/09
 */
@RestController
@RequestMapping("/userInfo")
public class UserInfoController extends ABaseController {

	@Resource
	private UserInfoService userInfoService;

	@RequestMapping("loadDataList")
	public ResponseVO loadDataList(UserInfoQuery query) {
		return getSuccessResponseVO(userInfoService.findListByPage(query));
	}

	/**
	 * 新增
	 */
	@RequestMapping("add")
	public ResponseVO add(UserInfo bean) {
		this.userInfoService.add(bean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 批量新增
	 */
	@RequestMapping("addBatch")
	public ResponseVO addBatch(@RequestBody List<UserInfo> listBean) {
		this.userInfoService.addBatch(listBean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 批量新增或修改
	 */
	@RequestMapping("addOrUpdateBatch")
	public ResponseVO addOrUpdateBatch(@RequestBody List<UserInfo> listBean) {
		this.userInfoService.addOrUpdateBatch(listBean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据UserId查询
	 */
	@RequestMapping("getUserInfoByUserId")
	public ResponseVO getUserInfoByUserId(String userId) {
		return getSuccessResponseVO(this.userInfoService.getUserInfoByUserId(userId));
	}

	/**
	 * 根据UserId更新
	 */
	@RequestMapping("updateUserInfoByUserId")
	public ResponseVO updateUserInfoByUserId(UserInfo bean, String userId) {
		this.userInfoService.updateUserInfoByUserId(bean,userId);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据UserId删除
	 */
	@RequestMapping("deleteUserInfoByUserId")
	public ResponseVO deleteUserInfoByUserId(String userId) {
		this.userInfoService.deleteUserInfoByUserId(userId);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据Phone查询
	 */
	@RequestMapping("getUserInfoByPhone")
	public ResponseVO getUserInfoByPhone(String phone) {
		return getSuccessResponseVO(this.userInfoService.getUserInfoByPhone(phone));
	}

	/**
	 * 根据Phone更新
	 */
	@RequestMapping("updateUserInfoByPhone")
	public ResponseVO updateUserInfoByPhone(UserInfo bean, String phone) {
		this.userInfoService.updateUserInfoByPhone(bean,phone);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据Phone删除
	 */
	@RequestMapping("deleteUserInfoByPhone")
	public ResponseVO deleteUserInfoByPhone(String phone) {
		this.userInfoService.deleteUserInfoByPhone(phone);
		return getSuccessResponseVO(null);
	}

}