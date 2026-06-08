package edu.nuc.light_system.service;

import edu.nuc.light_system.entity.vo.PaginationResultVO;
import edu.nuc.light_system.entity.po.ResultInfo;
import edu.nuc.light_system.entity.query.ResultInfoQuery;

import java.util.List;
/**
 * @Description:处理结果表Service
 * @author:Liu Wenhao
 * @date:2026/04/09
 */
public interface ResultInfoService{

	/**
	 * 根据条件查询列表
	 */
	List<ResultInfo> findListByParam(ResultInfoQuery query);

	/**
	 * 根据数量查询列表
	 */
	Integer findCountByParam(ResultInfoQuery query);

	/**
	 * 分页查询
	 */
	PaginationResultVO<ResultInfo> findListByPage(ResultInfoQuery query);

	/**
	 * 新增
	 */
	Integer add(ResultInfo bean);

	/**
	 * 批量新增
	 */
	Integer addBatch(List<ResultInfo> listBean);

	/**
	 * 批量新增或修改
	 */
	Integer addOrUpdateBatch(List<ResultInfo> listBean);

	/**
	 * 根据Id查询
	 */
	ResultInfo getResultInfoById(Integer id);

	/**
	 * 根据Id更新
	 */
	Integer updateResultInfoById(ResultInfo bean, Integer id);

	/**
	 * 根据Id删除
	 */
	Integer deleteResultInfoById(Integer id);

}