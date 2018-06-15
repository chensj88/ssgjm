package cn.com.winning.ssgj.service;


import cn.com.winning.ssgj.domain.EtBusinessProcess;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
public interface Facade {

    EtUserLookProjectService getEtUserLookProjectService();

    EtCustomerDetailService getEtCustomerDetailService();

    EtDataCheckService getEtDataCheckService();

    EtDevEnvHardwareService getEtDevEnvHardwareService();

    EtEasyDataCheckService getEtEasyDataCheckService();

    EtEasyDataCheckDetailService getEtEasyDataCheckDetailService();

    EtFloorQuestionInfoService getEtFloorQuestionInfoService();

    EtBusinessProcessService getEtBusinessProcessService();

    EtInterfaceInfoService getEtInterfaceInfoService();

    EtOnlineFileService getEtOnlineFileService();

    EtOnlineUserService getEtOnlineUserService();

    EtProcessManagerService getEtProcessManagerService();

    EtReportService getEtReportService();

    EtSimulateRecordService getEtSimulateRecordService();

    EtSiteInstallService getEtSiteInstallService();

    EtSiteInstallDetailService getEtSiteInstallDetailService();

    EtSiteQuestionInfoService getEtSiteQuestionInfoService();

    EtSoftHardwareService getEtSoftHardwareService();

    EtThirdIntterfaceService getEtThirdIntterfaceService();

    EtTrainVideoListService getEtTrainVideoListService();

    EtTrainVideoRecordService getEtTrainVideoRecordService();

    EtUserInfoService getEtUserInfoService();

    EtDepartmentService getEtDepartmentService();

    PmisContractInfoService getPmisContractInfoService();

    PmisContractProductInfoService getPmisContractProductInfoService();

    PmisCustomerInformationService getPmisCustomerInformationService();

    PmisProductInfoService getPmisProductInfoService();

    PmisProductLineInfoService getPmisProductLineInfoService();

    PmisProjctUserService getPmisProjctUserService();

    PmisProjectBasicInfoService getPmisProjectBasicInfoService();

    SysDataInfoService getSysDataInfoService();

    SysDataPopedomService getSysDataPopedomService();

    SysDictInfoService getSysDictInfoService();

    SysFlowAnswerService getSysFlowAnswerService();

    SysFlowInfoService getSysFlowInfoService();

    SysFlowQuestionService getSysFlowQuestionService();

    SysFunService getSysFunService();

    SysLogService getSysLogService();

    SysModFunService getSysModFunService();

    SysModPopedomService getSysModPopedomService();

    SysModuleService getSysModuleService();

    SysOrganizationService getSysOrganizationService();

    SysParamsService getSysParamsService();

    SysProductDataInfoService getSysProductDataInfoService();

    SysProductFlowInfoService getSysProductFlowInfoService();

    SysProductInterfaceInfoService getSysProductInterfaceInfoService();

    SysProductPaperInfoService getSysProductPaperInfoService();

    SysProductShInfoService getSysProductShInfoService();

    SysReportInfoService getSysReportInfoService();

    SysRoleInfoService getSysRoleInfoService();

    SysRoleUserService getSysRoleUserService();

    SysSoftHardwareInfoService getSysSoftHardwareInfoService();

    SysThirdInterfaceInfoService getSysThirdInterfaceInfoService();

    SysUserInfoService getSysUserInfoService();

    SysTrainVideoRepoService getSysTrainVideoRepoService();

    SysDataCheckScriptService getSysDataCheckScriptService();

    SysOrgExtService getSysOrgExtService();

    CommonQueryService getCommonQueryService();

    SysFloorsService getSysFloorsService();

    SysHospitalDeptService getSysHospitalDeptService();

    EtContractTaskService getEtContractTaskService();

    EtProjectImplPathService getEtProjectImplPathService();

    SysLoginUserService getSysLoginUserService();

    SysUserVideoAuthService getSysUserVideoAuthService();

    EtAccessTokenService getEtAccessTokenService();

    EtLogService getEtLogService();

    EtStartEndService getEtStartEndService();


}