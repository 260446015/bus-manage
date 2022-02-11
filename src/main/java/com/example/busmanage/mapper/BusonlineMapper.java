package com.example.busmanage.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.busmanage.dto.BusOnlineDTO;
import com.example.busmanage.entity.BusOnline;

public interface BusonlineMapper extends BaseMapper<BusOnline> {
    IPage<BusOnlineDTO> pageWithCompose(IPage<BusOnline> page, QueryWrapper<BusOnline> queryWrapper);

    BusOnlineDTO getComposeById(String id);
}
