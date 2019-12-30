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
