package cn.com.winning.ssgj.service;

import java.util.List;

import cn.com.winning.ssgj.domain.EtSiteInstall;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
public interface EtSiteInstallService {

    Integer createEtSiteInstall(EtSiteInstall t);

    int modifyEtSiteInstall(EtSiteInstall t);

    int removeEtSiteInstall(EtSiteInstall t);

    EtSiteInstall getEtSiteInstall(EtSiteInstall t);

    List<EtSiteInstall> getEtSiteInstallList(EtSiteInstall t);

    Integer getEtSiteInstallCount(EtSiteInstall t);

    List<EtSiteInstall> getEtSiteInstallPaginatedList(EtSiteInstall t);

}