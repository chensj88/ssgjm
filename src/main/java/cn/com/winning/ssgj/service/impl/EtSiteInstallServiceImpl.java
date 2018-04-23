package cn.com.winning.ssgj.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;


import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.util.ExcelUtil;
import cn.com.winning.ssgj.base.util.MD5;
import cn.com.winning.ssgj.domain.SysUserInfo;
import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.EtSiteInstallDao;
import cn.com.winning.ssgj.domain.EtSiteInstall;
import cn.com.winning.ssgj.service.EtSiteInstallService;

/**
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
@Service
public class EtSiteInstallServiceImpl implements EtSiteInstallService {

    @Resource
    private EtSiteInstallDao etSiteInstallDao;



    public Integer createEtSiteInstall(EtSiteInstall t) {
        return this.etSiteInstallDao.insertEntity(t);
    }


    public EtSiteInstall getEtSiteInstall(EtSiteInstall t) {
        return this.etSiteInstallDao.selectEntity(t);
    }


    public Integer getEtSiteInstallCount(EtSiteInstall t) {
        return (Integer) this.etSiteInstallDao.selectEntityCount(t);
    }


    public List<EtSiteInstall> getEtSiteInstallList(EtSiteInstall t) {
        return this.etSiteInstallDao.selectEntityList(t);
    }


    public int modifyEtSiteInstall(EtSiteInstall t) {
        return this.etSiteInstallDao.updateEntity(t);
    }


    public int removeEtSiteInstall(EtSiteInstall t) {
        return this.etSiteInstallDao.deleteEntity(t);
    }


    public List<EtSiteInstall> getEtSiteInstallPaginatedList(EtSiteInstall t) {
        return this.etSiteInstallDao.selectEntityPaginatedList(t);
    }

    @Override
    public List<EtSiteInstall> getEtSiteInstallNameList(EtSiteInstall t) {
        return this.etSiteInstallDao.selectEtSiteInstallNameList(t);
    }

    @Override
    public List<EtSiteInstall> getEtSiteInstallListWithSum(EtSiteInstall t) {
        return this.etSiteInstallDao.selectEtSiteInstallListWithSum(t);
    }

    @Override
    public List<EtSiteInstall> getEtSiteInstallListWithInfo(EtSiteInstall t) {
        return this.etSiteInstallDao.selectEtSiteInstallListWithInfo(t);
    }

    @Override
    public void getNerateSiteIntallExcel(EtSiteInstall info, String path) {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        List<EtSiteInstall> querySiteList = this.etSiteInstallDao.selectEtSiteInstallListWithInfo(info);
        int total = (Integer) this.etSiteInstallDao.selectEntityCount(info);
        List<String> colList = new ArrayList<String>();
        colList.add("deptName");
        colList.add("pdName");
        colList.add("hdName");
        colList.add("noScopeCode");
        colList.add("puserId");
        colList.add("num");
        List<Map> dataList = new ArrayList<Map>();

        for (EtSiteInstall siteInfo : querySiteList) {
            Map<String, String> siteMap = new HashMap<>();
            siteMap.put("deptName", siteInfo.getDeptName());
            siteMap.put("pdName", siteInfo.getPdName());
            siteMap.put("hdName", siteInfo.getHdName());
            siteMap.put("noScopeCode", siteInfo.getNoScopeCode());
            siteMap.put("puserId", String.valueOf(siteInfo.getMap().get("puserName")));
            siteMap.put("num", String.valueOf(siteInfo.getNum()));
            dataList.add(siteMap);
        }

        dataMap.put("colList", colList);
        dataMap.put("colSize", colList.size());
        dataMap.put("data", dataList);
        ExcelUtil.writeExcel(dataList, colList, colList.size(), path);


    }

    @Override
    public List<EtSiteInstall> getEtSiteInstallGroupPuser(EtSiteInstall t) {
        return this.etSiteInstallDao.selectEtSiteInstallGroupPuser(t);
    }

    @Override
    public void createEtSiteInstallDeptInfo(List<List<Object>> deptList, EtSiteInstall info) {
        for (List<Object> params : deptList) {
            String dept_code = params.get(0).toString();
            String dept_name = params.get(1).toString();


//            SysUserInfo user = new SysUserInfo();
//            user.setStatus(1);
//            user.setUserType(Constants.User.USER_TYPE_HOSPITAL);
//            user.setUserid(userid);
//            user.setSsgs(c_id);
//            user = this.getSysUserInfo(user);


        }


    }

}
