package cn.com.winning.ssgj.dao.mybatis;

import cn.com.winning.ssgj.dao.CommonQueryDao;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author chensj
 * @title
 * @email chensj@winning.com.cn
 * @package cn.com.winning.ssgj.dao.mybatis
 * @date: 2018-11-01 16:03
 */
@Service
public class CommonQueryDaoSqlMapImpl extends SqlSessionDaoSupport implements CommonQueryDao {
    @Resource
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }
    @Override
    public Set<String> listUserRolesByUserId(@Param(value="userId")String userId) {
        return new LinkedHashSet<String>(super.getSqlSession().selectList("listUserRolesByUserId",userId));
    }

    @Override
    public List<String> loadButtonFlagForPageByUrlAndRoles(@Param("params")Map<String, String> params) {
        return super.getSqlSession().selectList("loadButtonFlagForPageByUrlAndRoles",params);
    }
}
