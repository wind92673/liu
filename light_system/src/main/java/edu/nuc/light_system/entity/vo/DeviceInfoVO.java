package edu.nuc.light_system.entity.vo;

/**
 * @ClassName DeviceInfoVO
 * @Description // TODO: 设备前端视图类
 * @Author 19265
 * @Date 2026/4/21 20:14
 * @Version 1.0.0
 */
public class DeviceInfoVO {

    /**
     * 设备主键
     */
    private String deviceId;


    /**
     * 运行状态,0:自动模式,1:手动模式
     */
    private Integer systemMode;


    /**
     * 光照阈值
     */
    private Integer lightThreshold;


    /**
     * 白天开始时间
     */
    private Integer dayStart;


    /**
     * 夜晚开始时间
     */
    private Integer dayEnd;


    /**
     * 在线状态，0：在线，1：离线
     */
    private Integer online;


    /**
     * 上报间隔
     */
    private Integer reportInterval;

    /**
     * 绑定的用户id
     */
    private String userId;

    /**
     * 灯光亮度
     */
    private Integer brightNess;

    /**
     * 绑定的分组id
     */
    private String groupId;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Integer getSystemMode() {
        return systemMode;
    }

    public void setSystemMode(Integer systemMode) {
        this.systemMode = systemMode;
    }

    public Integer getLightThreshold() {
        return lightThreshold;
    }

    public void setLightThreshold(Integer lightThreshold) {
        this.lightThreshold = lightThreshold;
    }

    public Integer getDayStart() {
        return dayStart;
    }

    public void setDayStart(Integer dayStart) {
        this.dayStart = dayStart;
    }

    public Integer getDayEnd() {
        return dayEnd;
    }

    public void setDayEnd(Integer dayEnd) {
        this.dayEnd = dayEnd;
    }

    public Integer getOnline() {
        return online;
    }

    public void setOnline(Integer online) {
        this.online = online;
    }

    public Integer getReportInterval() {
        return reportInterval;
    }

    public void setReportInterval(Integer reportInterval) {
        this.reportInterval = reportInterval;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getBrightNess() {
        return brightNess;
    }

    public void setBrightNess(Integer brightNess) {
        this.brightNess = brightNess;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
