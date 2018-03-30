package cn.com.winning.ssgj.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @author ChenKuai
 * @create 2018-03-30 下午 3:34
 **/
public class EtSiteInstallDetailForm implements Serializable {

    private List<EtSiteInstallDetail> etSiteInstallDetails;

    public EtSiteInstallDetailForm(List<EtSiteInstallDetail> etSiteInstallDetails) {
        super();
        this.etSiteInstallDetails = etSiteInstallDetails;
    }

    public EtSiteInstallDetailForm() {
        super();
    }

    public List<EtSiteInstallDetail> getEtSiteInstallDetails() {
        return etSiteInstallDetails;
    }

    public void setEtSiteInstallDetails(List<EtSiteInstallDetail> etSiteInstallDetails) {
        this.etSiteInstallDetails = etSiteInstallDetails;
    }


}
