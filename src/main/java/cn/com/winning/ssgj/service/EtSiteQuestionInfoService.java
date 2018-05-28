package cn.com.winning.ssgj.service;

import java.util.List;

import cn.com.winning.ssgj.domain.EtSiteQuestionInfo;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
public interface EtSiteQuestionInfoService {

    Integer createEtSiteQuestionInfo(EtSiteQuestionInfo t);

    int modifyEtSiteQuestionInfo(EtSiteQuestionInfo t);

    int removeEtSiteQuestionInfo(EtSiteQuestionInfo t);

    EtSiteQuestionInfo getEtSiteQuestionInfo(EtSiteQuestionInfo t);

    List<EtSiteQuestionInfo> getEtSiteQuestionInfoList(EtSiteQuestionInfo t);

    Integer getEtSiteQuestionInfoCount(EtSiteQuestionInfo t);

    List<EtSiteQuestionInfo> getEtSiteQuestionInfoPaginatedList(EtSiteQuestionInfo t);

    void updateEtSiteQuestionInfoImg(EtSiteQuestionInfo info);

    List<EtSiteQuestionInfo> getEtSiteQuestionInfoUserTotal(EtSiteQuestionInfo info);

    /**
     * 生成站点问题信息
     * @param info
     * @param path
     */
    void generateEtSiteQuestionInfo(EtSiteQuestionInfo info, String path);
}