package edu.nuc.light_system.enums;

/**
 * @ClassName DeviceModeEnum
 * @Description //设备模式枚举
 * @Author 19265
 * @Date 2026/4/13 14:37
 * @Version 1.0.0
 */
public enum DeviceModeEnum {
    AUTO(0, "自动模式"),
    MANUAL(1, "手动模式");

    private final Integer code;
    private final String desc;

    DeviceModeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static DeviceModeEnum getByCode(Integer code) {
        if (code == null) {
            return null;
        }
        for (DeviceModeEnum mode : DeviceModeEnum.values()) {
            if (mode.getCode().equals(code)) {
                return mode;
            }
        }
        return null;
    }
}
