package edu.nuc.light_system.service.impl;

import edu.nuc.light_system.entity.po.DeviceGroupBound;
import edu.nuc.light_system.entity.query.DeviceGroupBoundQuery;
import edu.nuc.light_system.entity.query.SimplePage;
import edu.nuc.light_system.entity.vo.PaginationResultVO;
import edu.nuc.light_system.enums.PageSize;
import edu.nuc.light_system.mappers.DeviceGroupBoundMapper;
import edu.nuc.light_system.service.DeviceGroupBoundService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:设备分组表Service
 * @author:Liu Wenhao
 * @date:2026/04/09
 */
@Service("deviceGroupBoundService")
public class DeviceGroupBoundServiceImpl implements DeviceGroupBoundService{

	@Resource
	private DeviceGroupBoundMapper<DeviceGroupBound, DeviceGroupBoundQuery> deviceGroupBoundMapper;

	/**
	 * 根据条件查询列表
	 */
	public List<DeviceGroupBound> findListByParam(DeviceGroupBoundQuery query) {
		return this.deviceGroupBoundMapper.selectList(query);
	}
	/**
	 * 根据条件查询数量
	 */
	public Integer findCountByParam(DeviceGroupBoundQuery query) {
		return this.deviceGroupBoundMapper.selectCount(query);
	}
	/**
	 * 分页查询
	 */
	public PaginationResultVO<DeviceGroupBound> findListByPage(DeviceGroupBoundQuery query) {
		Integer count = this.findCountByParam(query);
		Integer pageSize = query.getPageSize() == null ? PageSize.SIZE15.getSize() : query.getPageSize();
		SimplePage page = new SimplePage(query.getPageNo(), count, pageSize);
		query.setSimplePage(page);
		List<DeviceGroupBound> list = this.findListByParam(query);
		PaginationResultVO<DeviceGroupBound> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
		return result;
	}
	/**
	 * 新增
	 */
	public Integer add(DeviceGroupBound bean) {
		return this.deviceGroupBoundMapper.insert(bean);
	}
	/**
	 * 批量新增
	 */
	public Integer addBatch(List<DeviceGroupBound> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.deviceGroupBoundMapper.insertBatch(listBean);
	}
	/**
	 * 批量新增或修改
	 */
	public Integer addOrUpdateBatch(List<DeviceGroupBound> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.deviceGroupBoundMapper.insertOrUpdateBatch(listBean);
	}
	/**
	 * 根据Id查询
	 */
	public DeviceGroupBound getDeviceGroupBoundById(Integer id) {
		return this.deviceGroupBoundMapper.selectById(id);
	}
	/**
	 * 根据Id更新
	 */
	public Integer updateDeviceGroupBoundById(DeviceGroupBound bean, Integer id) {
		return this.deviceGroupBoundMapper.updateById(bean,id);
	}
	/**
	 * 根据Id删除
	 */
	public Integer deleteDeviceGroupBoundById(Integer id) {
		return this.deviceGroupBoundMapper.deleteById(id);
	}
}