package cn.com.winning.ssgj.ws.job;

import cn.com.winning.ssgj.ws.service.PmisWebServiceClient;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

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
public class ImportPmisDataJob extends QuartzJobBean {

    @Autowired
    private PmisWebServiceClient pmisWebServiceClient;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        pmisWebServiceClient.importPmisData();
    }

    public PmisWebServiceClient getPmisWebServiceClient() {
        return pmisWebServiceClient;
    }

    public void setPmisWebServiceClient(PmisWebServiceClient pmisWebServiceClient) {
        this.pmisWebServiceClient = pmisWebServiceClient;
    }
}
