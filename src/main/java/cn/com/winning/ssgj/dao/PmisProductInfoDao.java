package cn.com.winning.ssgj.dao;

import cn.com.winning.ssgj.domain.PmisProductInfo;
import cn.com.winning.ssgj.dao.EntityDao;

import java.util.List;
import java.util.Map;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:47
 */
public interface PmisProductInfoDao extends EntityDao<PmisProductInfo> {

    Integer selectPmisProductInfoCountByCodeAndName(PmisProductInfo t);

    List<PmisProductInfo> selectPmisProductInfoPaginatedListByCodeAndName(PmisProductInfo t);

    /**
     * 根据产品ID获取产品信息
     * @param productInfo
     * @return
     */
    List<PmisProductInfo> selectPmisProductInfoListByIdList(PmisProductInfo productInfo);


    List<PmisProductInfo> selectEasyDataPmisProductInfoList(PmisProductInfo t);

    List<PmisProductInfo> selectBasicDataPmisProductInfoList(PmisProductInfo t);

    /**
     * 根据项目ID和合同产品类型获取产品信息
     * @param param
     * @return
     */
    List<PmisProductInfo> selectProductInfoListByPmIdAndType(Map<String, Object> param);
}
