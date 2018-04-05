package cn.com.winning.ssgj.dao;

import cn.com.winning.ssgj.domain.SysParams;
import cn.com.winning.ssgj.dao.EntityDao;

import java.util.List;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:47
 */
public interface SysParamsDao extends EntityDao<SysParams> {

    List<SysParams> selectSysParamsPageListBySelectiveKey(SysParams params);

    int selectSysParamsPageCountBySelectiveKey(SysParams params);
}
