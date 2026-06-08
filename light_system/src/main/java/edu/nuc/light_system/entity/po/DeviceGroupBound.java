package edu.nuc.light_system.entity.po;

import edu.nuc.light_system.enums.DateTimePatternEnum;
import edu.nuc.light_system.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description:设备分组表
 * @author:Liu Wenhao
 * @date:2026/04/09
 */
public class DeviceGroupBound implements Serializable {
	/**
	 * 自增主键
	 */
	private Integer id;


	/**
	 * 分组id
	 */
	private String groupId;


	/**
	 * 设备id
	 */
	private String deviceId;


	/**
	 * 创建时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;


	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getGroupId() {
		return this.groupId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceId() {
		return this.deviceId;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	@Override
	public String toString() {
		return "自增主键:" + (id == null ? "空" : id) + ",分组id:" + (groupId == null ? "空" : groupId) + ",设备id:" + (deviceId == null ? "空" : deviceId) + ",创建时间:" + (createTime == null ? "空" : DateUtils.format(createTime,DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern()));
	}
}