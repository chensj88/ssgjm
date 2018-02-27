package cn.com.winning.ssgj.service;

import java.util.List;

import cn.com.winning.ssgj.domain.SysDataInfo;
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

    Integer getSysThirdInterfaceInfoCountForSelectiveKey(SysThirdInterfaceInfo t);

    List<SysThirdInterfaceInfo> getSysThirdInterfaceInfoPaginatedListForSelectiveKey(SysThirdInterfaceInfo t);

    /**
     * 根据Map中封装的ID集合来获取符合要求的接口信息
     * @param sysThirdInterfaceInfo
     * @return
     */
    List<SysThirdInterfaceInfo> getSysThirdInterfaceInfoListByIds(SysThirdInterfaceInfo sysThirdInterfaceInfo);

    /**
     * 按照名称查询所有符合要求的数据
     * @param interfaceInfo
     * @return
     */
    List<SysThirdInterfaceInfo> getSysThirdInterfaceInfoListForNames(SysThirdInterfaceInfo interfaceInfo);
}