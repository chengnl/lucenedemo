package com.vrv.im;
/**
 *@author chengnl
 *@date 2015年3月24日 下午6:30:28
 *@version 1.0
 *@Description:
 */
public class Org {
    private long orgID;
    private String orgName;
    private String description;
    
    public Org(long orgID,String orgName,String description) {
		this.orgID=orgID;
		this.orgName=orgName;
		this.description=description;
	}
	public long getOrgID() {
		return orgID;
	}
	public void setOrgID(long orgID) {
		this.orgID = orgID;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}  
}
