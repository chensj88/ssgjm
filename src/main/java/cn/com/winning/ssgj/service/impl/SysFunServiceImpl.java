package cn.com.winning.ssgj.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.SysFunDao;
import cn.com.winning.ssgj.domain.SysFun;
import cn.com.winning.ssgj.service.SysFunService;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
@Service
public class SysFunServiceImpl implements SysFunService {

    @Resource
    private SysFunDao sysFunDao;


    public Integer createSysFun(SysFun t) {
        return this.sysFunDao.insertEntity(t);
    }

    public SysFun getSysFun(SysFun t) {
        return this.sysFunDao.selectEntity(t);
    }

    public Integer getSysFunCount(SysFun t) {
        return (Integer) this.sysFunDao.selectEntityCount(t);
    }

    public List<SysFun> getSysFunList(SysFun t) {
        return this.sysFunDao.selectEntityList(t);
    }

    public int modifySysFun(SysFun t) {
        return this.sysFunDao.updateEntity(t);
    }

    public int removeSysFun(SysFun t) {
        return this.sysFunDao.deleteEntity(t);
    }

    public List<SysFun> getSysFunPaginatedList(SysFun t) {
        return this.sysFunDao.selectEntityPaginatedList(t);
    }

}
