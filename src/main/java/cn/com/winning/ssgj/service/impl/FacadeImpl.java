package cn.com.winning.ssgj.service.impl;

import javax.annotation.Resource;

import cn.com.winning.ssgj.domain.EtBusinessProcess;
import cn.com.winning.ssgj.service.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
@Component()
//@Service
public class FacadeImpl implements Facade {

    @Resource
    EtUserLookProjectService etUserLookProjectService;

    @Resource
    EtCustomerDetailService etCustomerDetailService;

    @Resource
    EtDataCheckService etDataCheckService;

    @Resource
    EtDevEnvHardwareService etDevEnvHardwareService;

    @Resource
    EtEasyDataCheckService etEasyDataCheckService;

    @Resource
    EtEasyDataCheckDetailService etEasyDataCheckDetailService;

    @Resource
    EtFloorQuestionInfoService etFloorQuestionInfoService;

    @Resource
    EtBusinessProcessService etBusinessProcessService;

    @Resource
    EtInterfaceInfoService etInterfaceInfoService;

    @Resource
    EtOnlineFileService etOnlineFileService;

    @Resource
    EtOnlineUserService etOnlineUserService;

    @Resource
    EtProcessManagerService etProcessManagerService;

    @Resource
    EtReportService etReportService;

    @Resource
    EtSimulateRecordService etSimulateRecordService;

    @Resource
    EtSiteInstallService etSiteInstallService;

    @Resource
    EtSiteInstallDetailService etSiteInstallDetailService;

    @Resource
    EtSiteQuestionInfoService etSiteQuestionInfoService;

    @Resource
    EtSoftHardwareService etSoftHardwareService;

    @Resource
    EtThirdIntterfaceService etThirdIntterfaceService;

    @Resource
    EtTrainVideoListService etTrainVideoListService;

    @Resource
    EtTrainVideoRecordService etTrainVideoRecordService;

    @Resource
    EtUserInfoService etUserInfoService;

    @Resource
    PmisContractInfoService pmisContractInfoService;

    @Resource
    PmisContractProductInfoService pmisContractProductInfoService;

    @Resource
    PmisCustomerInformationService pmisCustomerInformationService;

    @Resource
    PmisProductInfoService pmisProductInfoService;

    @Resource
    PmisProductLineInfoService pmisProductLineInfoService;

    @Resource
    PmisProjctUserService pmisProjctUserService;

    @Resource
    PmisProjectBasicInfoService pmisProjectBasicInfoService;

    @Resource
    SysDataInfoService sysDataInfoService;

    @Resource
    SysDataPopedomService sysDataPopedomService;

    @Resource
    SysDictInfoService sysDictInfoService;

    @Resource
    SysFlowAnswerService sysFlowAnswerService;

    @Resource
    SysFlowInfoService sysFlowInfoService;

    @Resource
    SysFlowQuestionService sysFlowQuestionService;

    @Resource
    SysFunService sysFunService;

    @Resource
    SysLogService sysLogService;

    @Resource
    SysModFunService sysModFunService;

    @Resource
    SysModPopedomService sysModPopedomService;

    @Resource
    SysModuleService sysModuleService;

    @Resource
    SysOrganizationService sysOrganizationService;

    @Resource
    SysParamsService sysParamsService;

    @Resource
    SysProductDataInfoService sysProductDataInfoService;

    @Resource
    SysProductFlowInfoService sysProductFlowInfoService;

    @Resource
    SysProductInterfaceInfoService sysProductInterfaceInfoService;

    @Resource
    SysProductPaperInfoService sysProductPaperInfoService;

    @Resource
    SysProductShInfoService sysProductShInfoService;

    @Resource
    SysReportInfoService sysReportInfoService;

    @Resource
    SysRoleInfoService sysRoleInfoService;

    @Resource
    SysRoleUserService sysRoleUserService;

    @Resource
    SysSoftHardwareInfoService sysSoftHardwareInfoService;

    @Resource
    SysThirdInterfaceInfoService sysThirdInterfaceInfoService;

    @Resource
    SysUserInfoService sysUserInfoService;

    @Resource
    SysTrainVideoRepoService sysTrainVideoRepoService;

    @Resource
    SysDataCheckScriptService sysDataCheckScriptService;

    @Resource
    SysOrgExtService sysOrgExtService;

    @Resource
    CommonQueryService commonQueryService;

    @Resource
    SysFloorsService sysFloorsService;
    @Resource
    SysHospitalDeptService sysHospitalDeptService;

    @Resource
    EtContractTaskService etContractTaskService;

    @Resource
    EtProjectImplPathService etProjectImplPathService;

    @Resource
    SysLoginUserService sysLoginUserService;

    @Resource
    SysUserVideoAuthService sysUserVideoAuthService;

    @Resource
    EtAccessTokenService etAccessTokenService;

    public EtAccessTokenService getEtAccessTokenService() {
        return etAccessTokenService;
    }

    public EtUserLookProjectService getEtUserLookProjectService() {
        return etUserLookProjectService;
    }

    public EtCustomerDetailService getEtCustomerDetailService() {
        return etCustomerDetailService;
    }

    public EtDataCheckService getEtDataCheckService() {
        return etDataCheckService;
    }

    public EtDevEnvHardwareService getEtDevEnvHardwareService() {
        return etDevEnvHardwareService;
    }

    public EtEasyDataCheckService getEtEasyDataCheckService() {
        return etEasyDataCheckService;
    }

    public EtEasyDataCheckDetailService getEtEasyDataCheckDetailService() {
        return etEasyDataCheckDetailService;
    }

    public EtFloorQuestionInfoService getEtFloorQuestionInfoService() {
        return etFloorQuestionInfoService;
    }

    @Override
    public EtBusinessProcessService getEtBusinessProcessService() {
        return etBusinessProcessService;
    }

    public EtInterfaceInfoService getEtInterfaceInfoService() {
        return etInterfaceInfoService;
    }

    public EtOnlineFileService getEtOnlineFileService() {
        return etOnlineFileService;
    }

    public EtOnlineUserService getEtOnlineUserService() {
        return etOnlineUserService;
    }

    public EtProcessManagerService getEtProcessManagerService() {
        return etProcessManagerService;
    }

    public EtReportService getEtReportService() {
        return etReportService;
    }

    public EtSimulateRecordService getEtSimulateRecordService() {
        return etSimulateRecordService;
    }

    public EtSiteInstallService getEtSiteInstallService() {
        return etSiteInstallService;
    }

    public EtSiteInstallDetailService getEtSiteInstallDetailService() {
        return etSiteInstallDetailService;
    }

    public EtSiteQuestionInfoService getEtSiteQuestionInfoService() {
        return etSiteQuestionInfoService;
    }

    public EtSoftHardwareService getEtSoftHardwareService() {
        return etSoftHardwareService;
    }

    public EtThirdIntterfaceService getEtThirdIntterfaceService() {
        return etThirdIntterfaceService;
    }

    public EtTrainVideoListService getEtTrainVideoListService() {
        return etTrainVideoListService;
    }

    public EtTrainVideoRecordService getEtTrainVideoRecordService() {
        return etTrainVideoRecordService;
    }

    public EtUserInfoService getEtUserInfoService() {
        return etUserInfoService;
    }

    public PmisContractInfoService getPmisContractInfoService() {
        return pmisContractInfoService;
    }

    public PmisContractProductInfoService getPmisContractProductInfoService() {
        return pmisContractProductInfoService;
    }

    public PmisCustomerInformationService getPmisCustomerInformationService() {
        return pmisCustomerInformationService;
    }

    public PmisProductInfoService getPmisProductInfoService() {
        return pmisProductInfoService;
    }

    public PmisProductLineInfoService getPmisProductLineInfoService() {
        return pmisProductLineInfoService;
    }

    public PmisProjctUserService getPmisProjctUserService() {
        return pmisProjctUserService;
    }

    public PmisProjectBasicInfoService getPmisProjectBasicInfoService() {
        return pmisProjectBasicInfoService;
    }

    public SysDataInfoService getSysDataInfoService() {
        return sysDataInfoService;
    }

    public SysDataPopedomService getSysDataPopedomService() {
        return sysDataPopedomService;
    }

    public SysDictInfoService getSysDictInfoService() {
        return sysDictInfoService;
    }

    public SysFlowAnswerService getSysFlowAnswerService() {
        return sysFlowAnswerService;
    }

    public SysFlowInfoService getSysFlowInfoService() {
        return sysFlowInfoService;
    }

    public SysFlowQuestionService getSysFlowQuestionService() {
        return sysFlowQuestionService;
    }

    public SysFunService getSysFunService() {
        return sysFunService;
    }

    public SysLogService getSysLogService() {
        return sysLogService;
    }

    public SysModFunService getSysModFunService() {
        return sysModFunService;
    }

    public SysModPopedomService getSysModPopedomService() {
        return sysModPopedomService;
    }

    public SysModuleService getSysModuleService() {
        return sysModuleService;
    }

    public SysOrganizationService getSysOrganizationService() {
        return sysOrganizationService;
    }

    public SysParamsService getSysParamsService() {
        return sysParamsService;
    }

    public SysProductDataInfoService getSysProductDataInfoService() {
        return sysProductDataInfoService;
    }

    public SysProductFlowInfoService getSysProductFlowInfoService() {
        return sysProductFlowInfoService;
    }

    public SysProductInterfaceInfoService getSysProductInterfaceInfoService() {
        return sysProductInterfaceInfoService;
    }

    public SysProductPaperInfoService getSysProductPaperInfoService() {
        return sysProductPaperInfoService;
    }

    public SysProductShInfoService getSysProductShInfoService() {
        return sysProductShInfoService;
    }

    public SysReportInfoService getSysReportInfoService() {
        return sysReportInfoService;
    }

    public SysRoleInfoService getSysRoleInfoService() {
        return sysRoleInfoService;
    }

    public SysRoleUserService getSysRoleUserService() {
        return sysRoleUserService;
    }

    public SysSoftHardwareInfoService getSysSoftHardwareInfoService() {
        return sysSoftHardwareInfoService;
    }

    public SysThirdInterfaceInfoService getSysThirdInterfaceInfoService() {
        return sysThirdInterfaceInfoService;
    }

    public SysUserInfoService getSysUserInfoService() {
        return sysUserInfoService;
    }

    public SysTrainVideoRepoService getSysTrainVideoRepoService() {
        return sysTrainVideoRepoService;
    }

    public SysDataCheckScriptService getSysDataCheckScriptService() {
        return sysDataCheckScriptService;
    }

    @Override
    public SysOrgExtService getSysOrgExtService() {
        return sysOrgExtService;
    }

    @Override
    public CommonQueryService getCommonQueryService() {
        return commonQueryService;
    }

    @Override
    public SysFloorsService getSysFloorsService() {
        return sysFloorsService;
    }

    @Override
    public SysHospitalDeptService getSysHospitalDeptService() {
        return sysHospitalDeptService;
    }

    @Override
    public EtContractTaskService getEtContractTaskService() {
        return etContractTaskService;
    }

    @Override
    public EtProjectImplPathService getEtProjectImplPathService() {
        return etProjectImplPathService;
    }

    @Override
    public SysLoginUserService getSysLoginUserService() {
        return sysLoginUserService;
    }

    @Override
    public SysUserVideoAuthService getSysUserVideoAuthService() {
        return sysUserVideoAuthService;
    }

    @Resource
    EtDepartmentService etDepartmentService;

    public EtDepartmentService getEtDepartmentService() {
        return etDepartmentService;
    }

    @Resource
    EtLogService etLogService;

    public EtLogService getEtLogService() {
        return etLogService;
    }
}
