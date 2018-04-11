package cn.com.winning.ssgj.service.impl;

import java.util.List;

import javax.annotation.Resource;


import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.EtFlowSurveyDao;
import cn.com.winning.ssgj.domain.EtFlowSurvey;
import cn.com.winning.ssgj.service.EtFlowSurveyService;

/**
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
@Service
public class EtFlowSurveyServiceImpl implements EtFlowSurveyService {

    @Resource
    private EtFlowSurveyDao etFlowSurveyDao;



    public Integer createEtFlowSurvey(EtFlowSurvey t) {
        return this.etFlowSurveyDao.insertEntity(t);
    }


    public EtFlowSurvey getEtFlowSurvey(EtFlowSurvey t) {
        return this.etFlowSurveyDao.selectEntity(t);
    }


    public Integer getEtFlowSurveyCount(EtFlowSurvey t) {
        return (Integer) this.etFlowSurveyDao.selectEntityCount(t);
    }


    public List<EtFlowSurvey> getEtFlowSurveyList(EtFlowSurvey t) {
        return this.etFlowSurveyDao.selectEntityList(t);
    }


    public int modifyEtFlowSurvey(EtFlowSurvey t) {
        return this.etFlowSurveyDao.updateEntity(t);
    }


    public int removeEtFlowSurvey(EtFlowSurvey t) {
        return this.etFlowSurveyDao.deleteEntity(t);
    }


    public List<EtFlowSurvey> getEtFlowSurveyPaginatedList(EtFlowSurvey t) {
        return this.etFlowSurveyDao.selectEntityPaginatedList(t);
    }

    @Override
    public void generateEtFlowSurveyData(EtFlowSurvey flowSurvey) {

    }

}
