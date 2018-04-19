package cn.com.winning.ssgj.service;

import java.util.List;

import cn.com.winning.ssgj.domain.EtReport;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
public interface EtReportService {

    Integer createEtReport(EtReport t);

    int modifyEtReport(EtReport t);

    int removeEtReport(EtReport t);

    EtReport getEtReport(EtReport t);

    List<EtReport> getEtReportList(EtReport t);

    Integer getEtReportCount(EtReport t);

    List<EtReport> getEtReportPaginatedList(EtReport t);

    List<EtReport> selectEtReportByProductInfo(EtReport etReport);

}