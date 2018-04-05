package cn.com.winning.ssgj.service;

import java.util.List;

import cn.com.winning.ssgj.domain.PmisContractInfo;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
public interface PmisContractInfoService {

    Integer createPmisContractInfo(PmisContractInfo t);

    int modifyPmisContractInfo(PmisContractInfo t);

    int removePmisContractInfo(PmisContractInfo t);

    PmisContractInfo getPmisContractInfo(PmisContractInfo t);

    List<PmisContractInfo> getPmisContractInfoList(PmisContractInfo t);

    Integer getPmisContractInfoCount(PmisContractInfo t);

    List<PmisContractInfo> getPmisContractInfoPaginatedList(PmisContractInfo t);

}