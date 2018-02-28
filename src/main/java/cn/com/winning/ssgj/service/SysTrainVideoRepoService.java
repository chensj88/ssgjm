package cn.com.winning.ssgj.service;

import java.util.List;

import cn.com.winning.ssgj.domain.SysTrainVideoRepo;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-02-28 09:00:21
 */
public interface SysTrainVideoRepoService {

	Integer createSysTrainVideoRepo(SysTrainVideoRepo t);

	int modifySysTrainVideoRepo(SysTrainVideoRepo t);

	int removeSysTrainVideoRepo(SysTrainVideoRepo t);

	SysTrainVideoRepo getSysTrainVideoRepo(SysTrainVideoRepo t);

	List<SysTrainVideoRepo> getSysTrainVideoRepoList(SysTrainVideoRepo t);

	Integer getSysTrainVideoRepoCount(SysTrainVideoRepo t);

	List<SysTrainVideoRepo> getSysTrainVideoRepoPaginatedList(SysTrainVideoRepo t);

<<<<<<< HEAD
	Integer getSysTrainVideoRepoCountBySelective(SysTrainVideoRepo t);

	List<SysTrainVideoRepo> getSysTrainVideoRepoPageListBySelective(SysTrainVideoRepo t);

=======
    List<SysTrainVideoRepo> getSysTrainVideoRepoTypeList(SysTrainVideoRepo t);
>>>>>>> a600f38be7192b2483d1dff049557671a837a8b6
}