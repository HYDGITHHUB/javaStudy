# Spring MVC

Spring MVC，全称：Spring Web MVC，Spring 框架提供的一个专门用来实现 MVC 设计模式的框架，实现客户端到服务器的交互，属于 Spring 全家桶的一员。

## 1. Spring MVC 核心组件

- DispatcherServlet，前置控制器，负责调度其他组件的执行，可以降低不同组件之间的耦合性，是整个 Spring MVC 的核心模块。
- Handler：处理器，完成具体的业务逻辑，相当于之前的 Servlet。
- HandlerMapping：DispatcherServlet 通过 HandlerMapping 将请求映射到不同的 Handler。
- HandlerAdapter：处理器适配器，在 Handler 的业务方法执行之前执行的，Handler 执行之前，通常需要进行一系列的操作包括表单数据的封装、数据类型的转换、数据校验，这些工作全部交给 HandlerAdapter 来完成，DispatcherServlet 通过 HandlerAdapter 来执行不同的 Handler。
- ModelAndView：装载了模型数据和视图信息，作为 Handler 的处理结果返回给 DispatcherServlet。
- ViewResolver：视图解析器，DispatcherServlet 通过它将逻辑视图解析成为物理视图，最终将渲染的结果响应给用户。

![image-20200805193026248](C:\Users\userhan\AppData\Roaming\Typora\typora-user-images\image-20200805193026248.png)

## 2. 如何使用

1、创建 Maven 工程，WEB 工程，依赖于 WEB 模板。

![image-20200805193040207](C:\Users\userhan\AppData\Roaming\Typora\typora-user-images\image-20200805193040207.png)

Maven WEB 创建很慢解决方案：

https://www.cnblogs.com/yachao1120/p/10847889.html

![image-20200805193059622](C:\Users\userhan\AppData\Roaming\Typora\typora-user-images\image-20200805193059622.png)

![image-20200805193113106](C:\Users\userhan\AppData\Roaming\Typora\typora-user-images\image-20200805193113106.png)赖，pom.xml

```xml
<dependencies>

  <dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-webmvc</artifactId>
    <version>5.2.8.RELEASE</version>
  </dependency>

</dependencies>
```

3、搭建 Spring MVC 体系，配置 DispatcherServlet，在 web.xml 中配置。

```xml
<!DOCTYPE web-app PUBLIC
 "-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN"
 "http://java.sun.com/dtd/web-app_2_3.dtd" >

<web-app>
  <display-name>Archetype Created Web Application</display-name>
  
  <servlet>
    <servlet-name>springmvc</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <init-param>
      <param-name>contextConfigLocation</param-name>
      <param-value>classpath:springmvc.xml</param-value>
    </init-param>
  </servlet>
  
  <servlet-mapping>
    <servlet-name>springmvc</servlet-name>
    <url-pattern>/</url-pattern>
  </servlet-mapping>
  
</web-app>
```

3、创建 springmvc.xml 文件。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">

    <!-- 配置自动扫包 -->
    <context:component-scan base-package="com.southwind"></context:component-scan>

    <!-- 视图解析器 -->
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <!-- 前缀 -->
        <property name="prefix" value="/"></property>
        <!-- 后缀 -->
        <property name="suffix" value=".jsp"></property>
    </bean>

</beans>
```

4、创建 Handler

```java
package com.southwind.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloHandler {

    @RequestMapping("/index")
    public String index(){
        System.out.println("index...");
        //逻辑视图
        return "index";
    }

}
```

URL 请求 /index，通过注解直接映射到 index 方法中，直接执行该方法，并且返回的是逻辑视图，将逻辑视图的值交给视图解析器，补上前缀以及后缀，形成真的物理视图路径，找到该物理视图，返回给客户端。

## 3. 常用注解

@Contoller 标注控制器类，使得一个 Java 类成为一个控制器，处理 DispatcherServlet 转发过来的请求了。

@RequestMapping

Spring MVC 通过 @RequestMapping 注解将 URL 请求与业务方法进行映射，控制器类定义处也可以添加 @RequestMapping，添加之后，相当于请求时多了一层访问路径。

![image-20200805193139586](C:\Users\userhan\AppData\Roaming\Typora\typora-user-images\image-20200805193139586.png)

![image-20200805193148896](C:\Users\userhan\AppData\Roaming\Typora\typora-user-images\image-20200805193148896.png)

@RequestMapping 常用参数

1、value：指定 URL 请求的实际地址，默认值。

```java
@Controller
@RequestMapping(value = "/hello")
public class HelloHandler {

    @RequestMapping(value = "/index")
    public String index(){
        System.out.println("index...");
        //逻辑视图
        return "index";
    }

}
```

```java
@Controller
@RequestMapping("/hello")
public class HelloHandler {

    @RequestMapping("/index")
    public String index(){
        System.out.println("index...");
        //逻辑视图
        return "index";
    }

}
```

> 常见的错误状态码

404：资源找不到，你要检查 url 是否拼写正确。

405：请求类型不匹配，只能接收 POST，你发送的是 GET。

400：参数不匹配，检查 URL 的传参。

500：代码中抛出异常，逻辑代码的问题。

2、method：指定请求的 method 类型，包括 GET、POST、PUT、DELETE。

```java
@RequestMapping(value = "/index",method = RequestMethod.POST)
public String index(){
    System.out.println("index...");
    //逻辑视图
    return "index";
}
```

上述代码表示只有 POST 请求可以访问当前方法。

3、params：指定请求中必须包含的参数，如果没有该参数，无法访问。

```java
@RequestMapping(value = "/index",method = RequestMethod.GET,params = {"name","id=10"})
public String index(){
    System.out.println("index...");
    //逻辑视图
    return "index";
}
```

上述代码表示 URL 请求中必须包含 name 和 id 两个参数，且 id 的值必须是 10，否则报错 400。

http://localhost:8080/index?name=tom&id=10

获取参数

```java
@RequestMapping(value = "/index",method = RequestMethod.GET,params = {"name","id=10"})
public String index(@RequestParam("name") String title,@RequestParam("id") Integer num){
    System.out.println(title);
    System.out.println(num + 1);
    //逻辑视图
    return "index";
}
```

@RequestParam 的作用是将 URL 中的参数名和业务方法中的形参名进行映射，如果 URL 中的参数名本身和业务方法中的形参名一致，可以不添加 @RequestParam，自动根据名字完成映射，如果不一致，则必须添加。

## 4. RESTful

Spring MVC 也支持 RESTful 风格的 URL 传参

RESTful 是一种交互方式，规则，在不同的服务之间可以通过统一的规则完成数据的传输。

具体就是用 HTTP 请求的四种类来表示 CRUD 四种基本操作。

GET 表示查询

POST 表示添加

PUT 表示修改

DELETE 表示删除

传统 URL：localhost:8080/test?id=1&name=tom

RESTful RUL：localhost:8080/test/1/tom

```java
@Controller
@RequestMapping("/rest")
public class RESTfulHandler {

    @RequestMapping("/index/{id}/{name}")
    public String index(@PathVariable("id") Integer id,@PathVariable("name") String name){
        System.out.println(id + 1);
        System.out.println(name);
        return "index";
    }

}
```

![image-20200804201747437](C:\Users\ningn\AppData\Roaming\Typora\typora-user-images\image-20200804201747437.png)

## 5. POJO Java 对象

```jsp
<form action="/rest/register" method="post">
    用户名：<input type="text" name="username"/><br/>
    密码：<input type="text" name="password"/><br/>
    年龄：<input type="text" name="age"/><br/>
    电话：<input type="text" name="phone"/><br/>
    地址：<input type="text" name="email"/><br/>
    <input type="submit" />
</form>
```

```java
@RequestMapping(value = "/register",method = RequestMethod.POST)
public String register(User user){
    System.out.println(user);
    return "index";
}
```

## 6. 后端方法跳到另外一个方法

```java
@RequestMapping(value = "/register",method = RequestMethod.POST)
public String register(User user){
    System.out.println(user);
    //重定向
    return "redirect:/rest/ok";
}

@RequestMapping("/ok")
public String ok(){
    System.out.println("okokok");
    return "index";
}
```

## 7. Spring MVC 视图解析机制

Controller 中的数据如何传给 JSP

- HttpServletRequest
- Map
- Model
- ModelAndView
- @ModelAttribute
- @SessionAttribute

**HttpServletRequest**

![image-20200806111615151](C:\Users\userhan\AppData\Roaming\Typora\typora-user-images\image-20200806111615151.png)

![image-20200806111747972](C:\Users\userhan\AppData\Roaming\Typora\typora-user-images\image-20200806111747972.png)

**Map**

![image-20200806111921150](C:\Users\userhan\AppData\Roaming\Typora\typora-user-images\image-20200806111921150.png)

![image-20200806111747972](C:\Users\userhan\AppData\Roaming\Typora\typora-user-images\image-20200806111747972.png)

**Model**

![image-20200806143527248](C:\Users\userhan\AppData\Roaming\Typora\typora-user-images\image-20200806143527248.png)

**ModelAndView**

![image-20200806143800045](C:\Users\userhan\AppData\Roaming\Typora\typora-user-images\image-20200806143800045.png)

**@ModelAttribute**

在jsp中获取视图数据的时候，用**list**获取，是方法返回类型小写

![image-20200806153357242](C:\Users\userhan\AppData\Roaming\Typora\typora-user-images\image-20200806153357242.png)

![image-20200806235444797](C:\Users\userhan\AppData\Roaming\Typora\typora-user-images\image-20200806235444797.png)

当返回不带泛型的包装类的时候，无法识别类型，会以Object接收，所以要不在jsp中进行类型强转，否则无法获取其中数据

![image-20200806152355786](C:\Users\userhan\AppData\Roaming\Typora\typora-user-images\image-20200806152355786.png)

**SessionAttribute**

@SessionAttribute 是将模型数据存储到request中直接同步到 session 对象中，session  和 request 一样都是 JSP 内置对象，session 的作用域更大，request 只能在一次请求中存活，请求结束，request 销毁，里面保存的数据也就没了，session 作用于一个会话（打开浏览器发起请求，到关闭浏览器的整个过程），用 session 来保存用户登录信息。

session.setAttribute/getAttribute

@SessionAttribute 不是在方法定义处添加，而是直接添加到类定义处。

```java
@Controller
@RequestMapping(value = "/hello")
@SessionAttributes(types = User.class)
public class HelloHandler {
    ...
}
```

当类中的业务方法向 request 中存入数据的同时，会自动将数据同步到 session 中，保留 request 中的 key/value。

```xml
<!-- 从servlet中的request中去数据 -->
${requestScope.user}
<!-- 从session对象中取数据 -->
${sessionScope.user}
```

## 8. RESTful 小demo

```java
@Repository
public class UserMapper {
    private static Map<Integer, User> map;

    static {
        map = new HashMap<>();
        map.put(1, new User(1, "a"));
        map.put(2, new User(2, "b"));
        map.put(3, new User(3, "c"));
        map.put(4, new User(4, "d"));
        map.put(5, new User(5, "e"));
        map.put(6, new User(6, "f"));
    }

    public Collection<User> findAll(){
        return map.values();
    }

    public User findById(Integer id){
        return map.get(id);
    }

    public void save(User user){
        map.put(user.getId(), user);
    }
}
```

```java
package com.southwind.controller;

import com.southwind.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserHandler {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/findAll")
    public ModelAndView findAll(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("list",this.userMapper.findAll());
        modelAndView.setViewName("user");
        return modelAndView;
    }

    @GetMapping("/findById/{id}")
    public ModelAndView findAll(@PathVariable("id") Integer id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user",this.userMapper.findById(id));
        modelAndView.setViewName("user");
        return modelAndView;
    }
}
```

```jsp
<%--
  Created by IntelliJ IDEA.
  User: ningn
  Date: 2020/8/4
  Time: 20:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <c:if test="${list != null}">
        <c:forEach items="${list}" var="user">
            ${user.id}-${user.username}
        </c:forEach>
    </c:if>
    <c:if test="${user != null}">
        ${user.id}:${user.username}
    </c:if>
</body>
</html>
```

## 9. Spring MVC 数据校验

Spring MVC 提供两种校验方式：

- 基于 Validator 接口进行校验
- 使用 Annotation JSR-303 标准进行校验

使用 Validator 接口进行校验操作会复杂一些，具体的数据校验规则**需要开发者手动定义**。

使用 JSR-303 标准就会相对简单，开发者不需要进行数据校验的设置，直接通过注解的形式给每一条数据添加校验规则。

### 9.1 基于 Validator 接口进行校验

1、创建实体类

```java
package com.southwind.entity;

import lombok.Data;

@Data
public class Student {
    private String name;
    private String password;
}
```

2、自定义校验器，实现接口 Validator 接口。

```java
package com.southwind.validator;

import com.southwind.entity.Student;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class StudentValidation implements Validator {

    /**
     * 判断当前对象是否可以进行校验
     * @param aClass
     * @return
     */
    @Override
    public boolean supports(Class<?> aClass) {
        return Student.class.equals(aClass);
    }

    /**
     * 具体的校验逻辑
     * @param o
     * @param errors
     */
    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "name",null,"姓名不能为空");
        ValidationUtils.rejectIfEmpty(errors, "password", null,"密码不能为空");
    }
}
```

3、创建 Handler

```java
package com.southwind.controller;

import com.southwind.entity.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentHandler {

    //为了给表单绑定对象，为校验提供条件
    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("student",new Student());
        return "login";
    }

    //业务逻辑
    @PostMapping("/login")
    public String login(@Validated Student student, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "login";
        }else{
            return "index";
        }
    }
}

```

4、login.jsp

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
        <form:form modelAttribute="student" action="/student/login" method="post">
            学生姓名:<form:input path="name"></form:input><form:errors path="name"></form:errors><br/>
            学生密码:<form:input path="password"></form:input><form:errors path="password"></form:errors><br/>
            <input type="submit" value="登录"/>
        </form:form>
</body>
</html>
```

5、springmvc.xml 中配置校验器。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       http://www.springframework.org/schema/context/spring-context.xsd
       http://www.springframework.org/schema/mvc
       http://www.springframework.org/schema/mvc/spring-mvc-3.2.xsd">

    <!-- 配置自动扫包 -->
    <context:component-scan base-package="com.southwind"></context:component-scan>

    <!-- 视图解析器 -->
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <!-- 前缀 -->
        <property name="prefix" value="/"></property>
        <!-- 后缀 -->
        <property name="suffix" value=".jsp"></property>
    </bean>

    <mvc:annotation-driven validator="studentValidator"/>
    <bean id="studentValidator" class="com.southwind.validator.StudentValidation"></bean>

</beans>
```

### 9.2 JSR-303 标准

Hibernate Validator

1、pom.xml

```xml
<dependency>
  <groupId>org.hibernate</groupId>
  <artifactId>hibernate-validator</artifactId>
  <version>5.2.4.Final</version>
</dependency>
<dependency>
  <groupId>org.jboss.logging</groupId>
  <artifactId>jboss-logging</artifactId>
  <version>3.4.1.Final</version>
</dependency>
<!-- JDK9以上版本需要导入以下 jar 包 -->
<dependency>
  <groupId>javax.xml.bind</groupId>
  <artifactId>jaxb-api</artifactId>
  <version>2.3.0</version>
</dependency>
<dependency>
  <groupId>com.sun.xml.bind</groupId>
  <artifactId>jaxb-impl</artifactId>
  <version>2.3.0</version>
</dependency>
<dependency>
  <groupId>com.sun.xml.bind</groupId>
  <artifactId>jaxb-core</artifactId>
  <version>2.3.0</version>
</dependency>
<dependency>
  <groupId>javax.activation</groupId>
  <artifactId>activation</artifactId>
  <version>1.1.1</version>
</dependency>
```

2、创建实体类，成员变量上通过注解来完成校验的规则设置

```java
package com.southwind.entity;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

@Data
public class Account {
    @NotEmpty(message = "用户名不能为空")
    private String username;
    @NotEmpty(message = "密码不能为空")
    private String password;
}
```

3、创建控制器

```java
package com.southwind.controller;

import com.southwind.entity.Account;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/account")
public class AccountHandler {

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("account", new Account());
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid Account account, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "register";
        }else{
            return "index";
        }
    }

}
```

4、JSP

```jsp
<%--
  Created by IntelliJ IDEA.
  User: ningn
  Date: 2020/8/4
  Time: 20:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form:form modelAttribute="account" action="/account/register" method="post">
        用户名：<form:input path="username"></form:input><form:errors path="username"></form:errors><br/>
        密码：<form:input path="password"></form:input><form:errors path="password"></form:errors><br/>
        <input type="submit" value="注册"/>
    </form:form>
</body>
</html>
```

5、springmvc.xml 中配置

```xml
<mvc:annotation-driven/>
```

功能一致，Handler 和 JSP 层代码完全一致，JSR-303 实现更加简单，因为它将校验逻辑直接写入到实体类中了，不需要单独去配置校验器，更加方便，实际开发中更推荐使用 JSR-303 的方式。

## 10. SSM 小demo

Spring IoC

Spring AOP

Spring MVC 搭建、Spring MVC 常用注解、Spring MVC 数据绑定、Spring MVC 视图解析、Spring MVC RESTful（分布式开发的基础）、Spring MVC 数据校验

MyBatis 搭建、MyBatis Mapper 配置、MyBatis 缓存、MyBatis 延迟加载、MyBatis 动态 SQL

SSM 很麻烦 很繁琐

Spring：管理 Spring MVC 和 MyBatis 的，自动生成它们所需要的 bean IoC

Spring MVC：实现 MVC 设计模式，实现前后端的交互。

MyBatis：JDBC 的封装，实现 Java 到数据库的交互。

Spring 和 Spring MVC 本来就是一个框架，所以不存在整合的概念，SSM 整合实际上是将 MyBatis 和 Spring 进行整合，将 MyBatis 的各种配置交给 Spring 来管理。

1、创建 Maven Web 工程，pom.xml

```xml
<dependencies>
  <dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-webmvc</artifactId>
    <version>5.2.8.RELEASE</version>
  </dependency>

  <dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-jdbc</artifactId>
    <version>5.2.8.RELEASE</version>
  </dependency>

  <dependency>
    <groupId>org.mybatis</groupId>
    <artifactId>mybatis-spring</artifactId>
    <version>2.0.5</version>
  </dependency>

  <dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.21</version>
  </dependency>

  <dependency>
    <groupId>com.mchange</groupId>
    <artifactId>c3p0</artifactId>
    <version>0.9.5.5</version>
  </dependency>

  <dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.12</version>
  </dependency>

</dependencies>
```

2、web.xml 配置，DispatcherServlet，Spring 监听器。

```xml
<!DOCTYPE web-app PUBLIC
 "-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN"
 "http://java.sun.com/dtd/web-app_2_3.dtd" >

<web-app>
  <display-name>Archetype Created Web Application</display-name>

  <!-- 启动Spring的容器  -->
  <context-param>
    <param-name>contextConfigLocation</param-name>
    <param-value>classpath:spring.xml</param-value>
  </context-param>
  <listener>
    <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
  </listener>

  <!-- SpringMVC的前端控制器，拦截所有请求  -->
  <servlet>
    <servlet-name>mvc-dispatcher</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <init-param>
      <param-name>contextConfigLocation</param-name>
      <param-value>classpath:springmvc.xml</param-value>
    </init-param>
  </servlet>

  <servlet-mapping>
    <servlet-name>mvc-dispatcher</servlet-name>
    <url-pattern>/</url-pattern>
  </servlet-mapping>

</web-app>
```

3、配置resources中spring.xml springmvc.xml mybatis.xml

spring.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xmlns:tx="http://www.springframework.org/schema/tx"
       xsi:schemaLocation="http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-4.3.xsd
      http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.2.xsd
      http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx-4.3.xsd
      http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.3.xsd">

    <!-- 加载外部文件 -->
    <context:property-placeholder location="classpath:dbconfig.properties"/>

    <!-- 配置 C3P0 数据源 -->
    <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
        <property name="user" value="${jdbc.user}"></property>
        <property name="password" value="${jdbc.password}"></property>
        <property name="driverClass" value="${jdbc.driverClass}"></property>
        <property name="jdbcUrl" value="${jdbc.jdbcUrl}"></property>
        <property name="initialPoolSize" value="5"></property>
        <property name="maxPoolSize" value="10"></property>
    </bean>

    <!-- 配置MyBatis SqlSessionFactory -->
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <!-- 指定MyBatis 数据源 -->
        <property name="dataSource" ref="dataSource"/>
        <!-- 指定MyBatis mapper文件的位置 -->
        <property name="mapperLocations" value="classpath:com/southwind/repository/*.xml"/>
        <!-- 指定MyBatis全局配置文件的位置 -->
        <property name="configLocation" value="classpath:mybatis.xml"></property>
    </bean>

    <!-- 扫描MyBatis的mapper接口 -->
    <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <!--扫描所有Repository接口的实现，加入到IoC容器中 -->
        <property name="basePackage" value="com.southwind.repository"/>
    </bean>

</beans>
```

springmvc.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
        http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.0.xsd
        http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc-3.2.xsd">

    <!-- 告知spring，启用springmvc的注解驱动 -->
    <mvc:annotation-driven />

    <!-- 扫描业务代码 -->
    <context:component-scan base-package="com.southwind"></context:component-scan>

    <!-- 配置视图解析器 -->
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="prefix" value="/"></property>
        <property name="suffix" value=".jsp"></property>
    </bean>

</beans>
```

```xml
<!-- mybatis.xml -->
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN" "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <settings>
        <!-- 打印SQL-->
        <setting name="logImpl" value="STDOUT_LOGGING" />
    </settings>

    <typeAliases>
        <!-- 指定一个包名，MyBatis会在包名下搜索需要的JavaBean-->
        <package name="com.southwind.entity"/>
    </typeAliases>

</configuration>
```

## 11. 框架整合

1、注意框架的版本兼容性

2、一定要理解每个框架的作用

![image-20200807201140050](C:\Users\ningn\AppData\Roaming\Typora\typora-user-images\image-20200807201140050.png)

SSM 框架组合的结构如上图所示。

- Spring MVC 负责客户端与 Java 后台程序的交互。

- MyBatis 是负责 Java 后台程序与数据库的交互。

- Spring 是负责管理 Spring MVC 和 MyBatis，具体就是管理这个两个框架各种对象的自动创建和依赖注入。

![image-20200809000925161](C:\Users\userhan\AppData\Roaming\Typora\typora-user-images\image-20200809000925161.png)

### 11.1 SSM 框架整合

1、创建 Maven Web 工程，pom.xml 中添加 SSM 的依赖。

SSM 其实只需要整合两个框架 Spring 和 MyBatis，因为 Spring MVC 本来就是 Spring 的一个模块，所以不存在整合，所以只需要整合 MyBatis 和 Spring 即可，具体来说**就是将 MyBatis 的各种加载机制交给 Spring IoC 来管理即可**。

```xml
<dependency>
  <groupId>org.springframework</groupId>
  <artifactId>spring-webmvc</artifactId>
  <version>5.2.8.RELEASE</version>
</dependency>

<dependency>
  <groupId>org.mybatis</groupId>
  <artifactId>mybatis</artifactId>
  <version>3.5.5</version>
</dependency>

<dependency>
  <groupId>org.mybatis</groupId>
  <artifactId>mybatis-spring</artifactId>
  <version>2.0.5</version>
</dependency>

<dependency>
  <groupId>org.springframework</groupId>
  <artifactId>spring-jdbc</artifactId>
  <version>5.2.8.RELEASE</version>
</dependency>

<dependency>
  <groupId>mysql</groupId>
  <artifactId>mysql-connector-java</artifactId>
  <version>8.0.21</version>
</dependency>

<dependency>
  <groupId>com.mchange</groupId>
  <artifactId>c3p0</artifactId>
  <version>0.9.5.5</version>
</dependency>

<dependency>
  <groupId>org.projectlombok</groupId>
  <artifactId>lombok</artifactId>
  <version>1.18.12</version>
</dependency>

<dependency>
  <groupId>jstl</groupId>
  <artifactId>jstl</artifactId>
  <version>1.2</version>
</dependency>
```

2、web.xml 中配置，Spring MVC 的相关配置，Spring 的相关配置

```xml
<!DOCTYPE web-app PUBLIC
 "-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN"
 "http://java.sun.com/dtd/web-app_2_3.dtd" >

<web-app>
  <display-name>Archetype Created Web Application</display-name>

  <!-- 启动 Spring -->
  <context-param>
    <param-name>contextConfigLocation</param-name>
    <param-value>classpath:spring.xml</param-value>
  </context-param>
  <listener>
    <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
  </listener>

  <!-- Spring MVC -->
  <servlet>
    <servlet-name>mvc</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <init-param>
      <param-name>contextConfigLocation</param-name>
      <param-value>classpath:springmvc.xml</param-value>
    </init-param>
  </servlet>
  <servlet-mapping>
    <servlet-name>mvc</servlet-name>
    <url-pattern>/</url-pattern>
  </servlet-mapping>

</web-app>
```

3、在 resources 路径下创建三个框架的配置文件：spring.xml、springmvc.xml、mybatis.xml、dbconfig.properties

dbconfig.properties

```properties
jdbc.jdbcUrl=jdbc:mysql://localhost:3306/mybd2?useUnicode=true&characterEncoding=UTF-8
jdbc.driverClass=com.mysql.cj.jdbc.Driver
jdbc.user=root
jdbc.password=123456
```

spring.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xmlns:tx="http://www.springframework.org/schema/tx"
       xsi:schemaLocation="http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-4.3.xsd
      http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.2.xsd
      http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx-4.3.xsd
      http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.3.xsd">

    <!-- DataSource -->
    <context:property-placeholder location="classpath:dbconfig.properties"></context:property-placeholder>

    <!-- C3P0 -->
    <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
        <!-- SPEL -->
        <property name="user" value="${jdbc.user}"></property>
        <property name="password" value="${jdbc.password}"></property>
        <property name="jdbcUrl" value="${jdbc.jdbcUrl}"></property>
        <property name="driverClass" value="${jdbc.driverClass}"></property>
        <property name="initialPoolSize" value="5"></property>
        <property name="maxPoolSize" value="20"></property>
        <property name="minPoolSize" value="2"></property>
        <property name="acquireIncrement" value="5"></property>
    </bean>

    <!-- SqlSessionFactory -->
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="dataSource"></property>
        <property name="mapperLocations" value="classpath:com/southwind/mapper/*.xml"></property>
        <property name="configLocation" value="classpath:mybatis.xml"></property>
    </bean>

    <!-- Mapper接口 -->
    <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <property name="basePackage" value="com.southwind.mapper"></property>
    </bean>

</beans>
```

mybatis.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN" "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <settings>
        <!-- 打印SQL-->
        <setting name="logImpl" value="STDOUT_LOGGING" />
    </settings>

    <typeAliases>
        <!-- 指定一个包名，MyBatis会在包名下搜索需要的JavaBean-->
        <package name="com.southwind.entity"/>
    </typeAliases>

</configuration>
```

springmvc.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
        http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.0.xsd
        http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc-3.2.xsd">

    <!-- 告知 Spring，启用 Spring MVC 的注解驱动 -->
    <mvc:annotation-driven />

    <!-- 自动扫包 -->
    <context:component-scan base-package="com.southwind"></context:component-scan>

    <!-- 配置视图解析器 -->
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="prefix" value="/"></property>
        <property name="suffix" value=".jsp"></property>
    </bean>

</beans>
```

4、创建实体类

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

5、创建 AccountMapper 接口以及 Mapper.xml

```java
package com.southwind.mapper;

import com.southwind.entity.Account;

import java.util.List;

public interface AccountMapper {
    public List<Account> list();
}
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.southwind.mapper.AccountMapper">

    <select id="list" resultType="Account">
        select * from account
    </select>

</mapper>
```

6、创建 Service

```java
package com.southwind.service;

import com.southwind.entity.Account;

import java.util.List;

public interface AccountService {
    public List<Account> list();
}
```

```java
package com.southwind.service.impl;

import com.southwind.entity.Account;
import com.southwind.mapper.AccountMapper;
import com.southwind.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public List<Account> list() {
        return this.accountMapper.list();
    }
}
```

7、创建 Controller

```java
package com.southwind.controller;

import com.southwind.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AccountController {

    @Autowired
    private AccountService accountService;
    
    @GetMapping("/list")
    public ModelAndView list(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("list",this.accountService.list());
        modelAndView.setViewName("index");
        return modelAndView;
    }
}
```

8、JSP

```jsp
<%--
  Created by IntelliJ IDEA.
  User: ningn
  Date: 2020/8/7
  Time: 20:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <c:forEach items="${list}" var="account">
        ${account.id}-${account.username}-${account.password}-${account.createtime}-${account.updatetime}<br/>
    </c:forEach>
</body>
</html>
```

9、pom.xml 添加读取 XML 的设置

```xml
<resources>
  <resource>
    <directory>src/main/java</directory>
    <includes>
      <include>**/*.xml</include>
    </includes>
  </resource>
</resources>
```

