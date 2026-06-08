package edu.nuc.light_system.service.impl;

import edu.nuc.light_system.entity.po.WarnOrder;
import edu.nuc.light_system.entity.query.WarnOrderQuery;
import edu.nuc.light_system.entity.query.SimplePage;
import edu.nuc.light_system.entity.vo.PaginationResultVO;
import edu.nuc.light_system.enums.PageSize;
import edu.nuc.light_system.mappers.WarnOrderMapper;
import edu.nuc.light_system.service.WarnOrderService;
import edu.nuc.light_system.utils.SendWeChatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @Description:告警工单表Service
 * @author:Liu Wenhao
 * @date:2026/04/09
 */
@Service("warnOrderService")
public class WarnOrderServiceImpl implements WarnOrderService{

	private static final Logger logger = LoggerFactory.getLogger(WarnOrderServiceImpl.class);

	@Resource
	private WarnOrderMapper<WarnOrder, WarnOrderQuery> warnOrderMapper;

	/**
	 * 根据条件查询列表
	 */
	public List<WarnOrder> findListByParam(WarnOrderQuery query) {
		return this.warnOrderMapper.selectList(query);
	}
	/**
	 * 根据条件查询数量
	 */
	public Integer findCountByParam(WarnOrderQuery query) {
		return this.warnOrderMapper.selectCount(query);
	}
	/**
	 * 分页查询
	 */
	public PaginationResultVO<WarnOrder> findListByPage(WarnOrderQuery query) {
		Integer count = this.findCountByParam(query);
		Integer pageSize = query.getPageSize() == null ? PageSize.SIZE15.getSize() : query.getPageSize();
		SimplePage page = new SimplePage(query.getPageNo(), count, pageSize);
		query.setSimplePage(page);
		List<WarnOrder> list = this.findListByParam(query);
		PaginationResultVO<WarnOrder> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
		return result;
	}
	/**
	 * 新增
	 */
	public Integer add(WarnOrder bean) {
		Integer result = this.warnOrderMapper.insert(bean);

		if (result > 0) {
			sendWeChatNotification(bean);
		}

		return result;
	}
	/**
	 * 批量新增
	 */
	public Integer addBatch(List<WarnOrder> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.warnOrderMapper.insertBatch(listBean);
	}
	/**
	 * 批量新增或修改
	 */
	public Integer addOrUpdateBatch(List<WarnOrder> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.warnOrderMapper.insertOrUpdateBatch(listBean);
	}
	/**
	 * 根据Id查询
	 */
	public WarnOrder getWarnOrderById(Integer id) {
		return this.warnOrderMapper.selectById(id);
	}
	/**
	 * 根据Id更新
	 */
	public Integer updateWarnOrderById(WarnOrder bean, Integer id) {
		return this.warnOrderMapper.updateById(bean,id);
	}
	/**
	 * 根据Id删除
	 */
	public Integer deleteWarnOrderById(Integer id) {
		return this.warnOrderMapper.deleteById(id);
	}

	@Override
	public Integer countUnprocessed() {
		WarnOrderQuery query = new WarnOrderQuery();
		query.setState(0); // 0：未处理
		return this.warnOrderMapper.selectCount(query);
	}

	private void sendWeChatNotification(WarnOrder warnOrder) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String reportTimeStr = warnOrder.getReportTime() != null ?
					sdf.format(warnOrder.getReportTime()) : "未知";

			StringBuilder message = new StringBuilder();
			message.append("【故障工单通知】\n");
			message.append("设备ID: ").append(warnOrder.getDeviceId() != null ? warnOrder.getDeviceId() : "未知").append("\n");
			message.append("告警信息: ").append(warnOrder.getReportInfo() != null ? warnOrder.getReportInfo() : "未知").append("\n");
			message.append("告警时间: ").append(reportTimeStr).append("\n");
			message.append("请及时处理！");

			SendWeChatUtils.sendMsg(message.toString());

			logger.info("已发送故障工单企业微信通知，设备ID: {}", warnOrder.getDeviceId());
		} catch (Exception e) {
			logger.error("发送故障工单企业微信通知失败", e);
		}
	}
}