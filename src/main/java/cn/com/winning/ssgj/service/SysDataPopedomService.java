package cn.com.winning.ssgj.service;

import java.util.List;

import cn.com.winning.ssgj.domain.SysDataPopedom;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
public interface SysDataPopedomService {

    Integer createSysDataPopedom(SysDataPopedom t);

    int modifySysDataPopedom(SysDataPopedom t);

    int removeSysDataPopedom(SysDataPopedom t);

    SysDataPopedom getSysDataPopedom(SysDataPopedom t);

    List<SysDataPopedom> getSysDataPopedomList(SysDataPopedom t);

    Integer getSysDataPopedomCount(SysDataPopedom t);

    List<SysDataPopedom> getSysDataPopedomPaginatedList(SysDataPopedom t);

}