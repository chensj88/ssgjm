package cn.com.winning.ssgj.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.SysFloorsDao;
import cn.com.winning.ssgj.domain.SysFloors;
import cn.com.winning.ssgj.service.SysFloorsService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-03-23 15:16:59
 */
@Service
public class SysFloorsServiceImpl implements SysFloorsService {

	@Resource
	private SysFloorsDao sysFloorsDao;
	

	public Integer createSysFloors(SysFloors t) {
		return this.sysFloorsDao.insertEntity(t);
	}

	public SysFloors getSysFloors(SysFloors t) {
		return this.sysFloorsDao.selectEntity(t);
	}

	public Integer getSysFloorsCount(SysFloors t) {
		return (Integer) this.sysFloorsDao.selectEntityCount(t);
	}

	public List<SysFloors> getSysFloorsList(SysFloors t) {
		return this.sysFloorsDao.selectEntityList(t);
	}

	public int modifySysFloors(SysFloors t) {
		return this.sysFloorsDao.updateEntity(t);
	}

	public int removeSysFloors(SysFloors t) {
		return this.sysFloorsDao.deleteEntity(t);
	}

	public List<SysFloors> getSysFloorsPaginatedList(SysFloors t) {
		return this.sysFloorsDao.selectEntityPaginatedList(t);
	}

}
