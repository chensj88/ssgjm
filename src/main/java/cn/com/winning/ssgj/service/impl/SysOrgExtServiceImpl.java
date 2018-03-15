package cn.com.winning.ssgj.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.SysOrgExtDao;
import cn.com.winning.ssgj.domain.SysOrgExt;
import cn.com.winning.ssgj.service.SysOrgExtService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-03-15 10:21:04
 */
@Service
public class SysOrgExtServiceImpl implements SysOrgExtService {

	@Resource
	private SysOrgExtDao sysOrgExtDao;
	

	public Integer createSysOrgExt(SysOrgExt t) {
		return this.sysOrgExtDao.insertEntity(t);
	}

	public SysOrgExt getSysOrgExt(SysOrgExt t) {
		return this.sysOrgExtDao.selectEntity(t);
	}

	public Integer getSysOrgExtCount(SysOrgExt t) {
		return (Integer) this.sysOrgExtDao.selectEntityCount(t);
	}

	public List<SysOrgExt> getSysOrgExtList(SysOrgExt t) {
		return this.sysOrgExtDao.selectEntityList(t);
	}

	public int modifySysOrgExt(SysOrgExt t) {
		return this.sysOrgExtDao.updateEntity(t);
	}

	public int removeSysOrgExt(SysOrgExt t) {
		return this.sysOrgExtDao.deleteEntity(t);
	}

	public List<SysOrgExt> getSysOrgExtPaginatedList(SysOrgExt t) {
		return this.sysOrgExtDao.selectEntityPaginatedList(t);
	}

	@Override
	public void callProcedure() {
		this.sysOrgExtDao.callOrgExtInfoProcedure();
	}

}
