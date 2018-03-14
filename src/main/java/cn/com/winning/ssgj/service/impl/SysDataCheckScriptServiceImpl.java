package cn.com.winning.ssgj.service.impl;

import java.util.List;

import javax.annotation.Resource;


import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.SysDataCheckScriptDao;
import cn.com.winning.ssgj.domain.SysDataCheckScript;
import cn.com.winning.ssgj.service.SysDataCheckScriptService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-03-09 16:50:10
 */
@Service
public class SysDataCheckScriptServiceImpl implements SysDataCheckScriptService {

    @Resource
    private SysDataCheckScriptDao sysDataCheckScriptDao;



    public Integer createSysDataCheckScript(SysDataCheckScript t) {
        return this.sysDataCheckScriptDao.insertEntity(t);
    }


    public SysDataCheckScript getSysDataCheckScript(SysDataCheckScript t) {
        return this.sysDataCheckScriptDao.selectEntity(t);
    }


    public Integer getSysDataCheckScriptCount(SysDataCheckScript t) {
        return (Integer) this.sysDataCheckScriptDao.selectEntityCount(t);
    }


    public List<SysDataCheckScript> getSysDataCheckScriptList(SysDataCheckScript t) {
        return this.sysDataCheckScriptDao.selectEntityList(t);
    }


    public int modifySysDataCheckScript(SysDataCheckScript t) {
        return this.sysDataCheckScriptDao.updateEntity(t);
    }


    public int removeSysDataCheckScript(SysDataCheckScript t) {
        return this.sysDataCheckScriptDao.deleteEntity(t);
    }


    public List<SysDataCheckScript> getSysDataCheckScriptPaginatedList(SysDataCheckScript t) {
        return this.sysDataCheckScriptDao.selectEntityPaginatedList(t);
    }

    @Override

    public List<SysDataCheckScript> getSysDataCheckScriptPageListFuzzy(SysDataCheckScript script) {
        return this.sysDataCheckScriptDao.selectSysDataCheckScriptPageListFuzzy(script);
    }

    @Override

    public int getSysDataCheckScriptCountFuzzy(SysDataCheckScript script) {
        return this.sysDataCheckScriptDao.selectSysDataCheckScriptCountFuzzy(script);
    }

    @Override

    public boolean existDataCheckScriptName(SysDataCheckScript script) {
        int count = this.sysDataCheckScriptDao.selectExistDataCheckScriptName(script);
        if (count > 0) {
            return false;
        }
        return true;
    }

}
