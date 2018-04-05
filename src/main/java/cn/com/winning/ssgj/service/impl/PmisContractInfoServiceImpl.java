package cn.com.winning.ssgj.service.impl;

import java.util.List;

import javax.annotation.Resource;


import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.PmisContractInfoDao;
import cn.com.winning.ssgj.domain.PmisContractInfo;
import cn.com.winning.ssgj.service.PmisContractInfoService;

/**
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
@Service
public class PmisContractInfoServiceImpl implements PmisContractInfoService {

    @Resource
    private PmisContractInfoDao pmisContractInfoDao;



    public Integer createPmisContractInfo(PmisContractInfo t) {
        return this.pmisContractInfoDao.insertEntity(t);
    }


    public PmisContractInfo getPmisContractInfo(PmisContractInfo t) {
        return this.pmisContractInfoDao.selectEntity(t);
    }


    public Integer getPmisContractInfoCount(PmisContractInfo t) {
        return (Integer) this.pmisContractInfoDao.selectEntityCount(t);
    }


    public List<PmisContractInfo> getPmisContractInfoList(PmisContractInfo t) {
        return this.pmisContractInfoDao.selectEntityList(t);
    }


    public int modifyPmisContractInfo(PmisContractInfo t) {
        return this.pmisContractInfoDao.updateEntity(t);
    }


    public int removePmisContractInfo(PmisContractInfo t) {
        return this.pmisContractInfoDao.deleteEntity(t);
    }


    public List<PmisContractInfo> getPmisContractInfoPaginatedList(PmisContractInfo t) {
        return this.pmisContractInfoDao.selectEntityPaginatedList(t);
    }

}
