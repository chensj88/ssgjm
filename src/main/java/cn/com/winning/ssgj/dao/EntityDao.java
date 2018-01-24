package cn.com.winning.ssgj.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

public interface EntityDao<T> {
    public int insertEntity(T paramT) throws DataAccessException;

    public int updateEntity(T paramT) throws DataAccessException;

    public int deleteEntity(T paramT) throws DataAccessException;

    public T selectEntity(T paramT) throws DataAccessException;

    public Object selectEntityCount(T paramT) throws DataAccessException;

    public List<T> selectEntityList(T paramT) throws DataAccessException;

    public List<T> selectEntityPaginatedList(T paramT) throws DataAccessException;
}