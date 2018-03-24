package cn.com.winning.ssgj.dao.mybatis;

import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.EtCustomerDetailDao;
import cn.com.winning.ssgj.domain.EtCustomerDetail;
import cn.com.winning.ssgj.dao.mybatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-03-24 14:03:02
 */
@Service
public class EtCustomerDetailDaoSqlMapImpl extends EntityDaoSqlMapImpl<EtCustomerDetail> implements EtCustomerDetailDao {

    @Override
    public EtCustomerDetail selectMergeEtCustomerDetail(EtCustomerDetail t) {
        return super.getSqlSession().selectOne("selectMergeEtCustomerDetail",t);
    }
}
