[TOC]

# 【别让宝宝输在起跑线上】版本配置

```
bennyrhysdeMacBook-Pro:bennyrhys bennyrhys$ java -version
java version "1.8.0_121"
Java(TM) SE Runtime Environment (build 1.8.0_121-b13)
Java HotSpot(TM) 64-Bit Server VM (build 25.121-b13, mixed mode)
bennyrhysdeMacBook-Pro:bennyrhys bennyrhys$ mvn -v
Apache Maven 3.6.3 (cecedd343002696d0abb50b32b541b8a6ba2883f)

IDEA旗舰版
温馨提醒：学生邮箱注册旗舰版免费使用

创建springboot2.2.2-》web模块

推荐：meaven镜像仓库
vim ~/.m2/settings.xml 
    <mirror>
      <id>alimaven</id>
      <name>aliyun maven</name>
      <url>http://maven.aliyun.com/nexus/content/groups/public/</url>
      <mirrorOf>central</mirrorOf>
    </mirror>
    
<!--配置profiles节点-->
  <profiles>
  <profile>
            <id>jdk-1.8</id>
            <activation>
                <jdk>1.8</jdk>
            </activation>
            <repositories>
                <repository>
                    <id>nexus</id>
                    <name>local private nexus</name>
                    <url>http://maven.aliyun.com/nexus/content/groups/public/</url>
                    <releases>
                        <enabled>true</enabled>
                    </releases>
                    <snapshots>
                        <enabled>false</enabled>
                    </snapshots>
                </repository>
            </repositories>
         
   </profile> 
```

# 【初识】第一个程序hello world

HelloController//新建controller

```java
package com.bennyrhys.girl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String say(){
        return "hello world";
    }
}
```

访问

http://localhost:8080/hello

输出

hello world

# 【一万个哈姆雷特】花式启动程序

>第二种启动方式：命令行meaven
>
>mvn spring-boot:run
>
>
>
>第三种启动方式：
>
>//编译程序
>
>mvn install
>
>//进入到target目录下 girl-0.0.1-SNAPSHOT.jar启动
>
>java -jar girl-0.0.1-SNAPSHOT.jar

# 【君子之交-淡如水】解放双手，配置先行

## 配置选型

application.properties

```
server.port=8081
server.servlet.context-path=/girl
```

application.yml

推荐，但注意衔接留空格

```
server:
  port: 8081
  servlet:
    context-path: /girl
```

http://localhost:8081/girl/hello

## 场景模拟-个人信息

girl程序，模拟人体特征，处理个人信息。

当今人类文明在生物及科技的进步，极大程度推动大数据发展，人类基因芯片计划也在不断推进完成。

随着人类[基因](https://baike.baidu.com/item/基因)组（测序）计划（ Human genome project ）的逐步实施以及分子生物学相关学科的迅猛发展，越来越多的动植物、微生物基因组序列得以测定，基因序列数据正在以前所未有的速度迅速增长。然而 , 怎样去研究如此众多基因在生命过程中所担负的功能就成了全世界生命科学工作者共同的课题。为此，建立新型杂交和[测序](https://baike.baidu.com/item/测序)方法以对大量的[遗传信息](https://baike.baidu.com/item/遗传信息)进行高效、快速的检测、分析就显得格外重要了。

## 单个配置

application.yml

```
server:
  port: 8080
  servlet:
    context-path: /girl

cupSize: B
age: 18
content: "cupSize:${cupSize},age:${age}" #引用配置中的配置
```

HelloController

```java
package com.bennyrhys.girl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    //通过注解获取配置中的属性
    @Value("${cupSize}")
    private String cupSize;
    @Value("${age}")
    private Integer age;
    @Value("${content}")
    private String content;


    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String say(){
        return cupSize+" "+age+"content："+content;
    }
}
```

访问

http://localhost:8080/girl/hello

输出

B 18contentcupSize:B,age:18

## git提交-单配置-master

>bennyrhysdeMacBook-Pro:Idea_Demo bennyrhys$ git clone https://github.com/bennyrhys/Girl-SpringBootProject.git girl
>
>Cloning into 'girl'...
>
>remote: Enumerating objects: 6, done.
>
>remote: Counting objects: 100% (6/6), done.
>
>remote: Compressing objects: 100% (4/4), done.
>
>remote: Total 6 (delta 1), reused 0 (delta 0), pack-reused 0
>
>Unpacking objects: 100% (6/6), done.
>
>
>
>

