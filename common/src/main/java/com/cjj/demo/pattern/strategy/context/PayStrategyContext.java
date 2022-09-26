package com.cjj.demo.pattern.strategy.context;


import com.cjj.demo.pattern.strategy.strategy.PayStrategy;

/**
 * @Description 支付环境
 * @Author cjj
 * @Date 2022/9/19 17:40
 **/
public class PayStrategyContext {

    private PayStrategy payStrategy;

    public void setPayStrategy(PayStrategy payStrategy) {
        this.payStrategy = payStrategy;
    }

    public void pay(){
        System.out.println("准备支付，锁定账户");
        this.payStrategy.pay();
        System.out.println("支付完成，详情写进流水");
    }
}
