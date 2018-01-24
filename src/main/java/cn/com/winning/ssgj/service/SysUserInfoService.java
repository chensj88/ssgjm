package cn.com.winning.ssgj.service;

import java.util.List;

import cn.com.winning.ssgj.domain.SysUserInfo;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
public interface SysUserInfoService {

    Integer createSysUserInfo(SysUserInfo t);

    int modifySysUserInfo(SysUserInfo t);

    int removeSysUserInfo(SysUserInfo t);

    SysUserInfo getSysUserInfo(SysUserInfo t);

    List<SysUserInfo> getSysUserInfoList(SysUserInfo t);

    Integer getSysUserInfoCount(SysUserInfo t);

    List<SysUserInfo> getSysUserInfoPaginatedList(SysUserInfo t);

}