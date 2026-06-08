package edu.nuc.light_system.entity.query;

import java.util.Date;

/**
 * @Description:定时上报亮度表查询对象
 * @author:Liu Wenhao
 * @date:2026/04/09
 */
public class ReportLightQuery extends BaseQuery {
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
	 * 灯光状态
	 */
	private Integer lightState;


	/**
	 * 灯光亮度
	 */
	private Integer brightNess;


	/**
	 * 环境亮度
	 */
	private Integer ambientLight;


	/**
	 * 当前小时，0-23
	 */
	private Integer currentHour;


	/**
	 * 上报时间
	 */
	private Date reportTime;


	private String reportTimeStart;

	private String reportTimeEnd;

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

	public void setLightState(Integer lightState) {
		this.lightState = lightState;
	}

	public Integer getLightState() {
		return this.lightState;
	}

	public void setBrightNess(Integer brightNess) {
		this.brightNess = brightNess;
	}

	public Integer getBrightNess() {
		return this.brightNess;
	}

	public void setAmbientLight(Integer ambientLight) {
		this.ambientLight = ambientLight;
	}

	public Integer getAmbientLight() {
		return this.ambientLight;
	}

	public void setCurrentHour(Integer currentHour) {
		this.currentHour = currentHour;
	}

	public Integer getCurrentHour() {
		return this.currentHour;
	}

	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
	}

	public Date getReportTime() {
		return this.reportTime;
	}

	public void setDeviceIdFuzzy(String deviceIdFuzzy) {
		this.deviceIdFuzzy = deviceIdFuzzy;
	}

	public String getDeviceIdFuzzy() {
		return this.deviceIdFuzzy;
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

}