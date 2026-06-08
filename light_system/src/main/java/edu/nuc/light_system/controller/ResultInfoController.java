package edu.nuc.light_system.controller;

import edu.nuc.light_system.entity.po.ResultInfo;
import edu.nuc.light_system.entity.query.ResultInfoQuery;
import edu.nuc.light_system.entity.vo.ResponseVO;
import edu.nuc.light_system.service.ResultInfoService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:处理结果表Service
 * @author:Liu Wenhao
 * @date:2026/04/09
 */
@RestController
@RequestMapping("/resultInfo")
public class ResultInfoController extends ABaseController {

	@Resource
	private ResultInfoService resultInfoService;

	@RequestMapping("loadDataList")
	public ResponseVO loadDataList(ResultInfoQuery query) {
		return getSuccessResponseVO(resultInfoService.findListByPage(query));
	}

	/**
	 * 新增
	 */
	@RequestMapping("add")
	public ResponseVO add(ResultInfo bean) {
		this.resultInfoService.add(bean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 批量新增
	 */
	@RequestMapping("addBatch")
	public ResponseVO addBatch(@RequestBody List<ResultInfo> listBean) {
		this.resultInfoService.addBatch(listBean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 批量新增或修改
	 */
	@RequestMapping("addOrUpdateBatch")
	public ResponseVO addOrUpdateBatch(@RequestBody List<ResultInfo> listBean) {
		this.resultInfoService.addOrUpdateBatch(listBean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据Id查询
	 */
	@RequestMapping("getResultInfoById")
	public ResponseVO getResultInfoById(Integer id) {
		return getSuccessResponseVO(this.resultInfoService.getResultInfoById(id));
	}

	/**
	 * 根据Id更新
	 */
	@RequestMapping("updateResultInfoById")
	public ResponseVO updateResultInfoById(ResultInfo bean, Integer id) {
		this.resultInfoService.updateResultInfoById(bean,id);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据Id删除
	 */
	@RequestMapping("deleteResultInfoById")
	public ResponseVO deleteResultInfoById(Integer id) {
		this.resultInfoService.deleteResultInfoById(id);
		return getSuccessResponseVO(null);
	}

}