package cn.com.winning.ssgj.dao;

import cn.com.winning.ssgj.domain.SysFun;
import cn.com.winning.ssgj.dao.EntityDao;
import cn.com.winning.ssgj.domain.SysModule;
import org.springframework.dao.DataAccessException;

import java.util.List;
import java.util.Map;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:47
 */
public interface SysFunDao extends EntityDao<SysFun> {

    public Integer selectSysFunCountFuzzy(SysFun t) throws DataAccessException;

    public List<SysFun> selectSysFunPaginatedListFuzzy(SysFun t) throws DataAccessException;

    public int selectSysFunMaxOrderValue() throws DataAccessException;

    public List<SysFun> selectSysFunListForName(SysFun fun)  throws DataAccessException;

    public List<SysFun> selectSysFunByModuleInfo(Map<String,Object> param);
}
