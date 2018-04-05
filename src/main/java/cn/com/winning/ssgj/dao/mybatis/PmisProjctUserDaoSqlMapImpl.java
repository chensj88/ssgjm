package cn.com.winning.ssgj.dao.mybatis;

import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.PmisProjctUserDao;
import cn.com.winning.ssgj.domain.PmisProjctUser;
import cn.com.winning.ssgj.dao.mybatis.EntityDaoSqlMapImpl;

import java.util.List;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:47
 */
@Service
public class PmisProjctUserDaoSqlMapImpl extends EntityDaoSqlMapImpl<PmisProjctUser> implements PmisProjctUserDao {

    @Override
    public List<Long> selectPmisProjctUserIdListByProjectIdList(PmisProjctUser projctUser) {
        return super.getSqlSession().selectList("selectPmisProjctUserIdListByProjectIdList",projctUser);
    }
}
