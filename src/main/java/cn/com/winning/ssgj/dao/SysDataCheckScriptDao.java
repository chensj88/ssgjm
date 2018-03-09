package cn.com.winning.ssgj.dao;

import cn.com.winning.ssgj.domain.SysDataCheckScript;
import cn.com.winning.ssgj.dao.EntityDao;

import java.util.List;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-03-09 16:50:10
 */
public interface SysDataCheckScriptDao extends EntityDao<SysDataCheckScript> {

    public int selectSysDataCheckScriptCountFuzzy(SysDataCheckScript script);

    public List<SysDataCheckScript> selectSysDataCheckScriptPageListFuzzy(SysDataCheckScript script);
}
