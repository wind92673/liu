package edu.nuc.light_system.entity.po;

import edu.nuc.light_system.enums.DateTimePatternEnum;
import edu.nuc.light_system.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description:用户信息表
 * @author:Liu Wenhao
 * @date:2026/04/09
 */
public class UserInfo implements Serializable {
	/**
	 * 用户id
	 */
	private String userId;


	/**
	 * 手机号
	 */
	private String phone;


	/**
	 * 名称
	 */
	private String nickName;


	/**
	 * 密码
	 */
	private String password;


	/**
	 * 创建时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;


	/**
	 * 权限：0：普通用户，1：运维人员，2；管理员
	 */
	private Integer right;


	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getNickName() {
		return this.nickName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return this.password;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setRight(Integer right) {
		this.right = right;
	}

	public Integer getRight() {
		return this.right;
	}

	@Override
	public String toString() {
		return "用户id:" + (userId == null ? "空" : userId) + ",手机号:" + (phone == null ? "空" : phone) + ",名称:" + (nickName == null ? "空" : nickName) + ",密码:" + (password == null ? "空" : password) + ",创建时间:" + (createTime == null ? "空" : DateUtils.format(createTime,DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern())) + ",权限：0：普通用户，1：运维人员，2；管理员:" + (right == null ? "空" : right);
	}
}