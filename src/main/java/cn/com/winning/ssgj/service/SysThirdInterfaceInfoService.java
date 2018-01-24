package cn.com.winning.ssgj.service;

import java.util.List;

import cn.com.winning.ssgj.domain.SysThirdInterfaceInfo;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
public interface SysThirdInterfaceInfoService {

    Integer createSysThirdInterfaceInfo(SysThirdInterfaceInfo t);

    int modifySysThirdInterfaceInfo(SysThirdInterfaceInfo t);

    int removeSysThirdInterfaceInfo(SysThirdInterfaceInfo t);

    SysThirdInterfaceInfo getSysThirdInterfaceInfo(SysThirdInterfaceInfo t);

    List<SysThirdInterfaceInfo> getSysThirdInterfaceInfoList(SysThirdInterfaceInfo t);

    Integer getSysThirdInterfaceInfoCount(SysThirdInterfaceInfo t);

    List<SysThirdInterfaceInfo> getSysThirdInterfaceInfoPaginatedList(SysThirdInterfaceInfo t);

}