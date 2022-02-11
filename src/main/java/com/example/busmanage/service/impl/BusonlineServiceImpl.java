package com.example.busmanage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.busmanage.dto.BusOnlineDTO;
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
public class BusonlineServiceImpl extends ServiceImpl<BusonlineMapper, BusOnline> {

    private final BusMapper busMapper;
    private final DriverMapper driverMapper;
    private final BusonlineMapper busonlineMapper;

    public BusonlineServiceImpl(BusMapper busMapper, DriverMapper driverMapper, BusonlineMapper busonlineMapper) {
        this.busMapper = busMapper;
        this.driverMapper = driverMapper;
        this.busonlineMapper = busonlineMapper;
    }

    public void saveBusOnline(BusOnlineDTO busOnlineDTO) {
        String busId;
        String driverId;
        if(StringUtils.isEmpty(busOnlineDTO.getId())){
            BusOnline busOnline = new BusOnline();
            BeanUtils.copyProperties(busOnlineDTO, busOnline);
            busOnline.setOuterOrInner(false);
            busOnline.setCreateTime(Calendar.getInstance().getTime());
            busOnline.setOuterTime(Calendar.getInstance().getTime());
            busId = busOnlineDTO.getBusId();
            driverId = busOnlineDTO.getDriverId();
            busonlineMapper.insert(busOnline);
        }else{
            BusOnline busOnline = busonlineMapper.selectById(busOnlineDTO.getId());
            busId = busOnline.getBusId();
            driverId = busOnline.getDriverId();
            busOnline.setOuterOrInner(!busOnline.getOuterOrInner());
            if(!busOnline.getOuterOrInner()){
                busOnline.setOuterTime(Calendar.getInstance().getTime());
            }else{
                busOnline.setInnerTime(Calendar.getInstance().getTime());
            }
            busonlineMapper.updateById(busOnline);
        }
        Bus bus = busMapper.selectById(busId);
        Driver driver = driverMapper.selectById(driverId);
        bus.setOnline(!bus.getOnline());
        driver.setOnline(!driver.getOnline());
        busMapper.updateById(bus);
        driverMapper.updateById(driver);
    }

    public BusOnlineDTO getComposeById(String id) {
        return busonlineMapper.getComposeById(id);
    }

    public IPage<BusOnlineDTO> pageWithCompose(IPage<BusOnline> page, QueryWrapper<BusOnline> queryWrapper) {
        return busonlineMapper.pageWithCompose(page, queryWrapper);
    }

    public void deleteById(String id) {
        BusOnline busOnline = busonlineMapper.selectById(id);
        Bus bus = busMapper.selectById(busOnline.getBusId());
        Driver driver = driverMapper.selectById(busOnline.getDriverId());
        bus.setOnline(false);
        driver.setOnline(false);
        busMapper.updateById(bus);
        driverMapper.updateById(driver);
        busonlineMapper.deleteById(id);
    }
}
