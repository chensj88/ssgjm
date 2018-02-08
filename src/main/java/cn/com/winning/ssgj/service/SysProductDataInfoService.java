package cn.com.winning.ssgj.service;

import java.util.List;
import java.util.Map;

import cn.com.winning.ssgj.domain.SysProductDataInfo;

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
}