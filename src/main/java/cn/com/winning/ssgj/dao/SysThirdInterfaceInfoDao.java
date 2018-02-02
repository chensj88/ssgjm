package cn.com.winning.ssgj.dao;

import cn.com.winning.ssgj.domain.SysThirdInterfaceInfo;
import cn.com.winning.ssgj.dao.EntityDao;

import java.util.List;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:47
 */
public interface SysThirdInterfaceInfoDao extends EntityDao<SysThirdInterfaceInfo> {

    public Integer selectSysThirdInterfaceInfoCountByselective(SysThirdInterfaceInfo t);

    public List<SysThirdInterfaceInfo> selectSysThirdInterfaceInfoPaginatedListByselective(SysThirdInterfaceInfo t);
}
