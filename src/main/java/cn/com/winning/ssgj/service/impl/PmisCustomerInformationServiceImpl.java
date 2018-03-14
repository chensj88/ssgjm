package cn.com.winning.ssgj.service.impl;

import java.util.List;

import javax.annotation.Resource;


import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.PmisCustomerInformationDao;
import cn.com.winning.ssgj.domain.PmisCustomerInformation;
import cn.com.winning.ssgj.service.PmisCustomerInformationService;

/**
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
@Service
public class PmisCustomerInformationServiceImpl implements PmisCustomerInformationService {

    @Resource
    private PmisCustomerInformationDao pmisCustomerInformationDao;



    public Integer createPmisCustomerInformation(PmisCustomerInformation t) {
        return this.pmisCustomerInformationDao.insertEntity(t);
    }


    public PmisCustomerInformation getPmisCustomerInformation(PmisCustomerInformation t) {
        return this.pmisCustomerInformationDao.selectEntity(t);
    }


    public Integer getPmisCustomerInformationCount(PmisCustomerInformation t) {
        return (Integer) this.pmisCustomerInformationDao.selectEntityCount(t);
    }


    public List<PmisCustomerInformation> getPmisCustomerInformationList(PmisCustomerInformation t) {
        return this.pmisCustomerInformationDao.selectEntityList(t);
    }


    public int modifyPmisCustomerInformation(PmisCustomerInformation t) {
        return this.pmisCustomerInformationDao.updateEntity(t);
    }


    public int removePmisCustomerInformation(PmisCustomerInformation t) {
        return this.pmisCustomerInformationDao.deleteEntity(t);
    }


    public List<PmisCustomerInformation> getPmisCustomerInformationPaginatedList(PmisCustomerInformation t) {
        return this.pmisCustomerInformationDao.selectEntityPaginatedList(t);
    }

    @Override

    public int getPmisCustomerInformationCountFuzzy(PmisCustomerInformation c) {
        return this.pmisCustomerInformationDao.selectPmisCustomerInformationCountFuzzy(c);
    }

    @Override

    public List<PmisCustomerInformation> getPmisCustomerInformationPageListFuzzy(PmisCustomerInformation c) {
        return this.pmisCustomerInformationDao.selectPmisCustomerInformationPageListFuzzy(c);
    }

}
