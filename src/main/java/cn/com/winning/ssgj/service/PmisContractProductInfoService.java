package cn.com.winning.ssgj.service;

import java.util.List;

import cn.com.winning.ssgj.domain.PmisContractProductInfo;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
public interface PmisContractProductInfoService {

    Integer createPmisContractProductInfo(PmisContractProductInfo t);

    int modifyPmisContractProductInfo(PmisContractProductInfo t);

    int removePmisContractProductInfo(PmisContractProductInfo t);

    PmisContractProductInfo getPmisContractProductInfo(PmisContractProductInfo t);

    List<PmisContractProductInfo> getPmisContractProductInfoList(PmisContractProductInfo t);

    Integer getPmisContractProductInfoCount(PmisContractProductInfo t);

    List<PmisContractProductInfo> getPmisContractProductInfoPaginatedList(PmisContractProductInfo t);

    List<PmisContractProductInfo> getPmisContractProductInfoMkList(PmisContractProductInfo contractProductInfo);
}