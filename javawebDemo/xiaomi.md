# xiaomi

## Java

>hashCode 和 equals,什么时候可以只重写equals而不重写hashcode
>
>java何时会用到wait()
>
>Cglib与JDK的动态规划
>
>static
>
>序列化的作用(实现Serializable 接口)
>
>IO
>
>深拷贝和浅拷贝(深拷贝:实现CloneAble类并且重写clone方法)
>
>为什么用ArrayDeque而不用Java提供的Stack
>
>Java arraylist 和 linkedlist的区别，arraylist的扩容；
>
>数组和[链表](https://www.nowcoder.com/jump/super-jump/word?word=链表)区别？对于读写频繁的情况，用什么数据结构？B+树
>
>反射
>
>object包含的方法
>
>线程安全的数据结构
>
>stringbuild stringbuffer
>
>java特征(封装,继承,多态,抽象)
>
>HashMap中的hash有什么作用
>
>put一个对象的过程
>
>多态
>
>GIT,举个场景,如何恢复
>
>讲讲HashMap的底层实现  
>[红黑树]()是什么，为什么HashMap要用[红黑树]()  
>JDK1.8当中HashMap的put是怎么实现的，[链表]()和[红黑树]()的具体插入是怎么做的 
>
> JDK1.8中HashMap对[链表]()的插入是头插法还是尾插法？JDK1.7呢？为什么要改？  
> HashMap有线程安全版本吗  
> ConcurrentHashMap在JDK1.7和JDK1.8分别是怎么实现的  
> HashTable是什么，底层实现呢  
> ArrayList和LinkedList有什么区别  
> 为什么jdk1.7的ConcurrentHashMap用Reentra
>
>JavaNIO,BIO(select,pool,epool)
>
>Collection集合类
>
>**hashmap**(1.7,1.8)和hashtable,怎么扩容,散列因子,散列表如何存放
>
>hashmap的初始容量为什么是16，为什么每次扩容都是2倍扩容？
>
>equals 和 ==
>
>comparable
>
>红黑树调整

## JVM

>Java内存模型
>
>JVM区域划分
>
>什么时候栈溢出，什么时候内存溢出？ 
>
> 栈帧里面有什么？局部变量表里存的是什么，比如对于（int a = 1; Integer b = new Integer()） 
>
> 如何设置栈大小，堆大小，新生代老年代大小——JVM参数 
>
> 说说堆及对应的垃圾回收器？ 
>
> CMS具体流程，STW是什么？用的什么垃圾回收[算法]()？为什么先初始标记再并发标记； 
>
> G1 原理？标记清除[算法]()存在的问题？ 
>
> 说说垃圾回收[算法]()？什么是复制[算法]()？ 
>
> 新生代为什么用复制[算法]()？ 
>
> 创建一个对象的过程？ 
>
> 方法区主要放什么，会垃圾回收吗？方法区用的什么垃圾回收？方法区在对里面吗？
>
>jvm堆结构、创建对象过程、判断垃圾方法、垃圾回收算法
>
>syschronized和reentrantlock对比和原理、aqs原理、aqs公平锁和非公平锁、非公平锁实现原理
>
>虚拟内存机制
>
>cms垃圾回收算法,双亲委派机制
>
>jvm模型 哪些事公有哪些是私有
>
>常用的gc回收算法
>
>说一种复制算法，什么时候标记垃圾对象，怎么判断对象是否是垃圾
>
>虚引用垃圾回收怎么办
>
>类加载机制
>
>Java运行数据区
>
>垃圾收集算法有哪些
>
>页面置换中的页怎么理解
>
>内存碎片,标记清除
>
>JMM
>
>threadlocal内存泄露问题
>
>俩对象循环引用是否会被AC
>
>对象在内存中的存储结构
>
>死锁的排查
>
>对象分配在哪里
>
>垃圾回收机制
>
>JVM调优(XMS,Xmx)
>
>内存泄漏是什么,如何查看内存泄露
>
>如何查看具体那块内存泄漏(jmap)

## 多线程

>synchronized1.5之前,锁升级,底层原理
>
>线程保活怎么实现
>
>线程的三种实现方式
>
>对象头里的锁标志位干啥的？
>
>共享锁和排他锁
>
>有哪些锁
>
>G1垃圾收集器讲一下
>CMS和G1都有多次标记阶段，为什么要这样
>虚拟机栈里有什么，干什么用的
>虚拟机栈会抛出错误吗，什么时候会抛出什么错误
>
>单例模式双端检测锁
>
>如果线程池是空的，放入一个新的工作，线程池会怎样处理，线程池是满的呢？
>如果线程池是空的，放了一个工作之后该工作结束了，再放一个新的工作会怎么样
>
>atomic原子类线程安全原理。
>
>concurrenthashmap如何保证线程安全，多线程插入插入原理和如何保证安全1.7 1.8
>
>ThreadLocal，项目怎么用的，原理是什么？除了remove()还有什么方法防止内存溢出？
>
>CompleteableFuture怎么用的？
>
>如何实现线程同步
>
>几种阻塞队列的区别
>
>JVM对synchronized关键字的优化
>
>多线程实现方式
>
>怎么定位解决死锁,MVCC可以帮助解决死锁吗
>
>java中有哪些锁
>
>volatile和锁有区别吗
>
>hashmap底层源码，synchronizedMap，ConcurrentHashMap，hashtable底层实现；
>
>synchronizedMap，ConcurrentHashMap，hashtable，ArrayList，LinkList，synchronizedList底层实现
>
>JMM
>
>volatile
>
>wait和sleep区别
>
>sleep会释放资源吗
>
>CAS是什么,哪里用到,有什么问题
>
>线程合并
>
>wait notify
>
>什么是线程安全问题,如何实现
>
>ThreadLocal
>
>copeOnWrite
>
>JUC下用过哪些包
>
>ReentrantLock(底层原理)和countDownLock,CyclicBarrier,semaphore
>
>future
>
>分布式锁
>
>分库分表(读写分离,水平垂直)
>
>AQS源码理解,获取释放,共享独占,实现类
>
>线程池七大参数,原理
>
>互斥同步锁,区别
>
>java的锁

## 计网

>http为什么是无连接的,http1.1添加keepalive还算无连接的吗
>
>网页加载用什么协议 视频聊天用什么协议
>
>session和cookie
>
>什么是RPC，RPC全称； 
>
> 如果自己实现RPC，你会考虑什么？ 
>
> 为啥用RPC，为什么不用HTTP。 
>
> Dubbo是基于什么协议传输的？Dubbo支持http协议嘛？ 
>
> RabbitMQ作用是什么，为什么使用消息队列？
>
>OSI七层模型各个层的主要功能
>
>XXX协议是哪一层，没有细问，小case
>
>Socket是协议吗？是的话应该是哪一层？不是的话是什么(socket不太会）
>
>SSL握手过程
>
>ngin
>
>在浏览器中打开一个网页的过程
>
>http的请求的报文结构是什么
>
>http的[keep](https://www.nowcoder.com/jump/super-jump/word?word=keep)-alive有什么作用，及其与tcp[keep](https://www.nowcoder.com/jump/super-jump/word?word=keep)-alive的区别
>
>.TCP四次挥手的参数详解，以及两边各是什么状态说明一下。以及为啥要等待2msl时间。
>
>HTTPS原理,如何加密,和http区别
>
>cookie被禁了怎么办
>
>header里面有什么
>
>UDP和TCP
>
>tcp三次握手四次挥手
>
>tcp传输中seq作用,udp有没得
>
>TCP拥塞控制（慢开始，拥塞避免，快恢复，快重传）。

## 操作系统

>进程和线程
>
>线程内存空间可以共享吗
>
>进程间的通信方式
>
>从磁盘读取信息的过程
>
>磁道,扇区
>
>一个线程挂掉的影响

## Spring

>Spring中applicationContext和beanFactory区别
>
>如何加载bean
>
>常用注解
>
>动态代理模式有哪些角色,具体实现
>
>springboot内嵌Tomcat
>
>Spring拦截器和过滤器（知识盲区，平常都是用Filter,就很尴尬）。
>
>SpringMVC中的MVC代表什么
>
>spring的IOC和AOP使用了哪些Java底层的技术
>
>@Bean知道吗，有什么作用，@Configuration作用是啥？Spring是怎么加载@Configuration配置的类
>
>IoC实现过程
>
>ssm和springboot区别
>
>springboot如何处理异常
>
>spring支持哪些日志框架
>
>spring IoC和Aop
>
>springMVC流程
>
>@Aspect
>
>Spring的bean是怎么加载的？循环依赖怎么解决的？一二三级缓存
>
>Springboot和spring的区别

## 数据结构

>图的DFS和BFS

## mysql数据库

>数据库聚簇索引,非聚簇索引
>
>数据库事务acid以及底层实现
>
>数据库索引的原理
>
>es为什么比mysql快
>
>MySQL的结构
>关系型和非关系型数据库的区别
>
>如何优化一条SQL语句
>为什么SQL语句中的'非'操作性能低
>
>mysql特征
>
>如何保证原子性。通过undolog。介绍undolog。四种隔离级别的原理。
>
>数据库怎么实现重入
>
>update一条不存在的数据会怎么样
>
>悲观锁,乐观锁
>
>RC,RR,redolog
>
>为什么要使用索引
>
>最左前缀匹配原则
>
>mysql有哪些引擎,isam和InnoDB区别
>
>为什么不用b-树用B+树,区别
>
>索引的实现B+树
>
>SQL注入
>
>mysql联合索引
>
>判断where条件有没有启用
>
>mysql隔离级别,机制,会引起什么
>
>为什么默认不使用序列化
>
>脏读,幻读
>
>乐观锁,可重入锁
>
>数据库事务
>
>分页查询的过程是什么样的？
>
>当表很大的时候，有什么方法可以优化最后几页的查询？考虑数据只有逻辑删除，没有物理删除的情况，即，不考虑删除操作。
>
>索引覆盖知道吗？

## mybatis

>mybatis中#和$区别
>
>MyBatis是一个空接口+XML文件实现功能的，那么具体是怎么实现的？
>
>缓存一致性的问题

## redis

>redis分布式锁
>
>适用场景,
>
>数据结构
>
>zset
>
>缓存穿透,缓存雪崩
>
>基本数据结构
>
>底层原理
>
>redis 里hash数据结构，怎么给其中的一个 field 赋值？
>
>如何保证多次重复 post 操作只做一次？
>
>发布订阅模式

## linux

>grep怎么传多个参数
>
>硬链接和软连接区别
>
>常看当前主机IP,查看网络流量
>
>各种协议端口号
>
>网络端口怎么看？
>
>远程登陆的命令？
>
>如果要在日志中查一个关键字，可以怎么做？

## 设计模式

>单例模式
>
>熟悉哪个模式

## 手撕

>单例模式
>
>循环打印ABC

## 杂选

>docker
>
>项目
>
>什么是敏捷开发,如何保证质量
>
>了解tomcat吗？tomcat是什么，说说你的理解
>servlet是什么，和tomcat有什么关系
>
>socket为什么会阻塞--具体说明？状态的变化
>
>[项目](https://www.nowcoder.com/jump/super-jump/word?word=项目)中zoo[keep](https://www.nowcoder.com/jump/super-jump/word?word=keep)er用来干啥
>
>一个接口调用很慢,怎么进行排查(日志...)

## 算法

>二分
>
>快排
>
>反转二叉树
>
>二叉树前序遍历非递归
>
>DFS实现全排列

## 项目

>ajax怎么和后台进行交互
>
>如何解决跨域问题
>
>springcloud用到的组件



![image-20200929153724369](C:\Users\userhan\AppData\Roaming\Typora\typora-user-images\image-20200929153724369.png)![image-20200929153737682](C:\Users\userhan\AppData\Roaming\Typora\typora-user-images\image-20200929153737682.png)



## 不足

>动态代理
>
>IO
>
>网络编程
>
>反射