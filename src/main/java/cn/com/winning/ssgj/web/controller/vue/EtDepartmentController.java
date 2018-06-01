package cn.com.winning.ssgj.web.controller.vue;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.annoation.ILog;
import cn.com.winning.ssgj.base.helper.SSGJHelper;
import cn.com.winning.ssgj.base.util.ExcelUtil;
import cn.com.winning.ssgj.base.util.MD5;
import cn.com.winning.ssgj.domain.EtDepartment;
import cn.com.winning.ssgj.domain.SysUserInfo;
import cn.com.winning.ssgj.domain.support.Row;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @history
 *  @add chen.kuai
 *  @modify  chensj 2018-05-23 添加模糊查询
 */
@Controller
@CrossOrigin
@RequestMapping(value = "/vue/department")
public class EtDepartmentController extends BaseController {
    @Autowired
    private SSGJHelper ssgjHelper;

    /**
     * 查询项目下医院科室信息
     * @param queryDepart
     * @param row
     * @return
     */
    @RequestMapping(value = "/list.do")
    @ResponseBody
    public Map<String, Object> queryHospitalUserInfo(EtDepartment queryDepart, Row row) {
        queryDepart.setRow(row);
        queryDepart.setIsDel(Constants.PMIS_STATUS_USE);
        List<EtDepartment> queryDepartList = super.getFacade().getEtDepartmentService().getEtDepartmentPaginatedList(queryDepart);
        int total =super.getFacade().getEtDepartmentService().getEtDepartmentCount(queryDepart);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("total", total);
        result.put("status", Constants.SUCCESS);
        result.put("rows", queryDepartList);
        return result;

    }

    /**
     * 添加或者修改用户信息
     * @param depart
     * @return
     */
    @RequestMapping(value = "/addOrModify.do")
    @ResponseBody
    @Transactional
    public Map<String, Object> addOrModify(EtDepartment depart) {
        Map<String, Object> result = new HashMap<String, Object>();
        //类别+科室确定唯一性
        EtDepartment unique = new EtDepartment();
        unique.setDeptName(depart.getDeptName());
        unique.setTypeName(depart.getTypeName());
        unique.setSerialNo(depart.getSerialNo());
        unique.setIsDel(1);
        List<EtDepartment> uniqueList = new ArrayList<EtDepartment>();
        if (depart.getId() == 0L) {
            uniqueList = super.getFacade().getEtDepartmentService().getEtDepartmentList(unique);
            //判断分类是否存在
            EtDepartment DeptType = new EtDepartment();
            DeptType.setTypeName(depart.getTypeName());
            DeptType.setSerialNo(depart.getSerialNo());
            DeptType.setIsDel(1);
            depart.setId(ssgjHelper.createSysHospitalDeptIdService());
            depart.setIsDel(1);
            depart.setDeptCode(String.valueOf(depart.getId()));
            List<EtDepartment> deptTypeList= super.getFacade().getEtDepartmentService().getEtDepartmentList(DeptType);
            if(deptTypeList.size()>0){
                depart.setDeptType(deptTypeList.get(0).getDeptType());
            }else{
                depart.setDeptType(String.valueOf(ssgjHelper.createSysHospitalDeptTypeIdService()));
            }

            if(uniqueList==null||uniqueList.size()==0){
                super.getFacade().getEtDepartmentService().createEtDepartment(depart);
            }else{
                result.put("status", "repeat");
            }
        } else {
            EtDepartment unique_old = new EtDepartment();
            unique_old.setIsDel(1);
            unique_old.setSerialNo(depart.getSerialNo());
            unique_old.setTypeName(depart.getTypeName());
            unique_old.setDeptName(depart.getDeptName());
            unique_old.getMap().put("not_id",depart.getId());
            unique_old = super.getFacade().getEtDepartmentService().getEtDepartment(unique_old);
            if(unique_old==null){
                super.getFacade().getEtDepartmentService().modifyEtDepartment(depart);
            }else{
                result.put("status", "repeat");
            }
        }
        //result.put("status", Constants.SUCCESS);
        return result;
    }

    /**
     * 导出医院科室信息
     * @param department
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/exportExcel.do")
    public HttpServletResponse wiriteExcel(EtDepartment department, HttpServletResponse response) throws IOException {
        department.setIsDel(Constants.PMIS_STATUS_USE);
        String fileName = "department.xls";
        String path = getClass().getClassLoader().getResource("/template").getPath() + fileName;
        super.getFacade().getEtDepartmentService().generateDepartInfo(department,path);
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
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("医院科室信息.xls","UTF-8"));
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
     * 上传医院用户信息Excel
     * @param request
     * @param department
     * @param file
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/upload.do")
    @ResponseBody
    @Transactional
    public Map<String, Object> uploadDept(HttpServletRequest request, EtDepartment department,
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
                List<List<Object>> departList = ExcelUtil.importExcel(newFile.getPath());
                super.getFacade().getEtDepartmentService().createEtDepartmentExcel(departList,department);
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

    /**
     * 删除医院科室信息
     * @param department
     * @return
     */
    @RequestMapping(value = "/delete.do")
    @ResponseBody
    @Transactional
    public Map<String, Object> deleteDept(EtDepartment department){
        department.setIsDel(0);
        super.getFacade().getEtDepartmentService().modifyEtDepartment(department);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("status", Constants.SUCCESS);
        return result;
    }


}
