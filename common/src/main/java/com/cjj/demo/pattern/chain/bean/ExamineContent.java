package com.cjj.demo.pattern.chain.bean;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @Description 提交审核的内容
 * @Author cjj
 * @Date 2022/9/14 10:29
 **/
@Getter
@Setter
public class ExamineContent {
    /**
     * 项目金额
     */
    private BigDecimal amount;

}
