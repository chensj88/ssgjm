package cn.com.winning.ssgj.service.impl;

import java.util.List;

import javax.annotation.Resource;


import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.EtReportDao;
import cn.com.winning.ssgj.domain.EtReport;
import cn.com.winning.ssgj.service.EtReportService;

/**
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
@Service
public class EtReportServiceImpl implements EtReportService {

    @Resource
    private EtReportDao etReportDao;



    public Integer createEtReport(EtReport t) {
        return this.etReportDao.insertEntity(t);
    }


    public EtReport getEtReport(EtReport t) {
        return this.etReportDao.selectEntity(t);
    }


    public Integer getEtReportCount(EtReport t) {
        return (Integer) this.etReportDao.selectEntityCount(t);
    }


    public List<EtReport> getEtReportList(EtReport t) {
        return this.etReportDao.selectEntityList(t);
    }


    public int modifyEtReport(EtReport t) {
        return this.etReportDao.updateEntity(t);
    }


    public int removeEtReport(EtReport t) {
        return this.etReportDao.deleteEntity(t);
    }


    public List<EtReport> getEtReportPaginatedList(EtReport t) {
        return this.etReportDao.selectEntityPaginatedList(t);
    }

    @Override
    public List<EtReport> selectEtReportByProductInfo(EtReport etReport) {
        return this.etReportDao.selectEtReportByProductInfo(etReport);
    }

}
