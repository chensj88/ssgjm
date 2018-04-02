package cn.com.winning.ssgj.dao.mybatis;

import cn.com.winning.ssgj.domain.expand.FlotDataInfo;
import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.SysUserInfoDao;
import cn.com.winning.ssgj.domain.SysUserInfo;
import cn.com.winning.ssgj.dao.mybatis.EntityDaoSqlMapImpl;

import java.util.List;
import java.util.Map;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:47
 */
@Service
public class SysUserInfoDaoSqlMapImpl extends EntityDaoSqlMapImpl<SysUserInfo> implements SysUserInfoDao {

    @Override
    public Integer selectSysUserInfoQueryCount(SysUserInfo t) {
        String statement = "selectSysUserInfoQueryCount";
        return super.getSqlSession().selectOne(statement, t);
    }

    @Override
    public List<SysUserInfo> selectSysUserInfoQueryPaginatedList(SysUserInfo t) {
        String statement = "selectSysUserInfoQueryPaginatedList";
        return super.getSqlSession().selectList(statement, t);
    }

    @Override
    public List<FlotDataInfo> countUserInfoByType() {
        String statement = "countUserInfoByType";
        return super.getSqlSession().selectList(statement);
    }

    @Override
    public List<SysUserInfo> selectSysUserInfoListByUserIdList(SysUserInfo userInfo) {
        return super.getSqlSession().selectList("selectSysUserInfoListByUserIdList",userInfo);
    }

    @Override
    public SysUserInfo selectSysUserInfoByUserCode(SysUserInfo user) {
        return super.getSqlSession().selectOne("selectSysUserInfoByUserCode",user);
    }
}
