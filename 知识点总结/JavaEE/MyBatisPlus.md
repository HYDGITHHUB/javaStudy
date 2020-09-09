# MyBatisPlus

MyBatis 是一般自动化框架，MyBatis Plus 是一个升级版的 MyBatis 全自动化框架。

MyBatis Plus 是国产的基于 MyBatis 的开源框架，把 MyBatis 框架进行了进一步的封装，自动生成 SQL、自动生成整个应用的各个模块，Controller、Service、Mapper、Entity。

## 1. 使用流程

1、创建 Spring Boot 应用，pom.xml 导入 MP 的依赖。

```xml
<dependencies>

    <dependency>
        <groupId>com.baomidou</groupId>
        <artifactId>mybatis-plus-boot-starter</artifactId>
        <version>3.3.2</version>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter</artifactId>
    </dependency>

    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <scope>runtime</scope>
    </dependency>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
        <exclusions>
            <exclusion>
                <groupId>org.junit.vintage</groupId>
                <artifactId>junit-vintage-engine</artifactId>
            </exclusion>
        </exclusions>
    </dependency>
</dependencies>
```

2、创建实体类

```java
package com.southwind.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Account {
    private Integer id;
    private String username;
    private String password;
    private Integer age;
    private Date createtime;
    private Date updatetime;
}
```

3、创建 Mapper 接口

```java
package com.southwind.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.southwind.entity.Account;

public interface AccountMapper extends BaseMapper<Account> {
}
```

4、启动类配置扫包

```java
package com.southwind;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.southwind.mapper")
public class Mp001Application {

    public static void main(String[] args) {
        SpringApplication.run(Mp001Application.class, args);
    }

}
```

5、application.yml

```yml
spring:
  datasource:
    username: root
    password: 123456
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/mybd2
```

6、测试

```java
package com.southwind.mapper;

import com.southwind.entity.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountMapperTest {

    @Autowired
    private AccountMapper accountMapper;

    @Test
    void selectList(){
        List<Account> list = accountMapper.selectList(null);
        for (Account account : list) {
            System.out.println(account);
        }
    }

    @Test
    void selectById(){
        System.out.println(accountMapper.selectById(1));
    }

    @Test
    void insert(){
        Account account = new Account();
        account.setUsername("mp");
        account.setPassword("mp");
        account.setCreatetime(new Date());
        account.setUpdatetime(new Date());
        System.out.println(accountMapper.insert(account));
    }

    @Test
    void update(){
        Account account = accountMapper.selectById(1293908865968148481L);
        account.setUsername("张三");
        System.out.println(accountMapper.updateById(account));
    }

    @Test
    void deleteById(){
        System.out.println(accountMapper.deleteById(1293908865968148481L));
    }
}
```

## 2. 常用注解

> @TableName

@TableName 设置实体类名与表名的映射，如果不添加该注解，默认用类名作为表名进行映射

```java
package com.southwind.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "account")
public class Person {
    private Long id;
    private String username;
    private String password;
    private Integer age;
    private Date createtime;
    private Date updatetime;
}
```

> @TableId

@TableId 设置主键，value 映射主键字段名，如果不填，默认找 id

type 为主键类型，可选择的值为一组枚举类型，默认值为 NONE。

```java
AUTO(0),
NONE(1),
INPUT(2),
ASSIGN_ID(3),
ASSIGN_UUID(4),
/** @deprecated */
@Deprecated
ID_WORKER(3),
/** @deprecated */
@Deprecated
ID_WORKER_STR(3),
/** @deprecated */
@Deprecated
UUID(4);
```

@Deprecated 注解用来标注已经过期的 API，不推荐使用，在后期的 JDK 版本中可能被移除。

1、NONE 通过雪花算法生成一个随机数

2、INPUT，需要手动给主键，如果不赋值，主键的值为 null，数据库会自动赋值

3、AUTO，数据库自增

4、ASSIGN_ID 通过雪花算法生成随机数，主键类型必须时 Long 或者 Integer、String

5、ASSIGN_UUID 主键类型必须是 String，生成 UUID

> @TableField

设置非主键字段。

value 映射字段名。

exist 表示是否为数据库字段，默认是 true，如果设置为 false，表示查询数据库的时候不会带着该字段。

select 表示是否查询该字段，默认为 true，如果设置为 false，表示不查询该字段。

fill 表示是否自动添加，数据库自动赋值。

```java
package com.southwind.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "account")
public class Person {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer num;
    @TableField(value = "username")
    private String name;
    private String password;
    @TableField(select = false)
    private Integer age;
    @TableField(exist = false)
    private Double score;
    @TableField(fill = FieldFill.INSERT)
    private Date createtime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updatetime;
}
```

```java
package com.southwind.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MyMateDataHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createtime", new Date(), metaObject);
        this.setFieldValByName("updatetime", new Date(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updatetime", new Date(), metaObject);
    }
}
```

> @EnumValue

标注枚举类型的成员变量，两种方式：直接加注解，实现接口

直接加注释

```java
package com.southwind.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

public enum StatusEnum {
    WORK(1,"上班"),
    REST(2,"休息");

    StatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @EnumValue
    private Integer code;
    private String msg;
}
```

```java
package com.southwind.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.southwind.enums.StatusEnum;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "account")
public class Person {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer num;
    @TableField(value = "username")
    private String name;
    private String password;
    @TableField(select = false)
    private Integer age;
    @TableField(exist = false)
    private Double score;
    @TableField(fill = FieldFill.INSERT)
    private Date createtime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updatetime;
    private StatusEnum status;
}
```

```yml
spring:
  datasource:
    username: root
    password: 123456
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/mybd2
mybatis-plus:
  configuration:
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
  type-enums-package: com.southwind.enums
```

实现接口

```java
package com.southwind.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;

public enum AgeEnum implements IEnum<Integer> {
    ONE(1,"一岁"),
    TWO(2,"两岁"),
    THREE(3,"三岁");

    AgeEnum(Integer value, String msg) {
        this.value = value;
        this.msg = msg;
    }

    private Integer value;
    private String msg;

    @Override
    public Integer getValue() {
        return this.value;
    }
}
```

```java
package com.southwind.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.southwind.enums.AgeEnum;
import com.southwind.enums.StatusEnum;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "account")
public class Person {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer num;
    @TableField(value = "username")
    private String name;
    private String password;
    private AgeEnum age;
    @TableField(exist = false)
    private Double score;
    @TableField(fill = FieldFill.INSERT)
    private Date createtime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updatetime;
    private StatusEnum status;
}
```

## 3. CRUD

### 3.1 查询

> selectList(null) 查询全部数据

```java
List<Person> list = accountMapper.selectList(null);
for (Person person : list) {
    System.out.println(person);
}
```

> selectBatchIds(Collection) 通过 id 批量查询

```java
List<Person> list = accountMapper.selectBatchIds(Arrays.asList(1,2,3,4,5,6,7,8));
```

> selectById() 通过 id 查询

```java
accountMapper.selectById(1)
```

> selectByMap(Map<String,Object>) 条件查询，条件放入 Map 集合中

```java
Map<String,Object> map = new HashMap<>();
map.put("age", 3);
map.put("username", "mp38");
List<Person> list = accountMapper.selectByMap(map);
```

> selectCount(Wrapper) 查询符合条件的记录数

```java
QueryWrapper wrapper = new QueryWrapper();
wrapper.eq("age", 3);
Integer count = accountMapper.selectCount(wrapper);
```

> selectList(Wrapper) 条件查询

```java
QueryWrapper wrapper = new QueryWrapper();
wrapper.gt("age", 3);
List<Person> list = accountMapper.selectList(wrapper);
for (Person person : list) {
    System.out.println(person);
}
```

| 方法名      | 描述     |
| ----------- | -------- |
| eq          | 等值判断 |
| ne          | 不等判断 |
| gt          | 大于判断 |
| lt          | 小于判断 |
| between     | 区间判断 |
| like        | 模糊查询 |
| orderByAsc  | 升序     |
| orderByDesc | 降序排列 |

### 3.2 删除

| 方法名         | 描述                     |
| -------------- | ------------------------ |
| deleteById     | 通过 id 删除             |
| delete         | 条件删除                 |
| deleteBatchIds | 根据 id 批量删除         |
| deleteByMap    | 条件删除，只能做等值判断 |

### 3.3 修改

| 方法名     | 描述                  |
| ---------- | --------------------- |
| updateById | 通过 id 进行修改      |
| update     | 通过 Wrapper 进行修改 |

### 3.4 添加

```java
Person person = new Person();
person.setName("abc");
person.setPassword("123");
accountMapper.insert(person);
```

## 4. 代码生成器

MP 可以根据数据表自动生成对应的实体类、Controller、Service、ServiceImpl、Mapper。

1、创建 Spring Boot 应用，pom.xml

```xml
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-boot-starter</artifactId>
    <version>3.3.2</version>
</dependency>

<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-generator</artifactId>
    <version>3.3.2</version>
</dependency>

<dependency>
    <groupId>org.apache.velocity</groupId>
    <artifactId>velocity</artifactId>
    <version>1.7</version>
</dependency>
```

2、启动类

```java
package com.southwind;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        AutoGenerator generator = new AutoGenerator();
        //数据源
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL);
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("123456");
        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/mybd2");
        generator.setDataSource(dataSourceConfig);
        //全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOutputDir(System.getProperty("user.dir")+"/src/main/java");
        globalConfig.setAuthor("张三");
        globalConfig.setOpen(false);
        globalConfig.setServiceName("%sService");
        generator.setGlobalConfig(globalConfig);
        //包信息
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent("com.southwind");
        packageConfig.setEntity("entity");
        packageConfig.setMapper("mapper");
        packageConfig.setService("service");
        packageConfig.setServiceImpl("service.impl");
        packageConfig.setController("controller");
        generator.setPackageInfo(packageConfig);
        //策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setInclude("account");
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setEntityLombokModel(true);
        TableFill tableFill1 = new TableFill("createTime", FieldFill.INSERT);
        TableFill tableFill2 = new TableFill("updateTime", FieldFill.INSERT_UPDATE);
        List<TableFill> list = new ArrayList<>();
        list.add(tableFill1);
        list.add(tableFill2);
        strategyConfig.setTableFillList(list);
        generator.setStrategy(strategyConfig);
        generator.execute();
    }
}
```

