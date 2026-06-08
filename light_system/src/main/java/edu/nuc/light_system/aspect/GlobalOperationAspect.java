package edu.nuc.light_system.aspect;

import edu.nuc.light_system.annotation.GlobalInterceptor;
import edu.nuc.light_system.enums.ResponseCodeEnum;
import edu.nuc.light_system.enums.RightEnum;
import edu.nuc.light_system.exception.BusinessException;
import edu.nuc.light_system.utils.JwtUtils;
import edu.nuc.light_system.utils.StringTools;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Aspect
@Component("globalOperationAspect")
public class GlobalOperationAspect {
    private static final Logger logger = LoggerFactory.getLogger(GlobalOperationAspect.class);
    //这样注解的话，aop会先校验参数存在和是否符合接口要求，再判断是否登录和用户权限
    @Before("@annotation(edu.nuc.light_system.annotation.GlobalInterceptor)")
    public void interceptorDo(JoinPoint point) {
        try {
            Method method = ((MethodSignature)point.getSignature()).getMethod();
            GlobalInterceptor interceptor = method.getAnnotation(GlobalInterceptor.class);
            if (interceptor == null){
                return;
            }
            if (interceptor.checkLogin() || interceptor.checkAdmin()) {
                checkPermission(interceptor.checkLogin(), interceptor.checkAdmin(), interceptor.checkDevOps());
            }
        }catch (BusinessException e) {
            logger.error("全局拦截异常",e);
            throw e;
        } catch (Throwable e) {
            logger.error("全局拦截异常",e);
            throw new BusinessException(ResponseCodeEnum.CODE_500);

        }
    }

    private void checkPermission(Boolean checkLogin, Boolean checkAdmin, Boolean checkDevOps) {
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader("token");

        if (StringTools.isEmpty(token)) {
            throw new BusinessException(ResponseCodeEnum.CODE_901);
        }

        if (!JwtUtils.validateToken(token)) {
            throw new BusinessException(ResponseCodeEnum.CODE_901);
        }

        Integer right = JwtUtils.getRightFromToken(token);

        if (checkAdmin && !RightEnum.ADMINISTRATOR.getType().equals(right)) {
            throw new BusinessException(ResponseCodeEnum.CODE_902);
        }

        if (checkDevOps && !RightEnum.DEVOPS.getType().equals(right) && !RightEnum.ADMINISTRATOR.getType().equals(right)) {
            throw new BusinessException(ResponseCodeEnum.CODE_903);
        }
    }

}
