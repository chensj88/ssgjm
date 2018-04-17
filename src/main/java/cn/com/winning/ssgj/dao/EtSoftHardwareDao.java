package cn.com.winning.ssgj.dao;

import cn.com.winning.ssgj.domain.EtSoftHardware;
import cn.com.winning.ssgj.dao.EntityDao;

import java.util.List;

/**
 * @author SSGJ
 * @date 2018-01-18 10:11:47
 */
public interface EtSoftHardwareDao extends EntityDao<EtSoftHardware> {
    void insertEtSoftHardwareByList(List<EtSoftHardware> etSoftHardwares);

    List<EtSoftHardware> selectEtSoftHardwareByProductInfo(EtSoftHardware etSoftHardware);

}
