package cn.com.winning.ssgj.dao;

import cn.com.winning.ssgj.domain.SysFloors;
import cn.com.winning.ssgj.dao.EntityDao;

import java.util.List;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-03-23 15:16:59
 */
public interface SysFloorsDao extends EntityDao<SysFloors> {

    List<SysFloors> selectSysFloorsPageListByFuzzy(SysFloors floors);

    int selectSysFloorsCountByFuzzy(SysFloors floors);

    int selectSysFloorsExistsFloorsName(SysFloors floors);
}
