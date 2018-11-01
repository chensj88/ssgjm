package cn.com.winning.ssgj.web.controller.vue;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.annoation.ILog;
import cn.com.winning.ssgj.base.helper.SSGJHelper;
import cn.com.winning.ssgj.base.util.*;
import cn.com.winning.ssgj.domain.*;
import cn.com.winning.ssgj.domain.support.Row;
import cn.com.winning.ssgj.domain.support.UrlContent;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.*;

/**
 * 工作站点安装信息
 *
 * @author 陈蒯
 * @date 2018-4-9 15:54:03
 **/
@CrossOrigin
@Controller
@RequestMapping(value = "/vue/siteInstall")
public class EtSiteInstallController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(EtSiteInstallController.class);
    @Autowired
    private SSGJHelper ssgjHelper;

    private static int port = Integer.valueOf(FtpPropertiesLoader.getProperty("ftp.port")).intValue();

    /**
         * 查询站点信息
         * @param row
         * @return
         */
        @RequestMapping(value = "/list.do")
        @ResponseBody
        public Map<String,Object> siteInstallList(EtSiteInstall info, Long userId,Row row) {
            Map<String,Object> result = new HashMap<String,Object>();
            info.setRow(row);
            info.setStatus(1);//有效的
            List<EtSiteInstall> installList = super.getFacade().getEtSiteInstallService().getEtSiteInstallListWithInfo(info);
            if(installList.size() > 0){
                for (int i=0;i<installList.size();i++){
                    if(StringUtils.isNotBlank(installList.get(i).getPdId())){
                        String str[]=installList.get(i).getPdId().split(",");
                        int[] pppId=new int[str.length];
                        for(int j=0;j<str.length;j++){
                            String jj =str[j];
                            int jjj = Integer.parseInt(jj);
                            pppId[j]=jjj;
                        }
                        installList.get(i).setPppId(pppId);
                    }else{
                        int[] pppId=new int[0];
                        installList.get(i).setPppId(pppId);
                    }
                    //硬件
                    if(StringUtils.isNotBlank(installList.get(i).getHwId())){
                        String str[]=installList.get(i).getHwId().split(",");
                        int[] hhhId=new int[str.length];
                        for(int j=0;j<str.length;j++){
                            String jj =str[j];
                            int jjj = Integer.parseInt(jj);
                            hhhId[j]=jjj;
                        }
                        installList.get(i).setHhhId(hhhId);
                    }else{
                        int[] hhhId=new int[0];
                        installList.get(i).setHhhId(hhhId);
                    }
                }
            }

            //所需要的 软件
            result.put("productLineList", super.getProductDictInfo(info.getSerialNo()));
            //所需要的 硬件
            result.put("hardList",super.getHardWareList(info.getPmId()));
            result.put("userList",super.getEtUserInfo(info.getPmId()));
            result.put("total", super.getFacade().getEtSiteInstallService().getEtSiteInstallCount(info));
            result.put("status", Constants.SUCCESS);
            result.put("process",getProcessManager(info.getPmId()));
            result.put("rows", installList);
            return result;
        }

        /**
         * 查询产品条线系统
         * @return
         */
        @RequestMapping(value = "/addDept.do")
        @ResponseBody
        public Map<String,Object> siteInstallAddDept(EtSoftHardware info,Long serialNo,String deptType) {
            Map<String,Object> result = new HashMap<String,Object>();
            //科室类别与科室
            result.put("deptTypeList", super.getDepartmentTypeList(serialNo));
            result.put("deptList", super.getDepartmentList(serialNo,deptType));
            //产品条线
            result.put("productLineList", super.getProductLineList());
            return result;
        }

    /**
     * 查询分类下的科室
     * @return
     */
    @RequestMapping(value = "/deptList.do")
    @ResponseBody
    public Map<String,Object> deptList(EtDepartment info) {
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("deptList", super.getDepartmentList(info.getSerialNo(),info.getDeptType()));
        return result;
    }


    /**
     * 新增/修改科室
     * @return
     */
    @RequestMapping(value = "/updateSite.do")
    @ResponseBody
    public synchronized Map<String,Object> updateSite (EtSiteInstall info) {
        Map<String,Object> result = new HashMap<String,Object>();
        int isSucceed = -1;
        //判断科室名称是否重复
        EtSiteInstall install = new EtSiteInstall();
        install.setStatus(1);
        install.setDeptCode(info.getDeptCode());
        List<EtSiteInstall> installList=super.getFacade().getEtSiteInstallService().getEtSiteInstallList(install);
        if(installList.size()>0){
            result.put("status",1);
        }else{
            //获取部门名称
            EtDepartment dept = new EtDepartment();
            dept.setDeptCode(info.getDeptCode());
            dept.setIsDel(1);
            dept=super.getFacade().getEtDepartmentService().getEtDepartment(dept);
            info.setDeptName(dept.getDeptName());
            info.setId(ssgjHelper.createSiteInstallIdService());
            info.setDeptCode(info.getDeptCode());
            info.setStatus(1);
            info.setNum(0);
            //info.setPppId(new int[Integer.parseInt(info.getPdId())]);
            info.setCreator(info.getOperator());
            info.setCreateTime(new Timestamp(new Date().getTime()));
            info.setOperatorTime(new Timestamp(new Date().getTime()));
            super.getFacade().getEtSiteInstallService().createEtSiteInstall(info);
            result.put("status",0);
        }
        return result;
    }
    /**
     * 状态变化
     * @return
     */
    @RequestMapping(value = "/changeScope.do")
    @ResponseBody
    public synchronized Map<String,Object> changeScope (EtSiteInstall info) {
        String noScopeCode = info.getNoScopeCode();
        if (StringUtil.isEmptyOrNull(noScopeCode)) {
            info.setIsScope(1);
        } else {
            info.setIsScope(0);
        }
        Map map = new HashMap();
        super.getFacade().getEtSiteInstallService().modifyEtSiteInstall(info);
        map.put("type", Constants.SUCCESS);
        map.put("msg", "范围修改成功！");
        return map;
    }

    /**
     * 删除站点
     * @return
     */
    @RequestMapping(value = "/removeSite.do")
    @ResponseBody
    public synchronized Map<String,Object> removeSite (EtSiteInstall info) {
        Map map = new HashMap();
        info.setStatus(0);
        super.getFacade().getEtSiteInstallService().modifyEtSiteInstall(info);
        map.put("type", Constants.SUCCESS);
        map.put("msg", "删除成功！");
        return map;
    }

    /**
     * Chen,Kuai 删除图片
     * @param content
     * @return
     */
    @RequestMapping("/deleteImg.do")
    @ResponseBody
    public synchronized Map<String,Boolean> deleteImg(UrlContent content){
        Map<String,Boolean> map = new HashMap<String,Boolean>();
        String imgPath = content.getName();
        EtSiteInstallDetail info = new EtSiteInstallDetail();
        info.setId(content.getId());
        try{
            info = super.getFacade().getEtSiteInstallDetailService().getEtSiteInstallDetail(info);
            if (port == 21) {
                FtpUtils.deleteFtpFile(imgPath);
            } else if (port == 22) {
                SFtpUtils.rmFile(imgPath);
            }
            String[] imgs=info.getImgPath().split(";");
            String str="";
            for(int i = 0; i < imgs.length; i++) {
                if(imgPath.equals(imgs[i])){
                    info.setImgPath("");
                }else{
                    str +=imgs[i]+";";
                    info.setImgPath(str.substring(0,str.length()-1));
                }
            }

            super.getFacade().getEtSiteInstallDetailService().modifyEtSiteInstallDetail(info);
            map.put("status",true);
        }catch (Exception e){
            map.put("status",false);
        }
        return map;
    }


    /**
     * 硬件变化
     * @return
     */
    @RequestMapping(value = "/changeHardWare.do")
    @ResponseBody
    public synchronized Map<String,Object> changeHardWare (EtSiteInstall info) {
        Map map = new HashMap();
        String hwName="";
        if(StringUtils.isNotBlank(info.getHwId())){
            //所需要的 硬件
            List<EtSoftHardware> softHardwareList = super.getHardWareNameList(info.getPmId(),info.getHwId());
            //软件&硬件明细
            EtSiteInstall install = new EtSiteInstall();
            install.setSerialNo(info.getSerialNo());
            install.setId(info.getId());
            install = super.getFacade().getEtSiteInstallService().getEtSiteInstall(install);
            String[] hwId_list=info.getHwId().split(",");//[1,2,3]
            if(install != null){ //是否存在pdId 不为空时新增 修改 删除 三种状态
                if(StringUtils.isNotBlank(install.getHwId())){
                    String[] hwId_list_old=install.getHwId().split(",");//[1,3]
                    if(hwId_list.length < hwId_list_old.length){ //系统清单少于原有数据  出现了删除现象  判断是否已经安装
                        for(int i = 0; i < hwId_list_old.length; i++){ //新产生的系统信息
                            boolean is_exist = true;
                            for(int j =0;j < hwId_list.length;j++){ //原有的PD_ID
                                if(hwId_list_old[i].equals(hwId_list[j])){
                                    //相等不做操作
                                    is_exist = false;
                                }
                            }
                            if(is_exist){
                                EtSiteInstallDetail detail_old = new EtSiteInstallDetail();
                                detail_old.setSourceId(info.getId());
                                detail_old.setIp(hwId_list_old[i]);
                                detail_old.setSiteName("2"); //硬件
                                detail_old = super.getFacade().getEtSiteInstallDetailService().getEtSiteInstallDetail(detail_old);
                                if(detail_old != null){
                                    if(detail_old.getInstall()==1){ //已安装不能删除
                                        map.put("msg", "该硬件已安装删除无效！");
                                        return map;
                                    }else {
                                        super.getFacade().getEtSiteInstallDetailService().removeEtSiteInstallDetail(detail_old);
                                    }
                                }
                            }
                        }
                    }else{ //增加安装的软件
                        for(int i = 0; i < hwId_list.length; i++){ //新产生的系统信息
                            boolean is_exist = true;
                            for(int j =0;j < hwId_list_old.length;j++){ //原有的PD_ID
                                if(hwId_list_old[j].equals(hwId_list[i])){
                                    is_exist = false;//相等不做操作
                                }
                            }
                            if(is_exist){ //新增 明细数据
                                EtSiteInstallDetail detail = new EtSiteInstallDetail();
                                detail.setId(ssgjHelper.createSiteInstallDetailIdService());
                                detail.setSourceId(info.getId());
                                detail.setInstall(0);//未安装
                                detail.setIp(hwId_list[i]);//系统编码
                                detail.setSiteName("2");//1.软件 2.硬件
                                super.getFacade().getEtSiteInstallDetailService().createEtSiteInstallDetail(detail);
                            }
                        }
                    }

                }else{ //新增状态
                    for(int i = 0; i < hwId_list.length; i++) {
                        EtSiteInstallDetail detail = new EtSiteInstallDetail();
                        detail.setId(ssgjHelper.createSiteInstallDetailIdService());
                        detail.setSourceId(info.getId());
                        detail.setInstall(0);//未安装
                        detail.setIp(hwId_list[i]);//系统编码
                        detail.setSiteName("2");//1.软件 2.硬件
                        super.getFacade().getEtSiteInstallDetailService().createEtSiteInstallDetail(detail);
                    }
                }
            }


            for (EtSoftHardware s:softHardwareList){
                hwName +=s.getHwName()+";";
            }
            hwName=hwName.substring(0,hwName.length()-1);
            info.setHdName(hwName);
        }else{
            info.setHwId("");
            info.setHdName("");
        }
        super.getFacade().getEtSiteInstallService().modifyEtSiteInstall(info);
        map.put("type", Constants.SUCCESS);
        map.put("msg", "硬件修改成功！");
        return map;
    }

    /**
     * 软件变化
     * @return
     */
    @RequestMapping(value = "/changeSoftWare.do")
    @ResponseBody
    public synchronized Map<String,Object> changeSoftWare (EtSiteInstall info) {
        Map map = new HashMap();
        String sfName="";
        if(StringUtils.isNotBlank(info.getPdId())){
            //所需要的 软件
            EtContractTask task = new EtContractTask();
            task.setSerialNo(info.getSerialNo());
            //task.setId(info.getPdId());
            task.getMap().put("softNameList",info.getPdId());
            List<EtContractTask> softHardwareList = super.getFacade().getEtContractTaskService().getEtContractTaskList(task);
            //软件&硬件明细
            EtSiteInstall install = new EtSiteInstall();
            install.setSerialNo(info.getSerialNo());
            install.setId(info.getId());
            install = super.getFacade().getEtSiteInstallService().getEtSiteInstall(install);
            String[] pdId_list=info.getPdId().split(",");//[1,2,3]
            if(install != null){ //是否存在pdId 不为空时新增 修改 删除 三种状态
                if(StringUtils.isNotBlank(install.getPdId())){
                    String[] pdId_list_old=install.getPdId().split(",");//[1,3]
                    if(pdId_list.length < pdId_list_old.length){ //系统清单少于原有数据  出现了删除现象  判断是否已经安装
                        for(int i = 0; i < pdId_list_old.length; i++){ //新产生的系统信息
                            boolean is_exist = true;
                            for(int j =0;j < pdId_list.length;j++){ //原有的PD_ID
                                if(pdId_list_old[i].equals(pdId_list[j])){
                                    //相等不做操作
                                    is_exist = false;
                                }
                            }
                            if(is_exist){
                                EtSiteInstallDetail detail_old = new EtSiteInstallDetail();
                                detail_old.setSourceId(info.getId());
                                detail_old.setIp(pdId_list_old[i]);
                                detail_old.setSiteName("1"); //软件
                                detail_old = super.getFacade().getEtSiteInstallDetailService().getEtSiteInstallDetail(detail_old);
                                if(detail_old != null){
                                    if(detail_old.getInstall()==1){ //已安装不能删除
                                        map.put("msg", "该软件已安装删除无效！");
                                        return map;
                                    }else {
                                        super.getFacade().getEtSiteInstallDetailService().removeEtSiteInstallDetail(detail_old);
                                    }
                                }
                            }
                        }
                }else{ //增加安装的软件
                        for(int i = 0; i < pdId_list.length; i++){ //新产生的系统信息
                            boolean is_exist = true;
                            for(int j =0;j < pdId_list_old.length;j++){ //原有的PD_ID
                                if(pdId_list_old[j].equals(pdId_list[i])){
                                    is_exist = false;//相等不做操作
                                }
                            }
                            if(is_exist){ //新增 明细数据
                                EtSiteInstallDetail detail = new EtSiteInstallDetail();
                                detail.setId(ssgjHelper.createSiteInstallDetailIdService());
                                detail.setSourceId(info.getId());
                                detail.setInstall(0);//未安装
                                detail.setIp(pdId_list[i]);//系统编码
                                detail.setSiteName("1");//1.软件 2.硬件
                                super.getFacade().getEtSiteInstallDetailService().createEtSiteInstallDetail(detail);
                            }
                        }
                    }

                }else{ //新增状态
                    for(int i = 0; i < pdId_list.length; i++) {
                        EtSiteInstallDetail detail = new EtSiteInstallDetail();
                        detail.setId(ssgjHelper.createSiteInstallDetailIdService());
                        detail.setSourceId(info.getId());
                        detail.setInstall(0);//未安装
                        detail.setIp(pdId_list[i]);//系统编码
                        detail.setSiteName("1");//1.软件 2.硬件
                        super.getFacade().getEtSiteInstallDetailService().createEtSiteInstallDetail(detail);
                    }
                }
            }

            for (EtContractTask s:softHardwareList){
                sfName +=s.getZxtmc()+";";
            }
            sfName=sfName.substring(0,sfName.length()-1);
            info.setPdName(sfName);
        }else{
            info.setPdId("");
            info.setPdName("");
            EtSiteInstallDetail detail_old = new EtSiteInstallDetail();
            detail_old.setSourceId(info.getId());
            detail_old.setSiteName("1"); //软件
            detail_old = super.getFacade().getEtSiteInstallDetailService().getEtSiteInstallDetail(detail_old);
            if(detail_old != null){
                if(detail_old.getInstall()==1){ //已安装不能删除
                    map.put("msg", "该软件已安装不能删除！");
                    return map;
                }else {
                    super.getFacade().getEtSiteInstallDetailService().removeEtSiteInstallDetail(detail_old);
                }
            }
        }
        super.getFacade().getEtSiteInstallService().modifyEtSiteInstall(info);
        map.put("type", Constants.SUCCESS);
        map.put("msg", "软件修改成功！");
        return map;
    }

    /**
     * 分配人变化
     * @return
     */
    @RequestMapping(value = "/changeUser.do")
    @ResponseBody
    public synchronized Map<String,Object> changeUser (EtSiteInstall info) {
        Map map = new HashMap();
        if(info.getPuserId() != 0 && info.getPuserId() != null){
            super.getFacade().getEtSiteInstallService().modifyEtSiteInstall(info);
        }
        map.put("type", Constants.SUCCESS);
        map.put("msg", "硬件修改成功！");
        return map;
    }

    /**
     * 站点数
     * @return
     */
    @RequestMapping(value = "/changeSite.do")
    @ResponseBody
    public synchronized Map<String,Object> changeSite (EtSiteInstall info) {
        Map map = new HashMap();
        if(info.getNum() != 0 && info.getNum() != null){
            super.getFacade().getEtSiteInstallService().modifyEtSiteInstall(info);
        }
        map.put("type", Constants.SUCCESS);
        map.put("msg", "硬件修改成功！");
        return map;
    }


    /**
     * 编辑站点明细信息
     * @return
     */
    @RequestMapping(value = "/addSiteInstallDetail.do")
    @ResponseBody
    public Map<String,Object> addSiteInstallDetail (EtSiteInstall info) {
        Map<String,Object> result = new HashMap<String,Object>();
        //生成明细站点信息
        //根据客户编号 找出对应的全部
        info.setPmId(info.getPmId());
        info.setId(info.getId());
        info.setOperator(null);
        info = super.getFacade().getEtSiteInstallService().getEtSiteInstall(info);
        EtSiteInstallDetail installDetail = new EtSiteInstallDetail();
        //获取安装站点的信息
        installDetail.setSourceId(info.getId());
        installDetail.setSiteName("1");
        List<EtSiteInstallDetail> siteInstallSoftDetails = super.getFacade().getEtSiteInstallDetailService().getEtSiteInstallDetailList(installDetail);
        EtSiteInstallDetail installHardDetail = new EtSiteInstallDetail();
        installHardDetail.setSourceId(info.getId());
        installHardDetail.setSiteName("2");
        List<EtSiteInstallDetail> siteInstallHardDetails = super.getFacade().getEtSiteInstallDetailService().getEtSiteInstallHardDetailList(installHardDetail);

        result.put("siteInstallSoftDetails", siteInstallSoftDetails);
        result.put("siteInstallHardDetails", siteInstallHardDetails);
        result.put("deptName",info.getDeptName());
        result.put("status", Constants.SUCCESS);
        return result;
    }

    /**
     * 站点图片上传
     * @return
     */
    @RequestMapping(value = "/uploadFileSite.do")
    @ResponseBody
    public Map<String,Object> uploadFileSite (HttpServletRequest request,EtSiteInstallDetail installDetail, MultipartFile file) {
        Map map = new HashMap();
        String pmId =request.getParameter("pmId");
        try{
            if(!file.isEmpty()) {
                String pathLu=Constants.UPLOAD_PC_PREFIX+pmId+"/site/";
                String path = request.getServletContext().getRealPath(pathLu);
                //上传文件名
                String filename = file.getOriginalFilename();
                filename = System.currentTimeMillis()+"."+StringUtils.substringAfterLast(filename,".");
                File filepath = new File(path,filename);
                //判断路径是否存在，如果不存在就创建一个
                if (!filepath.getParentFile().exists()) {
                    filepath.getParentFile().mkdirs();
                }
                //将上传文件保存到一个目标文件当中
                File newFile = new File(path + File.separator + filename);
                if(newFile.exists()){
                    newFile.delete();
                }
                file.transferTo(newFile);
                String remotePath = pathLu+ filename;
                String remoteDir =pathLu ;
                boolean ftpStatus = false;
                String msg = "";
                if (port == 21){
                    ftpStatus = FtpUtils.uploadFile(remotePath, newFile);
                }else if(port == 22){
                    try {
                        SFtpUtils.uploadFile(newFile.getPath(),remoteDir,filename);
                        ftpStatus = true;
                    } catch (Exception e) {
                        e.printStackTrace();
                        ftpStatus = false;
                        msg = e.getMessage();
                    }
                }
                EtSiteInstallDetail info = new EtSiteInstallDetail();
                if(StringUtils.isNotBlank(String.valueOf(installDetail.getId()))){
                    info.setId(installDetail.getId());
                    info = super.getFacade().getEtSiteInstallDetailService().getEtSiteInstallDetail(info);
                    if(StringUtils.isNotBlank(info.getImgPath())){
                        info.setImgPath(info.getImgPath()+";"+ remotePath);//拼接图片路径
                    }else{
                        info.setImgPath(remotePath);
                    }
                    info.setOperator(installDetail.getOperator());
                    info.setOperatorTime(new Timestamp(new Date().getTime()));
                    super.getFacade().getEtSiteInstallDetailService().modifyEtSiteInstallDetail(info);
                    map.put("id",String.valueOf(installDetail.getId()));
                    map.put("status","1");
                }

                map.put("path",remotePath);
            }else {
                map.put("status","0");
            }

        }catch (Exception e){
            e.printStackTrace();
            map.put("status","0");
        }

        map.put("type", Constants.SUCCESS);
        map.put("msg", "硬件修改成功！");
        return map;
    }


    /**
     * 保存站点明细信息
     * @return
     */
    @RequestMapping(value = "/saveSiteDetail.do", method ={RequestMethod.POST})
    @ResponseBody
    public synchronized Map<String,Object> saveSiteDetail (HttpServletRequest request,EtSiteInstallDetailForm ss) throws  Exception {
        Map map = new HashMap();
        String detailData = request.getParameter("detailData");
        String sourceId = request.getParameter("sourceId");
        String operator = request.getParameter("operator");
        String detailHwData = request.getParameter("detailHwData");

        String[] idList = detailData.split(",");
        String[] hwIdList = detailHwData.split(",");
        List<String> list = new ArrayList(Arrays.asList(idList));
        list.addAll(Arrays.asList(hwIdList));

        if(list.size()>0){
            //全部置成未安装
            EtSiteInstallDetail detail_old = new EtSiteInstallDetail();
            detail_old.setSourceId(Long.valueOf(sourceId));
            //detail_old.setSiteName("1");//软件
            detail_old.setInstall(0);
            super.getFacade().getEtSiteInstallDetailService().updateEtSiteInstallDetailSourceId(detail_old);

            for(int i=0; i<list.size(); i++){
                EtSiteInstallDetail detail = new EtSiteInstallDetail();
                detail.setId(Long.parseLong(list.get(i)));
                detail.setInstall(1);
                detail.setOperator(Long.parseLong(operator));
                detail.setOperatorTime(new Timestamp(new Date().getTime()));
                super.getFacade().getEtSiteInstallDetailService().modifyEtSiteInstallDetail(detail);

            }
        }

        map.put("type", Constants.SUCCESS);
        map.put("msg", "站点修改成功！");
        return map;
    }

    /**
     * 确认完成安装
     * @return
     */
    @RequestMapping(value = "/siteEnd.do", method ={RequestMethod.POST})
    @ResponseBody
    public synchronized Map<String,Object> siteEnd (EtSiteInstall info,Long userId,Long dataType){
        Map map = new HashMap();
        EtProcessManager manager = new EtProcessManager();
        manager.setPmId(info.getPmId());
        if(dataType ==0){
            manager.setIsSiteInstall(1);
            map.put("type", "1");
            map.put("msg", "确认成功！");

        }else{
            manager.setIsSiteInstall(0);
            map.put("type", "0");
            map.put("msg", "取消确认成功！");
        }
        manager.setOperator(userId);
        manager.setOperatorTime(new Timestamp(new Date().getTime()));
        super.getFacade().getEtProcessManagerService().updateEtProcessManagerByPmId(manager);
        map.put("dataType",manager.getIsSiteInstall());
        return map;
    }
    /**
     *导入excel文件
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/upload.do")
    @ResponseBody
    @Transactional
    public Map<String, Object> uploadExcel(HttpServletRequest request,EtSiteInstall info,
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
                    List<List<Object>> deptList = ExcelUtil.importExcel(newFile.getPath());
                    super.getFacade().getEtSiteInstallService().createEtSiteInstallDeptInfo(deptList,info);
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
     *导出excel文件
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/exportExcel.do")
    public HttpServletResponse wiriteExcel(EtSiteInstall info, HttpServletResponse response) throws IOException {
        String fileName = "siteInstall.xls";
        String path = getClass().getClassLoader().getResource("/template").getPath() + fileName;
        super.getFacade().getEtSiteInstallService().getNerateSiteIntallExcel(info,path);
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
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("工作站点安装.xls","UTF-8"));
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
     * 确认完成安装
     * @return
     */
    @RequestMapping(value = "/showEchart.do", method ={RequestMethod.POST})
    @ResponseBody
    public synchronized Map<String,Object> showEchart (EtSiteInstall info){
        Map map = new HashMap();
        //获取分配人
        List<EtSiteInstall> list = super.getFacade().getEtSiteInstallService().getEtSiteInstallGroupPuser(info);
        List puserNameList = new ArrayList();
        List puserNumList = new ArrayList();
        if (list.size()>0){
            for (int i=0;i<list.size();i++){
                puserNameList.add(list.get(i).getMap().get("puserName"));
                puserNumList.add(list.get(i).getMap().get("puserNum"));
            }
        }
        map.put("puserNameList",puserNameList);
        map.put("puserNumList",puserNumList);
        return map;
    }



}
