package com.cjj.aop;

import com.cjj.annotation.RepeatRequest;
import com.cjj.beans.TokenArgument;
import com.cjj.beans.enums.BusinessExceptionEnum;
import com.cjj.exception.BusinessException;
import com.cjj.utils.RedisUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @Description 代理所有请求接口，检查是否可以重复提交
 * @Author cjj
 * @Date 2022/9/23 14:24
 **/
@Aspect
@Component
public class ControllerAop {

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 切入点：增强有RepeatRequest注解的方法
     */
    @Pointcut(value = "@annotation(com.cjj.annotation.RepeatRequest)")
    public void repeatRequestPointcut() {
    }

    /**
     * 切入点：增强Controller的共有方法
     */
    @Pointcut("execution(public * com.cjj.controller.*.*(..))")
    public void controllerMethodPointcut() {
    }

    /**
     * 前置通知，检查请求是否重复提交，token存放在服务器中
     *
     * @param joinPoint
     */
    @Before("repeatRequestPointcut() && controllerMethodPointcut()")
    private void checkRepeatRequest(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        boolean present = method.isAnnotationPresent(RepeatRequest.class);
        if (present) {
            RepeatRequest repeatRequest = method.getAnnotation(RepeatRequest.class);
            boolean value = repeatRequest.value();
            if (!value) {
                Object[] args = joinPoint.getArgs();
                for (Object o : args) {
                    if (o.getClass() == TokenArgument.class) {
                        TokenArgument tokenArgument = (TokenArgument) o;
                        String requestToken = tokenArgument.getToken();
                        //Integer count = TokenUtil.requestToken.get(requestToken);
                        Integer count = redisUtil.get(requestToken) == null ? null : Integer.valueOf(redisUtil.get(requestToken).toString());
                        if (count == null) {
                            throw new BusinessException(BusinessExceptionEnum.TOKEN_NOT_FOUND);
                        } else if (count > 0) {
                            throw new BusinessException(BusinessExceptionEnum.REPEAT_REQUEST);
                        } else {
                            //TokenUtil.requestToken.put(requestToken, count + 1);
                            redisUtil.incr(requestToken, 1L);
                            return;
                        }
                    }
                }
                throw new BusinessException(BusinessExceptionEnum.ARGUMENT_NOT_FOUND.getCode(), "未找到接口参数类型：" + TokenArgument.class.getName());
            }
        }
    }

}
