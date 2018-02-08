package cn.com.winning.ssgj.web.controller;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.domain.SysDataInfo;
import cn.com.winning.ssgj.domain.SysDictInfo;
import cn.com.winning.ssgj.domain.SysProductDataInfo;
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
 * @title
 * @email chensj@winning.com.cm
 * @package cn.com.winning.ssgj.web.controller
 * @date 2018-02-07 11:15
 */
@Controller
@RequestMapping(value = "/admin")
public class ProductBDataController extends BaseController {

    @RequestMapping(value = "/mapping/pBdataInfo.do")
    public String queryMappingPage(HttpServletRequest request, Model model){
        return "auth/mapping/productBDMapping";
    }

    @RequestMapping(value = "/pBdata/queryById.do")
    @ResponseBody
    public Map<String,Object> queryBdataInfoById(SysProductDataInfo dataInfo){
        List<SysProductDataInfo> dataInfoList = super.getFacade().getSysProductDataInfoService().getSysProductDataInfoList(dataInfo);

        Map<String,Object> param = new HashMap<String,Object>();
        Map<String,Object> result = new HashMap<String,Object>();
        if (dataInfoList != null && dataInfoList.size() > 0 ){
            List<String> idString = super.getFacade().getSysProductDataInfoService().getDataInfoId(dataInfoList);
            param.put("pks",idString);
            SysDataInfo data = new SysDataInfo();
            data.setMap(param);
            List<SysDataInfo> sysDataInfoList = super.getFacade().getSysDataInfoService().getSysDataInfoListById(data);
            result.put("status", Constants.SUCCESS);
            result.put("data", sysDataInfoList);
            return result;
        }
        result.put("status", Constants.SUCCESS);
        return result;

    }


}
