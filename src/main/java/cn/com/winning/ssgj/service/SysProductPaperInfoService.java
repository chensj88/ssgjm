package cn.com.winning.ssgj.service;

import java.text.ParseException;
import java.util.List;

import cn.com.winning.ssgj.domain.SysProductPaperInfo;
import cn.com.winning.ssgj.domain.SysUserInfo;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
public interface SysProductPaperInfoService {

    Integer createSysProductPaperInfo(SysProductPaperInfo t);

    int modifySysProductPaperInfo(SysProductPaperInfo t);

    int removeSysProductPaperInfo(SysProductPaperInfo t);

    SysProductPaperInfo getSysProductPaperInfo(SysProductPaperInfo t);

    List<SysProductPaperInfo> getSysProductPaperInfoList(SysProductPaperInfo t);

    Integer getSysProductPaperInfoCount(SysProductPaperInfo t);

    List<SysProductPaperInfo> getSysProductPaperInfoPaginatedList(SysProductPaperInfo t);

    List<String> getSysPaperInfoIds(List<SysProductPaperInfo> paperInfoList);

    List<SysProductPaperInfo> getSysProductPaperInfoByIds(Integer pdId, String reportids);

    int removeSysProductPaperInfoMapping(String idList, SysUserInfo userInfo);

    int addSysProductPaperInfoMapping(String idList, SysUserInfo userInfo) throws ParseException;
}