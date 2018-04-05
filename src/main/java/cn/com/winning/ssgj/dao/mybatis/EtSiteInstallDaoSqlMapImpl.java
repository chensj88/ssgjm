package cn.com.winning.ssgj.dao.mybatis;

import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.EtSiteInstallDao;
import cn.com.winning.ssgj.domain.EtSiteInstall;
import cn.com.winning.ssgj.dao.mybatis.EntityDaoSqlMapImpl;

import java.util.List;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:47
 */
@Service
public class EtSiteInstallDaoSqlMapImpl extends EntityDaoSqlMapImpl<EtSiteInstall> implements EtSiteInstallDao {

    @Override
    public List<EtSiteInstall> selectEtSiteInstallNameList(EtSiteInstall t) {
        return this.getSqlSession().selectList("selectEtSiteInstallNameList",t);
    }
}
