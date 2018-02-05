package cn.com.winning.ssgj.ws;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.util.PinyinTools;
import cn.com.winning.ssgj.base.util.PmisWSUtil;
import cn.com.winning.ssgj.domain.SysUserInfo;
import cn.com.winning.ssgj.ws.client.*;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenshijie
 * @title
 * @email chensj@winning.com.cm
 * @package cn.com.winning.ssgj.ws.service
 * @date 2018-02-05 11:12
 */
public class PmisWebServiceTest {

    private static final  String url = "http://weberp.winning.com.cn:9080/service/LBEBusiness?wsdl";
    private static final LoginResult LOGIN_RESULT;
    private static final LBEBusinessService LBE_BUSINESS_SERVICE;
    private static final  int FIRST_BATCH_SIZE = 1;
    private static final  int COMMON_BATCH_SIZE = 100;

    static {
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(LBEBusinessService.class);
        factory.setAddress(url);
        LBE_BUSINESS_SERVICE = factory.create(LBEBusinessService.class);
        LOGIN_RESULT = LBE_BUSINESS_SERVICE.login(Constants.PmisWSConstants.WS_USER,
               Constants.PmisWSConstants.WS_PASSWORD,"",
                Constants.PmisWSConstants.WS_ALGORITHM,"");
    }

    /**
     * 测试登录
     */
    @Test
    public void testLogin(){
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(LBEBusinessService.class);
        factory.setAddress(url);
        LBEBusinessService lbeBusinessService = factory.create(LBEBusinessService.class);
        String userId = "sszhb";
        String password = "abc123";
        LoginResult loginResult = lbeBusinessService.login(userId,password,"","plain","");
        System.out.println(loginResult.getResult());
        System.out.println(loginResult.getSessionId());
        System.out.println(loginResult.getMessage());
    }

    /**
     * 测试获取用户信息数据
     */
    @Test
    public void testQueryUserInfoData(){
        LbParameter parameter = new LbParameter();
        parameter.setName("Ptype");
        parameter.setValue("1");
        List<LbParameter> params = new ArrayList<LbParameter>();
        params.add(parameter);
        QueryOption option = new QueryOption();
        option.setBatchNo(1);
        option.setBatchSize(1);
        option.setQueryCount(true);
        option.setValueOption(ValueOption.VALUE);
        QueryResult result = LBE_BUSINESS_SERVICE.query(LOGIN_RESULT.getSessionId(),"WS_DS_CXJCXX",params,"",option);
        System.out.println("Result:"+result.getResult());
        System.out.println("Message:"+result.getMessage());
        System.out.println("HasMore:"+result.isHasMore());
        System.out.println("Count:"+result.getCount());
        System.out.println("CountId"+result.getQueryId());
        System.out.println("MetaData:"+result.getMetaData());
        System.out.println("MetaData.ColumnCount"+result.getMetaData().getColumnCount());
        System.out.println("MetaData.ColInfo:"+result.getMetaData().getColInfo());
        List<ColInfo> colInfos = result.getMetaData().getColInfo();
        for (int i = 0; i < colInfos.size(); i++) {
            ColInfo info = colInfos.get(i);
            System.out.println("ColInfo{" +
                    "label='" + info.getLabel() + '\'' + PinyinTools.cn2FirstSpell(info.getLabel()) +
                    ", length=" + info.getLength() +
                    ", name='" + info.getName() + '\'' +
                    ", scale=" + info.getScale() +
                    ", type=" + info.getType() +
                    '}');
        }
        System.out.println("Record:"+result.getRecords());
        List<LbRecord> records = result.getRecords();
        for (int i = 0; i < records.size(); i++) {
            System.out.println("record.size:"+records.get(i).getSize());
            System.out.println("record.string:"+records.get(i).getStrings());
            System.out.println("record.values:"+records.get(i).getValues());
        }
    }

    /**
     * 测试用户数据不同类型的ValueOption
     */
    @Test
    public void tesGetUserInfoData(){
        LbParameter parameter = new LbParameter();
        parameter.setName("Ptype");
        parameter.setValue("1");
        List<LbParameter> params = new ArrayList<LbParameter>();
        params.add(parameter);
        QueryOption option = new QueryOption();
        option.setBatchNo(1);
        option.setBatchSize(1);
        option.setQueryCount(true);
       // option.setValueOption(ValueOption.VALUE);   getValues String
       // option.setValueOption(ValueOption.DISPLAY); getValues String
       // option.setValueOption(ValueOption.BOTH);    getValues JSON
        QueryResult result = LBE_BUSINESS_SERVICE.query(LOGIN_RESULT.getSessionId(),"WS_DS_CXJCXX",params,"",option);
        System.out.println("Result:"+result.getResult());
        System.out.println("Message:"+result.getMessage());
        System.out.println("HasMore:"+result.isHasMore());
        System.out.println("Count:"+result.getCount());
        System.out.println("CountId"+result.getQueryId());
        System.out.println("MetaData:"+result.getMetaData());
        System.out.println("MetaData.ColumnCount"+result.getMetaData().getColumnCount());
        System.out.println("MetaData.ColInfo:"+result.getMetaData().getColInfo());
        List<ColInfo> colInfos = result.getMetaData().getColInfo();
        for (int i = 0; i < colInfos.size(); i++) {
            ColInfo info = colInfos.get(i);
            System.out.println("ColInfo{" +
                    "label='" + info.getLabel() + '\'' + PinyinTools.cn2FirstSpell(info.getLabel()) +
                    ", length=" + info.getLength() +
                    ", name='" + info.getName() + '\'' +
                    ", scale=" + info.getScale() +
                    ", type=" + info.getType() +
                    '}');
        }
        System.out.println("Record:"+result.getRecords());
        List<LbRecord> records = result.getRecords();
        for (int i = 0; i < records.size(); i++) {
            System.out.println("record.size:"+records.get(i).getSize());
            System.out.println("record.string:"+records.get(i).getStrings());
            System.out.println("record.values:"+records.get(i).getValues());
        }
    }

    @Test
    public void getDatainfoSql(){
        LBEBusinessService lbeBusinessService = PmisWSUtil.createLBEBusinessService();
        LoginResult loginResult = PmisWSUtil.createLoginResult();
        List<LbParameter> params = PmisWSUtil.createLbParameter(Constants.PmisWSConstants.WS_SERVICE_TYPE_USER);
        QueryOption queryOption  = PmisWSUtil.createFirstCountValueOption();
        QueryResult result = lbeBusinessService.query(loginResult.getSessionId(),Constants.PmisWSConstants.WS_SERVICE_OBJECT_NAME,
                                 params,"",queryOption);
        int total = result.getCount();
        int pageSize = 100;
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

        System.out.println(total);
        System.out.println(page);
        System.out.println(lastPageSize);
        for ( int i = 1;i<= page;i++){
            if( (i == page) && pageEnd ){
                queryOption = PmisWSUtil.createQueryValueOption(i,lastPageSize);
                result = lbeBusinessService.query(loginResult.getSessionId(),Constants.PmisWSConstants.WS_SERVICE_OBJECT_NAME,
                        params,"",queryOption);
                resolveWSResult(result);
            }else{
                queryOption = PmisWSUtil.createQueryValueOption(i);
                result = lbeBusinessService.query(loginResult.getSessionId(),Constants.PmisWSConstants.WS_SERVICE_OBJECT_NAME,
                        params,"",queryOption);
                resolveWSResult(result);
            }
        }
    }

    private void resolveWSResult(QueryResult result) {
        int resultNum = result.getRecords().size();
        List<ColInfo> colInfos = result.getMetaData().getColInfo();
        List<LbRecord> recordList = result.getRecords();
        for (int i = 0; i < recordList.size(); i++) {
            resolveWSRecord(colInfos,recordList.get(i));
        }
    }

    private void resolveWSRecord(List<ColInfo> colInfos, LbRecord record) {
        List<Object>  values = record.getValues();
        SysUserInfo userInfo = new SysUserInfo();
        userInfo.setId(Long.valueOf(values.get(0).toString()));
        userInfo.setStatus(1);
        userInfo.setYhmc(values.get(1).toString());
        userInfo.setUserid(values.get(2).toString());
        userInfo.setPassword(values.get(3).toString());
        userInfo.setName(values.get(1).toString() + values.get(2).toString());
        userInfo.setUserType("1");
        userInfo.setSsgs(values.get(4).toString());
        userInfo.setYhsjgh(values.get(5).toString());
        System.out.println(userInfo);

    }
}
