package cn.com.winning.ssgj.dao.mybatis;

import cn.com.winning.ssgj.dao.EtTempQuestionInfoDao;
import cn.com.winning.ssgj.domain.EtTempQuestionInfo;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chenshijie
 * @title
 * @email chensj@winning.com.cm
 * @package cn.com.winning.ssgj.dao.mybatis
 * @date 2018-06-04 13:36
 */
@Service
public class EtTempQuestionInfoDaoSqlMapImpl extends EntityDaoSqlMapImpl<EtTempQuestionInfo> implements EtTempQuestionInfoDao {
    @Override
    public void updateEtTempQuestionInfoDictValue(EtTempQuestionInfo info) {
        super.getSqlSession().update("updateEtTempQuestionInfoDictValue",info);
    }

    @Override
    public void deleteEtTempQuestionInfoBySerialNo(EtTempQuestionInfo info) {
        super.getSqlSession().delete("deleteEtTempQuestionInfoBySerialNo",info);
    }
}
