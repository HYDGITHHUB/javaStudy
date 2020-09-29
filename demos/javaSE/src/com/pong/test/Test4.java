package com.pong.test;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

public class Test4 implements Cloneable {



    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public static void main(String[] args) throws InterruptedException {

        ArrayDeque arrayDeque = new ArrayDeque();
        

        List list = new ArrayList<>();
        List list1 = new LinkedList();
        List list2 = new Vector();
        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();

        StringBuilder stringBuilder = new StringBuilder();
        StringBuffer stringBuffer = new StringBuffer();

        Set set = new HashSet();
        Set set1 = new TreeSet();
        Set set2 = new LinkedHashSet();

        Map map = new HashMap();
        Map map1 =  new Hashtable();
        Map map2 = new TreeMap();
        Map map3 = new LinkedHashMap();
//        Test test = new Test();
//        Test test1 = new Test();
//        Test[] tests = new Test[10];
//        tests[0] = test;
//        tests[1] = test1;
//        for (Test test2 : tests) {
//            System.out.println(test2);
//        }
//        BitSet set = new BitSet();
//        Hashtable hashtable = new Hashtable();
//        HashSet set1 = new HashSet();
        Vector<TestStudent> vector = new Vector<>();
        vector.add(new TestStudent("zhangsan",14));
        vector.add(new TestStudent("lisi",14));
        vector.add(new TestStudent("wangwu",14));
        for (TestStudent testStudent : vector) {
            System.out.println(testStudent);
        }
    }

    String name;
    Integer age;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Test4 test4 = (Test4) o;
        return Objects.equals(name, test4.name) &&
                Objects.equals(age, test4.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}
