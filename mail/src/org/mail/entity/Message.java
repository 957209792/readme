/***********************************************************************
 * Module:  Message.java
 * Author:  VIC
 * Purpose: Defines the Class Message
 ***********************************************************************/
package org.mail.entity;
import java.util.*;

/** 注册短信，订单短信，支付短信，验证码短信 */
public class Message {
   public double mId;
   public java.lang.String mTitle;
   public java.lang.String mContent;
   public double mPhone;
   public java.util.Date mSendTime;
   public java.lang.String mSendState;
   public java.lang.String mType;
   public java.util.Date createTime;
   public java.lang.String creator;
   public java.lang.String state;

}