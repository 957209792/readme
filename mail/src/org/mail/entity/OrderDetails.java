/***********************************************************************
 * Module:  OrderDetails.java
 * Author:  VIC
 * Purpose: Defines the Class OrderDetails
 ***********************************************************************/
package org.mail.entity;
import java.util.*;

/** 订单详情 */
public class OrderDetails {
   /** 订单详情ID */
   public double odId;
   /** 商品ID */
   public double cId;
   /** 商品数量 */
   public double cNumber;
   /** 商品单价 */
   public float cPrice;
   /** 状态 */
   public java.lang.String state;
   /** 订单ID */
   public double oId;
   /** 创建时间 */
   public java.util.Date createTime;

}