package cn.com.winning.ssgj.dao.mybatis;

import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.SysDataCheckScriptDao;
import cn.com.winning.ssgj.domain.SysDataCheckScript;
import cn.com.winning.ssgj.dao.mybatis.EntityDaoSqlMapImpl;

import java.util.List;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-03-09 16:50:10
 */
@Service
public class SysDataCheckScriptDaoSqlMapImpl extends EntityDaoSqlMapImpl<SysDataCheckScript> implements SysDataCheckScriptDao {

    @Override
    public int selectSysDataCheckScriptCountFuzzy(SysDataCheckScript script) {
        return super.getSqlSession().selectOne("selectSysDataCheckScriptCountFuzzy",script);
    }

    @Override
    public List<SysDataCheckScript> selectSysDataCheckScriptPageListFuzzy(SysDataCheckScript script) {
        return super.getSqlSession().selectList("selectSysDataCheckScriptPageListFuzzy",script);
    }

    @Override
    public int selectExistDataCheckScriptName(SysDataCheckScript script) {
        return super.getSqlSession().selectOne("selectExistDataCheckScriptName",script);
    }

    @Override
    public int existDataCheckScript(SysDataCheckScript script) {
        return super.getSqlSession().selectOne("existDataCheckScript",script);
    }
}
