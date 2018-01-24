package cn.com.winning.ssgj.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.EtDevEnvHardwareDao;
import cn.com.winning.ssgj.domain.EtDevEnvHardware;
import cn.com.winning.ssgj.service.EtDevEnvHardwareService;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
@Service
public class EtDevEnvHardwareServiceImpl implements EtDevEnvHardwareService {

    @Resource
    private EtDevEnvHardwareDao etDevEnvHardwareDao;


    public Integer createEtDevEnvHardware(EtDevEnvHardware t) {
        return this.etDevEnvHardwareDao.insertEntity(t);
    }

    public EtDevEnvHardware getEtDevEnvHardware(EtDevEnvHardware t) {
        return this.etDevEnvHardwareDao.selectEntity(t);
    }

    public Integer getEtDevEnvHardwareCount(EtDevEnvHardware t) {
        return (Integer) this.etDevEnvHardwareDao.selectEntityCount(t);
    }

    public List<EtDevEnvHardware> getEtDevEnvHardwareList(EtDevEnvHardware t) {
        return this.etDevEnvHardwareDao.selectEntityList(t);
    }

    public int modifyEtDevEnvHardware(EtDevEnvHardware t) {
        return this.etDevEnvHardwareDao.updateEntity(t);
    }

    public int removeEtDevEnvHardware(EtDevEnvHardware t) {
        return this.etDevEnvHardwareDao.deleteEntity(t);
    }

    public List<EtDevEnvHardware> getEtDevEnvHardwarePaginatedList(EtDevEnvHardware t) {
        return this.etDevEnvHardwareDao.selectEntityPaginatedList(t);
    }

}
