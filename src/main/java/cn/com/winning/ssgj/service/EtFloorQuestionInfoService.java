package cn.com.winning.ssgj.service;

import java.util.List;

import cn.com.winning.ssgj.domain.EtFloorQuestionInfo;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
public interface EtFloorQuestionInfoService {

    Integer createEtFloorQuestionInfo(EtFloorQuestionInfo t);

    int modifyEtFloorQuestionInfo(EtFloorQuestionInfo t);

    int removeEtFloorQuestionInfo(EtFloorQuestionInfo t);

    EtFloorQuestionInfo getEtFloorQuestionInfo(EtFloorQuestionInfo t);

    List<EtFloorQuestionInfo> getEtFloorQuestionInfoList(EtFloorQuestionInfo t);

    Integer getEtFloorQuestionInfoCount(EtFloorQuestionInfo t);

    List<EtFloorQuestionInfo> getEtFloorQuestionInfoPaginatedList(EtFloorQuestionInfo t);

}