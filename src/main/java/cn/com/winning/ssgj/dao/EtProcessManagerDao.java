package cn.com.winning.ssgj.dao;

import cn.com.winning.ssgj.domain.EtProcessManager;
import cn.com.winning.ssgj.dao.EntityDao;
import cn.com.winning.ssgj.domain.SysDataInfo;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:47
 */
public interface EtProcessManagerDao extends EntityDao<EtProcessManager> {
    void updateEtProcessManagerByPmId(EtProcessManager etProcessManager);

}
