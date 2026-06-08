package edu.nuc.light_system.service.impl;

import com.alibaba.excel.EasyExcel;
import edu.nuc.light_system.entity.constant.Constants;
import edu.nuc.light_system.entity.dto.StatusReportMessage;
import edu.nuc.light_system.entity.po.DeviceInfo;
import edu.nuc.light_system.entity.po.SaveInfo;
import edu.nuc.light_system.entity.query.SaveInfoQuery;
import edu.nuc.light_system.entity.query.SimplePage;
import edu.nuc.light_system.entity.vo.PaginationResultVO;
import edu.nuc.light_system.entity.vo.SaveInfoExcelVO;
import edu.nuc.light_system.entity.vo.SaveInfoMonthlyExcelVO;
import edu.nuc.light_system.entity.vo.SaveInfoYearlyExcelVO;
import edu.nuc.light_system.enums.DateTimePatternEnum;
import edu.nuc.light_system.enums.PageSize;
import edu.nuc.light_system.mappers.SaveInfoMapper;
import edu.nuc.light_system.service.DeviceInfoService;
import edu.nuc.light_system.service.SaveInfoService;
import edu.nuc.light_system.utils.CopyTools;
import edu.nuc.light_system.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description:节能统计表Service
 * @author:Liu Wenhao
 * @date:2026/04/09
 */
@Service("saveInfoService")
public class SaveInfoServiceImpl implements SaveInfoService{

	private static final Logger logger = LoggerFactory.getLogger(SaveInfoServiceImpl.class);

	@Resource
	private SaveInfoMapper<SaveInfo, SaveInfoQuery> saveInfoMapper;

	@Resource
	private DeviceInfoService deviceInfoService;

	/**
	 * 根据条件查询列表
	 */
	public List<SaveInfo> findListByParam(SaveInfoQuery query) {
		return this.saveInfoMapper.selectList(query);
	}
	/**
	 * 根据条件查询数量
	 */
	public Integer findCountByParam(SaveInfoQuery query) {
		return this.saveInfoMapper.selectCount(query);
	}
	/**
	 * 分页查询
	 */
	public PaginationResultVO<SaveInfo> findListByPage(SaveInfoQuery query) {
		Integer count = this.findCountByParam(query);
		Integer pageSize = query.getPageSize() == null ? PageSize.SIZE15.getSize() : query.getPageSize();
		SimplePage page = new SimplePage(query.getPageNo(), count, pageSize);
		query.setSimplePage(page);
		List<SaveInfo> list = this.findListByParam(query);
		PaginationResultVO<SaveInfo> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
		return result;
	}
	/**
	 * 新增
	 */
	public Integer add(SaveInfo bean) {
		return this.saveInfoMapper.insert(bean);
	}
	/**
	 * 批量新增
	 */
	public Integer addBatch(List<SaveInfo> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.saveInfoMapper.insertBatch(listBean);
	}
	/**
	 * 批量新增或修改
	 */
	public Integer addOrUpdateBatch(List<SaveInfo> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.saveInfoMapper.insertOrUpdateBatch(listBean);
	}
	/**
	 * 根据Id查询
	 */
	public SaveInfo getSaveInfoById(Integer id) {
		return this.saveInfoMapper.selectById(id);
	}
	/**
	 * 根据Id更新
	 */
	public Integer updateSaveInfoById(SaveInfo bean, Integer id) {
		return this.saveInfoMapper.updateById(bean,id);
	}
	/**
	 * 根据Id删除
	 */
	public Integer deleteSaveInfoById(Integer id) {
		return this.saveInfoMapper.deleteById(id);
	}

	@Override
	public SaveInfo getSaveInfoByDeviceId(String deviceId) {
		return this.saveInfoMapper.selectByDeviceId(deviceId);
	}

	@Override
	public Integer updateSaveInfoByDeviceId(SaveInfo bean, String deviceId) {
		return this.saveInfoMapper.updateByDeviceId(bean, deviceId);
	}

	@Override
	public Integer updateSaveInfoByDeviceIdAndTime(SaveInfo bean, String deviceId, Date currentTime, Integer currentHour) {
		return this.saveInfoMapper.updateByDeviceIdAndTime(bean, deviceId, currentTime, currentHour);

	}

	@Override
	public List<SaveInfo> getListByDay(SaveInfoQuery query) {
		return this.saveInfoMapper.selectListByDay(query);
	}

	@Override
	public List<Map<String, Object>> getMonthlySummary(SaveInfoQuery query) {
		return this.saveInfoMapper.selectMonthlySummary(query);
	}

	@Override
	public List<Map<String, Object>> getYearlySummary(SaveInfoQuery query) {
		return this.saveInfoMapper.selectYearlySummary(query);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void handleStatusReport(StatusReportMessage message) {
		try {
			String deviceId = message.getDeviceId();
			if (deviceId == null || deviceId.isEmpty()) {
				logger.error("设备ID为空，无法处理状态上报");
				return;
			}

			Integer brightness = message.getBrightNess();
			if (brightness == null) {
				logger.error("亮度值为空，无法处理状态上报，设备ID: {}", deviceId);
				return;
			}

			Date timestamp = message.getTimestampAsDate();
			if (timestamp == null) {
				logger.error("时间戳为空，无法处理状态上报，设备ID: {}", deviceId);
				return;
			}

			Integer currentHour = message.getCurrentHour();

			DeviceInfo deviceInfo = deviceInfoService.getDeviceInfoByDeviceId(deviceId);
			if (deviceInfo == null) {
				logger.error("设备不存在，无法处理状态上报，设备ID: {}", deviceId);
				return;
			}

			Integer reportInterval = deviceInfo.getReportInterval();
			if (reportInterval == null || reportInterval <= 0) {
				logger.warn("设备上报间隔异常，使用默认值60秒，设备ID: {}", deviceId);
				reportInterval = 60;
			}

			int brightnessInt = brightness.intValue();

			double power = (100 - brightnessInt) * reportInterval * Constants.POWER_NUMBER;

			double carbon = power * Constants.CARBON_NUMBER;

			String dateStr = DateUtils.format(timestamp, DateTimePatternEnum.YYYY_MM_DD.getPattern());
			Date currentTime = DateUtils.parse(dateStr, DateTimePatternEnum.YYYY_MM_DD.getPattern());

			SaveInfoQuery query = new SaveInfoQuery();
			query.setDeviceId(deviceId);
			query.setCurrentTime(currentTime);
			query.setCurrentHour(currentHour);

			List<SaveInfo> existList = this.saveInfoMapper.selectList(query);

			if (existList != null && !existList.isEmpty()) {
				SaveInfo existInfo = existList.get(0);

				SaveInfo updateInfo = new SaveInfo();
				updateInfo.setPower(String.valueOf(Double.parseDouble(existInfo.getPower()) + power));
				updateInfo.setCarbon(String.valueOf(Double.parseDouble(existInfo.getCarbon()) + carbon));

				this.saveInfoMapper.updateByDeviceIdAndTime(updateInfo, deviceId, currentTime, currentHour);

				logger.info("更新节能统计数据，设备ID: {}, 日期: {}, 小时: {}, 累计节电量: {}, 累计节碳量: {}",
						deviceId, dateStr, currentHour, updateInfo.getPower(), updateInfo.getCarbon());
			} else {
				SaveInfo newInfo = new SaveInfo();
				newInfo.setDeviceId(deviceId);
				newInfo.setCurrentTime(currentTime);
				newInfo.setCurrentHour(currentHour);
				newInfo.setPower(String.valueOf(power));
				newInfo.setCarbon(String.valueOf(carbon));

				this.saveInfoMapper.insert(newInfo);

				logger.info("新增节能统计数据，设备ID: {}, 日期: {}, 小时: {}, 节电量: {}, 节碳量: {}",
						deviceId, dateStr, currentHour, power, carbon);
			}

		} catch (Exception e) {
			logger.error("处理状态上报失败，设备ID: {}, 错误信息: {}", message.getDeviceId(), e.getMessage(), e);
			throw new RuntimeException("处理状态上报失败", e);
		}

	}

	@Override
	public void exportExcel(SaveInfoQuery query, HttpServletResponse response) {
		try {
			boolean isMonthlyExport = (query.getYear() != null || query.getMonth() != null);
			boolean isYearlyExport = (query.getYear() != null && query.getMonth() == null);

			if (isYearlyExport) {
				exportYearlyExcel(query, response);
			} else if (isMonthlyExport) {
				exportMonthlyExcel(query, response);
			} else {
				exportDailyExcel(query, response);
			}

		} catch (IOException e) {
			logger.error("导出节能统计数据失败，错误信息: {}", e.getMessage(), e);
			throw new RuntimeException("导出Excel失败", e);
		}
	}

	private void exportDailyExcel(SaveInfoQuery query, HttpServletResponse response) throws IOException {
		List<SaveInfo> dataList = this.findListByParam(query);
		List<SaveInfoExcelVO> excelDataList = CopyTools.copyList(dataList, SaveInfoExcelVO.class);

		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		response.setCharacterEncoding("utf-8");

		String fileName = URLEncoder.encode("节能统计数据_" + DateUtils.format(new Date(), DateTimePatternEnum.YYYY_MM_DD.getPattern()), "UTF-8").replaceAll("\\+", "%20");
		response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");

		EasyExcel.write(response.getOutputStream(), SaveInfoExcelVO.class)
				.autoCloseStream(false)
				.sheet("节能统计数据")
				.doWrite(excelDataList);

		logger.info("导出节能统计明细数据成功，共{}条记录", excelDataList.size());
	}

	private void exportMonthlyExcel(SaveInfoQuery query, HttpServletResponse response) throws IOException {
		List<Map<String, Object>> summaryData = this.saveInfoMapper.selectMonthlySummary(query);

		List<SaveInfoMonthlyExcelVO> excelDataList = new java.util.ArrayList<>();
		for (Map<String, Object> map : summaryData) {
			SaveInfoMonthlyExcelVO vo = new SaveInfoMonthlyExcelVO();
			Object dateObj = map.get("date");
			if (dateObj instanceof Date) {
				vo.setDate((Date) dateObj);
			} else if (dateObj instanceof String) {
				vo.setDate(DateUtils.parse((String) dateObj, DateTimePatternEnum.YYYY_MM_DD.getPattern()));
			}
			vo.setTotalPower(map.get("total_power").toString());
			vo.setTotalCarbon(map.get("total_carbon").toString());
			excelDataList.add(vo);
		}

		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		response.setCharacterEncoding("utf-8");

		String fileName = URLEncoder.encode("月度节能汇总_" + DateUtils.format(new Date(), DateTimePatternEnum.YYYY_MM_DD.getPattern()), "UTF-8").replaceAll("\\+", "%20");
		response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");

		EasyExcel.write(response.getOutputStream(), SaveInfoMonthlyExcelVO.class)
				.autoCloseStream(false)
				.sheet("月度节能汇总")
				.doWrite(excelDataList);

		logger.info("导出月度节能汇总数据成功，共{}条记录", excelDataList.size());
	}

	private void exportYearlyExcel(SaveInfoQuery query, HttpServletResponse response) throws IOException {
		List<Map<String, Object>> summaryData = this.saveInfoMapper.selectYearlySummary(query);

		List<SaveInfoYearlyExcelVO> excelDataList = new java.util.ArrayList<>();
		for (Map<String, Object> map : summaryData) {
			SaveInfoYearlyExcelVO vo = new SaveInfoYearlyExcelVO();
			vo.setMonth(map.get("month").toString());
			vo.setTotalPower(map.get("total_power").toString());
			vo.setTotalCarbon(map.get("total_carbon").toString());
			excelDataList.add(vo);
		}

		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		response.setCharacterEncoding("utf-8");

		String fileName = URLEncoder.encode("年度节能汇总_" + DateUtils.format(new Date(), DateTimePatternEnum.YYYY_MM_DD.getPattern()), "UTF-8").replaceAll("\\+", "%20");
		response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");

		EasyExcel.write(response.getOutputStream(), SaveInfoYearlyExcelVO.class)
				.autoCloseStream(false)
				.sheet("年度节能汇总")
				.doWrite(excelDataList);

		logger.info("导出年度节能汇总数据成功，共{}条记录", excelDataList.size());
	}
}