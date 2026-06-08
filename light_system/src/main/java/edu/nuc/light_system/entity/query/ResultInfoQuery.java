package edu.nuc.light_system.entity.query;

import java.util.Date;

/**
 * @Description:处理结果表查询对象
 * @author:Liu Wenhao
 * @date:2026/04/09
 */
public class ResultInfoQuery extends BaseQuery {
	/**
	 * 自增id
	 */
	private Integer id;


	/**
	 * 告警工单id
	 */
	private Integer orderId;


	/**
	 * 处理人员id
	 */
	private String userId;


	private String userIdFuzzy;

	/**
	 * 备注信息
	 */
	private String info;


	private String infoFuzzy;

	/**
	 * 处理时间
	 */
	private Date repairTime;


	private String repairTimeStart;

	private String repairTimeEnd;

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getOrderId() {
		return this.orderId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getInfo() {
		return this.info;
	}

	public void setRepairTime(Date repairTime) {
		this.repairTime = repairTime;
	}

	public Date getRepairTime() {
		return this.repairTime;
	}

	public void setUserIdFuzzy(String userIdFuzzy) {
		this.userIdFuzzy = userIdFuzzy;
	}

	public String getUserIdFuzzy() {
		return this.userIdFuzzy;
	}

	public void setInfoFuzzy(String infoFuzzy) {
		this.infoFuzzy = infoFuzzy;
	}

	public String getInfoFuzzy() {
		return this.infoFuzzy;
	}

	public void setRepairTimeStart(String repairTimeStart) {
		this.repairTimeStart = repairTimeStart;
	}

	public String getRepairTimeStart() {
		return this.repairTimeStart;
	}

	public void setRepairTimeEnd(String repairTimeEnd) {
		this.repairTimeEnd = repairTimeEnd;
	}

	public String getRepairTimeEnd() {
		return this.repairTimeEnd;
	}

}