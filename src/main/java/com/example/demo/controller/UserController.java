package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;
import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping
    public Result save(@RequestBody User user) {
        return Result.success(userService.save(user));
    }

    @GetMapping
    public Result getAll() {
        return Result.success(userService.list());
    }

    @GetMapping("/{id}")
    public Result getOne(@PathVariable long id) {
        return Result.success(userService.getOptById(id));
    }

    @PutMapping("/{id}")
    public Result update(@PathVariable long id, @RequestBody User user) {
        return Result.success(userService.updateById(user));
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable long id) {
        return Result.success(userService.removeById(id));
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                           @RequestParam(defaultValue = "10") Integer pageSize,
                           @RequestParam(defaultValue = "")String name){
        LambdaQueryWrapper<User> LambdaQueryWrapper=new LambdaQueryWrapper<>();
        if(!"".equals(name)&&name!=null){
            LambdaQueryWrapper.like(User::getName,name);
        }
        return Result.success(userService.page(new Page<User>(pageNum,pageSize),LambdaQueryWrapper));
    }
}
