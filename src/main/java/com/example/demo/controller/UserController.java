package com.example.demo.controller;

import com.example.demo.entity.User;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.events.Event;

@RestController
public class UserController {
    @PostMapping("/user")
    public String addUser(@RequestBody User user){
        return "用户添加成功";
    }
    @GetMapping("user")
    public String getUser(){
        return "查询用户成功";
    }
    @PostMapping("user/{id}")
    public String getOne(@PathVariable long id){
        return "查询单个用户成功";
    }
    @PutMapping("user/{id}")
    public String update(@PathVariable long id,@RequestBody User user){
        return "修改用户成功";
    }
    @DeleteMapping("user/{id}")
    public String delete(@PathVariable long id){
        return "删除用户成功";
    }
}
