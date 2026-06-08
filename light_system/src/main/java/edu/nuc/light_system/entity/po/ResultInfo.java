package edu.nuc.light_system.entity.po;

import edu.nuc.light_system.enums.DateTimePatternEnum;
import edu.nuc.light_system.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description:处理结果表
 * @author:Liu Wenhao
 * @date:2026/04/09
 */
public class ResultInfo implements Serializable {
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


	/**
	 * 备注信息
	 */
	private String info;


	/**
	 * 处理时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date repairTime;


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

	@Override
	public String toString() {
		return "自增id:" + (id == null ? "空" : id) + ",告警工单id:" + (orderId == null ? "空" : orderId) + ",处理人员id:" + (userId == null ? "空" : userId) + ",备注信息:" + (info == null ? "空" : info) + ",处理时间:" + (repairTime == null ? "空" : DateUtils.format(repairTime,DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern()));
	}
}