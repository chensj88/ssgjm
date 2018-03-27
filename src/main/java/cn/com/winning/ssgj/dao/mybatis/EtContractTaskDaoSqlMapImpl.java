package cn.com.winning.ssgj.dao.mybatis;

import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.EtContractTaskDao;
import cn.com.winning.ssgj.domain.EtContractTask;
import cn.com.winning.ssgj.dao.mybatis.EntityDaoSqlMapImpl;

import java.util.List;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-03-26 16:19:08
 */
@Service
public class EtContractTaskDaoSqlMapImpl extends EntityDaoSqlMapImpl<EtContractTask> implements EtContractTaskDao {

    @Override
    public List<EtContractTask> selectEtContractTaskMergePageList(EtContractTask paramT) {
        return super.getSqlSession().selectList("selectEtContractTaskMergePageList",paramT);
    }

    @Override
    public int selectEtContractTaskMergeCount(EtContractTask paramT) {
        return super.getSqlSession().selectOne("selectEtContractTaskMergeCount",paramT);
    }
}
