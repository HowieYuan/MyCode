package com.howie.java.java8.stream;

/**
 * Created with IntelliJ IDEA
 *
 * @Author yuanhaoyue swithaoy@gmail.com
 * @Description
 * @Date 2018-07-23
 * @Time 18:50
 */
public class Person {
    private String name;
    private Integer age;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
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

    public void setAge(int age) {
        this.age = age;
    }

    public int getRangeAge() {
        if (age <= 25) {
            return 1;
        } else {
            return 2;
        }
    }

    public void test() {
        System.out.println(s());
    }

    public String s(){
        return "Person";
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }



}
