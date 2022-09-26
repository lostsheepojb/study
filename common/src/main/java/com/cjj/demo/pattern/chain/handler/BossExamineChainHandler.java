package com.cjj.demo.pattern.chain.handler;


import com.cjj.demo.pattern.chain.bean.ExamineContent;
import java.math.BigDecimal;

/**
 * @Description 经理审核处理器
 * @Author cjj
 * @Date 2022/9/14 10:26
 **/
public class BossExamineChainHandler<T extends ExamineContent> implements ExamineChainHandler<T> {
    @Override
    public boolean examine(ExamineContent examineContent) {

        if (examineContent.getAmount() == null) {
            System.out.println("老板审核不通过，原因：项目金额未填写");
            return false;
        }
        if (BigDecimal.valueOf(9000).compareTo(examineContent.getAmount()) < 0) {
            System.out.println("老板审核不通过，原因：项目金额大于9000元");
            return false;
        }

        return true;
    }
}
