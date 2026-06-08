package edu.nuc.light_system.controller;

import edu.nuc.light_system.entity.po.SaveInfo;
import edu.nuc.light_system.entity.query.SaveInfoQuery;
import edu.nuc.light_system.entity.vo.ResponseVO;
import edu.nuc.light_system.exception.BusinessException;
import edu.nuc.light_system.service.SaveInfoService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Description:节能统计表Service
 * @author:Liu Wenhao
 * @date:2026/04/09
 */
@RestController
@RequestMapping("/saveInfo")
public class SaveInfoController extends ABaseController {

	@Resource
	private SaveInfoService saveInfoService;

	@RequestMapping("loadDataList")
	public ResponseVO loadDataList(SaveInfoQuery query) {
		return getSuccessResponseVO(saveInfoService.findListByPage(query));
	}

	/**
	 * 新增
	 */
	@RequestMapping("add")
	public ResponseVO add(SaveInfo bean) {
		this.saveInfoService.add(bean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 批量新增
	 */
	@RequestMapping("addBatch")
	public ResponseVO addBatch(@RequestBody List<SaveInfo> listBean) {
		this.saveInfoService.addBatch(listBean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 批量新增或修改
	 */
	@RequestMapping("addOrUpdateBatch")
	public ResponseVO addOrUpdateBatch(@RequestBody List<SaveInfo> listBean) {
		this.saveInfoService.addOrUpdateBatch(listBean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据Id查询
	 */
	@RequestMapping("getSaveInfoById")
	public ResponseVO getSaveInfoById(Integer id) {
		return getSuccessResponseVO(this.saveInfoService.getSaveInfoById(id));
	}

	/**
	 * 根据Id更新
	 */
	@RequestMapping("updateSaveInfoById")
	public ResponseVO updateSaveInfoById(SaveInfo bean, Integer id) {
		this.saveInfoService.updateSaveInfoById(bean,id);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据Id删除
	 */
	@RequestMapping("deleteSaveInfoById")
	public ResponseVO deleteSaveInfoById(Integer id) {
		this.saveInfoService.deleteSaveInfoById(id);
		return getSuccessResponseVO(null);
	}

	/**
	 * 按天查询所有记录
	 * 参数：deviceId（可选）、currentTime（必选，格式：yyyy-MM-dd）
	 */
	@RequestMapping("getListByDay")
	public ResponseVO getListByDay(SaveInfoQuery query) {
		if (query.getCurrentTime() == null) {
			throw new BusinessException("当前时间不能为空");
		}
		return getSuccessResponseVO(saveInfoService.getListByDay(query));
	}

	/**
	 * 按月汇总查询（按天累加）
	 * 参数：deviceId（可选）、year（可选）、month（可选）、currentTimeStart（可选）、currentTimeEnd（可选）
	 * 返回：每天的节电量和节碳量汇总
	 */
	@RequestMapping("getMonthlySummary")
	public ResponseVO getMonthlySummary(SaveInfoQuery query) {
		return getSuccessResponseVO(saveInfoService.getMonthlySummary(query));
	}

	/**
	 * 按年汇总查询（按月累加）
	 * 参数：deviceId（可选）、year（可选）、currentTimeStart（可选）、currentTimeEnd（可选）
	 * 返回：每月的节电量和节碳量汇总
	 */
	@RequestMapping("getYearlySummary")
	public ResponseVO getYearlySummary(SaveInfoQuery query) {
		return getSuccessResponseVO(saveInfoService.getYearlySummary(query));
	}

	/**
	 * 导出Excel文件
	 * 参数：支持所有SaveInfoQuery的查询条件（deviceId、currentTime等）
	 */
	@RequestMapping("exportExcel")
	public void exportExcel(SaveInfoQuery query, HttpServletResponse response) {
		saveInfoService.exportExcel(query, response);
	}


}