package cn.com.winning.ssgj.dao.mybatis;

import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.SysFloorsDao;
import cn.com.winning.ssgj.domain.SysFloors;
import cn.com.winning.ssgj.dao.mybatis.EntityDaoSqlMapImpl;

import java.util.List;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-03-23 15:16:59
 */
@Service
public class SysFloorsDaoSqlMapImpl extends EntityDaoSqlMapImpl<SysFloors> implements SysFloorsDao {

    @Override
    public List<SysFloors> selectSysFloorsPageListByFuzzy(SysFloors floors) {
        return super.getSqlSession().selectList("selectSysFloorsPageListByFuzzy",floors);
    }

    @Override
    public int selectSysFloorsCountByFuzzy(SysFloors floors) {
        return super.getSqlSession().selectOne("selectSysFloorsCountByFuzzy",floors);
    }

    @Override
    public int selectSysFloorsExistsFloorsName(SysFloors floors) {
        return super.getSqlSession().selectOne("selectSysFloorsExistsFloorsName",floors);
    }
}
