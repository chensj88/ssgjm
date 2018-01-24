package cn.com.winning.ssgj.service;

import java.util.List;

import cn.com.winning.ssgj.domain.SysFlowInfo;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
public interface SysFlowInfoService {

    Integer createSysFlowInfo(SysFlowInfo t);

    int modifySysFlowInfo(SysFlowInfo t);

    int removeSysFlowInfo(SysFlowInfo t);

    SysFlowInfo getSysFlowInfo(SysFlowInfo t);

    List<SysFlowInfo> getSysFlowInfoList(SysFlowInfo t);

    Integer getSysFlowInfoCount(SysFlowInfo t);

    List<SysFlowInfo> getSysFlowInfoPaginatedList(SysFlowInfo t);

}