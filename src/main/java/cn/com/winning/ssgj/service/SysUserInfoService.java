package cn.com.winning.ssgj.service;

import java.util.List;

import cn.com.winning.ssgj.domain.SysUserInfo;
import cn.com.winning.ssgj.domain.expand.FlotDataInfo;

import javax.servlet.http.HttpServletResponse;

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

    Integer getSysUserInfoQueryCount(SysUserInfo t);

    List<SysUserInfo> getSysUserInfoQueryPaginatedList(SysUserInfo t);

    List<SysUserInfo> getSysUserInfoQueryPageListASC(SysUserInfo t);

    List<FlotDataInfo> countUserInfoByType();

    void generateUserInfo(SysUserInfo queryUser, HttpServletResponse response, String fileName);

    void createHospitalUserInfo(List<List<Object>> userList,SysUserInfo userInfo);

    SysUserInfo getSysUserInfoById(long userid);

    /**
     * 根据用户id获取用户信息
     * @param userIdList
     * @return
     */
    List<SysUserInfo> getSysUserInfoListByUserIdList(List<Long> userIdList);
}