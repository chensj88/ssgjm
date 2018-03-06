package cn.com.winning.ssgj.dao.mybatis;

import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.SysReportInfoDao;
import cn.com.winning.ssgj.domain.SysReportInfo;
import cn.com.winning.ssgj.dao.mybatis.EntityDaoSqlMapImpl;

import java.util.List;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:47
 */
@Service
public class SysReportInfoDaoSqlMapImpl extends EntityDaoSqlMapImpl<SysReportInfo> implements SysReportInfoDao {


    @Override
    public Integer selectSysReportInfoCountByselective(SysReportInfo t) {
        String statement = "selectSysReportInfoCountByselective";
        return super.getSqlSession().selectOne(statement,t);
    }

    @Override
    public List<SysReportInfo> selectSysReportInfoPaginatedListByselective(SysReportInfo t) {
        String statement = "selectSysReportInfoPaginatedListByselective";
        return super.getSqlSession().selectList(statement,t);
    }

    @Override
    public List<SysReportInfo> selectSysReportInfoListByIds(SysReportInfo reportInfo) {
        return super.getSqlSession().selectList("selectSysReportInfoListByIds",reportInfo);
    }

    @Override
    public List<SysReportInfo> selectSysReportInfolistNoPage(SysReportInfo info) {
        return  super.getSqlSession().selectList("selectSysReportInfolistNoPage",info);
    }
}
