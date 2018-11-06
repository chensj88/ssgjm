package cn.com.winning.ssgj.service;

import java.util.List;

import cn.com.winning.ssgj.domain.SysModPopedom;
import cn.com.winning.ssgj.domain.expand.NodeTree;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
public interface SysModPopedomService {

    Integer createSysModPopedom(SysModPopedom t);

    int modifySysModPopedom(SysModPopedom t);

    int removeSysModPopedom(SysModPopedom t);

    SysModPopedom getSysModPopedom(SysModPopedom t);

    List<SysModPopedom> getSysModPopedomList(SysModPopedom t);

    Integer getSysModPopedomCount(SysModPopedom t);

    List<SysModPopedom> getSysModPopedomPaginatedList(SysModPopedom t);

    List<Long> getModuleIdList(SysModPopedom modPopedom);

    void createSysModPopedomForIdList(String idList);

    void modifyModPopedomMapping(String idList);

    List<SysModPopedom> getSysModPopedomHasPopedomList(SysModPopedom modPopedom);

    void createSysModPopedomByList(List<SysModPopedom> modPopedomList);
}