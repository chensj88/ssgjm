package cn.com.winning.ssgj.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.EtSimulateRecordDao;
import cn.com.winning.ssgj.domain.EtSimulateRecord;
import cn.com.winning.ssgj.service.EtSimulateRecordService;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
@Service
public class EtSimulateRecordServiceImpl implements EtSimulateRecordService {

    @Resource
    private EtSimulateRecordDao etSimulateRecordDao;


    public Integer createEtSimulateRecord(EtSimulateRecord t) {
        return this.etSimulateRecordDao.insertEntity(t);
    }

    public EtSimulateRecord getEtSimulateRecord(EtSimulateRecord t) {
        return this.etSimulateRecordDao.selectEntity(t);
    }

    public Integer getEtSimulateRecordCount(EtSimulateRecord t) {
        return (Integer) this.etSimulateRecordDao.selectEntityCount(t);
    }

    public List<EtSimulateRecord> getEtSimulateRecordList(EtSimulateRecord t) {
        return this.etSimulateRecordDao.selectEntityList(t);
    }

    public int modifyEtSimulateRecord(EtSimulateRecord t) {
        return this.etSimulateRecordDao.updateEntity(t);
    }

    public int removeEtSimulateRecord(EtSimulateRecord t) {
        return this.etSimulateRecordDao.deleteEntity(t);
    }

    public List<EtSimulateRecord> getEtSimulateRecordPaginatedList(EtSimulateRecord t) {
        return this.etSimulateRecordDao.selectEntityPaginatedList(t);
    }

}
