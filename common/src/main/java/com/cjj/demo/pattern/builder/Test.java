package com.cjj.demo.pattern.builder;

/**
 * @author cjj
 * @date 2022/12/6 14:39
 * @description 建造者模式，适合创建复杂的对象
 **/
public class Test {

    public static void main(String[] args) {
        MyBean.Builder builder = new MyBean.Builder();
        MyBean myBean = builder.age(20).weight(60.0).hight(172.7).build();
        System.out.println(myBean);
    }
}
