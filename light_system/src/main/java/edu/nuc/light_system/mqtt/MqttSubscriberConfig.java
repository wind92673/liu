package edu.nuc.light_system.mqtt;

import com.alibaba.fastjson.JSONObject;
import edu.nuc.light_system.entity.constant.Constants;
import edu.nuc.light_system.enums.EventTypeEnum;
import edu.nuc.light_system.entity.dto.DeviceOnlineMessage;
import edu.nuc.light_system.entity.dto.StatusReportMessage;
import edu.nuc.light_system.service.DeviceInfoService;
import edu.nuc.light_system.service.ReportLightService;
import edu.nuc.light_system.service.SaveInfoService;
import edu.nuc.light_system.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

import javax.annotation.Resource;

/**
 * @ClassName MqttSubscriberConfig
 * @Description //MQTT订阅与消息处理
 * @Author 19265
 * @Date 2026/3/8 16:03
 * @Version 1.0.0
 */
@Configuration
public class MqttSubscriberConfig {

    private static final Logger logger = LoggerFactory.getLogger(MqttSubscriberConfig.class);

    @Resource
    private DeviceInfoService deviceInfoService;

    @Resource
    private ReportLightService reportLightService;

    @Resource
    private SaveInfoService saveInfoService;


    @Bean
    public MessageChannel mqttInputChannel() {

        return new DirectChannel();
    }

    @Bean
    public MessageProducer inbound(MqttPahoClientFactory mqttClientFactory) {

        MqttPahoMessageDrivenChannelAdapter adapter =
                new MqttPahoMessageDrivenChannelAdapter(
                        Constants.MQTT_CLIENT_ID,
                        mqttClientFactory,
                        Constants.MQTT_SUBSCRIB_TOPIC); // 订阅主题
        adapter.setCompletionTimeout(5000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(0);
        adapter.setOutputChannel(mqttInputChannel());
        return adapter;
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel")
    public MessageHandler handler() {

        return message -> {

            String topic = message.getHeaders().get(MqttHeaders.RECEIVED_TOPIC).toString();
            String payload = message.getPayload().toString();
            System.out.println("收到MQTT消息 - 主题: " + topic + ", 内容: " + payload);
            //处理接收到的消息
            try {
                JSONObject jsonObject = JSONObject.parseObject(payload);
                String eventTypeStr = jsonObject.getString("eventType");

                if (eventTypeStr == null) {
                    logger.error("MQTT消息缺少eventType字段: {}", payload);
                    return;
                }
                EventTypeEnum eventType = getEventTypeEnum(eventTypeStr);
                if (eventType == null) {
                    logger.warn("未知的消息类型: {}", eventTypeStr);
                    return;
                }

                switch (eventType) {
                    case DEVICE_ONLINE:
                        onlineReportHandler(payload);
                        break;
                    case STATUS_REPORT:
                        statusReportHandler(payload);
                        break;
                    default:
                        logger.warn("未知的消息类型: {}", eventType);
                        break;
                }
            } catch (Exception e) {
                logger.error("处理MQTT消息异常: {}", payload, e);
            }
        };
    }

    private EventTypeEnum getEventTypeEnum(String eventTypeStr) {
        for (EventTypeEnum type : EventTypeEnum.values()) {
            if (type.getEventType().equals(eventTypeStr)) {
                return type;
            }
        }
        return null;
    }

    //处理设备上线通知消息
    private void onlineReportHandler(String payload) {
        try {
            DeviceOnlineMessage message = JsonUtils.convertJson2Obj(payload, DeviceOnlineMessage.class);
            logger.info("处理设备上线消息 - 设备ID: {}, 时间戳: {}", message.getDeviceId(), message.getTimestamp());

            deviceInfoService.handleDeviceOnline(message.getDeviceId());

            logger.info("设备上线消息处理成功: {}", message.getDeviceId());
        } catch (Exception e) {
            logger.error("处理设备上线消息失败: {}", payload, e);
        }
    }

    //处理设备定时上报消息
    private void statusReportHandler(String payload) {
        try {
            StatusReportMessage message = JsonUtils.convertJson2Obj(payload, StatusReportMessage.class);
            logger.info("处理设备状态上报 - 设备ID: {}, 亮度: {}, 环境光: {},时间: {}",
                    message.getDeviceId(),
                    message.getBrightNess(),
                    message.getAmbientLight(),
                    message.getTimestamp());

            reportLightService.handleStatusReport(message);
            saveInfoService.handleStatusReport(message);


            logger.info("设备状态上报处理成功: {}", message.getDeviceId());
        } catch (Exception e) {
            logger.error("处理设备状态上报失败: {}", payload, e);
        }
    }
}

