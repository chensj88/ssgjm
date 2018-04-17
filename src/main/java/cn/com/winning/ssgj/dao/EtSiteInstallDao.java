package cn.com.winning.ssgj.dao;

import cn.com.winning.ssgj.domain.EtSiteInstall;
import cn.com.winning.ssgj.dao.EntityDao;

import java.util.List;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:47
 */
public interface EtSiteInstallDao extends EntityDao<EtSiteInstall> {

    List<EtSiteInstall> selectEtSiteInstallNameList(EtSiteInstall t);

    List<EtSiteInstall> selectEtSiteInstallListWithSum(EtSiteInstall t);

    List<EtSiteInstall> selectEtSiteInstallListWithInfo(EtSiteInstall t);
}
