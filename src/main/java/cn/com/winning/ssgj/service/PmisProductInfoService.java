package cn.com.winning.ssgj.service;

import java.util.List;

import cn.com.winning.ssgj.domain.PmisProductInfo;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
public interface PmisProductInfoService {
	/**
	 * 添加ProductInfo
	 * @param t
	 * @return
	 */
    Integer createPmisProductInfo(PmisProductInfo t);
    /**
     * 修改ProductInfo
     * @param t
     * @return
     */
    int modifyPmisProductInfo(PmisProductInfo t);
    /**
     * 删除ProductInfo
     * @param t
     * @return
     */
    int removePmisProductInfo(PmisProductInfo t);
    /**
     * 根据ID查询ProductInfo
     * @param t
     * @return
     */
    PmisProductInfo getPmisProductInfo(PmisProductInfo t);
    
    List<PmisProductInfo> getPmisProductInfoList(PmisProductInfo t);
    /**
     * 根据条件查询总数量
     * @param t
     * @return
     */
    Integer getPmisProductInfoCount(PmisProductInfo t);

    List<PmisProductInfo> getPmisProductInfoPaginatedList(PmisProductInfo t);

    Integer getPmisProductInfoCountByCodeAndName(PmisProductInfo t);

    List<PmisProductInfo> getPmisProductInfoPaginatedListByCodeAndName(PmisProductInfo t);

}