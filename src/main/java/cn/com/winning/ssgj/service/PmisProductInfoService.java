package cn.com.winning.ssgj.service;

import java.util.List;

import cn.com.winning.ssgj.domain.PmisProductInfo;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
public interface PmisProductInfoService {

    Integer createPmisProductInfo(PmisProductInfo t);

    int modifyPmisProductInfo(PmisProductInfo t);

    int removePmisProductInfo(PmisProductInfo t);

    PmisProductInfo getPmisProductInfo(PmisProductInfo t);

    List<PmisProductInfo> getPmisProductInfoList(PmisProductInfo t);

    Integer getPmisProductInfoCount(PmisProductInfo t);

    List<PmisProductInfo> getPmisProductInfoPaginatedList(PmisProductInfo t);

}