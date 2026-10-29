package com.example.demo.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
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
    // 测试登录，浏览器访问： http://localhost:8081/user/doLogin?username=zhang&password=123456
    @RequestMapping("doLogin")
    public Result doLogin(String username, String password) {
        // 此处仅作模拟示例，真实项目需要从数据库中查询数据进行比对
        if("zhang".equals(username) && "123456".equals(password)) {
            StpUtil.login(10001);
            SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
            return Result.success(tokenInfo);
        }
        return Result.error("登录失败");
    }

    // 查询登录状态，浏览器访问： http://localhost:8081/user/isLogin
    @RequestMapping("isLogin")
    public Result isLogin() {
        return Result.success("当前对话是否登录："+StpUtil.isLogin());
    }

    @Resource
    private UserService userService;

    @PostMapping
    @SaCheckPermission("user.add")
    public Result save(@RequestBody User user) {
        return Result.success(userService.save(user));
    }

    @GetMapping
    @SaCheckPermission("user.get")
    public Result getAll() {
        return Result.success(userService.list());
    }

    @GetMapping("/{id}")
    public Result getOne(@PathVariable long id) {
        return Result.success(userService.getOptById(id));
    }

    @PutMapping("/{id}")
    @SaCheckPermission("user.update")
    public Result update(@PathVariable long id, @RequestBody User user) {
        return Result.success(userService.updateById(user));
    }

    @DeleteMapping("/{id}")
    @SaCheckPermission("user.delete")
    public Result delete(@PathVariable long id) {
        return Result.success(userService.removeById(id));
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                           @RequestParam(defaultValue = "10") Integer pageSize,
                           @RequestParam(defaultValue = "") String name) {
        LambdaQueryWrapper<User> LambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (!"".equals(name) && name != null) {
            LambdaQueryWrapper.like(User::getName, name);
        }
        return Result.success(userService.page(new Page<User>(pageNum, pageSize), LambdaQueryWrapper));
    }
}
