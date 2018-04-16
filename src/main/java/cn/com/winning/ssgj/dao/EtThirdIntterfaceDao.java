package cn.com.winning.ssgj.dao;

import cn.com.winning.ssgj.domain.EtThirdIntterface;
import cn.com.winning.ssgj.dao.EntityDao;

import java.util.List;

/**
 * @author SSGJ
 * @date 2018-01-18 10:11:47
 */
public interface EtThirdIntterfaceDao extends EntityDao<EtThirdIntterface> {

    List<EtThirdIntterface> selectEtThirdIntterfaceMergePageList(EtThirdIntterface etThirdIntterface);

    Integer selectEtThirdIntterfaceMergeCount(EtThirdIntterface etThirdIntterface);

    List<EtThirdIntterface> selectEtThirdIntterfaceMergeList(EtThirdIntterface etThirdIntterface);

    List<EtThirdIntterface> selectPmisInterfaceList(EtThirdIntterface etThirdIntterface);



}
