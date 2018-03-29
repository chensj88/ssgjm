package cn.com.winning.ssgj.dao.mybatis;

import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.EtInterfaceInfoDao;
import cn.com.winning.ssgj.domain.EtInterfaceInfo;
import cn.com.winning.ssgj.dao.mybatis.EntityDaoSqlMapImpl;

import java.util.List;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:47
 */
@Service
public class EtInterfaceInfoDaoSqlMapImpl extends EntityDaoSqlMapImpl<EtInterfaceInfo> implements EtInterfaceInfoDao {

    @Override
    public List<EtInterfaceInfo> selectEtInterfaceInfoMergePageList(EtInterfaceInfo etInterfaceInfo) {
        String statement = "selectEtInterfaceInfoMergePageList";
        return super.getSqlSession().selectList(statement,etInterfaceInfo);
    }

    @Override
    public Integer selectEtInterfaceInfoMergeCount(EtInterfaceInfo etInterfaceInfo) {
        String statement = "selectEtInterfaceInfoMergeCount";
        return super.getSqlSession().selectOne(statement,etInterfaceInfo);
    }
}
