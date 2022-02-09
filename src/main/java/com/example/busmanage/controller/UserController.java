package com.example.busmanage.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.busmanage.common.ApiResult;
import com.example.busmanage.dto.QueryDto;
import com.example.busmanage.entity.User;
import com.example.busmanage.service.impl.UserServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/user")
@RestController
public class UserController {

    private final UserServiceImpl userService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserServiceImpl userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    public ApiResult save(@RequestBody User user) {
        String encode = passwordEncoder.encode(user.getPassword());
        user.setPassword(encode);
        user.setIsAccountNonExpired(false).setIsAccountNonLocked(false)
                .setIsCredentialsNonExpired(false).setAuthorities("all");
        userService.saveOrUpdate(user);
        return ApiResult.ok();
    }

    @GetMapping
    public ApiResult get(QueryDto queryDto) {
        IPage<User> page = new Page<>(queryDto.getPn(),queryDto.getPs());
        User user = new User();
        BeanUtils.copyProperties(queryDto, user);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>(user);
        userService.page(page,queryWrapper);
        return ApiResult.successPages(page);
    }

    @GetMapping("{id}")
    public ApiResult getById(@PathVariable String id){
        return ApiResult.ok(userService.getById(id));
    }

    @DeleteMapping("{id}")
    public ApiResult del(@PathVariable String id){
        userService.removeById(id);
        return ApiResult.ok();
    }
}
