package cn.com.winning.ssgj.service;

import java.util.List;

import cn.com.winning.ssgj.domain.SysProductInterfaceInfo;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
public interface SysProductInterfaceInfoService {

    Integer createSysProductInterfaceInfo(SysProductInterfaceInfo t);

    int modifySysProductInterfaceInfo(SysProductInterfaceInfo t);

    int removeSysProductInterfaceInfo(SysProductInterfaceInfo t);

    SysProductInterfaceInfo getSysProductInterfaceInfo(SysProductInterfaceInfo t);

    List<SysProductInterfaceInfo> getSysProductInterfaceInfoList(SysProductInterfaceInfo t);

    Integer getSysProductInterfaceInfoCount(SysProductInterfaceInfo t);

    List<SysProductInterfaceInfo> getSysProductInterfaceInfoPaginatedList(SysProductInterfaceInfo t);

}