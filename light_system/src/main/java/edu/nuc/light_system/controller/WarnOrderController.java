package edu.nuc.light_system.controller;

import edu.nuc.light_system.entity.po.WarnOrder;
import edu.nuc.light_system.entity.query.WarnOrderQuery;
import edu.nuc.light_system.entity.vo.ResponseVO;
import edu.nuc.light_system.service.WarnOrderService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:告警工单表Service
 * @author:Liu Wenhao
 * @date:2026/04/09
 */
@RestController
@RequestMapping("/warnOrder")
public class WarnOrderController extends ABaseController {

	@Resource
	private WarnOrderService warnOrderService;

	@RequestMapping("loadDataList")
	public ResponseVO loadDataList(WarnOrderQuery query) {
		return getSuccessResponseVO(warnOrderService.findListByPage(query));
	}

	/**
	 * 新增
	 */
	@RequestMapping("add")
	public ResponseVO add(WarnOrder bean) {
		this.warnOrderService.add(bean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 批量新增
	 */
	@RequestMapping("addBatch")
	public ResponseVO addBatch(@RequestBody List<WarnOrder> listBean) {
		this.warnOrderService.addBatch(listBean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 批量新增或修改
	 */
	@RequestMapping("addOrUpdateBatch")
	public ResponseVO addOrUpdateBatch(@RequestBody List<WarnOrder> listBean) {
		this.warnOrderService.addOrUpdateBatch(listBean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据Id查询
	 */
	@RequestMapping("getWarnOrderById")
	public ResponseVO getWarnOrderById(Integer id) {
		return getSuccessResponseVO(this.warnOrderService.getWarnOrderById(id));
	}

	/**
	 * 根据Id更新
	 */
	@RequestMapping("updateWarnOrderById")
	public ResponseVO updateWarnOrderById(WarnOrder bean, Integer id) {
		this.warnOrderService.updateWarnOrderById(bean,id);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据Id删除
	 */
	@RequestMapping("deleteWarnOrderById")
	public ResponseVO deleteWarnOrderById(Integer id) {
		this.warnOrderService.deleteWarnOrderById(id);
		return getSuccessResponseVO(null);
	}

	/**
	 * 统计未处理告警数量
	 */
	@RequestMapping("countUnprocessed")
	public ResponseVO countUnprocessed() {
		Map<String, Integer> result = new HashMap<>();
		result.put("count", this.warnOrderService.countUnprocessed());
		return getSuccessResponseVO(result);
	}

}