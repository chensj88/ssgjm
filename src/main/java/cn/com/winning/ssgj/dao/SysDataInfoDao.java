package cn.com.winning.ssgj.dao;

import cn.com.winning.ssgj.domain.SysDataInfo;
import cn.com.winning.ssgj.dao.EntityDao;

import java.util.List;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:47
 */
public interface SysDataInfoDao extends EntityDao<SysDataInfo> {

    public Integer selectSysDataInfoCountByselective(SysDataInfo t);
    public List<SysDataInfo> selectSysDataInfoPaginatedListByselective(SysDataInfo t);
}
