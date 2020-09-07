# IO流

IO 操作是指使用 Java 程序完成输入（Input）、输出（Output）的功能，所谓输入是指将文件（图片、文本、Word、Excel、MP3、MP4）以数据流的形式读取到 Java 程序中（JVM 内存）。

输出相反，指将 Java 程序（JVM 内存）中的数据流写入到文件中。

## 1. File 类

Java 通过 File 来描述计算机中的文件，每个文件都可以抽象成一个 File 对象。

| 方法                               | 描述                               |
| ---------------------------------- | ---------------------------------- |
| public File(String pathname)       | 根据路径创建对象                   |
| public String getName()            | 获取文件名                         |
| public String getParent()          | 获取文件所在的目录                 |
| public File getParentFile()        | 获取文件所在的目录对应的 File 对象 |
| public String getPath()            | 获取文件路径                       |
| public boolean exists()            | 判断文件是否存在                   |
| public boolean isDirectory()       | 判断对象是否为目录                 |
| public boolean isFile()            | 判断对象是否为文件                 |
| public long length()               | 获取文件的大小                     |
| public boolean createNewFile()     | 创建新的文件                       |
| public boolean delete()            | 删除文件                           |
| public boolean mkdir()             | 创建目录                           |
| public boolean renameTo(File file) | 文件重命名                         |

UTF-8 编码下，一个汉字是 3 个 byte，一个字母、一个数字、一个符号都是 1 个 byte。

```java
package com.southwind.demo1;

import java.io.File;

public class Test5 {
    public static void main(String[] args) {
        File file = new File("D:/java/test.txt");
        if(file.exists()){
            System.out.println(file.getName());
            System.out.println(file.length());
            System.out.println(file.getParent());
            System.out.println(file.getParentFile().isDirectory());
            System.out.println(file.isFile());
            System.out.println(file.getParentFile().isFile());
            System.out.println(file.isDirectory());
            System.out.println(file.getPath());
        }else{
            throw new RuntimeException("文件不存在！");
        }
    }
}
```

Windows

D:\java\test.txt

Mac

/Users/PC名称/Desktop

```java
package com.southwind.demo1;

import java.io.File;
import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        //创建新文件
        File file = new File("D:\\java2\\test2.txt");
        //给文件重命名
        File newFile = new File("D:\\java\\test3.txt");
        boolean b = file.renameTo(newFile);
        System.out.println(b);
        File file3 = new File("D:\\java\\test3.txt");
        boolean delete = file3.delete();
        System.out.println(delete);
    }
}
```

## 2. 流

流是一种以先进先出的方式传输数据的序列，Java 中的流有很多种不同的分类：

按照方向分，可以分为输入流 I 和输出流 O。

按照单位分，可以分为字节流和字符流，字节流是指每次处理的数据是以字节为单位的，字符流是指每次处理的数据以字符为单位。UTF-8 编码下中文 1 个字符 = 3 个字节，英文/数字/符号 1 个字符 =  1 个字节。

### 2.0 节点流和处理流

按照功能分，可以分为节点流和处理流。

非文本类型的文件只能用字节流去读取，不能用字符流读取。

节点流：IO 流中最基础的流，如何来区分节点流？只需要判断该流能不能直接关联到文件对象 File。

该流有以 File 为参数的构造器，就是节点流，否则就是处理流。

```java
package com.southwind.demo1;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class Test3 {
    public static void main(String[] args) throws Exception {
        //创建节点流
        InputStream inputStream = new FileInputStream("D:\\javahome\\test.txt");
        //创建处理流
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"UTF-8");
        //创建字符流
        Reader reader = inputStreamReader;
        char[] chars = new char[1024];
        int length = reader.read(chars);
        System.out.println(new String(chars,0,length));
    }
}
```

### 2.1 字节流（节点流）

把文件中的数据流读到 Java 内存中，以字节为单位处理数据流。

#### 2.1.1 输入字节流 InputStream

InputStream 常用方法

| 方法                                | 描述                                     |
| ----------------------------------- | ---------------------------------------- |
| int read()                          | 以字节为单位读取数据                     |
| int read(byte[] b)                  | 读取数据，将数据存入 byte 数组中         |
| int read(btyte[] b,int off,int len) | 读取数据，将数据存入 byte 数组的指定区间 |
| byte[] readAllBytes()               | 将数据存入 byte 数组中                   |
| int available()                     | 返回当前数据流中未读取的数据个数         |
| void close()                        | 关闭数据流，释放资源                     |

```java
package com.southwind.demo1;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class InputStreamTest {
    public static void main(String[] args) throws Exception {
        //获取目标文件
        File file = new File("D:\\java\\test.txt");
        //字节输入流
        InputStream inputStream = new FileInputStream(file);
        //读取数据 1 byte 8位二进制数
//        for (int i = 0; i < file.length(); i++) {
//            System.out.println(inputStream.read());
//        }
        int temp = 0;
        System.out.println("读取之前，可读的数据还有：" + inputStream.available());
        while((temp = inputStream.read()) != -1){
            System.out.println(temp);
            System.out.println("读取中，可读的数据还有：" + inputStream.available());
        }
        System.out.println("读取完毕，可读的数据还有：" + inputStream.available());
        inputStream.close();
    }
}
```

```java
package com.southwind.demo1;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;

public class InputStreamTest2 {
    public static void main(String[] args) throws Exception {
        InputStream inputStream = new FileInputStream("D:\\java\\test.txt");
        byte[] bytes = new byte[10];
//        int length = inputStream.read(bytes,3,6);
//        System.out.println(length);
//        System.out.println(Arrays.toString(bytes));
        byte[] bytes1 = inputStream.readAllBytes();
        System.out.println(Arrays.toString(bytes1));
    }
}
```

#### 2.1.2 输出字节流 OutputStream

OutputStream 常用方法

| 方法                                 | 描述                             |
| ------------------------------------ | -------------------------------- |
| void write(int b)                    | 以字节位单位写出数据             |
| void write(byte b[])                 | 将 byte 数组中的数据写出         |
| void write(byte[] b,int off,int len) | 将 byte 数组中指定区间的数据写出 |
| void flush()                         | 强制将缓冲区的数据同步到输出流中 |
| void close()                         | 关闭数据流                       |

```java
package com.southwind.demo1;

import java.io.FileOutputStream;
import java.io.OutputStream;

public class OutputStreamTest {
    public static void main(String[] args) throws Exception {
        OutputStream outputStream = new FileOutputStream("D:\\java\\test.txt");
        byte[] bytes = {'h','e','l','l','o','w','o','r','l','d'};
        outputStream.write(bytes,3,3);
        outputStream.flush();
        outputStream.close();
    }
}
```

### 2.2 字符流（节点流）

将 Java 内存中的数据写入到文件中，以字节为单位处理数据流。

UTF-8：汉字 1 个字符 = 3 个字节，其他 1 个字符 = 1 个字节

跟字节流一样，只不过每次处理的数据单位不同，以字符为单位处理数据流。

#### 2.2.1 Reader 字符输入流

| 方法                               | 描述                         |
| ---------------------------------- | ---------------------------- |
| int read()                         | 以字符为单位读取数据         |
| int read(char c[])                 | 将数据读入 char 数组中       |
| int read(char c[],int off,int len) | 将数据读入 char 数组指定区间 |
| void close()                       | 关闭数据流                   |
| long transferTo(Writer out)        | 将数据直接读入到字符输出流   |

```java
package com.southwind.demo2;

import java.io.*;
import java.util.Arrays;

public class ReaderTest {
    public static void main(String[] args) throws Exception {
        //字符流，一个字符就是一个汉字 26159 = 是
//        Reader reader = new FileReader("D:\\java\\test.txt");
//        System.out.println(reader.read());
//        System.out.println(reader.read());
//        System.out.println(reader.read());
//        System.out.println(reader.read());
//        System.out.println(reader.read());
        //字节流，一个字节是 1/3 个汉字，230&152&175 是的 1/3
//        InputStream inputStream = new FileInputStream("D:\\\\java\\\\test.txt");
//        System.out.println(inputStream.read());
//        System.out.println(inputStream.read());
//        System.out.println(inputStream.read());
//        System.out.println(inputStream.read());
//        System.out.println(inputStream.read());
//        System.out.println(inputStream.read());
//        System.out.println(inputStream.read());
        Reader reader = new FileReader("D:\\java\\test.txt");
//        int temp = 0;
//        while((temp = reader.read())!=-1){
//            System.out.println(temp);
//        }
        char[] chars = new char[1024];
        int length = reader.read(chars);
        chars = Arrays.copyOf(chars,length);
        System.out.println(length);
        System.out.println(Arrays.toString(chars));
        String str = new String(chars);
        System.out.println(str);
        reader.close();
    }
}
```

#### 2.2.2 Writer 字符输出流

Writer 实现了 Appendable 接口，可以将 char 类型的数据读入到数据缓冲区中。

| 方法                              | 描述                             |
| --------------------------------- | -------------------------------- |
| write(int c)                      | 以字符为单位写出数据             |
| write(char c[])                   | 将 char 数组中的数据写出         |
| write(char c[],int off,int len)   | 将 char 数组中指定区间的数据写出 |
| write(String str)                 | 将 String 数据写出               |
| write(String str,int off,int len) | 将 String 指定区间的数据写出     |
| flush()                           | 强制将缓冲区的数据同步到输出流   |
| close()                           | 关闭数据流                       |

```java
package com.southwind.demo1;

import java.io.FileWriter;
import java.io.Writer;

public class Test {
    public static void main(String[] args) throws Exception {
        Writer writer = new FileWriter("D:\\javahome\\test.txt");
        char[] chars = {'你','好','世','界'};
//        writer.write(20320);
//        writer.write(22909);
//        writer.write(chars,2,2);
        String str = "hello world，你好世界";
        writer.write(str,10,6);
        writer.flush();
        writer.close();
    }
}
```

### 2.3 缓冲流（缓冲流）

缓冲流自带缓冲区，可以一次性从硬盘中读取部分数据存入缓冲区，再写入程序内存。

![image-20200712104929546](C:\Users\userhan\AppData\Roaming\Typora\typora-user-images\image-20200712104929546.png)

缓冲流自带缓冲区，可以一次性从硬盘中读取部分数据存入缓冲区，再写入程序内存。

#### 2.3.1 字符输入缓冲流 BufferedReader

```java
package com.southwind.demo2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;

public class Test {
    public static void main(String[] args) throws Exception {
        Reader reader = new FileReader("D:\\javahome\\test.txt");
        BufferedReader bufferedReader = new BufferedReader(reader);
        //读取一整行数据，返回字符串
        String str = null;
        while((str = bufferedReader.readLine())!=null){
            System.out.println(str);
        }
    }

}
```

#### 2.3.2 字符输出缓冲流 BufferedWriter

```java
package com.southwind.demo2;

import java.io.*;

public class Test2 {
    public static void main(String[] args) throws Exception {
        //字符流
        Writer writer = new FileWriter("D:\\javahome\\test2.txt");
        //缓冲字符流
        BufferedWriter bufferedWriter = new BufferedWriter(writer);
        String content = "20世纪90年代，硬件领域出现了单片式计算机系统，这种价格低廉的系统一出现就立即引起了自动控制领域人员的注意，因为使用它可以大幅度提升消费类电子产品（如电视机顶盒、面包烤箱、移动电话等）的智能化程度。Sun公司为了抢占市场先机，在1991年成立了一个称为Green的项目小组，帕特里克、詹姆斯·高斯林、麦克·舍林丹和其他几个工程师一起组成的工作小组在加利福尼亚州门洛帕克市沙丘路的一个小工作室里面研究开发新技术，专攻计算机在家电产品上的嵌入式应用。由于C++所具有的优势，该项目组的研究人员首先考虑采用C++来编写程序。但对于硬件资源极其匮乏的单片式系统来说，C++程序过于复杂和庞大。另外由于消费电子产品所采用的嵌入式处理器芯片的种类繁杂，如何让编写的程序跨平台运行也是个难题。为了解决困难，他们首先着眼于语言的开发，假设了一种结构简单、符合嵌入式应用需要的硬件平台体系结构并为其制定了相应的规范，其中就定义了这种硬件平台的二进制机器码指令系统（即后来成为“字节码”的指令系统），以待语言开发成功后，能有半导体芯片生产商开发和生产这种硬件平台。对于新语言的设计，Sun公司研发人员并没有开发一种全新的语言，而是根据嵌入式软件的要求，对C++进行了改造，去除了留在C++的一些不太实用及影响安全的成分，并结合嵌入式系统的实时性要求，开发了一种称为Oak的面向对象语言。";
        bufferedWriter.write(content);
        bufferedWriter.flush();
        bufferedWriter.close();
    }
}
```

非文本类型的文件只能用字节流去读取，不能用字符流读取。

```java
package com.southwind.demo3;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class Test {
    public static void main(String[] args) throws Exception {
        //读
        InputStream inputStream = new FileInputStream("D:\\javahome\\1.png");
        //写
        OutputStream outputStream = new FileOutputStream("C:\\java\\2.png");
        int temp = 0;
        int count = 0;
        while((temp = inputStream.read())!=-1){
            outputStream.write(temp);
            count++;
        }
        System.out.println(count);
        outputStream.flush();
        outputStream.close();
        inputStream.close();
    }
}
```

```java
package com.southwind.demo3;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;

public class Test2 {
    public static void main(String[] args) throws Exception {
        Reader reader = new FileReader("D:\\javahome\\test2.txt");
        Writer writer = new FileWriter("C:\\java\\copy2.txt");
        int temp = 0;
        int count = 0;
        while((temp = reader.read())!=-1){
            writer.write(temp);
            count++;
        }
        System.out.println(count);
        writer.flush();
        writer.close();
        reader.close();
    }
}
```

```java
package com.southwind.demo3;

import java.io.*;

public class Test3 {
    public static void main(String[] args) throws Exception {
        Reader reader = new FileReader("D:\\javahome\\test2.txt");
        Writer writer = new FileWriter("C:\\java\\copy22.txt");
        BufferedReader bufferedReader = new BufferedReader(reader);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);
        String str = null;
        int count=0;
        while ((str = bufferedReader.readLine())!=null){
            bufferedWriter.write(str);
            count++;
        }
        System.out.println(count);
        bufferedWriter.flush();
        bufferedWriter.close();
        bufferedReader.close();
        writer.close();
        reader.close();
    }
}
```

## 3. 序列化和反序列化

都是完成对 Java 对象的操作，将 Java 对象写入到本地硬盘的文件中，将本地文件中存储的 Java 对象还原到 Jave 内存中。

序列化就是指将内存中的对象输出到硬盘中进行保存，反序列化就是相反的操作，从文件中读取数据并且还原成内存中的对象。

### 3.1 序列化

给类实现接口 java.io.Serializable，该类的对象就具备了序列化的功能。

```java
package com.southwind.demo1;

import java.io.Serializable;

public class User implements Serializable {
    private Integer id;
    private String name;
    private Integer age;

    public User(Integer id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
```

节点流（直接连接文件）、处理流（必须建立在节点流的基础上）

```java
package com.southwind.demo1;

import java.io.*;

public class Test {
    public static void main(String[] args) throws Exception {
        //创建对象
        User user = new User(1,"张三",22);
        //开始序列化
        OutputStream outputStream = new FileOutputStream("D:\\javahome\\test.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(user);
        objectOutputStream.flush();
        objectOutputStream.close();
        outputStream.close();
    }
}
```

持久化：将数据永久地保存下来。

### 3.2 反序列化

从文件中读取数据还原成 Java 对象。

```java
package com.southwind.demo1;

import java.io.*;

public class Test2 {
    public static void main(String[] args) throws Exception {
        InputStream inputStream = new FileInputStream("D:\\javahome\\test.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        User user = (User) objectInputStream.readObject();
        System.out.println(user);
        objectInputStream.close();
        inputStream.close();
    }
}
```
