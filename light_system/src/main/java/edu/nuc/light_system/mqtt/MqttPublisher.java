package edu.nuc.light_system.mqtt;

import com.alibaba.fastjson.JSONObject;
import edu.nuc.light_system.entity.constant.Constants;
import edu.nuc.light_system.entity.po.CommandLog;
import edu.nuc.light_system.enums.DeviceModeEnum;
import edu.nuc.light_system.enums.EventTypeEnum;
import edu.nuc.light_system.service.CommandLogService;
import edu.nuc.light_system.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName MqttPublisher
 * @Description // TODO: MQTT消息推送
 * @Author 19265
 * @Date 2026/3/8 15:52
 * @Version 1.0.0
 */
@Service
public class MqttPublisher {
    private static final Logger logger = LoggerFactory.getLogger(MqttPublisher.class);
    private final MqttPahoClientFactory mqttClientFactory;
    private final String clientId;

    private final CommandLogService commandLogService;

    private MqttPahoMessageHandler messageHandler;

    public MqttPublisher(MqttPahoClientFactory mqttClientFactory,
                         @Value("${mqtt.clientId}") String clientId,
                         CommandLogService commandLogService) {

        this.mqttClientFactory = mqttClientFactory;
        this.clientId = clientId + "-publisher";
        this.commandLogService = commandLogService;
    }

    @PostConstruct
    public void init() {
        try {
            logger.info("正在初始化 MQTT Publisher, ClientId: {}", clientId);
            messageHandler = new MqttPahoMessageHandler(clientId, mqttClientFactory);

            // 关键配置
            messageHandler.setAsync(true);
            messageHandler.setDefaultTopic(Constants.MQTT_PUBLISH_TOPIC);
            messageHandler.setConverter(new DefaultPahoMessageConverter());

            // 启动处理器
            messageHandler.start();

            // 等待一小段时间确保底层 Paho 客户端完成连接握手
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            logger.info("MQTT Publisher 初始化成功");
        } catch (Exception e) {
            logger.error("MQTT Publisher 初始化失败: {}", e.getMessage(), e);
        }
    }

    @PreDestroy
    public void cleanup() {
        if (messageHandler != null) {
            messageHandler.stop();
            logger.info("MQTT Publisher 已停止");
        }
    }

    public void publish(String topic, String payload) {
        if (messageHandler == null) {
            logger.error("MQTT 消息处理器未初始化");
            return;
        }

        try {
            // 构建消息，显式指定 Topic
            Map<String, Object> headers = new HashMap<>();
            headers.put(MqttHeaders.TOPIC, topic);
            MessageHeaders messageHeaders = new MessageHeaders(headers);

            GenericMessage<String> message = new GenericMessage<>(payload, messageHeaders);

            logger.debug("准备发送 MQTT 消息到 Topic: {}, Payload: {}", topic, payload);
            messageHandler.handleMessage(message);

        } catch (Exception e) {
            logger.error("MQTT 发送异常: {}", e.getMessage(), e);
        }
    }

    // 发送配置命令并记录日志
    public void publishConfigOrder(String deviceId, Integer lightThreshold, Integer reportInterval,
                                   Integer dayStartTime, Integer dayEndTime) {
        Map<String, Object> config = new HashMap<>();
        config.put("lightThreshold", lightThreshold);
        config.put("reportInterval", reportInterval);
        config.put("dayStartTime", dayStartTime);
        config.put("dayEndTime", dayEndTime);

        Map<String, Object> message = new HashMap<>();
        message.put("eventType", EventTypeEnum.CONFIGURATION.name());
        message.put("deviceId", deviceId);
        message.put("config", config);

        String payload = JSONObject.toJSONString(message);

        // 记录操作日志
        saveCommandLog(EventTypeEnum.CONFIGURATION.name(), deviceId, payload);

        publish(Constants.MQTT_PUBLISH_TOPIC, payload);

        logger.info("发送配置命令 - 设备ID: {}, 亮度阈值: {}, 上报间隔: {}, 白天开始: {}, 白天结束: {}",
                deviceId, lightThreshold, reportInterval, dayStartTime, dayEndTime);
    }

    // 发送模式切换命令并记录日志
    public void publishModeOrder(String deviceId, Integer mode) {
        if (mode == null) {
            logger.error("模式不能为空");
            return;
        }

        Map<String, Object> command = new HashMap<>();
        command.put("mode", mode);

        Map<String, Object> message = new HashMap<>();
        message.put("eventType", EventTypeEnum.SET_MODE.name());
        message.put("deviceId", deviceId);
        message.put("command", command);

        String payload = JSONObject.toJSONString(message);

        // 记录操作日志
        saveCommandLog(EventTypeEnum.SET_MODE.name(), deviceId, payload);

        publish(Constants.MQTT_PUBLISH_TOPIC, payload);

        logger.info("发送模式切换命令 - 设备ID: {}, 模式: {}", deviceId, mode);
    }

    // 发送亮度控制命令并记录日志
    public void publishBrightOrder(String deviceId, Integer brightness) {
        if (brightness == null || brightness < 0 || brightness > 100) {
            logger.error("亮度值必须在0-100之间");
            return;
        }

        Map<String, Object> command = new HashMap<>();
        command.put("brightness", brightness);

        Map<String, Object> message = new HashMap<>();
        message.put("eventType", EventTypeEnum.SET_LIGHT_BRIGHT.name());
        message.put("deviceId", deviceId);
        message.put("command", command);

        String payload = JSONObject.toJSONString(message);

        // 记录操作日志
        saveCommandLog(EventTypeEnum.SET_LIGHT_BRIGHT.name(), deviceId, payload);

        publish(Constants.MQTT_PUBLISH_TOPIC, payload);

        int dutyCycle = 100 - brightness;
        logger.info("发送亮度控制命令 - 设备ID: {}, 亮度: {}, 占空比: {}", deviceId, brightness, dutyCycle);
    }

    // 发送时间同步命令并记录日志
    public void publishSyncOrder(String deviceId, String currentTime) {
        Map<String, Object> config = new HashMap<>();
        config.put("currentTime", currentTime);

        Map<String, Object> message = new HashMap<>();
        message.put("eventType", EventTypeEnum.SYNC_TIME.name());
        message.put("deviceId", deviceId);
        message.put("config", config);

        String payload = JSONObject.toJSONString(message);

        // 记录操作日志
        saveCommandLog(EventTypeEnum.SYNC_TIME.name(), deviceId, payload);

        publish(Constants.MQTT_PUBLISH_TOPIC, payload);

        logger.info("发送时间同步命令 - 设备ID: {}, 时间: {}", deviceId, currentTime);
    }

    public void publishSyncOrderNow(String deviceId) {
        String currentTime = DateUtils.format(new java.util.Date(), "yyyyMMddHHmmss");
        publishSyncOrder(deviceId, currentTime);
    }

    /**
     * 保存命令日志到数据库
     */
    private void saveCommandLog(String eventType, String deviceId, String command) {
        try {
            CommandLog commandLog = new CommandLog();
            commandLog.setEventType(eventType);
            commandLog.setDeviceId(deviceId);
            commandLog.setCommand(command);
            commandLog.setTimestamp(new java.util.Date());

            commandLogService.add(commandLog);
        } catch (Exception e) {
            logger.error("保存命令日志失败: {}", e.getMessage(), e);
        }
    }

}

