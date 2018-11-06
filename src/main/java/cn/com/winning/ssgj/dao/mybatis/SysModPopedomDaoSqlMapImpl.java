package cn.com.winning.ssgj.dao.mybatis;

import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.SysModPopedomDao;
import cn.com.winning.ssgj.domain.SysModPopedom;
import cn.com.winning.ssgj.dao.mybatis.EntityDaoSqlMapImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:47
 */
@Service
public class SysModPopedomDaoSqlMapImpl extends EntityDaoSqlMapImpl<SysModPopedom> implements SysModPopedomDao {

    @Override
    public List<Long> selectModuleIdList(SysModPopedom modPopedom) {
        String statement = "selectModuleIdList";
        return super.getSqlSession().selectList(statement,modPopedom);
    }

    @Override
    public void deleteSysModPopedomForIds(Map<String, Object> param) {
        String statement = "deleteSysModPopedomForIds";
        super.getSqlSession().update(statement,param);
    }

    @Override
    public List<SysModPopedom> selectModulePopedomInfoList(SysModPopedom modPopedom) {
        return super.getSqlSession().selectList("selectModulePopedomInfoList",modPopedom);
    }

    @Override
    public List<SysModPopedom> selectSysModPopedomHasPopedomList(SysModPopedom modPopedom) {
        return super.getSqlSession().selectList("selectSysModPopedomHasPopedomList",modPopedom);
    }

    @Override
    public void updateSysModPopedomAllPopedomCode(SysModPopedom modPopedom) {
        super.getSqlSession().update("updateSysModPopedomAllPopedomCode",modPopedom);
    }

    @Override
    public Set<String> selectButtonFlagForPageByModUrlAndRoles(SysModPopedom modPopedom) {
        List<String> btnGlag = super.getSqlSession().selectList("selectButtonFlagForPageByModUrlAndRoles",modPopedom);
        Set<String> btnGlagSet = new HashSet<>();
        btnGlagSet.addAll(btnGlag);
        return btnGlagSet;
    }
}
