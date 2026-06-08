package edu.nuc.light_system.entity.po;

import edu.nuc.light_system.enums.DateTimePatternEnum;
import edu.nuc.light_system.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description:节能统计表
 * @author:Liu Wenhao
 * @date:2026/04/09
 */
public class SaveInfo implements Serializable {
	/**
	 * 自增id
	 */
	private Integer id;


	/**
	 * 设备id
	 */
	private String deviceId;


	/**
	 * 当前时间，精确到天
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date currentTime;


	/**
	 * 当前小时
	 */
	private Integer currentHour;


	/**
	 * 节电量
	 */
	private String power;


	/**
	 * 节碳量
	 */
	private String carbon;


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

	@Override
	public String toString() {
		return "自增id:" + (id == null ? "空" : id) + ",设备id:" + (deviceId == null ? "空" : deviceId) + ",当前时间，精确到天:" + (currentTime == null ? "空" : DateUtils.format(currentTime,DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern())) + ",当前小时:" + (currentHour == null ? "空" : currentHour) + ",节电量:" + (power == null ? "空" : power) + ",节碳量:" + (carbon == null ? "空" : carbon);
	}
}