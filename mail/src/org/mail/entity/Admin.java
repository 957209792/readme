package org.mail.entity;
/***********************************************************************
 * Module:  Admin.java
 * Author:  VIC
 * Purpose: Defines the Class Admin
 ***********************************************************************/

import java.util.*;

/** 后台管理员 */
public class Admin {
   public int aId;
   /** 管理员登录账号 */
   public java.lang.String aAccount;
   /** 管理员登录密码 */
   public java.lang.String aPassword;
   /** 管理员邮箱 */
   public java.util.Date aEamil;
   /** 管理员手机 */
   public double aPhone;
   /** 管理员头像 */
   public java.util.Date aHead;
   /** 创建时间 */
   public java.util.Date createTime;
   /** 创建人默认为系统) */
   public java.lang.String creator;
   /** 更新时间 */
   public java.util.Date updateTime;
   /** 更新时间 */
   public java.util.Date updateCreator;
   /** 状态 */
   public java.lang.String state;
public int getaId() {
	return aId;
}
public void setaId(int aId) {
	this.aId = aId;
}
public java.lang.String getaAccount() {
	return aAccount;
}
public void setaAccount(java.lang.String aAccount) {
	this.aAccount = aAccount;
}
public java.lang.String getaPassword() {
	return aPassword;
}
public void setaPassword(java.lang.String aPassword) {
	this.aPassword = aPassword;
}
public java.util.Date getaEamil() {
	return aEamil;
}
public void setaEamil(java.util.Date aEamil) {
	this.aEamil = aEamil;
}
public double getaPhone() {
	return aPhone;
}
public void setaPhone(double aPhone) {
	this.aPhone = aPhone;
}
public java.util.Date getaHead() {
	return aHead;
}
public void setaHead(java.util.Date aHead) {
	this.aHead = aHead;
}
public java.util.Date getCreateTime() {
	return createTime;
}
public void setCreateTime(java.util.Date createTime) {
	this.createTime = createTime;
}
public java.lang.String getCreator() {
	return creator;
}
public void setCreator(java.lang.String creator) {
	this.creator = creator;
}
public java.util.Date getUpdateTime() {
	return updateTime;
}
public void setUpdateTime(java.util.Date updateTime) {
	this.updateTime = updateTime;
}
public java.util.Date getUpdateCreator() {
	return updateCreator;
}
public void setUpdateCreator(java.util.Date updateCreator) {
	this.updateCreator = updateCreator;
}
public java.lang.String getState() {
	return state;
}
public void setState(java.lang.String state) {
	this.state = state;
}
   

}