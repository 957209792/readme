/***********************************************************************
 * Module:  PaymentRecord.java
 * Author:  VIC
 * Purpose: Defines the Class PaymentRecord
 ***********************************************************************/
package org.mail.entity;
import java.util.*;

/** 支付记录 */
public class PaymentRecord {
   public double prId;
   /** 支付方式 */
   public java.lang.String paymentMethod;
   /** 支付状态 */
   public java.lang.String paymentState;
   /** 支付时间 */
   public java.util.Date paymentTime;
   /** 支付总金额 */
   public float paymentTotal;
   /** 商品数量 */
   public double paymentNumber;
   /** 支付其他 */
   public java.lang.String paymentOther;
   /** 状态 */
   public java.lang.String state;
   /** 订单ID */
   public double oId;
   /** 创建时间 */
   public java.util.Date createTime;

}