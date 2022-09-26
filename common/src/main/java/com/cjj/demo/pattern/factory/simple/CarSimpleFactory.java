package com.cjj.demo.pattern.factory.simple;

/**
 * @Description
 * @Author cjj
 * @Date 2022/9/14 11:31
 **/
public class CarSimpleFactory {
    public static int BENZ = 1;
    public static int AUDI = 2;

    /**
     * @param carType 品牌类型
     * @return
     */
    public static Car getInstance(int carType) {

        switch (carType) {
            case 1:
                return new BenzCar();
            case 2:
                return new AudiCar();
        }
        return null;
    }
}
