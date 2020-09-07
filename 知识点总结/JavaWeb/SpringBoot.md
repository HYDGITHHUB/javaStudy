# SpringBoot

SM 框架整合的步骤非常繁琐、很麻烦，可以使用 Spring Boot 对 SSM 框架整合进行极大的简化。

Spring Boot 是 Spring 全家桶的一员，用来快速搭建基于 Spring 的 Java 应用，是一个脚手架框架，内部自动集成了 Spring 的所有产品，以及主流的第三方框架，只需要开箱即用，不需要进行配置。

基于 Spring 框架的，完成自动装配

Spring 提供了 IoC 容器，开发者不需要手动创建程序中所需要的 bean 了，需要开发者手动进行配置，spring.xml 配置，IoC 容器会自动根据配置文件创建各种 bean。

Spring Boot 可以自动创建各种 bean，开发者不需要手动配置 spring.xml

Spring 管理 MyBatis

MyBatis 需要的 bean

- DataSource
- SqlSessionFactory
- Mapper 代理

Spring 创建 MyBatis 所需要的 bean，开发者手动进行 bean 配置，开发者只是不用自己创建 bean，但是 bean 的配置还是需要开发者来完成。

不使用 Spring

1、自己造零件

2、自己组装

使用 Spring

不需要自己造零件了，Spring IoC 制作零件，但是需要开发者给出配置文件 spring.xml，IoC 根据配置文件制作零件，开发者只需要组装即可。

Spring VS Spring Boot

Spring 的功能，开发者不需要自己创建程序中需要用的 bean，只需要在 spring.xml 进行配置，然后交给 Spring 框架来读取配置，进而创建各种 bean，构建起整个程序。

Spring Boot 是在 Spring 的基础上进一步优化了 IoC 机制，自动配置，开发者不需要手动配置了。

不使用 Spring -- 使用 Spring -- 使用 Spring Boot

纯手工 -- 半自动化（只配置不需要创建）-- 全自动化（不需要配置）

因为 Spring Boot 已经将 Java 领域所有的主流框架全部进行了集成，在你使用之前，Spring Boot 已经整合好了，你只需要使用的时候把你需要的框架导入即可，只要导入就能使用，因为 Spring Boot 已经自动进行了配置。

## 1. Spring Boot VS SSM

1、不需要手动添加各种 pom.xml 的配置，全部都是在可视化工具中选择。

![image-20200809001107374](C:\Users\userhan\AppData\Roaming\Typora\typora-user-images\image-20200809001107374.png)

2、不需要任何的 XML 文件，个性化直接写入 application.yml 中

![image-20200809001122018](C:\Users\userhan\AppData\Roaming\Typora\typora-user-images\image-20200809001122018.png)

![image-20200809001137613](C:\Users\userhan\AppData\Roaming\Typora\typora-user-images\image-20200809001137613.png)

application.yml

![image-20200809001147824](C:\Users\userhan\AppData\Roaming\Typora\typora-user-images\image-20200809001147824.png)

3、不需要配置 Tomcat，直接启动 Application 即可，因为 Spring Boot 内嵌了一个 Tomcat 容器。

>为什么SpringBoot项目自动注入mapper的时候idea报红

MyBatis 的 Mapper 是动态代理机制，开发者只需要提供接口和对应的 Mapper.xml，MyBatis 会自动根据接口和配置文件动态创建实现类，进而创建 bean。

**动态** 程序运行期间创建

IDEA 是无法识别到 bean

## 2. SpringBoot 自动装配机制

Spring Boot 是一个快速搭建基于 Spring 框架的 Java 应用的脚手架，Spring Boot 本身没有特定的业务（MVC、ORM），就是快速搭建工程，方便开发。

Spring Boot 已经将 Java 领域所有主流的技术栈全部进行了集成，自动封装，开发者需要使用的话就不需要手动进行配置，直接“开箱即用”。

Spring Boot 工具包，需要什么，取什么即可，不同框架之间的整合 Spring Boot 自动完成。

### 2.1 @SpringBootApplication

Spring Boot 的核心注解 @SpringBootApplication，是整个 Spring Boot 启动的入口。

```java
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(
    excludeFilters = {@Filter(
    type = FilterType.CUSTOM,
    classes = {TypeExcludeFilter.class}
), @Filter(
    type = FilterType.CUSTOM,
    classes = {AutoConfigurationExcludeFilter.class}
)}
)
public @interface SpringBootApplication {
```

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited

是元注解，就是用来描述注解的注解，@SpringBootApplication 其实是由 3 个注解组成：

@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan

#### 2.1.1 @SpringBootConfiguration

@SpringBootConfiguration 注解其实就是 @Configuration，@Configuration 是 Spring Boot 中使用频率最高的注解。

@Configuration 注解的作用？

实现自动配置

@Configuration 注解的作用是将一个 Java 类标注成为一个配置类。

什么是配置类？

用 Java 类的形式来替代之前的 XML 配置文件。

Spring Boot 中没有一个 XML 文件，因为 XML 配置文件的功能全部被配置类替换了，自动配置。

传统的 IoC 配置 XML

```java
public class Account{
	private Integer id;
	private String name;
}
```

```xml
<beans>
	<bean id="account" class="com.entity.Account">
		<property name="id" value="1"/>
		<property name="name" value="张三"/>
	</bean>
</beans>
```

@Configuration

@Bean如果不配做 value，默认的 id 是方法名 getAccount

```java
@Configuration
public class MyConfiguration {

    @Bean(value = "abc")
    public Account getAccount(){
        return new Account(1,"张三","123123",22, new Date(),new Date());
    }

}
```

等于

```xml
<beans>
	<bean id="abc" class="com.entity.Account">
		<property name="id" value="1"/>
		<property name="name" value="张三"/>
        ...
	</bean>
</beans>
```

##### 2.1..1.1 @ConfigurationProperties

@ConfigurationProperties可以直接读取 application.yml 文件中的数据并且封装到 bean 中。

```java
package com.southwind.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "people")
public class People {
    private Integer id;
    private String name;
    private String tel;
}
```

```yml
people:
  id: 11
  name: 张三
  tel: 13678787878
```

#### 2.1.2 @ComponentScan

替代 XML 中的自动扫包	

![image-20200812111010851](C:\Users\userhan\AppData\Roaming\Typora\typora-user-images\image-20200812111010851.png)

该注解的作用是自动扫描并加载符合条件的 bean，通过设置 basePackage 属性的值来指定需要扫描的根目录，该目录下的类及其子目录下的类都会被扫描。

@ComponetScan(basePackage="com.southwind")

如果没有显示设置 basePackage 的值，默认值就是添加了该注解的类所在的包。

启动类一定要放在父包中，所有的业务类一定要被启动类辐射到，否则无法注入 IoC。

#### 2.1.3 @EnableAutoConfiguration

是自动配置的核心，非常重要，由两个注解构成

```java
@AutoConfigurationPackage
@Import({AutoConfigurationImportSelector.class})
```

@AutoConfigurationPackage 根据包进行自动装配，底层是 @Import({Registrar.class})

@Import 注解的作用是根据参数中类所返回的信息，将对应的 bean 进行注入

@Import(A.class)

A 类中会返回一部分 bean 信息，@Import 就会将这些 bean 进行注入。

Spring Boot 自动装载的 bean 包括两部分内容：

- 开发者自定义的组件 controller、service、mapper、myconfiguration
- 框架自带组件

spring.factories 配置文件，框架自带的组件都是在这个配置文件中定义的。

```xml
org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
org.springframework.boot.autoconfigure.admin.SpringApplicationAdminJmxAutoConfiguration,\
org.springframework.boot.autoconfigure.aop.AopAutoConfiguration,\
org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration,\
org.springframework.boot.autoconfigure.batch.BatchAutoConfiguration,\
org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration,\
org.springframework.boot.autoconfigure.cassandra.CassandraAutoConfiguration,\
org.springframework.boot.autoconfigure.context.ConfigurationPropertiesAutoConfiguration,\
org.springframework.boot.autoconfigure.context.LifecycleAutoConfiguration,\
org.springframework.boot.autoconfigure.context.MessageSourceAutoConfiguration,\
org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration,\
org.springframework.boot.autoconfigure.couchbase.CouchbaseAutoConfiguration,\
org.springframework.boot.autoconfigure.dao.PersistenceExceptionTranslationAutoConfiguration,\
org.springframework.boot.autoconfigure.data.cassandra.CassandraDataAutoConfiguration,\
org.springframework.boot.autoconfigure.data.cassandra.CassandraReactiveDataAutoConfiguration,\
org.springframework.boot.autoconfigure.data.cassandra.CassandraReactiveRepositoriesAutoConfiguration,\
org.springframework.boot.autoconfigure.data.cassandra.CassandraRepositoriesAutoConfiguration,\
org.springframework.boot.autoconfigure.data.couchbase.CouchbaseDataAutoConfiguration,\
org.springframework.boot.autoconfigure.data.couchbase.CouchbaseReactiveDataAutoConfiguration,\
org.springframework.boot.autoconfigure.data.couchbase.CouchbaseReactiveRepositoriesAutoConfiguration,\
org.springframework.boot.autoconfigure.data.couchbase.CouchbaseRepositoriesAutoConfiguration,\
org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataAutoConfiguration,\
org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchRepositoriesAutoConfiguration,\
org.springframework.boot.autoconfigure.data.elasticsearch.ReactiveElasticsearchRepositoriesAutoConfiguration,\
org.springframework.boot.autoconfigure.data.elasticsearch.ReactiveElasticsearchRestClientAutoConfiguration,\
org.springframework.boot.autoconfigure.data.jdbc.JdbcRepositoriesAutoConfiguration,\
org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration,\
org.springframework.boot.autoconfigure.data.ldap.LdapRepositoriesAutoConfiguration,\
org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration,\
org.springframework.boot.autoconfigure.data.mongo.MongoReactiveDataAutoConfiguration,\
org.springframework.boot.autoconfigure.data.mongo.MongoReactiveRepositoriesAutoConfiguration,\
org.springframework.boot.autoconfigure.data.mongo.MongoRepositoriesAutoConfiguration,\
org.springframework.boot.autoconfigure.data.neo4j.Neo4jDataAutoConfiguration,\
org.springframework.boot.autoconfigure.data.neo4j.Neo4jRepositoriesAutoConfiguration,\
org.springframework.boot.autoconfigure.data.solr.SolrRepositoriesAutoConfiguration,\
org.springframework.boot.autoconfigure.data.r2dbc.R2dbcDataAutoConfiguration,\
org.springframework.boot.autoconfigure.data.r2dbc.R2dbcRepositoriesAutoConfiguration,\
org.springframework.boot.autoconfigure.data.r2dbc.R2dbcTransactionManagerAutoConfiguration,\
org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration,\
org.springframework.boot.autoconfigure.data.redis.RedisReactiveAutoConfiguration,\
org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration,\
org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration,\
org.springframework.boot.autoconfigure.data.web.SpringDataWebAutoConfiguration,\
org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchRestClientAutoConfiguration,\
org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration,\
org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration,\
org.springframework.boot.autoconfigure.groovy.template.GroovyTemplateAutoConfiguration,\
org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration,\
org.springframework.boot.autoconfigure.h2.H2ConsoleAutoConfiguration,\
org.springframework.boot.autoconfigure.hateoas.HypermediaAutoConfiguration,\
org.springframework.boot.autoconfigure.hazelcast.HazelcastAutoConfiguration,\
org.springframework.boot.autoconfigure.hazelcast.HazelcastJpaDependencyAutoConfiguration,\
org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration,\
org.springframework.boot.autoconfigure.http.codec.CodecsAutoConfiguration,\
org.springframework.boot.autoconfigure.influx.InfluxDbAutoConfiguration,\
org.springframework.boot.autoconfigure.info.ProjectInfoAutoConfiguration,\
org.springframework.boot.autoconfigure.integration.IntegrationAutoConfiguration,\
org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration,\
org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration,\
org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration,\
org.springframework.boot.autoconfigure.jdbc.JndiDataSourceAutoConfiguration,\
org.springframework.boot.autoconfigure.jdbc.XADataSourceAutoConfiguration,\
org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration,\
org.springframework.boot.autoconfigure.jms.JmsAutoConfiguration,\
org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration,\
org.springframework.boot.autoconfigure.jms.JndiConnectionFactoryAutoConfiguration,\
org.springframework.boot.autoconfigure.jms.activemq.ActiveMQAutoConfiguration,\
org.springframework.boot.autoconfigure.jms.artemis.ArtemisAutoConfiguration,\
org.springframework.boot.autoconfigure.jersey.JerseyAutoConfiguration,\
org.springframework.boot.autoconfigure.jooq.JooqAutoConfiguration,\
org.springframework.boot.autoconfigure.jsonb.JsonbAutoConfiguration,\
org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration,\
org.springframework.boot.autoconfigure.availability.ApplicationAvailabilityAutoConfiguration,\
org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapAutoConfiguration,\
org.springframework.boot.autoconfigure.ldap.LdapAutoConfiguration,\
org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration,\
org.springframework.boot.autoconfigure.mail.MailSenderAutoConfiguration,\
org.springframework.boot.autoconfigure.mail.MailSenderValidatorAutoConfiguration,\
org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration,\
org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration,\
org.springframework.boot.autoconfigure.mongo.MongoReactiveAutoConfiguration,\
org.springframework.boot.autoconfigure.mustache.MustacheAutoConfiguration,\
org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration,\
org.springframework.boot.autoconfigure.quartz.QuartzAutoConfiguration,\
org.springframework.boot.autoconfigure.r2dbc.R2dbcAutoConfiguration,\
org.springframework.boot.autoconfigure.rsocket.RSocketMessagingAutoConfiguration,\
org.springframework.boot.autoconfigure.rsocket.RSocketRequesterAutoConfiguration,\
org.springframework.boot.autoconfigure.rsocket.RSocketServerAutoConfiguration,\
org.springframework.boot.autoconfigure.rsocket.RSocketStrategiesAutoConfiguration,\
org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration,\
org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration,\
org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration,\
org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration,\
org.springframework.boot.autoconfigure.security.reactive.ReactiveUserDetailsServiceAutoConfiguration,\
org.springframework.boot.autoconfigure.security.rsocket.RSocketSecurityAutoConfiguration,\
org.springframework.boot.autoconfigure.security.saml2.Saml2RelyingPartyAutoConfiguration,\
org.springframework.boot.autoconfigure.sendgrid.SendGridAutoConfiguration,\
org.springframework.boot.autoconfigure.session.SessionAutoConfiguration,\
org.springframework.boot.autoconfigure.security.oauth2.client.servlet.OAuth2ClientAutoConfiguration,\
org.springframework.boot.autoconfigure.security.oauth2.client.reactive.ReactiveOAuth2ClientAutoConfiguration,\
org.springframework.boot.autoconfigure.security.oauth2.resource.servlet.OAuth2ResourceServerAutoConfiguration,\
org.springframework.boot.autoconfigure.security.oauth2.resource.reactive.ReactiveOAuth2ResourceServerAutoConfiguration,\
org.springframework.boot.autoconfigure.solr.SolrAutoConfiguration,\
org.springframework.boot.autoconfigure.task.TaskExecutionAutoConfiguration,\
org.springframework.boot.autoconfigure.task.TaskSchedulingAutoConfiguration,\
org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration,\
org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration,\
org.springframework.boot.autoconfigure.transaction.jta.JtaAutoConfiguration,\
org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration,\
org.springframework.boot.autoconfigure.web.client.RestTemplateAutoConfiguration,\
org.springframework.boot.autoconfigure.web.embedded.EmbeddedWebServerFactoryCustomizerAutoConfiguration,\
org.springframework.boot.autoconfigure.web.reactive.HttpHandlerAutoConfiguration,\
org.springframework.boot.autoconfigure.web.reactive.ReactiveWebServerFactoryAutoConfiguration,\
org.springframework.boot.autoconfigure.web.reactive.WebFluxAutoConfiguration,\
org.springframework.boot.autoconfigure.web.reactive.error.ErrorWebFluxAutoConfiguration,\
org.springframework.boot.autoconfigure.web.reactive.function.client.ClientHttpConnectorAutoConfiguration,\
org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientAutoConfiguration,\
org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration,\
org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryAutoConfiguration,\
org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration,\
org.springframework.boot.autoconfigure.web.servlet.HttpEncodingAutoConfiguration,\
org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration,\
org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration,\
org.springframework.boot.autoconfigure.websocket.reactive.WebSocketReactiveAutoConfiguration,\
org.springframework.boot.autoconfigure.websocket.servlet.WebSocketServletAutoConfiguration,\
org.springframework.boot.autoconfigure.websocket.servlet.WebSocketMessagingAutoConfiguration,\
org.springframework.boot.autoconfigure.webservices.WebServicesAutoConfiguration,\
org.springframework.boot.autoconfigure.webservices.client.WebServiceTemplateAutoConfiguration
```

这个配置文件中的类全部都是配置类，这些配置类就是 Spring Boot 框架默认加载的系统组件。

![image-20200812111132829](C:\Users\userhan\AppData\Roaming\Typora\typora-user-images\image-20200812111132829.png)

## 3. SpringBoot 自己定义 Starter

Spring Boot 是一个脚手架框架，快速构建 Spring 应用，自动装配，已经将主流的 Java 框架进行了自动集成，需要使用某个框架，只需要将该框架的组件引入工程即可，Spring Boot 会自动进行装载。

starter

每一个可以集成到 Spring Boot 的框架都有一个 starter，有两种 starter

- Spring 全家桶组件 Spring MVC 

  ```
  spring-boot-starter-web
  ```

- 第三方组件 MyBatis

  ```
  mybatis-spring-boot-starter
  ```

如果是 Spring 全家桶的产品，spring-boot-starter-***，

如果是第三方的框架 ***-spring-boot-starter

Spring Boot 之前 1、导入 jar 2、进行配置

Spring Boot 只需要导入 starter 启动器，自动配置

自定义 Starter 的创建

1、创建一个 Spring Boot 应用，完成各种自动配置的逻辑。

2、打包 jar

3、部署到本地 Maven 仓库

> 具体实现

1、创建 Spring Boot 应用，pom.xml 

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-autoconfigure</artifactId>
</dependency>
```

2、创建 Service

```java
package com.southwind.service;

public class Service {
    private String prefix;
    private String suffix;

    public Service(String prefix, String suffix) {
        this.prefix = prefix;
        this.suffix = suffix;
    }

    public String service(String value){
        return this.prefix + value + this.suffix;
    }
}
```

3、创建 ServicePropeties 读取配置信息

```java
package com.southwind.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "southwind.service")
public class ServiceProperties {
    private String prefix;
    private String suffix;
}
```

4、创建自动配置类 AutoConfigure

```java
package com.southwind.configure;

import com.southwind.properties.ServiceProperties;
import com.southwind.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(Service.class)
@EnableConfigurationProperties(ServiceProperties.class)
public class AutoConfigure {

    @Autowired
    private ServiceProperties serviceProperties;

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "southwind.service",value = "enable",havingValue = "true")
    public Service service(){
        return new Service(serviceProperties.getPrefix(), serviceProperties.getSuffix());
    }
}
```



5、创建 spring.factories

```properties
# Auto Configure
org.springframework.boot.autoconfigure.EnableAutoConfiguration=com.southwind.configure.AutoConfigure
```

> 打包

![image-20200822154955514](C:\Users\userhan\AppData\Roaming\Typora\typora-user-images\image-20200822154955514.png)![image-20200822155010663](C:\Users\userhan\AppData\Roaming\Typora\typora-user-images\image-20200822155010663.png)![image-20200822155021712](C:\Users\userhan\AppData\Roaming\Typora\typora-user-images\image-20200822155021712.png)

3、将 jar 安装到本地 Maven 仓库，在终端执行命令。

```shell
mvn install:install-file 
-DgroupId=com.southwind 
-DartifactId=demo_starter 
-Dversion=1.2.2 
-Dpackaging=jar 
-Dfile=D:\WorkSpace\IdeaProjects\mystarter002\out\artifacts\mystarter002_jar\mystarter002.jar
```

