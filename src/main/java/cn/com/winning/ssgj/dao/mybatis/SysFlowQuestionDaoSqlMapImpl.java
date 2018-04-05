package cn.com.winning.ssgj.dao.mybatis;

import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.SysFlowQuestionDao;
import cn.com.winning.ssgj.domain.SysFlowQuestion;
import cn.com.winning.ssgj.dao.mybatis.EntityDaoSqlMapImpl;

import java.util.List;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:47
 */
@Service
public class SysFlowQuestionDaoSqlMapImpl extends EntityDaoSqlMapImpl<SysFlowQuestion> implements SysFlowQuestionDao {

    @Override
    public Integer selectFlowQuestionPageCount(SysFlowQuestion question) {
        String statement = "selectFlowQuestionPageCount";
        return super.getSqlSession().selectOne(statement,question);
    }

    @Override
    public List<SysFlowQuestion> selectFlowQuestionPageList(SysFlowQuestion question) {
        String statement = "selectFlowQuestionPageList";
        return super.getSqlSession().selectList(statement,question);
    }
}
