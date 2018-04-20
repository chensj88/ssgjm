package cn.com.winning.ssgj.dao.mybatis;

import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.SysUserVideoAuthDao;
import cn.com.winning.ssgj.domain.SysUserVideoAuth;
import cn.com.winning.ssgj.dao.mybatis.EntityDaoSqlMapImpl;

import java.util.List;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-04-20 15:01:08
 */
@Service
public class SysUserVideoAuthDaoSqlMapImpl extends EntityDaoSqlMapImpl<SysUserVideoAuth> implements SysUserVideoAuthDao {


    @Override
    public void deleteSysUserVideoAuthBySerialNo(SysUserVideoAuth auth) {
        super.getSqlSession().delete("deleteSysUserVideoAuthBySerialNo",auth);
    }

}
