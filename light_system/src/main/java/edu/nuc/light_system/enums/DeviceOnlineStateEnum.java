package edu.nuc.light_system.enums;

public enum DeviceOnlineStateEnum {
    OFFLINE(1),
    ONLINE(0);

    private final Integer state;

    DeviceOnlineStateEnum(Integer state) {
        this.state = state;
    }

    public Integer getState() {
        return state;
    }
}
