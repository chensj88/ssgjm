package cn.com.winning.ssgj.dao;

import cn.com.winning.ssgj.domain.SysProductDataInfo;
import cn.com.winning.ssgj.domain.SysProductInterfaceInfo;
import cn.com.winning.ssgj.dao.EntityDao;

import java.util.List;
import java.util.Map;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-02-08 15:30:03
 */
public interface SysProductInterfaceInfoDao extends EntityDao<SysProductInterfaceInfo> {
    /**
     * 按照ID字符串查询数据
     * @param param
     * @return
     */
    List<SysProductInterfaceInfo> selectSysProductInterfaceInfoByIds(Map<String, Object> param);

    /**
     * 移除映射信息
     * @param param
     */
    int removeSysProductInterInfoByIds(Map<String, Object> param);

    /**
     * 使用拼接字符串查询
     * @param param
     * @return
     */
    List<SysProductInterfaceInfo> selectSysProductInterfaceInfoForIds(Map<String, Object> param);
}
