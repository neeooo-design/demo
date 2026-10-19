package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserMapper userMapper;
    @PostMapping
    public String addUser(@RequestBody User user){
        return "用户添加成功";
    }
    @GetMapping
    public Page getUser(){
        return userMapper.selectPage(new Page<>(1,10),new LambdaUpdateWrapper<>());
    }
    @PostMapping("/{id}")
    public String getOne(@PathVariable long id){
        return "查询单个用户成功";
    }
    @PutMapping("/{id}")
    public String update(@PathVariable long id,@RequestBody User user){
        return "修改用户成功";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable long id){
        return "删除用户成功";
    }
}
