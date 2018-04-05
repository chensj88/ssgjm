package cn.com.winning.ssgj.service;

import java.util.List;

import cn.com.winning.ssgj.domain.EtInterfaceInfo;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
public interface EtInterfaceInfoService {

    Integer createEtInterfaceInfo(EtInterfaceInfo t);

    int modifyEtInterfaceInfo(EtInterfaceInfo t);

    int removeEtInterfaceInfo(EtInterfaceInfo t);

    EtInterfaceInfo getEtInterfaceInfo(EtInterfaceInfo t);

    List<EtInterfaceInfo> getEtInterfaceInfoList(EtInterfaceInfo t);

    Integer getEtInterfaceInfoCount(EtInterfaceInfo t);

    List<EtInterfaceInfo> getEtInterfaceInfoPaginatedList(EtInterfaceInfo t);
    /**
     * 根据pmid获取接口信息
     * @return
     */
    List<EtInterfaceInfo> selectEtInterfaceInfoMergePageList(EtInterfaceInfo etInterfaceInfo);

    List<EtInterfaceInfo>selectEtInterfaceInfoMergeList(EtInterfaceInfo etInterfaceInfo);

    /**
     * 根据pmid获取接口信息数
     * @return
     */
    Integer selectEtInterfaceInfoMergeCount(EtInterfaceInfo etInterfaceInfo);

}