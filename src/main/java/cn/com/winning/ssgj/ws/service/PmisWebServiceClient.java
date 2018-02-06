package cn.com.winning.ssgj.ws.service;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.util.PmisWSUtil;
import cn.com.winning.ssgj.domain.PmisContractInfo;
import cn.com.winning.ssgj.domain.PmisCustomerInformation;
import cn.com.winning.ssgj.domain.SysUserInfo;
import cn.com.winning.ssgj.ws.client.*;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author chenshijie
 * @title
 * @email chensj@winning.com.cm
 * @package cn.com.winning.ssgj.ws.service
 * @date 2018-02-05 13:09
 */
public class PmisWebServiceClient {

    /**
     * 从PMIS系统导入数据
     * @param dataType 数据类型 参考Constants.PmisWSConstants.WS_SERVICE_TYPE_*
     */
    public void importDataFromPmisByDataType(String dataType){
        //基础信息
        LBEBusinessService lbeBusinessService = PmisWSUtil.createLBEBusinessService();
        LoginResult loginResult = PmisWSUtil.createLoginResult();
        List<LbParameter> params = PmisWSUtil.createLbParameter(dataType);
        QueryOption queryOption  = PmisWSUtil.createFirstCountValueOption();
        //查询数量信息
        QueryResult result = lbeBusinessService.query(loginResult.getSessionId(),Constants.PmisWSConstants.WS_SERVICE_OBJECT_NAME,
                params,"",queryOption);
        int total = result.getCount();
        int pageSize = Constants.PmisWSConstants.QUERY_BATCH_SIZE;
        int lastPageSize = total % pageSize;
        int page = 0;
        boolean pageEnd = false;

        if(lastPageSize > 0){
            page = total/pageSize + 1;
            pageEnd = true;
        }else{
            page = total/pageSize;
            pageEnd = false;
        }

        for ( int i = 1;i<= page;i++){
            if( (i == page) && pageEnd ){
                queryOption = PmisWSUtil.createQueryValueOption(i,lastPageSize);
                result = lbeBusinessService.query(loginResult.getSessionId(),Constants.PmisWSConstants.WS_SERVICE_OBJECT_NAME,
                        params,"",queryOption);
                resolveWSResult(result,dataType);
            }else{
                queryOption = PmisWSUtil.createQueryValueOption(i);
                result = lbeBusinessService.query(loginResult.getSessionId(),Constants.PmisWSConstants.WS_SERVICE_OBJECT_NAME,
                        params,"",queryOption);
                resolveWSResult(result,dataType);
            }
        }

    }

    /**
     * 解析WS返回数据数据
     * @param result  查询获取数据
     * @param dataType 查询数据类型
     */
    private void resolveWSResult(QueryResult result,String dataType) {
        if(Constants.PmisWSConstants.WS_SERVICE_TYPE_USER.equals(dataType)){
            resolveUserInfoData(result);
        }else  if (Constants.PmisWSConstants.WS_SERVICE_QUERY_CUSTOM.equals(dataType)){
            resolveCustomData(result);
        }

    }

    /**
     * 解析客户信息
     * @param result
     */
    private void resolveCustomData(QueryResult result) {
        int resultNum = result.getRecords().size();
        List<LbRecord> recordList = result.getRecords();
        for (int i = 0; i < recordList.size(); i++) {
            LbRecord record = recordList.get(i);
            List<Object>  values = record.getValues();
            PmisCustomerInformation customerInformation = new PmisCustomerInformation();
            customerInformation.setId(Long.valueOf(values.get(0).toString()));
            customerInformation.setCode(values.get(1).toString());
            customerInformation.setName(values.get(2).toString());
            customerInformation.setYylx(values.get(3).toString());
            customerInformation.setQyxx(values.get(4).toString());
            customerInformation.setCity(values.get(5).toString());
            System.out.println(customerInformation);
        }
    }

    /**
     * 解析用户信息
     * @param result
     */
    private void resolveUserInfoData(QueryResult result){
        int resultNum = result.getRecords().size();
        List<LbRecord> recordList = result.getRecords();
        for (int i = 0; i < recordList.size(); i++) {
            LbRecord record = recordList.get(i);
            List<Object>  values = record.getValues();
            SysUserInfo userInfo = new SysUserInfo();
            userInfo.setId(Long.valueOf(values.get(0).toString()));
            userInfo.setStatus(1);
            userInfo.setYhmc(values.get(1).toString());
            userInfo.setUserid(values.get(2).toString());
            userInfo.setPassword(values.get(3).toString());
            userInfo.setName(values.get(1).toString() + values.get(2).toString());
            userInfo.setUserType(Constants.User.USER_STATUS_NORMAL);
            userInfo.setSsgs(values.get(4).toString());
            userInfo.setYhsjgh(values.get(5).toString());
            System.out.println(userInfo);
        }

    }

}
