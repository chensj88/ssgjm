package cn.com.winning.ssgj.service;

import java.util.List;

import cn.com.winning.ssgj.domain.SysFloors;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-03-23 15:16:59
 */
public interface SysFloorsService {

	Integer createSysFloors(SysFloors t);

	int modifySysFloors(SysFloors t);

	int removeSysFloors(SysFloors t);

	SysFloors getSysFloors(SysFloors t);

	List<SysFloors> getSysFloorsList(SysFloors t);

	Integer getSysFloorsCount(SysFloors t);

	List<SysFloors> getSysFloorsPaginatedList(SysFloors t);

}