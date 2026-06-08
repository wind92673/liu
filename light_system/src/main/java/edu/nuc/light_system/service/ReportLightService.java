package edu.nuc.light_system.service;

import edu.nuc.light_system.entity.dto.StatusReportMessage;
import edu.nuc.light_system.entity.vo.PaginationResultVO;
import edu.nuc.light_system.entity.po.ReportLight;
import edu.nuc.light_system.entity.query.ReportLightQuery;

import java.util.List;
/**
 * @Description:定时上报亮度表Service
 * @author:Liu Wenhao
 * @date:2026/04/09
 */
public interface ReportLightService{

	/**
	 * 根据条件查询列表
	 */
	List<ReportLight> findListByParam(ReportLightQuery query);

	/**
	 * 根据数量查询列表
	 */
	Integer findCountByParam(ReportLightQuery query);

	/**
	 * 分页查询
	 */
	PaginationResultVO<ReportLight> findListByPage(ReportLightQuery query);

	/**
	 * 新增
	 */
	Integer add(ReportLight bean);

	/**
	 * 批量新增
	 */
	Integer addBatch(List<ReportLight> listBean);

	/**
	 * 批量新增或修改
	 */
	Integer addOrUpdateBatch(List<ReportLight> listBean);

	/**
	 * 根据Id查询
	 */
	ReportLight getReportLightById(Integer id);

	/**
	 * 根据Id更新
	 */
	Integer updateReportLightById(ReportLight bean, Integer id);

	/**
	 * 根据Id删除
	 */
	Integer deleteReportLightById(Integer id);

	void handleStatusReport(StatusReportMessage message);

}