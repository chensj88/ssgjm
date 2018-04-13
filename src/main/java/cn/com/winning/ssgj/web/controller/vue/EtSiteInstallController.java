package cn.com.winning.ssgj.web.controller.vue;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.helper.SSGJHelper;
import cn.com.winning.ssgj.domain.EtFloorQuestionInfo;
import cn.com.winning.ssgj.domain.EtSiteInstall;
import cn.com.winning.ssgj.domain.support.Row;
import cn.com.winning.ssgj.web.controller.common.BaseController;
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
            List<EtSiteInstall> installList = super.getFacade().getEtSiteInstallService().getEtSiteInstallListWithSum(info);
            //所需硬件
            

            //所需要的 软件
            result.put("productLineList", super.getProductLineList(info.getPmId()));
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
     * 查询产品条线系统
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


}
