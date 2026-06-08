package edu.nuc.light_system.entity.query;

import java.util.Date;

/**
 * @Description:分组表查询对象
 * @author:Liu Wenhao
 * @date:2026/04/09
 */
public class GroupInfoQuery extends BaseQuery {
	/**
	 * 分组主键
	 */
	private String groupId;


	private String groupIdFuzzy;

	/**
	 * 分组名称
	 */
	private String groupName;


	private String groupNameFuzzy;

	/**
	 * 创建时间
	 */
	private Date createTime;


	private String createTimeStart;

	private String createTimeEnd;

	/**
	 * 创建人
	 */
	private String createPeople;


	private String createPeopleFuzzy;

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getGroupId() {
		return this.groupId;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupName() {
		return this.groupName;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreatePeople(String createPeople) {
		this.createPeople = createPeople;
	}

	public String getCreatePeople() {
		return this.createPeople;
	}

	public void setGroupIdFuzzy(String groupIdFuzzy) {
		this.groupIdFuzzy = groupIdFuzzy;
	}

	public String getGroupIdFuzzy() {
		return this.groupIdFuzzy;
	}

	public void setGroupNameFuzzy(String groupNameFuzzy) {
		this.groupNameFuzzy = groupNameFuzzy;
	}

	public String getGroupNameFuzzy() {
		return this.groupNameFuzzy;
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

	public void setCreatePeopleFuzzy(String createPeopleFuzzy) {
		this.createPeopleFuzzy = createPeopleFuzzy;
	}

	public String getCreatePeopleFuzzy() {
		return this.createPeopleFuzzy;
	}

}