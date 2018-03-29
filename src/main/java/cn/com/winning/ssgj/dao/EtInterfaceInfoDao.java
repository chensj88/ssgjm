package cn.com.winning.ssgj.dao;

import cn.com.winning.ssgj.domain.EtInterfaceInfo;
import cn.com.winning.ssgj.dao.EntityDao;
import sun.security.krb5.internal.crypto.EType;

import java.util.List;

/**
 * @author SSGJ
 * @date 2018-01-18 10:11:47
 */
public interface EtInterfaceInfoDao extends EntityDao<EtInterfaceInfo> {

    /**
     * 根据pmid获取接口信息
     * @return
     */
    List<EtInterfaceInfo> selectEtInterfaceInfoMergePageList(EtInterfaceInfo etInterfaceInfo);

    /**
     * 根据pmid获取接口信息数
     * @return
     */
    Integer selectEtInterfaceInfoMergeCount(EtInterfaceInfo etInterfaceInfo);

}
