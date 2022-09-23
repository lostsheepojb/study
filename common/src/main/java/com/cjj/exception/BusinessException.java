package com.cjj.exception;

import com.cjj.beans.enums.BusinessExceptionEnum;
import com.cjj.beans.enums.ResponseCodeEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * @Description 业务异常
 * @Author cjj
 * @Date 2022/9/23 17:40
 **/
@Getter
@Setter
public class BusinessException extends RuntimeException {

    private String code;

    private String msg;

    public BusinessException() {
        super();
        this.code = ResponseCodeEnum.BUSINESS_ERROR.getCode();
    }

    public BusinessException(BusinessExceptionEnum businessExceptionEnum) {
        this.code = businessExceptionEnum.getCode();
        this.msg = businessExceptionEnum.getMsg();

    }

    public BusinessException(String code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

}
