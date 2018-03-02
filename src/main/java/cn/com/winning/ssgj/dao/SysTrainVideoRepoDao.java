package cn.com.winning.ssgj.dao;

import cn.com.winning.ssgj.domain.SysTrainVideoRepo;
import cn.com.winning.ssgj.dao.EntityDao;

import java.util.List;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-02-28 09:00:20
 */
public interface SysTrainVideoRepoDao extends EntityDao<SysTrainVideoRepo> {

    int selectSysTrainVideoRepoCountBySelective(SysTrainVideoRepo repo);

    List<SysTrainVideoRepo> selectSysTrainVideoRepoPageListBySelective(SysTrainVideoRepo repo);

    List<SysTrainVideoRepo> selectSysTrainVideoRepoTypeList(SysTrainVideoRepo t);

    List<SysTrainVideoRepo> selectSysTrainVideoWithRecoedList(SysTrainVideoRepo t);
}
