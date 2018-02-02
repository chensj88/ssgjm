package cn.com.winning.ssgj.dao.mybatis;

import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.SysSoftHardwareInfoDao;
import cn.com.winning.ssgj.domain.SysSoftHardwareInfo;
import cn.com.winning.ssgj.dao.mybatis.EntityDaoSqlMapImpl;

import java.util.List;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:47
 */
@Service
public class SysSoftHardwareInfoDaoSqlMapImpl extends EntityDaoSqlMapImpl<SysSoftHardwareInfo> implements SysSoftHardwareInfoDao {

    @Override
    public Integer selectSysSoftHardwareInfoCountByselective(SysSoftHardwareInfo t) {
        String statement = "query" + t.getClass().getSimpleName() + "CountForSelective";
        return super.getSqlSession().selectOne(statement,t);
    }

    @Override
    public List<SysSoftHardwareInfo> selectSysSoftHardwareInfoPaginatedListByselective(SysSoftHardwareInfo t) {
        String statement = "query" + t.getClass().getSimpleName() + "PaginatedListForSelective";
        return super.getSqlSession().selectList(statement,t);
    }
}
