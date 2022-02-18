package com.example.busmanage.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.busmanage.common.ApiResult;
import com.example.busmanage.dto.QueryDto;
import com.example.busmanage.dto.vo.UserVo;
import com.example.busmanage.entity.User;
import com.example.busmanage.exception.BusinessException;
import com.example.busmanage.service.impl.UserServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/user")
@RestController
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping
    public ApiResult save(@RequestBody @Validated User user) {
        String encode = PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(user.getPassword());
        user.setPassword(encode);
        user.setIsAccountNonExpired(false).setIsAccountNonLocked(false)
                .setIsCredentialsNonExpired(false).setAuthorities("all");
        try {
            userService.saveOrUpdate(user);
        } catch (Exception e) {
            throw new BusinessException("用户名重复");
        }
        return ApiResult.ok();
    }

    @GetMapping
    public ApiResult get(QueryDto<User> queryDto) {
        IPage<UserVo> page = new Page<>(queryDto.getPn(), queryDto.getLimit());
        IPage<User> page2 = new Page<>(queryDto.getPn(), queryDto.getLimit());
        userService.pageCustom(page, queryDto.buildQuery());
//        userService.page(page2, queryDto.buildQuery());
        return ApiResult.successPages(page);
    }

    @GetMapping("{id}")
    public ApiResult getById(@PathVariable String id) {
        User byId = userService.getById(id);
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(byId, userVo);
        return ApiResult.ok(userVo);
    }

    @DeleteMapping("{id}")
    public ApiResult del(@PathVariable String id) {
        userService.removeById(id);
        return ApiResult.ok();
    }
}
