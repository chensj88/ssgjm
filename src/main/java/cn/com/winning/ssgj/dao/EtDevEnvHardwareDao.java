package cn.com.winning.ssgj.dao;

import cn.com.winning.ssgj.domain.EtDevEnvHardware;
import cn.com.winning.ssgj.dao.EntityDao;

import java.util.List;

/**
 * @author SSGJ
 * @date 2018-01-18 10:11:47
 */
public interface EtDevEnvHardwareDao extends EntityDao<EtDevEnvHardware> {


    List<EtDevEnvHardware> selectEtDevEnvHardwareMergeList(EtDevEnvHardware etDevEnvHardware);

    void insertEtDevEnvHardwareByList(List<EtDevEnvHardware> etDevEnvHardwares);

}
