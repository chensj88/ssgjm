package cn.com.winning.ssgj.web.controller.vue;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.annoation.ILog;
import cn.com.winning.ssgj.base.helper.SSGJHelper;
import cn.com.winning.ssgj.base.util.*;
import cn.com.winning.ssgj.domain.*;
import cn.com.winning.ssgj.domain.support.Row;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import com.jcraft.jsch.ChannelSftp;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author chenfeng
 * @Description 接口信息controller
 * @Date 2018年3月29日09:59:48
 */
@CrossOrigin
@Controller
@RequestMapping(value = "/vue/sysFloors")
public class SysFloorsVueController extends BaseController {

    @Autowired
    private SSGJHelper ssgjHelper;

    /**
     * 获取楼层信息集合
     *
     * @param sysFloors
     * @param row
     * @return
     */
    @RequestMapping(value = "/list.do")
    @ResponseBody
    public Map<String, Object> list(SysFloors sysFloors, Row row) throws ParseException {
        sysFloors.setRow(row);
        List<SysFloors> sysFloorsPaginatedList = getFacade().getSysFloorsService().getSysFloorsPaginatedList(sysFloors);
        Integer total = getFacade().getSysFloorsService().getSysFloorsCount(sysFloors);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("total", total);
        result.put("status", Constants.SUCCESS);
        result.put("rows", sysFloorsPaginatedList);
        return result;
    }

    /**
     * 添加或修改
     *
     * @param sysFloors
     * @return
     */
    @RequestMapping(value = "/addOrModify.do")
    @ResponseBody
    @ILog
    @Transactional
    public Map<String, Object> addOrModify(SysFloors sysFloors) {
        Map<String, Object> result = new HashMap<String, Object>();
        Long id = sysFloors.getId();
        //创建临时变量
        SysFloors temp = new SysFloors();
        temp.setSerialNo(sysFloors.getSerialNo());
        temp.setFloorName(sysFloors.getFloorName());
        temp = super.getFacade().getSysFloorsService().getSysFloors(temp);
        if (id != 0) {
            //编辑
            if (temp != null && temp.getId() != id) {
                //楼层重复
                result.put("status", Constants.FAILD);
                return result;
            }
            super.getFacade().getSysFloorsService().modifySysFloors(sysFloors);
        } else {
            //新增
            if (temp != null) {
                //楼层重复
                result.put("status", Constants.FAILD);
                return result;
            }
            //根据客户查询客户
            Long serialNo = sysFloors.getSerialNo();
            PmisCustomerInformation pmisCustomerInformationTemp = new PmisCustomerInformation();
            pmisCustomerInformationTemp.setId(serialNo);
            PmisCustomerInformation pmisCustomerInformation = getFacade().getPmisCustomerInformationService().getPmisCustomerInformation(pmisCustomerInformationTemp);
            sysFloors.setId(ssgjHelper.createFloorQuestionIdService());
            sysFloors.setIsDel(1);
            sysFloors.setFloorCode(sysFloors.getId().toString());
            sysFloors.setSerialName(pmisCustomerInformation == null ? "" : pmisCustomerInformation.getName());
            super.getFacade().getSysFloorsService().createSysFloors(sysFloors);
        }
        result.put("status", Constants.SUCCESS);
        return result;

    }


    /**
     * 删除接口信息
     *
     * @param sysFloors
     * @return
     */
    @RequestMapping(value = "/delete.do")
    @ResponseBody
    @ILog
    @Transactional
    public Map<String, Object> delete(SysFloors sysFloors) {
        super.getFacade().getSysFloorsService().removeSysFloors(sysFloors);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        return result;
    }


    /**
     * @param request
     * @param file
     * @return
     * @throws IOException
     * @description 文件上传
     */
    @RequestMapping(value = "/upload.do")
    @ResponseBody
    public Map<String, Object> upload(HttpServletRequest request, MultipartFile file, SysFloors param) throws IOException {
        //根据客户查询客户
        Long serialNo = param.getSerialNo();
        PmisCustomerInformation pmisCustomerInformationTemp = new PmisCustomerInformation();
        pmisCustomerInformationTemp.setId(serialNo);
        PmisCustomerInformation pmisCustomerInformation = getFacade().getPmisCustomerInformationService().getPmisCustomerInformation(pmisCustomerInformationTemp);
        String serialName = pmisCustomerInformation == null ? "" : pmisCustomerInformation.getName();
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
                List<List<Object>> sysFloorsList = ExcelUtil.importExcel(newFile.getPath());
                for (List<Object> temp : sysFloorsList) {
                    String floorName = temp.get(0) == null ? null : temp.get(0).toString();
                    if (StringUtil.isEmptyOrNull(floorName)) {
                        continue;
                    }
                    //创建临时变量查询楼层是否存在
                    SysFloors floorTemp = new SysFloors();
                    floorTemp.setSerialNo(serialNo);
                    floorTemp.setFloorName(floorName);
                    floorTemp = super.getFacade().getSysFloorsService().getSysFloors(floorTemp);
                    if (floorTemp != null) {
                        //楼层重复,继续录入下条数据
                        continue;
                    }
                    SysFloors sysFloorsTemp = new SysFloors();
                    sysFloorsTemp.setId(ssgjHelper.createFloorQuestionIdService());
                    sysFloorsTemp.setFloorCode(sysFloorsTemp.getId().toString());
                    sysFloorsTemp.setSerialName(serialName);
                    sysFloorsTemp.setFloorName(floorName);
                    sysFloorsTemp.setIsDel(1);
                    sysFloorsTemp.setSerialNo(serialNo);
                    getFacade().getSysFloorsService().createSysFloors(sysFloorsTemp);
                }
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

}
