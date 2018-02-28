package cn.com.winning.ssgj.dao.mybatis;

import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.SysThirdInterfaceInfoDao;
import cn.com.winning.ssgj.domain.SysThirdInterfaceInfo;
import cn.com.winning.ssgj.dao.mybatis.EntityDaoSqlMapImpl;

import java.util.List;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:47
 */
@Service
public class SysThirdInterfaceInfoDaoSqlMapImpl extends EntityDaoSqlMapImpl<SysThirdInterfaceInfo> implements SysThirdInterfaceInfoDao {

    @Override
    public Integer selectSysThirdInterfaceInfoCountByselective(SysThirdInterfaceInfo t) {
        String statement = "selectSysThirdInterfaceInfoCountByselective";
        return super.getSqlSession().selectOne(statement,t);
    }

    @Override
    public List<SysThirdInterfaceInfo> selectSysThirdInterfaceInfoPaginatedListByselective(SysThirdInterfaceInfo t) {
        String statement = "selectSysThirdInterfaceInfoPaginatedListByselective";
        return super.getSqlSession().selectList(statement,t);
    }

    @Override
    public List<SysThirdInterfaceInfo> selectSysThirdInterfaceInfoListByIds(SysThirdInterfaceInfo sysThirdInterfaceInfo) {
        String statement = "selectSysThirdInterfaceInfoListByIds";
        return super.getSqlSession().selectList(statement,sysThirdInterfaceInfo);
    }

    @Override
    public List<SysThirdInterfaceInfo> selectSysThirdInterfaceInfoListForNames(SysThirdInterfaceInfo info) {
        String statement = "selectSysThirdInterfaceInfoListForNames";
        return super.getSqlSession().selectList(statement,info);
    }
}
