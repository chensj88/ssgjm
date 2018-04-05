package cn.com.winning.ssgj.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import cn.com.winning.ssgj.domain.SysProductDataInfo;
import cn.com.winning.ssgj.domain.SysUserInfo;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
public interface SysProductDataInfoService {

    Integer createSysProductDataInfo(SysProductDataInfo t);

    int modifySysProductDataInfo(SysProductDataInfo t);

    int removeSysProductDataInfo(SysProductDataInfo t);

    SysProductDataInfo getSysProductDataInfo(SysProductDataInfo t);

    List<SysProductDataInfo> getSysProductDataInfoList(SysProductDataInfo t);

    Integer getSysProductDataInfoCount(SysProductDataInfo t);

    List<SysProductDataInfo> getSysProductDataInfoPaginatedList(SysProductDataInfo t);

    List<String> getDataInfoId(List<SysProductDataInfo> sysProductDataInfoList);


    List<SysProductDataInfo> getSysProductDataInfoByIds(Integer pdId,String bdIds);

    Integer removeSysProductDataInfo(String idList);

    /**
     * 添加或维护新的映射关系
     * @param idListString 前台传入ID字符串, 实例 156,1;156,2
     * @param user
     * @throws ParseException
     */
    void addSysProductDataInfoMapping(String idListString, SysUserInfo user) throws ParseException;
}