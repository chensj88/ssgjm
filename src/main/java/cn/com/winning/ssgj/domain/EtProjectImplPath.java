package cn.com.winning.ssgj.domain;

import java.io.Serializable;
import org.apache.ibatis.type.Alias;
import cn.com.winning.ssgj.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-04-04 09:10:32
 */
 
@Alias("etProjectImplPath")
public class EtProjectImplPath extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private String milepost;
	
	private String mainStep;
	
	private String stepType;
	
	private String stepContent;
	
	private String stepDesc;
	
	private String stepOutput;
	
	private String remark;
	
	public EtProjectImplPath() {

	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getMilepost() {
		return milepost;
	}
	
	public void setMilepost(String milepost) {
		this.milepost = milepost;
	}
	
	public String getMainStep() {
		return mainStep;
	}
	
	public void setMainStep(String mainStep) {
		this.mainStep = mainStep;
	}
	
	public String getStepType() {
		return stepType;
	}
	
	public void setStepType(String stepType) {
		this.stepType = stepType;
	}
	
	public String getStepContent() {
		return stepContent;
	}
	
	public void setStepContent(String stepContent) {
		this.stepContent = stepContent;
	}
	
	public String getStepDesc() {
		return stepDesc;
	}
	
	public void setStepDesc(String stepDesc) {
		this.stepDesc = stepDesc;
	}
	
	public String getStepOutput() {
		return stepOutput;
	}
	
	public void setStepOutput(String stepOutput) {
		this.stepOutput = stepOutput;
	}
	
	public String getRemark() {
		return remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}