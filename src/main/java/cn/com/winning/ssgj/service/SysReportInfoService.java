package cn.com.winning.ssgj.service;

import java.util.List;

import cn.com.winning.ssgj.domain.SysReportInfo;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
public interface SysReportInfoService {

    Integer createSysReportInfo(SysReportInfo t);

    int modifySysReportInfo(SysReportInfo t);

    int removeSysReportInfo(SysReportInfo t);

    SysReportInfo getSysReportInfo(SysReportInfo t);

    List<SysReportInfo> getSysReportInfoList(SysReportInfo t);

    Integer getSysReportInfoCount(SysReportInfo t);

    List<SysReportInfo> getSysReportInfoPaginatedList(SysReportInfo t);

}