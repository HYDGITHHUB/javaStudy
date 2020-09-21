```java
package com.pong.util;

//lambda 表达式的演进史
//实现的接口 必须 是函数式接口(只有一个方法)
/**
 * 1. 普通实现方法
 * 2. 静态内部类
 * 3. 局部内部类
 * 4. 匿名内部类
 * 5. Lambda 表达式 (必须通过类定义)
 */

public class Lambda {
    // 2. 静态内部类
    static class Student2 implements People {
        @Override
        public void specious() {
            System.out.println("学生2");
        }
    }
    public static void main(String[] args) {
        //3. 局部内部类
        class Student3 implements People{
            @Override
            public void specious() {
                System.out.println("学生3");
            }
        }

        People student = new Student();
        student.specious();

        Student2 student2 = new Student2();
        student2.specious();

        Student3 student3 = new Student3();
        student3.specious();

        //4. 匿名内部类
        Student student4 = new Student() {
            @Override
            public void specious() {
                System.out.println("学生4");
            }
        };
        student4.specious();

        //5. Lambda 只有一个参数的时候可以省略 ()
        People student5 = ()->{
            System.out.println("学生5");
        };
        student5.specious();



    }
}

interface People {
    public void specious();
}
// 1. 正常方法
class Student implements People {

    @Override
    public void specious() {
        System.out.println("学生1");
    }
}


```

