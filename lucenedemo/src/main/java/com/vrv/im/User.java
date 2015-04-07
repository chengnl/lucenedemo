package com.vrv.im;
/**
 *@author chengnl
 *@date 2015年3月24日 下午6:32:59
 *@version 1.0
 *@Description:TODO类描述
 */
public class User {
   private long userID;
   private long orgID;
   private String userName;
   private String userNamePY;
   private String description;
   public User(long userID,long orgID,String userName,
		   String userNamePY,String description) {
	this.userID=userID;
	this.orgID=orgID;
	this.userName=userName;
	this.userNamePY=userNamePY;
	this.description=description;
}
public long getUserID() {
	return userID;
}
public void setUserID(long userID) {
	this.userID = userID;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public long getOrgID() {
	return orgID;
}
public void setOrgID(long orgID) {
	this.orgID = orgID;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public String getUserNamePY() {
	return userNamePY;
}
public void setUserNamePY(String userNamePY) {
	this.userNamePY = userNamePY;
}
   
}
