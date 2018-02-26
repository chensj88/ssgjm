package cn.com.winning.ssgj.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import cn.com.winning.ssgj.base.helper.SSGJHelper;
import cn.com.winning.ssgj.base.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.SysModPopedomDao;
import cn.com.winning.ssgj.domain.SysModPopedom;
import cn.com.winning.ssgj.service.SysModPopedomService;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
@Service
public class SysModPopedomServiceImpl implements SysModPopedomService {

    @Resource
    private SysModPopedomDao sysModPopedomDao;
    @Autowired
    private SSGJHelper ssgjHelper;


    public Integer createSysModPopedom(SysModPopedom t) {
        return this.sysModPopedomDao.insertEntity(t);
    }

    public SysModPopedom getSysModPopedom(SysModPopedom t) {
        return this.sysModPopedomDao.selectEntity(t);
    }

    public Integer getSysModPopedomCount(SysModPopedom t) {
        return (Integer) this.sysModPopedomDao.selectEntityCount(t);
    }

    public List<SysModPopedom> getSysModPopedomList(SysModPopedom t) {
        return this.sysModPopedomDao.selectEntityList(t);
    }

    public int modifySysModPopedom(SysModPopedom t) {
        return this.sysModPopedomDao.updateEntity(t);
    }

    public int removeSysModPopedom(SysModPopedom t) {
        return this.sysModPopedomDao.deleteEntity(t);
    }

    public List<SysModPopedom> getSysModPopedomPaginatedList(SysModPopedom t) {
        return this.sysModPopedomDao.selectEntityPaginatedList(t);
    }

    @Override
    public List<Long> getModuleIdList(SysModPopedom modPopedom) {
        return this.sysModPopedomDao.selectModuleIdList(modPopedom);
    }

    @Override
    public void createSysModPopedomForIdList(String idList) {
        Map<String,Object> param = new HashMap<String, Object>();
        param.put("ids", StringUtil.generateDeleteSqlString(idList,"ROLE_ID"));
        System.out.println(StringUtil.generateDeleteSqlString(idList,"ROLE_ID"));
        this.sysModPopedomDao.deleteSysModPopedomForIds(param);
        List<String>  addModPopedom = StringUtil.generateStringList(idList);
        for (String s : addModPopedom) {
           SysModPopedom modPopedom = new SysModPopedom();
           modPopedom.setId(ssgjHelper.createSysRoleModId());
           modPopedom.setRoleId(Long.valueOf(s.split(",")[0]));
           modPopedom.setModId(Long.valueOf(s.split(",")[1]));
           this.sysModPopedomDao.insertEntity(modPopedom);
        }

    }

}
