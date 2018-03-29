package cn.com.winning.ssgj.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;


import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.util.ExcelUtil;
import cn.com.winning.ssgj.base.util.MD5;
import cn.com.winning.ssgj.dao.EtUserInfoDao;
import cn.com.winning.ssgj.domain.EtUserInfo;
import cn.com.winning.ssgj.domain.SysUserInfo;
import cn.com.winning.ssgj.service.EtUserInfoService;

/**
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
@Service
public class EtUserInfoServiceImpl implements EtUserInfoService {

    @Resource
    private EtUserInfoDao etUserInfoDao;



    public Integer createEtUserInfo(EtUserInfo t) {
        return this.etUserInfoDao.insertEntity(t);
    }


    public EtUserInfo getEtUserInfo(EtUserInfo t) {
        return this.etUserInfoDao.selectEntity(t);
    }


    public Integer getEtUserInfoCount(EtUserInfo t) {
        return (Integer) this.etUserInfoDao.selectEntityCount(t);
    }


    public List<EtUserInfo> getEtUserInfoList(EtUserInfo t) {
        return this.etUserInfoDao.selectEntityList(t);
    }


    public int modifyEtUserInfo(EtUserInfo t) {
        return this.etUserInfoDao.updateEntity(t);
    }


    public int removeEtUserInfo(EtUserInfo t) {
        return this.etUserInfoDao.deleteEntity(t);
    }


    public List<EtUserInfo> getEtUserInfoPaginatedList(EtUserInfo t) {
        return this.etUserInfoDao.selectEntityPaginatedList(t);
    }


	@Override
	public void generateEtUserInfo(EtUserInfo etUserInfo, String path) {

	    Map<String, Object> dataMap = new HashMap<String, Object>();
        List<EtUserInfo> etUserInfoList = this.etUserInfoDao.selectEntityList(etUserInfo);
        List<String> colList = new ArrayList<String>();
        colList.add("userType");
        colList.add("userCard");
        colList.add("cName");
        colList.add("orgName");
        colList.add("positionName");
        colList.add("telephone");
        colList.add("email");
        List<Map> dataList = new ArrayList<Map>();

        for (EtUserInfo et : etUserInfoList) {
            Map<String, String> userMap = new HashMap<>();
            userMap.put("userType", et.getUserType().toString());
            userMap.put("userCard", et.getUserCard());
            userMap.put("cName", et.getCName());
            userMap.put("orgName", et.getOrgName());
            userMap.put("positionName", et.getPositionName());
            userMap.put("telephone", et.getTelephone());
            userMap.put("email", et.getEmail());
            dataList.add(userMap);
        }
        dataMap.put("colList", colList);
        dataMap.put("colSize", colList.size());
        dataMap.put("data", dataList);
        ExcelUtil.writeExcel(dataList, colList, colList.size(), path);
		
	}


	public void createEtUserInfoList(List<List<Object>> userList) {
//		   long c_id = 9879L;
//	        for (List<Object> params : userList) {
//	            String userid = params.get(0).toString();
//	            String yhmc = params.get(1).toString();
//	            String clo1 = params.get(2).toString();
//	            String clo2 = params.get(3).toString();
//	            String mobile = params.get(4).toString();
//	            String email = params.get(5).toString();
//	            EtUserInfo user = new EtUserInfo();
//	            user.setIsDel(Constants.STATUS_UNUSE);
//	            user.setUserType(Constants.User.USER_TYPE_HOSPITAL);
//	            user.setUserid(userid);
//	            user.setSsgs(c_id);
//	            user = this.getSysUserInfo(user);
//	            if(user != null){
//	                user.setYhmc(yhmc);
//	                user.setName(user.getYhmc() + "(" + user.getUserid() + ")");
//	                user.setClo1(clo1);
//	                user.setClo2(clo2);
//	                user.setEmail(email);
//	                user.setMobile(mobile);
//	                user.setPassword(MD5.stringMD5(user.getUserid()));
//	                this.sysUserInfoDao.updateEntity(user);
//	            }else{
//	                user = new SysUserInfo();
//	                user.setId(ssgjHelper.createUserId());
//	                user.setStatus(1);
//	                user.setUserType(Constants.User.USER_TYPE_HOSPITAL);
//	                user.setUserid(userid);
//	                user.setSsgs(c_id);
//	                user.setYhmc(yhmc);
//	                user.setName(user.getYhmc() + "(" + user.getUserid() + ")");
//	                user.setClo1(clo1);
//	                user.setClo2(clo2);
//	                user.setEmail(email);
//	                user.setMobile(mobile);
//	                user.setPassword(MD5.stringMD5(user.getUserid()));
//	                this.sysUserInfoDao.insertEntity(user);
//	            }
//	        }
//		
	}

}
