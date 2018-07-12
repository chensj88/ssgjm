package cn.com.winning.ssgj.dao;

import cn.com.winning.ssgj.domain.EtEasyDataCheck;
import cn.com.winning.ssgj.dao.EntityDao;

import java.util.List;

/**
 * @author SSGJ
 * @date 2018-01-18 10:11:47
 */
public interface EtEasyDataCheckDao extends EntityDao<EtEasyDataCheck> {

    List<EtEasyDataCheck> selectEtEasyDataCheckListByPmIdAndDataType(EtEasyDataCheck etEasyDataCheck);

    List<EtEasyDataCheck> getInitEtEasyDataCheck(EtEasyDataCheck etEasyDataCheck);

}
