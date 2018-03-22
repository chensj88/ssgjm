package cn.com.winning.ssgj.service.impl;

import java.util.List;

import javax.annotation.Resource;


import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.EtSiteQuestionInfoDao;
import cn.com.winning.ssgj.domain.EtSiteQuestionInfo;
import cn.com.winning.ssgj.service.EtSiteQuestionInfoService;

/**
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
@Service
public class EtSiteQuestionInfoServiceImpl implements EtSiteQuestionInfoService {

    @Resource
    private EtSiteQuestionInfoDao etSiteQuestionInfoDao;



    public Integer createEtSiteQuestionInfo(EtSiteQuestionInfo t) {
        return this.etSiteQuestionInfoDao.insertEntity(t);
    }


    public EtSiteQuestionInfo getEtSiteQuestionInfo(EtSiteQuestionInfo t) {
        return this.etSiteQuestionInfoDao.selectEntity(t);
    }


    public Integer getEtSiteQuestionInfoCount(EtSiteQuestionInfo t) {
        return (Integer) this.etSiteQuestionInfoDao.selectEntityCount(t);
    }


    public List<EtSiteQuestionInfo> getEtSiteQuestionInfoList(EtSiteQuestionInfo t) {
        return this.etSiteQuestionInfoDao.selectEntityList(t);
    }


    public int modifyEtSiteQuestionInfo(EtSiteQuestionInfo t) {
        return this.etSiteQuestionInfoDao.updateEntity(t);
    }


    public int removeEtSiteQuestionInfo(EtSiteQuestionInfo t) {
        return this.etSiteQuestionInfoDao.deleteEntity(t);
    }


    public List<EtSiteQuestionInfo> getEtSiteQuestionInfoPaginatedList(EtSiteQuestionInfo t) {
        return this.etSiteQuestionInfoDao.selectEntityPaginatedList(t);
    }

    public void updateEtSiteQuestionInfoImg(EtSiteQuestionInfo t) {
        this.etSiteQuestionInfoDao.updateEtSiteQuestionInfoImg(t);

    }

}
