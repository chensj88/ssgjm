package cn.com.winning.ssgj.service.impl;

import java.util.List;

import javax.annotation.Resource;


import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.EtDataCheckDao;
import cn.com.winning.ssgj.domain.EtDataCheck;
import cn.com.winning.ssgj.service.EtDataCheckService;

/**
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
@Service
public class EtDataCheckServiceImpl implements EtDataCheckService {

    @Resource
    private EtDataCheckDao etDataCheckDao;


    public Integer createEtDataCheck(EtDataCheck t) {
        return this.etDataCheckDao.insertEntity(t);
    }


    public EtDataCheck getEtDataCheck(EtDataCheck t) {
        return this.etDataCheckDao.selectEntity(t);
    }


    public Integer getEtDataCheckCount(EtDataCheck t) {
        return (Integer) this.etDataCheckDao.selectEntityCount(t);
    }


    public List<EtDataCheck> getEtDataCheckList(EtDataCheck t) {
        return this.etDataCheckDao.selectEntityList(t);
    }


    public int modifyEtDataCheck(EtDataCheck t) {
        return this.etDataCheckDao.updateEntity(t);
    }


    public int removeEtDataCheck(EtDataCheck t) {
        return this.etDataCheckDao.deleteEntity(t);
    }


    public List<EtDataCheck> getEtDataCheckPaginatedList(EtDataCheck t) {
        return this.etDataCheckDao.selectEntityPaginatedList(t);
    }

}
