package cn.com.winning.ssgj.domain;

import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ChenKuai
 * @create 2018-03-30 下午 3:34
 **/
public class EtSiteInstallDetailForm implements Serializable {
    private ArrayList<Long> id;
   private ArrayList<String> siteName;
    private ArrayList<String> ip;
    private ArrayList<String> building;
    private ArrayList<String> floorNum;
    private ArrayList<String> pcModel;

    private ArrayList<EtSiteInstallDetail> etSiteInstallDetails;

    public ArrayList<Long> getId() { return id; }

    public void setId(ArrayList<Long> id) { this.id = id; }

    public ArrayList<String> getSiteName() {
        return siteName;
    }

    public void setSiteName(ArrayList<String> siteName) {
        this.siteName = siteName;
    }

    public ArrayList<String> getIp() {
        return ip;
    }

    public void setIp(ArrayList<String> ip) {
        this.ip = ip;
    }

    public ArrayList<String> getBuilding() {
        return building;
    }

    public void setBuilding(ArrayList<String> building) {
        this.building = building;
    }

    public ArrayList<String> getFloorNum() {
        return floorNum;
    }

    public void setFloorNum(ArrayList<String> floorNum) {
        this.floorNum = floorNum;
    }

    public ArrayList<String> getPcModel() {
        return pcModel;
    }

    public void setPcModel(ArrayList<String> pcModel) {
        this.pcModel = pcModel;
    }

    public ArrayList<EtSiteInstallDetail> getEtSiteInstallDetails() {
        int size = pcModel.size();
        ArrayList<EtSiteInstallDetail> etSiteInstallDetailList = new ArrayList<EtSiteInstallDetail>();
        for(int i=0;i<size;i++){
            EtSiteInstallDetail detail = new EtSiteInstallDetail();
            detail.setId(id.get(i));
            detail.setIp(ip.get(i));
            detail.setSiteName(siteName.get(i));
            detail.setBuilding(building.get(i));
            if(StringUtils.isBlank(floorNum.get(i))) {
                detail.setFloorNum(0);
            }else{
                detail.setFloorNum(Integer.parseInt(floorNum.get(i)));
            }
            detail.setPcModel(pcModel.get(i));
            etSiteInstallDetailList.add(detail);
        }
        return etSiteInstallDetailList;
    }

    public void setEtSiteInstallDetails(ArrayList<EtSiteInstallDetail> etSiteInstallDetails) {
        this.etSiteInstallDetails = etSiteInstallDetails;
    }
}
