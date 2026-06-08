package edu.nuc.light_system.entity.query;


import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description:节能统计表查询对象
 * @author:Liu Wenhao
 * @date:2026/04/09
 */
public class SaveInfoQuery extends BaseQuery {
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
	 * 当前时间，精确到天
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date currentTime;


	private String currentTimeStart;

	private String currentTimeEnd;

	/**
	 * 当前小时
	 */
	private Integer currentHour;


	/**
	 * 节电量
	 */
	private String power;


	private String powerFuzzy;

	/**
	 * 节碳量
	 */
	private String carbon;

	/**
	 * 年份（用于按年查询）
	 */
	private Integer year;

	/**
	 * 月份（用于按月查询）
	 */
	private Integer month;


	private String carbonFuzzy;

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

	public void setCurrentTime(Date currentTime) {
		this.currentTime = currentTime;
	}

	public Date getCurrentTime() {
		return this.currentTime;
	}

	public void setCurrentHour(Integer currentHour) {
		this.currentHour = currentHour;
	}

	public Integer getCurrentHour() {
		return this.currentHour;
	}

	public void setPower(String power) {
		this.power = power;
	}

	public String getPower() {
		return this.power;
	}

	public void setCarbon(String carbon) {
		this.carbon = carbon;
	}

	public String getCarbon() {
		return this.carbon;
	}

	public void setDeviceIdFuzzy(String deviceIdFuzzy) {
		this.deviceIdFuzzy = deviceIdFuzzy;
	}

	public String getDeviceIdFuzzy() {
		return this.deviceIdFuzzy;
	}

	public void setCurrentTimeStart(String currentTimeStart) {
		this.currentTimeStart = currentTimeStart;
	}

	public String getCurrentTimeStart() {
		return this.currentTimeStart;
	}

	public void setCurrentTimeEnd(String currentTimeEnd) {
		this.currentTimeEnd = currentTimeEnd;
	}

	public String getCurrentTimeEnd() {
		return this.currentTimeEnd;
	}

	public void setPowerFuzzy(String powerFuzzy) {
		this.powerFuzzy = powerFuzzy;
	}

	public String getPowerFuzzy() {
		return this.powerFuzzy;
	}

	public void setCarbonFuzzy(String carbonFuzzy) {
		this.carbonFuzzy = carbonFuzzy;
	}

	public String getCarbonFuzzy() {
		return this.carbonFuzzy;
	}


	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getYear() {
		return this.year;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getMonth() {
		return this.month;
	}
}