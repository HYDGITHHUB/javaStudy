## Arrays

```java
import java.util.Arrays;
```

### 1. Arrays.toString()

将数组直接以字符串的形式输出

```java
System.out.println(Arrays.toString(array));
```

### 2. equales()

```java
int[] array = {1,2,3,4,5};
        int[] arrays = {1,2,3,4,5,6};
        //equals重载,array从0到5 和 arrays从0到5比较
//        boolean flag = Arrays.equals(array,0,5,arrays,0,5);
        boolean flag2 = Arrays.equals(array,arrays);
        System.out.println(flag2);
```

### 3. fill()

给数组array中全部赋值66

```java
Arrays.fill(array,66);
```

### 4. copeOf()

拷贝一个数组到一个新的数组中，长度自定义

```java
int[] newArray = Arrays.copyOf(array,10);
System.out.println(Arrays.toString(newArray));
```

### 5. binarySearch()

查询升序数组里面某个数的位置，不是升序数组会查询错误

```java
int index = Arrays.binarySearch(arrays,6);
System.out.println(index);
```

### 6. sort()

将数组升序排序

```java
int[] array = {66,67,86,99,88};
Arrays.sort(array);
System.out.println(Arrays.toString(array));
```

### 7. asList

　首先，该方法是将数组转化为list。有以下几点需要注意：

　　（1）该方法不适用于基本数据类型（byte,short,int,long,float,double,boolean）

　　（2）该方法将数组与列表链接起来，当更新其中之一时，另一个自动更新

　　（3）不支持add和remove方法

![image-20200803085359356](C:\Users\userhan\AppData\Roaming\Typora\typora-user-images\image-20200803085359356.png)