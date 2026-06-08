package edu.nuc.light_system.entity.query;

import java.util.Date;

/**
 * @Description:设备分组表查询对象
 * @author:Liu Wenhao
 * @date:2026/04/09
 */
public class DeviceGroupBoundQuery extends BaseQuery {
	/**
	 * 自增主键
	 */
	private Integer id;


	/**
	 * 分组id
	 */
	private String groupId;


	private String groupIdFuzzy;

	/**
	 * 设备id
	 */
	private String deviceId;


	private String deviceIdFuzzy;

	/**
	 * 创建时间
	 */
	private Date createTime;


	private String createTimeStart;

	private String createTimeEnd;

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

	public void setGroupIdFuzzy(String groupIdFuzzy) {
		this.groupIdFuzzy = groupIdFuzzy;
	}

	public String getGroupIdFuzzy() {
		return this.groupIdFuzzy;
	}

	public void setDeviceIdFuzzy(String deviceIdFuzzy) {
		this.deviceIdFuzzy = deviceIdFuzzy;
	}

	public String getDeviceIdFuzzy() {
		return this.deviceIdFuzzy;
	}

	public void setCreateTimeStart(String createTimeStart) {
		this.createTimeStart = createTimeStart;
	}

	public String getCreateTimeStart() {
		return this.createTimeStart;
	}

	public void setCreateTimeEnd(String createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}

	public String getCreateTimeEnd() {
		return this.createTimeEnd;
	}

}