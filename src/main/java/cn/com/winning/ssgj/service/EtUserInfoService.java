package cn.com.winning.ssgj.service;

import java.util.List;

import cn.com.winning.ssgj.domain.EtUserInfo;
import cn.com.winning.ssgj.domain.SysUserInfo;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
public interface EtUserInfoService {

    Integer createEtUserInfo(EtUserInfo t);

    int modifyEtUserInfo(EtUserInfo t);

    int removeEtUserInfo(EtUserInfo t);

    EtUserInfo getEtUserInfo(EtUserInfo t);

    List<EtUserInfo> getEtUserInfoList(EtUserInfo t);

    Integer getEtUserInfoCount(EtUserInfo t);

    List<EtUserInfo> getEtUserInfoPaginatedList(EtUserInfo t);
    
    void generateEtUserInfo(EtUserInfo etUserInfo, String path);
    
    void createEtUserInfoList(List<List<Object>> userList);
}