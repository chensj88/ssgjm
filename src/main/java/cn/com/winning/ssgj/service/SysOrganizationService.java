package cn.com.winning.ssgj.service;

import java.util.List;

import cn.com.winning.ssgj.domain.SysOrganization;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
public interface SysOrganizationService {

    Integer createSysOrganization(SysOrganization t);

    int modifySysOrganization(SysOrganization t);

    int removeSysOrganization(SysOrganization t);

    SysOrganization getSysOrganization(SysOrganization t);

    List<SysOrganization> getSysOrganizationList(SysOrganization t);

    Integer getSysOrganizationCount(SysOrganization t);

    List<SysOrganization> getSysOrganizationPaginatedList(SysOrganization t);

}