package cn.com.winning.ssgj.web.controller.admin;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.domain.SysDataInfo;
import cn.com.winning.ssgj.domain.SysProductDataInfo;
import cn.com.winning.ssgj.domain.SysUserInfo;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenshijie
 * @title 产品基础数据Controller
 * @email chensj@winning.com.cm
 * @package cn.com.winning.ssgj.web.controller
 * @date 2018-02-07 11:15
 */
@Controller
@RequestMapping(value = "/admin")
@CrossOrigin
public class ProductBDataDemoController extends BaseController {

    @RequestMapping(value = "/mapping/pBdataDInfo.do")
    public String queryMappingPage(HttpServletRequest request, Model model) {
        return "auth/mapping/productBDDemo";
    }

    @RequestMapping(value = "/pBdataD/queryById.do")
    @ResponseBody
    public Map<String, Object> queryBdataInfoById(SysProductDataInfo dataInfo) {
        Map<String, Object> result = new HashMap<String, Object>();
        SysDataInfo d = new SysDataInfo();
        d.setStatus(Constants.STATUS_USE);
        d.getMap().put("pdId",dataInfo.getPdId());
        if(dataInfo.getPdId() == -1L){
            result.put("nsData",super.getFacade().getSysDataInfoService().getNonSelectedSysDataInfoListByProductId(d));
        }else{
            result.put("sData",super.getFacade().getSysDataInfoService().getNonSelectedSysDataInfoListByProductId(d));
            result.put("nsData",super.getFacade().getSysDataInfoService().getSelectedSysDataInfoListByProductId(d));
        }

        result.put("status", Constants.SUCCESS);
        return result;

    }

    @RequestMapping(value = "/pBdataD/queryProductDataInfoById.do")
    @ResponseBody
    public Map<String, Object> queryProductDataInfoById(Integer pdId, String bdIds) {
        List<SysProductDataInfo> dataInfos = super.getFacade().getSysProductDataInfoService().getSysProductDataInfoByIds(pdId, bdIds);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        result.put("data", dataInfos);
        return result;
    }

    @RequestMapping(value = "/pBdataD/removeMapping.do")
    @ResponseBody
    public Map<String, Object> removePBMapping(String idList) {
        super.getFacade().getSysProductDataInfoService().removeSysProductDataInfo(idList);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        result.put("data", "");
        return result;

    }

    @RequestMapping(value = "/pBdataD/addMapping.do")
    @ResponseBody
    public Map<String, Object> addPBMapping(String idList) throws ParseException {
        SysUserInfo userInfo = (SysUserInfo) SecurityUtils.getSubject().getPrincipal();
        System.out.println(idList);
        super.getFacade().getSysProductDataInfoService().addSysProductDataInfoMapping(idList, userInfo);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", Constants.SUCCESS);
        return result;

    }
}
