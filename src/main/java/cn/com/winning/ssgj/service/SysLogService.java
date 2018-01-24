package cn.com.winning.ssgj.service;

import java.util.List;

import cn.com.winning.ssgj.domain.SysLog;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
public interface SysLogService {

    Integer createSysLog(SysLog t);

    int modifySysLog(SysLog t);

    int removeSysLog(SysLog t);

    SysLog getSysLog(SysLog t);

    List<SysLog> getSysLogList(SysLog t);

    Integer getSysLogCount(SysLog t);

    List<SysLog> getSysLogPaginatedList(SysLog t);

}