package cn.com.winning.ssgj.service;

import java.util.List;

import cn.com.winning.ssgj.domain.SysProductFlowInfo;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
public interface SysProductFlowInfoService {

    Integer createSysProductFlowInfo(SysProductFlowInfo t);

    int modifySysProductFlowInfo(SysProductFlowInfo t);

    int removeSysProductFlowInfo(SysProductFlowInfo t);

    SysProductFlowInfo getSysProductFlowInfo(SysProductFlowInfo t);

    List<SysProductFlowInfo> getSysProductFlowInfoList(SysProductFlowInfo t);

    Integer getSysProductFlowInfoCount(SysProductFlowInfo t);

    List<SysProductFlowInfo> getSysProductFlowInfoPaginatedList(SysProductFlowInfo t);

}