package cn.com.winning.ssgj.dao.mybatis;

import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.SysTrainVideoRepoDao;
import cn.com.winning.ssgj.domain.SysTrainVideoRepo;
import cn.com.winning.ssgj.dao.mybatis.EntityDaoSqlMapImpl;

import java.util.List;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-02-28 09:00:20
 */
@Service
public class SysTrainVideoRepoDaoSqlMapImpl extends EntityDaoSqlMapImpl<SysTrainVideoRepo> implements SysTrainVideoRepoDao {

    @Override
    public int selectSysTrainVideoRepoCountBySelective(SysTrainVideoRepo repo) {
        String statement = "selectSysTrainVideoRepoCountBySelective";
        return super.getSqlSession().selectOne(statement,repo);
    }

    @Override
    public List<SysTrainVideoRepo> selectSysTrainVideoRepoPageListBySelective(SysTrainVideoRepo repo) {
        String statement = "selectSysTrainVideoRepoPageListBySelective";
        return super.getSqlSession().selectList(statement,repo);
    }
}
