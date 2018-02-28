package cn.com.winning.ssgj.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.SysTrainVideoRepoDao;
import cn.com.winning.ssgj.domain.SysTrainVideoRepo;
import cn.com.winning.ssgj.service.SysTrainVideoRepoService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-02-28 09:00:21
 */
@Service
public class SysTrainVideoRepoServiceImpl implements SysTrainVideoRepoService {

	@Resource
	private SysTrainVideoRepoDao sysTrainVideoRepoDao;
	

	public Integer createSysTrainVideoRepo(SysTrainVideoRepo t) {
		return this.sysTrainVideoRepoDao.insertEntity(t);
	}

	public SysTrainVideoRepo getSysTrainVideoRepo(SysTrainVideoRepo t) {
		return this.sysTrainVideoRepoDao.selectEntity(t);
	}

	public Integer getSysTrainVideoRepoCount(SysTrainVideoRepo t) {
		return (Integer) this.sysTrainVideoRepoDao.selectEntityCount(t);
	}

	public List<SysTrainVideoRepo> getSysTrainVideoRepoList(SysTrainVideoRepo t) {
		return this.sysTrainVideoRepoDao.selectEntityList(t);
	}

	public int modifySysTrainVideoRepo(SysTrainVideoRepo t) {
		return this.sysTrainVideoRepoDao.updateEntity(t);
	}

	public int removeSysTrainVideoRepo(SysTrainVideoRepo t) {
		return this.sysTrainVideoRepoDao.deleteEntity(t);
	}

	public List<SysTrainVideoRepo> getSysTrainVideoRepoPaginatedList(SysTrainVideoRepo t) {
		return this.sysTrainVideoRepoDao.selectEntityPaginatedList(t);
	}

	@Override
	public Integer getSysTrainVideoRepoCountBySelective(SysTrainVideoRepo t) {
		return this.sysTrainVideoRepoDao.selectSysTrainVideoRepoCountBySelective(t);
	}

	@Override
	public List<SysTrainVideoRepo> getSysTrainVideoRepoPageListBySelective(SysTrainVideoRepo t) {
		return this.sysTrainVideoRepoDao.selectSysTrainVideoRepoPageListBySelective(t);
	}

}
