package cn.com.winning.ssgj.dao;

import cn.com.winning.ssgj.domain.SysReportInfo;
import cn.com.winning.ssgj.dao.EntityDao;

import java.util.List;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:47
 */
public interface SysReportInfoDao extends EntityDao<SysReportInfo> {

    public Integer selectSysReportInfoCountByselective(SysReportInfo t);
    public List<SysReportInfo> selectSysReportInfoPaginatedListByselective(SysReportInfo t);

}
