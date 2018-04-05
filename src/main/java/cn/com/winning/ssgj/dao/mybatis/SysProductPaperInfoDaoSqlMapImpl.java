package cn.com.winning.ssgj.dao.mybatis;

import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.SysProductPaperInfoDao;
import cn.com.winning.ssgj.domain.SysProductPaperInfo;
import cn.com.winning.ssgj.dao.mybatis.EntityDaoSqlMapImpl;

import java.util.List;
import java.util.Map;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-02-08 15:30:03
 */
@Service
public class SysProductPaperInfoDaoSqlMapImpl extends EntityDaoSqlMapImpl<SysProductPaperInfo> implements SysProductPaperInfoDao {

    @Override
    public List<SysProductPaperInfo> selectSysProductPaperInfoByIds(Map<String, Object> param) {
        return this.getSqlSession().selectList("selectSysProductPaperInfoByIds",param);
    }

    @Override
    public int removeSysProductPaperInfoMapping(Map<String, Object> param) {
        return this.getSqlSession().update("removeSysProductPaperInfoMapping",param);
    }

    @Override
    public List<SysProductPaperInfo> selectSysProductPaperInfoByIdMap(Map<String, Object> param) {
        return this.getSqlSession().selectList("selectSysProductPaperInfoByIdMap",param);
    }
}
