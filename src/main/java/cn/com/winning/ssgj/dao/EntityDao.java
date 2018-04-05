package cn.com.winning.ssgj.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

public interface EntityDao<T> {
    int insertEntity(T paramT) throws DataAccessException;

    int updateEntity(T paramT) throws DataAccessException;

    int deleteEntity(T paramT) throws DataAccessException;

    T selectEntity(T paramT) throws DataAccessException;

    Object selectEntityCount(T paramT) throws DataAccessException;

    List<T> selectEntityList(T paramT) throws DataAccessException;

    List<T> selectEntityPaginatedList(T paramT) throws DataAccessException;
}