package cn.com.winning.ssgj.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;


import cn.com.winning.ssgj.base.util.ExcelUtil;
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
            dataMap.put("deptName",qinfo.getSiteName());
            dataMap.put("sysName",qinfo.getProductName());
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

}
