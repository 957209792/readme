/***********************************************************************
 * Module:  Order.java
 * Author:  VIC
 * Purpose: Defines the Class Order
 ***********************************************************************/
package org.mail.entity;
import java.util.*;

/** 订单 */
public class Order {
   /** 订单ID */
   public double oId;
   public float oPrice;
   public java.util.Date createTime;
   public java.lang.String oState;
   /** 用户ID */
   public double uId;
   /** 订单号:系统生成 */
   public java.lang.String oNumber;

}