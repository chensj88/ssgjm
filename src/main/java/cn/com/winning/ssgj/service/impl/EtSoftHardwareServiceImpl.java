package cn.com.winning.ssgj.service.impl;

import java.util.List;

import javax.annotation.Resource;


import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.EtSoftHardwareDao;
import cn.com.winning.ssgj.domain.EtSoftHardware;
import cn.com.winning.ssgj.service.EtSoftHardwareService;

/**
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
@Service
public class EtSoftHardwareServiceImpl implements EtSoftHardwareService {

    @Resource
    private EtSoftHardwareDao etSoftHardwareDao;



    public Integer createEtSoftHardware(EtSoftHardware t) {
        return this.etSoftHardwareDao.insertEntity(t);
    }


    public EtSoftHardware getEtSoftHardware(EtSoftHardware t) {
        return this.etSoftHardwareDao.selectEntity(t);
    }


    public Integer getEtSoftHardwareCount(EtSoftHardware t) {
        return (Integer) this.etSoftHardwareDao.selectEntityCount(t);
    }


    public List<EtSoftHardware> getEtSoftHardwareList(EtSoftHardware t) {
        return this.etSoftHardwareDao.selectEntityList(t);
    }


    public int modifyEtSoftHardware(EtSoftHardware t) {
        return this.etSoftHardwareDao.updateEntity(t);
    }


    public int removeEtSoftHardware(EtSoftHardware t) {
        return this.etSoftHardwareDao.deleteEntity(t);
    }


    public List<EtSoftHardware> getEtSoftHardwarePaginatedList(EtSoftHardware t) {
        return this.etSoftHardwareDao.selectEntityPaginatedList(t);
    }

}
