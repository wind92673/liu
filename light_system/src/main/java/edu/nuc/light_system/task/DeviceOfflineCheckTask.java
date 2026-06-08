package edu.nuc.light_system.task;

import edu.nuc.light_system.entity.po.DeviceInfo;
import edu.nuc.light_system.entity.po.ReportLight;
import edu.nuc.light_system.entity.po.WarnOrder;
import edu.nuc.light_system.entity.query.DeviceInfoQuery;
import edu.nuc.light_system.entity.query.ReportLightQuery;
import edu.nuc.light_system.enums.DeviceOnlineStateEnum;
import edu.nuc.light_system.service.DeviceInfoService;
import edu.nuc.light_system.service.ReportLightService;
import edu.nuc.light_system.service.WarnOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @ClassName DeviceOfflineCheckTask
 * @Description //定时任务监测设备连接状态
 * @Author 19265
 * @Date 2026/4/14 10:01
 * @Version 1.0.0
 */
@Component
public class DeviceOfflineCheckTask {
    private static final Logger logger = LoggerFactory.getLogger(DeviceOfflineCheckTask.class);

    @Resource
    private ReportLightService reportLightService;

    @Resource
    private DeviceInfoService deviceInfoService;

    @Resource
    private WarnOrderService warnOrderService;

    @Scheduled(fixedRate = 60000)
    public void checkDeviceOffline() {
        logger.info("开始执行设备离线检查定时任务");

        try {
            DeviceInfoQuery deviceQuery = new DeviceInfoQuery();
            deviceQuery.setOnline(DeviceOnlineStateEnum.ONLINE.getState());
            List<DeviceInfo> onlineDevices = deviceInfoService.findListByParam(deviceQuery);

            if (onlineDevices == null || onlineDevices.isEmpty()) {
                logger.info("没有在线设备，跳过检查");
                return;
            }

            Date now = new Date();
            long twoMinutesInMillis = 2 * 60 * 1000;

            for (DeviceInfo device : onlineDevices) {
                String deviceId = device.getDeviceId();

                ReportLightQuery reportQuery = new ReportLightQuery();
                reportQuery.setDeviceId(deviceId);
                List<ReportLight> reportLights = reportLightService.findListByParam(reportQuery);

                if (reportLights == null || reportLights.isEmpty()) {
                    logger.warn("设备 {} 没有上报记录，标记为离线", deviceId);

                    DeviceInfo updateDevice = new DeviceInfo();
                    updateDevice.setOnline(DeviceOnlineStateEnum.OFFLINE.getState());
                    deviceInfoService.updateDeviceInfoByDeviceId(updateDevice, deviceId);

                    WarnOrder warnOrder = new WarnOrder();
                    warnOrder.setDeviceId(deviceId);
                    warnOrder.setReportInfo("设备从未上报状态");
                    warnOrder.setReportTime(now);
                    warnOrder.setState(0);
                    warnOrderService.add(warnOrder);
                    continue;
                }

                ReportLight latestReport = reportLights.stream()
                        .max((r1, r2) -> r1.getReportTime().compareTo(r2.getReportTime()))
                        .orElse(null);

                if (latestReport == null || latestReport.getReportTime() == null) {
                    continue;
                }

                Date lastReportTime = latestReport.getReportTime();
                long timeDiff = now.getTime() - lastReportTime.getTime();

                if (timeDiff > twoMinutesInMillis) {
                    logger.warn("设备 {} 超过2分钟未上报，标记为离线", deviceId);

                    DeviceInfo updateDevice = new DeviceInfo();
                    updateDevice.setOnline(DeviceOnlineStateEnum.OFFLINE.getState());
                    deviceInfoService.updateDeviceInfoByDeviceId(updateDevice, deviceId);

                    WarnOrder warnOrder = new WarnOrder();
                    warnOrder.setDeviceId(deviceId);
                    warnOrder.setReportInfo("设备异常断开连接");
                    warnOrder.setReportTime(now);
                    warnOrder.setState(0);

                    warnOrderService.add(warnOrder);

                    logger.info("已为设备 {} 生成告警工单", deviceId);
                }
            }

            logger.info("设备离线检查定时任务执行完成");
        } catch (Exception e) {
            logger.error("设备离线检查定时任务执行失败", e);
        }
    }
}
