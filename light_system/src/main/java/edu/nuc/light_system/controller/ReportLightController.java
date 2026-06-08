package edu.nuc.light_system.controller;

import edu.nuc.light_system.entity.po.ReportLight;
import edu.nuc.light_system.entity.query.ReportLightQuery;
import edu.nuc.light_system.entity.vo.ResponseVO;
import edu.nuc.light_system.service.ReportLightService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:定时上报亮度表Service
 * @author:Liu Wenhao
 * @date:2026/04/09
 */
@RestController
@RequestMapping("/reportLight")
public class ReportLightController extends ABaseController {

	@Resource
	private ReportLightService reportLightService;

	@RequestMapping("loadDataList")
	public ResponseVO loadDataList(ReportLightQuery query) {
		return getSuccessResponseVO(reportLightService.findListByPage(query));
	}

	/**
	 * 新增
	 */
	@RequestMapping("add")
	public ResponseVO add(ReportLight bean) {
		this.reportLightService.add(bean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 批量新增
	 */
	@RequestMapping("addBatch")
	public ResponseVO addBatch(@RequestBody List<ReportLight> listBean) {
		this.reportLightService.addBatch(listBean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 批量新增或修改
	 */
	@RequestMapping("addOrUpdateBatch")
	public ResponseVO addOrUpdateBatch(@RequestBody List<ReportLight> listBean) {
		this.reportLightService.addOrUpdateBatch(listBean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据Id查询
	 */
	@RequestMapping("getReportLightById")
	public ResponseVO getReportLightById(Integer id) {
		return getSuccessResponseVO(this.reportLightService.getReportLightById(id));
	}

	/**
	 * 根据Id更新
	 */
	@RequestMapping("updateReportLightById")
	public ResponseVO updateReportLightById(ReportLight bean, Integer id) {
		this.reportLightService.updateReportLightById(bean,id);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据Id删除
	 */
	@RequestMapping("deleteReportLightById")
	public ResponseVO deleteReportLightById(Integer id) {
		this.reportLightService.deleteReportLightById(id);
		return getSuccessResponseVO(null);
	}

}