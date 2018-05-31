package cn.com.winning.ssgj.web.controller.vue;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.annoation.ILog;
import cn.com.winning.ssgj.base.exception.SSGJException;
import cn.com.winning.ssgj.base.util.DateUtil;
import cn.com.winning.ssgj.base.util.StringUtil;
import cn.com.winning.ssgj.domain.EtSiteQuestionInfo;
import cn.com.winning.ssgj.domain.EtUserInfo;
import cn.com.winning.ssgj.domain.PmisProjectBasicInfo;
import cn.com.winning.ssgj.domain.SysUserInfo;
import cn.com.winning.ssgj.domain.support.Row;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import cn.com.winning.ssgj.ws.client.BizProcessResult;
import cn.com.winning.ssgj.ws.service.PmisWebServiceClient;
import cn.com.winning.ssgj.ws.work.service.PmisWorkingPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.*;

/**
 * @author chenshijie
 * @title 指挥中心安排 PC端
 * @email chensj@winning.com.cm
 * @package cn.com.winning.ssgj.web.controller.vue
 * @date 2018-03-29 16:08
 */
@Controller
@CrossOrigin
@RequestMapping(value = "/vue/siteCenter")
public class EtSiteQuestionInfoController extends BaseController {

    @Autowired
    private PmisWebServiceClient pmisWebServiceClient;
    @Autowired
    private PmisWorkingPaperService pmisWorkingPaperService;

    /**
     * 站点问题初始化显示
     *
     * @param info
     * @param row
     * @return
     */
    @RequestMapping(value = "/list.do")
    @ResponseBody
    public Map<String, Object> listSiteQuestionInfo(EtSiteQuestionInfo info, Row row, String startDate, String endDate) throws ParseException {
        long pmid = info.getPmId();
        String serialNo = info.getSerialNo();
        info.setPmId(null);
        info.setRow(row);
        //前端传输时间处理
        if (!"null".equals(startDate) && !"null".equals(endDate) && !StringUtil.isEmptyOrNull(startDate) && !StringUtil.isEmptyOrNull(endDate)) {
            Map<String, Object> param = info.getMap();
            param.put("startDate", DateUtil.convertDateStringToTimestap(startDate));
            param.put("endDate", DateUtil.convertDateStringToTimestap(endDate));
        }
        //站点问题分页显示
        List<EtSiteQuestionInfo> questionInfoList = super.getFacade().getEtSiteQuestionInfoService().getEtSiteQuestionInfoPaginatedList(info);
        int total = super.getFacade().getEtSiteQuestionInfoService().getEtSiteQuestionInfoCount(info);
        info = new EtSiteQuestionInfo();
        info.setSerialNo(serialNo);
        List<EtSiteQuestionInfo> infoList = super.getFacade().getEtSiteQuestionInfoService().getEtSiteQuestionInfoUserTotal(info);
        //人员信息
        List<String> nameList = new ArrayList<String>();
        List<Integer> numList = new ArrayList<Integer>();
        if (infoList != null && infoList.size() > 0) {
            for (EtSiteQuestionInfo en : infoList) {
                nameList.add((String) en.getMap().get("c_name"));
                numList.add((Integer) en.getMap().get("num"));
            }
        }
        //可分配人员信息
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("total", total);
        result.put("status", Constants.SUCCESS);
        result.put("rows", questionInfoList);
        result.put("data", this.getEtUserInfo(pmid));
        result.put("nameList", nameList);
        result.put("numList", numList);
        result.put("plList", this.getProductLineList());
        result.put("deptList", this.getDepartmentList(Long.valueOf(serialNo), null));
        return result;
    }

    /**
     * 柱状图更新
     *
     * @param info
     * @return
     */
    @RequestMapping(value = "/updateChart.do")
    @ResponseBody
    public Map<String, Object> updateChart(EtSiteQuestionInfo info) {
        List<EtSiteQuestionInfo> infoList = super.getFacade().getEtSiteQuestionInfoService().getEtSiteQuestionInfoUserTotal(info);
        //人员信息
        List<String> nameList = new ArrayList<String>();
        List<Integer> numList = new ArrayList<Integer>();
        if (infoList != null && infoList.size() > 0) {
            for (EtSiteQuestionInfo en : infoList) {
                nameList.add((String) en.getMap().get("c_name"));
                numList.add((Integer) en.getMap().get("num"));
            }
        }
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        result.put("nameList", nameList);
        result.put("numList", numList);
        return result;
    }

    /**
     * 处理方式修改/优先级修改/分配人修改
     *
     * @param info
     * @return
     */
    @RequestMapping(value = "/update.do")
    @ResponseBody
    @ILog
    @Transactional
    public Map<String, Object> updateOperate(EtSiteQuestionInfo info) {
        info.setOperatorTime(new Timestamp(new Date().getTime()));
        super.getFacade().getEtSiteQuestionInfoService().modifyEtSiteQuestionInfo(info);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        return result;
    }

    /**
     * 单条导入PMIS
     *
     * @param info
     * @return
     */
    @RequestMapping(value = "/exportPmisData.do", method = {RequestMethod.POST})
    @ResponseBody
    @ILog
    @Transactional
    public Map<String, Object> exportPmisData(EtSiteQuestionInfo info) {
        info.setOperatorTime(new Timestamp(new Date().getTime()));
        super.getFacade().getEtSiteQuestionInfoService().modifyEtSiteQuestionInfo(info);
        EtSiteQuestionInfo oldInfo = new EtSiteQuestionInfo();
        oldInfo.setId(info.getId());
        oldInfo = super.getFacade().getEtSiteQuestionInfoService().getEtSiteQuestionInfo(oldInfo);
        importData(oldInfo);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        return result;
    }

    /**
     * 批量导入PMIS
     *
     * @param info
     * @param idList
     * @return
     */
    @RequestMapping(value = "/exportBatchPmisData.do")
    @ResponseBody
    public Map<String, Object> exportBatchPmisData(EtSiteQuestionInfo info, Long[] idList) {
        EtSiteQuestionInfo oldInfo = null;
        for (int i = 0; i < idList.length; i++) {
            oldInfo = new EtSiteQuestionInfo();
            oldInfo.setId(idList[i]);
            oldInfo = super.getFacade().getEtSiteQuestionInfoService().getEtSiteQuestionInfo(oldInfo);
            if (oldInfo.getPmisStatus() == 2) {
                oldInfo.setPmId(info.getPmId());
                oldInfo.setBatchNo(info.getBatchNo());
                oldInfo.setQuestionType(info.getQuestionType());
                oldInfo.setReasonType(info.getReasonType());
                oldInfo.setManuscriptStatus(info.getManuscriptStatus());
                oldInfo.setDiffcultLevel(info.getDiffcultLevel());
                oldInfo.setDevUser(info.getDevUser());
                oldInfo.setDevUserName(info.getDevUserName());
                oldInfo.setLinkman(info.getLinkman());
                oldInfo.setMobile(info.getMobile());
                oldInfo.setSolutionResult(info.getSolutionResult());
                oldInfo.setHopeFinishDate(info.getHopeFinishDate());
                oldInfo.setResolveDate(info.getResolveDate());
                oldInfo.setWorkLoad(info.getWorkLoad());
                oldInfo.setUserMessage(info.getUserMessage());
                oldInfo.setIntroducer(info.getIntroducer());
                oldInfo.setIntroducerDate(info.getIntroducerDate());
                oldInfo.setIntroducerName(info.getIntroducerName());
                oldInfo.setOperator(info.getOperator());
                oldInfo.setOperatorTime(new Timestamp(new Date().getTime()));
                SysUserInfo user = new SysUserInfo();
                user.setId(oldInfo.getCreator());
                user = super.getFacade().getSysUserInfoService().getSysUserInfo(user);
                oldInfo.setCreateNo(user.getUserid());
                importData(oldInfo);
            }
        }
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        return result;
    }

    /**
     * 导出工作底稿功能
     *
     * @param info
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/exportExcel.do")
    public HttpServletResponse writeExcel(EtSiteQuestionInfo info, HttpServletResponse response) throws IOException {
        String fileName = "EtSiteQuestion.xls";
        String path = getClass().getClassLoader().getResource("/template").getPath() + fileName;
        super.getFacade().getEtSiteQuestionInfoService().generateEtSiteQuestionInfo(info, path);
        try {
            // path是指欲下载的文件的路径。
            File file = new File(path);
            // 取得文件名。
            String filename = file.getName();
            // 取得文件的后缀名。
            String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();
            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("站点问题汇总.xls", "UTF-8"));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            throw ex;
        }
        return response;
    }

    /**
     * 导入PMIS底稿功能
     *
     * @param info
     */
    private void importData(EtSiteQuestionInfo info) {
        //TODO 生产环境工作底稿导入功能 从返回的额外参数中获取需求编号
        BizProcessResult bizResult = null;
        if(info.getPmisStatus() == 2){
            //使用WS_TEST_URL 为测试环境  WS_URL为生产环境
             bizResult =  pmisWebServiceClient.importWorkDataToPmis(info);
            if(bizResult.getResult() != -1){
                info.setRequirementNo(bizResult.getOutputVariables().get(1).getValue());
            }else{
                throw new SSGJException(bizResult.getMessage(),"PMIS-WS-E0001","错误原因:"+bizResult.getMessage());
            }
        }
        //TODO 测试环境工作底稿导入功能  从返回的额外参数中获取需求编号
//        cn.com.winning.ssgj.ws.work.client.BizProcessResult bizResult = null;
//        if (info.getPmisStatus() == 2) {
//            bizResult = pmisWorkingPaperService.importWorkReport(info);
//            if (bizResult.getResult() != -1) {
//                info.setRequirementNo(bizResult.getOutputVariables().get(1).getValue());
//            } else {
//                throw new SSGJException(bizResult.getMessage(), "PMIS-WS-E0001", "错误原因:" + bizResult.getMessage());
//            }
//        }
        info.setPmisStatus(Constants.STATUS_USE);
        super.getFacade().getEtSiteQuestionInfoService().modifyEtSiteQuestionInfo(info);
    }

    @RequestMapping(value = "/queryWrokreportStatus.do")
    @ResponseBody
    public Map<String, Object> queryWrokreportStatus(EtSiteQuestionInfo info) {
        info = super.getFacade().getEtSiteQuestionInfoService().getEtSiteQuestionInfo(info);
        String[] attrs = new String[2];
        if (info.getPmisStatus() == 1) {
            //TODO 测试环境
            //attrs = pmisWorkingPaperService.queryWorkReport(info.getRequirementNo());
            //TODO 生产环境
            attrs = pmisWebServiceClient.queryReportWorkStatus(info.getRequirementNo());
        }
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        if (!StringUtil.isEmptyOrNull(attrs[1])) {
            info.setManuscriptStatus(Integer.parseInt(attrs[1]));
            super.getFacade().getEtSiteQuestionInfoService().modifyEtSiteQuestionInfo(info);
            result.put("data", attrs[1]);
        }
        return result;
    }



    @RequestMapping(value = "/countInfo.do")
    @ResponseBody
    public Map<String,Object> countInfo(EtSiteQuestionInfo info,String columns){
        Map<String,Object> queryCol = info.getMap();
        convertStringToMap(queryCol,columns);
        List<Map<String,Object>> param = super.getFacade().getEtSiteQuestionInfoService().getEtSiteQuestionCountInfo(info);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("status", Constants.SUCCESS);
        result.put("data",param );
        return result;

    }

    public Map<String,Object> convertStringToMap(Map<String,Object> map,String columns){
        String[] column = columns.split(",");
        for( int i =0 ; i< column.length ;i++){
            int key = Integer.parseInt(column[i]);
            switch (key){
                case 1 : map.put("isDeptName","1"); break;
                case 2 : map.put("isPlName","1"); break;
                case 3 : map.put("isMenu","1"); break;
                case 4 : map.put("isRNO","1"); break;
                case 5 : map.put("isQType","1"); break;
                case 6 : map.put("isQDesc","1"); break;
                case 7 : map.put("isOType","1"); break;
                case 8 : map.put("isPType","1"); break;
                case 9 : map.put("isAName","1"); break;
                case 10 : map.put("isRString","1"); break;
                case 11 : map.put("isMString","1"); break;
                case 12 : map.put("isDString","1"); break;
                case 13 : map.put("isDevUser","1"); break;
                case 14 : map.put("isDevName","1"); break;
                case 15 : map.put("isLinkman","1"); break;
                case 16 : map.put("isMobile","1"); break;
                case 17 : map.put("isintroName","1"); break;
                case 18 : map.put("isintroDate","1"); break;
                case 19 : map.put("isHopeFD","1"); break;
                case 20 : map.put("isResolveDate","1"); break;
                case 21 : map.put("isSoluRes","1"); break;
                case 22 : map.put("isWorkload ","1"); break;
                case 23 : map.put("isUserMessage","1"); break;
                case 24 : map.put("isCreator","1"); break;
                case 25 : map.put("isCreateTime ","1"); break;
                case 26 : map.put("isOperation","1"); break;
            }
        }
        System.out.println(map);
        return  map;

    }
}
