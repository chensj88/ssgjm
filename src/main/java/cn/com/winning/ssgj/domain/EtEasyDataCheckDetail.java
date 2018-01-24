package cn.com.winning.ssgj.domain;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;
import cn.com.winning.ssgj.domain.BaseDomain;

/**
 *
 *
 * @author SSGJ
 * @date 2018-01-18 10:11:45
 */

@Alias("etEasyDataCheckDetail")
public class EtEasyDataCheckDetail extends BaseDomain implements Serializable {

    private static final long serialVersionUID = -1L;

    private Long id;

    private Long sourceId;

    private String deptDoctorCode;

    private String deptDoctorName;

    private Integer num;

    private String remark;

    public EtEasyDataCheckDetail() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSourceId() {
        return sourceId;
    }

    public void setSourceId(Long sourceId) {
        this.sourceId = sourceId;
    }

    public String getDeptDoctorCode() {
        return deptDoctorCode;
    }

    public void setDeptDoctorCode(String deptDoctorCode) {
        this.deptDoctorCode = deptDoctorCode;
    }

    public String getDeptDoctorName() {
        return deptDoctorName;
    }

    public void setDeptDoctorName(String deptDoctorName) {
        this.deptDoctorName = deptDoctorName;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}