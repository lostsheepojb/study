package com.cjj.beans;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

/**
 * @Description
 * @Author cjj
 * @Date 2022/9/23 14:18
 **/
@Getter
@Setter
@Valid
public class TokenArgument {
    @NotEmpty(message = "token不能为空")
    private String token;
}
