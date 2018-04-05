package cn.com.winning.ssgj.service.impl;

import java.util.List;

import javax.annotation.Resource;


import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.EtInterfaceInfoDao;
import cn.com.winning.ssgj.domain.EtInterfaceInfo;
import cn.com.winning.ssgj.service.EtInterfaceInfoService;

/**
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
@Service
public class EtInterfaceInfoServiceImpl implements EtInterfaceInfoService {

    @Resource
    private EtInterfaceInfoDao etInterfaceInfoDao;



    public Integer createEtInterfaceInfo(EtInterfaceInfo t) {
        return this.etInterfaceInfoDao.insertEntity(t);
    }


    public EtInterfaceInfo getEtInterfaceInfo(EtInterfaceInfo t) {
        return this.etInterfaceInfoDao.selectEntity(t);
    }


    public Integer getEtInterfaceInfoCount(EtInterfaceInfo t) {
        return (Integer) this.etInterfaceInfoDao.selectEntityCount(t);
    }


    public List<EtInterfaceInfo> getEtInterfaceInfoList(EtInterfaceInfo t) {
        return this.etInterfaceInfoDao.selectEntityList(t);
    }


    public int modifyEtInterfaceInfo(EtInterfaceInfo t) {
        return this.etInterfaceInfoDao.updateEntity(t);
    }


    public int removeEtInterfaceInfo(EtInterfaceInfo t) {
        return this.etInterfaceInfoDao.deleteEntity(t);
    }


    public List<EtInterfaceInfo> getEtInterfaceInfoPaginatedList(EtInterfaceInfo t) {
        return this.etInterfaceInfoDao.selectEntityPaginatedList(t);
    }

    @Override
    public List<EtInterfaceInfo> selectEtInterfaceInfoMergePageList(EtInterfaceInfo etInterfaceInfo) {
        return this.etInterfaceInfoDao.selectEtInterfaceInfoMergePageList(etInterfaceInfo);
    }

    @Override
    public List<EtInterfaceInfo> selectEtInterfaceInfoMergeList(EtInterfaceInfo etInterfaceInfo) {
        return this.etInterfaceInfoDao.selectEtInterfaceInfoMergeList(etInterfaceInfo);
    }

    @Override
    public Integer selectEtInterfaceInfoMergeCount(EtInterfaceInfo etInterfaceInfo) {
        return this.etInterfaceInfoDao.selectEtInterfaceInfoMergeCount(etInterfaceInfo);
    }

}
