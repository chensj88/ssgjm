package cn.com.winning.ssgj.service.impl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;


import cn.com.winning.ssgj.base.helper.SSGJHelper;
import cn.com.winning.ssgj.base.util.DateUtil;
import cn.com.winning.ssgj.base.util.ExcelUtil;
import cn.com.winning.ssgj.base.util.StringUtil;
import cn.com.winning.ssgj.dao.EtTempQuestionInfoDao;
import cn.com.winning.ssgj.domain.EtTempQuestionInfo;
import cn.com.winning.ssgj.domain.SysUserInfo;
import cn.com.winning.ssgj.service.SysUserInfoService;
import org.omg.CORBA.OMGVMCID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.winning.ssgj.dao.EtSiteQuestionInfoDao;
import cn.com.winning.ssgj.domain.EtSiteQuestionInfo;
import cn.com.winning.ssgj.service.EtSiteQuestionInfoService;

/**
 * @author SSGJ
 * @date 2018-01-18 10:11:48
 */
@Service
public class EtSiteQuestionInfoServiceImpl implements EtSiteQuestionInfoService {

    @Resource
    private EtSiteQuestionInfoDao etSiteQuestionInfoDao;
    @Autowired
    private EtTempQuestionInfoDao etTempQuestionInfoDao;
    @Autowired
    private SSGJHelper ssgjHelper;
    @Autowired
    private SysUserInfoService sysUserInfoService;


    public Integer createEtSiteQuestionInfo(EtSiteQuestionInfo t) {
        return this.etSiteQuestionInfoDao.insertEntity(t);
    }


    public EtSiteQuestionInfo getEtSiteQuestionInfo(EtSiteQuestionInfo t) {
        return this.etSiteQuestionInfoDao.selectEntity(t);
    }


    public Integer getEtSiteQuestionInfoCount(EtSiteQuestionInfo t) {
        return (Integer) this.etSiteQuestionInfoDao.selectEntityCount(t);
    }


    public List<EtSiteQuestionInfo> getEtSiteQuestionInfoList(EtSiteQuestionInfo t) {
        return this.etSiteQuestionInfoDao.selectEntityList(t);
    }


    public int modifyEtSiteQuestionInfo(EtSiteQuestionInfo t) {
        return this.etSiteQuestionInfoDao.updateEntity(t);
    }


    public int removeEtSiteQuestionInfo(EtSiteQuestionInfo t) {
        return this.etSiteQuestionInfoDao.deleteEntity(t);
    }


    public List<EtSiteQuestionInfo> getEtSiteQuestionInfoPaginatedList(EtSiteQuestionInfo t) {
        return this.etSiteQuestionInfoDao.selectEntityPaginatedList(t);
    }

    public void updateEtSiteQuestionInfoImg(EtSiteQuestionInfo t) {
        this.etSiteQuestionInfoDao.updateEtSiteQuestionInfoImg(t);

    }

    @Override
    public List<EtSiteQuestionInfo> getEtSiteQuestionInfoUserTotal(EtSiteQuestionInfo t) {
        return this.etSiteQuestionInfoDao.selectEtSiteQuestionInfoUserTotal(t);
    }

    @Override
    public void generateEtSiteQuestionInfo(EtSiteQuestionInfo info, String path) {
        List<EtSiteQuestionInfo> infoList = this.etSiteQuestionInfoDao.selectEntityList(info);
        List<String> colList = new ArrayList<String>();
        colList.add("deptName");
        colList.add("sysName");
        colList.add("menuName");
        colList.add("requireNo");
        colList.add("questionType");
        colList.add("questionDesc");
        colList.add("operTypeString");
        colList.add("priorityString");
        colList.add("allocateUser");
        colList.add("create_name");
        colList.add("createTimeString");
        colList.add("isPmis");

        List<Map> dataList = new ArrayList<Map>();

        for (EtSiteQuestionInfo qinfo : infoList) {
            Map<String, String> dataMap = new HashMap<>();
            dataMap.put("deptName",qinfo.getMap().get("deptName").toString());
            dataMap.put("sysName",qinfo.getMap().get("plName").toString());
            dataMap.put("menuName",qinfo.getMenuName());
            dataMap.put("requireNo",qinfo.getRequirementNo());
            dataMap.put("questionType",qinfo.getMap().get("dict_label").toString());
            dataMap.put("questionDesc",qinfo.getQuestionDesc());
            if(qinfo.getMap().get("operTypeString") == null){
                dataMap.put("operTypeString","");
            }else {
                dataMap.put("operTypeString",qinfo.getMap().get("operTypeString").toString());
            }
            if(qinfo.getMap().get("priorityString") == null){
                dataMap.put("priorityString","");
            }else {
                dataMap.put("priorityString",qinfo.getMap().get("priorityString").toString());
            }
            if(qinfo.getMap().get("allocateUser") == null){
                dataMap.put("allocateUser","");
            }else {
                dataMap.put("allocateUser",qinfo.getMap().get("allocateUser").toString());
            }
            dataMap.put("create_name",qinfo.getMap().get("create_name").toString());
            dataMap.put("createTimeString",qinfo.getMap().get("createTimeString").toString());
            dataMap.put("isPmis",qinfo.getPmisStatus() == 2 ? "否" :"是");
            dataList.add(dataMap);
        }
        ExcelUtil.writeExcel(dataList, colList, colList.size(), path);
    }

    @Override
    public List<Map<String, Object>> getEtSiteQuestionCountInfo(EtSiteQuestionInfo info) {

        return this.etSiteQuestionInfoDao.selectEtSiteQuestionCountInfo(info);
    }

    @Override
    public void createEtSiteQuestionInfo(List<List<Object>> questionList, EtSiteQuestionInfo info) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        for (List<Object> list : questionList) {
            EtTempQuestionInfo tempInfo = new EtTempQuestionInfo();
            tempInfo.setId(ssgjHelper.createtempWorkReportGenerateService());
            tempInfo.setPmId(info.getPmId());
            tempInfo.setCId(info.getCId());
            tempInfo.setSerialNo(info.getSerialNo());
            tempInfo.setPriorityType(list.get(0).toString());
            tempInfo.setSiteName(list.get(1).toString());
            tempInfo.setProductName(list.get(2).toString());
            tempInfo.setMenuName(list.get(3).toString());
            tempInfo.setQuestionDesc(list.get(4).toString());
            tempInfo.setQuestionVar(list.get(5).toString());
            tempInfo.setReasonVar(list.get(6).toString());
            tempInfo.setManuscriptVar(list.get(7).toString());
            tempInfo.setDiffcultVar(list.get(8).toString());
            tempInfo.setDevUser(list.get(9).toString());
            tempInfo.setDevUserName(list.get(10).toString());
            tempInfo.setIntroducer(list.get(11).toString());
            tempInfo.setIntroducerName(list.get(12).toString());
            tempInfo.setIntroducerDate(list.get(13).toString());
            tempInfo.setLinkman(list.get(14).toString());
            tempInfo.setMobile(list.get(15).toString());
            tempInfo.setOperVar(list.get(16).toString());
            tempInfo.setHopeFinishDate(list.get(17).toString());
            String userMsg  = "";
            if(list.size() >= 19 && !StringUtil.isEmptyOrNull(list.get(18).toString())){
                userMsg = list.get(18).toString();
            }
            tempInfo.setUserMessage(userMsg);
            String requireNo  = "";
            if(list.size() >= 20 && !StringUtil.isEmptyOrNull(list.get(19).toString())){
                requireNo = list.get(19).toString();
            }
            tempInfo.setRequirementNo(requireNo);
            etTempQuestionInfoDao.insertEntity(tempInfo);
        }
        EtTempQuestionInfo tempQuestionInfo = new EtTempQuestionInfo();
        tempQuestionInfo.setSerialNo(info.getSerialNo());
        etTempQuestionInfoDao.updateEtTempQuestionInfoDictValue(tempQuestionInfo);
        SysUserInfo user = sysUserInfoService.getSysUserInfoById(info.getCreator());
        List<EtTempQuestionInfo> etTempQuestionInfos = etTempQuestionInfoDao.selectEntityList(tempQuestionInfo);
        for (EtTempQuestionInfo tinfo : etTempQuestionInfos) {
            EtSiteQuestionInfo qinfo = new EtSiteQuestionInfo();
            qinfo.setId(ssgjHelper.createSiteQuestionIdService());
            qinfo.setPmId(info.getPmId());
            qinfo.setCId(info.getCId());
            qinfo.setSerialNo(info.getSerialNo());
            qinfo.setSiteName(tinfo.getSiteId());
            qinfo.setProductName(tinfo.getProductId());
            qinfo.setMenuName(tinfo.getMenuName());
            qinfo.setQuestionType(tinfo.getQuestionType());
            qinfo.setQuestionDesc(tinfo.getQuestionDesc());
            qinfo.setOperType(tinfo.getOperType());
            qinfo.setPriority(tinfo.getPriority());
            qinfo.setReasonType(tinfo.getReasonType());
            qinfo.setManuscriptStatus(tinfo.getManuscriptStatus());
            qinfo.setDevUser(tinfo.getDevUser());
            qinfo.setDevUserName(tinfo.getDevUserName());
            qinfo.setIntroducer(tinfo.getIntroducer());
            qinfo.setIntroducerName(tinfo.getIntroducerName());
            qinfo.setIntroducerDate(tinfo.getIntroducerDate());
            qinfo.setLinkman(tinfo.getLinkman());
            qinfo.setMobile(tinfo.getMobile());
            qinfo.setCreateTime(new Timestamp(new Date().getTime()));
            qinfo.setCreator(info.getCreator());
            if(tinfo.getRequirementNo() != null && !StringUtil.isEmptyOrNull(tinfo.getRequirementNo())){
                qinfo.setRequirementNo(tinfo.getRequirementNo());
                qinfo.setPmisStatus(1);
            }else{
                qinfo.setPmisStatus(2);
            }
            qinfo.setCreateNo(user.getUserid());
            qinfo.setIsOperation(0);
            etSiteQuestionInfoDao.insertEntity(qinfo);
        }

    }

}
