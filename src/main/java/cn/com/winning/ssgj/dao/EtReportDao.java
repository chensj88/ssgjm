package cn.com.winning.ssgj.dao;

import cn.com.winning.ssgj.domain.EtReport;
import cn.com.winning.ssgj.dao.EntityDao;

import java.util.List;

/**
 * @author SSGJ
 * @date 2018-01-18 10:11:47
 */
public interface EtReportDao extends EntityDao<EtReport> {
    List<EtReport> selectEtReportByProductInfo(EtReport etReport);
}
