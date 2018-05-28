package cn.com.winning.ssgj.web.controller.vue;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.annoation.ILog;
import cn.com.winning.ssgj.base.helper.SSGJHelper;
import cn.com.winning.ssgj.domain.*;
import cn.com.winning.ssgj.domain.support.Row;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenshijie
 * @title
 * @email chensj@winning.com.cm
 * @package cn.com.winning.ssgj.web.controller.vue
 * @date 2018-03-26 8:52
 */
@CrossOrigin
@Controller
@RequestMapping(value = "/vue/projectProduct")
public class EtContractProjectController extends BaseController {

    @Autowired
    private SSGJHelper ssgjHelper;

    /**
     * 查询项目产品信息
     * @param task
     * @param row
     * @return
     */
    @RequestMapping(value = "/initData.do")
    @ResponseBody
    public Map<String, Object>  listProductOfProject(EtContractTask task, Row row) {
      /*  super.getFacade().getCommonQueryService().generateEtContractTaskFromPmisContractProductInfo(task.getPmId());*/
        initData(task.getPmId());
        task.setRow(row);
        List<EtContractTask> taskList = super.getFacade().getEtContractTaskService().getEtContractTaskPaginatedList(task);
        int total = super.getFacade().getEtContractTaskService().getEtContractTaskCount(task);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("total", total);
        result.put("status", Constants.SUCCESS);
        result.put("rows", taskList);
        return result;
    }

    private  synchronized void initData(Long pmId){
        super.getFacade().getCommonQueryService().generateEtContractTaskFromPmisContractProductInfo(pmId);
    }

    /**
     * 查询产品信息
     *
     * @param productInfo
     * @return
     */
    @RequestMapping(value = "/queryProduct.do")
    @ResponseBody
    public Map<String, Object> queryProduct(PmisProductInfo productInfo) {
        Row row = new Row(0, 10);
        productInfo.setRow(row);
        productInfo.setZt(Constants.PMIS_STATUS_USE);
        productInfo.setCplb("1");
        List<PmisProductInfo> productInfos = super.getFacade().getPmisProductInfoService().getPmisProductInfoPaginatedListByCodeAndName(productInfo);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        result.put("data", productInfos);
        return result;
    }

    /**
     * 添加或者修改项目产品信息
     *
     * @param task
     * @return
     */
    @RequestMapping(value = "/addOrModifyProduct.do")
    @ResponseBody
    @ILog
    public Map<String, Object> addOrModifyProduct(EtContractTask task) {
        EtContractTask oldTask = new EtContractTask();
        oldTask.setId(task.getId());
        oldTask = super.getFacade().getEtContractTaskService().getEtContractTask(oldTask);
        PmisProjectBasicInfo basicInfo = new PmisProjectBasicInfo();
        basicInfo.setId(task.getPmId());
        basicInfo = super.getFacade().getPmisProjectBasicInfoService().getPmisProjectBasicInfo(basicInfo);
        task.setSerialNo(basicInfo.getKhxx() + "");
        if (oldTask != null) {
            task.setOperatorTime(new Timestamp(new Date().getTime()));
            super.getFacade().getEtContractTaskService().modifyEtContractTask(task);
        } else {
            task.setId(ssgjHelper.createEtContractTaskIdService());
            task.setCreator(task.getOperator());
            task.setCreateTime(new Timestamp(new Date().getTime()));
            task.setOperatorTime(new Timestamp(new Date().getTime()));
            super.getFacade().getEtContractTaskService().createEtContractTask(task);
        }
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        return result;

    }

    /**
     * 删除项目产品信息
     *
     * @param task
     * @return
     */
    @RequestMapping(value = "/deleteProduct.do")
    @ResponseBody
    @ILog
    public Map<String, Object> deleteProduct(EtContractTask task) {
        super.getFacade().getEtContractTaskService().removeEtContractTask(task);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        return result;
    }

    /**
     * 导出Excel
     *
     * @param task
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/exportExcel.do")
    @ILog
    public HttpServletResponse wiriteExcel(EtContractTask task, HttpServletResponse response) throws IOException {
        String fileName = "ContractTask.xls";
        String path = getClass().getClassLoader().getResource("/template").getPath() + fileName;
        super.getFacade().getEtContractTaskService().generateEtContractTask(task, path);
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
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("合同产品清单.xls", "UTF-8"));
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

    @RequestMapping(value = "/confirm.do")
    @ResponseBody
    @ILog
    public Map<String, Object> confirmTask(EtProcessManager etProcessManager) {
        EtProcessManager temp = new EtProcessManager();
        temp.setPmId(etProcessManager.getPmId());
        temp = super.getFacade().getEtProcessManagerService().getEtProcessManager(temp);
        temp.setOperator(etProcessManager.getOperator());
        temp.setOperatorTime(new Timestamp(new Date().getTime()));
        temp.setIsPmScope(etProcessManager.getIsPmScope());
        temp.setIsEtPlan(etProcessManager.getIsEtPlan());
        super.getFacade().getEtProcessManagerService().modifyEtProcessManager(temp);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        return result;

    }
}
