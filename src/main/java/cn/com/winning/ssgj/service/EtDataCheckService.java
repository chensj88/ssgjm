package cn.com.winning.ssgj.service;

import java.util.List;

import cn.com.winning.ssgj.domain.EtDataCheck;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
public interface EtDataCheckService {

    Integer createEtDataCheck(EtDataCheck t);

    int modifyEtDataCheck(EtDataCheck t);

    int removeEtDataCheck(EtDataCheck t);

    EtDataCheck getEtDataCheck(EtDataCheck t);

    List<EtDataCheck> getEtDataCheckList(EtDataCheck t);

    Integer getEtDataCheckCount(EtDataCheck t);

    List<EtDataCheck> getEtDataCheckPaginatedList(EtDataCheck t);

    List<EtDataCheck> selectEtDataCheckListByPmIdAndDataType(EtDataCheck etDataCheck);

}