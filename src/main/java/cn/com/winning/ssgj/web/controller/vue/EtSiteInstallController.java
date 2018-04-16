package cn.com.winning.ssgj.web.controller.vue;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.helper.SSGJHelper;
import cn.com.winning.ssgj.base.util.StringUtil;
import cn.com.winning.ssgj.domain.EtFloorQuestionInfo;
import cn.com.winning.ssgj.domain.EtSiteInstall;
import cn.com.winning.ssgj.domain.EtSoftHardware;
import cn.com.winning.ssgj.domain.support.Row;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import com.sun.xml.internal.xsom.impl.scd.Iterators;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            List<EtSiteInstall> installList = super.getFacade().getEtSiteInstallService().getEtSiteInstallList(info);
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
        public Map<String,Object> siteInstallAddDept() {
            Map<String,Object> result = new HashMap<String,Object>();
            //result.put("productLineList", super.getProductLineList(info.getPmId()));
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
        if(StringUtils.isNotBlank(info.getPdId())){
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


}
