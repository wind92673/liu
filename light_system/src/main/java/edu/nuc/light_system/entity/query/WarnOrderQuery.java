package edu.nuc.light_system.entity.query;

import java.util.Date;

/**
 * @Description:告警工单表查询对象
 * @author:Liu Wenhao
 * @date:2026/04/09
 */
public class WarnOrderQuery extends BaseQuery {
	/**
	 * 自增id
	 */
	private Integer id;


	/**
	 * 设备id
	 */
	private String deviceId;


	private String deviceIdFuzzy;

	/**
	 * 告警信息
	 */
	private String reportInfo;


	private String reportInfoFuzzy;

	/**
	 * 告警时间
	 */
	private Date reportTime;


	private String reportTimeStart;

	private String reportTimeEnd;

	/**
	 * 处理人员id
	 */
	private String userId;


	private String userIdFuzzy;

	/**
	 * 是否处理，0：未处理，1：已处理
	 */
	private Integer state;


	/**
	 * 处理时间
	 */
	private Date repairTime;


	private String repairTimeStart;

	private String repairTimeEnd;

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

	public void setDeviceIdFuzzy(String deviceIdFuzzy) {
		this.deviceIdFuzzy = deviceIdFuzzy;
	}

	public String getDeviceIdFuzzy() {
		return this.deviceIdFuzzy;
	}

	public void setReportInfoFuzzy(String reportInfoFuzzy) {
		this.reportInfoFuzzy = reportInfoFuzzy;
	}

	public String getReportInfoFuzzy() {
		return this.reportInfoFuzzy;
	}

	public void setReportTimeStart(String reportTimeStart) {
		this.reportTimeStart = reportTimeStart;
	}

	public String getReportTimeStart() {
		return this.reportTimeStart;
	}

	public void setReportTimeEnd(String reportTimeEnd) {
		this.reportTimeEnd = reportTimeEnd;
	}

	public String getReportTimeEnd() {
		return this.reportTimeEnd;
	}

	public void setUserIdFuzzy(String userIdFuzzy) {
		this.userIdFuzzy = userIdFuzzy;
	}

	public String getUserIdFuzzy() {
		return this.userIdFuzzy;
	}

	public void setRepairTimeStart(String repairTimeStart) {
		this.repairTimeStart = repairTimeStart;
	}

	public String getRepairTimeStart() {
		return this.repairTimeStart;
	}

	public void setRepairTimeEnd(String repairTimeEnd) {
		this.repairTimeEnd = repairTimeEnd;
	}

	public String getRepairTimeEnd() {
		return this.repairTimeEnd;
	}

}