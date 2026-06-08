package edu.nuc.light_system.controller;

import edu.nuc.light_system.entity.po.DeviceGroupBound;
import edu.nuc.light_system.entity.query.DeviceGroupBoundQuery;
import edu.nuc.light_system.entity.vo.ResponseVO;
import edu.nuc.light_system.service.DeviceGroupBoundService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:设备分组表Service
 * @author:Liu Wenhao
 * @date:2026/04/09
 */
@RestController
@RequestMapping("/deviceGroupBound")
public class DeviceGroupBoundController extends ABaseController {

	@Resource
	private DeviceGroupBoundService deviceGroupBoundService;

	@RequestMapping("loadDataList")
	public ResponseVO loadDataList(DeviceGroupBoundQuery query) {
		return getSuccessResponseVO(deviceGroupBoundService.findListByPage(query));
	}

	/**
	 * 新增
	 */
	@RequestMapping("add")
	public ResponseVO add(DeviceGroupBound bean) {
		this.deviceGroupBoundService.add(bean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 批量新增
	 */
	@RequestMapping("addBatch")
	public ResponseVO addBatch(@RequestBody List<DeviceGroupBound> listBean) {
		this.deviceGroupBoundService.addBatch(listBean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 批量新增或修改
	 */
	@RequestMapping("addOrUpdateBatch")
	public ResponseVO addOrUpdateBatch(@RequestBody List<DeviceGroupBound> listBean) {
		this.deviceGroupBoundService.addOrUpdateBatch(listBean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据Id查询
	 */
	@RequestMapping("getDeviceGroupBoundById")
	public ResponseVO getDeviceGroupBoundById(Integer id) {
		return getSuccessResponseVO(this.deviceGroupBoundService.getDeviceGroupBoundById(id));
	}

	/**
	 * 根据Id更新
	 */
	@RequestMapping("updateDeviceGroupBoundById")
	public ResponseVO updateDeviceGroupBoundById(DeviceGroupBound bean, Integer id) {
		this.deviceGroupBoundService.updateDeviceGroupBoundById(bean,id);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据Id删除
	 */
	@RequestMapping("deleteDeviceGroupBoundById")
	public ResponseVO deleteDeviceGroupBoundById(Integer id) {
		this.deviceGroupBoundService.deleteDeviceGroupBoundById(id);
		return getSuccessResponseVO(null);
	}

}