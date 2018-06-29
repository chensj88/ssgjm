package cn.com.winning.ssgj.dao;

import cn.com.winning.ssgj.domain.EtDataCheck;
import cn.com.winning.ssgj.dao.EntityDao;

import java.util.List;

/**
 * @author SSGJ
 * @date 2018-01-18 10:11:47
 */
public interface EtDataCheckDao extends EntityDao<EtDataCheck> {

    List<EtDataCheck> selectEtDataCheckListByPmIdAndDataType(EtDataCheck etDataCheck);

    List<EtDataCheck> getInitEtDataCheck(EtDataCheck etDataCheck);


}
