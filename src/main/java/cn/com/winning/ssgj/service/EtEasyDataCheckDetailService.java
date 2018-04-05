package cn.com.winning.ssgj.service;

import java.util.List;

import cn.com.winning.ssgj.domain.EtEasyDataCheckDetail;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
public interface EtEasyDataCheckDetailService {

    Integer createEtEasyDataCheckDetail(EtEasyDataCheckDetail t);

    int modifyEtEasyDataCheckDetail(EtEasyDataCheckDetail t);

    int removeEtEasyDataCheckDetail(EtEasyDataCheckDetail t);

    EtEasyDataCheckDetail getEtEasyDataCheckDetail(EtEasyDataCheckDetail t);

    List<EtEasyDataCheckDetail> getEtEasyDataCheckDetailList(EtEasyDataCheckDetail t);

    Integer getEtEasyDataCheckDetailCount(EtEasyDataCheckDetail t);

    List<EtEasyDataCheckDetail> getEtEasyDataCheckDetailPaginatedList(EtEasyDataCheckDetail t);


    void insertEtEasyDataCheckDetailByList(List<EtEasyDataCheckDetail> etEasyDataCheckDetails);


}