package cn.com.winning.ssgj.web.controller.vue;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.annoation.ILog;
import cn.com.winning.ssgj.base.exception.SSGJException;
import cn.com.winning.ssgj.base.util.DateUtil;
import cn.com.winning.ssgj.base.util.ExcelUtil;
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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
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
     * @param info 前端传输的数据
     * @param row 分页数据
     * @param startDate 查询开始日期
     * @param endDate 查询结束日期
     * @param paramName echarts查询参数
     * @return
     */
    @RequestMapping(value = "/list.do")
    @ResponseBody
    public Map<String, Object> listSiteQuestionInfo(EtSiteQuestionInfo info, Row row, String startDate, String endDate,String paramName) throws ParseException {
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
        if ( !StringUtil.isEmptyOrNull(paramName) && !StringUtil.isEmptyOrNull(paramName)) {
            if("未分配".equals(paramName)){
                Map<String, Object> param = info.getMap();
                param.put("notAllocate", true);
             }else{
                EtUserInfo userInfo = new EtUserInfo();
                userInfo.setSerialNo(serialNo);
                userInfo.setCName(paramName);
                userInfo = super.getFacade().getEtUserInfoService().getEtUserInfo(userInfo);
                info.setAllocateUser(userInfo.getUserId());
            }
        }
        //站点问题分页显示
        List<EtSiteQuestionInfo> questionInfoList = super.getFacade().getEtSiteQuestionInfoService().getEtSiteQuestionInfoPaginatedList(info);
        int total = super.getFacade().getEtSiteQuestionInfoService().getEtSiteQuestionInfoCount(info);
        info = new EtSiteQuestionInfo();
        info.setSerialNo(serialNo);
        List<EtSiteQuestionInfo> infoList = super.getFacade().getEtSiteQuestionInfoService().getEtSiteQuestionInfoTotalCountByUser(info);
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
        //产品信息更
        result.put("plList", this.getProductDictInfo(info.getSerialNo()));
        /*result.put("plList", this.getProductLineList());*/
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
        List<EtSiteQuestionInfo> infoList = super.getFacade().getEtSiteQuestionInfoService().getEtSiteQuestionInfoTotalCountByUser(info);
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
    @Transactional
    public Map<String, Object> updateOperate(EtSiteQuestionInfo info) {
        info.setOperatorTime(new Timestamp(new Date().getTime()));
        super.getFacade().getEtSiteQuestionInfoService().modifyEtSiteQuestionInfo(info);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        return result;
    }

    /**
     * 删除问题
     * @param info
     * @return
     */
    @RequestMapping(value = "/delete.do")
    @ResponseBody
    @Transactional
    public Map<String, Object> deleteSiteQuestion(EtSiteQuestionInfo info) {
        super.getFacade().getEtSiteQuestionInfoService().removeEtSiteQuestionInfo(info);
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
        SysUserInfo user = new SysUserInfo();
        user.setId(info.getOperator());
        user = super.getFacade().getSysUserInfoService().getSysUserInfo(user);
        for (int i = 0; i < idList.length; i++) {
            oldInfo = new EtSiteQuestionInfo();
            oldInfo.setId(idList[i]);
            oldInfo = super.getFacade().getEtSiteQuestionInfoService().getEtSiteQuestionInfo(oldInfo);
            if (oldInfo.getPmisStatus() == 2) {
                oldInfo.setPmId(info.getPmId()); //项目ID
                oldInfo.setBatchNo(info.getBatchNo()); //批次
                oldInfo.setQuestionType(info.getQuestionType()); //问题类别
                oldInfo.setReasonType(info.getReasonType());//原因分类
                oldInfo.setManuscriptStatus(info.getManuscriptStatus()); //底稿状态
                oldInfo.setDiffcultLevel(info.getDiffcultLevel()); //难度级别
                oldInfo.setDevUser(user.getUserid()); //工程师工号
                oldInfo.setDevUserName(user.getYhmc()); //公司接收人
                oldInfo.setLinkman(info.getLinkman()); //联系人
                oldInfo.setMobile(info.getMobile()); //联系电话
                oldInfo.setSolutionResult(info.getSolutionResult()); //解决结果
                oldInfo.setHopeFinishDate(info.getHopeFinishDate()); //期望完成时间
                oldInfo.setResolveDate(info.getResolveDate()); //解决日期
                oldInfo.setWorkLoad(info.getWorkLoad()); //工作量
                oldInfo.setUserMessage(info.getUserMessage()); //用户意见
                oldInfo.setOperType(oldInfo.getOperType() == null ? 3 : oldInfo.getOperType()); //处理方式
                oldInfo.setPriority(oldInfo.getPriority() == null ? 4: oldInfo.getPriority()); //优先级 存在则为原先，反之为4 D级(暂缓)
                oldInfo.setOperator(info.getOperator());
                oldInfo.setOperatorTime(new Timestamp(new Date().getTime()));
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

    @RequestMapping(value = "/upload.do")
    @ResponseBody
    public Map<String, Object> importDataFromExcel(HttpServletRequest request,EtSiteQuestionInfo info,
                                                   MultipartFile file) throws IOException {
        Map<String, Object> result = new HashMap<String, Object>();
        //如果文件不为空，写入上传路径
        if (!file.isEmpty()) {
            //上传文件路径
            String path = request.getServletContext().getRealPath("/temp/");
            //上传文件名
            String filename = file.getOriginalFilename();
            File filepath = new File(path, filename);
            //判断路径是否存在，如果不存在就创建一个
            if (!filepath.getParentFile().exists()) {
                filepath.getParentFile().mkdirs();
            }
            //将上传文件保存到一个目标文件当中
            File newFile = new File(path + File.separator + filename);
            if (newFile.exists()) {
                newFile.delete();
            }
            file.transferTo(newFile);

            try {
                List<List<Object>> questionList = ExcelUtil.importExcel(newFile.getPath());
                super.getFacade().getEtSiteQuestionInfoService().createEtSiteQuestionInfo(questionList,info);
                newFile.delete();
                result.put("status", "success");
            } catch (Exception e) {
                e.printStackTrace();
                result.put("status", "error");
                result.put("msg", "上传文件失败,原因是："+e.getMessage());
            }
        } else {
            result.put("status", "error");
            result.put("msg", "上传文件失败,原因是：上传文件为空");
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
        result.put("columns",columns);
        return result;

    }

    public Map<String,Object> convertStringToMap(Map<String,Object> map,String columns){
        String[] column = columns.split(",");
        for( int i =0 ; i< column.length ;i++){
            int key = Integer.parseInt(column[i]);
            switch (key){
                case 1 : map.put("isQ1","1"); break;
                case 2 : map.put("isQ2","1"); break;
                case 3 : map.put("isQ3","1"); break;
                case 4 : map.put("isQ4","1"); break;
                case 5 : map.put("isQ5","1"); break;
                case 6 : map.put("isQ6","1"); break;
                case 7 : map.put("isQ7","1"); break;
                case 8 : map.put("isQ8","1"); break;
                case 9 : map.put("isQ9","1"); break;
                case 10 : map.put("isQ10","1"); break;
                case 11 : map.put("isQ11","1"); break;
                case 12 : map.put("isQ12","1"); break;
                case 13 : map.put("isQ13","1"); break;
                case 14 : map.put("isQ14","1"); break;
                case 15 : map.put("isQ15","1"); break;
                case 16 : map.put("isQ16","1"); break;
                case 17 : map.put("isQ17","1"); break;
                case 18 : map.put("isQ18","1"); break;
                case 19 : map.put("isQ19","1"); break;
                case 20 : map.put("isQ20","1"); break;
                case 21 : map.put("isQ21","1"); break;
                case 22 : map.put("isQ22","1"); break;
                case 23 : map.put("isQ23","1"); break;
                case 24 : map.put("isQ24","1"); break;
                case 25 : map.put("isQ25","1"); break;
                case 26 : map.put("isQ26","1"); break;
            }
        }
        System.out.println(map);
        return  map;

    }
}
