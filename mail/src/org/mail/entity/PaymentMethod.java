/***********************************************************************
 * Module:  PaymentMethod.java
 * Author:  VIC
 * Purpose: Defines the Class PaymentMethod
 ***********************************************************************/
package org.mail.entity;
import java.util.*;

/** 支付方式，支付平台，支付渠道 */
public class PaymentMethod {
   /** ID */
   public double pmId;
   /** 支付方式名称 */
   public java.lang.String pmName;
   /** 支付KEY */
   public java.lang.String pmKey;
   /** 支付通知URL */
   public java.lang.String pmNotice;
   /** 支付URL */
   public java.lang.String pmUrl;
   /** 支付Token */
   public java.lang.String pmToken;
   /** 支付其他参数 */
   public java.lang.String pmOther;
   /** 状态 */
   public java.lang.String state;
   /** 创建时间 */
   public java.util.Date createTime;
   /** 创建人 */
   public java.lang.String creator;
   /** 更新时间 */
   public java.util.Date updateTime;
   /** 更新人 */
   public java.lang.String updateCreator;

}