package cn.com.winning.ssgj.service;

import java.util.List;

import cn.com.winning.ssgj.domain.EtProcessManager;
import cn.com.winning.ssgj.domain.SysDataInfo;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
public interface EtProcessManagerService {

    Integer createEtProcessManager(EtProcessManager t);

    int modifyEtProcessManager(EtProcessManager t);

    int removeEtProcessManager(EtProcessManager t);

    EtProcessManager getEtProcessManager(EtProcessManager t);

    List<EtProcessManager> getEtProcessManagerList(EtProcessManager t);

    Integer getEtProcessManagerCount(EtProcessManager t);

    List<EtProcessManager> getEtProcessManagerPaginatedList(EtProcessManager t);

    void updateEtProcessManagerByPmId(EtProcessManager etProcessManager);

}