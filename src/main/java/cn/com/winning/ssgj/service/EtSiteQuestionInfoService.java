package cn.com.winning.ssgj.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import cn.com.winning.ssgj.domain.EtSiteQuestionInfo;
import cn.com.winning.ssgj.domain.MobileSiteQuestion;

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

    List<Map<String,Object>> getEtSiteQuestionCountInfo(EtSiteQuestionInfo info);

    /**
     * 根据Excel读取内容生成类
     * @param questionList
     * @param info
     */
    void createEtSiteQuestionInfo(List<List<Object>> questionList, EtSiteQuestionInfo info) throws ParseException;

    /**
     * 统计用户任务信息
     * @param info
     * @return
     */
    List<EtSiteQuestionInfo> getEtSiteQuestionInfoTotalCountByUser(EtSiteQuestionInfo info);

    List<MobileSiteQuestion> getSiteQuestionInfoByUser(EtSiteQuestionInfo info) throws ParseException;

    List<EtSiteQuestionInfo> selectMobileEtSiteQuestionInfo(EtSiteQuestionInfo questionInfo);

    EtSiteQuestionInfo getEtSiteQuestionProcessStatus(EtSiteQuestionInfo qinfo);

    int checkEtSiteQuestionInfoStatus(EtSiteQuestionInfo info);
}