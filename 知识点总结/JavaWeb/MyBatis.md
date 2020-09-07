# MyBatis

## 1. ORM 方式

ORM：Object Relationship Mapping 对象关系映射

面向对象的编程

```java
public class Orders{
	private Integer id;
	private Double cost;
}

public class User{
	private Integer id;
	private String name;
	private List<Orders> orders;
}

Orders orders = new Orders(1,6000);
Orders orders2 = new Orders(1,3000);

User user = new User(1,"小红");
user.setOrders(Arrays.asList(orders,orders2));
```

关系型数据库，通过主外键约束完成表之间的关联

Java 完成业务逻辑，数据需要持久化到 DB

Java 到数据库的数据存取需要进行转换，将面向对象方式转成面向关系的方式，这种转换就是映射，这就是 ORM。

MyBatis（MyBatis Plus）、Hibernate、Spring Data JPA 是 Java 领域 ORM 方式的主流实现框架。

Hiernate 和 Spring Data JPA （底层就是 Hibernate 实现）是全自动化的 ORM 框架，不需要做任何的操作，就可以获取到结果。

MyBatis 是半自动化的 ORM 框架。

全自动就意味着扩展性，个性化，灵活性较差，半自动化就意味灵活性更好，互联网项目需求迭代较快，MyBatis 很适合互联网项目。

## 2. MyBatis使用

1、创建 Maven 工程

```xml
<dependencies>
    <dependency>
        <groupId>org.mybatis</groupId>
        <artifactId>mybatis</artifactId>
        <version>3.5.5</version>
    </dependency>

    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.21</version>
    </dependency>
    
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <version>1.18.12</version>
    </dependency>
</dependencies>
```

lombok 插件，可以帮助我们生成实体类中的 getter、setter、toString、构造...

IDEA 中使用 Lombok 需要安装插件

![image-20200723203810869](C:\Users\ningn\AppData\Roaming\Typora\typora-user-images\image-20200723203810869.png)

2、创建实体类

```java
package com.southwind.entity;

import lombok.Data;

@Data
public class Account {
    private Integer id;
    private String username;
    private String password;
    private Integer age;
}
```

3、创建配置文件，名字可以自定义。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <settings>
        <!-- 打开SQL语句打印 -->
        <setting name="logImpl" value="STDOUT_LOGGING" />
        <!-- 打开懒加载 -->
        <setting name="lazyLoadingEnabled" value="true"/>
        <!-- 打开二级缓存 -->
        <setting name="cacheEnabled" value="true"/>
    </settings>

    <!-- 将包名提出，不用每次写类型的时候都写包名 -->
    <typeAliases>
        <package name="com.study.entity"/>
    </typeAliases>

    <!-- 配置数据源 -->
    <environments default="development">
        <environment id="development">
            <!-- 事务管理，MyBatis 底层基于 JDBC 进行事务管理 -->
            <transactionManager type="JDBC"></transactionManager>
            <!-- 数据源，可以直接使用 MySQL 为数据源，也可以使用数据库连接池 POOLED 作为数据源-->
            <dataSource type="POOLED">
                <property name="driver" value="com.mysql.cj.jdbc.Driver"/>
                <property name="url" value="jdbc:mysql://localhost:3306/study"/>
                <property name="username" value="UserHan"/>
                <property name="password" value="root"/>
            </dataSource>
        </environment>
    </environments>


    <!-- 注册mapper代理的XML文件 -->
    <mappers>
        <mapper resource="com/study/mapper/StudentMapper.xml"></mapper>
        <mapper resource="com/study/mapper/SCMapper.xml"></mapper>
        <mapper resource="com/study/mapper/CourseMapper.xml"></mapper>
    </mappers>
</configuration>
```

4、开发，Mapper 代理实现自定义接口

使用 MyBatis 框架，开发者只需要定义接口即可，不需要自己实现，由 MyBatis 通过 Mapper 代理机制自动帮助我们创建接口的实现类，并且创建接口的实例化对象。

```java
package com.southwind.mapper;

import com.southwind.entity.Account;

import java.util.List;

public interface AccountMapper {
    public List<Account> findAll();
    public Account findById(Integer id);
    public void save(Account account);
    public void update(Account account);
    public void deleteById(Integer id);
}
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.southwind.mapper.AccountMapper">
    <!-- statement -->
    <select id="findAll" resultType="com.southwind.entity.Account">
        select * from account
    </select>

    <select id="findById" parameterType="java.lang.Integer" resultType="com.southwind.entity.Account">
        select * from account where id = #{id}
    </select>

    <insert id="save" parameterType="com.southwind.entity.Account">
        insert into account(username,password,age) values(#{username},#{password},#{age})
    </insert>

    <update id="update" parameterType="com.southwind.entity.Account">
        update account set username = #{username},password = #{password},age = #{age} where id = #{id}
    </update>

    <delete id="deleteById" parameterType="java.lang.Integer">
        delete from account where id = #{id}
    </delete>
</mapper>
```

5、在 config.xml 中注册 Mapper.xml

```xml
<mappers>
    <mapper resource="com/southwind/mapper/AccountMapper.xml"></mapper>
</mappers>
```

6、使用

```java
package com.southwind.test;

import com.southwind.entity.Account;
import com.southwind.mapper.AccountMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class Test {
    public static void main(String[] args) {
        //加载 MyBatis 配置文件
        InputStream inputStream = Test.class.getClassLoader().getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //操作API
        AccountMapper mapper = sqlSession.getMapper(AccountMapper.class);
        mapper.findAll().forEach(System.out::println);
    }
}
```

## 3. 常见错误

- Mapper.xml 找不到，pom.xml 配置让 Maven 可以读取 java 路径下 XML 文件。

```xml
<build>
    <resources>
        <resource>
            <directory>src/main/java</directory>
            <includes>
                <include>**/*.xml</include>
            </includes>
        </resource>
    </resources>
</build>
```

- Mapper.xml 中的 namespace 必须和接口的全类名保持一致。

- Mapper.xml 中的 statement 的 id 值必须和接口方法一致。
- Mapper.xml 必须在 mybatis-config.xml 中进行注册。

## 4. 打印 SQL

在 xml 文件中配置

```xml
<settings>
    <setting name="logImpl" value="STDOUT_LOGGING" />
</settings>
```

## 5. Mapper.xml 常见配置

mapper 是配置文件的根节点，namespace 需要和对应的接口进行关联。因为 MyBatis 会自动读取所有的接口和对应的 Mapper.xml，来动态创建实现类，进而实例化对象，这就是 Mapper 代理机制。

statement 标签一共有 4 种：insert、update、select、delete

id 对应接口的方法名

parameterType 设置方法参数的数据类型映射，支持基本数据类型、包装类、String、多个参数、POJO（对象）。

resultType 设置方法返回值的数据类型映射，支持基本数据类型、包装类、String、POJO（对象）

## 6. 多表关联查询

### 6.1 一对多

**一条查询结果集中包含多个集合**

```java
//多表联合查询，一条SQL语句
public List<Student> findById2(Integer id);
```

```xml
    <resultMap id="studentMap" type="Student">
        <id property="id" column="sid"></id>
        <result property="name" column="sname"></result>
        <result property="age" column="sage"></result>
        <collection property="scs" ofType="SC">
            <id property="id" column="cid"></id>
            <result property="num" column="cnum"></result>
        </collection>
    </resultMap>
    <select id="findById2" parameterType="java.lang.Integer" resultMap="studentMap">
        select student.id sid,student.name sname,student.age sage,sc.id cid,sc.num cnum
        from student,sc
        where student.id = sc.student_id and student.id = 1;
    </select>
```

**一条查询结果集中包含一个对象**

```java
public SC findById(Integer id);
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.study.mapper.SCMapper">

    <resultMap id="SCMap" type="SC">
        <id property="id" column="cid"></id>
        <result property="num" column="cnum"></result>
        <association property="student" javaType="Student">
            <id property="id" column="sid"></id>
            <result property="name" column="sname"></result>
            <result property="age" column="sage"></result>
        </association>
    </resultMap>

    <select id="findById" parameterType="java.lang.Integer" resultMap="SCMap">
        select sc.id cid,sc.num cnum,student.id sid,student.name sname,student.age sage
        from student,sc
        where student.id = sc.student_id and sc.id = #{id};
    </select>
</mapper>
```

**将SQL语句进行拆分，解耦合以及方便进行延迟加载**

```java
public SC findById2(Integer id);
```

```xml
    <resultMap id="SCMap2" type="SC">
        <id property="id" column="id"></id>
        <result property="num" column="num"></result>
        <association property="student" column="student_id" javaType="Student" select="com.study.mapper.StudentMapper.findById"></association>
    </resultMap>

    <select id="findById2" parameterType="java.lang.Integer" resultMap="SCMap2">
        select * from SC where id = #{id};
    </select>
```

```java
    public Student findById(Integer id);
```

```xml
    <select id="findById" parameterType="java.lang.Integer" resultType="com.study.entity.Student">
        select * from student where id = #{id};
    </select>
```

### 6.2 多对多

```java
public Course findById(Integer id);
```

```xml
    <resultMap id="courseMap" type="Course">
        <id property="id" column="cid"></id>
        <result property="name" column="cname"></result>
        <collection property="students" ofType="Student">
            <id property="id" column="sid"></id>
            <result property="name" column="sname"></result>
        </collection>
    </resultMap>

    <select id="findById" parameterType="java.lang.Integer" resultMap="courseMap">
        select course.id cid,course.name cname,student.id sid,student.name sname
        from student,sc,course
        where student.id = sc.student_id and sc.course_id = course.id and course.id = #{id};
    </select>
```

```java
public Student findById3(Integer id);
```

```xml
    <resultMap id="studentMap2" type="Student">
        <id property="id" column="sid"></id>
        <result property="name" column="sname"></result>
        <collection property="courses" ofType="Course">
            <id property="id" column="cid"></id>
            <result property="name" column="cname"></result>
        </collection>
    </resultMap>

    <select id="findById3" parameterType="java.lang.Integer" resultMap="studentMap2">
        select student.id sid,student.name sname,course.id cid,course.name cname
        from student,sc,course
        where student.id = sc.student_id and sc.course_id = course.id and student.id = #{id};
    </select>
```

### 6.3 多参数查询

```java
public Student findByIdAndName(Integer id,String name);
```

```xml
<select id="findByIdAndName" resultType="Student">
    select * from student where id = #{arg0} and name = #{arg1};
</select>
```

## 7. MyBatis 延迟加载

延迟加载或者叫懒加载、惰性加载、按需加载

提高程序运行效率的一种优化方案，通过延迟加载可以减少 Java 程序与数据库的交互次数。

查询 Orders，获取 Orders 的 Id，此时只查询 Orders 完全可以满足需求，无需查询 Account。

当获取 Orders 级联的 Account 对象，再去查询 Account 即可。

如何开启延迟加载？

1、SQL 语句的分离，不要写一次 SQL 实现两张表的关联查询，这样是无法进行延迟。

2、config.xml 开启延迟加载配置即可。

```xml
<settings>
    <!-- 开启延迟加载 -->
    <setting name="lazyLoadingEnabled" value="true"/>
</settings>
```

## 8. MyBatis 缓存

![image-20200801173126307](C:\Users\userhan\AppData\Roaming\Typora\typora-user-images\image-20200801173126307.png)

通过 MyBatis 缓存，可以有效减少 Java 程序与数据库的交互次数。

MyBatis 提供了两种缓存机制：一级缓存和二级缓存

### 8.1 MyBaits 一级缓存

MyBatis 自带一级缓存，天生就有，无法关闭。一级缓存的数据存储在 SqlSession 中，它的作用域是同一个 SqlSession，当我们使用同一个 SqlSession 对象执行查询的时候，第一次的查询结果会自动存入 SqlSession 中，第二次直接从 SqlSession 中获取即可。

```java
package com.southwind.cache;

import com.southwind.entity.Account;
import com.southwind.mapper.AccountMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class Test {
    public static void main(String[] args) {
        //加载 MyBatis 配置文件
        InputStream inputStream = com.southwind.test.Test.class.getClassLoader().getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取AccountMapper代理对象
        AccountMapper mapper = sqlSession.getMapper(AccountMapper.class);
        Account account = mapper.findById(1);
        System.out.println(account);
        //释放sqlSession
        sqlSession.close();
        SqlSession newSqlSession = sqlSessionFactory.openSession();
        AccountMapper newMapper = newSqlSession.getMapper(AccountMapper.class);
        Account account1 = newMapper.findById(1);
        System.out.println(account1);
    }
}
```

### 8.2 MyBatis 二级缓存

MyBatis 的二级缓存是作用域范围比一级缓存更大的一种缓存机制，Mapper 级别，只要是来自同一个 Mapper.xml/Mapper 接口的代理对象，无论是否通过同一个 SqlSession 创建，都可以共用缓存。

MyBatis 的二级缓存默认关闭，需要手动开启。

1、mybatis-config.xml 中配置开启二级缓存

```xml
<settings>
    <!-- 开启二级缓存 -->
    <setting name="cacheEnabled" value="true"/>
</settings>
```

2、Mapper.xml 中配置二级缓存

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.southwind.mapper.AccountMapper">
    <cache></cache>
</mapper>
```

3、实体类实现序列化接口

```java
package com.southwind.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class Account implements Serializable {
    private Integer id;
    private String username;
    private String password;
    private Integer age;
    private List<Orders> orders;

    public Account(String username, String password, Integer age) {
        this.username = username;
        this.password = password;
        this.age = age;
    }
}
```

![image-20200801173159085](C:\Users\userhan\AppData\Roaming\Typora\typora-user-images\image-20200801173159085.png)

实体类实现序列化接口之后，对象可以存入硬盘，“钝化”

程序重启时，从硬盘中读取数据还原成 JVM 中的对象，“活化”

## 9. MyBatis 动态SQL

```java
package com.southwind.entity;

public class User {
    private Integer id;
    private String username;
    private String password;
    private Integer age;
}
```

- 通过 id 和 username 查询 User
- 通过 username 和 password 查询 User
- 通过 password 和 age 查询 User

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.southwind.mapper.UserMapper">

    <select id="findByUser1" parameterType="User">
        select * from user where id = #{id} and username = #{username}
    </select>

    <select id="findByUser2" parameterType="User">
        select * from user where username = #{username} and password = #{password}
    </select>

    <select id="findByUser3" parameterType="User">
        select * from user where password = #{password} and age = #{age}
    </select>

</mapper>
```

SQL 的结构不是一成不变的，而是根据具体的需求和情况来动态生成的，即动态 SQL，可以提高程序的灵活性，扩展性。

```java
package com.southwind.dynimic;

import com.southwind.entity.Account;
import com.southwind.mapper.AccountMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class Test {
    public static void main(String[] args) {
        //加载 MyBatis 配置文件
        InputStream inputStream = com.southwind.test.Test.class.getClassLoader().getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        AccountMapper mapper = sqlSession.getMapper(AccountMapper.class);
        //条件
        Account account = new Account();
        account.setId(1);
        account.setUsername("zs");
        account.setPassword("123123");
        account.setAge(12);
        Account byAccount = mapper.findByAccount(account);
        System.out.println(byAccount);
    }
}
```



```xml
<select id="findByAccount" parameterType="Account" resultType="Account">
    select * from account
    <!-- 当 where 和 and 连在一起的时候自动删除 and -->
    <where>
        <if test="id!=null">
            id = #{id}
        </if>
        <if test="username!=null">
            and username = #{username}
        </if>
        <if test="password != null">
            and password = #{password}
        </if>
        <if test="age != null">
            and age = #{age}
        </if>
    </where>
</select>
```

if 标签用来处理流程控制，根据业务需求来动态判断是否要添加某个条件表达式，where 是结合多条件判断语句使用的，动态来处理 where 和 and 的关系：where 和 and 直接连接的时候，自动删除掉 and。

choose、when 标签

跟 if 标签的作用一致，但是区别在于，当第一个 when 成立的时候，后面的 when 都不再进行判断，而是直接忽略。

```xml
<select id="findByAccount" parameterType="Account" resultType="Account">
    select * from account
    <where>
        <choose>
            <when test="id != null">
                id = #{id}
            </when>
            <when test="username != null">
                and username = #{username}
            </when>
            <when test="password != null">
                and password = #{password}
            </when>
            <when test="age != null">
                and age = #{age}
            </when>
        </choose>
    </where>
</select>
```

set 标签

set 标签作用于 update 操作，会自动根据参数选择生成修改的 SQL 语句。

```xml
<update id="update" parameterType="Account">
    update account
    <set>
        <if test="username != null">
            username = #{username},
        </if>
        <if test="password != null">
            password = #{password},
        </if>
        <if test="age != null">
            age = #{age}
        </if>
    </set>
    where id = #{id}
</update>
```

foreach 标签

foreach 标签可以迭代生成一系列值，主要用于 in 查询。

```xml
<select id="findByAccount" parameterType="Account" resultType="Account">
    select * from account
    <where>
        <foreach collection="ids" open="id in(" close=")" item="id" separator=",">
            #{id}
        </foreach>
    </where>
</select>
```

MyBatis 及其他框架、工具、组件的使用，一定需要先构建一定数量的对象，才能使用，这种方式效率较低，尤其是企业级开发，如何优化？

如果有这么一种技术，可以自动帮助我们生成各个组件要使用的对象，那么开发者只需要使用即可，大大提升开发效率。

Spring Boot 

Spring 大框架的一个模块 Spring 全家桶：一系列企业级开发框架。