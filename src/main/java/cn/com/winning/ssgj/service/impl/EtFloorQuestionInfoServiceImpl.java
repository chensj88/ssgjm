package cn.com.winning.ssgj.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.EtFloorQuestionInfoDao;
import cn.com.winning.ssgj.domain.EtFloorQuestionInfo;
import cn.com.winning.ssgj.service.EtFloorQuestionInfoService;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
@Service
public class EtFloorQuestionInfoServiceImpl implements EtFloorQuestionInfoService {

    @Resource
    private EtFloorQuestionInfoDao etFloorQuestionInfoDao;


    public Integer createEtFloorQuestionInfo(EtFloorQuestionInfo t) {
        return this.etFloorQuestionInfoDao.insertEntity(t);
    }

    public EtFloorQuestionInfo getEtFloorQuestionInfo(EtFloorQuestionInfo t) {
        return this.etFloorQuestionInfoDao.selectEntity(t);
    }

    public Integer getEtFloorQuestionInfoCount(EtFloorQuestionInfo t) {
        return (Integer) this.etFloorQuestionInfoDao.selectEntityCount(t);
    }

    public List<EtFloorQuestionInfo> getEtFloorQuestionInfoList(EtFloorQuestionInfo t) {
        return this.etFloorQuestionInfoDao.selectEntityList(t);
    }

    public int modifyEtFloorQuestionInfo(EtFloorQuestionInfo t) {
        return this.etFloorQuestionInfoDao.updateEntity(t);
    }

    public int removeEtFloorQuestionInfo(EtFloorQuestionInfo t) {
        return this.etFloorQuestionInfoDao.deleteEntity(t);
    }

    public List<EtFloorQuestionInfo> getEtFloorQuestionInfoPaginatedList(EtFloorQuestionInfo t) {
        return this.etFloorQuestionInfoDao.selectEntityPaginatedList(t);
    }

    @Override
    public List<EtFloorQuestionInfo> getEtFloorQuestionInfoWithHospitalList(EtFloorQuestionInfo t) {
        return this.etFloorQuestionInfoDao.selectEtFloorQuestionInfoWithHospitalList(t);
    }

    @Override
    public List<EtFloorQuestionInfo> getEtFloorQuestionInfoSummaryList(EtFloorQuestionInfo t) {
        return this.etFloorQuestionInfoDao.selectEtFloorQuestionInfoSummaryList(t);
    }

}
