package cn.com.winning.ssgj.dao;

import cn.com.winning.ssgj.domain.PmisContractProductInfo;
import cn.com.winning.ssgj.domain.PmisProductInfo;
import cn.com.winning.ssgj.domain.PmisProductLineInfo;
import cn.com.winning.ssgj.dao.EntityDao;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * @author SSGJ
 * @date 2018-01-18 10:11:47
 */
public interface PmisProductLineInfoDao extends EntityDao<PmisProductLineInfo> {

    List<PmisProductLineInfo> selectPmisProductLineInfoByNameForList(PmisProductLineInfo t);

    Integer selectPmisProductLineInfoByNameForCount(PmisProductLineInfo t);

    /**
     * 根据产品获取产品条线
     */
    List<PmisProductLineInfo> selectPmisProductLineInfoByProductInfo(List<PmisProductInfo> pmisProductInfos);


    List<PmisProductLineInfo>selectPmisProductLineInfoByPmidAndType(PmisContractProductInfo pmisContractProductInfo);

}
