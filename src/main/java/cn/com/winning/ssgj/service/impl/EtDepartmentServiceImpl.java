package cn.com.winning.ssgj.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.EtDepartmentDao;
import cn.com.winning.ssgj.domain.EtDepartment;
import cn.com.winning.ssgj.service.EtDepartmentService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-04-23 14:32:40
 */
@Service
public class EtDepartmentServiceImpl implements EtDepartmentService {

	@Resource
	private EtDepartmentDao etDepartmentDao;
	

	public Integer createEtDepartment(EtDepartment t) {
		return this.etDepartmentDao.insertEntity(t);
	}

	public EtDepartment getEtDepartment(EtDepartment t) {
		return this.etDepartmentDao.selectEntity(t);
	}

	public Integer getEtDepartmentCount(EtDepartment t) {
		return (Integer) this.etDepartmentDao.selectEntityCount(t);
	}

	public List<EtDepartment> getEtDepartmentList(EtDepartment t) {
		return this.etDepartmentDao.selectEntityList(t);
	}

	public int modifyEtDepartment(EtDepartment t) {
		return this.etDepartmentDao.updateEntity(t);
	}

	public int removeEtDepartment(EtDepartment t) {
		return this.etDepartmentDao.deleteEntity(t);
	}

	public List<EtDepartment> getEtDepartmentPaginatedList(EtDepartment t) {
		return this.etDepartmentDao.selectEntityPaginatedList(t);
	}

}
