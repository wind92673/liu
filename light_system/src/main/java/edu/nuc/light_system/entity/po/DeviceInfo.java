package edu.nuc.light_system.entity.po;


import java.io.Serializable;

/**
 * @Description:设备信息表
 * @author:Liu Wenhao
 * @date:2026/04/13
 */
public class DeviceInfo implements Serializable {
	/**
	 * 设备主键
	 */
	private String deviceId;


	/**
	 * 运行状态,0:自动模式,1:手动模式
	 */
	private Integer systemMode;


	/**
	 * 光照阈值
	 */
	private Integer lightThreshold;


	/**
	 * 白天开始时间
	 */
	private Integer dayStart;


	/**
	 * 夜晚开始时间
	 */
	private Integer dayEnd;


	/**
	 * 在线状态，0：在线，1：离线
	 */
	private Integer online;


	/**
	 * 上报间隔
	 */
	private Integer reportInterval;


	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceId() {
		return this.deviceId;
	}

	public void setSystemMode(Integer systemMode) {
		this.systemMode = systemMode;
	}

	public Integer getSystemMode() {
		return this.systemMode;
	}

	public void setLightThreshold(Integer lightThreshold) {
		this.lightThreshold = lightThreshold;
	}

	public Integer getLightThreshold() {
		return this.lightThreshold;
	}

	public void setDayStart(Integer dayStart) {
		this.dayStart = dayStart;
	}

	public Integer getDayStart() {
		return this.dayStart;
	}

	public void setDayEnd(Integer dayEnd) {
		this.dayEnd = dayEnd;
	}

	public Integer getDayEnd() {
		return this.dayEnd;
	}

	public void setOnline(Integer online) {
		this.online = online;
	}

	public Integer getOnline() {
		return this.online;
	}

	public void setReportInterval(Integer reportInterval) {
		this.reportInterval = reportInterval;
	}

	public Integer getReportInterval() {
		return this.reportInterval;
	}

	@Override
	public String toString() {
		return "设备主键:" + (deviceId == null ? "空" : deviceId) + ",运行状态,0:自动模式,1:手动模式:" + (systemMode == null ? "空" : systemMode) + ",光照阈值:" + (lightThreshold == null ? "空" : lightThreshold) + ",白天开始时间:" + (dayStart == null ? "空" : dayStart) + ",夜晚开始时间:" + (dayEnd == null ? "空" : dayEnd) + ",在线状态，0：在线，1：离线:" + (online == null ? "空" : online) + ",上报间隔:" + (reportInterval == null ? "空" : reportInterval);
	}
}