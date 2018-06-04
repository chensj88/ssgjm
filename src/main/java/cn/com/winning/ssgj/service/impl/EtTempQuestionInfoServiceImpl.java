package cn.com.winning.ssgj.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;  

import cn.com.winning.ssgj.domain.EtTempQuestionInfo;  

import cn.com.winning.ssgj.dao.EtTempQuestionInfoDao;  

import cn.com.winning.ssgj.service.EtTempQuestionInfoService;  


/**
* @author chensj
* @title ET_TEMP_QUESTION_INFO
* @email chensj@163.com
* @package cn.com.winning.ssgj.service.impl
* @date 2018-52-04 13:52:48
*/
@Service
public class EtTempQuestionInfoServiceImpl implements  EtTempQuestionInfoService {

    @Autowired
    private EtTempQuestionInfoDao etTempQuestionInfo;

    public int createEtTempQuestionInfo(EtTempQuestionInfo etTempQuestionInfo){
        return this.etTempQuestionInfo.insertEntity(etTempQuestionInfo);
    }

    public int modifyEtTempQuestionInfo(EtTempQuestionInfo etTempQuestionInfo){
        return this.etTempQuestionInfo.updateEntity(etTempQuestionInfo);
    }

    public int removeEtTempQuestionInfo(EtTempQuestionInfo etTempQuestionInfo){
        return this.etTempQuestionInfo.deleteEntity(etTempQuestionInfo);
    }

    public EtTempQuestionInfo getEtTempQuestionInfo(EtTempQuestionInfo etTempQuestionInfo){
        return this.etTempQuestionInfo.selectEntity(etTempQuestionInfo);
    }

    public int getEtTempQuestionInfoCount(EtTempQuestionInfo etTempQuestionInfo){
        return (Integer)this.etTempQuestionInfo.selectEntityCount(etTempQuestionInfo);
    }

    public List<EtTempQuestionInfo> getEtTempQuestionInfoList(EtTempQuestionInfo etTempQuestionInfo){
        return this.etTempQuestionInfo.selectEntityList(etTempQuestionInfo);
    }

    public List<EtTempQuestionInfo> getEtTempQuestionInfoPageList(EtTempQuestionInfo etTempQuestionInfo){
        return this.etTempQuestionInfo.selectEntityPaginatedList(etTempQuestionInfo);
    }
}