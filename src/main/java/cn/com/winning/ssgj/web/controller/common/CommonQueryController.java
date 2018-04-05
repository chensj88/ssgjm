package cn.com.winning.ssgj.web.controller.common;

import cn.com.winning.ssgj.base.helper.SSGJHelper;
import cn.com.winning.ssgj.base.util.RequestUtil;
import cn.com.winning.ssgj.domain.SysDictInfo;
import cn.com.winning.ssgj.service.Facade;
import cn.com.winning.ssgj.service.SysDictInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CommonQueryController {

    @Autowired
    private  Facade facadeImpl;

    /**
     * @param info
     * @return 获取字典集合方法类
     */
    public List<SysDictInfo> getCode(SysDictInfo info){
        List<SysDictInfo> dictInfos = facadeImpl.getSysDictInfoService().getSysDictInfoList(info);
        return dictInfos;
    }


}
