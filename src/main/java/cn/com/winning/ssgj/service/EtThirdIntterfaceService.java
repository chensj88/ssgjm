package cn.com.winning.ssgj.service;

import java.util.List;

import cn.com.winning.ssgj.domain.EtThirdIntterface;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
public interface EtThirdIntterfaceService {

    Integer createEtThirdIntterface(EtThirdIntterface t);

    int modifyEtThirdIntterface(EtThirdIntterface t);

    int removeEtThirdIntterface(EtThirdIntterface t);

    EtThirdIntterface getEtThirdIntterface(EtThirdIntterface t);

    List<EtThirdIntterface> getEtThirdIntterfaceList(EtThirdIntterface t);

    Integer getEtThirdIntterfaceCount(EtThirdIntterface t);

    List<EtThirdIntterface> getEtThirdIntterfacePaginatedList(EtThirdIntterface t);

}