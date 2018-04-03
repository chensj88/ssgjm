package cn.com.winning.ssgj.dao.mybatis;

import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.PmisProductInfoDao;
import cn.com.winning.ssgj.domain.PmisProductInfo;
import cn.com.winning.ssgj.dao.mybatis.EntityDaoSqlMapImpl;

import java.util.List;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:47
 */
@Service
public class PmisProductInfoDaoSqlMapImpl extends EntityDaoSqlMapImpl<PmisProductInfo> implements PmisProductInfoDao {

    @Override
    public Integer selectPmisProductInfoCountByCodeAndName(PmisProductInfo t) {
        String statement = "selectPmisProductInfoCountByCodeAndName";
        return super.getSqlSession().selectOne(statement,t);
    }

    @Override
    public List<PmisProductInfo> selectPmisProductInfoPaginatedListByCodeAndName(PmisProductInfo t) {
        String statement = "selectPmisProductInfoPaginatedListByCodeAndName";
        return super.getSqlSession().selectList(statement,t);
    }

    @Override
    public List<PmisProductInfo> selectPmisProductInfoListByIdList(PmisProductInfo productInfo) {
        return super.getSqlSession().selectList("selectPmisProductInfoListByIdList",productInfo);
    }
<<<<<<< HEAD
    @Override
    public List<PmisProductInfo> selectEasyDataPmisProductInfoList(PmisProductInfo t) {
        String statement = "selectEasyDataPmisProductInfoList";
        return super.getSqlSession().selectList(statement, t);
    }

    @Override
    public List<PmisProductInfo> selectBasicDataPmisProductInfoList(PmisProductInfo t) {
        String statement = "selectBasicDataPmisProductInfoList";
        return super.getSqlSession().selectList(statement, t);
    }
=======
>>>>>>> 237e489d166a693f8c4dbfefeb298d88a286c683
}
