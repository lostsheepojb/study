package com.cjj.beans.enums;

import lombok.Getter;

/**
 * @Description
 * @Author cjj
 * @Date 2022/9/22 16:51
 **/
@Getter
public enum BusinessExceptionEnum {

    TOKEN_NOT_FOUND("7001", "未找到token"),
    REPEAT_REQUEST("7002", "不可重复请求"),
    ARGUMENT_NOT_FOUND("7003", "未找到参数");


    private String code;
    private String msg;

    BusinessExceptionEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
