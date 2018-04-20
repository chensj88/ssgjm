package cn.com.winning.ssgj.dao;

import cn.com.winning.ssgj.domain.BaseDomain;
import cn.com.winning.ssgj.domain.SysUserVideoAuthTemp;

import java.util.List;

/**
 * @author chenshijie
 * @title
 * @email chensj@winning.com.cm
 * @package cn.com.winning.ssgj.dao
 * @date 2018-04-20 23:03
 */
public interface SysUserVideoAuthTempDao extends EntityDao<SysUserVideoAuthTemp> {

    void deleteSysUserVideoAuthTempBySerialNo(SysUserVideoAuthTemp temp);

    public void batchUpdteMenuName(SysUserVideoAuthTemp auth);

    public void validateExistsInSySUserInfo(SysUserVideoAuthTemp auth);

    public void updateExistsUserId(SysUserVideoAuthTemp auth);

    List<SysUserVideoAuthTemp> selectSumUserVideoAuthList(SysUserVideoAuthTemp auth);
}
