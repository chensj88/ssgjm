package cn.com.winning.ssgj.dao;

import cn.com.winning.ssgj.domain.SysProductShInfo;
import cn.com.winning.ssgj.dao.EntityDao;

import java.util.List;
import java.util.Map;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-02-08 15:42:37
 */
public interface SysProductShInfoDao extends EntityDao<SysProductShInfo> {

    List<SysProductShInfo> selectSysProductShInfoByIds(Map<String, Object> param);

    int removeSysProductShInfoMapping(Map<String, Object> param);

    List<SysProductShInfo> selectSysProductSHInfoByIdMap(Map<String, Object> param);
}
