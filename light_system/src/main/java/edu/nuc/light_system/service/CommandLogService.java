package edu.nuc.light_system.service;

import edu.nuc.light_system.entity.vo.PaginationResultVO;
import edu.nuc.light_system.entity.po.CommandLog;
import edu.nuc.light_system.entity.query.CommandLogQuery;

import java.util.List;
/**
 * @Description:操作日志表Service
 * @author:Liu Wenhao
 * @date:2026/04/09
 */
public interface CommandLogService{

	/**
	 * 根据条件查询列表
	 */
	List<CommandLog> findListByParam(CommandLogQuery query);

	/**
	 * 根据数量查询列表
	 */
	Integer findCountByParam(CommandLogQuery query);

	/**
	 * 分页查询
	 */
	PaginationResultVO<CommandLog> findListByPage(CommandLogQuery query);

	/**
	 * 新增
	 */
	Integer add(CommandLog bean);

	/**
	 * 批量新增
	 */
	Integer addBatch(List<CommandLog> listBean);

	/**
	 * 批量新增或修改
	 */
	Integer addOrUpdateBatch(List<CommandLog> listBean);

	/**
	 * 根据Id查询
	 */
	CommandLog getCommandLogById(Integer id);

	/**
	 * 根据Id更新
	 */
	Integer updateCommandLogById(CommandLog bean, Integer id);

	/**
	 * 根据Id删除
	 */
	Integer deleteCommandLogById(Integer id);

}