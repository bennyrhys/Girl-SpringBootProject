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
>
>//启动时配置切换生产环境
>
>java -jar target/girl-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod





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

cupSize: B #罩杯
age: 18 #年龄
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
>bennyrhysdeMacBook-Pro:Idea_Demo bennyrhys$ cd Girl-SpringBootProject/
>
>bennyrhysdeMacBook-Pro:Girl-SpringBootProject bennyrhys$ git status
>
>On branch master
>
>Your branch is up to date with 'origin/master'.
>
>
>
>Untracked files:
>
> (use "git add <file>..." to include in what will be committed)
>
>
>
>​	girl/
>
>​	girl_note.md
>
>
>
>nothing added to commit but untracked files present (use "git add" to track)
>
>bennyrhysdeMacBook-Pro:Girl-SpringBootProject bennyrhys$ git remote
>
>origin
>
>bennyrhysdeMacBook-Pro:Girl-SpringBootProject bennyrhys$ git add .
>
>bennyrhysdeMacBook-Pro:Girl-SpringBootProject bennyrhys$ git commit -m "单配置"
>
>[master 8545dc9] 单配置
>
> 7 files changed, 337 insertions(+)
>
> create mode 100644 girl/.gitignore
>
> create mode 100644 girl/pom.xml
>
> create mode 100644 girl/src/main/java/com/bennyrhys/girl/GirlApplication.java
>
> create mode 100644 girl/src/main/java/com/bennyrhys/girl/HelloController.java
>
> create mode 100644 girl/src/main/resources/application.yml
>
> create mode 100644 girl/src/test/java/com/bennyrhys/girl/GirlApplicationTests.java
>
> create mode 100644 girl_note.md
>
>bennyrhysdeMacBook-Pro:Girl-SpringBootProject bennyrhys$ git branch
>
>\* master
>
>bennyrhysdeMacBook-Pro:Girl-SpringBootProject bennyrhys$ git push origin master
>
>Enumerating objects: 23, done.
>
>Counting objects: 100% (23/23), done.
>
>Delta compression using up to 4 threads
>
>Compressing objects: 100% (14/14), done.
>
>Writing objects: 100% (22/22), 4.80 KiB | 2.40 MiB/s, done.
>
>Total 22 (delta 0), reused 0 (delta 0)
>
>To https://github.com/bennyrhys/Girl-SpringBootProject.git
>
>  d4be8b7..8545dc9 master -> master

## 多个配置

application.yml

```
server:
  port: 8080
  servlet:
    context-path: /girl
girl:
  cupSize: B
  age: 18
```

GirlConfig //新建配置文件

```java
package com.bennyrhys.girl;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

//对应配置文件的girl
@ConfigurationProperties(prefix = "girl")
//注入配置
@Component
public class GirlConfig {
    private String cupSize;
    private Integer age;

    public String getCupSize() {
        return cupSize;
    }

    public void setCupSize(String cupSize) {
        this.cupSize = cupSize;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
```

HelloController

```java
package com.bennyrhys.girl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    //通过注解获取配置:类中的属性
    @Autowired
    private GirlConfig girlConfig;



    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String say(){
        return girlConfig.getCupSize()+girlConfig.getAge();
    }
}
```

访问

http://localhost:8080/girl/hello

输出

B18

## git提交-多配置-master

>bennyrhysdeMacBook-Pro:Girl-SpringBootProject bennyrhys$ git add .
>
>bennyrhysdeMacBook-Pro:Girl-SpringBootProject bennyrhys$ git commit -m "多配置"
>
>[master 167fc1a] 多配置
>
> 4 files changed, 195 insertions(+), 14 deletions(-)
>
> create mode 100644 girl/src/main/java/com/bennyrhys/girl/GirlConfig.java
>
>bennyrhysdeMacBook-Pro:Girl-SpringBootProject bennyrhys$ git push origin master
>
>Enumerating objects: 26, done.
>
>Counting objects: 100% (26/26), done.
>
>Delta compression using up to 4 threads
>
>Compressing objects: 100% (10/10), done.
>
>Writing objects: 100% (14/14), 2.37 KiB | 1.18 MiB/s, done.
>
>Total 14 (delta 3), reused 0 (delta 0)
>
>remote: Resolving deltas: 100% (3/3), completed with 3 local objects.
>
>To https://github.com/bennyrhys/Girl-SpringBootProject.git
>
>  8545dc9..167fc1a master -> master

# 【我的命，我自己说的才算】生产/开发环境切换

## 场景模拟

dev：生产环境，先天cupSzie:B

Prod:开发环境，后天想达到cupSize:F

## 配置

application-dev.yml

```
server:
  port: 8081
  servlet:
    context-path: /girl
girl:
  cupSize: B
  age: 18
```

application-prod.yml

```
server:
  port: 8081
  servlet:
    context-path: /girl
girl:
  cupSize: F
  age: 18
```

application.yml

```
spring:
  profiles:
    active: prod
```

## 验证

访问

http://localhost:8082/girl/hello

输出

F18

## git提交-生产环境切换

>bennyrhysdeMacBook-Pro:girl bennyrhys$ git add .
>
>bennyrhysdeMacBook-Pro:girl bennyrhys$ git commit -m "生产环境切换"
>
>[master 3c7eca6] 生产环境切换
>
> 3 files changed, 17 insertions(+), 7 deletions(-)
>
> create mode 100644 girl/src/main/resources/application-dev.yml
>
> create mode 100644 girl/src/main/resources/application-prod.yml
>
>bennyrhysdeMacBook-Pro:girl bennyrhys$ git push origin master
>
>Enumerating objects: 15, done.
>
>Counting objects: 100% (15/15), done.
>
>Delta compression using up to 4 threads
>
>Compressing objects: 100% (8/8), done.
>
>Writing objects: 100% (9/9), 830 bytes | 830.00 KiB/s, done.
>
>Total 9 (delta 1), reused 0 (delta 0)
>
>remote: Resolving deltas: 100% (1/1), completed with 1 local object.
>
>To https://github.com/bennyrhys/Girl-SpringBootProject.git
>
>  167fc1a..3c7eca6 master -> master

#【人生若只如初见】从“程序”入口Controller开始

| @Controller     | 处理http请求                                                 |
| --------------- | ------------------------------------------------------------ |
| @RestController | Spring4之后新加的注解<br />原来返回json需要@ResponseBody配合@Controller |
| @RequestMapping | 配置url映射                                                  |

## @controller+thymeleaf

@controller单独写，访问时会找不到模版，必须在pom中添加thymeleaf模版依赖

pom.xml

```xml
<!--        添加thymeleaf依赖-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-thymeleaf</artifactId>
        </dependency>
```

HelloController

```java
package com.bennyrhys.girl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HelloController {
    //通过注解获取配置:类中的属性
    @Autowired
    private GirlConfig girlConfig;



    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String say(){
//        return girlConfig.getCupSize()+girlConfig.getAge();
        return "index";
    }
}
```

访问：

http://localhost:8081/girl/hello

输出：

hello thymeleaf

## git提交-@controller+thymeleaf

>bennyrhysdeMacBook-Pro:girl bennyrhys$ git add .
>
>bennyrhysdeMacBook-Pro:girl bennyrhys$ git commit -m "@controller+thymeleaf"
>
>[master 3d23c58] @controller+thymeleaf
>
> 5 files changed, 22 insertions(+), 4 deletions(-)
>
> create mode 100644 girl/src/main/resources/templates/index.html
>
>bennyrhysdeMacBook-Pro:girl bennyrhys$ git push origin master
>
>Enumerating objects: 29, done.
>
>Counting objects: 100% (29/29), done.
>
>Delta compression using up to 4 threads
>
>Compressing objects: 100% (12/12), done.
>
>Writing objects: 100% (16/16), 1.35 KiB | 692.00 KiB/s, done.
>
>Total 16 (delta 4), reused 0 (delta 0)
>
>remote: Resolving deltas: 100% (4/4), completed with 4 local objects.
>
>To https://github.com/bennyrhys/Girl-SpringBootProject.git
>
>  3c7eca6..3d23c58 master -> master



## @RestController=@ResponseBody+@Controller

主要作用见标题

其次可以用在类上、方法体上

```java
package com.bennyrhys.girl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
//@ResponseBody
@Controller
public class HelloController {
    //通过注解获取配置:类中的属性
    @Autowired
    private GirlConfig girlConfig;


    @ResponseBody
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String say(){
        return girlConfig.getCupSize()+girlConfig.getAge();
//        return "index";
    }
}
```

访问

http://localhost:8081/girl/hello

输出

F18

## girl/hello ==girl/hi

快捷键：查看列表可用参数command+p

```
@RequestMapping(value = {"/hello","/hi"}, method = RequestMethod.GET)
```

http://localhost:8081/girl/hi

F18

http://localhost:8081/girl/hello

F18



## url访问一层girl/say/hi

注解@RequestMapping("/say")

HelloController

```java
package com.bennyrhys.girl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/say")
public class HelloController {
    //通过注解获取配置:类中的属性
    @Autowired
    private GirlConfig girlConfig;


    @RequestMapping(value = {"/hello","/hi"}, method = RequestMethod.GET)
    public String say(){
        return girlConfig.getCupSize()+girlConfig.getAge();
//        return "index";
    }
}
```

http://localhost:8081/girl/say/hi

F18

## Post访问

```java
@RequestMapping(value = {"/hello","/hi"}, method = RequestMethod.POST)
```

页面无法直接访问，用postman

## get/post都可访问（不推荐）

@RequestMapping(value = {"/hello","/hi"})

具体业务场景，要严谨

## url参数获取

| @PathVariable | 获取url中数据    |
| ------------- | ---------------- |
| @RequestParam | 获取请求参数的值 |
| @GetMapping   | 组合注解         |

## @PathVariable:url中数据

HelloController

@RequestMapping(value = {"/hi/{id}"}，id在前后都行

```
package com.bennyrhys.girl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/say")
public class HelloController {
    //通过注解获取配置:类中的属性
    @Autowired
    private GirlConfig girlConfig;

    @RequestMapping(value = {"/hi/{id}"}, method = RequestMethod.GET)
    public String say(@PathVariable("id") Integer id){
//        return girlConfig.getCupSize()+girlConfig.getAge();
        return "id："+id;
    }
}
```

http://localhost:8081/girl/say/hi/99

id：99

## @RequestParam：请求参数

```
@RequestParam("id") Integer id  //这参数的两个id不用对应，前者等同于url的id
```

HelloController

```java
package com.bennyrhys.girl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/say")
public class HelloController {
    //通过注解获取配置:类中的属性
    @Autowired
    private GirlConfig girlConfig;

    @RequestMapping(value = {"/hi"}, method = RequestMethod.GET)
    public String say(@RequestParam("id") Integer myid){
//        return girlConfig.getCupSize()+girlConfig.getAge();
        return "id："+myid;
    }
}
```

http://localhost:8081/girl/say/hi?id=99

id：99

## 空id，设置默认值

http://localhost:8081/girl/say/hi?id=

id：null

http://localhost:8081/girl/say/hi

 (type=Bad Request, status=400).

设置默认值

```java
//required = false 是否必传
public String say(@RequestParam(value = "id", required = false, defaultValue = "0") Integer myid){
```

http://localhost:8081/girl/say/hi

id：0

## 简化请求注解

http://localhost:8081/girl/say/hi?id=2

id：2

有默认值

http://localhost:8081/girl/say/hi

id：0

**mapping是一个系列get、post、put**

```java
//    @RequestMapping(value = {"/hi"}, method = RequestMethod.GET)
    @GetMapping(value = "/hi")
```

HelloController

```java
package com.bennyrhys.girl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/say")
public class HelloController {
    //通过注解获取配置:类中的属性
    @Autowired
    private GirlConfig girlConfig;

//    @RequestMapping(value = {"/hi"}, method = RequestMethod.GET)
    @GetMapping(value = "/hi")

    public String say(@RequestParam(value = "id", required = false, defaultValue = "0") Integer myid){
//        return girlConfig.getCupSize()+girlConfig.getAge();
        return "id："+myid;
    }
}
```

## git提交-controller

>bennyrhysdeMacBook-Pro:girl bennyrhys$ git add .
>
>bennyrhysdeMacBook-Pro:girl bennyrhys$ git commit -m "controller"
>
>[master b0517fe] controller
>
> 1 file changed, 7 insertions(+), 9 deletions(-)
>
>bennyrhysdeMacBook-Pro:girl bennyrhys$ git push origin master
>
>Enumerating objects: 19, done.
>
>Counting objects: 100% (19/19), done.
>
>Delta compression using up to 4 threads
>
>Compressing objects: 100% (7/7), done.
>
>Writing objects: 100% (10/10), 885 bytes | 885.00 KiB/s, done.
>
>Total 10 (delta 3), reused 0 (delta 0)
>
>remote: Resolving deltas: 100% (3/3), completed with 3 local objects.
>
>To https://github.com/bennyrhys/Girl-SpringBootProject.git
>
>  3d23c58..b0517fe master -> master

# 【从删库到跑路】撸程序-操作数据库

## spring-data-jpa+mysql

java客户端：spring-data-jpa

jpa是对象持久化标准

实现这标准hibernate、toplink等

spring-data-jpa，是Spring对hibernate整合



数据库：mysql

## RESTful API设计

| 请求类型 | 请求路径  | 功能               |
| -------- | --------- | ------------------ |
| get      | /girls    | 获取女生列表       |
| post     | /girls    | 创建一个女生       |
| get      | /girls/id | 通过id查询一个女生 |
| put      | /girls/id | 通过id更新一个女生 |
| delete   | /girls/id | 通过id删除一个女生 |

## pom依赖添加 jpa+mysql

```xml
<!--        添加jpa+mysql-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>
```

Error:(1, 1) java: 无法访问com.bennyrhys
  Error reading file /Users/bennyrhys/.m2/repository/org/springframework/boot/spring-boot-starter-data-jpa/2.2.2.RELEASE/spring-boot-starter-data-jpa-2.2.2.RELEASE.jar: error in opening zip file

## yml配置mysql驱动+jpa创建表

```
server:
  port: 8081
  servlet:
    context-path: /girl

girl:
  cupSize: B
  age: 18
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/girl?characterEncoding=utf-8
    username: root
    password: rootroot
  jpa:
    hibernate:
      ddl-auto: create #update第一次创建，后来如果有数据会保留
    show-sql: true
```

## Girl //表字段

构造方法/get/set，千万不要导包错误

```java
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//表示对应数据库对表
@Entity
public class Girl {
    @Id //指定唯一id
    @GeneratedValue  //自增
    private Integer id;
    private String cupSize;
    private Integer age;
```

## Dao层：操作数据库

GirlRepository

```java
package com.bennyrhys.girl;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * dao
 * 注意命名，继承jpa，参数<字段类，唯一id类型>
 * extends JpaRepository<Girl,Integer>
 */
public interface GirlRepository extends JpaRepository<Girl,Integer> {
    //通过年龄来查询，方法名有讲究
    public List<Girl> findByAge(Integer age);
}
```

## controller实现增删改查

GirlController

```java
package com.bennyrhys.girl;

import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class GirlController {

    @Autowired
    private GirlRepository repository;

    /**
     * 查询所有女生列表
     * @return
     * http://localhost:8081/girl/girls
     * [
     *     {
     *         "id": 1,
     *         "cupSize": "B",
     *         "age": 18
     *     }
     * ]
     */
    @GetMapping(value = "/girls")
    public List<Girl> girlList(){
        return repository.findAll();
    }
    /**
     * 新增一个女生
     * http://localhost:8081/girl/girls
     * 注意：post请求
     *
     * 参数
     * cupSize  f
     * age  16
     *
     * 插入因为自增注意id冲突
     */
    @PostMapping(value = "/girls")
    public Girl girlAdd(@RequestParam("cupSize") String cupSize,
                          @RequestParam("age") Integer age){
        Girl girl = new Girl();
        girl.setCupSize("F");
        girl.setAge(16);
        //save返回添加对对象
        return repository.save(girl);
    }
    /**
     * 通过id查询一个女生
     * @return
     * http://localhost:8081/girl/girl/2
     * {
     *     "id": 2,
     *     "cupSize": "F",
     *     "age": 16
     * }
     */
    @GetMapping(value = "/girls/{id}")
    public Girl girlFindById(@PathVariable("id") Integer id){
        return repository.findById(id).orElse(null);

    }

    /**
     * 更新某个id女生基因信息
     * http://localhost:8081/girl/girls/2
     * 参数
     * cupSize  G
     * age  22
     *
     * {
     *     "id": 2,
     *     "cupSize": "G",
     *     "age": 22
     * }
     */
    @PutMapping(value = "/girls/{id}")
    public Girl updateGirl(@PathVariable("id") Integer id,
                           @RequestParam("cupSize") String cupSize,
                           @RequestParam("age") Integer age){
        Optional<Girl> optional = repository.findById(id);
        if (optional.isPresent()){
            Girl girl = optional.get();
            girl.setAge(age);
            girl.setCupSize(cupSize);
            return girl;
        }
        return null;
    }
    /**
     *删除某个id女生的信息
     * http://localhost:8081/girl/girls/2
     * 2
     */
    @DeleteMapping(value = "/girls/{id}")
    public Integer deleteGirlById(@PathVariable("id") Integer id){
        Optional<Girl> optional = repository.findById(id);
        if (optional.isPresent()){
            Girl girl = optional.get();
            repository.delete(girl);
            return id;
        }
        return null;
    }
    /**
     * 通过年龄查询女生列表
     * 先自定义dao方法
     * http://localhost:8081/girl/girls/age/16
     * [
     *     {
     *         "id": 3,
     *         "cupSize": "F",
     *         "age": 16
     *     },
     *     {
     *         "id": 4,
     *         "cupSize": "g",
     *         "age": 16
     *     },
     */
    @GetMapping("/girls/age/{age}")
    public List<Girl> girlListByAge(@PathVariable("age") Integer age){
        return repository.findByAge(age);
    }


}
```

# 【搞定并发就在一起】事务要么都完成要么都不做

## 使用场景

当某个名声显赫的家族要新增整个家族的女性优质基因时，为了事情不败露，必须保证同时实现基因的改变。

事务：要么都完成要么都不做

@Transactional //保证同时发生的注解，spring版

## GirlService

```java
package com.bennyrhys.girl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GirlService {
    @Autowired
    private GirlRepository repository;

    @Transactional //保证同时发生的注解，spring版
    public void changgeTwo(){
        Girl girl = new Girl();
        girl.setId(3);
        girl.setAge(20);
        girl.setCupSize("G");
        repository.save(girl);

        Girl girl2 = new Girl();
        girl2.setId(4);
        girl2.setAge(20);
        girl2.setCupSize("G");
        repository.save(girl2);
    }
}
```

## GirlController

```java
/**
 * 触发事务
 * 同时改变基因cupSize
 * http://localhost:8081/girl/girls/two
 * 数据发生改变
 */
@PostMapping(value = "/girls/two")
public void girlsTwo(){
    service.changgeTwo();
}
```

