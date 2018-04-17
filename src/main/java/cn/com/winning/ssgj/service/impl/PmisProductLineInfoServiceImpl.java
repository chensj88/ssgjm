package cn.com.winning.ssgj.service.impl;

import java.util.List;

import javax.annotation.Resource;


import cn.com.winning.ssgj.domain.PmisContractProductInfo;
import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.PmisProductLineInfoDao;
import cn.com.winning.ssgj.domain.PmisProductLineInfo;
import cn.com.winning.ssgj.service.PmisProductLineInfoService;

/**
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
@Service
public class PmisProductLineInfoServiceImpl implements PmisProductLineInfoService {

    @Resource
    private PmisProductLineInfoDao pmisProductLineInfoDao;



    public Integer createPmisProductLineInfo(PmisProductLineInfo t) {
        return this.pmisProductLineInfoDao.insertEntity(t);
    }


    public PmisProductLineInfo getPmisProductLineInfo(PmisProductLineInfo t) {
        return this.pmisProductLineInfoDao.selectEntity(t);
    }


    public Integer getPmisProductLineInfoCount(PmisProductLineInfo t) {
        return (Integer) this.pmisProductLineInfoDao.selectEntityCount(t);
    }


    public List<PmisProductLineInfo> getPmisProductLineInfoList(PmisProductLineInfo t) {
        return this.pmisProductLineInfoDao.selectEntityList(t);
    }


    public int modifyPmisProductLineInfo(PmisProductLineInfo t) {
        return this.pmisProductLineInfoDao.updateEntity(t);
    }


    public int removePmisProductLineInfo(PmisProductLineInfo t) {
        return this.pmisProductLineInfoDao.deleteEntity(t);
    }


    public List<PmisProductLineInfo> getPmisProductLineInfoPaginatedList(PmisProductLineInfo t) {
        return this.pmisProductLineInfoDao.selectEntityPaginatedList(t);
    }

    @Override

    public Integer getPmisProductLineInfoCountByName(PmisProductLineInfo t) {
        return this.pmisProductLineInfoDao.selectPmisProductLineInfoByNameForCount(t);
    }

    @Override

    public List<PmisProductLineInfo> getPmisProductLineInfoPaginatedListByName(PmisProductLineInfo t) {
        return this.pmisProductLineInfoDao.selectPmisProductLineInfoByNameForList(t);
    }

    @Override
    public List<PmisProductLineInfo> selectPmisProductLineInfoByPmidAndType(PmisContractProductInfo pmisContractProductInfo) {
        return this.pmisProductLineInfoDao.selectPmisProductLineInfoByPmidAndType(pmisContractProductInfo);
    }

}
