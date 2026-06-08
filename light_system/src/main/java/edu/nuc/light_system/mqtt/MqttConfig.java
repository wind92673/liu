package edu.nuc.light_system.mqtt;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;

/**
 * @ClassName MqttConfig
 * @Description //MQTT配置类
 * @Author 19265
 * @Date 2026/3/8 15:51
 * @Version 1.0.0
 */
@Configuration
public class MqttConfig {


    @Value("${mqtt.host}")
    private String host;

    @Value("${mqtt.clientId}")
    private String clientId;

    @Value("${mqtt.username}")
    private String username;

    @Value("${mqtt.password}")
    private String password;

    @Value("${mqtt.defaultTopic}")
    private String testTopic;

    // 配置MQTT客户端工厂
    @Bean
    public MqttConnectOptions getMqttConnectOptions() {

        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setUserName(username);
        mqttConnectOptions.setPassword(password.toCharArray());
        mqttConnectOptions.setServerURIs(new String[]{
                host});
        mqttConnectOptions.setKeepAliveInterval(20);
        return mqttConnectOptions;
    }

    // 配置MQTT客户端
    @Bean
    public MqttPahoClientFactory mqttClientFactory() {

        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        factory.setConnectionOptions(getMqttConnectOptions());
        return factory;
    }

    // 其他配置...
}

