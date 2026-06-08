package edu.nuc.light_system.entity.query;

import java.util.Date;

/**
 * @Description:操作日志表查询对象
 * @author:Liu Wenhao
 * @date:2026/04/09
 */
public class CommandLogQuery extends BaseQuery {
	/**
	 * 自增id
	 */
	private Integer id;


	/**
	 * 操作类型
	 */
	private String eventType;


	private String eventTypeFuzzy;

	/**
	 * 设备id
	 */
	private String deviceId;


	private String deviceIdFuzzy;

	/**
	 * 指令内容
	 */
	private String command;


	private String commandFuzzy;

	/**
	 * 发送时间
	 */
	private Date timestamp;


	private String timestampStart;

	private String timestampEnd;

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

	public void setEventTypeFuzzy(String eventTypeFuzzy) {
		this.eventTypeFuzzy = eventTypeFuzzy;
	}

	public String getEventTypeFuzzy() {
		return this.eventTypeFuzzy;
	}

	public void setDeviceIdFuzzy(String deviceIdFuzzy) {
		this.deviceIdFuzzy = deviceIdFuzzy;
	}

	public String getDeviceIdFuzzy() {
		return this.deviceIdFuzzy;
	}

	public void setCommandFuzzy(String commandFuzzy) {
		this.commandFuzzy = commandFuzzy;
	}

	public String getCommandFuzzy() {
		return this.commandFuzzy;
	}

	public void setTimestampStart(String timestampStart) {
		this.timestampStart = timestampStart;
	}

	public String getTimestampStart() {
		return this.timestampStart;
	}

	public void setTimestampEnd(String timestampEnd) {
		this.timestampEnd = timestampEnd;
	}

	public String getTimestampEnd() {
		return this.timestampEnd;
	}

}