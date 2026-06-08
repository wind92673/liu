package edu.nuc.light_system.enums;

public enum EventTypeEnum {
    DEVICE_ONLINE("DEVICE_ONLINE"),
    STATUS_REPORT("STATUS_REPORT"),
    SET_LIGHT_BRIGHT("SET_LIGHT_BRIGHT"),
    SET_MODE("SET_MODE"),
    CONFIGURATION("CONFIGURATION"),
    SYNC_TIME("SYNC_TIME");


    private final String eventType;

    EventTypeEnum(String eventType) {
        this.eventType = eventType;
    }

    public String getEventType() {
        return eventType;
    }
}
