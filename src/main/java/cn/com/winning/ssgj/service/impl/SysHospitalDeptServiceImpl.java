package cn.com.winning.ssgj.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.SysHospitalDeptDao;
import cn.com.winning.ssgj.domain.SysHospitalDept;
import cn.com.winning.ssgj.service.SysHospitalDeptService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-03-23 15:16:59
 */
@Service
public class SysHospitalDeptServiceImpl implements SysHospitalDeptService {

	@Resource
	private SysHospitalDeptDao sysHospitalDeptDao;
	

	public Integer createSysHospitalDept(SysHospitalDept t) {
		return this.sysHospitalDeptDao.insertEntity(t);
	}

	public SysHospitalDept getSysHospitalDept(SysHospitalDept t) {
		return this.sysHospitalDeptDao.selectEntity(t);
	}

	public Integer getSysHospitalDeptCount(SysHospitalDept t) {
		return (Integer) this.sysHospitalDeptDao.selectEntityCount(t);
	}

	public List<SysHospitalDept> getSysHospitalDeptList(SysHospitalDept t) {
		return this.sysHospitalDeptDao.selectEntityList(t);
	}

	public int modifySysHospitalDept(SysHospitalDept t) {
		return this.sysHospitalDeptDao.updateEntity(t);
	}

	public int removeSysHospitalDept(SysHospitalDept t) {
		return this.sysHospitalDeptDao.deleteEntity(t);
	}

	public List<SysHospitalDept> getSysHospitalDeptPaginatedList(SysHospitalDept t) {
		return this.sysHospitalDeptDao.selectEntityPaginatedList(t);
	}

}
