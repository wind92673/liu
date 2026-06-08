package edu.nuc.light_system.entity.po;

import edu.nuc.light_system.enums.DateTimePatternEnum;
import edu.nuc.light_system.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description:操作日志表
 * @author:Liu Wenhao
 * @date:2026/04/09
 */
public class CommandLog implements Serializable {
	/**
	 * 自增id
	 */
	private Integer id;


	/**
	 * 操作类型
	 */
	private String eventType;


	/**
	 * 设备id
	 */
	private String deviceId;


	/**
	 * 指令内容
	 */
	private String command;


	/**
	 * 发送时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date timestamp;


	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getEventType() {
		return this.eventType;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceId() {
		return this.deviceId;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getCommand() {
		return this.command;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public Date getTimestamp() {
		return this.timestamp;
	}

	@Override
	public String toString() {
		return "自增id:" + (id == null ? "空" : id) + ",操作类型:" + (eventType == null ? "空" : eventType) + ",设备id:" + (deviceId == null ? "空" : deviceId) + ",指令内容:" + (command == null ? "空" : command) + ",发送时间:" + (timestamp == null ? "空" : DateUtils.format(timestamp,DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern()));
	}
}