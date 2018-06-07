package cn.com.winning.ssgj.dao.mybatis;

import cn.com.winning.ssgj.domain.EtDepartment;
import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.EtSiteInstallDetailDao;
import cn.com.winning.ssgj.domain.EtSiteInstallDetail;
import cn.com.winning.ssgj.dao.mybatis.EntityDaoSqlMapImpl;

import java.util.List;

/**
 * @author SSGJ
 * @date 2018-01-18 10:11:47
 */
@Service
public class EtSiteInstallDetailDaoSqlMapImpl extends EntityDaoSqlMapImpl<EtSiteInstallDetail> implements EtSiteInstallDetailDao {

    @Override
    public List<EtSiteInstallDetail> getSiteListByDeptId(EtDepartment etDepartment) {
        return this.getSqlSession().selectList("getSiteListByDeptId", etDepartment);
    }

    @Override
    public void updateEtSiteInstallDetailSourceId(EtSiteInstallDetail t) {
         this.getSqlSession().selectList("updateEtSiteInstallDetailSourceId",t);
    }
}
