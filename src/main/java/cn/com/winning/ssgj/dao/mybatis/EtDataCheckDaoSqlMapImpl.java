package cn.com.winning.ssgj.dao.mybatis;

import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.EtDataCheckDao;
import cn.com.winning.ssgj.domain.EtDataCheck;
import cn.com.winning.ssgj.dao.mybatis.EntityDaoSqlMapImpl;

import java.util.List;

/**
 * @author SSGJ
 * @date 2018-01-18 10:11:47
 */
@Service
public class EtDataCheckDaoSqlMapImpl extends EntityDaoSqlMapImpl<EtDataCheck> implements EtDataCheckDao {
    public List<EtDataCheck> selectEtDataCheckListByPmIdAndDataType(EtDataCheck etDataCheck) {
        return this.getSqlSession().selectList("selectEtDataCheckListByPmIdAndDataType", etDataCheck);
    }
}
