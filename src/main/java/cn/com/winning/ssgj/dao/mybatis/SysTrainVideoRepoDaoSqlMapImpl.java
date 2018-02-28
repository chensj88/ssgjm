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
    public List<SysTrainVideoRepo> selectSysTrainVideoRepoTypeList(SysTrainVideoRepo t) {
        return this.getSqlSession().selectList("selectSysTrainVideoRepoTypeList",t);
    }
}
