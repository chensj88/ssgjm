package cn.com.winning.ssgj.service;

import java.util.List;

import cn.com.winning.ssgj.domain.EtEasyDataCheck;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
public interface EtEasyDataCheckService {

    Integer createEtEasyDataCheck(EtEasyDataCheck t);

    int modifyEtEasyDataCheck(EtEasyDataCheck t);

    int removeEtEasyDataCheck(EtEasyDataCheck t);

    EtEasyDataCheck getEtEasyDataCheck(EtEasyDataCheck t);

    List<EtEasyDataCheck> getEtEasyDataCheckList(EtEasyDataCheck t);

    Integer getEtEasyDataCheckCount(EtEasyDataCheck t);

    List<EtEasyDataCheck> getEtEasyDataCheckPaginatedList(EtEasyDataCheck t);

}