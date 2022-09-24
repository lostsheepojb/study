package com.cjj.demo;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description java8 steam的基本用法 获取流.处理流.终结流，例如：stream.map().collect()
 * @Author cjj
 * @Date 2022/8/10 10:53
 **/
public class Java8StreamDemo {
    private static List<Student> list;

    static {
        list = init();
    }

    public static void main(String[] args) {
        join();
    }

    /**
     * 1.对列表元素某一属性升序排序
     */
    public static List<Student> sortAsc() {
        System.out.println("排序前：");
        for (Student student : list) {
            System.out.println(student.getGrade());
        }
        //按年级升序排序
        List<Student> afeter = list.stream().sorted(Comparator.comparing(Student::getGrade)).collect(Collectors.toList());

        System.out.println("升序排序后：");
        for (Student student : afeter) {
            System.out.println(student.getGrade());
        }
        return afeter;
    }

    /**
     * 2.对列表元素某一属性降序排序
     */
    public static List<Student> sortDesc() {
        System.out.println("排序前：");
        for (Student student : list) {
            System.out.println(student.getGrade());
        }
        //按年级升序排序
        List<Student> afeter = list.stream().sorted(Comparator.comparing(Student::getGrade).reversed()).collect(Collectors.toList());

        System.out.println("降序排序后：");
        for (Student student : afeter) {
            System.out.println(student.getGrade());
        }
        return afeter;
    }


    /**
     * 2.对列表元素某一属性分组排序
     */
    public static Map<Integer, List<Student>> sortGroup() {
        System.out.println("分组排序前：");
        for (Student student : list) {
            System.out.println(student.getGrade() + ": " + student.getScore());
        }
        //按年级分组对分数升序排序
        Map<Integer, List<Student>> map = list.stream().sorted(Comparator.comparing(Student::getScore)).collect(Collectors.groupingBy(Student::getGrade));
        Set<Integer> keySet = map.keySet();
        System.out.println("分组排序后：");
        for (Integer item : keySet) {
            List<Student> students = map.get(item);
            for (Student student : students) {
                System.out.println(student.getGrade() + ": " + student.getScore());
            }
        }
        return map;
    }


    /**
     * 3.对列表元素某一属性建立新的list
     */
    public static void subColumn() {

        List<String> collect = list.stream().map(Student::getName).collect(Collectors.toList());

        for (String name : collect) {
            System.out.println(name);
        }

    }


    /**
     * 4.对列表元素某一属性求和
     */

    public static void sum() {

        Double score = list.stream().collect(Collectors.summingDouble(Student::getScore));

        System.out.println("分数总和：" + score);

    }


    /**
     * 5.对列表元素某一属性求平均值
     */

    public static void average() {

        System.out.println("分数：");
        for (Student student : list) {
            System.out.println(student.getScore());
        }

        Double score = list.stream().collect(Collectors.averagingDouble(Student::getScore));

        System.out.println("平均分数：" + score);

    }


    /**
     * 6.对列表元素筛选
     */
    public static void select() {

        System.out.println("筛选前：");
        for (Student student : list) {
            System.out.println(student.getAge());
        }
        List<Student> select = list.stream().filter(student -> student.getAge() > 9).collect(Collectors.toList());

        System.out.println("筛选后：");
        for (Student student : select) {
            System.out.println(student.getAge());
        }
    }

    /**
     * 6.查找列表元素某一属性的最大值的元素
     */
    public static void maxOrMin() {

        System.out.println("分数：");
        for (Student student : list) {
            System.out.println(student.getScore());
        }

        Student student = list.stream().max(Comparator.comparing(Student::getScore)).get();
        Student student2 = list.stream().min(Comparator.comparing(Student::getScore)).get();

        System.out.println("最高分数：" + student.getScore());
        System.out.println("最低分数：" + student2.getScore());
    }

    /**
     * 7.遍历元素
     */
    public static void forEach() {

        list.stream().forEach(student -> System.out.println(student.getId()));

    }

    /**
     * 8.元素去重
     */
    public static void distinct() {

        System.out.println("学生年龄去重前：");
        list.stream().sorted(((stu1, stu2) -> stu1.getAge() - stu2.getAge())).forEach(student -> System.out.println(student.getAge()));

        List<Integer> ages = list.stream().map(Student::getAge).collect(Collectors.toList());

        List<Integer> after = ages.stream().distinct().collect(Collectors.toList());

        System.out.println("学生年龄去重后：");
        after.stream().sorted(Comparator.comparing(o -> o)).forEach(age -> System.out.println(age));
    }

    /**
     * 9.将列表元素拼接成字符串
     */
    public static void join() {
        String result = list.stream().map(student -> student.getId().toString()).collect(Collectors.joining(","));
        System.out.println("将所有的学生用逗号拼接起来：");
        System.out.println(result);
    }


//-----------------------------------------------------------------------------------------------

    /**
     * 初始化列表
     */
    private static List<Student> init() {
        ArrayList<Student> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Random random = new Random();
            Student student = new Student();
            student.setId(i + 1);
            int age = random.nextInt(6) + 6;
            student.setAge(age);
            //int grade = random.nextInt(5) + 1;
            student.setGrade(age - 5);
            Double score = Math.random() * 100;
            student.setScore(score);
            student.setName("小明" + (i + 1) + "号");
            list.add(student);
        }
        return list;
    }

    /**
     * 内部类
     */
    private static class Student {
        private Integer id;
        private String name;
        private Integer age;
        private Double score;
        private Integer grade;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public Double getScore() {
            return score;
        }

        public void setScore(Double score) {
            this.score = score;
        }

        public Integer getGrade() {
            return grade;
        }

        public void setGrade(Integer grade) {
            this.grade = grade;
        }

        @Override
        public String toString() {
            return "id:" + id + "name:" + name + "age:" + age + "score:" + score + "grade:" + grade;
        }
    }
}
