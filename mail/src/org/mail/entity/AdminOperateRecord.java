/***********************************************************************
 * Module:  AdminOperateRecord.java
 * Author:  VIC
 * Purpose: Defines the Class AdminOperateRecord
 ***********************************************************************/
package org.mail.entity;
import java.util.*;

/** 管理员操作记录 */
public class AdminOperateRecord {
   public double aorId;
   /** 操作人(管理员账号) */
   public java.lang.String creator;
   /** 操作目标(例如:添加商品,来源于权限表数据) */
   public java.lang.String operateTarget;
   /** 操作时间 */
   public java.util.Date operateTime;
   /** 操作详情 */
   public java.lang.String operateDetail;

}