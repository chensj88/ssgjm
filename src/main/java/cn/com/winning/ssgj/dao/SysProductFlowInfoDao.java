package cn.com.winning.ssgj.dao;

import cn.com.winning.ssgj.domain.SysProductFlowInfo;
import cn.com.winning.ssgj.dao.EntityDao;

import java.util.List;
import java.util.Map;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-02-08 15:30:03
 */
public interface SysProductFlowInfoDao extends EntityDao<SysProductFlowInfo> {
    /**
     * 查询待删除的数据
     * @param param
     * @return
     */
    List<SysProductFlowInfo> selectSysProductFlowInfoByPdIdAndFlowId(Map<String, Object> param);

    /**
     * 查询新增已存在的数据
     * @param param
     * @return
     */
    List<SysProductFlowInfo> selectProductFlowInfoForIds(Map<String, Object> param);

    /**
     * 移除不在使用的映射关系
     * @param param
     * @return
     */
    Integer removeSysProductFlowInfoMappingByIds(Map<String, Object> param);
}
