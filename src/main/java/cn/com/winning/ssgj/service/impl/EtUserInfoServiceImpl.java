package cn.com.winning.ssgj.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;


import cn.com.winning.ssgj.base.exception.SSGJException;
import cn.com.winning.ssgj.base.helper.SSGJHelper;
import cn.com.winning.ssgj.base.util.StringUtil;
import cn.com.winning.ssgj.dao.PmisProjectBasicInfoDao;
import cn.com.winning.ssgj.dao.SysDictInfoDao;
import cn.com.winning.ssgj.dao.SysUserInfoDao;
import cn.com.winning.ssgj.domain.PmisProjectBasicInfo;
import cn.com.winning.ssgj.domain.SysDictInfo;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private PmisProjectBasicInfoDao pmisProjectBasicInfoDao;
    @Autowired
    private SysDictInfoDao sysDictInfoDao;

    @Autowired
    private SSGJHelper ssgjHelper;
    @Autowired
    private SysUserInfoDao sysUserInfoDao;


    public Integer createEtUserInfo(EtUserInfo t) {
        initEtUserInfoForUserId(t);
        return this.etUserInfoDao.insertEntity(t);
    }

    private void initEtUserInfoForUserId(EtUserInfo t) {
        if (t.getUserType() == 1) {
            SysUserInfo userInfo = new SysUserInfo();
            userInfo.setUserid(t.getUserCard().trim());
            userInfo = sysUserInfoDao.selectSysUserInfoByUserCode(userInfo);
            if (userInfo != null) {
                t.setUserId(userInfo.getId());
            }
        }


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
        initEtUserInfoForUserId(t);
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
            userMap.put("userType", et.getMap().get("userType").toString());
            userMap.put("userCard", et.getUserCard());
            userMap.put("cName", et.getCName());
            userMap.put("orgName", et.getOrgName());
            userMap.put("positionName", et.getMap().get("positionName").toString());
            userMap.put("telephone", et.getTelephone());
            userMap.put("email", et.getEmail());
            dataList.add(userMap);
        }
        dataMap.put("colList", colList);
        dataMap.put("colSize", colList.size());
        dataMap.put("data", dataList);
        ExcelUtil.writeExcel(dataList, colList, colList.size(), path, 1);
    }

    public void createEtUserInfoList(List<List<Object>> userList, EtUserInfo userInfo) {
        PmisProjectBasicInfo basicInfo = new PmisProjectBasicInfo();
        basicInfo.setId(userInfo.getPmId());
        basicInfo = pmisProjectBasicInfoDao.selectEntity(basicInfo);
        for (List<Object> params : userList) {
            if (params.size() < 5) {
                throw new SSGJException("上传文件中存在必填项未填写。");
            }
        }
        for (List<Object> params : userList) {
            //查重
            EtUserInfo temp = new EtUserInfo();
            temp.setPmId(basicInfo.getId());
            temp.setSerialNo(basicInfo.getKhxx() + "");
            temp.setcId(basicInfo.getHtxx());
            temp.setUserCard(params.get(1).toString().trim());
            temp = etUserInfoDao.selectEntity(temp);
            if (temp != null && temp.getIsDel() != 2) {
                //存在则录入下一条数据
                continue;
            }
            EtUserInfo user = new EtUserInfo();
            user.setUserType(Integer.parseInt(resolveDictType(params.get(0).toString(), "userType")));
            user.setcId(basicInfo.getHtxx());
            user.setPmId(basicInfo.getId());
            user.setSerialNo(basicInfo.getKhxx() + "");
            user.setUserCard(params.get(1).toString().trim());
            user = etUserInfoDao.selectEntity(user);
            if (user != null) {
                user.setCName(params.get(2).toString());
                user.setOrgName(params.get(3).toString());
                user.setPositionName((String) resolveDictType(params.get(4).toString(), "positionName"));
                if (params.size() < 6) {
                    user.setTelephone(" ");
                } else {
                    user.setTelephone(params.get(5).toString());
                }
                if (params.size() < 7) {
                    user.setEmail(" ");
                } else {
                    user.setEmail(params.get(6).toString());
                }
                user.setIsDel(Constants.STATUS_USE);
                user.setUserId(getuserIdByUserCardAndUserType(user.getUserCard(), user.getUserType()));
                etUserInfoDao.updateEntity(user);
            } else {
                user = new EtUserInfo();
                user.setId(ssgjHelper.createEtUserInfoIdService());
                user.setUserType(Integer.parseInt(resolveDictType(params.get(0).toString(), "userType")));
                user.setcId(basicInfo.getHtxx());
                user.setSerialNo(basicInfo.getKhxx() + "");
                user.setPmId(basicInfo.getId());
                user.setUserCard(params.get(1).toString().trim());
                user.setCName(params.get(2).toString());
                user.setOrgName(params.get(3).toString());
                user.setPositionName((String) resolveDictType(params.get(4).toString(), "positionName"));
                if (params.size() < 6) {
                    user.setTelephone(" ");
                } else {
                    user.setTelephone(params.get(5).toString());
                }
                if (params.size() < 7) {
                    user.setEmail(" ");
                } else {
                    user.setEmail(params.get(6).toString());
                }
                user.setUserId(getuserIdByUserCardAndUserType(user.getUserCard(), user.getUserType()));
                user.setIsDel(Constants.STATUS_USE);
                etUserInfoDao.insertEntity(user);
            }
        }


    }

    private Long getuserIdByUserCardAndUserType(String userCard, Integer userType) {
        long userId = 0L;
        if (userType != 0) {
            SysUserInfo userInfo = new SysUserInfo();
            userInfo.setUserid(userCard);
            userInfo.setUserType(userType + "");
            userInfo = sysUserInfoDao.selectEntity(userInfo);
            if (userInfo != null) {
                userId = userInfo.getId();
            }
        }
        return userId;
    }

    /**
     * 字典值转换
     *
     * @param dictLabel
     * @param dictType
     * @return
     */
    private String resolveDictType(String dictLabel, String dictType) {
        SysDictInfo dictInfo = new SysDictInfo();
        dictInfo.setDictLabel(dictLabel.trim());
        dictInfo.setDictCode(dictType);
        dictInfo = this.sysDictInfoDao.selectEntity(dictInfo);
        if (dictInfo == null) {
            return dictLabel;
        } else {
            return dictInfo.getDictValue();
        }
    }

}
