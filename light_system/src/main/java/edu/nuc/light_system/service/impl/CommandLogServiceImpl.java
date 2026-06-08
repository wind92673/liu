package edu.nuc.light_system.service.impl;

import edu.nuc.light_system.entity.po.CommandLog;
import edu.nuc.light_system.entity.query.CommandLogQuery;
import edu.nuc.light_system.entity.query.SimplePage;
import edu.nuc.light_system.entity.vo.PaginationResultVO;
import edu.nuc.light_system.enums.PageSize;
import edu.nuc.light_system.mappers.CommandLogMapper;
import edu.nuc.light_system.service.CommandLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:操作日志表Service
 * @author:Liu Wenhao
 * @date:2026/04/09
 */
@Service("commandLogService")
public class CommandLogServiceImpl implements CommandLogService{

	@Resource
	private CommandLogMapper<CommandLog, CommandLogQuery> commandLogMapper;

	/**
	 * 根据条件查询列表
	 */
	public List<CommandLog> findListByParam(CommandLogQuery query) {
		return this.commandLogMapper.selectList(query);
	}
	/**
	 * 根据条件查询数量
	 */
	public Integer findCountByParam(CommandLogQuery query) {
		return this.commandLogMapper.selectCount(query);
	}
	/**
	 * 分页查询
	 */
	public PaginationResultVO<CommandLog> findListByPage(CommandLogQuery query) {
		Integer count = this.findCountByParam(query);
		Integer pageSize = query.getPageSize() == null ? PageSize.SIZE15.getSize() : query.getPageSize();
		SimplePage page = new SimplePage(query.getPageNo(), count, pageSize);
		query.setSimplePage(page);
		List<CommandLog> list = this.findListByParam(query);
		PaginationResultVO<CommandLog> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
		return result;
	}
	/**
	 * 新增
	 */
	public Integer add(CommandLog bean) {
		return this.commandLogMapper.insert(bean);
	}
	/**
	 * 批量新增
	 */
	public Integer addBatch(List<CommandLog> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.commandLogMapper.insertBatch(listBean);
	}
	/**
	 * 批量新增或修改
	 */
	public Integer addOrUpdateBatch(List<CommandLog> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.commandLogMapper.insertOrUpdateBatch(listBean);
	}
	/**
	 * 根据Id查询
	 */
	public CommandLog getCommandLogById(Integer id) {
		return this.commandLogMapper.selectById(id);
	}
	/**
	 * 根据Id更新
	 */
	public Integer updateCommandLogById(CommandLog bean, Integer id) {
		return this.commandLogMapper.updateById(bean,id);
	}
	/**
	 * 根据Id删除
	 */
	public Integer deleteCommandLogById(Integer id) {
		return this.commandLogMapper.deleteById(id);
	}
}