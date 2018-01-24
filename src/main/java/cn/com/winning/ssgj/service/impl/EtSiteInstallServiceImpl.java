package cn.com.winning.ssgj.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.EtSiteInstallDao;
import cn.com.winning.ssgj.domain.EtSiteInstall;
import cn.com.winning.ssgj.service.EtSiteInstallService;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
@Service
public class EtSiteInstallServiceImpl implements EtSiteInstallService {

    @Resource
    private EtSiteInstallDao etSiteInstallDao;


    public Integer createEtSiteInstall(EtSiteInstall t) {
        return this.etSiteInstallDao.insertEntity(t);
    }

    public EtSiteInstall getEtSiteInstall(EtSiteInstall t) {
        return this.etSiteInstallDao.selectEntity(t);
    }

    public Integer getEtSiteInstallCount(EtSiteInstall t) {
        return (Integer) this.etSiteInstallDao.selectEntityCount(t);
    }

    public List<EtSiteInstall> getEtSiteInstallList(EtSiteInstall t) {
        return this.etSiteInstallDao.selectEntityList(t);
    }

    public int modifyEtSiteInstall(EtSiteInstall t) {
        return this.etSiteInstallDao.updateEntity(t);
    }

    public int removeEtSiteInstall(EtSiteInstall t) {
        return this.etSiteInstallDao.deleteEntity(t);
    }

    public List<EtSiteInstall> getEtSiteInstallPaginatedList(EtSiteInstall t) {
        return this.etSiteInstallDao.selectEntityPaginatedList(t);
    }

}
