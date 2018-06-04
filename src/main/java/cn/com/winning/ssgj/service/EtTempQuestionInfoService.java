package cn.com.winning.ssgj.service;

import java.util.List;  

import cn.com.winning.ssgj.domain.EtTempQuestionInfo;  


/**
* @author chensj
* @title ET_TEMP_QUESTION_INFO
* @email chensj@163.com
* @package cn.com.winning.ssgj.service
* @date 2018-47-04 13:47:01
*/
public interface EtTempQuestionInfoService {

    public int createEtTempQuestionInfo(EtTempQuestionInfo etTempQuestionInfo);

    public int modifyEtTempQuestionInfo(EtTempQuestionInfo etTempQuestionInfo);

    public int removeEtTempQuestionInfo(EtTempQuestionInfo etTempQuestionInfo);

    public EtTempQuestionInfo getEtTempQuestionInfo(EtTempQuestionInfo etTempQuestionInfo);

    public int getEtTempQuestionInfoCount(EtTempQuestionInfo etTempQuestionInfo);

    public List<EtTempQuestionInfo> getEtTempQuestionInfoList(EtTempQuestionInfo etTempQuestionInfo);

    public List<EtTempQuestionInfo> getEtTempQuestionInfoPageList(EtTempQuestionInfo etTempQuestionInfo);
}