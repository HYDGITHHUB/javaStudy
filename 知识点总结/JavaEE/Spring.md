# Spring

Java 领域第一框架，是行业标准和规范。

Java EE 基于 Java 的企业级解决方案，Java WEB 开发就是 Java EE 的一部分。

Java 初期使用 EJB 开发，但是这种方式非常繁重，不灵活，不便于维护和升级。

Spring IoC 的雏形，读取配置文件的方式，通过反射机制自动创建开发中需要使用的组件对象，并且完成依赖注入，对象和对象之间的联系。

2002 正式发布 Spring 1.0 版本，发展至今，将近 20 年的发展，Spring 框架已经从最初取代 EJB 构建企业级开发的方式，发展成一套自有的生态体系。

SSH：Structs2（实现 MVC，客户端和服务器交互）、Spring（构建项目）、Hibernate（持久层）

![image-20200803105855397](C:\Users\userhan\AppData\Roaming\Typora\typora-user-images\image-20200803105855397.png)

SSM：Spring MVC、Spring、MyBatis

![image-20200803105903983](C:\Users\userhan\AppData\Roaming\Typora\typora-user-images\image-20200803105903983.png)

Spring 已经发展成了一套生态体系，常规意义上的 Spring 框架是指 Spring Framework，Spring Framework 是整套 Spring 全家桶所有产品的基石，Spring 全家桶的所有组件产品都是基于 Spring Framework，IoC 容器。

Spring 主要有两大核心组件：

- IoC 控制反转 
- AOP 面向切面编程

## 1. Spring Ioc

控制反转具体反转的是 Java 程序中对象的创建方式，传统开发方式，对象都是开发者需要的时候手动 new 出来的。

控制反转是对象不需要开发者手动 new，而是交给 IoC 容器来创建，开发者直接使用即可。

1、创建 Java 工程，导入 Spring 的依赖（jar）

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-context</artifactId>
        <version>5.2.8.RELEASE</version>
    </dependency>
</dependencies>
```

2、创建 Student 类

```java
package com.southwind.entity;

import lombok.Data;

@Data
public class Student {
    private Integer id;
    private String name;
    private Integer age;
}
```

3、传统方式

```java
package com.southwind.test;

import com.southwind.entity.Student;

public class Test {
    public static void main(String[] args) {
        Student student = new Student();
        student.setId(1);
        student.setName("张三");
        student.setAge(22);
    }
}
```

IoC 实现

不需要你创建，只需要在 XML 中配置你需要的对象即可。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">

    <bean id="stu" class="com.southwind.entity.Student">
        <property name="id" value="1"></property>
        <property name="name" value="李四"></property>
        <property name="age" value="22"></property>
    </bean>

</beans>
```

通过 id 取值

```java
package com.southwind.ioc;

import com.southwind.entity.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
        //加载IoC容器
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        Student stu = (Student) applicationContext.getBean("stu");
        System.out.println(stu);
    }
}
```

通过实体类的 Class 对象取值

```java
package com.southwind.ioc;

import com.southwind.entity.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
        //加载IoC容器
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        System.out.println(applicationContext.getBean(Student.class));
    }
}
```

这种方式存在一个问题，如果 IoC 中同时存在两个数据类型相同的 bean 时，就会抛出不是唯一 bean 的异常，使用 Class 获取对象，必须保证 IoC 中该数据类型只有唯一的一个 bean。

### 1.1 Spring IoC 的使用流程

1、将程序中需要用到的对象在 spring.xml 中进行配置。

2、读取 spring.xml 生成 ApplicationContext 对象（IoC 容器）。

3、从 ApplicationContext 对象中获取需要的 bean

- 通过 id 获取（必须保证 IoC 中的每个 bean 都是不同的 id）
- 通过 Class 类型获取（必须保证该类的 bean 只有一个）

IoC 创建 bean 的方式

1、通过无参构造创建对象，通过 setter 方法完成属性的赋值。（类中必须要有相应的方法才可以赋值否则会报错	）

```xml
<bean id="stu" class="com.southwind.entity.Student">
    <property name="id" value="1"></property>
    <property name="name" value="李四"></property>
    <property name="age" value="22"></property>
</bean>
```

2、通过有参构造创建对象，同时完成赋值。

```xml
<bean id="stu2" class="com.southwind.entity.Student">
    <constructor-arg name="id" value="2"></constructor-arg>
    <constructor-arg name="name" value="李四"></constructor-arg>
    <constructor-arg name="age" value="23"></constructor-arg>
</bean>
```

```xml
<bean id="stu2" class="com.southwind.entity.Student">
    <constructor-arg index="1" value="李四"></constructor-arg>
    <constructor-arg index="0" value="2"></constructor-arg>
    <constructor-arg index="2" value="23"></constructor-arg>
</bean>
```

bean 之间的依赖注入（DI），IoC 包含 DI，IoC 可以自动完成 DI 的操作，把一个 bean 注入到另外一个 bean。

传统方式

```java
package com.southwind.test;

import com.southwind.entity.Class;
import com.southwind.entity.Student;

public class Test {
    public static void main(String[] args) {
        Student student = new Student();
        student.setId(1);
        student.setName("张三");
        student.setAge(22);
        Class clazz = new Class();
        clazz.setId(101);
        clazz.setName("一班");
        //依赖注入 DI
        student.setClazz(clazz);
    }
}
```

IoC

```xml
<!-- 通过无参创建对象 -->
<bean id="stu" class="com.southwind.entity.Student">
    <property name="id" value="1"></property>
    <property name="name" value="李四"></property>
    <property name="age" value="22"></property>
    <property name="clazz" ref="clazz"></property>
</bean>

<!-- Class对象 -->
<bean id="clazz" class="com.southwind.entity.Class">
    <property name="id" value="101"></property>
    <property name="name" value="一班"></property>
</bean>
```

两个 bean 之间的 DI，一定要使用 ref 完成注入。

传统方式

```java
package com.southwind.test;

import com.southwind.entity.Class;
import com.southwind.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class Test2 {
    public static void main(String[] args) {
        Student stu1 = new Student();
        stu1.setId(1);
        stu1.setName("张三");
        stu1.setAge(22);
        Student stu2 = new Student();
        stu2.setId(2);
        stu2.setName("李四");
        stu2.setAge(23);
        Class clazz = new Class();
        clazz.setId(101);
        clazz.setName("一班");
        List<Student> list = new ArrayList<>();
        list.add(stu1);
        list.add(stu2);
        clazz.setStudents(list);
        System.out.println(clazz);
    }
}
```

IoC

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">

    <bean id="stu1" class="com.southwind.entity.Student">
        <property name="id" value="1"></property>
        <property name="name" value="张三"></property>
        <property name="age" value="22"></property>
    </bean>

    <bean id="stu2" class="com.southwind.entity.Student">
        <property name="id" value="2"></property>
        <property name="name" value="李四"></property>
        <property name="age" value="23"></property>
    </bean>

    <bean id="clazz" class="com.southwind.entity.Class">
        <property name="id" value="101"></property>
        <property name="name" value="一班"></property>
        <property name="students">
            <list>
                <ref bean="stu1"></ref>
                <ref bean="stu2"></ref>
            </list>
        </property>
    </bean>

</beans>
```

### 1.2 Spring IoC 基于注解的开发

Controller（只负责接收客户端请求以及做出响应） ---》    Service（只负责业务逻辑的编写）  ---》     Mapper（只负责跟数据库的交互）

使用 Spring IoC 有两种方式：IoC 的核心就是创建 bean

- 基于 XML：在 spring.xml 中配置各种 bean

```xml
<bean id="controller" class="com.southwind.demoxml.Controller">
    <property name="service" ref="service"></property>
</bean>

<bean id="service" class="com.southwind.demoxml.Service">
    <property name="mapper" ref="mapper"></property>
</bean>

<bean id="mapper" class="com.southwind.demoxml.Mapper"></bean>
```

```xml
<bean id="controller" class="com.southwind.demoxml.Controller" autowire="byType">
</bean>

<bean id="service" class="com.southwind.demoxml.Service" autowire="byType">
</bean>

<bean id="mapper" class="com.southwind.demoxml.Mapper"></bean>
```

```java
package com.southwind.demoxml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-xml.xml");
        Controller controller = applicationContext.getBean(Controller.class);
        controller.handler();
    }
}
```

- 基于注解：不需要在 spring.xml 中配置各种 bean，而是在类处添加注解

1、自动扫包，将需要注入到 IoC 中的类设置扫描，让 IoC 容器可以扫描到这些类。

2、在需要注入的类定义处添加注解。

以上两个步骤缺一不可

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">

    <!-- 配置自动扫包 -->
    <context:component-scan base-package="com.southwind.demoannotation"></context:component-scan>

</beans>
```

```java
package com.southwind.demoannotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MyController {

    @Autowired
    private MyService myService;

    public void setMyService(MyService myService) {
        this.myService = myService;
    }

    /**
     * 接收客户端请求
     */
    public void handler(){
        System.out.println("进入了控制层");
        this.myService.service();
    }
}
```

```java
package com.southwind.demoannotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    @Autowired
    private Mapper mapper;

    public void setMapper(Mapper mapper) {
        this.mapper = mapper;
    }

    /**
     * 处理业务逻辑
     */
    public void service(){
        System.out.println("进入了业务层");
        this.mapper.crud();
    }
}
```

```java
package com.southwind.demoannotation;

import org.springframework.stereotype.Repository;

@Repository
public class Mapper {

    /**
     * 连接数据库
     */
    public void crud(){
        System.out.println("进入了Mapper");
    }
}
```

@Autowire 是用来完成 bean 的自动装载，byType、byName

@Autowire 是 byType 的方式

如果要修改为 byName 的方式，需要追加 @Qualifier 

```java
@Service(value = "abc")
public class MyService {

<bean id="abc" class="com.southwind.MyService"></bean>

@Service
public class MyService {

<bean id="myService" class="com.southwind.MyService"></bean>
```

```java
package com.southwind.demoannotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class MyController {

    @Autowired
    @Qualifier("abc")
    private MyService myService;

    public void setMyService(MyService myService) {
        this.myService = myService;
    }

    /**
     * 接收客户端请求
     */
    public void handler(){
        System.out.println("进入了控制层");
        this.myService.service();
    }
}
```

```java
package com.southwind.demoannotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service(value = "abc")
public class MyService {

    @Autowired
    @Qualifier("def")
    private Mapper mapper;

    public void setMapper(Mapper mapper) {
        this.mapper = mapper;
    }

    /**
     * 处理业务逻辑
     */
    public void service(){
        System.out.println("进入了业务层");
        this.mapper.crud();
    }
}
```

```java
package com.southwind.demoannotation;

import org.springframework.stereotype.Repository;

@Repository(value = "def")
public class Mapper {

    /**
     * 连接数据库
     */
    public void crud(){
        System.out.println("进入了Mapper");
    }
}
```

## 2. Spring bean 的特性

### 2.1 bean 的生命周期

通过 scope 属性来设置 bean 的生命周期

```java
package com.southwind.test;

import com.southwind.entity.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test3 {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring3.xml");
        Student stu1 = (Student) applicationContext.getBean("stu1");
        Student stu2 = (Student) applicationContext.getBean("stu1");
        System.out.println(stu1 == stu2);
    }
}
```

- singleton，单例，表示通过 Spring 容器获取的 bean 是唯一的，默认

IoC 容器一旦加载，就会自动创建对象，并且只有一份，无论是否获取。

```xml
<bean id="stu1" class="com.southwind.entity.Student" scope="singleton">
    <property name="id" value="1"></property>
    <property name="name" value="张三"></property>
    <property name="age" value="22"></property>
</bean>
```

![image-20200804083741774](C:\Users\userhan\AppData\Roaming\Typora\typora-user-images\image-20200804083741774.png)

- prototype，原型，表示通过 Spring 容器获取的 bean 是不同的

如果只加载 IoC 容器，但是不取值则不会创建对象，取一次则创建一个新的对象。

```xml
<bean id="stu1" class="com.southwind.entity.Student" scope="prototype">
    <property name="id" value="1"></property>
    <property name="name" value="张三"></property>
    <property name="age" value="22"></property>
</bean>
```

![image-20200804083751571](C:\Users\userhan\AppData\Roaming\Typora\typora-user-images\image-20200804083751571.png)

### 2.2 Spring 继承

与 Java 的继承不同，Java 继承是在描述类之间的关系，子类可以直接继承父类的结构。

Spring 的继承是在 bean 层面来处理的，子 bean 可以继承父 bean 的所有属性值。

```xml
<bean id="stu1" class="com.southwind.entity.Student">
    <property name="id" value="1"></property>
    <property name="name" value="张三"></property>
    <property name="age" value="22"></property>
</bean>

<bean id="stu2" class="com.southwind.entity.Student" parent="stu1">
    <property name="name" value="李四"></property>
</bean>
```

### 2.3 Spring 的依赖

与 Spring 继承类似，也是描述 bean 和 bean 之间的一种关系，用来控制 bean 的创建顺序，默认的创建顺序，配置在前面的 bean 先创建。

```xml
<bean id="class" class="com.southwind.entity.Class" depends-on="stu1">
    <property name="id" value="101"></property>
    <property name="name" value="一班"></property>
</bean>

<bean id="stu1" class="com.southwind.entity.Student">
    <property name="id" value="1"></property>
    <property name="name" value="张三"></property>
    <property name="age" value="22"></property>
</bean>
```

### 2.4 Spring 读取外部资源

实际开发中，一般数据源的配置会单独放到一个文件中，db.properties，spring.xml 需要读取 db.properties 中的数据，这就是读取外部资源。

```properties
driverName=com.mysql.cj.jdbc.Driver
url=jdbc:mysql://localhost:3306/mybd2
user=root
password=123456
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">

    <context:property-placeholder location="classpath:jdbc.properties"></context:property-placeholder>

    <!-- SPEL -->
    <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
        <property name="user" value="${user}"></property>
        <property name="password" value="${password}"></property>
        <property name="driverClass" value="${driverName}"></property>
        <property name="jdbcUrl" value="${url}"></property>
    </bean>

</beans>
```

### 2.5 Spring p 命名空间

p 命名空间用来简化 bean 的各种配置。

```xml
<bean id="stu" class="com.southwind.entity.Student">
    <property name="id" value="1"></property>
    <property name="name" value="张三"></property>
    <property name="age" value="22"></property>
</bean>

<bean id="stu2" class="com.southwind.entity.Student" p:id="1" p:name="李四" p:age="23"></bean>
```

### 2.6 Spring IoC 自动装载 autowire

自动装载有两种方式：

- byName

```xml
<bean id="clazz" class="com.southwind.entity.Class" p:id="101" p:name="一班"></bean>

<bean id="student" class="com.southwind.entity.Student"
      p:id="1"
      p:name="张三"
      p:age="22"
autowire="byName" ></bean>
```

根据属性名进行自动装载

- byType

```xml
<bean id="abc" class="com.southwind.entity.Class" p:id="101" p:name="一班"></bean>

<bean id="student" class="com.southwind.entity.Student"
      p:id="1"
      p:name="张三"
      p:age="22"
autowire="byType" ></bean>
```

## 3. Spring AOP

AOP 面向切面编程，是面向对象编程思想的一种补充，实现了逻辑定义和逻辑实现的解耦合。

业务代码、日志代码

代理模式，现在业务方法我们不想输出日志信息，但是日志又必须输出，找个人帮我们输出日志（代理）

我们只需要写业务代码，找个代理帮我们输出日志。

![image-20200804232859595](C:\Users\userhan\AppData\Roaming\Typora\typora-user-images\image-20200804232859595.png)

面向切面编程使用动态代理机制来完成，代理类并不是提前写好的，而是程序员运行期间，根据类的元信息动态创建一个代理类，进而获取到代理对象，然后完成日志的输出。

动态创建一个类，帮助委托类完成某项工作。

代理类的特点就是具备委托类的功能，Java 程序中如何描述具备功能？

接口就是描述功能的，某个类实现了某个接口，该类就具备了某些功能。

1、代理类需要实现委托类所实现的接口。

代理类创建好之后，还需要加载到 JVM 中才能运行。

2、将动态创建的代理类加载到 JVM 中。

```java
package com.southwind.aop;

public interface Cal {
    public int add(int num1,int num2);
    public int sub(int num1,int num2);
    public int mul(int num1,int num2);
    public int div(int num1,int num2);
}
```

```java
package com.southwind.aop.impl;

import com.southwind.aop.Cal;

public class Calimpl implements Cal {
    public int add(int num1, int num2) {
        int result = num1 + num2;
        return result;
    }

    public int sub(int num1, int num2) {
        int result = num1 - num2;
        return result;
    }

    public int mul(int num1, int num2) {
        int result = num1 * num2;
        return result;
    }

    public int div(int num1, int num2) {
        int result = num1 / num2;
        return result;
    }
}
```

```java
package com.southwind.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * 不是动态代理类
 * 是帮助我们生成动态代理类的类
 */
public class MyInvocationHandler implements InvocationHandler {
    //委托对象
    private Object object = null;

    //返回动态代理对象
    public Object bind(Object object){
        //记录委托对象
        this.object = object;
        //返回代理对象
        return Proxy.newProxyInstance(MyInvocationHandler.class.getClassLoader(),object.getClass().getInterfaces(),this);
    }

    //代理对象方法调用
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //打印参数
        System.out.println(method.getName() + "方法的参数是：" + Arrays.toString(args));
        //业务方法
        int result = (Integer) method.invoke(this.object,args);
        //打印结果
        System.out.println(method.getName() + "方法的结果是" + result);
        return result;
    }
}
```

```java
package com.southwind.aop;

import com.southwind.aop.impl.Calimpl;

public class Test {
    public static void main(String[] args) {
        //委托对象
        Cal cal = new Calimpl();
        //创建动态代理对象
        MyInvocationHandler handler = new MyInvocationHandler();
        Cal proxy = (Cal) handler.bind(cal);
        System.out.println(proxy.add(10, 3));
        System.out.println(proxy.div(10, 3));
        System.out.println(proxy.mul(10, 3));
        System.out.println(proxy.sub(10, 3));
    }
}
```

上述代码的目的是实现业务代码和日志代码的解耦合，通过动态代理来实现了 AOP，动态代理也是反射机制的主要实际应用之一。

直接用原生动态代理机制来实现 AOP，非常抽象，不便于理解，所以 Spring AOP 对上述非常抽象的过程进行了封装，让开发者可以以更加简单的视角来进行 AOP 的开发。

1、pom.xml 导入最新版 Spring AOP 依赖。

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-context</artifactId>
        <version>5.2.8.RELEASE</version>
    </dependency>

    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-aspects</artifactId>
        <version>5.2.8.RELEASE</version>
    </dependency>

    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-aop</artifactId>
        <version>5.2.8.RELEASE</version>
    </dependency>
</dependencies>
```

2、创建切面类

方法需要切入到业务代码执行之前。

时间节点：执行之前

位置节点：业务代码

![image-20200805184518997](C:\Users\userhan\AppData\Roaming\Typora\typora-user-images\image-20200805184518997.png)

$Proxy12@2460

![image-20200805184526112](C:\Users\userhan\AppData\Roaming\Typora\typora-user-images\image-20200805184526112.png)

Calimpl@2079

```java
package com.southwind.aop.impl;

import com.southwind.aop.Cal;
import org.springframework.stereotype.Component;

@Component
public class Calimpl implements Cal {
    public int add(int num1, int num2) {
        int result = num1 + num2;
        return result;
    }

    public int sub(int num1, int num2) {
        int result = num1 - num2;
        return result;
    }

    public int mul(int num1, int num2) {
        int result = num1 * num2;
        return result;
    }

    public int div(int num1, int num2) {
        int result = num1 / num2;
        return result;
    }
}
```

```java
package com.southwind.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component//将bean注入到 IoC
@Aspect//标注为切面类
public class LoggerAspect {

    @Before("execution(public int com.southwind.aop.impl.Calimpl.*(..))")
    public void args(JoinPoint joinPoint){
        System.out.println(joinPoint.getSignature().getName()+"方法的参数是："+ Arrays.toString(joinPoint.getArgs()));
    }

}
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:p="http://www.springframework.org/schema/p"
       xmlns:util="http://www.springframework.org/schema/util"
       xmlns="http://www.springframework.org/schema/beans" xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop.xsd">

    <!-- 配置自动扫包 -->
    <context:component-scan base-package="com.southwind.aop"></context:component-scan>

    <!-- 创建代理对象 -->
    <aop:aspectj-autoproxy></aop:aspectj-autoproxy>

</beans>
```

```java
package com.southwind.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test2 {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-aop.xml");
        Cal cal = applicationContext.getBean(Cal.class);
        System.out.println(cal.add(10, 3));
        System.out.println(cal.sub(10, 3));
        System.out.println(cal.mul(10, 3));
        System.out.println(cal.div(10, 3));
    }
}
```

