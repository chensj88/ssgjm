package cn.com.winning.ssgj.service.impl;

import java.util.List;

import javax.annotation.Resource;


import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.EtEasyDataCheckDao;
import cn.com.winning.ssgj.domain.EtEasyDataCheck;
import cn.com.winning.ssgj.service.EtEasyDataCheckService;

/**
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
@Service
public class EtEasyDataCheckServiceImpl implements EtEasyDataCheckService {

    @Resource
    private EtEasyDataCheckDao etEasyDataCheckDao;


    public Integer createEtEasyDataCheck(EtEasyDataCheck t) {
        return this.etEasyDataCheckDao.insertEntity(t);
    }


    public EtEasyDataCheck getEtEasyDataCheck(EtEasyDataCheck t) {
        return this.etEasyDataCheckDao.selectEntity(t);
    }


    public Integer getEtEasyDataCheckCount(EtEasyDataCheck t) {
        return (Integer) this.etEasyDataCheckDao.selectEntityCount(t);
    }


    public List<EtEasyDataCheck> getEtEasyDataCheckList(EtEasyDataCheck t) {
        return this.etEasyDataCheckDao.selectEntityList(t);
    }


    public int modifyEtEasyDataCheck(EtEasyDataCheck t) {
        return this.etEasyDataCheckDao.updateEntity(t);
    }


    public int removeEtEasyDataCheck(EtEasyDataCheck t) {
        return this.etEasyDataCheckDao.deleteEntity(t);
    }


    public List<EtEasyDataCheck> getEtEasyDataCheckPaginatedList(EtEasyDataCheck t) {
        return this.etEasyDataCheckDao.selectEntityPaginatedList(t);
    }

    @Override
    public List<EtEasyDataCheck> selectEtEasyDataCheckListByPmIdAndDataType(EtEasyDataCheck etEasyDataCheck) {
        return this.etEasyDataCheckDao.selectEtEasyDataCheckListByPmIdAndDataType(etEasyDataCheck);
    }

    @Override
    public List<EtEasyDataCheck> getInitEtEasyDataCheck(EtEasyDataCheck etEasyDataCheck) {
        return this.etEasyDataCheckDao.getInitEtEasyDataCheck(etEasyDataCheck);
    }

}
