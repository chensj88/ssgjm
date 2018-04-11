package cn.com.winning.ssgj.dao.mybatis;

import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.SysProductFlowInfoDao;
import cn.com.winning.ssgj.domain.SysProductFlowInfo;
import cn.com.winning.ssgj.dao.mybatis.EntityDaoSqlMapImpl;

import java.util.List;
import java.util.Map;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-02-08 15:30:03
 */
@Service
public class SysProductFlowInfoDaoSqlMapImpl extends EntityDaoSqlMapImpl<SysProductFlowInfo> implements SysProductFlowInfoDao {

    @Override
    public List<SysProductFlowInfo> selectSysProductFlowInfoByPdIdAndFlowId(Map<String, Object> param) {
        String statement = "selectSysProductFlowInfoByPdIdAndFlowId";
        return super.getSqlSession().selectList(statement,param);
    }

    @Override
    public List<SysProductFlowInfo> selectProductFlowInfoForIds(Map<String, Object> param) {
        String statement = "selectProductFlowInfoForIds";
        return super.getSqlSession().selectList(statement,param);
    }

    @Override
    public Integer removeSysProductFlowInfoMappingByIds(Map<String, Object> param) {
        String statement = "removeSysProductFlowInfoMappingByIds";
        return  super.getSqlSession().update(statement,param);
    }

    @Override
    public List<Long> selectSysFlowInfoIdsoByPdId(Map<String, Object> param) {
        return super.getSqlSession().selectList("selectSysFlowInfoIdsoByPdId",param);
    }
}
