package edu.nuc.light_system.enums;

public enum LightStateEnum {
    OPEN(1),
    CLOSE(0);

    private final Integer state;

    LightStateEnum(Integer state) {
        this.state = state;
    }

    public Integer getState() {
        return state;
    }
}
