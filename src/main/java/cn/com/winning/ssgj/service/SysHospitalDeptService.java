package cn.com.winning.ssgj.service;

import java.util.List;

import cn.com.winning.ssgj.domain.SysHospitalDept;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-03-23 15:16:59
 */
public interface SysHospitalDeptService {

	Integer createSysHospitalDept(SysHospitalDept t);

	int modifySysHospitalDept(SysHospitalDept t);

	int removeSysHospitalDept(SysHospitalDept t);

	SysHospitalDept getSysHospitalDept(SysHospitalDept t);

	List<SysHospitalDept> getSysHospitalDeptList(SysHospitalDept t);

	Integer getSysHospitalDeptCount(SysHospitalDept t);

	List<SysHospitalDept> getSysHospitalDeptPaginatedList(SysHospitalDept t);

}