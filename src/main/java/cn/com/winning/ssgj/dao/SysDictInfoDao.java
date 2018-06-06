package cn.com.winning.ssgj.dao;

import cn.com.winning.ssgj.domain.SysDictInfo;
import cn.com.winning.ssgj.dao.EntityDao;

import java.util.List;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:47
 */
public interface SysDictInfoDao extends EntityDao<SysDictInfo> {
    /**
     * 按照字典编码和字典值使用and进行模糊查询数据（分页）
     * @param dict
     * @return
     */
    public List<SysDictInfo> selectEntityListBySelectiveKeyForAnd(SysDictInfo dict);

    /**
     * 按照字典编码和字典值使用and进行模糊统计数据
     * @param dict
     * @return
     */
    public Integer selectEntityCountBySelectiveKeyForAnd(SysDictInfo dict);

    /**
     * 按照字典编码和字典值使用OR进行模糊查询数据（分页）
     * @param dict
     * @return
     */
    public List<SysDictInfo> selectEntityListBySelectiveKeyForOr(SysDictInfo dict);

    /**
     *  按照字典编码和字典值使用OR进行模糊统计数据
     * @param dict
     * @return
     */
    public Integer selectEntityCountBySelectiveKeyForOr(SysDictInfo dict);

    List<SysDictInfo> selectSysDictInfoListByType(SysDictInfo dict);

    List<SysDictInfo> selectSysDictInfoListByValue(SysDictInfo dict);
}
