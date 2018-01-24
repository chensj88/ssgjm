package cn.com.winning.ssgj.service;

import java.util.List;

import cn.com.winning.ssgj.domain.SysProductShInfo;

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

}