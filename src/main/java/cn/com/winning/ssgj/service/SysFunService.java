package cn.com.winning.ssgj.service;

import java.util.List;

import cn.com.winning.ssgj.domain.SysFun;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
public interface SysFunService {

    Integer createSysFun(SysFun t);

    int modifySysFun(SysFun t);

    int removeSysFun(SysFun t);

    SysFun getSysFun(SysFun t);

    List<SysFun> getSysFunList(SysFun t);

    Integer getSysFunCount(SysFun t);

    List<SysFun> getSysFunPaginatedList(SysFun t);

}