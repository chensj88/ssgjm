package cn.com.winning.ssgj.dao;

import cn.com.winning.ssgj.domain.SysUserInfo;
import cn.com.winning.ssgj.dao.EntityDao;

import java.util.List;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:47
 */
public interface SysUserInfoDao extends EntityDao<SysUserInfo> {

   public  Integer selectSysUserInfoQueryCount(SysUserInfo t);

   public List<SysUserInfo> selectSysUserInfoQueryPaginatedList(SysUserInfo t);
}
