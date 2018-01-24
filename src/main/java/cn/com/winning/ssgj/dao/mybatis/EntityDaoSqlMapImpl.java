package cn.com.winning.ssgj.dao.mybatis;

import cn.com.winning.ssgj.dao.EntityDao;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.dao.DataAccessException;

import javax.annotation.Resource;
import java.util.List;


public class EntityDaoSqlMapImpl<T> extends SqlSessionDaoSupport implements EntityDao<T> {

    @Resource
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    public static final String PREFIX_INSERT = "insert";
    public static final String PREFIX_UPDATE = "update";
    public static final String PREFIX_DELETE = "delete";
    public static final String PREFIX_SELECT = "select";
    public static final String SUFFIX_COUNT = "Count";
    public static final String SUFFIX_LIST = "List";
    public static final String SUFFIX_PAGINATED_LIST = "PaginatedList";

    public int insertEntity(T t) throws DataAccessException {
        String statement = "insert" + t.getClass().getSimpleName();
        return super.getSqlSession().insert(statement, t);
    }

    public int updateEntity(T t) throws DataAccessException {
        String statement = "update" + t.getClass().getSimpleName();
        return super.getSqlSession().update(statement, t);
    }

    public int deleteEntity(T t) throws DataAccessException {
        String statement = "delete" + t.getClass().getSimpleName();
        return super.getSqlSession().update(statement, t);
    }

    public T selectEntity(T t) throws DataAccessException {
        String statement = "select" + t.getClass().getSimpleName();
        return super.getSqlSession().selectOne(statement, t);
    }

    public Object selectEntityCount(T t) throws DataAccessException {
        String statement = "select" + t.getClass().getSimpleName() + "Count";
        return super.getSqlSession().selectOne(statement, t);
    }

    public List<T> selectEntityList(T t) throws DataAccessException {
        String statement = "select" + t.getClass().getSimpleName() + "List";
        return super.getSqlSession().selectList(statement, t);
    }

    public List<T> selectEntityPaginatedList(T t) throws DataAccessException {
        String statement = "select" + t.getClass().getSimpleName() + "PaginatedList";
        return super.getSqlSession().selectList(statement, t);
    }
}