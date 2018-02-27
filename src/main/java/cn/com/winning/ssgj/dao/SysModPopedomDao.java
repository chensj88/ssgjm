package cn.com.winning.ssgj.dao;

import cn.com.winning.ssgj.domain.SysModPopedom;
import cn.com.winning.ssgj.dao.EntityDao;

import java.util.List;
import java.util.Map;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:47
 */
public interface SysModPopedomDao extends EntityDao<SysModPopedom> {

    List<Long> selectModuleIdList(SysModPopedom modPopedom);

    void deleteSysModPopedomForIds(Map<String, Object> param);

}
