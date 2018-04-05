package cn.com.winning.ssgj.service.impl;

import java.util.List;

import javax.annotation.Resource;


import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.EtSiteInstallDetailDao;
import cn.com.winning.ssgj.domain.EtSiteInstallDetail;
import cn.com.winning.ssgj.service.EtSiteInstallDetailService;

/**
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
@Service
public class EtSiteInstallDetailServiceImpl implements EtSiteInstallDetailService {

    @Resource
    private EtSiteInstallDetailDao etSiteInstallDetailDao;



    public Integer createEtSiteInstallDetail(EtSiteInstallDetail t) {
        return this.etSiteInstallDetailDao.insertEntity(t);
    }


    public EtSiteInstallDetail getEtSiteInstallDetail(EtSiteInstallDetail t) {
        return this.etSiteInstallDetailDao.selectEntity(t);
    }


    public Integer getEtSiteInstallDetailCount(EtSiteInstallDetail t) {
        return (Integer) this.etSiteInstallDetailDao.selectEntityCount(t);
    }


    public List<EtSiteInstallDetail> getEtSiteInstallDetailList(EtSiteInstallDetail t) {
        return this.etSiteInstallDetailDao.selectEntityList(t);
    }


    public int modifyEtSiteInstallDetail(EtSiteInstallDetail t) {
        return this.etSiteInstallDetailDao.updateEntity(t);
    }


    public int removeEtSiteInstallDetail(EtSiteInstallDetail t) {
        return this.etSiteInstallDetailDao.deleteEntity(t);
    }


    public List<EtSiteInstallDetail> getEtSiteInstallDetailPaginatedList(EtSiteInstallDetail t) {
        return this.etSiteInstallDetailDao.selectEntityPaginatedList(t);
    }

}
