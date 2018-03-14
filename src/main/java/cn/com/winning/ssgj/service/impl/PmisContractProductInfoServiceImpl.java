package cn.com.winning.ssgj.service.impl;

import java.util.List;

import javax.annotation.Resource;


import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.PmisContractProductInfoDao;
import cn.com.winning.ssgj.domain.PmisContractProductInfo;
import cn.com.winning.ssgj.service.PmisContractProductInfoService;

/**
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
@Service
public class PmisContractProductInfoServiceImpl implements PmisContractProductInfoService {

    @Resource
    private PmisContractProductInfoDao pmisContractProductInfoDao;



    public Integer createPmisContractProductInfo(PmisContractProductInfo t) {
        return this.pmisContractProductInfoDao.insertEntity(t);
    }


    public PmisContractProductInfo getPmisContractProductInfo(PmisContractProductInfo t) {
        return this.pmisContractProductInfoDao.selectEntity(t);
    }


    public Integer getPmisContractProductInfoCount(PmisContractProductInfo t) {
        return (Integer) this.pmisContractProductInfoDao.selectEntityCount(t);
    }


    public List<PmisContractProductInfo> getPmisContractProductInfoList(PmisContractProductInfo t) {
        return this.pmisContractProductInfoDao.selectEntityList(t);
    }


    public int modifyPmisContractProductInfo(PmisContractProductInfo t) {
        return this.pmisContractProductInfoDao.updateEntity(t);
    }


    public int removePmisContractProductInfo(PmisContractProductInfo t) {
        return this.pmisContractProductInfoDao.deleteEntity(t);
    }


    public List<PmisContractProductInfo> getPmisContractProductInfoPaginatedList(PmisContractProductInfo t) {
        return this.pmisContractProductInfoDao.selectEntityPaginatedList(t);
    }

}
