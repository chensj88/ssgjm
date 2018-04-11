package cn.com.winning.ssgj.service;

import java.util.List;

import cn.com.winning.ssgj.domain.EtFlowSurvey;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
public interface EtFlowSurveyService {

    Integer createEtFlowSurvey(EtFlowSurvey t);

    int modifyEtFlowSurvey(EtFlowSurvey t);

    int removeEtFlowSurvey(EtFlowSurvey t);

    EtFlowSurvey getEtFlowSurvey(EtFlowSurvey t);

    List<EtFlowSurvey> getEtFlowSurveyList(EtFlowSurvey t);

    Integer getEtFlowSurveyCount(EtFlowSurvey t);

    List<EtFlowSurvey> getEtFlowSurveyPaginatedList(EtFlowSurvey t);

    void generateEtFlowSurveyData(EtFlowSurvey flowSurvey);
}