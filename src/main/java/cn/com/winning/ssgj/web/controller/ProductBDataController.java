package cn.com.winning.ssgj.web.controller;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.domain.SysDataInfo;
import cn.com.winning.ssgj.domain.SysDictInfo;
import cn.com.winning.ssgj.domain.SysProductDataInfo;
import cn.com.winning.ssgj.service.SysDataInfoService;
import cn.com.winning.ssgj.service.SysProductDataInfoService;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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
public class ProductBDataController extends BaseController {

    private SysProductDataInfoService sysProductDataInfoService = super.getFacade().getSysProductDataInfoService();
    private SysDataInfoService sysDataInfoService = super.getFacade().getSysDataInfoService();

    @RequestMapping(value = "/mapping/pBdataInfo.do")
    public String queryMappingPage(HttpServletRequest request, Model model){
        return "auth/mapping/productBDMapping";
    }

    @RequestMapping(value = "/pBdata/queryById.do")
    @ResponseBody
    public Map<String,Object> queryBdataInfoById(SysProductDataInfo dataInfo){
        List<SysProductDataInfo> dataInfoList = sysProductDataInfoService.getSysProductDataInfoList(dataInfo);

        Map<String,Object> param = new HashMap<String,Object>();
        Map<String,Object> result = new HashMap<String,Object>();
        if (dataInfoList != null && dataInfoList.size() > 0 ){
            List<String> idString = sysProductDataInfoService.getDataInfoId(dataInfoList);
            param.put("pks",idString);
            SysDataInfo data = new SysDataInfo();
            data.setMap(param);
            List<SysDataInfo> sysDataInfoList = sysDataInfoService.getSysDataInfoListById(data);
            result.put("status", Constants.SUCCESS);
            result.put("data", sysDataInfoList);
            return result;
        }
        result.put("status", Constants.SUCCESS);
        return result;

    }

    @RequestMapping(value = "/pBdata/queryProductDataInfoById.do")
    @ResponseBody
    public Map<String,Object> queryProductDataInfoById(Integer pdId,List<Integer> bdIds){


        Map<String,Object> result = new HashMap<String,Object>();
        result.put("status", Constants.SUCCESS);
        /*result.put("data", );*/
        return result;

    }

}
