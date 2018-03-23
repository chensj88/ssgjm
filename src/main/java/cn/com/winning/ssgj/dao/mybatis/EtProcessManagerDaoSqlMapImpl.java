package cn.com.winning.ssgj.dao.mybatis;

import cn.com.winning.ssgj.domain.SysDataInfo;
import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.EtProcessManagerDao;
import cn.com.winning.ssgj.domain.EtProcessManager;
import cn.com.winning.ssgj.dao.mybatis.EntityDaoSqlMapImpl;

/**
 * @author SSGJ
 * @date 2018-01-18 10:11:47
 */
@Service
public class EtProcessManagerDaoSqlMapImpl extends EntityDaoSqlMapImpl<EtProcessManager> implements EtProcessManagerDao {

    @Override
    public void updateEtProcessManagerByPmId(EtProcessManager etProcessManager) {
        String statement = "update" + etProcessManager.getClass().getSimpleName();
        super.getSqlSession().update(statement, etProcessManager);
    }
}
