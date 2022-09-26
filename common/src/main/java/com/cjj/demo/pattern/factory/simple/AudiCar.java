package com.cjj.demo.pattern.factory.simple;

/**
 * @Description
 * @Author cjj
 * @Date 2022/9/14 11:24
 **/
public class AudiCar implements Car {
    @Override
    public void boot() {
        System.out.println("audi starting !");
    }

    @Override
    public void run() {
        System.out.println("audi running !");
    }

    public void lightShow() {
        System.out.println("audi light show !");
    }
}
