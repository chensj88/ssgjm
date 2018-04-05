package cn.com.winning.ssgj.service.impl;

import java.sql.Timestamp;
import java.util.*;

import javax.annotation.Resource;


import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.helper.SSGJHelper;
import cn.com.winning.ssgj.base.util.ExcelUtil;
import cn.com.winning.ssgj.dao.PmisProjectBasicInfoDao;
import cn.com.winning.ssgj.domain.EtUserInfo;
import cn.com.winning.ssgj.domain.PmisProjectBasicInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.EtOnlineUserDao;
import cn.com.winning.ssgj.domain.EtOnlineUser;
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
            Map<String, String> userMap = new HashMap<>();
            userMap.put("userCard",et.getUserCode());
            userMap.put("cName",et.getCName());
            userMap.put("roleName",et.getRoleName());
            userMap.put("responseDept",et.getResponseDept());
            userMap.put("responseSite",et.getResponseSite());
            userMap.put("telephone",et.getTelephone());
            userMap.put("wechatNo",et.getWechatNo());
            userMap.put("email",et.getEmail());
            userMap.put("lodging",et.getLodging());
            dataList.add(userMap);
        }
        ExcelUtil.writeExcel(dataList, colList, colList.size(), path);
	}

	@Override
	public void createEtOnlineUserList(List<List<Object>> userList,EtOnlineUser etOnlineUser) {
        PmisProjectBasicInfo basicInfo = new PmisProjectBasicInfo();
        basicInfo.setId(etOnlineUser.getPmId());
        basicInfo = pmisProjectBasicInfoDao.selectEntity(basicInfo);
        for (List<Object> params : userList) {
            EtOnlineUser user = new EtOnlineUser();
            user.setCId(basicInfo.getHtxx());
            user.setPmId(basicInfo.getId());
            user.setUserCode(params.get(0).toString().trim());
            user = etOnlineUserDao.selectEntity(user);
            if(user != null){
                user.setStatus(Constants.STATUS_USE);
                user.setCName(params.get(1).toString());
                user.setRoleName(params.get(2).toString());
                user.setResponseDept(params.get(3).toString());
                user.setResponseSite(params.get(4).toString());
                user.setTelephone(params.get(5).toString());
                user.setWechatNo(params.get(6).toString());
                user.setEmail(params.get(7).toString());
                user.setLodging(params.get(8).toString());
                user.setOperator(etOnlineUser.getOperator());
                user.setOperatorTime(new Timestamp(new Date().getTime()));
                etOnlineUserDao.updateEntity(user);
            }else{
                user = new EtOnlineUser();
                user.setId(ssgjHelper.createEtOnlineInfoIdService());
                user.setPmId(etOnlineUser.getPmId());
                user.setcId(etOnlineUser.getcId());
                user.setStatus(Constants.STATUS_USE);
                user.setUserCode(params.get(0).toString().trim());
                user.setCName(params.get(1).toString());
                user.setRoleName(params.get(2).toString());
                user.setResponseDept(params.get(3).toString());
                user.setResponseSite(params.get(4).toString());
                user.setTelephone(params.get(5).toString());
                user.setWechatNo(params.get(6).toString());
                user.setEmail(params.get(7).toString());
                user.setLodging(params.get(8).toString());
                user.setCreator(etOnlineUser.getOperator());
                user.setCreateTime(new Timestamp(new Date().getTime()));
                user.setOperator(etOnlineUser.getOperator());
                user.setOperatorTime(new Timestamp(new Date().getTime()));
                etOnlineUserDao.insertEntity(user);
            }
        }
		
	}

}
