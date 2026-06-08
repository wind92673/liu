package edu.nuc.light_system.service.impl;

import edu.nuc.light_system.entity.po.ResultInfo;
import edu.nuc.light_system.entity.query.ResultInfoQuery;
import edu.nuc.light_system.entity.query.SimplePage;
import edu.nuc.light_system.entity.vo.PaginationResultVO;
import edu.nuc.light_system.enums.PageSize;
import edu.nuc.light_system.mappers.ResultInfoMapper;
import edu.nuc.light_system.service.ResultInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:处理结果表Service
 * @author:Liu Wenhao
 * @date:2026/04/09
 */
@Service("resultInfoService")
public class ResultInfoServiceImpl implements ResultInfoService{

	@Resource
	private ResultInfoMapper<ResultInfo, ResultInfoQuery> resultInfoMapper;

	/**
	 * 根据条件查询列表
	 */
	public List<ResultInfo> findListByParam(ResultInfoQuery query) {
		return this.resultInfoMapper.selectList(query);
	}
	/**
	 * 根据条件查询数量
	 */
	public Integer findCountByParam(ResultInfoQuery query) {
		return this.resultInfoMapper.selectCount(query);
	}
	/**
	 * 分页查询
	 */
	public PaginationResultVO<ResultInfo> findListByPage(ResultInfoQuery query) {
		Integer count = this.findCountByParam(query);
		Integer pageSize = query.getPageSize() == null ? PageSize.SIZE15.getSize() : query.getPageSize();
		SimplePage page = new SimplePage(query.getPageNo(), count, pageSize);
		query.setSimplePage(page);
		List<ResultInfo> list = this.findListByParam(query);
		PaginationResultVO<ResultInfo> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
		return result;
	}
	/**
	 * 新增
	 */
	public Integer add(ResultInfo bean) {
		return this.resultInfoMapper.insert(bean);
	}
	/**
	 * 批量新增
	 */
	public Integer addBatch(List<ResultInfo> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.resultInfoMapper.insertBatch(listBean);
	}
	/**
	 * 批量新增或修改
	 */
	public Integer addOrUpdateBatch(List<ResultInfo> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.resultInfoMapper.insertOrUpdateBatch(listBean);
	}
	/**
	 * 根据Id查询
	 */
	public ResultInfo getResultInfoById(Integer id) {
		return this.resultInfoMapper.selectById(id);
	}
	/**
	 * 根据Id更新
	 */
	public Integer updateResultInfoById(ResultInfo bean, Integer id) {
		return this.resultInfoMapper.updateById(bean,id);
	}
	/**
	 * 根据Id删除
	 */
	public Integer deleteResultInfoById(Integer id) {
		return this.resultInfoMapper.deleteById(id);
	}
}