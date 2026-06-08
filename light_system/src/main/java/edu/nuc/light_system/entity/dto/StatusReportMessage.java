package edu.nuc.light_system.entity.dto;

import com.alibaba.fastjson.annotation.JSONField;
import edu.nuc.light_system.utils.DateUtils;

import java.util.Date;

/**
 * @ClassName StatusReportMessage
 * @Description //设备定时上报消息
 * @Author 19265
 * @Date 2026/4/13 13:47
 * @Version 1.0.0
 */
public class StatusReportMessage {
    private String eventType;
    private String deviceId;
    private String timestamp;
    private Data data;

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

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Integer getBrightNess() {
        return data != null ? data.getBrightness() : null;
    }

    public Integer getAmbientLight() {
        return data != null ? data.getAmbientLight() : null;
    }

    public Date getTimestampAsDate() {
        return DateUtils.parseTimestamp(timestamp);
    }

    public Integer getCurrentHour() {
        return DateUtils.extractHourFromTimestamp(timestamp);
    }

    public static class Data {
        private Integer brightness;
        private Integer ambientLight;
        private Integer systemMode;

        public Integer getBrightness() {
            return brightness;
        }

        public void setBrightness(Integer brightness) {
            this.brightness = brightness;
        }

        public Integer getAmbientLight() {
            return ambientLight;
        }

        public void setAmbientLight(Integer ambientLight) {
            this.ambientLight = ambientLight;
        }

        public Integer getSystemMode() {
            return systemMode;
        }

        public void setSystemMode(Integer systemMode) {
            this.systemMode = systemMode;
        }
    }
}
