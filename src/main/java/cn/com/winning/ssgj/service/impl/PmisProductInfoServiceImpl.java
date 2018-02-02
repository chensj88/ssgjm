package cn.com.winning.ssgj.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.PmisProductInfoDao;
import cn.com.winning.ssgj.domain.PmisProductInfo;
import cn.com.winning.ssgj.service.PmisProductInfoService;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
@Service
public class PmisProductInfoServiceImpl implements PmisProductInfoService {

    @Resource
    private PmisProductInfoDao pmisProductInfoDao;


    public Integer createPmisProductInfo(PmisProductInfo t) {
        return this.pmisProductInfoDao.insertEntity(t);
    }

    public PmisProductInfo getPmisProductInfo(PmisProductInfo t) {
        return this.pmisProductInfoDao.selectEntity(t);
    }

    public Integer getPmisProductInfoCount(PmisProductInfo t) {
        return (Integer) this.pmisProductInfoDao.selectEntityCount(t);
    }

    public List<PmisProductInfo> getPmisProductInfoList(PmisProductInfo t) {
        return this.pmisProductInfoDao.selectEntityList(t);
    }

    public int modifyPmisProductInfo(PmisProductInfo t) {
        return this.pmisProductInfoDao.updateEntity(t);
    }

    public int removePmisProductInfo(PmisProductInfo t) {
        return this.pmisProductInfoDao.deleteEntity(t);
    }

    public List<PmisProductInfo> getPmisProductInfoPaginatedList(PmisProductInfo t) {
        return this.pmisProductInfoDao.selectEntityPaginatedList(t);
    }

    @Override
    public Integer getPmisProductInfoCountByCodeAndName(PmisProductInfo t) {
        return this.pmisProductInfoDao.selectPmisProductInfoCountByCodeAndName(t);
    }

    @Override
    public List<PmisProductInfo> getPmisProductInfoPaginatedListByCodeAndName(PmisProductInfo t) {
        return this.pmisProductInfoDao.selectPmisProductInfoPaginatedListByCodeAndName(t);
    }

}
