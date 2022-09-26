package com.cjj.demo.pattern.chain;

import com.cjj.demo.pattern.chain.bean.ExamineContent;
import com.cjj.demo.pattern.chain.handler.BossExamineChainHandler;
import com.cjj.demo.pattern.chain.handler.ManagerExamineChainHandler;

import java.math.BigDecimal;

/**
 * @Description
 * @Author cjj
 * @Date 2022/9/14 10:41
 **/
public class Test {
    public static void main(String[] args) {
        ExamineContent examineContent = new ExamineContent();
        examineContent.setAmount(BigDecimal.valueOf(9000));

        ExamineChain<ExamineContent> chain = new ExamineChain<>();
        chain.addHandler(new ManagerExamineChainHandler());
        chain.addHandler(new BossExamineChainHandler());

        chain.doChain(examineContent);
    }
}
