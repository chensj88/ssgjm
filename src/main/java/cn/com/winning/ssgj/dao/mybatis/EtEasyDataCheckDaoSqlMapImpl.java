package cn.com.winning.ssgj.dao.mybatis;

import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.EtEasyDataCheckDao;
import cn.com.winning.ssgj.domain.EtEasyDataCheck;
import cn.com.winning.ssgj.dao.mybatis.EntityDaoSqlMapImpl;

import java.util.List;

/**
 * @author SSGJ
 * @date 2018-01-18 10:11:47
 */
@Service
public class EtEasyDataCheckDaoSqlMapImpl extends EntityDaoSqlMapImpl<EtEasyDataCheck> implements EtEasyDataCheckDao {

    @Override
    public List<EtEasyDataCheck> selectEtEasyDataCheckListByPmIdAndDataType(EtEasyDataCheck etEasyDataCheck) {
        return this.getSqlSession().selectList("selectEtEasyDataCheckListByPmIdAndDataType", etEasyDataCheck);
    }

    @Override
    public List<EtEasyDataCheck> getInitEtEasyDataCheck(EtEasyDataCheck etEasyDataCheck) {
        return this.getSqlSession().selectList("getInitEtEasyDataCheck", etEasyDataCheck);
    }
}
