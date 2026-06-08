package edu.nuc.light_system.controller;

import edu.nuc.light_system.entity.po.CommandLog;
import edu.nuc.light_system.entity.query.CommandLogQuery;
import edu.nuc.light_system.entity.vo.ResponseVO;
import edu.nuc.light_system.service.CommandLogService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:操作日志表Service
 * @author:Liu Wenhao
 * @date:2026/04/09
 */
@RestController
@RequestMapping("/commandLog")
public class CommandLogController extends ABaseController {

	@Resource
	private CommandLogService commandLogService;

	@RequestMapping("loadDataList")
	public ResponseVO loadDataList(CommandLogQuery query) {
		return getSuccessResponseVO(commandLogService.findListByPage(query));
	}

	/**
	 * 新增
	 */
	@RequestMapping("add")
	public ResponseVO add(CommandLog bean) {
		this.commandLogService.add(bean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 批量新增
	 */
	@RequestMapping("addBatch")
	public ResponseVO addBatch(@RequestBody List<CommandLog> listBean) {
		this.commandLogService.addBatch(listBean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 批量新增或修改
	 */
	@RequestMapping("addOrUpdateBatch")
	public ResponseVO addOrUpdateBatch(@RequestBody List<CommandLog> listBean) {
		this.commandLogService.addOrUpdateBatch(listBean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据Id查询
	 */
	@RequestMapping("getCommandLogById")
	public ResponseVO getCommandLogById(Integer id) {
		return getSuccessResponseVO(this.commandLogService.getCommandLogById(id));
	}

	/**
	 * 根据Id更新
	 */
	@RequestMapping("updateCommandLogById")
	public ResponseVO updateCommandLogById(CommandLog bean, Integer id) {
		this.commandLogService.updateCommandLogById(bean,id);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据Id删除
	 */
	@RequestMapping("deleteCommandLogById")
	public ResponseVO deleteCommandLogById(Integer id) {
		this.commandLogService.deleteCommandLogById(id);
		return getSuccessResponseVO(null);
	}

}