package cn.com.winning.ssgj.dao.mybatis;

import cn.com.winning.ssgj.dao.SysUserVideoAuthTempDao;
import cn.com.winning.ssgj.domain.SysUserVideoAuthTemp;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chenshijie
 * @title
 * @email chensj@winning.com.cm
 * @package cn.com.winning.ssgj.dao.mybatis
 * @date 2018-04-20 23:05
 */
@Service
public class SysUserVideoAuthTempDaoSqlMapImpl extends EntityDaoSqlMapImpl<SysUserVideoAuthTemp> implements SysUserVideoAuthTempDao {
    @Override
    public void deleteSysUserVideoAuthTempBySerialNo(SysUserVideoAuthTemp temp) {
        super.getSqlSession().delete("deleteSysUserVideoAuthTempBySerialNo",temp);
    }

    @Override
    public void batchUpdteMenuName(SysUserVideoAuthTemp auth) {
        super.getSqlSession().update("batchUpdteMenuName",auth);
    }

    @Override
    public void validateExistsInSySUserInfo(SysUserVideoAuthTemp auth) {
        super.getSqlSession().update("validateExistsInSySUserInfo",auth);
    }

    @Override
    public void updateExistsUserId(SysUserVideoAuthTemp auth) {
        super.getSqlSession().update("updateExistsUserId",auth);
    }

    @Override
    public List<SysUserVideoAuthTemp> selectSumUserVideoAuthList(SysUserVideoAuthTemp auth) {
        return super.getSqlSession().selectList("selectSumUserVideoAuthList",auth);
    }
}
