package cn.com.winning.ssgj.dao.mybatis;

import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.SysParamsDao;
import cn.com.winning.ssgj.domain.SysParams;
import cn.com.winning.ssgj.dao.mybatis.EntityDaoSqlMapImpl;

import java.util.List;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:47
 */
@Service
public class SysParamsDaoSqlMapImpl extends EntityDaoSqlMapImpl<SysParams> implements SysParamsDao {

    @Override
    public List<SysParams> selectSysParamsPageListBySelectiveKey(SysParams params) {
        String statement = "selectSysParamsPageListBySelectiveKey";
        return super.getSqlSession().selectList(statement,params);
    }

    @Override
    public int selectSysParamsPageCountBySelectiveKey(SysParams params) {
        String statement = "selectSysParamsPageCountBySelectiveKey";
        return super.getSqlSession().selectOne(statement,params);
    }
}
