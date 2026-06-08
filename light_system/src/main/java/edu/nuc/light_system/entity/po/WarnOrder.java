package edu.nuc.light_system.entity.po;

import edu.nuc.light_system.enums.DateTimePatternEnum;
import edu.nuc.light_system.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description:告警工单表
 * @author:Liu Wenhao
 * @date:2026/04/09
 */
public class WarnOrder implements Serializable {
	/**
	 * 自增id
	 */
	private Integer id;


	/**
	 * 设备id
	 */
	private String deviceId;


	/**
	 * 告警信息
	 */
	private String reportInfo;


	/**
	 * 告警时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date reportTime;


	/**
	 * 处理人员id
	 */
	private String userId;


	/**
	 * 是否处理，0：未处理，1：已处理
	 */
	private Integer state;


	/**
	 * 处理时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date repairTime;


	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceId() {
		return this.deviceId;
	}

	public void setReportInfo(String reportInfo) {
		this.reportInfo = reportInfo;
	}

	public String getReportInfo() {
		return this.reportInfo;
	}

	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
	}

	public Date getReportTime() {
		return this.reportTime;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getState() {
		return this.state;
	}

	public void setRepairTime(Date repairTime) {
		this.repairTime = repairTime;
	}

	public Date getRepairTime() {
		return this.repairTime;
	}

	@Override
	public String toString() {
		return "自增id:" + (id == null ? "空" : id) + ",设备id:" + (deviceId == null ? "空" : deviceId) + ",告警信息:" + (reportInfo == null ? "空" : reportInfo) + ",告警时间:" + (reportTime == null ? "空" : DateUtils.format(reportTime,DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern())) + ",处理人员id:" + (userId == null ? "空" : userId) + ",是否处理，0：未处理，1：已处理:" + (state == null ? "空" : state) + ",处理时间:" + (repairTime == null ? "空" : DateUtils.format(repairTime,DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern()));
	}
}