package com.cjj.beans.enums;

import lombok.Getter;

/**
 * @Description
 * @Author cjj
 * @Date 2022/9/22 16:51
 **/
@Getter
public enum ResponseCodeEnum {
    /**
     * 成功
     */
    OK("200", "成功"),

    /**
     * 失败
     */
    FAIL("500", "失败"),

    /**
     * 请求参数校验失败
     */
    ARGUMENT_ERROR("400", "请求参数校验失败"),

    /**
     * 业务异常
     */
    BUSINESS_ERROR("7000", "业务异常");


    private String code;
    private String msg;

    ResponseCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
