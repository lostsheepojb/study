package com.cjj.demo.pattern.factory;

import com.cjj.demo.pattern.factory.simple.AudiCar;
import com.cjj.demo.pattern.factory.simple.Car;
import com.cjj.demo.pattern.factory.simple.CarSimpleFactory;

/**
 * @Description
 * @Author cjj
 * @Date 2022/9/14 11:12
 **/
public class Test {
    public static void main(String[] args) {
        AudiCar audi =(AudiCar) CarSimpleFactory.getInstance(CarSimpleFactory.AUDI);

        audi.boot();
        audi.run();
        audi.lightShow();

        Car benz = CarSimpleFactory.getInstance(CarSimpleFactory.BENZ);
        benz.boot();
        benz.run();
    }
}
