package cn.com.winning.ssgj.dao.mybatis;

import cn.com.winning.ssgj.domain.PmisContractProductInfo;
import cn.com.winning.ssgj.domain.PmisProductInfo;
import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.PmisProductLineInfoDao;
import cn.com.winning.ssgj.domain.PmisProductLineInfo;
import cn.com.winning.ssgj.dao.mybatis.EntityDaoSqlMapImpl;

import java.util.List;

/**
 * @author SSGJ
 * @date 2018-01-18 10:11:47
 */
@Service
public class PmisProductLineInfoDaoSqlMapImpl extends EntityDaoSqlMapImpl<PmisProductLineInfo> implements PmisProductLineInfoDao {

    @Override
    public List<PmisProductLineInfo> selectPmisProductLineInfoByNameForList(PmisProductLineInfo t) {
        String statement = "selectPmisProductLineInfoByNameForList";
        return super.getSqlSession().selectList(statement, t);
    }

    @Override
    public Integer selectPmisProductLineInfoByNameForCount(PmisProductLineInfo t) {
        String statement = "selectPmisProductLineInfoByNameForCount";
        return super.getSqlSession().selectOne(statement, t);
    }

    @Override
    public List<PmisProductLineInfo> selectPmisProductLineInfoByProductInfo(List<PmisProductInfo> pmisProductInfos) {
        return this.getSqlSession().selectList("selectPmisProductLineInfoByProductInfo", pmisProductInfos);
    }

    @Override
    public List<PmisProductLineInfo> selectPmisProductLineInfoByPmidAndType(PmisContractProductInfo pmisContractProductInfo) {
        return this.getSqlSession().selectList("selectPmisProductLineInfoByPmidAndType", pmisContractProductInfo);
    }
}
