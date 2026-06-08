package edu.nuc.light_system.entity.constant;

/**
 * @ClassName Constant
 * @Description // TODO: 常量
 * @Author 19265
 * @Date 2026/4/9 15:33
 * @Version 1.0.0
 */
public class Constants {
    public static final String MQTT_CLIENT_ID = "subscriberClient";
    public static final String MQTT_SUBSCRIB_TOPIC = "report";
    public static final String MQTT_PUBLISH_TOPIC = "corridor";

    public static final String REGEX_PASSWORD = "^(?=.*\\d)(?=.*[a-zA-Z])[\\da-zA-Z~!@#$%^&*_]{8,18}$";

    public static final String REGEX_PHONE = "^\\d{11}$";

    public static final Integer LENGTH_3 = 3;

    //节能统计的能耗和碳排放基数
    public static final Double POWER_NUMBER = 0.1;
    public static final Double CARBON_NUMBER = 0.015;



}
