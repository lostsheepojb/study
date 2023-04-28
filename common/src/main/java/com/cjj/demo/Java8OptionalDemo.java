package com.cjj.demo;

import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

/**
 * @author cjj
 * @date 2023/4/27 16:35
 * @description
 **/
public class Java8OptionalDemo {

    @Getter
    @Setter
    class TestUser {
        private Integer id;
        private String name;
        private Address address;
    }

    @Getter
    @Setter
    class Address {
        private Integer id;
        private String addr;
        private City city;
    }

    @Getter
    @Setter
    class City {
        private Integer id;
        private String name;
        private String code;
    }

    public void test() {
        TestUser user = new TestUser();
        user.setId(1);
        user.setName("小明");
        Address address = new Address();
        address.setAddr("广东省广州市天河区001街001号");
        City city = new City();
        city.setName("广州市");

        address.setCity(city);
        user.setAddress(address);

        Optional<TestUser> userOptional = Optional.ofNullable(user);

        //1、判断user是否为空
        System.out.println("user不为空-" + userOptional.isPresent());

        //2、获取user对象：如果user为空，则抛出异常
        System.out.println("get:" + userOptional.get());

        //3、获取user对象：如果user为空，则返回参数中的对象，否则返回user对象，并不会创建orElse方法参数中的对象
        System.out.println("orElse:" + userOptional.orElse(new TestUser()));

        //4、获取user对象：如果user为空，则返回参数中的对象，否则返回user对象，并会创建orElseGet方法参数中的对象
        System.out.println("orElseGet:" + (userOptional.orElseGet(() -> new TestUser())));

        //5、如果user为空，则抛出orElseThrow方法参数中的异常，否则不抛出
        try {
            System.out.println(userOptional.orElseThrow(() -> new RuntimeException("user is null")));
        } catch (RuntimeException e) {
            System.out.println("orElseThrow: user is null");
        }

        //6、过滤user对象：获取name为小明的user对象，没有就返回null
        TestUser testUser = userOptional.filter(item -> "小明".equals(item.getName())).orElse(null);
        System.out.println("filter:" + testUser);

        //7、转换Optional中包装类：将Optional包装的TestUser类转换成Address类，返回结果为Optional<Address>
        Optional<Address> address1 = userOptional.map(TestUser::getAddress);
        System.out.println("map:" + address1);

        //8、转换Optional中包装类：将Optional包装的TestUser类转换成Address类，返回结果为Optional<Address>
        Optional<Address> address2 = userOptional.flatMap(u -> Optional.ofNullable(u.getAddress()));
        System.out.println("flatMap:" + address2);

        /*
            map与flapMap的区别：
            (1)传参不同，map的参数是没有被Optional包装过的，flapMap的参数是包装过的
            (2)返回对象的方式不同，map方法是将参数包装后返回，flapMap方法是返回传参对象
         */

        //Optional的常用方式1：获取user的城市名称，如果城市为null，就返回空字符串
        userOptional.map(TestUser::getAddress).map(Address::getCity).map(City::getName).orElse("");

        //Optional的常用方式2：判断user.id是否为空，如果user或者user.id为空，则返回false，否则返回true
        System.out.println("user.id不为空:" + userOptional.map(TestUser::getId).isPresent());

    }


    public static void main(String[] args) {
        new Java8OptionalDemo().test();
    }


}
