package cn.com.winning.ssgj.dao.mybatis;

import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.EtReportDao;
import cn.com.winning.ssgj.domain.EtReport;
import cn.com.winning.ssgj.dao.mybatis.EntityDaoSqlMapImpl;

import java.util.List;

/**
 * @author SSGJ
 * @date 2018-01-18 10:11:47
 */
@Service
public class EtReportDaoSqlMapImpl extends EntityDaoSqlMapImpl<EtReport> implements EtReportDao {

    @Override
    public List<EtReport> selectEtReportByProductInfo(EtReport etReport) {
        return this.getSqlSession().selectList("selectEtReportByProductInfo", etReport);
    }
}
