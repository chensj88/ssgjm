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
 * @author chenfeng
 * @Description 接口信息controller
 * @Date 2018年3月29日09:59:48
 */
@CrossOrigin
@Controller
@RequestMapping(value = "/vue/interfaceInfo")
public class EtInterfaceInfoController extends BaseController {

    @Autowired
    private SSGJHelper ssgjHelper;

    /**
     * 获取接口信息集合
     * @param etInterfaceInfo
     * @param row
     * @return
     */
    @RequestMapping(value = "/initData.do")
    @ResponseBody
    public Map<String, Object> list(EtInterfaceInfo etInterfaceInfo, Row row) {
        etInterfaceInfo.setRow(row);
        //根据pmid获取所有接口数据
        List<EtInterfaceInfo> etInterfaceInfos = getFacade().getEtInterfaceInfoService().selectEtInterfaceInfoMergePageList(etInterfaceInfo);
        //根据pmid获取接口数
        Integer total = getFacade().getEtInterfaceInfoService().selectEtInterfaceInfoMergeCount(etInterfaceInfo);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("total", total);
        result.put("status", Constants.SUCCESS);
        result.put("rows", etInterfaceInfos);
        return result;
    }

    /**
     * 添加或者修改项目产品信息
     *
     * @param etInterfaceInfo
     * @return
     */
    @RequestMapping(value = "/addOrModify.do")
    @ResponseBody
    @ILog
    public Map<String, Object> addOrModify(EtInterfaceInfo etInterfaceInfo) {
        //创建临时变量
        EtInterfaceInfo etInterfaceInfoTemp = new EtInterfaceInfo();
        etInterfaceInfoTemp.setId(etInterfaceInfo.getId());
        etInterfaceInfoTemp = super.getFacade().getEtInterfaceInfoService().getEtInterfaceInfo(etInterfaceInfoTemp);
        PmisProjectBasicInfo basicInfo = new PmisProjectBasicInfo();
        basicInfo.setId(etInterfaceInfo.getPmId());
        basicInfo = super.getFacade().getPmisProjectBasicInfoService().getPmisProjectBasicInfo(basicInfo);
        etInterfaceInfo.setSerialNo(basicInfo.getKhxx() + "");
        if (etInterfaceInfoTemp != null) {
            etInterfaceInfo.setOperatorTime(new Timestamp(new Date().getTime()));
            super.getFacade().getEtInterfaceInfoService().modifyEtInterfaceInfo(etInterfaceInfo);
        } else {
            etInterfaceInfo.setId(ssgjHelper.createInterfaceInfoId());
            etInterfaceInfo.setCreator(etInterfaceInfo.getOperator());
            etInterfaceInfo.setCreateTime(new Timestamp(new Date().getTime()));
            etInterfaceInfo.setOperatorTime(new Timestamp(new Date().getTime()));
            super.getFacade().getEtInterfaceInfoService().createEtInterfaceInfo(etInterfaceInfo);
        }
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        return result;

    }

    /**
     * 删除接口信息
     *
     * @param etInterfaceInfo
     * @return
     */
    @RequestMapping(value = "/delete.do")
    @ResponseBody
    @ILog
    public Map<String, Object> delete(EtInterfaceInfo etInterfaceInfo) {
        super.getFacade().getEtInterfaceInfoService().removeEtInterfaceInfo(etInterfaceInfo);
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
}
