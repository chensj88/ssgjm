package cn.com.winning.ssgj.service;

import java.text.ParseException;
import java.util.List;

import cn.com.winning.ssgj.domain.SysProductDataInfo;
import cn.com.winning.ssgj.domain.SysProductInterfaceInfo;
import cn.com.winning.ssgj.domain.SysUserInfo;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
public interface SysProductInterfaceInfoService {

    Integer createSysProductInterfaceInfo(SysProductInterfaceInfo t);

    int modifySysProductInterfaceInfo(SysProductInterfaceInfo t);

    int removeSysProductInterfaceInfo(SysProductInterfaceInfo t);

    SysProductInterfaceInfo getSysProductInterfaceInfo(SysProductInterfaceInfo t);

    List<SysProductInterfaceInfo> getSysProductInterfaceInfoList(SysProductInterfaceInfo t);

    Integer getSysProductInterfaceInfoCount(SysProductInterfaceInfo t);

    List<SysProductInterfaceInfo> getSysProductInterfaceInfoPaginatedList(SysProductInterfaceInfo t);

    /**
     * 从List对象集合中生成接口ID的List
     * @param interfaceInfoList
     * @return interIds
     */
    List<String> getInterfaceIds(List<SysProductInterfaceInfo> interfaceInfoList);

    /**
     * 根据ID字符串来查询待删除数据
     * @param pdId
     * @param interIds
     * @return
     */
    List<SysProductInterfaceInfo> getSysProductInterfaceInfoByIds(Integer pdId, String interIds);

    /**
     * 移除映射信息
     * @param idList
     */
    void removeProductInterInfo(String idList);

    /**
     * 添加新的映射信息信息
     * @param idList
     * @param userInfo
     */
    void addSysProductInterfaceInfoMapping(String idList, SysUserInfo userInfo) throws ParseException;
}