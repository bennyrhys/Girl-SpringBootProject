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
