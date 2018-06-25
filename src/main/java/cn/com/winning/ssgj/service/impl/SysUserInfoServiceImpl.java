package cn.com.winning.ssgj.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import cn.com.winning.ssgj.base.Constants;

import cn.com.winning.ssgj.base.helper.SSGJHelper;
import cn.com.winning.ssgj.base.util.ExcelUtil;
import cn.com.winning.ssgj.base.util.MD5;
import cn.com.winning.ssgj.base.util.StringUtil;
import cn.com.winning.ssgj.domain.expand.FlotDataInfo;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.SysUserInfoDao;
import cn.com.winning.ssgj.domain.SysUserInfo;
import cn.com.winning.ssgj.service.SysUserInfoService;

/**
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
@Service
public class SysUserInfoServiceImpl implements SysUserInfoService {

    @Resource
    private SysUserInfoDao sysUserInfoDao;
    @Autowired
    private SSGJHelper ssgjHelper;


    public Integer createSysUserInfo(SysUserInfo t) {
        return this.sysUserInfoDao.insertEntity(t);
    }


    public SysUserInfo getSysUserInfo(SysUserInfo t) {
        return this.sysUserInfoDao.selectEntity(t);
    }


    public Integer getSysUserInfoCount(SysUserInfo t) {
        return (Integer) this.sysUserInfoDao.selectEntityCount(t);
    }


    public List<SysUserInfo> getSysUserInfoList(SysUserInfo t) {
        return this.sysUserInfoDao.selectEntityList(t);
    }


    public int modifySysUserInfo(SysUserInfo t) {
        return this.sysUserInfoDao.updateEntity(t);
    }


    public int removeSysUserInfo(SysUserInfo t) {
        return this.sysUserInfoDao.deleteEntity(t);
    }


    public List<SysUserInfo> getSysUserInfoPaginatedList(SysUserInfo t) {
        return this.sysUserInfoDao.selectEntityPaginatedList(t);
    }


    @Override
    public Integer getSysUserInfoQueryCount(SysUserInfo t) {
        return this.sysUserInfoDao.selectSysUserInfoQueryCount(t);
    }


    @Override
    public List<SysUserInfo> getSysUserInfoQueryPaginatedList(SysUserInfo t) {
        return this.sysUserInfoDao.selectSysUserInfoQueryPaginatedList(t);
    }

    @Override
    public List<SysUserInfo> getSysUserInfoQueryPageListASC(SysUserInfo t) {
        return this.sysUserInfoDao.selectSysUserInfoQueryPageListASC(t);
    }

    @Override

    public List<FlotDataInfo> countUserInfoByType() {
        List<FlotDataInfo> userinfos = this.sysUserInfoDao.countUserInfoByType();
        for (FlotDataInfo userinfo : userinfos) {
            if (Constants.User.USER_TYPE_ADMIN_LABEL.equals(userinfo.getLabel())) {
                userinfo.setColor("#DA5430");
            } else if (Constants.User.USER_TYPE_COMPANY_LABEL.equals(userinfo.getLabel())) {
                userinfo.setColor("#68BC31");
            } else if (Constants.User.USER_TYPE_HOSPITAL_LABEL.equals(userinfo.getLabel())) {
                userinfo.setColor("#2091CF");
            }
        }
        return userinfos;
    }

    @Override
    public void generateUserInfo(SysUserInfo queryUser, HttpServletResponse response, String fileName) {
        List<SysUserInfo> queryUserList = this.sysUserInfoDao.selectEntityList(queryUser);
        List<String> colList = new ArrayList<String>();
        colList.add("userid");
        colList.add("yhmc");
        colList.add("clo1");
        colList.add("clo2");
        colList.add("mobile");
        colList.add("email");
        List<String> colNameList = new ArrayList<String>();
        colNameList.add("工号 *");
        colNameList.add("姓名 *");
        colNameList.add("部门 *");
        colNameList.add("角色 *");
        colNameList.add("手机号码");
        colNameList.add("邮箱");
        List<Map> dataList = new ArrayList<Map>();

        for (SysUserInfo userInfo : queryUserList) {
            Map<String, String> userMap = new HashMap<>();
            userMap.put("userid", userInfo.getUserid());
            userMap.put("yhmc", userInfo.getYhmc());
            userMap.put("clo1", userInfo.getClo1());
            userMap.put("clo2", userInfo.getClo2());
            userMap.put("mobile", userInfo.getMobile());
            userMap.put("email", userInfo.getEmail());
            dataList.add(userMap);
        }
        //创建工作簿
        Workbook workbook = new HSSFWorkbook();
        ExcelUtil.exportExcelByStream(dataList, colList, colNameList, response, workbook, fileName);
    }

    @Override
    public void createHospitalUserInfo(List<List<Object>> userList,SysUserInfo userInfo) {
        long serialNo = userInfo.getSsgs();
        for (List<Object> params : userList) {
            String userid = serialNo +""+ params.get(0).toString();
            String yhmc = params.get(1).toString();
            String clo1 = params.get(2).toString();
            String clo2 = params.get(3).toString();
            String mobile = "";
            if(params.size() >= 5 && !StringUtil.isEmptyOrNull(params.get(4).toString())){
                mobile = params.get(4).toString();
            }
            String email = "";
            if(params.size() >= 6 && !StringUtil.isEmptyOrNull(params.get(5).toString())){
                mobile = params.get(5).toString();
            }
            SysUserInfo user = new SysUserInfo();
            user.setStatus(1);
            user.setUserType(Constants.User.USER_TYPE_HOSPITAL);
            user.setUserid(userid);
            user.setSsgs(serialNo);
            user = this.getSysUserInfo(user);
            if(user != null){
                user.setYhmc(yhmc);
                user.setName(user.getYhmc() + "(" + user.getUserid() + ")");
                user.setClo1(clo1);
                user.setClo2(clo2);
                user.setEmail(email);
                user.setMobile(mobile);
                user.setPassword(MD5.stringMD5(user.getUserid()));
                this.sysUserInfoDao.updateEntity(user);
            }else{
                user = new SysUserInfo();
                user.setId(ssgjHelper.createUserId());
                user.setStatus(1);
                user.setUserType(Constants.User.USER_TYPE_HOSPITAL);
                user.setUserid(userid);
                user.setSsgs(serialNo);
                user.setYhmc(yhmc);
                user.setName(user.getYhmc() + "(" + user.getUserid() + ")");
                user.setClo1(clo1);
                user.setClo2(clo2);
                user.setEmail(email);
                user.setMobile(mobile);
                user.setPassword(MD5.stringMD5(user.getUserid()));
                this.sysUserInfoDao.insertEntity(user);
            }
        }
    }

    @Override
    public SysUserInfo getSysUserInfoById(long userid) {
        SysUserInfo userInfo = new SysUserInfo();
        userInfo.setId(userid);
        return sysUserInfoDao.selectEntity(userInfo);
    }

    @Override
    public List<SysUserInfo> getSysUserInfoListByUserIdList(List<Long> userIdList) {
        SysUserInfo userInfo = new SysUserInfo();
        userInfo.setStatus(Constants.PMIS_STATUS_USE);
        userInfo.setUserType(Constants.User.USER_STATUS_NORMAL);
        userInfo.getMap().put("pks",userIdList);
        return sysUserInfoDao.selectSysUserInfoListByUserIdList(userInfo);
    }

}
