package cn.com.winning.ssgj.web.controller.vue;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.domain.PmisContractProductInfo;
import cn.com.winning.ssgj.domain.support.Row;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenshijie
 * @title
 * @email chensj@winning.com.cm
 * @package cn.com.winning.ssgj.web.controller.vue
 * @date 2018-03-26 8:52
 */
@CrossOrigin
@Controller
@RequestMapping(value = "/vue/projectProduct")
public class EtContractProjectController extends BaseController {

    @RequestMapping(value = "/initData.do")
    @ResponseBody
    public Map<String,Object> listProductOfProject(PmisContractProductInfo productInfo, Row row){
        productInfo.setRow(row);
        productInfo.setHtcplb(Constants.PMIS.CPLB_1);
        List<PmisContractProductInfo> productInfoList = super.getFacade().getPmisContractProductInfoService().getPmisContractProductInfoPaginatedList(productInfo);
        int total = super.getFacade().getPmisContractProductInfoService().getPmisContractProductInfoCount(productInfo);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("total", total);
        result.put("status", Constants.SUCCESS);
        result.put("rows", productInfoList);
        return result;

    }
}
