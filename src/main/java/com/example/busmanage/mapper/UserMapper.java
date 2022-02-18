package com.example.busmanage.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.example.busmanage.dto.vo.UserVo;
import com.example.busmanage.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper extends BaseMapper<User> {
    @Select(value = "select id,name,username,is_enabled from user ${ew.customSqlSegment}")
    IPage<UserVo> pageCustom(IPage<UserVo> page,@Param(Constants.WRAPPER) QueryWrapper<User> queryWrapper);
}
