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
    /**
     * 模糊查询--统计总数
     * @param t
     * @return
     */
    public Integer selectSysThirdInterfaceInfoCountByselective(SysThirdInterfaceInfo t);

    /**
     * 模糊查询--分页数据
     * @param t
     * @return
     */
    public List<SysThirdInterfaceInfo> selectSysThirdInterfaceInfoPaginatedListByselective(SysThirdInterfaceInfo t);

    /**
     * 根据Map传入Id查询数据
     * @param sysThirdInterfaceInfo
     * @return
     */
    List<SysThirdInterfaceInfo> selectSysThirdInterfaceInfoListByIds(SysThirdInterfaceInfo sysThirdInterfaceInfo);

    /**
     * 按照名称查询所有符合要求的数据
     * @param info
     * @return
     */
    List<SysThirdInterfaceInfo> selectSysThirdInterfaceInfoListForNames(SysThirdInterfaceInfo info);
}
