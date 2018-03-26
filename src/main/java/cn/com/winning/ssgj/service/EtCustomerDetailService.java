package cn.com.winning.ssgj.service;

import java.util.List;

import cn.com.winning.ssgj.domain.EtCustomerDetail;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
public interface EtCustomerDetailService {

    Integer createEtCustomerDetail(EtCustomerDetail t);

    int modifyEtCustomerDetail(EtCustomerDetail t);

    int removeEtCustomerDetail(EtCustomerDetail t);

    EtCustomerDetail getEtCustomerDetail(EtCustomerDetail t);

    List<EtCustomerDetail> getEtCustomerDetailList(EtCustomerDetail t);

    Integer getEtCustomerDetailCount(EtCustomerDetail t);

    List<EtCustomerDetail> getEtCustomerDetailPaginatedList(EtCustomerDetail t);

    EtCustomerDetail getMergeEtCustomerDetail(EtCustomerDetail t);

}