package cn.com.winning.ssgj.dao.mybatis;

import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.EtEasyDataCheckDetailDao;
import cn.com.winning.ssgj.domain.EtEasyDataCheckDetail;
import cn.com.winning.ssgj.dao.mybatis.EntityDaoSqlMapImpl;

<<<<<<< HEAD
=======
import java.util.List;

>>>>>>> a340590b36085a7325c63510bc48d0535149fc66
/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:47
 */
@Service
public class EtEasyDataCheckDetailDaoSqlMapImpl extends EntityDaoSqlMapImpl<EtEasyDataCheckDetail> implements EtEasyDataCheckDetailDao {

<<<<<<< HEAD
=======
    @Override
    public void insertEtEasyDataCheckDetailByList(List<EtEasyDataCheckDetail> etEasyDataCheckDetails) {
        String state="insertEtEasyDataCheckDetailByList";
        this.getSqlSession().insert(state,etEasyDataCheckDetails);
    }
>>>>>>> a340590b36085a7325c63510bc48d0535149fc66
}
