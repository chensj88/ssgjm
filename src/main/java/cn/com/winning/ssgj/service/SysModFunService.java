package cn.com.winning.ssgj.service;

import java.util.List;

import cn.com.winning.ssgj.domain.SysModFun;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
public interface SysModFunService {

    Integer createSysModFun(SysModFun t);

    int modifySysModFun(SysModFun t);

    int removeSysModFun(SysModFun t);

    SysModFun getSysModFun(SysModFun t);

    List<SysModFun> getSysModFunList(SysModFun t);

    Integer getSysModFunCount(SysModFun t);

    List<SysModFun> getSysModFunPaginatedList(SysModFun t);

}