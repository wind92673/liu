package edu.nuc.light_system.entity.query;

import java.util.Date;

/**
 * @Description:用户信息表查询对象
 * @author:Liu Wenhao
 * @date:2026/04/09
 */
public class UserInfoQuery extends BaseQuery {
	/**
	 * 用户id
	 */
	private String userId;


	private String userIdFuzzy;

	/**
	 * 手机号
	 */
	private String phone;


	private String phoneFuzzy;

	/**
	 * 名称
	 */
	private String nickName;


	private String nickNameFuzzy;

	/**
	 * 密码
	 */
	private String password;


	private String passwordFuzzy;

	/**
	 * 创建时间
	 */
	private Date createTime;


	private String createTimeStart;

	private String createTimeEnd;

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

	public void setUserIdFuzzy(String userIdFuzzy) {
		this.userIdFuzzy = userIdFuzzy;
	}

	public String getUserIdFuzzy() {
		return this.userIdFuzzy;
	}

	public void setPhoneFuzzy(String phoneFuzzy) {
		this.phoneFuzzy = phoneFuzzy;
	}

	public String getPhoneFuzzy() {
		return this.phoneFuzzy;
	}

	public void setNickNameFuzzy(String nickNameFuzzy) {
		this.nickNameFuzzy = nickNameFuzzy;
	}

	public String getNickNameFuzzy() {
		return this.nickNameFuzzy;
	}

	public void setPasswordFuzzy(String passwordFuzzy) {
		this.passwordFuzzy = passwordFuzzy;
	}

	public String getPasswordFuzzy() {
		return this.passwordFuzzy;
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