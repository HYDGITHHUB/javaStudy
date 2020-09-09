## Collections 工具

Collections 是 JDK 提供的专门用来操作集合的工具类，比如添加元素、对元素进行排序、替换元素等。

Collections 和 Arrays 很类似，Arrays 是针对数组的工具类，Collections 是针对集合的工具类。

| 方法         | 描述                                             |
| ------------ | ------------------------------------------------ |
| sort         | 对集合进行排序                                   |
| binarySearch | 检索元素在集合中的下标，集合中的元素必须升序排列 |
| get          | 根据下标获取集合中的元素                         |
| reverse      | 对集合中的元素进行反序输出                       |
| swap         | 通过 key 取出 value                              |
| fill         | 将集合中的所有元素进行替换                       |
| min          | 返回集合中的最小值                               |
| max          | 返回集合中的最大值                               |
| replaceAll   | 将集合中所有的值进行替换                         |
| addAll       | 向集合中添加元素                                 |

```java
package com.southwind.demo3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Test {
    public static void main(String[] args) {
        ArrayList list = new ArrayList();
//        list.add("abHello");
//        list.add("acWorld");
//        list.add("acWorld");
//        System.out.println("添加之前的集合" + list);
//        Collections.addAll(list,"aaJava","adJavaSE","aeJavaME");
//        System.out.println("添加之后的集合" + list);
//        Collections.reverse(list);
//        System.out.println("反转之后的集合" + list);
//        Collections.swap(list,1,3);
//        System.out.println("交换之后的集合" + list);
//        Collections.sort(list);
//        System.out.println("升序之后的集合" + list);
//        //降序？
//        Collections.reverse(list);
//        System.out.println("降序之后的集合" + list);
//        //集合元素必须是升序排列，才能调方法
//        int index = Collections.binarySearch(list,"adJavaSE");
//        System.out.println(index);
//        Collections.replaceAll(list,"acWorld","ccc");
//        System.out.println(list);
        list = new ArrayList();
        Collections.addAll(list,new Data(1),new Data(3),new Data(2));
        System.out.println(list);
        Collections.sort(list, new Comparator<Object>() {
            @Override
            public int compare(Object o1, Object o2) {
                Data data1 = (Data)o1;
                Data data2 = (Data)o2;
                if(data1.id > data2.id){
                    return 1;
                }else if(data1.id == data2.id){
                    return 0;
                }else{
                    return -1;
                }
            }
        });
        System.out.println(list);

    }
}

class Data{
    public Integer id;

    public Data(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Data{" +
                "id=" + id +
                '}';
    }
}
```

