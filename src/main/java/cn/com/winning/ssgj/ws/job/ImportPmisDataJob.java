package cn.com.winning.ssgj.ws.job;

import cn.com.winning.ssgj.service.SysOrgExtService;
import cn.com.winning.ssgj.ws.service.PmisWebServiceClient;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author chenshijie
 * @title
 * @email chensj@winning.com.cm
 * @package cn.com.winning.ssgj.ws
 * @date 2018-03-07 8:52
 * @description 由于Spring提供对Quartz的支持，所以直接使用Spring提供的API
 *  继承  org.springframework.scheduling.quartz.QuartzJobBean
 *
 */
public class ImportPmisDataJob {

    private static final Logger logger = LoggerFactory.getLogger(ImportPmisDataJob.class);
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Autowired
    private PmisWebServiceClient pmisWebServiceClient;
    @Autowired
    private SysOrgExtService sysOrgExtService;

    public void execute() throws JobExecutionException {
        logger.info("开始导入PMIS数据，开始时间为[{}]",sdf.format(new Date()));
       /* pmisWebServiceClient.importPmisData();*/
        logger.info("PMIS数据导入结束，结束时间为[{}]",sdf.format(new Date()));
        logger.info("开始生成机构全路径数据，开始时间为[{}]",sdf.format(new Date()));
        /*sysOrgExtService.callProcedure();*/
        logger.info("机构全路径数据导入结束，结束时间为[{}]",sdf.format(new Date()));
    }


    public PmisWebServiceClient getPmisWebServiceClient() {
        return pmisWebServiceClient;
    }

    public void setPmisWebServiceClient(PmisWebServiceClient pmisWebServiceClient) {
        this.pmisWebServiceClient = pmisWebServiceClient;
    }
}
