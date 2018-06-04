package cn.com.winning.ssgj.dao;

import java.util.List;
import org.springframework.dao.DataAccessException;
import cn.com.winning.ssgj.domain.EtTempQuestionInfo;  

/**
* @author chensj
* @title DAO接口
* @email chensj@163.com
* @package cn.com.winning.ssgj.dao
* @date 2018-35-04 13:35:18
*/
public interface EtTempQuestionInfoDao extends EntityDao<EtTempQuestionInfo> {
    public void  updateEtTempQuestionInfoDictValue(EtTempQuestionInfo info);

    public void  deleteEtTempQuestionInfoBySerialNo(EtTempQuestionInfo info);
}