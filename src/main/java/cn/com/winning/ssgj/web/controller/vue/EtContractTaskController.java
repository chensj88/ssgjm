package cn.com.winning.ssgj.web.controller.vue;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.annoation.ILog;
import cn.com.winning.ssgj.base.helper.SSGJHelper;
import cn.com.winning.ssgj.base.util.PinyinTools;
import cn.com.winning.ssgj.base.util.StringUtil;
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
import java.util.*;

/**
 * @author chenshijie
 * @title 系统清单Controller
 * @email chensj@winning.com.cm
 * @package cn.com.winning.ssgj.web.controller.vue
 * @date 2018-03-26 8:52
 */
@CrossOrigin
@Controller
@RequestMapping(value = "/vue/contractTask")
public class EtContractTaskController extends BaseController {

    @Autowired
    private SSGJHelper ssgjHelper;

    /**
     * 根据选择的产品序号来初始化产品
     *
     * @param task
     * @param idList
     * @return
     */
    @RequestMapping(value = "/initData.do")
    @ResponseBody
    public Map<String, Object> initProjectProduct(EtContractTask task, String idList) {
        List<String> valueList = new ArrayList<>();
        for (int i = 0; i < idList.split(",").length; i++) {
            valueList.add(idList.split(",")[i]);
        }
        //去除已经的值,根据客户号查询已经生成的产品ID并去除，减少数据库查询
        List<SysDictInfo> dicts = super.getFacade().getSysDictInfoService().getSysDictInfoListByValue(valueList, task.getSerialNo());
        for (SysDictInfo info : dicts) {
            EtContractTask t = new EtContractTask();
            t.setcId(task.getcId());
            t.setPmId(task.getPmId());
            t.setSerialNo(task.getSerialNo());
            t.setSourceId(Long.parseLong(info.getDictSort()));
            t = super.getFacade().getEtContractTaskService().getEtContractTask(t);
            if (t == null) {
                t = new EtContractTask();
                t.setId(ssgjHelper.createEtContractTaskIdService()); //ID
                t.setcId(task.getcId()); //合同ID
                t.setPmId(task.getPmId()); //项目ID
                t.setSerialNo(task.getSerialNo()); //客户ID
                t.setZxtmc(info.getDictLabel()); //字典显示值
                t.setCpzxt(Long.parseLong(info.getDictSort())); //主系统ID
                t.setMx(info.getProductType());//产品大类
                t.setSourceId(Long.parseLong(info.getDictSort())); //来源序号或ID
                t.setBz(info.getPyCode()); //备注放置拼音码
                t.setCreator(task.getCreator()); //创建人
                t.setAllocateUser(task.getCreator()); //默认任务分配给当前创建人
                t.setCreateTime(new Timestamp(new Date().getTime())); //创建时间
                getFacade().getEtContractTaskService().createEtContractTask(t);
            }
        }
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        return result;
    }

    /**
     * 查询项目产品信息
     *
     * @param task
     * @param row
     * @return
     */
    @RequestMapping(value = "/list.do")
    @ResponseBody
    public Map<String, Object> listProductOfProject(EtContractTask task, Row row) {
        task.setRow(row);
        List<EtContractTask> taskList = super.getFacade().getEtContractTaskService().getEtContractTaskPaginatedList(task);
        int total = super.getFacade().getEtContractTaskService().getEtContractTaskCount(task);
        //根据pmid获取项目进程
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("total", total);
        result.put("status", Constants.SUCCESS);
        result.put("rows", taskList);
        return result;
    }

    @RequestMapping(value = "/process.do")
    @ResponseBody
    public Map<String, Object> process(EtContractTask task) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        result.put("process", this.getProcessManager(task.getPmId()));
        return result;
    }

    /**
     * 查询产品信息
     *
     * @param info
     * @param serialNo 客户号
     * @return
     */
    @RequestMapping(value = "/queryProduct.do")
    @ResponseBody
    public Map<String, Object> queryProduct(SysDictInfo info, String serialNo) {
        info.setDictCode(Constants.DictInfo.PRODUCT_NAME);
        info.getMap().put("serialNo", serialNo);
        List<SysDictInfo> productInfos = super.getFacade().getSysDictInfoService().getSysDictInfoListBySelectKey(info);
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
    public Map<String, Object> addOrModifyProduct(EtContractTask task) {
        EtContractTask oldTask = new EtContractTask();
        oldTask.setId(task.getId());
        oldTask = super.getFacade().getEtContractTaskService().getEtContractTask(oldTask);
        if (oldTask != null) {
            task.setOperatorTime(new Timestamp(new Date().getTime()));
            task.setBz(PinyinTools.cn2FirstSpell(task.getZxtmc()));
            super.getFacade().getEtContractTaskService().modifyEtContractTask(task);
        } else {
            task.setId(ssgjHelper.createEtContractTaskIdService());
            task.setCreator(task.getOperator());
            task.setSourceId(task.getCpzxt());
            task.setBz(PinyinTools.cn2FirstSpell(task.getZxtmc()));
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
     * 需要判断系统是否在硬件清单、站点问题和站点问题中是否使用
     * 使用 则不允许删除
     * 反之 则允许删除
     *
     * @param task
     * @return
     */
    @RequestMapping(value = "/deleteProduct.do")
    @ResponseBody
    public Map<String, Object> deleteProduct(EtContractTask task) {
        String msg = super.getFacade().getEtContractTaskService().checkEtContractTaskIsUse(task);
        Map<String, Object> result = new HashMap<String, Object>();
        if (StringUtil.isEmptyOrNull(msg)) {
            super.getFacade().getEtContractTaskService().removeEtContractTask(task);
            result.put("status", Constants.SUCCESS);
        } else {
            result.put("msg", msg);
            result.put("status", Constants.FAILD);
        }
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
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("系统清单.xls", "UTF-8"));
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
