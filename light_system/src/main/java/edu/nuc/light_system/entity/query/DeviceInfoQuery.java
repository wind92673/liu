package edu.nuc.light_system.entity.query;


/**
 * @Description:设备信息表查询对象
 * @author:Liu Wenhao
 * @date:2026/04/13
 */
public class DeviceInfoQuery extends BaseQuery {
	/**
	 * 设备主键
	 */
	private String deviceId;


	private String deviceIdFuzzy;

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

	private String systemModeFuzzy;

	public String getSystemModeFuzzy() {
		return systemModeFuzzy;
	}

	public void setSystemModeFuzzy(String systemModeFuzzy) {
		this.systemModeFuzzy = systemModeFuzzy;
	}


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

	public void setDeviceIdFuzzy(String deviceIdFuzzy) {
		this.deviceIdFuzzy = deviceIdFuzzy;
	}

	public String getDeviceIdFuzzy() {
		return this.deviceIdFuzzy;
	}

}