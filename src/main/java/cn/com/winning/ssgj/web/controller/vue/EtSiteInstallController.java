package cn.com.winning.ssgj.web.controller.vue;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.helper.SSGJHelper;
import cn.com.winning.ssgj.base.util.FtpPropertiesLoader;
import cn.com.winning.ssgj.base.util.FtpUtils;
import cn.com.winning.ssgj.base.util.SFtpUtils;
import cn.com.winning.ssgj.base.util.StringUtil;
import cn.com.winning.ssgj.domain.*;
import cn.com.winning.ssgj.domain.support.Row;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import com.sun.xml.internal.ws.resources.HttpserverMessages;
import com.sun.xml.internal.xsom.impl.scd.Iterators;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
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
                    }
                }
            }
            //所需要的 软件
            result.put("productLineList", super.getProductLineList(info.getPmId()));
            //所需要的 硬件
            result.put("hardList",super.getHardWareList(info.getPmId()));
            result.put("userList",super.getEtUserInfo(info.getPmId()));
            result.put("total", installList.size());
            result.put("status", Constants.SUCCESS);
            result.put("rows", installList);
            return result;
        }

        /**
         * 查询产品条线系统
         * @return
         */
        @RequestMapping(value = "/addDept.do")
        @ResponseBody
        public Map<String,Object> siteInstallAddDept(EtSoftHardware info) {
            Map<String,Object> result = new HashMap<String,Object>();
            result.put("productLineList", super.getProductLineList(info.getPmId()));
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
        install.setDeptName(info.getDeptName());
        List<EtSiteInstall> installList=super.getFacade().getEtSiteInstallService().getEtSiteInstallList(info);
        if(installList.size()>0){
            result.put("status",1);
        }else{
            info.setId(ssgjHelper.createSiteInstallIdService());
            info.setDeptCode(String.valueOf(ssgjHelper.createSysHospitalDeptIdService()));
            info.setStatus(1);
            info.setNum(0);
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
            for (EtSoftHardware s:softHardwareList){
                hwName +=s.getHwName()+";";
            }
            hwName=hwName.substring(0,hwName.length()-1);
            info.setHdName(hwName);
            super.getFacade().getEtSiteInstallService().modifyEtSiteInstall(info);
        }
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
            List<PmisProductLineInfo> softHardwareList = super.getPdNameList(info.getPmId(),info.getPdId());
            for (PmisProductLineInfo s:softHardwareList){
                sfName +=s.getName()+";";
            }
            sfName=sfName.substring(0,sfName.length()-1);
            info.setPdName(sfName);
            super.getFacade().getEtSiteInstallService().modifyEtSiteInstall(info);
        }
        map.put("type", Constants.SUCCESS);
        map.put("msg", "硬件修改成功！");
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
        info = super.getFacade().getEtSiteInstallService().getEtSiteInstall(info);
        EtSiteInstallDetail installDetail = new EtSiteInstallDetail();
        //获取安装站点的信息
        installDetail.setSourceId(info.getId());
        List<EtSiteInstallDetail> siteInstallDetails=new ArrayList<EtSiteInstallDetail>();
        siteInstallDetails = super.getFacade().getEtSiteInstallDetailService().getEtSiteInstallDetailList(installDetail);
        if((siteInstallDetails.size()==0 || siteInstallDetails==null) && info.getNum() > 0){
            for(int i =0 ; i<info.getNum(); i++){
                EtSiteInstallDetail detail = new EtSiteInstallDetail();
                detail.setId(ssgjHelper.createSiteInstallDetailIdService());
                detail.setSourceId(info.getId());
                detail.setInstall(0);
                super.getFacade().getEtSiteInstallDetailService().createEtSiteInstallDetail(detail);
            }
            //当为空的时候重新获取 初始化的安装明细信息
            siteInstallDetails=super.getFacade().getEtSiteInstallDetailService().getEtSiteInstallDetailList(installDetail);
        }else{//存在集合 获取图片信息
            for (EtSiteInstallDetail detail: siteInstallDetails) {
                if(StringUtils.isNotBlank(detail.getImgPath())){
                    String[] imgs=detail.getImgPath().split(";");
                    //List<String> lists= Arrays.asList(imgs); detail.setImgs(lists);
                    detail.setImgsArray(imgs);
                }
            }
        }
        result.put("siteInstallDetails", siteInstallDetails);
        result.put("status", Constants.SUCCESS);
        return result;
    }

    /**
     * 站点数
     * @return
     */
    @RequestMapping(value = "/uploadFileSite.do")
    @ResponseBody
    public Map<String,Object> uploadFileSite (HttpServletRequest request,EtSiteInstallDetail installDetail, MultipartFile file) {
        Map map = new HashMap();
        try{
            if(!file.isEmpty()) {
                String path = request.getServletContext().getRealPath("/onlineFile/");
                //上传文件名
                String filename = file.getOriginalFilename();
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
                String remotePath = "/onlineFile/"+ filename;
                String remoteDir ="/onlineFile/" ;
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
    public synchronized Map<String,Object> saveSiteDetail (HttpServletRequest request,EtSiteInstallDetailForm ss) {
        Map map = new HashMap();
        String sss = request.getParameter("jj");
        String siteList = request.getParameter("siteList");



        map.put("type", Constants.SUCCESS);
        map.put("msg", "硬件修改成功！");
        return map;
    }




}
