package cn.com.winning.ssgj.dao;

import cn.com.winning.ssgj.domain.SysDataInfo;
import cn.com.winning.ssgj.domain.SysSoftHardwareInfo;
import cn.com.winning.ssgj.dao.EntityDao;

import java.util.List;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:47
 */
public interface SysSoftHardwareInfoDao extends EntityDao<SysSoftHardwareInfo> {

    public Integer selectSysSoftHardwareInfoCountByselective(SysSoftHardwareInfo t);

    public List<SysSoftHardwareInfo> selectSysSoftHardwareInfoPaginatedListByselective(SysSoftHardwareInfo t);

    List<SysSoftHardwareInfo> selectSysSoftHardwareInfoListByIds(SysSoftHardwareInfo data);

    List<SysSoftHardwareInfo> selectSysSoftHardwareInfoListForNames(SysSoftHardwareInfo info);
}
