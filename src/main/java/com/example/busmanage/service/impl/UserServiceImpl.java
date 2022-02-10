package com.example.busmanage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.busmanage.dto.vo.UserVo;
import com.example.busmanage.entity.User;
import com.example.busmanage.mapper.UserMapper;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> {

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public IPage<UserVo> pageCustom(IPage<UserVo> page, QueryWrapper<User> queryWrapper) {
        return userMapper.pageCustom(page,queryWrapper);
    }
}
