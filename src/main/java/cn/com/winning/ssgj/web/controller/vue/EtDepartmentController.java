package cn.com.winning.ssgj.web.controller.vue;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.annoation.ILog;
import cn.com.winning.ssgj.base.helper.SSGJHelper;
import cn.com.winning.ssgj.base.util.*;
import cn.com.winning.ssgj.domain.EtDepartment;
import cn.com.winning.ssgj.domain.EtEasyDataCheck;
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
import java.util.*;

/**
 * @history
 * @add chen.kuai
 * @modify chensj 2018-05-23 添加模糊查询
 */
@Controller
@CrossOrigin
@RequestMapping(value = "/vue/department")
public class EtDepartmentController extends BaseController {
    @Autowired
    private SSGJHelper ssgjHelper;

    /**
     * 查询项目下医院科室信息
     *
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
        int total = super.getFacade().getEtDepartmentService().getEtDepartmentCount(queryDepart);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("total", total);
        result.put("status", Constants.SUCCESS);
        result.put("rows", queryDepartList);
        return result;

    }

    /**
     * 添加或者修改用户信息
     *
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
            List<EtDepartment> deptTypeList = super.getFacade().getEtDepartmentService().getEtDepartmentList(DeptType);
            if (deptTypeList.size() > 0) {
                depart.setDeptType(deptTypeList.get(0).getDeptType());
            } else {
                depart.setDeptType(String.valueOf(ssgjHelper.createSysHospitalDeptTypeIdService()));
            }

            if (uniqueList == null || uniqueList.size() == 0) {
                depart.setSerialName(PinyinTools.cn2GetFirstSpell(depart.getDeptName()).toUpperCase());
                super.getFacade().getEtDepartmentService().createEtDepartment(depart);
            } else {
                result.put("status", "repeat");
            }
        } else {
            EtDepartment unique_old = new EtDepartment();
            unique_old.setIsDel(1);
            unique_old.setSerialNo(depart.getSerialNo());
            unique_old.setTypeName(depart.getTypeName());
            unique_old.setDeptName(depart.getDeptName());
            unique_old.getMap().put("not_id", depart.getId());
            unique_old = super.getFacade().getEtDepartmentService().getEtDepartment(unique_old);
            if (unique_old == null) {
                depart.setSerialName(PinyinTools.cn2GetFirstSpell(depart.getDeptName()).toUpperCase());
                super.getFacade().getEtDepartmentService().modifyEtDepartment(depart);
            } else {
                result.put("status", "repeat");
            }
        }
        //result.put("status", Constants.SUCCESS);
        return result;
    }

    /**
     * 导出医院科室信息
     *
     * @param department
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/exportExcel.do")
    public void wiriteExcel(EtDepartment department, HttpServletResponse response) throws IOException {
        department.setIsDel(Constants.PMIS_STATUS_USE);
        String fileName = "医院科室信息_" + DateUtil.format(DateUtil.PATTERN_14) + ".xls";
        super.getFacade().getEtDepartmentService().generateDepartInfo(department, response, fileName);
    }


    /**
     * 上传医院用户信息Excel
     *
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
                super.getFacade().getEtDepartmentService().createEtDepartmentExcel(departList, department);
                newFile.delete();
                result.put("status", "success");
            } catch (Exception e) {
                e.printStackTrace();
                result.put("status", "error");
                result.put("msg", "上传文件失败,原因是：" + e.getMessage());
            }
        } else {
            result.put("status", "error");
            result.put("msg", "上传文件失败,原因是：上传文件为空");
        }
        return result;
    }

    /**
     * 删除医院科室信息
     * 需要判断科室是否在站点问题和站点问题中是否使用
     * 使用 则不允许删除
     * 反之 则允许删除
     *
     * @param department
     * @return
     */
    @RequestMapping(value = "/delete.do")
    @ResponseBody
    @Transactional
    public Map<String, Object> deleteDept(EtDepartment department) {
        String msg = getFacade().getEtDepartmentService().checkEtDepartmentIsUse(department);
        Map<String, Object> result = new HashMap<String, Object>();
        if (StringUtil.isEmptyOrNull(msg)) {
            super.getFacade().getEtDepartmentService().removeEtDepartment(department);
            result.put("status", Constants.SUCCESS);
        } else {
            result.put("status", Constants.FAILD);
            result.put("msg", msg);
        }
        return result;
    }


    /**
     * 批量删除
     *
     * @param idsStr
     * @return
     */
    @RequestMapping(value = "/bantchDelete.do")
    @ResponseBody
    @Transactional
    public Map<String, Object> bantchDelete(String idsStr) {
        if (StringUtil.isEmptyOrNull(idsStr)) {
            resultMap.put("status", Constants.FAILD);
            return resultMap;
        }
        String[] split = idsStr.split(",");
        List<String> ids = Arrays.asList(split);
        EtDepartment etDepartment = new EtDepartment();
        etDepartment.getMap().put("pks", ids);
        getFacade().getEtDepartmentService().removeEtDepartment(etDepartment);
        resultMap.put("status", Constants.SUCCESS);
        return resultMap;
    }


}
