package com.example.busmanage.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.busmanage.common.MyUser;
import com.example.busmanage.entity.User;
import com.example.busmanage.service.impl.UserServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

public class MyUserDetailService implements UserDetailsService {
    private final UserServiceImpl userService;

    public MyUserDetailService(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", s);
        List<User> list = userService.list(queryWrapper);
        UserDetails userDetails = null;
        if (!list.isEmpty()) {
            userDetails = new MyUser();
            User user = list.get(0);
            BeanUtils.copyProperties(user, userDetails);
        }
        return userDetails;
    }
}
