package edu.nuc.light_system.controller;

import edu.nuc.light_system.entity.vo.ResponseVO;
import edu.nuc.light_system.service.DeviceInfoService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName CorridorController
 * @Description // TODO: 控制命令控制器，这个不应该在
 * @Author 19265
 * @Date 2026/4/9 16:09
 * @Version 1.0.0
 */
@RestController
@RequestMapping("/corridor")
@Validated
public class CorridorController extends ABaseController{

    @Resource
    private DeviceInfoService deviceInfoService;


    //发送控制设备配置命令
    @RequestMapping("/publishConfigOrder")
    public ResponseVO publishConfigOrder() {

        return getSuccessResponseVO(null);
    }

    //发送控制设备运行状态命令
    @RequestMapping("/publishStatusOrder")
    public ResponseVO publishStatusOrder() {

        return getSuccessResponseVO(null);
    }

    //发送控制设备亮度命令
    @RequestMapping("/publishBrightOrder")
    public ResponseVO publishBrightOrder() {

        return getSuccessResponseVO(null);
    }

    //发送控制设备时间命令
    @RequestMapping("/publishSyncOrder")
    public ResponseVO publishSyncOrder() {

        return getSuccessResponseVO(null);
    }
}
