package edu.nuc.light_system.service;

import edu.nuc.light_system.entity.vo.PaginationResultVO;
import edu.nuc.light_system.entity.po.UserInfo;
import edu.nuc.light_system.entity.query.UserInfoQuery;
import edu.nuc.light_system.entity.vo.UserVO;
import edu.nuc.light_system.enums.RightEnum;

import java.util.List;
/**
 * @Description:用户信息表Service
 * @author:Liu Wenhao
 * @date:2026/04/09
 */
public interface UserInfoService{

	/**
	 * 根据条件查询列表
	 */
	List<UserInfo> findListByParam(UserInfoQuery query);

	/**
	 * 根据数量查询列表
	 */
	Integer findCountByParam(UserInfoQuery query);

	/**
	 * 分页查询
	 */
	PaginationResultVO<UserInfo> findListByPage(UserInfoQuery query);

	/**
	 * 新增
	 */
	Integer add(UserInfo bean);

	/**
	 * 批量新增
	 */
	Integer addBatch(List<UserInfo> listBean);

	/**
	 * 批量新增或修改
	 */
	Integer addOrUpdateBatch(List<UserInfo> listBean);

	/**
	 * 根据UserId查询
	 */
	UserInfo getUserInfoByUserId(String userId);

	/**
	 * 根据UserId更新
	 */
	Integer updateUserInfoByUserId(UserInfo bean, String userId);

	/**
	 * 根据UserId删除
	 */
	Integer deleteUserInfoByUserId(String userId);

	/**
	 * 根据Phone查询
	 */
	UserInfo getUserInfoByPhone(String phone);

	/**
	 * 根据Phone更新
	 */
	Integer updateUserInfoByPhone(UserInfo bean, String phone);

	/**
	 * 根据Phone删除
	 */
	Integer deleteUserInfoByPhone(String phone);

	/**
	 * 注册
	 */
	void register(String password, String nickName, String phone);
	/**
	 * 登录
	 */
	UserVO login(String phone, String password);

	/**
	 * 登录
	 */
	void setRight(String userId, RightEnum right);

}