package cn.com.winning.ssgj.dao;

import cn.com.winning.ssgj.domain.EtDepartment;
import cn.com.winning.ssgj.domain.EtSiteInstallDetail;
import cn.com.winning.ssgj.dao.EntityDao;

import java.util.List;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:47
 */
public interface EtSiteInstallDetailDao extends EntityDao<EtSiteInstallDetail> {

    List<EtSiteInstallDetail> getSiteListByDeptId(EtDepartment etDepartment);

    void updateEtSiteInstallDetailSourceId(EtSiteInstallDetail t);
}
