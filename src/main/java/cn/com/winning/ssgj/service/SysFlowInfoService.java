package cn.com.winning.ssgj.service;

import java.util.List;

import cn.com.winning.ssgj.domain.SysFlowInfo;
import cn.com.winning.ssgj.domain.SysProductFlowInfo;

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

    List<SysFlowInfo> querySysFlowInfoList(SysFlowInfo t);
    List<SysFlowInfo> querySysFlowInfoListForName(SysFlowInfo t);

    /**
     * 查询子流程的流程编码
     * @param flowCode 上级流程的编码
     * @param flowType 流程类型
     * @return flowCode
     */
    String createFlowCode(String flowCode,String flowType);

    Integer getSysFlowInfoCountForSelective(SysFlowInfo t);

    List<SysFlowInfo> getSysFlowInfoPaginatedListForSelective(SysFlowInfo t);

    List<SysFlowInfo> getSysFlowInfoListForSelectiveKey(SysFlowInfo flowInfo);

    Integer getSysFlowInfoCountForSelectiveKey(SysFlowInfo flowInfo);

    List<String> getFlowInfoId(List<SysProductFlowInfo> flowInfoList);

    List<SysFlowInfo> getSysFlowInfoListById(SysFlowInfo flowInfo);
}