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
