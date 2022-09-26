package com.cjj.demo.pattern.chain;


import com.cjj.demo.pattern.chain.handler.ExamineChainHandler;

import java.util.LinkedList;

/**
 * @Description 责任链模式-审核处理
 * @Author cjj
 * @Date 2022/9/14 9:43
 **/
public class ExamineChain<T> {

    private LinkedList<ExamineChainHandler> handlers;

    public ExamineChain() {
        handlers = new LinkedList<>();
    }

    /**
     * 添加责任链审核处理器，先添加的处理器先执行
     *
     * @param handler
     */
    void addHandler(ExamineChainHandler handler) {
        handlers.offer(handler);
    }

    /**
     * 执行审核流程，执行顺序是处理器添加的顺序
     *
     * @return
     */
    boolean doChain(T t) {

        while (handlers.size() > 0) {
            ExamineChainHandler handler = handlers.poll();
            boolean result = handler.examine(t);
            if (!result) {
                return false;
            }
        }

        System.out.println("审核通过");

        return true;
    }
}
