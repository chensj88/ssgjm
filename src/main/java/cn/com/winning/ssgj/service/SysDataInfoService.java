package cn.com.winning.ssgj.service;

import java.util.List;

import cn.com.winning.ssgj.domain.SysDataInfo;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
public interface SysDataInfoService {

    Integer createSysDataInfo(SysDataInfo t);

    int modifySysDataInfo(SysDataInfo t);

    int removeSysDataInfo(SysDataInfo t);

    SysDataInfo getSysDataInfo(SysDataInfo t);

    List<SysDataInfo> getSysDataInfoList(SysDataInfo t);

    Integer getSysDataInfoCount(SysDataInfo t);

    List<SysDataInfo> getSysDataInfoPaginatedList(SysDataInfo t);

    Integer getSysDataInfoCountForSelectiveKey(SysDataInfo t);

    List<SysDataInfo> getSysDataInfoPaginatedListForSelectiveKey(SysDataInfo t);

    List<SysDataInfo> getSysDataInfoListForSelectiveKey(SysDataInfo t);

    List<SysDataInfo> getSysDataInfoListById(SysDataInfo t);

}