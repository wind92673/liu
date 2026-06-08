package edu.nuc.light_system.controller;

import edu.nuc.light_system.entity.po.UserDeviceBound;
import edu.nuc.light_system.entity.query.UserDeviceBoundQuery;
import edu.nuc.light_system.entity.vo.ResponseVO;
import edu.nuc.light_system.service.UserDeviceBoundService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:用户设备关系表Service
 * @author:Liu Wenhao
 * @date:2026/04/09
 */
@RestController
@RequestMapping("/userDeviceBound")
public class UserDeviceBoundController extends ABaseController {

	@Resource
	private UserDeviceBoundService userDeviceBoundService;

	@RequestMapping("loadDataList")
	public ResponseVO loadDataList(UserDeviceBoundQuery query) {
		return getSuccessResponseVO(userDeviceBoundService.findListByPage(query));
	}

	/**
	 * 新增
	 */
	@RequestMapping("add")
	public ResponseVO add(UserDeviceBound bean) {
		this.userDeviceBoundService.add(bean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 批量新增
	 */
	@RequestMapping("addBatch")
	public ResponseVO addBatch(@RequestBody List<UserDeviceBound> listBean) {
		this.userDeviceBoundService.addBatch(listBean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 批量新增或修改
	 */
	@RequestMapping("addOrUpdateBatch")
	public ResponseVO addOrUpdateBatch(@RequestBody List<UserDeviceBound> listBean) {
		this.userDeviceBoundService.addOrUpdateBatch(listBean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据Id查询
	 */
	@RequestMapping("getUserDeviceBoundById")
	public ResponseVO getUserDeviceBoundById(Integer id) {
		return getSuccessResponseVO(this.userDeviceBoundService.getUserDeviceBoundById(id));
	}

	/**
	 * 根据Id更新
	 */
	@RequestMapping("updateUserDeviceBoundById")
	public ResponseVO updateUserDeviceBoundById(UserDeviceBound bean, Integer id) {
		this.userDeviceBoundService.updateUserDeviceBoundById(bean,id);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据Id删除
	 */
	@RequestMapping("deleteUserDeviceBoundById")
	public ResponseVO deleteUserDeviceBoundById(Integer id) {
		this.userDeviceBoundService.deleteUserDeviceBoundById(id);
		return getSuccessResponseVO(null);
	}

}