package com.example.busmanage.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.busmanage.common.ApiResult;
import com.example.busmanage.dto.QueryDto;
import com.example.busmanage.entity.Bus;
import com.example.busmanage.service.impl.BusServiceImpl;
import com.example.busmanage.util.JsonUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@RequestMapping("api/bus")
@RestController
public class BusController {

    private final BusServiceImpl busService;

    public BusController(BusServiceImpl busService) {
        this.busService = busService;
    }

    @PostMapping
    public ApiResult save(@RequestBody @Validated Bus bus) {
        busService.saveOrUpdate(bus);
        return ApiResult.ok();
    }

    @GetMapping
    public ApiResult get(QueryDto queryDto) {
        IPage<Bus> page = new Page<>(queryDto.getPn(), queryDto.getLimit());
        Bus bus = new Bus();
        BeanUtils.copyProperties(queryDto,bus);
        QueryWrapper<Bus> queryWrapper = new QueryWrapper<>(bus);
        if(!StringUtils.isEmpty(queryDto.getType())){
            queryWrapper.like(queryDto.getType(), queryDto.getSearch());
        }
        busService.page(page, queryWrapper);
        return ApiResult.successPages(page);
    }

    @DeleteMapping("{id}")
    public ApiResult del(@PathVariable String id) {
        busService.removeById(id);
        return ApiResult.ok();
    }

    @GetMapping("{id}")
    public ApiResult getById(@PathVariable String id) {
        return ApiResult.ok(busService.getById(id));
    }
}
