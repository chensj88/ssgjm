package cn.com.winning.ssgj.domain;

import java.io.Serializable;
import org.apache.ibatis.type.Alias;
import cn.com.winning.ssgj.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator
 * @date 2018-03-23 15:16:59
 */
 
@Alias("sysFloors")
public class SysFloors extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;
	
	private Long serialNo;
	
	private String serialName;
	
	private String floorCode;
	
	private String floorName;
	
	private Integer isDel;
	
	public SysFloors() {

	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getSerialNo() {
		return serialNo;
	}
	
	public void setSerialNo(Long serialNo) {
		this.serialNo = serialNo;
	}
	
	public String getSerialName() {
		return serialName;
	}
	
	public void setSerialName(String serialName) {
		this.serialName = serialName;
	}
	
	public String getFloorCode() {
		return floorCode;
	}
	
	public void setFloorCode(String floorCode) {
		this.floorCode = floorCode;
	}
	
	public String getFloorName() {
		return floorName;
	}
	
	public void setFloorName(String floorName) {
		this.floorName = floorName;
	}
	
	public Integer getIsDel() {
		return isDel;
	}
	
	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}
	
}