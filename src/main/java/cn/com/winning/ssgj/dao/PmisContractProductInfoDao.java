package cn.com.winning.ssgj.dao;

import cn.com.winning.ssgj.domain.PmisContractProductInfo;
import cn.com.winning.ssgj.dao.EntityDao;

import java.util.List;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:47
 */
public interface PmisContractProductInfoDao extends EntityDao<PmisContractProductInfo> {

    List<PmisContractProductInfo> selectPmisContractProductInfoMkList(PmisContractProductInfo t);
}
