package cn.com.winning.ssgj.service;

import java.util.List;

import cn.com.winning.ssgj.domain.SysFun;
import cn.com.winning.ssgj.domain.expand.NodeTree;

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

    List<SysFun> getSysFunPaginatedListFuzzy(SysFun fun);

    int getSysFunCountFuzzy(SysFun fun);

    List<NodeTree> createSysFunTree(SysFun fun);
}