/***********************************************************************
 * Module:  IpBlock.java
 * Author:  VIC
 * Purpose: Defines the Class IpBlock
 ***********************************************************************/
package org.mail.entity;
import java.util.*;

/** IP黑名单 */
public class IpBlock {
   /** ID */
   public double ibId;
   /** IP地址 */
   public java.lang.String ibIp;
   /** 创建时间 */
   public java.util.Date createTime;
   /** 创建人 */
   public java.lang.String creator;
   /** 更新时间 */
   public java.util.Date updateTime;
   /** 更新人 */
   public java.lang.String updateCreator;
   /** 状态 */
   public java.lang.String state;

}