package cn.com.winning.ssgj.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.EtUserHospitalLogDao;
import cn.com.winning.ssgj.domain.EtUserHospitalLog;
import cn.com.winning.ssgj.service.EtUserHospitalLogService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-06-22 16:58:18
 */
@Service
public class EtUserHospitalLogServiceImpl implements EtUserHospitalLogService {

	@Resource
	private EtUserHospitalLogDao etUserHospitalLogDao;
	

	public Integer createEtUserHospitalLog(EtUserHospitalLog t) {
		return this.etUserHospitalLogDao.insertEntity(t);
	}

	public EtUserHospitalLog getEtUserHospitalLog(EtUserHospitalLog t) {
		return this.etUserHospitalLogDao.selectEntity(t);
	}

	public Integer getEtUserHospitalLogCount(EtUserHospitalLog t) {
		return (Integer) this.etUserHospitalLogDao.selectEntityCount(t);
	}

	public List<EtUserHospitalLog> getEtUserHospitalLogList(EtUserHospitalLog t) {
		return this.etUserHospitalLogDao.selectEntityList(t);
	}

	public int modifyEtUserHospitalLog(EtUserHospitalLog t) {
		return this.etUserHospitalLogDao.updateEntity(t);
	}

	public int removeEtUserHospitalLog(EtUserHospitalLog t) {
		return this.etUserHospitalLogDao.deleteEntity(t);
	}

	public List<EtUserHospitalLog> getEtUserHospitalLogPaginatedList(EtUserHospitalLog t) {
		return this.etUserHospitalLogDao.selectEntityPaginatedList(t);
	}

}
