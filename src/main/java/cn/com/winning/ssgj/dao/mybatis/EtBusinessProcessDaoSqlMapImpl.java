package cn.com.winning.ssgj.dao.mybatis;

import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.EtBusinessProcessDao;
import cn.com.winning.ssgj.domain.EtBusinessProcess;
import cn.com.winning.ssgj.dao.mybatis.EntityDaoSqlMapImpl;

import java.util.List;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-04-12 10:44:43
 */
@Service
public class EtBusinessProcessDaoSqlMapImpl extends EntityDaoSqlMapImpl<EtBusinessProcess> implements EtBusinessProcessDao {


    @Override
    public List<Long> selectUnInitEtBusinessProcess(EtBusinessProcess process) {
        return super.getSqlSession().selectList("selectUnInitEtBusinessProcess",process);
    }

    @Override
    public int updateEtBusinessProcessConfigBatch(EtBusinessProcess process) {
        return  super.getSqlSession().update("updateEtBusinessProcessConfigBatch",process);
    }
}
