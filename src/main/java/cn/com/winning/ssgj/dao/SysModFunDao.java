package cn.com.winning.ssgj.dao;

import cn.com.winning.ssgj.domain.SysModFun;
import cn.com.winning.ssgj.dao.EntityDao;

import java.util.List;
import java.util.Map;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:47
 */
public interface SysModFunDao extends EntityDao<SysModFun> {

    public List<Long> selectFunIdsList(SysModFun fun);

    void deleteSysModFuncForIds(Map<String, Object> param);
}
