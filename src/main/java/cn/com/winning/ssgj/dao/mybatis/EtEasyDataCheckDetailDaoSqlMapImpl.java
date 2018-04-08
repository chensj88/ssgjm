package cn.com.winning.ssgj.dao.mybatis;

import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.EtEasyDataCheckDetailDao;
import cn.com.winning.ssgj.domain.EtEasyDataCheckDetail;

import java.util.List;


/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:47
 */
@Service
public class EtEasyDataCheckDetailDaoSqlMapImpl extends EntityDaoSqlMapImpl<EtEasyDataCheckDetail> implements EtEasyDataCheckDetailDao {

    @Override
    public void insertEtEasyDataCheckDetailByList(List<EtEasyDataCheckDetail> etEasyDataCheckDetails) {
        String state="insertEtEasyDataCheckDetailByList";
        this.getSqlSession().insert(state,etEasyDataCheckDetails);
    }

}
