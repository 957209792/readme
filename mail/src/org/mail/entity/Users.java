/***********************************************************************
 * Module:  Users.java
 * Author:  VIC
 * Purpose: Defines the Class Users
 ***********************************************************************/
package org.mail.entity;

import java.util.Date;

/** 用户 */
public class Users {
	
	
	public static final String DISABLED  = "DISABLED";
	public static final String ENABLED  = "ENABLED";
	
	private Integer USERID;
	   /** 用户名
	    * 
	    * @pdOid 1f31398c-a5cf-415d-bf06-510f78ed430c */
	   private String UNAME;
	   /** 用户登录账号
	    * 
	    * @pdOid 695fa068-1971-4cc1-9eb3-1700e7916130 */
	   private String UACCOUNT;
	   /** 用户登录密码
	    * 
	    * @pdOid 337d2eef-4aad-4907-8c65-661c6ae2afc7 */
	   private String UPASSWORD;
	   /** 用户性别
	    * 
	    * @pdOid 80a68f0c-2b4b-4558-a852-b5a385196bd2 */
	   private String USEX;
	   /** 用户年龄
	    * 
	    * @pdOid 8c756530-05a5-406b-a1ed-3f3267c29093 */
	   private Integer UAGE;
	   /** 用户头像
	    * 
	    * @pdOid 1ec337d9-8b9e-4877-9199-338288dd9664 */
	   private String UHEAD;
	   /** 用户邮箱
	    * 
	    * @pdOid 4b980e35-14bd-4cd8-949d-b4415e85c04b */
	   private String UEMAIL;
	   /** 用户手机
	    * 
	    * @pdOid f430dcbd-6d52-4833-a3c2-4222ad8b18c1 */
	   private String UPHONE;
	   /** 用户状态
	    * 
	    * @pdOid e1646710-9f1d-444a-a73a-c8770b951089 */
	   private String STATE;
	   /** 注册时间
	    * 
	    * @pdOid a4495eab-830b-4321-b5d1-3b8f6d8a2e0b */
	   private Date CREATETIME;
	
	   
	public Integer getUSERID() {
		return USERID;
	}


	public void setUSERID(Integer uSERID) {
		USERID = uSERID;
	}


	public String getUNAME() {
		return UNAME;
	}


	public void setUNAME(String uNAME) {
		UNAME = uNAME;
	}


	public String getUACCOUNT() {
		return UACCOUNT;
	}


	public void setUACCOUNT(String uACCOUNT) {
		UACCOUNT = uACCOUNT;
	}


	public String getUPASSWORD() {
		return UPASSWORD;
	}


	public void setUPASSWORD(String uPASSWORD) {
		UPASSWORD = uPASSWORD;
	}


	public String getUSEX() {
		return USEX;
	}


	public void setUSEX(String uSEX) {
		USEX = uSEX;
	}


	public Integer getUAGE() {
		return UAGE;
	}


	public void setUAGE(Integer uAGE) {
		UAGE = uAGE;
	}


	public String getUHEAD() {
		return UHEAD;
	}


	public void setUHEAD(String uHEAD) {
		UHEAD = uHEAD;
	}


	public String getUEMAIL() {
		return UEMAIL;
	}


	public void setUEMAIL(String uEMAIL) {
		UEMAIL = uEMAIL;
	}


	public String getUPHONE() {
		return UPHONE;
	}


	public void setUPHONE(String uPHONE) {
		UPHONE = uPHONE;
	}


	public String getSTATE() {
		return STATE;
	}


	public void setSTATE(String sTATE) {
		STATE = sTATE;
	}


	public Date getCREATETIME() {
		return CREATETIME;
	}


	public void setCREATETIME(Date cREATETIME) {
		CREATETIME = cREATETIME;
	}


	public static String getDisabled() {
		return DISABLED;
	}


	public static String getEnabled() {
		return ENABLED;
	}


	@Override
	public String toString() {
		return "Users [USERID=" + USERID + ", UNAME=" + UNAME + ", UACCOUNT=" + UACCOUNT + ", UPASSWORD=" + UPASSWORD
				+ ", USEX=" + USEX + ", UAGE=" + UAGE + ", UHEAD=" + UHEAD + ", UEMAIL=" + UEMAIL + ", UPHONE=" + UPHONE
				+ ", STATE=" + STATE + ", CREATETIME=" + CREATETIME + "]";
	}
   
}