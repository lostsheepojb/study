package com.cjj.beans.enums;

import lombok.Getter;

/**
 * @Description
 * @Author cjj
 * @Date 2022/9/22 16:51
 **/
@Getter
public enum ResponseEnum {
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
    ARGUMENT_ERROR("600", "请求参数校验失败");


    private String code;
    private String msg;

    ResponseEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
