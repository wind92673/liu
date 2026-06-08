package edu.nuc.light_system.enums;

import edu.nuc.light_system.utils.StringTools;

public enum RightEnum {
    USER(0,"普通员工"),
    DEVOPS(1,"运维人员"),
    ADMINISTRATOR(2,"管理员");

    private final Integer type;
    private final String desc;

    RightEnum(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public Integer getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }

    public static RightEnum getByDesc(String desc) {
        try {
            if (StringTools.isEmpty(desc)) {
                return null;
            }
            return RightEnum.valueOf(desc.toUpperCase());
        }catch (IllegalArgumentException e) {
            return null;
        }
    }
    public static RightEnum getByType(Integer type) {
        for (RightEnum rightEnum : RightEnum.values()) {
            if (rightEnum.getType().equals(type)) {
                return rightEnum;
            }
        }
        return null;
    }
}
