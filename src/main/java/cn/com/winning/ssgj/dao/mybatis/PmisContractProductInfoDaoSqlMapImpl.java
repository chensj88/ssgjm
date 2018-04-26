package cn.com.winning.ssgj.dao.mybatis;

import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.PmisContractProductInfoDao;
import cn.com.winning.ssgj.domain.PmisContractProductInfo;
import cn.com.winning.ssgj.dao.mybatis.EntityDaoSqlMapImpl;

import java.util.List;
import java.util.Map;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:47
 */
@Service
public class PmisContractProductInfoDaoSqlMapImpl extends EntityDaoSqlMapImpl<PmisContractProductInfo> implements PmisContractProductInfoDao {

    @Override
    public List<PmisContractProductInfo> selectPmisContractProductInfoMkList(PmisContractProductInfo t) {
        return this.getSqlSession().selectList("selectPmisContractProductInfoMkList",t);
    }

    @Override
    public List<Long> selectProcuctIdListByPmIdAndHtcplb( Map<String,Object> param) {
        return this.getSqlSession().selectList("selectProcuctIdListByPmIdAndHtcplb",param);
    }
}
