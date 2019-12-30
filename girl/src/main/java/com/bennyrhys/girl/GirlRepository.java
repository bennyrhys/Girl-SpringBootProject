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
