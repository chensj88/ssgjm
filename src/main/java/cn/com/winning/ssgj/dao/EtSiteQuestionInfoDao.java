package cn.com.winning.ssgj.dao;

import cn.com.winning.ssgj.domain.EtSiteQuestionInfo;
import cn.com.winning.ssgj.dao.EntityDao;

import java.util.List;
import java.util.Map;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:47
 */
public interface EtSiteQuestionInfoDao extends EntityDao<EtSiteQuestionInfo> {

    void updateEtSiteQuestionInfoImg(EtSiteQuestionInfo t);

    List<EtSiteQuestionInfo> selectEtSiteQuestionInfoUserTotal(EtSiteQuestionInfo t);

    List<Map<String,Object>> selectEtSiteQuestionCountInfo(EtSiteQuestionInfo info);
}
