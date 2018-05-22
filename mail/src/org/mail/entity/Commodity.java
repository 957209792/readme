/***********************************************************************
 * Module:  Commodity.java
 * Author:  VIC
 * Purpose: Defines the Class Commodity
 ***********************************************************************/
package org.mail.entity;
import java.util.*;

/** 商品 */
public class Commodity {
   /** 商品ID */
   public double cId;
   /** 商品名称 */
   public java.lang.String cName;
   /** 商品地址 */
   public java.lang.String cUrl;
   /** 商品描述 */
   public java.lang.String cDescribe;
   /** 商品关键字 */
   public java.lang.String cKeywords;
   /** 商品价格 */
   public java.lang.String cPrice;
   /** 商品图片: 图片存储的相对路径或绝对路径 */
   public float cPic;
   /** 商品状态: 新建/上架/下架/删除 */
   public java.lang.String cState;
   /** 创建时间(当前系统时间) */
   public java.util.Date createTime;
   /** 创建人(管理员账号) */
   public java.lang.String creator;
   /** 更新时间(当前系统时间) */
   public java.util.Date updateTime;
   /** 更新人(管理员账号) */
   public java.lang.String updateCreator;

}