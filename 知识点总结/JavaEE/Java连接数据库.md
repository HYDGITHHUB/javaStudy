# Java连接数据库

## 1. JDBC

Java DataBase Connectivity 是一个**独立于特定数据库**管理系统，**通用**的 SQL 数据库存取和操作的公共接口。

JDBC 就是 JDK 提供的一套接口和类，用来管理数据库，意义和 JVM 类似，屏蔽底层，让开发者可以使用一套统一通用的标准进行开发，而不需要考虑底层的转换。

JDBC 体系结构

1、面向应用的 API，供应用开发人员调用。

Java 官方提供，java.sql 和 javax.sql 中

DriverManager

Connection

Statement

ResultSet

2、面向数据库的 API，供数据库开发厂商研发驱动程序。

数据库驱动，打包之后的 jar 文件。

## 2. 安装 Maven

1、下载 Maven 压缩包。

2、找个地方（不要有中文路径，尽量不要有空格），解压。

3、配环境变量

4、cmd 输入 mvn -version

创建 Maven 工程

## 3. JDBC使用过程

1、加载数据库驱动，Java 程序和 MySQL 的桥梁。

2、获取 Connection，一次连接。

3、由 Statement 执行 SQL 语句，Connection 产生的。

4、ResultSet 接收 Statement 执行之后的结果（查询操作）。

### 3.1 实际开发

1、创建 Maven 工程，pom.xml 中引入 MySQL 驱动依赖。

```xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.21</version>
</dependency>
```

2、写代码。

工具类

```java
package com.southwind.jdbctest.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTools {

    static {
        try {
            //加载驱动类
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        Connection connection = null;
        try {
            //获取连接
            String url = "jdbc:mysql://localhost:3306/mybd2";
            String user = "root";
            String password = "123456";
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }

    public static void release(Statement statement,Connection connection){
        try {
            if(statement!=null){
                statement.close();
            }
            if(connection!=null){
                connection.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
```

添加数据

```java
Connection connection = JDBCTools.getConnection();
String sql = "insert into user(name) values('张三')";
Statement statement = connection.createStatement();
int row = statement.executeUpdate(sql);
System.out.println(row);
JDBCTools.release(statement, connection);
```

更新数据

```java
Connection connection = JDBCTools.getConnection();
String sql = "update user set name = '李四' where id = 13";
Statement statement = connection.createStatement();
int row = statement.executeUpdate(sql);
System.out.println(row);
JDBCTools.release(statement, connection);
```

删除数据

```java
Connection connection = JDBCTools.getConnection();
String sql = "delete from user where id = 13";
Statement statement = connection.createStatement();
int row = statement.executeUpdate(sql);
System.out.println(row);
JDBCTools.release(statement, connection);
```

查询数据

```java
Connection connection = JDBCTools.getConnection();
String sql = "select * from user";
Statement statement = connection.createStatement();
ResultSet resultSet = statement.executeQuery(sql);
while (resultSet.next()){
    int id = resultSet.getInt("id");
    String name = resultSet.getString("name");
    System.out.println(id + "-" + name);
}
JDBCTools.release(statement, connection,resultSet);
```

### 3.2 PreparedStatement

PreparedStatment 是 Statement 的子接口，实际开发中一般使用 PreparedStatement。

1、提供 SQL 占位符的功能，避免频繁拼接字符串造成的错误问题。

2、防止 SQL 注入的风险。

SQL 注入：利用某些系统没有对用户输入的数据进行充分检测，在用户输入的数据中注入非法的 SQL 语句，从而利用系统的 SQL 引擎完成恶意行为的操作。

```java
Connection connection = JDBCTools.getConnection();
//        String sql = "select * from user where name = '"+name+"' and password = '"+password+"'";
String sql = "select * from user where name = ? and password = ?";
PreparedStatement preparedStatement = connection.prepareStatement(sql);
preparedStatement.setString(1, name);
preparedStatement.setString(2, password);
ResultSet resultSet = preparedStatement.executeQuery();
if(resultSet.next()){
    System.out.println("登录成功！");
}else{
    System.out.println("登录失败！");
}
JDBCTools.release(preparedStatement, connection, resultSet);
```

优点：

1、不用拼接字符串

2、防止 SQL 注入

3、执行效率更高

## 4. 将图片保存到数据库

原理：将图片转换为二进制流，然后将该二进制流保存到数据库中。

1、在数据库表中添加一个 blob 类型的字段

2、JDBC 添加数据

​	（1）将图片转换成流

​	（2）通过调用 setBlob(InputStream inputStream)

```java
public static void saveImg() throws Exception{
    InputStream inputStream = new FileInputStream("D:\\javahome\\1.png");
    Connection connection = JDBCTools.getConnection();
    String sql = "insert into user(name,password,img) values(?,?,?)";
    PreparedStatement statement = connection.prepareStatement(sql);
    statement.setString(1, "img");
    statement.setString(2, "img");
    statement.setBlob(3, inputStream);
    int row = statement.executeUpdate();
    System.out.println(row);
    inputStream.close();
    JDBCTools.release(statement, connection, null);
}

public static void getImg() throws Exception{
    Connection connection = JDBCTools.getConnection();
    String sql = "select img from user where id = ?";
    PreparedStatement statement = connection.prepareStatement(sql);
    statement.setInt(1, 14);
    ResultSet resultSet = statement.executeQuery();
    if(resultSet.next()){
        Blob blob = resultSet.getBlob(1);
        InputStream inputStream = blob.getBinaryStream();
        OutputStream outputStream = new FileOutputStream("D:\\javahome\\2.png");
        int temp = 0;
        while((temp=inputStream.read())!=-1){
            outputStream.write(temp);
        }
        outputStream.close();
        inputStream.close();
    }
    JDBCTools.release(statement, connection, resultSet);
}
```

## 5. JDBC操作事物

1、关闭 Connection 的自动提交

2、捕获异常，在 catch 中让事务进行回滚

3、如果没有异常，手动提交事务

```java
Connection connection = JDBCTools.getConnection();
//1、关闭 Connection 的自动提交
connection.setAutoCommit(false);
String sql = "update user set money = ? where id = ?";

PreparedStatement statement = connection.prepareStatement(sql);
statement.setInt(1, 500);
statement.setInt(2, 2);
statement.executeUpdate();

try {
    int i = 10/10;
    statement = connection.prepareStatement(sql);
    statement.setInt(1, 1500);
    statement.setInt(2, 8);
    statement.executeUpdate();
    //3、提交事务
    connection.commit();
} catch (Exception e) {
    //2、事务回滚
    connection.rollback();
}
JDBCTools.release(statement, connection, null);
```

```java
package com.southwind.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Test {
    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/mybd2";
        String user = "root";
        String password = "123456";
        Connection connection = DriverManager.getConnection(url, user, password);
        String sql = "update user set money = ? where id = ?";
        //关闭自动提交
        connection.setAutoCommit(false);
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, 500);
            statement.setInt(2, 1);
            statement.executeUpdate();

            int i = 10/10;

            statement = connection.prepareStatement(sql);
            statement.setInt(1, 1500);
            statement.setInt(2, 2);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            connection.rollback();
        } finally {
            statement.close();
            connection.close();
        }

    }
}
```

## 6. 数据库连接池

“池化”思想，字符串常量池、包装类常量池（-128-127）、线程池

提高资源的利用率，JDK 提供了接口 DataSource 实现数据库连接池的，JDK 本身没有实现连接池，只是提供了接口，一般连接池都是第三方实现的，C3P0。

数据库连接池的基本思想就是为数据库建立一个缓冲池，预先向缓冲池中放入一定数量的连接对象 Connection，当需要获取数据库连接的时候，只需要从缓冲池中取出一个对象使用，用完之后还回到缓冲池中，供下一次请求使用。做到资源的重复利用。

C3P0。

1、创建 Maven 工程，pom.xml 导入依赖

```xml
<dependency>
    <groupId>com.mchange</groupId>
    <artifactId>c3p0</artifactId>
    <version>0.9.5.5</version>
</dependency>
```

2、测试

```java
ComboPooledDataSource dataSource = new ComboPooledDataSource();
dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/mydb1");
dataSource.setUser("root");
dataSource.setPassword("123456");
dataSource.setInitialPoolSize(50);
dataSource.setMinPoolSize(2);
dataSource.setMaxPoolSize(100);
dataSource.setAcquireIncrement(10);
Connection connection = dataSource.getConnection();
System.out.println(connection);
```

![image-20200723192429557](C:\Users\userhan\AppData\Roaming\Typora\typora-user-images\image-20200723192429557.png)

3、实际开发中不使用硬编码的方式配置连接池，而是使用配置文件的方式，将数据源相关配置参数写到 XML 中，Java 程序读取 XML 文件。命名格式：c2p0-config.xml

```xml
<c3p0-config>
    <named-config name="mysql">
        <property name="driverClass">com.mysql.cj.jdbc.Driver</property>
        <property name="jdbcUrl">jdbc:mysql://localhost:3306/mydb1</property>
        <property name="user">root</property>
        <property name="password">123456</property>
        <property name="initialPoolSize">50</property>
        <property name="maxPoolSize">100</property>
        <property name="minPoolSize">2</property>
        <property name="acquireIncrement">10</property>
    </named-config>
</c3p0-config>
```

```java
ComboPooledDataSource dataSource = new ComboPooledDataSource("mysql");
Connection connection = dataSource.getConnection();
```

```java
package com.southwind.test;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.*;

public class Test {
    public static void main(String[] args) throws Exception {
        ComboPooledDataSource dataSource = new ComboPooledDataSource("mysql");
        for (int i = 0; i < 300; i++) {
            final int num = i;
            new Thread(()->{
                Connection connection = null;
                PreparedStatement statement = null;
                ResultSet resultSet = null;
                try {
                    connection = dataSource.getConnection();
                    String sql = "select * from student where id = 1";
                    statement = connection.prepareStatement(sql);
                    resultSet = statement.executeQuery();
                    while(resultSet.next()){
                        System.out.println(resultSet.getString(2)+"---"+num);
                    }
                } catch (Exception throwables) {
                    throwables.printStackTrace();
                } finally {
                    try {
                        //关闭，归还
                        connection.close();
                        //关闭
                        statement.close();
                        //关闭
                        resultSet.close();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
```

## 7. 手写DBUtils

无论查哪张表，都可以调用同一个方法，一个方法可以返回任意的数据类型。

```java
package com.southwind.util;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBUtils<T> {

    public List<T> getList(Connection connection,String sql,Class clazz){
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<T> list = new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                //反射
                //获取无参构造函数
                Constructor<T> constructor = clazz.getConstructor(null);
                //创建对象
                T t = constructor.newInstance(null);
                //赋值
                //解析并返回
                ResultSetMetaData metaData = resultSet.getMetaData();
                for (int i = 1; i <= metaData.getColumnCount(); i++) {
                    String columName = metaData.getColumnName(i);
                    String columType = metaData.getColumnTypeName(i);
                    Object columValue = null;
                    switch (columType){
                        case "INT":
                            columValue = resultSet.getInt(columName);
                            break;
                        case "VARCHAR":
                            columValue = resultSet.getString(columName);
                            break;
                    }
                    String methodName = "set"+columName.substring(0,1).toUpperCase()+columName.substring(1);
                    Field field = clazz.getDeclaredField(columName);
                    Method method = clazz.getMethod(methodName, field.getType());
                    method.invoke(t,columValue);
                }
                list.add(t);
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public T getPOJO(Connection connection,String sql,Class clazz){
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        T t = null;
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                //反射
                //获取无参构造函数
                Constructor<T> constructor = clazz.getConstructor(null);
                //创建对象
                t = constructor.newInstance(null);
                //赋值
                //解析并返回
                ResultSetMetaData metaData = resultSet.getMetaData();
                for (int i = 1; i <= metaData.getColumnCount(); i++) {
                    String columName = metaData.getColumnName(i);
                    String columType = metaData.getColumnTypeName(i);
                    Object columValue = null;
                    switch (columType){
                        case "INT":
                            columValue = resultSet.getInt(columName);
                            break;
                        case "VARCHAR":
                            columValue = resultSet.getString(columName);
                            break;
                    }
                    String methodName = "set"+columName.substring(0,1).toUpperCase()+columName.substring(1);
                    Field field = clazz.getDeclaredField(columName);
                    Method method = clazz.getMethod(methodName, field.getType());
                    method.invoke(t,columValue);
                }
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        return t;
    }

}
```

