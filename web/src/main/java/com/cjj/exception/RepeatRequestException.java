package com.cjj.exception;

/**
 * @Description 重复请求异常
 * @Author cjj
 * @Date 2022/9/23 15:24
 **/
public class RepeatRequestException extends RuntimeException {
    public RepeatRequestException() {
        super();
    }

    public RepeatRequestException(String message) {
        super(message);
    }
}
