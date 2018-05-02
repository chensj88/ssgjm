package cn.com.winning.ssgj.dao;

import cn.com.winning.ssgj.domain.SysDataInfo;
import cn.com.winning.ssgj.dao.EntityDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author SSGJ
 * @date 2018-01-18 10:11:47
 */
public interface SysDataInfoDao extends EntityDao<SysDataInfo> {

    public Integer selectSysDataInfoCountForSelectiveKey(SysDataInfo t);

    public List<SysDataInfo> selectSysDataInfoPaginatedListForSelectiveKey(SysDataInfo t);

    public List<SysDataInfo> selectSysDataInfoListForSelectiveKey(SysDataInfo t);

    public List<SysDataInfo> selectSysDataInfoListByIds(SysDataInfo t);

    public List<SysDataInfo> selectSysDataInfoPaginatedListByIds(SysDataInfo t);

    public Integer selectSysDataInfoCountByIds(SysDataInfo t);

    public List<SysDataInfo> selectSysDataInfoPaginatedListByPmIdAndDataType(SysDataInfo t);

    public Integer countSysDataInfoListByPmIdAndDataType(SysDataInfo sysDataInfo);

    public List<SysDataInfo> selectSysDataInfoListByPmIdAndDataType(SysDataInfo t);

    public List<SysDataInfo> selectSysDataInfoListForORKey(SysDataInfo t);

    Integer countTable(String tableName);

    List<SysDataInfo> selectNonSelectedSysDataInfoListByProductId(SysDataInfo d);

    List<SysDataInfo> selectSelectedSysDataInfoListByProductId(SysDataInfo d);
}
