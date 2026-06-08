package edu.nuc.light_system.service.impl;

import edu.nuc.light_system.entity.dto.StatusReportMessage;
import edu.nuc.light_system.entity.po.ReportLight;
import edu.nuc.light_system.entity.query.ReportLightQuery;
import edu.nuc.light_system.entity.query.SimplePage;
import edu.nuc.light_system.entity.vo.PaginationResultVO;
import edu.nuc.light_system.enums.LightStateEnum;
import edu.nuc.light_system.enums.PageSize;
import edu.nuc.light_system.mappers.ReportLightMapper;
import edu.nuc.light_system.service.ReportLightService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Description:定时上报亮度表Service
 * @author:Liu Wenhao
 * @date:2026/04/09
 */
@Service("reportLightService")
public class ReportLightServiceImpl implements ReportLightService{

	private static final Logger logger = LoggerFactory.getLogger(ReportLightServiceImpl.class);

	@Resource
	private ReportLightMapper<ReportLight, ReportLightQuery> reportLightMapper;

	/**
	 * 根据条件查询列表
	 */
	public List<ReportLight> findListByParam(ReportLightQuery query) {
		return this.reportLightMapper.selectList(query);
	}
	/**
	 * 根据条件查询数量
	 */
	public Integer findCountByParam(ReportLightQuery query) {
		return this.reportLightMapper.selectCount(query);
	}
	/**
	 * 分页查询
	 */
	public PaginationResultVO<ReportLight> findListByPage(ReportLightQuery query) {
		Integer count = this.findCountByParam(query);
		Integer pageSize = query.getPageSize() == null ? PageSize.SIZE15.getSize() : query.getPageSize();
		SimplePage page = new SimplePage(query.getPageNo(), count, pageSize);
		query.setSimplePage(page);
		List<ReportLight> list = this.findListByParam(query);
		PaginationResultVO<ReportLight> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
		return result;
	}
	/**
	 * 新增
	 */
	public Integer add(ReportLight bean) {
		return this.reportLightMapper.insert(bean);
	}
	/**
	 * 批量新增
	 */
	public Integer addBatch(List<ReportLight> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.reportLightMapper.insertBatch(listBean);
	}
	/**
	 * 批量新增或修改
	 */
	public Integer addOrUpdateBatch(List<ReportLight> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.reportLightMapper.insertOrUpdateBatch(listBean);
	}
	/**
	 * 根据Id查询
	 */
	public ReportLight getReportLightById(Integer id) {
		return this.reportLightMapper.selectById(id);
	}
	/**
	 * 根据Id更新
	 */
	public Integer updateReportLightById(ReportLight bean, Integer id) {
		return this.reportLightMapper.updateById(bean,id);
	}
	/**
	 * 根据Id删除
	 */
	public Integer deleteReportLightById(Integer id) {
		return this.reportLightMapper.deleteById(id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void handleStatusReport(StatusReportMessage message) {
		//TODO:每次上报都创建一条新记录，是否有必要？
		logger.info("处理设备状态上报: {}", message.getDeviceId());
		ReportLight reportLight = new ReportLight();
		reportLight.setDeviceId(message.getDeviceId());

		if (message.getBrightNess() != 0) {
			reportLight.setLightState(LightStateEnum.OPEN.getState());
		}else {
			reportLight.setLightState(LightStateEnum.CLOSE.getState());
		}
		reportLight.setBrightNess(message.getBrightNess());
		reportLight.setAmbientLight(message.getAmbientLight());
		reportLight.setCurrentHour(message.getCurrentHour());
		reportLight.setReportTime(message.getTimestampAsDate());

		this.reportLightMapper.insertOrUpdate(reportLight);

		logger.info("设备状态上报保存成功: {}", message.getDeviceId());

	}
}