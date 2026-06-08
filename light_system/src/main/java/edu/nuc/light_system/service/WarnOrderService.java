package edu.nuc.light_system.service;

import edu.nuc.light_system.entity.vo.PaginationResultVO;
import edu.nuc.light_system.entity.po.WarnOrder;
import edu.nuc.light_system.entity.query.WarnOrderQuery;

import java.util.List;
/**
 * @Description:告警工单表Service
 * @author:Liu Wenhao
 * @date:2026/04/09
 */
public interface WarnOrderService{

	/**
	 * 根据条件查询列表
	 */
	List<WarnOrder> findListByParam(WarnOrderQuery query);

	/**
	 * 根据数量查询列表
	 */
	Integer findCountByParam(WarnOrderQuery query);

	/**
	 * 分页查询
	 */
	PaginationResultVO<WarnOrder> findListByPage(WarnOrderQuery query);

	/**
	 * 新增
	 */
	Integer add(WarnOrder bean);

	/**
	 * 批量新增
	 */
	Integer addBatch(List<WarnOrder> listBean);

	/**
	 * 批量新增或修改
	 */
	Integer addOrUpdateBatch(List<WarnOrder> listBean);

	/**
	 * 根据Id查询
	 */
	WarnOrder getWarnOrderById(Integer id);

	/**
	 * 根据Id更新
	 */
	Integer updateWarnOrderById(WarnOrder bean, Integer id);

	/**
	 * 根据Id删除
	 */
	Integer deleteWarnOrderById(Integer id);

	/**
	 * 统计未处理告警数量
	 */
	Integer countUnprocessed();

}