package cn.com.winning.ssgj.service;

import java.util.List;

import cn.com.winning.ssgj.domain.SysDictInfo;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
public interface SysDictInfoService {

    Integer createSysDictInfo(SysDictInfo t);

    int modifySysDictInfo(SysDictInfo t);

    int removeSysDictInfo(SysDictInfo t);

    SysDictInfo getSysDictInfo(SysDictInfo t);

    List<SysDictInfo> getSysDictInfoList(SysDictInfo t);

    Integer getSysDictInfoCount(SysDictInfo t);

    List<SysDictInfo> getSysDictInfoPaginatedList(SysDictInfo t);

    List<SysDictInfo> getSysDictInfoPageForAnd(SysDictInfo t);

    Integer getSysDictInfoCountForAnd(SysDictInfo t);

    List<SysDictInfo> getSysDictInfoPageForOr(SysDictInfo t);

    Integer getSysDictInfoCountForOr(SysDictInfo t);

    boolean existDictValue(SysDictInfo dictInfo);
}