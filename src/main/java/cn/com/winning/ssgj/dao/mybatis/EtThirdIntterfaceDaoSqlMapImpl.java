package cn.com.winning.ssgj.dao.mybatis;

import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.EtThirdIntterfaceDao;
import cn.com.winning.ssgj.domain.EtThirdIntterface;
import cn.com.winning.ssgj.dao.mybatis.EntityDaoSqlMapImpl;

import java.util.List;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:47
 */
@Service
public class EtThirdIntterfaceDaoSqlMapImpl extends EntityDaoSqlMapImpl<EtThirdIntterface> implements EtThirdIntterfaceDao {

    @Override
    public List<EtThirdIntterface> selectEtThirdIntterfaceMergePageList(EtThirdIntterface etThirdIntterface) {
        String statement="selectEtThirdIntterfaceMergePageList";
        return this.getSqlSession().selectList(statement,etThirdIntterface);
    }

    @Override
    public Integer selectEtThirdIntterfaceMergeCount(EtThirdIntterface etThirdIntterface) {
        String statement="selectEtThirdIntterfaceMergeCount";
        return this.getSqlSession().selectOne(statement,etThirdIntterface);
    }

    @Override
    public List<EtThirdIntterface> selectEtThirdIntterfaceMergeList(EtThirdIntterface etThirdIntterface) {
        String statement="selectEtThirdIntterfaceMergeList";
        return this.getSqlSession().selectList(statement,etThirdIntterface);
    }
}
