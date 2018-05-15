package cn.com.winning.ssgj.ws;

import cn.com.winning.ssgj.service.SysOrgExtService;
import cn.com.winning.ssgj.service.SysOrganizationService;
import cn.com.winning.ssgj.service.impl.SysOrgExtServiceImpl;
import cn.com.winning.ssgj.service.impl.SysOrganizationServiceImpl;
import cn.com.winning.ssgj.ws.service.PmisWebServiceClient;
import org.junit.Test;

/**
 * @author chenshijie
 * @title
 * @email chensj@winning.com.cm
 * @package cn.com.winning.ssgj.ws
 * @date 2018-05-14 18:16
 */
public class PmisDataImportTestClient {

    @Test
    public void batchImportData(){
        PmisWebServiceClient.insertData();
//        SysOrgExtService service = new SysOrgExtServiceImpl();
//        service.callProcedure();
    }
}
