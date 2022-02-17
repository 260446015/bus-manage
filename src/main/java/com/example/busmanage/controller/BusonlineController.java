package com.example.busmanage.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.busmanage.common.ApiResult;
import com.example.busmanage.dto.BusOnlineDTO;
import com.example.busmanage.dto.QueryDto;
import com.example.busmanage.entity.BusOnline;
import com.example.busmanage.service.impl.BusonlineServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/busonline")
@RestController
public class BusonlineController {
    private final BusonlineServiceImpl busonlineServiceImpl;

    public BusonlineController(BusonlineServiceImpl busonlineServiceImpl) {
        this.busonlineServiceImpl = busonlineServiceImpl;
    }

    @PostMapping
    public ApiResult save(@RequestBody @Validated BusOnlineDTO busOnlineDTO) {
        busonlineServiceImpl.saveBusOnline(busOnlineDTO);
        return ApiResult.ok();
    }

    @GetMapping
    public ApiResult get(QueryDto queryDto) {
        IPage<BusOnline> page = new Page<>(queryDto.getPn(),queryDto.getLimit());
        BusOnline busOnline = new BusOnline();
        BeanUtils.copyProperties(queryDto, busOnline);
        QueryWrapper<BusOnline> queryWrapper = new QueryWrapper<>(busOnline);
        busonlineServiceImpl.pageWithCompose(page,queryWrapper);
        return ApiResult.successPages(page);
    }

    @DeleteMapping("{id}")
    public ApiResult del(@PathVariable String id){
        busonlineServiceImpl.deleteById(id);
        return ApiResult.ok();
    }

    @GetMapping("{id}")
    public ApiResult getById(@PathVariable String id){
        return ApiResult.ok(busonlineServiceImpl.getComposeById(id));
    }
}
