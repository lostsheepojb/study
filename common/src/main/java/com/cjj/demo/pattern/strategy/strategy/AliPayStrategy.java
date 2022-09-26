package com.cjj.demo.pattern.strategy.strategy;

/**
 * @Description
 * @Author cjj
 * @Date 2022/9/19 17:37
 **/
public class AliPayStrategy implements PayStrategy {
    @Override
    public void pay() {
        System.out.println("支付成功，使用了支付宝支付！");
    }
}
