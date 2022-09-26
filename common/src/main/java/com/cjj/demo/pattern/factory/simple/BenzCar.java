package com.cjj.demo.pattern.factory.simple;

/**
 * @Description
 * @Author cjj
 * @Date 2022/9/14 11:23
 **/
public class BenzCar implements Car {
    @Override
    public void boot() {
        System.out.println("benz starting !");
    }

    @Override
    public void run() {
        System.out.println("benz running !");
    }

    public void music() {
        System.out.println("ben music !");
    }
}
