package cn.com.winning.ssgj.service;

import java.util.List;

import cn.com.winning.ssgj.domain.SysProductPaperInfo;

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

}