package cn.com.winning.ssgj.dao.mybatis;

import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.SysFlowInfoDao;
import cn.com.winning.ssgj.domain.SysFlowInfo;
import cn.com.winning.ssgj.dao.mybatis.EntityDaoSqlMapImpl;

import java.util.List;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:47
 */
@Service
public class SysFlowInfoDaoSqlMapImpl extends EntityDaoSqlMapImpl<SysFlowInfo> implements SysFlowInfoDao {

    @Override
    public List<SysFlowInfo> querySysFlowInfoList(SysFlowInfo t) {
        String statement = "query" + t.getClass().getSimpleName() + SUFFIX_LIST;
        return super.getSqlSession().selectList(statement,t);
    }

    @Override
    public List<SysFlowInfo> querySysFlowInfoByFlowTypeAndFlowCode(SysFlowInfo t) {
        String statement = "query" + t.getClass().getSimpleName() + "ByFlowTypeAndFlowCode";
        return super.getSqlSession().selectList(statement,t);
    }
}
