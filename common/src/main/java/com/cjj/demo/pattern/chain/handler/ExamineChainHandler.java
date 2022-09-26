package com.cjj.demo.pattern.chain.handler;

/**
 * 责任链处理器
 * T-审核内容
 */
public interface ExamineChainHandler<T> {
    /**
     * 审核
     * @return
     */
    boolean examine(T t);
}
