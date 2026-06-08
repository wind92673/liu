package edu.nuc.light_system.service;

import edu.nuc.light_system.entity.dto.StatusReportMessage;
import edu.nuc.light_system.entity.vo.PaginationResultVO;
import edu.nuc.light_system.entity.po.SaveInfo;
import edu.nuc.light_system.entity.query.SaveInfoQuery;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description:节能统计表Service
 * @author:Liu Wenhao
 * @date:2026/04/09
 */
public interface SaveInfoService{

	/**
	 * 根据条件查询列表
	 */
	List<SaveInfo> findListByParam(SaveInfoQuery query);

	/**
	 * 根据数量查询列表
	 */
	Integer findCountByParam(SaveInfoQuery query);

	/**
	 * 分页查询
	 */
	PaginationResultVO<SaveInfo> findListByPage(SaveInfoQuery query);

	/**
	 * 新增
	 */
	Integer add(SaveInfo bean);

	/**
	 * 批量新增
	 */
	Integer addBatch(List<SaveInfo> listBean);

	/**
	 * 批量新增或修改
	 */
	Integer addOrUpdateBatch(List<SaveInfo> listBean);

	/**
	 * 根据Id查询
	 */
	SaveInfo getSaveInfoById(Integer id);

	/**
	 * 根据Id更新
	 */
	Integer updateSaveInfoById(SaveInfo bean, Integer id);

	/**
	 * 根据Id删除
	 */
	Integer deleteSaveInfoById(Integer id);

	/**
	 * 根据DeviceId查询
	 */
	SaveInfo getSaveInfoByDeviceId(String deviceId);

	/**
	 * 根据DeviceId更新
	 */
	Integer updateSaveInfoByDeviceId(SaveInfo bean, String deviceId);


	/**
	 * 状态上报自动处理
	 */
	void handleStatusReport(StatusReportMessage message);
	/**
	 * 根据DeviceId、时间和小时更新
	 */
	Integer updateSaveInfoByDeviceIdAndTime(SaveInfo bean, String deviceId, Date currentTime, Integer currentHour);

	/**
	 * 按天查询所有记录
	 */
	List<SaveInfo> getListByDay(SaveInfoQuery query);

	/**
	 * 按月汇总查询（按天累加）
	 */
	List<Map<String, Object>> getMonthlySummary(SaveInfoQuery query);

	/**
	 * 按年汇总查询（按月累加）
	 */
	List<Map<String, Object>> getYearlySummary(SaveInfoQuery query);

	/**
	 * 导出Excel文件
	 */
	void exportExcel(SaveInfoQuery query, HttpServletResponse response);

}