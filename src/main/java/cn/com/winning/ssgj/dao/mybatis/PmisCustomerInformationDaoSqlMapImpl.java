package cn.com.winning.ssgj.dao.mybatis;

import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.PmisCustomerInformationDao;
import cn.com.winning.ssgj.domain.PmisCustomerInformation;
import cn.com.winning.ssgj.dao.mybatis.EntityDaoSqlMapImpl;

import java.util.List;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:47
 */
@Service
public class PmisCustomerInformationDaoSqlMapImpl extends EntityDaoSqlMapImpl<PmisCustomerInformation> implements PmisCustomerInformationDao {

    @Override
    public int selectPmisCustomerInformationCountFuzzy(PmisCustomerInformation c) {
        return super.getSqlSession().selectOne("selectPmisCustomerInformationCountFuzzy",c);
    }

    @Override
    public List<PmisCustomerInformation> selectPmisCustomerInformationPageListFuzzy(PmisCustomerInformation c) {
        return super.getSqlSession().selectList("selectPmisCustomerInformationPageListFuzzy",c);
    }

    @Override
    public List<PmisCustomerInformation> selectCustomerInfoListByProjectList(PmisCustomerInformation custinfo) {
        return super.getSqlSession().selectList("selectCustomerInfoListByProjectList",custinfo);
    }
}
