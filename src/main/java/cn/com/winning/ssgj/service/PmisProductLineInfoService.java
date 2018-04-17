package cn.com.winning.ssgj.service;

import java.util.List;

import cn.com.winning.ssgj.domain.PmisContractProductInfo;
import cn.com.winning.ssgj.domain.PmisProductLineInfo;

/**
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
public interface PmisProductLineInfoService {

    Integer createPmisProductLineInfo(PmisProductLineInfo t);

    int modifyPmisProductLineInfo(PmisProductLineInfo t);

    int removePmisProductLineInfo(PmisProductLineInfo t);

    PmisProductLineInfo getPmisProductLineInfo(PmisProductLineInfo t);

    List<PmisProductLineInfo> getPmisProductLineInfoList(PmisProductLineInfo t);

    Integer getPmisProductLineInfoCount(PmisProductLineInfo t);

    List<PmisProductLineInfo> getPmisProductLineInfoPaginatedList(PmisProductLineInfo t);

    Integer getPmisProductLineInfoCountByName(PmisProductLineInfo t);

    List<PmisProductLineInfo> getPmisProductLineInfoPaginatedListByName(PmisProductLineInfo t);

    List<PmisProductLineInfo> selectPmisProductLineInfoByPmidAndType(PmisContractProductInfo pmisContractProductInfo);
}