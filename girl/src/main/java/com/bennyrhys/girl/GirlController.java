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
    @Autowired
    private GirlService service;

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

}
