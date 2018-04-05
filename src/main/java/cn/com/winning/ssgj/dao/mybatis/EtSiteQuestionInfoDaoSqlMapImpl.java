package cn.com.winning.ssgj.dao.mybatis;

import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.EtSiteQuestionInfoDao;
import cn.com.winning.ssgj.domain.EtSiteQuestionInfo;
import cn.com.winning.ssgj.dao.mybatis.EntityDaoSqlMapImpl;

import java.util.List;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:47
 */
@Service
public class EtSiteQuestionInfoDaoSqlMapImpl extends EntityDaoSqlMapImpl<EtSiteQuestionInfo> implements EtSiteQuestionInfoDao {

    @Override
    public void updateEtSiteQuestionInfoImg(EtSiteQuestionInfo t) {
        this.getSqlSession().update("updateEtSiteQuestionInfoImg",t);
    }

    @Override
    public List<EtSiteQuestionInfo> selectEtSiteQuestionInfoUserTotal(EtSiteQuestionInfo t) {
        return this.getSqlSession().selectList("selectEtSiteQuestionInfoUserTotal",t);
    }

}
