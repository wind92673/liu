package edu.nuc.light_system.entity.po;

import edu.nuc.light_system.enums.DateTimePatternEnum;
import edu.nuc.light_system.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description:分组表
 * @author:Liu Wenhao
 * @date:2026/04/09
 */
public class GroupInfo implements Serializable {
	/**
	 * 分组主键
	 */
	private String groupId;


	/**
	 * 分组名称
	 */
	private String groupName;


	/**
	 * 创建时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;


	/**
	 * 创建人
	 */
	private String createPeople;


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

	@Override
	public String toString() {
		return "分组主键:" + (groupId == null ? "空" : groupId) + ",分组名称:" + (groupName == null ? "空" : groupName) + ",创建时间:" + (createTime == null ? "空" : DateUtils.format(createTime,DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern())) + ",创建人:" + (createPeople == null ? "空" : createPeople);
	}
}