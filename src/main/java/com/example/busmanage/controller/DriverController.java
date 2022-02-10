package com.example.busmanage.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.busmanage.common.ApiResult;
import com.example.busmanage.dto.QueryDto;
import com.example.busmanage.entity.Driver;
import com.example.busmanage.service.impl.DriverServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/driver")
@RestController
public class DriverController {
    private final DriverServiceImpl driverService;

    public DriverController(DriverServiceImpl driverService) {
        this.driverService = driverService;
    }

    @PostMapping
    public ApiResult save(@RequestBody @Validated Driver driver) {
        if(StringUtils.isEmpty(driver.getId())){
            driver.setOnline(false);
        }
        driverService.saveOrUpdate(driver);
        return ApiResult.ok();
    }

    @GetMapping
    public ApiResult get(QueryDto queryDto) {
        IPage<Driver> page = new Page<>(queryDto.getPn(),queryDto.getPs());
        Driver driver = new Driver();
        BeanUtils.copyProperties(queryDto, driver);
        QueryWrapper<Driver> queryWrapper = new QueryWrapper<>(driver);
        driverService.page(page,queryWrapper);
        return ApiResult.successPages(page);
    }

    @DeleteMapping("{id}")
    public ApiResult del(@PathVariable String id){
        driverService.removeById(id);
        return ApiResult.ok();
    }

    @GetMapping("{id}")
    public ApiResult getById(@PathVariable String id){
        return ApiResult.ok(driverService.getById(id));
    }
}
