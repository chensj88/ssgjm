package cn.com.winning.ssgj.dao.mybatis;

import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.SysProductDataInfoDao;
import cn.com.winning.ssgj.domain.SysProductDataInfo;
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
public class SysProductDataInfoDaoSqlMapImpl extends EntityDaoSqlMapImpl<SysProductDataInfo> implements SysProductDataInfoDao {

    @Override
    public List<SysProductDataInfo> selectSysProductDataInfoByIds(Map<String,Object> param) {
        String statement = "selectSysProductDataInfoByIds";
        return super.getSqlSession().selectList(statement,param);
    }
}
