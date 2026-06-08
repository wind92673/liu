package edu.nuc.light_system.entity.dto;

/**
 * @ClassName DeviceOnlineMessage
 * @Description // TODO: 设备上线消息实体类
 * @Author 19265
 * @Date 2026/4/13 13:46
 * @Version 1.0.0
 */
public class DeviceOnlineMessage {
    private String eventType;
    private String deviceId;
    private String timestamp;

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
