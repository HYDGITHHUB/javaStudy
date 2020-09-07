# JavaWeb

Java WEB 是一套开发组件，属于 Java EE，Java 的企业级解决方案，Java SE、Java EE、Java ME

Servlet、JSP、Filter、EL、JSTL

用 Java 语言开发 WEB 应用程序。

Server Client

Java Server

Client 浏览器

## 1. JavaWeb 开发流程

使用 Java 开发一套 WEB 后端的程序，部署到服务器中，用户可以通过浏览器来访问我们的程序。

1、开发应用程序。

2、使用 Tomcat 进行部署，才能被访问。

Tomcat 是应用服务器，专门用来将 Java 程序部署，对外开放，供用户去访问。

http://tomcat.apache.org/

1、下载

2、解压

bin 存放脚本命令 启动、关闭 Tomcat

conf 存放配置文件

lib 存放 jar 包

temp 存放临时文件

webapps 存放允许客户端访问的资源

work 存放 JSP 转换的 Servlet 文件

 3、启动 Tomcat

进入 bin 目录，执行 startup.bat

先开发自己的 Java WEB 程序，之后将 Java WEB 程序部署到 Tomcat 中，启动 Tomcat，客户端就可以访问你的 Java WEB 程序。

## 2. Servlet

使用 Java 来完成网站后台的业务逻辑操作，需要借助于 Servlet 完成，Servlet 是一个接口，专门用来提供 WEB 访问服务的。

GET/POST

1、创建 Java WEB，开发代码

2、部署 Tomcat，启动应用。

3、客户端（浏览器）可以直接访问 Tomcat，调用部署的项目。

Servlet 中直接返回 HTML 非常麻烦，维护性几乎为零，如何解决？

使用 JSP，JSP 本质上就是一个 Servlet，可以像编写 HTML 代码一样去编写 Servlet 的逻辑。

```java
package com.southwind.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/my")
public class MyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        String keyword = req.getParameter("keyword");
        String result = null;
        switch (keyword){
            case "java":
                result = "Java是一门面向对象编程语言，不仅吸收了C++语言的各种优点，还摒弃了C++里难以理解的多继承、指针等概念，因此Java语言具有功能强大和简单易用两个特征。Java语言作为静态面向对象编程语言的代表，极好地实现了面向对象理论，允许程序员以优雅的思维方式进行复杂的编程 [1]  。";
                break;
            case "python":
                result = "Python 是一种解释型、面向对象、动态数据类型的高级程序设计语言。\n" +
                        "\n" +
                        "Python 由 Guido van Rossum 于 1989 年底发明，第一个公开发行版发行于 1991 年。\n" +
                        "\n" +
                        "像 Perl 语言一样, Python 源代码同样遵循 GPL(GNU General Public License) 协议。";
                break;
            case "c":
                result = "C 语言是一种通用的、面向过程式的计算机程序设计语言。1972 年，为了移植与开发 UNIX 操作系统，丹尼斯·里奇在贝尔电话实验室设计开发了 C 语言。";
                break;
        }
        req.setAttribute("data",result);
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
```

```jsp
<%--
  Created by IntelliJ IDEA.
  User: ningn
  Date: 2020/7/5
  Time: 16:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
    <div style="width: 300px;height: 300px;background-color: #FFFF00;" onclick="test()"></div>
    <h1 id="h1">hhh</h1>
    <%
      String mess = (String) request.getAttribute("data");
    %>
    <p>
      <%=mess%>
    </p>
  </body>
</html>
```

## 3. EL

JSP 提供的一种表达式，可以用来简化 JSP 页面取值的问题

````jsp
<%
String mess = (String) request.getAttribute("data");
%>
<p>
    <%=mess%>
</p>
````

EL 进行优化

```jsp
<p>
  ${data}
</p>
```

## 4. JSTL

JSP 标准标签库 Java Standard Tag Library，利用 JSTL 的标签可以结合 EL 表达式对 JSP 页面的数据进行逻辑处理，比如循环遍历。

1、导入 jar

![image-20200722082400654](C:\Users\userhan\AppData\Roaming\Typora\typora-user-images\image-20200722082400654.png)

配置导入工程。

![image-20200722082416136](C:\Users\userhan\AppData\Roaming\Typora\typora-user-images\image-20200722082416136.png)![image-20200722082423816](C:\Users\userhan\AppData\Roaming\Typora\typora-user-images\image-20200722082423816.png)![image-20200722082430952](C:\Users\userhan\AppData\Roaming\Typora\typora-user-images\image-20200722082430952.png)![image-20200722082438551](C:\Users\userhan\AppData\Roaming\Typora\typora-user-images\image-20200722082438551.png)

2、使用，JSP 页面引入标签

```jsp
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
```

3、写代码

```java
package com.southwind.servlet;

import com.southwind.entity.User;
import com.southwind.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/user")
public class UserServlet extends HttpServlet {

    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //查询数据
        List<User> list = userService.data();
        req.setAttribute("list", list);
        req.getRequestDispatcher("user.jsp").forward(req, resp);
    }
}
```

```java
package com.southwind.service;

import com.southwind.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    public List<User> data(){
        List<User> list = new ArrayList<>();
        list.add(new User(1, "张三", 22));
        list.add(new User(2, "李四", 23));
        list.add(new User(3, "王五", 30));
        return list;
    }

}
```

```jsp
<%--
  Created by IntelliJ IDEA.
  User: ningn
  Date: 2020/7/5
  Time: 17:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <table>
        <tr>
            <td>编号</td>
            <td>姓名</td>
            <td>年龄</td>
        </tr>
        <c:forEach items="${list}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.age}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
```