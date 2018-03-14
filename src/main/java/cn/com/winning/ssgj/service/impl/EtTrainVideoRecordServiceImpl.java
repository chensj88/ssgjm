package cn.com.winning.ssgj.service.impl;

import java.util.List;

import javax.annotation.Resource;


import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.EtTrainVideoRecordDao;
import cn.com.winning.ssgj.domain.EtTrainVideoRecord;
import cn.com.winning.ssgj.service.EtTrainVideoRecordService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-02-27 16:02:55
 */
@Service
public class EtTrainVideoRecordServiceImpl implements EtTrainVideoRecordService {

    @Resource
    private EtTrainVideoRecordDao etTrainVideoRecordDao;



    public Integer createEtTrainVideoRecord(EtTrainVideoRecord t) {
        return this.etTrainVideoRecordDao.insertEntity(t);
    }



    public EtTrainVideoRecord getEtTrainVideoRecord(EtTrainVideoRecord t) {
        return this.etTrainVideoRecordDao.selectEntity(t);
    }


    public Integer getEtTrainVideoRecordCount(EtTrainVideoRecord t) {
        return (Integer) this.etTrainVideoRecordDao.selectEntityCount(t);
    }



    public List<EtTrainVideoRecord> getEtTrainVideoRecordList(EtTrainVideoRecord t) {
        return this.etTrainVideoRecordDao.selectEntityList(t);
    }



    public int modifyEtTrainVideoRecord(EtTrainVideoRecord t) {
        return this.etTrainVideoRecordDao.updateEntity(t);
    }



    public int removeEtTrainVideoRecord(EtTrainVideoRecord t) {
        return this.etTrainVideoRecordDao.deleteEntity(t);
    }



    public List<EtTrainVideoRecord> getEtTrainVideoRecordPaginatedList(EtTrainVideoRecord t) {
        return this.etTrainVideoRecordDao.selectEntityPaginatedList(t);
    }

}