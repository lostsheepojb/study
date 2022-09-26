package com.cjj.demo.pattern.chain.handler;


import com.cjj.demo.pattern.chain.bean.ExamineContent;

import java.math.BigDecimal;

/**
 * @Description 经理审核处理器
 * @Author cjj
 * @Date 2022/9/14 10:26
 **/
public class ManagerExamineChainHandler<T extends ExamineContent> implements ExamineChainHandler<T> {
    @Override
    public boolean examine(ExamineContent examineContent) {

        if (examineContent.getAmount() == null) {
            System.out.println("经理审核不通过，原因：项目金额未填写");
            return false;
        }
        if (BigDecimal.valueOf(10000).compareTo(examineContent.getAmount()) < 0) {
            System.out.println("经理审核不通过，原因：项目金额大于10000元");
            return false;
        }

        return true;
    }
}
