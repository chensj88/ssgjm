package cn.com.winning.ssgj.service;

import java.text.ParseException;
import java.util.List;

import cn.com.winning.ssgj.domain.SysProductShInfo;
import cn.com.winning.ssgj.domain.SysUserInfo;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
public interface SysProductShInfoService {

    Integer createSysProductShInfo(SysProductShInfo t);

    int modifySysProductShInfo(SysProductShInfo t);

    int removeSysProductShInfo(SysProductShInfo t);

    SysProductShInfo getSysProductShInfo(SysProductShInfo t);

    List<SysProductShInfo> getSysProductShInfoList(SysProductShInfo t);

    Integer getSysProductShInfoCount(SysProductShInfo t);

    List<SysProductShInfo> getSysProductShInfoPaginatedList(SysProductShInfo t);

    /**
     * 生成ID的list
     * @param shInfoList
     * @return
     */
    List<String> getSoftwareHardwareInfoId(List<SysProductShInfo> shInfoList);

    List<SysProductShInfo> getSysProductShInfoByIds(Integer pdId, String shIds);

    int removeSysProductShInfoMapping(String idList, SysUserInfo userInfo);

    int addSysProductShInfoMapping(String idList, SysUserInfo userInfo) throws ParseException;
}