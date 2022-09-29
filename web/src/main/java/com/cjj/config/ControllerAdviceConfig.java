package com.cjj.config;

import com.cjj.beans.BaseResponse;
import com.cjj.beans.enums.ResponseCodeEnum;
import com.cjj.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 统一异常处理
 *
 * @author cjj
 */
@ControllerAdvice
@Slf4j
public class ControllerAdviceConfig {

    /**
     * 统一处理json对象参数校验异常（如post请求使用json对象传参）
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public BaseResponse defaultErrorHandler(MethodArgumentNotValidException e) {
        BindingResult result = e.getBindingResult();
        String msg = "";
        if (result.hasErrors()) {
            msg = result.getFieldErrors().get(0).getDefaultMessage();
            log.info("{}请求参数验证不通过：{}", result.getFieldErrors().get(0).getObjectName(), result.getFieldErrors().get(0).getDefaultMessage());
        }
        return new BaseResponse(ResponseCodeEnum.ARGUMENT_ERROR.getCode(), msg);
    }

    /**
     * 统一处理实体对象传参校验异常（如post请求使用实体传参）
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public BaseResponse defaultErrorHandler(BindException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        String msg = fieldErrors.get(0).getField() + ":" + fieldErrors.get(0).getDefaultMessage();
        log.info("请求参数验证不通过：{}", msg);
        return new BaseResponse(ResponseCodeEnum.ARGUMENT_ERROR.getCode(), msg);
    }

    /**
     * 统一处理普通传参校验异常（如get请求）
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseBody
    public BaseResponse defaultErrorHandler(ConstraintViolationException e) {
        String msg = e.getConstraintViolations().stream().map(cv -> cv.getPropertyPath() + ":" + cv.getMessage()).collect(Collectors.joining(","));
        log.info("请求参数验证不通过：{}", msg);
        return new BaseResponse(ResponseCodeEnum.ARGUMENT_ERROR.getCode(), msg);
    }

    /**
     * 统一处理业务异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public BaseResponse defaultErrorHandler(BusinessException e) {
        log.warn("业务异常信息：" + e.getMessage());
        e.printStackTrace();
        return new BaseResponse(e.getCode(), e.getMsg());
    }


    /**
     * 统一处理一般异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public BaseResponse defaultErrorHandler(Exception e) {
        e.printStackTrace();
        log.error("异常信息：" + e.getMessage());
        String msg = e.getMessage();
        return new BaseResponse(ResponseCodeEnum.FAIL.getCode(), msg);
    }

}
