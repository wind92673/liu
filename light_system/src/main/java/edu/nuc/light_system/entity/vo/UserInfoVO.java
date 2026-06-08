package edu.nuc.light_system.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @ClassName UserInfoVO
 * @Description // TODO: 前端用户视图类
 * @Author 19265
 * @Date 2026/4/21 20:09
 * @Version 1.0.0
 */
public class UserInfoVO {
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
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;


    /**
     * 权限：0：普通用户，1：运维人员，2；管理员
     */
    private Integer right;

    /**
     * 用户绑定的设备id
     */
    private String deviceId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getRight() {
        return right;
    }

    public void setRight(Integer right) {
        this.right = right;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}
