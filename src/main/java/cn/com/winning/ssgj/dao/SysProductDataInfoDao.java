package cn.com.winning.ssgj.dao;

import cn.com.winning.ssgj.domain.SysProductDataInfo;

import java.util.List;
import java.util.Map;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:47
 */
public interface SysProductDataInfoDao extends EntityDao<SysProductDataInfo> {

    List<SysProductDataInfo> selectSysProductDataInfoByIds(Map<String,Object> param);

    Integer removeSysProductDataInfoByIds(Map<String,Object> param);

    /**
     * 按照ID查询，不考虑失效时间
     * @param param
     * @return
     */
    List<SysProductDataInfo> selectSysProductDataInfoForIds(Map<String,Object> param);
}
