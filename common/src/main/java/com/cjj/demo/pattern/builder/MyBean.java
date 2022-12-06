package com.cjj.demo.pattern.builder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author cjj
 * @date 2022/12/6 14:39
 * @description
 **/
@Getter
@Setter
@ToString
public class MyBean {
    private int age;
    private double hight;
    private double weight;
    private double bmi;


    /**
     * 私有化构造器，使对象只能通过builder创建
     *
     * @param builder
     */
    private MyBean(Builder builder) {
        this.age = builder.age;
        this.hight = builder.hight;
        this.weight = builder.weight;
        this.bmi = builder.bmi;
    }


    /**
     * 建造者模式，适合创建复杂的对象
     */
    public static class Builder {
        private int age;
        private double hight;
        private double weight;
        private double bmi;

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public Builder hight(double hight) {
            this.hight = hight;
            return this;
        }

        public Builder weight(double weight) {
            this.weight = weight;
            return this;
        }

        public MyBean build() {
            if (hight != 0 && weight != 0) {
                this.bmi = hight * weight / 100;
            }
            MyBean myBean = new MyBean(this);
            return myBean;
        }
    }

}
