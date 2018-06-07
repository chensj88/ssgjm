package cn.com.winning.ssgj.service;

import java.util.List;

import cn.com.winning.ssgj.domain.PmisCustomerInformation;
import cn.com.winning.ssgj.domain.PmisProjectBasicInfo;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
public interface PmisCustomerInformationService {

    Integer createPmisCustomerInformation(PmisCustomerInformation t);

    int modifyPmisCustomerInformation(PmisCustomerInformation t);

    int removePmisCustomerInformation(PmisCustomerInformation t);

    PmisCustomerInformation getPmisCustomerInformation(PmisCustomerInformation t);

    List<PmisCustomerInformation> getPmisCustomerInformationList(PmisCustomerInformation t);

    Integer getPmisCustomerInformationCount(PmisCustomerInformation t);

    List<PmisCustomerInformation> getPmisCustomerInformationPaginatedList(PmisCustomerInformation t);

    public int getPmisCustomerInformationCountFuzzy(PmisCustomerInformation c);

    public List<PmisCustomerInformation> getPmisCustomerInformationPageListFuzzy(PmisCustomerInformation c);

    List<PmisCustomerInformation> getCustomerInfoListByProjectList(PmisCustomerInformation basicInfoList);

    /**
     * 获取用户可以查看的客户信息
     * @param userId
     * @return
     */
    List<PmisCustomerInformation> getUserCanViewCustomerList(Long userId);

    List<PmisCustomerInformation> getAllCustomerByPageList( PmisCustomerInformation info);
}