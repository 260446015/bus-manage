package com.example.busmanage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.busmanage.dto.BusOnlineInput;
import com.example.busmanage.entity.Bus;
import com.example.busmanage.entity.BusOnline;
import com.example.busmanage.entity.Driver;
import com.example.busmanage.mapper.BusMapper;
import com.example.busmanage.mapper.BusonlineMapper;
import com.example.busmanage.mapper.DriverMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Calendar;

@Service
public class BusonlineServiceImpl extends ServiceImpl<BusonlineMapper,BusOnline> {

    private final BusMapper busMapper;
    private final DriverMapper driverMapper;
    private final BusonlineMapper busonlineMapper;

    public BusonlineServiceImpl(BusMapper busMapper, DriverMapper driverMapper, BusonlineMapper busonlineMapper) {
        this.busMapper = busMapper;
        this.driverMapper = driverMapper;
        this.busonlineMapper = busonlineMapper;
    }

    public void saveBusOnline(BusOnlineInput busOnlineInput) {

        BusOnline busOnline = new BusOnline();
        BeanUtils.copyProperties(busOnlineInput,busOnline);
        busOnline.setCreateTime(Calendar.getInstance().getTime());

        String busId = busOnlineInput.getBusId();
        Bus bus = busMapper.selectById(busId);
        bus.setOnline(!bus.getOnline());
        String driverId = busOnlineInput.getDriverId();
        Driver driver = driverMapper.selectById(driverId);
        driver.setOnline(!driver.getOnline());
        if(busOnlineInput.getOuterOrInner()){
            bus.setOnline(true);
            driver.setOnline(true);
        }else{
            bus.setOnline(false);
            driver.setOnline(false);
        }
        busMapper.updateById(bus);
        driverMapper.updateById(driver);
        if(StringUtils.isEmpty(busOnlineInput.getId())){
            busonlineMapper.insert(busOnline);
        }else{
            busonlineMapper.updateById(busOnline);
        }

    }
}
