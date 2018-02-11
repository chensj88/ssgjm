package cn.com.winning.ssgj.service;

import java.text.ParseException;
import java.util.List;

import cn.com.winning.ssgj.domain.SysProductFlowInfo;
import cn.com.winning.ssgj.domain.SysUserInfo;

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

    List<SysProductFlowInfo> getSysProductFlowInfoByPdIdAndFlowId(Integer pdId, String bdIds);

    void addSysProductFlowInfoMapping(String idList, SysUserInfo user) throws ParseException;

    Integer removeSysProductFlowInfoMappingByIds(String idList);
}