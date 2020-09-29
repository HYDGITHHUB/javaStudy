package com.pong.test;

public class TestStudent {
    public static void main(String[] args) {
        TestAbstract t = new Test5();
        t.aVoid();
    }
    String name;
    Integer age;

    public TestStudent(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "TestStudent{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
