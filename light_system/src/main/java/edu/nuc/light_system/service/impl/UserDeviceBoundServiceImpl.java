package edu.nuc.light_system.service.impl;

import edu.nuc.light_system.entity.po.UserDeviceBound;
import edu.nuc.light_system.entity.query.UserDeviceBoundQuery;
import edu.nuc.light_system.entity.query.SimplePage;
import edu.nuc.light_system.entity.vo.PaginationResultVO;
import edu.nuc.light_system.enums.PageSize;
import edu.nuc.light_system.mappers.UserDeviceBoundMapper;
import edu.nuc.light_system.service.UserDeviceBoundService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:用户设备关系表Service
 * @author:Liu Wenhao
 * @date:2026/04/09
 */
@Service("userDeviceBoundService")
public class UserDeviceBoundServiceImpl implements UserDeviceBoundService{

	@Resource
	private UserDeviceBoundMapper<UserDeviceBound, UserDeviceBoundQuery> userDeviceBoundMapper;

	/**
	 * 根据条件查询列表
	 */
	public List<UserDeviceBound> findListByParam(UserDeviceBoundQuery query) {
		return this.userDeviceBoundMapper.selectList(query);
	}
	/**
	 * 根据条件查询数量
	 */
	public Integer findCountByParam(UserDeviceBoundQuery query) {
		return this.userDeviceBoundMapper.selectCount(query);
	}
	/**
	 * 分页查询
	 */
	public PaginationResultVO<UserDeviceBound> findListByPage(UserDeviceBoundQuery query) {
		Integer count = this.findCountByParam(query);
		Integer pageSize = query.getPageSize() == null ? PageSize.SIZE15.getSize() : query.getPageSize();
		SimplePage page = new SimplePage(query.getPageNo(), count, pageSize);
		query.setSimplePage(page);
		List<UserDeviceBound> list = this.findListByParam(query);
		PaginationResultVO<UserDeviceBound> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
		return result;
	}
	/**
	 * 新增
	 */
	public Integer add(UserDeviceBound bean) {
		return this.userDeviceBoundMapper.insert(bean);
	}
	/**
	 * 批量新增
	 */
	public Integer addBatch(List<UserDeviceBound> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.userDeviceBoundMapper.insertBatch(listBean);
	}
	/**
	 * 批量新增或修改
	 */
	public Integer addOrUpdateBatch(List<UserDeviceBound> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.userDeviceBoundMapper.insertOrUpdateBatch(listBean);
	}
	/**
	 * 根据Id查询
	 */
	public UserDeviceBound getUserDeviceBoundById(Integer id) {
		return this.userDeviceBoundMapper.selectById(id);
	}
	/**
	 * 根据Id更新
	 */
	public Integer updateUserDeviceBoundById(UserDeviceBound bean, Integer id) {
		return this.userDeviceBoundMapper.updateById(bean,id);
	}
	/**
	 * 根据Id删除
	 */
	public Integer deleteUserDeviceBoundById(Integer id) {
		return this.userDeviceBoundMapper.deleteById(id);
	}
}