package cn.com.winning.ssgj.ws;

import cn.com.winning.ssgj.base.Constants;
import cn.com.winning.ssgj.base.exception.SSGJException;
import cn.com.winning.ssgj.base.util.ConnectionUtil;
import cn.com.winning.ssgj.base.util.PinyinTools;
import cn.com.winning.ssgj.base.util.PmisWSUtil;
import cn.com.winning.ssgj.domain.PmisCustomerInformation;
import cn.com.winning.ssgj.domain.PmisProjectBasicInfo;
import cn.com.winning.ssgj.domain.SysUserInfo;
import cn.com.winning.ssgj.ws.client.*;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenshijie
 * @title PMIS接口测试
 * @email chensj@winning.com.cm
 * @package cn.com.winning.ssgj.ws.service
 * @date 2018-02-05 11:12
 */
public class PmisWebServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(PmisWebServiceTest.class);
    private static final String url = "http://weberp.winning.com.cn:9080/service/LBEBusiness?wsdl";
    private static final LoginResult LOGIN_RESULT;
    private static final LBEBusinessService LBE_BUSINESS_SERVICE;
    private static final int FIRST_BATCH_SIZE = 1;
    private static final int COMMON_BATCH_SIZE = 100;

    static {
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(LBEBusinessService.class);
        factory.setAddress(url);
        LBE_BUSINESS_SERVICE = factory.create(LBEBusinessService.class);
        LOGIN_RESULT = LBE_BUSINESS_SERVICE.login(Constants.PmisWSConstants.WS_USER,
                Constants.PmisWSConstants.WS_PASSWORD, "",
                Constants.PmisWSConstants.WS_ALGORITHM, "");
    }

    /**
     * 测试登录
     */
    @Test
    public void testLogin() {
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(LBEBusinessService.class);
        factory.setAddress(url);
        LBEBusinessService lbeBusinessService = factory.create(LBEBusinessService.class);
        String userId = "sszhb";
        String password = "abc123";
        LoginResult loginResult = lbeBusinessService.login(userId, password, "", "plain", "");
        System.out.println(loginResult.getResult());
        System.out.println(loginResult.getSessionId());
        System.out.println(loginResult.getMessage());
    }

    @Test
    public void testQueryAreaInfoData() {
        //10  areaCode
        //11  hospitalType
        //12  hospitalLevel
        generateDataSql("SYS_DICT_INFO", "12","hospitalLevel");
    }

    private void generateDataSql(String tableName, String dataType,String dictType) {
        LBEBusinessService lbeBusinessService = PmisWSUtil.createLBEBusinessService();
        LoginResult loginResult = PmisWSUtil.createLoginResult();
        List<LbParameter> params = PmisWSUtil.createLbParameter(dataType);
        QueryOption queryOption = PmisWSUtil.createFirstCountValueOption();
        QueryResult result = lbeBusinessService.query(loginResult.getSessionId(), Constants.PmisWSConstants.WS_SERVICE_OBJECT_NAME,
                params, "", queryOption);
        if (result.getResult() <= 0) {
            return;
        }
        int total = result.getCount();
        int pageSize = 1000;
        int lastPageSize = total % pageSize;
        int page = 0;
        boolean pageEnd = false;
        if (lastPageSize > 0) {
            page = total / pageSize + 1;
            pageEnd = true;
        } else {
            page = total / pageSize;
            pageEnd = false;
        }

        for (int i = 1; i <= page; i++) {
            if ((i == page) && pageEnd) {
                queryOption = PmisWSUtil.createQueryValueOption(i, lastPageSize);
                result = lbeBusinessService.query(loginResult.getSessionId(), Constants.PmisWSConstants.WS_SERVICE_OBJECT_NAME,
                        params, "", queryOption);
                generateDictInfo(result, tableName,dictType);
            } else {
                queryOption = PmisWSUtil.createQueryValueOption(i);
                result = lbeBusinessService.query(loginResult.getSessionId(), Constants.PmisWSConstants.WS_SERVICE_OBJECT_NAME,
                        params, "", queryOption);
                generateDictInfo(result, tableName,dictType);
            }
        }
    }

    private void generateDictInfo(QueryResult result, String tableName,String dictType) {
        StringBuilder sb = new StringBuilder();
        sb.append("insert into " + tableName.toUpperCase() + " values \n");
        List<LbRecord> recordList = result.getRecords();
        List<ColInfo> colInfos = result.getMetaData().getColInfo();
        for (int i = 0; i < recordList.size(); i++) {
            LbRecord record = recordList.get(i);
            List<Object> values = record.getValues();
            for (int j = 0; j < values.size(); j++) {
//                 开始数据为数字
                if (j == 0 && colInfos.get(j).getType() == 1) {
                    sb.append("('" +dictType+"',"+ resolveValue(values.get(j)) + ",");
//              开始数据为字符或者时间
                } else if (j == 0 && (colInfos.get(j).getType() == 0 || colInfos.get(j).getType() == 3)) {
                    sb.append("('" +dictType+"',"+  values.get(j) + "\',");
//              全部结束数据为数字
                } else if ((j == values.size() - 1) && (colInfos.get(j).getType() == 1) &&
                        (i == recordList.size() - 1)) {
                    sb.append(resolveValue(values.get(j)) + ",null,null );  \n");
//              全部结束数据为字符或者时间
                } else if ((j == values.size() - 1) && (colInfos.get(j).getType() == 0 || colInfos.get(j).getType() == 3) &&
                        (i == recordList.size() - 1)) {
                    sb.append("\'" + values.get(j) + "\' ,null,null);  \n");
//              单行结束为数字
                } else if ((j == values.size() - 1) && (colInfos.get(j).getType() == 1)) {
                    sb.append(resolveValue(values.get(j)) + "  ,null,null),  \n");
//              单行结束为字符或者时间
                } else if ((j == values.size() - 1) && (colInfos.get(j).getType() == 0 || colInfos.get(j).getType() == 3)) {
                    sb.append("\'" + values.get(j) + " \',null,null ),\n");
//              字段处理 数字
                } else if (colInfos.get(j).getType() == 1) {
                    sb.append(resolveValue(values.get(j)) + ",");
                } else if (colInfos.get(j).getType() == 0 || colInfos.get(j).getType() == 3) {
//              字段处理 字符或者时间
                    sb.append("\'" + values.get(j) + "\',");
                }
            }
        }
          System.out.println(sb);
        executeSqlInfo(sb.toString());
    }

    /**
     * 测试获取用户信息数据
     */
    @Test
    public void testQueryUserInfoData() {
        LbParameter parameter = new LbParameter();
        parameter.setName("Ptype");
        parameter.setValue("10");
        List<LbParameter> params = new ArrayList<LbParameter>();
        params.add(parameter);
        QueryOption option = new QueryOption();
        option.setBatchNo(1);
        option.setBatchSize(1);
        option.setQueryCount(true);
        option.setValueOption(ValueOption.VALUE);
        QueryResult result = LBE_BUSINESS_SERVICE.query(LOGIN_RESULT.getSessionId(), "WS_DS_CXJCXX", params, "", option);
        System.out.println("Result:" + result.getResult());
        System.out.println("Message:" + result.getMessage());
        System.out.println("HasMore:" + result.isHasMore());
        System.out.println("Count:" + result.getCount());
        System.out.println("CountId" + result.getQueryId());
        System.out.println("MetaData:" + result.getMetaData());
        System.out.println("MetaData.ColumnCount" + result.getMetaData().getColumnCount());
        System.out.println("MetaData.ColInfo:" + result.getMetaData().getColInfo());
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
        System.out.println("Record:" + result.getRecords());
        List<LbRecord> records = result.getRecords();
        for (int i = 0; i < records.size(); i++) {
            System.out.println("record.size:" + records.get(i).getSize());
            System.out.println("record.string:" + records.get(i).getStrings());
            System.out.println("record.values:" + records.get(i).getValues());
        }


    }

    /**
     * 测试用户数据不同类型的ValueOption
     */
    @Test
    public void tesGetUserInfoData() {
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
        QueryResult result = LBE_BUSINESS_SERVICE.query(LOGIN_RESULT.getSessionId(), "WS_DS_CXJCXX", params, "", option);
        System.out.println("Result:" + result.getResult());
        System.out.println("Message:" + result.getMessage());
        System.out.println("HasMore:" + result.isHasMore());
        System.out.println("Count:" + result.getCount());
        System.out.println("CountId" + result.getQueryId());
        System.out.println("MetaData:" + result.getMetaData());
        System.out.println("MetaData.ColumnCount" + result.getMetaData().getColumnCount());
        System.out.println("MetaData.ColInfo:" + result.getMetaData().getColInfo());
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
        System.out.println("Record:" + result.getRecords());
        List<LbRecord> records = result.getRecords();
        for (int i = 0; i < records.size(); i++) {
            System.out.println("record.size:" + records.get(i).getSize());
            System.out.println("record.string:" + records.get(i).getStrings());
            System.out.println("record.values:" + records.get(i).getValues());
        }
    }

    private static final Map<String, String> pmisInfoData;

    static {
        pmisInfoData = new HashMap<String, String>();
        pmisInfoData.put("SYS_USER_INFO", "1"); //用户信息 成功
        pmisInfoData.put("PMIS_CUSTOMER_INFORMATION", "2"); //客户信息库 成功
        pmisInfoData.put("PMIS_PROJECT_BASIC_INFO", "3"); //  项目基本信息 OK
        pmisInfoData.put("PMIS_PROJCT_USER", "4"); // OK  项目成员信息 OK
        pmisInfoData.put("PMIS_CONTRACT_INFO", "5"); // 合同信息 OK
        pmisInfoData.put("PMIS_PRODUCT_INFO", "6");// 产品信息库 OK
        pmisInfoData.put("PMIS_CONTRACT_PRODUCT_INFO", "7"); // 合同产品清单 OK
        pmisInfoData.put("SYS_ORGANIZATION", "8"); //组织机构
        pmisInfoData.put("PMIS_PRODUCT_LINE_INFO", "9"); //产品条线信息
    }

    @Test
    public void insertData() {
        for (String key : pmisInfoData.keySet()) {
            String value = pmisInfoData.get(key);
            generateDymaicSql(key, value);
        }
    }

    @Test
    public void  insertUserInfoData(){
        generateDymaicSql("SYS_USER_INFO", "1");
    }
    private void generateDymaicSql(String tableName, String dataType) {
        String sql = "";
        if(dataType.equals(Constants.PmisWSConstants.WS_SERVICE_QUERY_USER)){
            sql ="DELETE FROM "+tableName + " WHERE USER_TYPE='1';";
        }else{
            sql = "DELETE FROM "+tableName + " ;";
        }
        LOGGER.info("删除表SQL："+sql );
        executeSqlInfo(sql);
        LOGGER.info("删除表"+tableName+"数据结束" );
        LBEBusinessService lbeBusinessService = PmisWSUtil.createLBEBusinessService();
        LoginResult loginResult = PmisWSUtil.createLoginResult();
        List<LbParameter> params = PmisWSUtil.createLbParameter(dataType);
        QueryOption queryOption = PmisWSUtil.createFirstCountValueOption();
        QueryResult result = lbeBusinessService.query(loginResult.getSessionId(), Constants.PmisWSConstants.WS_SERVICE_OBJECT_NAME,
                params, "", queryOption);
        if (result.getResult() <= 0) {
            return;
        }
        int total = result.getCount();
        int pageSize = 1000;
        int lastPageSize = total % pageSize;
        int page = 0;
        boolean pageEnd = false;
        if (lastPageSize > 0) {
            page = total / pageSize + 1;
            pageEnd = true;
        } else {
            page = total / pageSize;
            pageEnd = false;
        }

        for (int i = 1; i <= page; i++) {
            if ((i == page) && pageEnd) {
                queryOption = PmisWSUtil.createQueryValueOption(i, lastPageSize);
                result = lbeBusinessService.query(loginResult.getSessionId(), Constants.PmisWSConstants.WS_SERVICE_OBJECT_NAME,
                        params, "", queryOption);
                resolveWSResult(result, tableName);
            } else {
                queryOption = PmisWSUtil.createQueryValueOption(i);
                result = lbeBusinessService.query(loginResult.getSessionId(), Constants.PmisWSConstants.WS_SERVICE_OBJECT_NAME,
                        params, "", queryOption);
                resolveWSResult(result, tableName);
            }
        }
    }

    @Test
    public void queryDataCount() {
        String tableName = "PMIS_CONTRACT_PRODUCT_INFO";
        String dataType = "7";
        LBEBusinessService lbeBusinessService = PmisWSUtil.createLBEBusinessService();
        LoginResult loginResult = PmisWSUtil.createLoginResult();
        List<LbParameter> params = PmisWSUtil.createLbParameter(dataType);
        QueryOption queryOption = PmisWSUtil.createFirstCountValueOption();
        QueryResult result = lbeBusinessService.query(loginResult.getSessionId(),
                Constants.PmisWSConstants.WS_SERVICE_OBJECT_NAME,
                params, "", queryOption);
        if (result.getResult() <= 0) { //失败
            throw new SSGJException(result.getMessage());
        } else {
            int total = result.getCount();
            System.out.println(total);
            resolveWSDataColInfo(result);
        }
    }

    @Test
    public void getDatainfoSql() {
        String tableName = "PMIS_PRODUCT_LINE_INFO";
        String dataType = "9";
        LBEBusinessService lbeBusinessService = PmisWSUtil.createLBEBusinessService();
        LoginResult loginResult = PmisWSUtil.createLoginResult();
        List<LbParameter> params = PmisWSUtil.createLbParameter(dataType);
        QueryOption queryOption = PmisWSUtil.createFirstCountValueOption();
        QueryResult result = lbeBusinessService.query(loginResult.getSessionId(),
                Constants.PmisWSConstants.WS_SERVICE_OBJECT_NAME,
                params, "", queryOption);
        if (result.getResult() <= 0) { //失败
            System.out.println(result.getMessage());
            return;
        } else {
            int total = result.getCount();
            int pageSize = Constants.PmisWSConstants.QUERY_BATCH_SIZE;
            int lastPageSize = total % pageSize;
            int page = 0;
            boolean pageEnd = false;
            if (lastPageSize > 0) {
                page = total / pageSize + 1;
                pageEnd = true;
            } else {
                page = total / pageSize;
                pageEnd = false;
            }
            resolveWSDataColInfo(result);
            System.out.println(total);
            System.out.println(page);
            System.out.println(lastPageSize);
            System.out.println(result.getMetaData().getColumnCount());
            System.out.println(result.getRecords().size());
            if (result.getRecords().size() > 0) {
                System.out.println(result.getRecords().get(0).getValues());
                System.out.println(result.getRecords().get(0).getValues().size());
                for (int i = 1; i <= page; i++) {
                    if ((i == page) && pageEnd) {
                        queryOption = PmisWSUtil.createQueryValueOption(i, lastPageSize);
                        result = lbeBusinessService.query(loginResult.getSessionId(), Constants.PmisWSConstants.WS_SERVICE_OBJECT_NAME,
                                params, "", queryOption);
                        resolveWSResult(result, tableName);
                    } else {
                        queryOption = PmisWSUtil.createQueryValueOption(i);
                        result = lbeBusinessService.query(loginResult.getSessionId(), Constants.PmisWSConstants.WS_SERVICE_OBJECT_NAME,
                                params, "", queryOption);
                        resolveWSResult(result, tableName);
                    }
                }
            }
        }
    }

    /**
     * 解析传输数据结果
     *
     * @param result
     * @param tableName
     */
    private void resolveWSResult(QueryResult result, String tableName) {
        if (tableName.toUpperCase().equals("SYS_USER_INFO")) {
            resolveUserInfoData(result);
        } else {
            generateSQLInfo(result, tableName);
        }
    }

    /**
     * 获取Col信息
     *
     * @param result
     */
    private void resolveWSDataColInfo(QueryResult result) {
        List<ColInfo> colInfos = result.getMetaData().getColInfo();
        StringBuilder sb = new StringBuilder();
        for (ColInfo colInfo : colInfos) {
            sb.append("ColInfo [ label:" + colInfo.getLabel() + "  name:" + colInfo.getName() + "  length:" + colInfo.getLength() + "  type:" + colInfo.getType() + " ] \n");
        }
        System.out.println(sb);
    }


    private void generateSQLInfo(QueryResult result, String tableName) {
        StringBuilder sb = new StringBuilder();
        sb.append("insert into " + tableName.toUpperCase() + " values \n");
        List<LbRecord> recordList = result.getRecords();
        List<ColInfo> colInfos = result.getMetaData().getColInfo();
        for (int i = 0; i < recordList.size(); i++) {
            LbRecord record = recordList.get(i);
            List<Object> values = record.getValues();
            for (int j = 0; j < values.size(); j++) {
//                 开始数据为数字
                if (j == 0 && colInfos.get(j).getType() == 1) {
                    sb.append("(" + resolveValue(values.get(j)) + ",");
//              开始数据为字符或者时间
                } else if (j == 0 && (colInfos.get(j).getType() == 0 || colInfos.get(j).getType() == 3)) {
                    sb.append("(\'" + values.get(j) + "\',");
//              全部结束数据为数字
                } else if ((j == values.size() - 1) && (colInfos.get(j).getType() == 1) &&
                        (i == recordList.size() - 1)) {
                    sb.append(resolveValue(values.get(j)) + " );  \n");
//              全部结束数据为字符或者时间
                } else if ((j == values.size() - 1) && (colInfos.get(j).getType() == 0 || colInfos.get(j).getType() == 3) &&
                        (i == recordList.size() - 1)) {
                    sb.append("\'" + values.get(j) + "\' );  \n");
//              单行结束为数字
                } else if ((j == values.size() - 1) && (colInfos.get(j).getType() == 1)) {
                    sb.append(resolveValue(values.get(j)) + "  ),  \n");
//              单行结束为字符或者时间
                } else if ((j == values.size() - 1) && (colInfos.get(j).getType() == 0 || colInfos.get(j).getType() == 3)) {
                    sb.append("\'" + values.get(j) + " \' ),");
//              字段处理 数字
                } else if (colInfos.get(j).getType() == 1) {
                    sb.append(resolveValue(values.get(j)) + ",");
                } else if (colInfos.get(j).getType() == 0 || colInfos.get(j).getType() == 3) {
//              字段处理 字符或者时间
                    sb.append("\'" + values.get(j) + "\',");
                }
            }
        }
//        /*  System.out.println(sb);*/
        executeSqlInfo(sb.toString());
    }

    /**
     * 转换接口中存在数字为空的数据
     *
     * @param o
     * @return
     */
    private Object resolveValue(Object o) {
        return o.toString().equals("") ? 0 : o;
    }

    private void resolveUserInfoData(QueryResult result) {
        StringBuilder sb = new StringBuilder();
        sb.append("insert into SYS_USER_INFO values \n");
        List<LbRecord> recordList = result.getRecords();
        List<ColInfo> colInfos = result.getMetaData().getColInfo();
        for (int i = 0; i < recordList.size(); i++) {
            LbRecord record = recordList.get(i);
            List<Object> values = record.getValues();
            for (int j = 0; j < values.size(); j++) {
                if (j == 0 && colInfos.get(j).getType() == 1) {
                    sb.append("(" + values.get(j) + ",");
                } else if ((j == values.size() - 1) && (colInfos.get(j).getType() == 1) &&
                        (i == recordList.size() - 1)) {
                    sb.append(values.get(j) + ",\'1\',-1,null,null,null,null,null,null,null );  \n");
                } else if ((j == values.size() - 1) && (colInfos.get(j).getType() == 1)) {
                    sb.append(values.get(j) + ",\'1\',-1,null,null,null,null,null,null,null ),  \n");
                } else if (j == 0 && colInfos.get(j).getType() == 0) {
                    sb.append("(\'" + values.get(j) + "\',");
                } else if ((j == values.size() - 1) && (colInfos.get(j).getType() == 0)) {
                    sb.append("\'" + values.get(j) + " \' ),");
                } else if (colInfos.get(j).getType() == 1) {
                    sb.append(values.get(j) + ",");
                } else if (colInfos.get(j).getType() == 0) {
                    sb.append("\'" + values.get(j) + "\',");
                }
            }
        }
//        System.out.println(sb);
        executeSqlInfo(sb.toString());
    }

    private void executeSqlInfo(String sql) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionUtil.getConnection();
            connection.setAutoCommit(true);
            ps = connection.prepareStatement(sql);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionUtil.closeAll(connection, ps, rs);
        }
    }
}
