package cn.com.winning.ssgj.dao;

import cn.com.winning.ssgj.domain.SysProductPaperInfo;
import cn.com.winning.ssgj.dao.EntityDao;

import java.util.List;
import java.util.Map;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-02-08 15:30:03
 */
public interface SysProductPaperInfoDao extends EntityDao<SysProductPaperInfo> {

    List<SysProductPaperInfo> selectSysProductPaperInfoByIds(Map<String, Object> param);

    int removeSysProductPaperInfoMapping(Map<String, Object> param);

    List<SysProductPaperInfo> selectSysProductPaperInfoByIdMap(Map<String, Object> param);
}
