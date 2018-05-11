package cn.com.winning.ssgj.service.impl;

import java.sql.Timestamp;
import java.util.*;

import javax.annotation.Resource;


import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.helper.SSGJHelper;
import cn.com.winning.ssgj.base.util.ExcelUtil;
import cn.com.winning.ssgj.base.util.NumberParseUtil;
import cn.com.winning.ssgj.base.util.StringUtil;
import cn.com.winning.ssgj.dao.*;
import cn.com.winning.ssgj.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.service.EtOnlineUserService;

/**
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
@Service
public class EtOnlineUserServiceImpl implements EtOnlineUserService {

    @Resource
    private EtOnlineUserDao etOnlineUserDao;
    @Autowired
    private PmisProjectBasicInfoDao pmisProjectBasicInfoDao;
    @Autowired
    private EtDepartmentDao etDepartmentDao;
    @Autowired
    private EtSiteInstallDetailDao etSiteInstallDetailDao;
    @Autowired
    private EtSiteInstallDao etSiteInstallDao;
    @Autowired
    private SSGJHelper ssgjHelper;

    public Integer createEtOnlineUser(EtOnlineUser t) {
        return this.etOnlineUserDao.insertEntity(t);
    }


    public EtOnlineUser getEtOnlineUser(EtOnlineUser t) {
        return this.etOnlineUserDao.selectEntity(t);
    }


    public Integer getEtOnlineUserCount(EtOnlineUser t) {
        return (Integer) this.etOnlineUserDao.selectEntityCount(t);
    }


    public List<EtOnlineUser> getEtOnlineUserList(EtOnlineUser t) {
        return this.etOnlineUserDao.selectEntityList(t);
    }


    public int modifyEtOnlineUser(EtOnlineUser t) {
        return this.etOnlineUserDao.updateEntity(t);
    }


    public int removeEtOnlineUser(EtOnlineUser t) {
        return this.etOnlineUserDao.deleteEntity(t);
    }


    public List<EtOnlineUser> getEtOnlineUserPaginatedList(EtOnlineUser t) {
        return this.etOnlineUserDao.selectEntityPaginatedList(t);
    }


    @Override
    public void generateEtOnlineUser(EtOnlineUser etOnlineUser, String path) {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        List<EtOnlineUser> etUserInfoList = this.etOnlineUserDao.selectEntityList(etOnlineUser);
        List<String> colList = new ArrayList<String>();
        colList.add("userCard");
        colList.add("cName");
        colList.add("roleName");
        colList.add("responseDept");
        colList.add("responseSite");
        colList.add("telephone");
        colList.add("wechatNo");
        colList.add("email");
        colList.add("lodging");
        List<Map> dataList = new ArrayList<Map>();

        for (EtOnlineUser et : etUserInfoList) {
            EtDepartment departmentTemp = new EtDepartment();
            EtSiteInstallDetail siteInstallDetail = new EtSiteInstallDetail();
            Map<String, String> userMap = new HashMap<>();
            userMap.put("userCard", et.getUserCode());
            userMap.put("cName", et.getCName());
            userMap.put("roleName", et.getRoleName());
            Long deptId = NumberParseUtil.parseLong(et.getResponseDept());
            Long siteId = NumberParseUtil.parseLong(et.getResponseSite());
            if (deptId != null) {
                departmentTemp.setId(deptId);
                departmentTemp = etDepartmentDao.selectEntity(departmentTemp);
            }
            if (siteId != null) {
                siteInstallDetail.setId(siteId);
                siteInstallDetail = etSiteInstallDetailDao.selectEntity(siteInstallDetail);
            }
            userMap.put("responseDept", departmentTemp == null ? "" : departmentTemp.getDeptName());
            userMap.put("responseSite", siteInstallDetail == null ? "" : siteInstallDetail.getSiteName());
            userMap.put("telephone", et.getTelephone());
            userMap.put("wechatNo", et.getWechatNo());
            userMap.put("email", et.getEmail());
            userMap.put("lodging", et.getLodging());
            dataList.add(userMap);
        }
        ExcelUtil.writeExcel(dataList, colList, colList.size(), path);
    }

    @Override
    public void createEtOnlineUserList(List<List<Object>> userList, EtOnlineUser etOnlineUser, Long serialNo) {
        PmisProjectBasicInfo basicInfo = new PmisProjectBasicInfo();
        basicInfo.setId(etOnlineUser.getPmId());
        basicInfo = pmisProjectBasicInfoDao.selectEntity(basicInfo);
        for (List<Object> params : userList) {
            List<EtOnlineUser> etOnlineUsers = null;
            String deptName = null;
            String siteName = null;
            EtDepartment departmentTemp = null;
            EtSiteInstall etSiteInstall = null;
            EtSiteInstallDetail siteInstallDetail = null;
            EtOnlineUser user = new EtOnlineUser();
            user.setCId(basicInfo.getHtxx());
            user.setPmId(basicInfo.getId());
            user.setUserCode(params.get(0).toString().trim());
            //user = etOnlineUserDao.selectEntity(user);
            etOnlineUsers = etOnlineUserDao.selectEntityList(user);
            deptName = params.get(3).toString();
            siteName = params.get(4).toString();
            if (!StringUtil.isEmptyOrNull(deptName)) {
                departmentTemp = new EtDepartment();
                departmentTemp.setDeptName(deptName);
                departmentTemp.setSerialNo(serialNo);
                departmentTemp = etDepartmentDao.selectEntity(departmentTemp);
            }
            if (!StringUtil.isEmptyOrNull(siteName) && departmentTemp != null) {
                etSiteInstall = new EtSiteInstall();
                etSiteInstall.setSerialNo(serialNo.toString());
                etSiteInstall.setDeptCode(departmentTemp.getId().toString());
                etSiteInstall = etSiteInstallDao.selectEntity(etSiteInstall);
                if (etSiteInstall != null) {
                    siteInstallDetail = new EtSiteInstallDetail();
                    siteInstallDetail.setSourceId(etSiteInstall.getId());
                    siteInstallDetail.setSiteName(siteName);
                    siteInstallDetail = etSiteInstallDetailDao.selectEntity(siteInstallDetail);
                }else{
                    siteInstallDetail=null;
                }
            }
            if (etOnlineUsers == null || etOnlineUsers.size() <= 0) {
                user.setId(ssgjHelper.createEtOnlineInfoIdService());
                user.setStatus(Constants.STATUS_USE);
                user.setCName(params.get(1).toString());
                user.setRoleName(params.get(2).toString());
                user.setResponseDept(departmentTemp == null ? "" : departmentTemp.getId().toString());
                user.setResponseSite(siteInstallDetail == null ? "" : siteInstallDetail.getId().toString());
                user.setTelephone(params.get(5).toString());
                user.setWechatNo(params.get(6).toString());
                user.setEmail(params.get(7).toString());
                user.setLodging(params.get(8).toString());
                user.setCreator(etOnlineUser.getOperator());
                user.setCreateTime(new Timestamp(new Date().getTime()));
                user.setOperator(etOnlineUser.getOperator());
                user.setOperatorTime(new Timestamp(new Date().getTime()));
                etOnlineUserDao.insertEntity(user);
            } else {
                for (EtOnlineUser onlineUser : etOnlineUsers) {
                    onlineUser.setStatus(Constants.STATUS_USE);
                    onlineUser.setCName(params.get(1).toString());
                    onlineUser.setRoleName(params.get(2).toString());
                    onlineUser.setResponseDept(departmentTemp == null ? "" : departmentTemp.getId().toString());
                    onlineUser.setResponseSite(siteInstallDetail == null ? "" : siteInstallDetail.getId().toString());
                    onlineUser.setTelephone(params.get(5).toString());
                    onlineUser.setWechatNo(params.get(6).toString());
                    onlineUser.setEmail(params.get(7).toString());
                    onlineUser.setLodging(params.get(8).toString());
                    onlineUser.setOperator(etOnlineUser.getOperator());
                    onlineUser.setOperatorTime(new Timestamp(new Date().getTime()));
                    etOnlineUserDao.updateEntity(onlineUser);
                }
            }
        }

    }

}
