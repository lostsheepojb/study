package com.cjj.controller.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

/**
 * @Description
 * @Author cjj
 * @Date 2022/9/22 17:33
 **/
@Getter
@Setter
@Valid
public class HelloArgument {
    @ApiModelProperty("名称")
    @NotEmpty(message = "名字不能为空")
    private String name;
}
