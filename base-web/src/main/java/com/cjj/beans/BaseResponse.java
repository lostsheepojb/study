package com.cjj.beans;

import com.cjj.beans.enums.ResponseEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @Description
 * @Author cjj
 * @Date 2022/9/22 11:13
 **/
@Getter
@Setter
public class BaseResponse {
    @ApiModelProperty("状态码")
    private String code;
    @ApiModelProperty("提示")
    private String msg;
    @ApiModelProperty("返回数据")
    private Object data;


    public BaseResponse() {
        this(ResponseEnum.OK);
    }

    public BaseResponse(ResponseEnum responseEnum) {
        this.code = responseEnum.getCode();
        this.msg = responseEnum.getMsg();
    }

    public BaseResponse(Object data) {
        this(ResponseEnum.OK);
        this.data = data;
    }


}
