package cn.com.winning.ssgj.service.impl;

import java.util.List;

import javax.annotation.Resource;


import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.EtTrainVideoListDao;
import cn.com.winning.ssgj.domain.EtTrainVideoList;
import cn.com.winning.ssgj.service.EtTrainVideoListService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-02-27 16:02:55
 */
@Service
public class EtTrainVideoListServiceImpl implements EtTrainVideoListService {

    @Resource
    private EtTrainVideoListDao etTrainVideoListDao;



    public Integer createEtTrainVideoList(EtTrainVideoList t) {
        return this.etTrainVideoListDao.insertEntity(t);
    }


    public EtTrainVideoList getEtTrainVideoList(EtTrainVideoList t) {
        return this.etTrainVideoListDao.selectEntity(t);
    }


    public Integer getEtTrainVideoListCount(EtTrainVideoList t) {
        return (Integer) this.etTrainVideoListDao.selectEntityCount(t);
    }


    public List<EtTrainVideoList> getEtTrainVideoListList(EtTrainVideoList t) {
        return this.etTrainVideoListDao.selectEntityList(t);
    }



    public int modifyEtTrainVideoList(EtTrainVideoList t) {
        return this.etTrainVideoListDao.updateEntity(t);
    }



    public int removeEtTrainVideoList(EtTrainVideoList t) {
        return this.etTrainVideoListDao.deleteEntity(t);
    }



    public List<EtTrainVideoList> getEtTrainVideoListPaginatedList(EtTrainVideoList t) {
        return this.etTrainVideoListDao.selectEntityPaginatedList(t);
    }

}