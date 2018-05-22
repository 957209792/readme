/***********************************************************************
 * Module:  Email.java
 * Author:  VIC
 * Purpose: Defines the Class Email
 ***********************************************************************/
package org.mail.entity;

import java.util.*;

/** 系统邮件，注册邮件，订单邮件 */
public class Email {
	
	public static final String REGISTER = "REGISTER";
	
	public double eId;
	public java.lang.String eTitle;
	public java.lang.String eContent;
	public java.lang.String eEmail;
	public java.util.Date eSendTime;
	public java.util.Date createTime;
	public java.lang.String creator;
	public java.lang.String eSendState;
	public java.lang.String state;
	public java.lang.String eType;

	public double geteId() {
		return eId;
	}

	public void seteId(double eId) {
		this.eId = eId;
	}

	public java.lang.String geteTitle() {
		return eTitle;
	}

	public void seteTitle(java.lang.String eTitle) {
		this.eTitle = eTitle;
	}

	public java.lang.String geteContent() {
		return eContent;
	}

	public void seteContent(java.lang.String eContent) {
		this.eContent = eContent;
	}

	public java.lang.String geteEmail() {
		return eEmail;
	}

	public void seteEmail(java.lang.String eEmail) {
		this.eEmail = eEmail;
	}

	public java.util.Date geteSendTime() {
		return eSendTime;
	}

	public void seteSendTime(java.util.Date eSendTime) {
		this.eSendTime = eSendTime;
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

	public java.lang.String geteSendState() {
		return eSendState;
	}

	public void seteSendState(java.lang.String eSendState) {
		this.eSendState = eSendState;
	}

	public java.lang.String getState() {
		return state;
	}

	public void setState(java.lang.String state) {
		this.state = state;
	}

	public java.lang.String geteType() {
		return eType;
	}

	public void seteType(java.lang.String eType) {
		this.eType = eType;
	}

}