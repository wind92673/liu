package edu.nuc.light_system.controller;

import edu.nuc.light_system.annotation.GlobalInterceptor;
import edu.nuc.light_system.entity.constant.Constants;
import edu.nuc.light_system.entity.vo.ResponseVO;
import edu.nuc.light_system.entity.vo.UserVO;
import edu.nuc.light_system.enums.RightEnum;
import edu.nuc.light_system.service.UserInfoService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * @ClassName AccountController
 * @Description //账户控制器
 * @Author 19265
 * @Date 2026/4/9 15:56
 * @Version 1.0.0
 */
@RestController
@RequestMapping("/account")
@Validated
public class AccountController extends ABaseController{

    @Resource
    private UserInfoService userInfoService;

    //注册
    @RequestMapping("/register")
    public ResponseVO register(@NotEmpty @Pattern(regexp = Constants.REGEX_PASSWORD) String password,
                               @NotEmpty String nickName,
                               @NotEmpty @Pattern(regexp = Constants.REGEX_PHONE) String phone) {
        userInfoService.register(password, nickName, phone);
        return getSuccessResponseVO(null);
    }

    //登录
    @RequestMapping("/login")
    public ResponseVO login (@NotEmpty @Pattern(regexp = Constants.REGEX_PHONE) String phone,
                             @NotEmpty String password) {
        UserVO userVO = userInfoService.login(phone,password);
        return getSuccessResponseVO(userVO);
    }

    //更新权限
    @RequestMapping("/setRight")
    @GlobalInterceptor(checkAdmin = true)
    public ResponseVO setRight (@NotEmpty String userId,
                             @NotEmpty RightEnum right) {
        userInfoService.setRight(userId,right);
        return getSuccessResponseVO(null);
    }
}
