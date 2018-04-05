package cn.com.winning.ssgj.dao;

import cn.com.winning.ssgj.domain.PmisCustomerInformation;
import cn.com.winning.ssgj.dao.EntityDao;

import java.util.List;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:47
 */
public interface PmisCustomerInformationDao extends EntityDao<PmisCustomerInformation> {

    public int selectPmisCustomerInformationCountFuzzy(PmisCustomerInformation c);
    public List<PmisCustomerInformation> selectPmisCustomerInformationPageListFuzzy(PmisCustomerInformation c);

    List<PmisCustomerInformation> selectCustomerInfoListByProjectList(PmisCustomerInformation custinfo);
}
