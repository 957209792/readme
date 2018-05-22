/***********************************************************************
 * Module:  Auth.java
 * Author:  VIC
 * Purpose: Defines the Class Auth
 ***********************************************************************/
package org.mail.entity;
import java.util.*;

/** 管理后台系统权限 */
public class Auth {
   /** 权限ID */
   public double authId;
   /** 权限名称 */
   public java.lang.String authName;
   /** 权限代码 */
   public java.lang.String authCode;
   /** 权限URL */
   public java.lang.String authUrl;
   /** 创建时间 */
   public java.util.Date createTime;
   /** 创建人(默认为系统,否则为管理员) */
   public java.lang.String creator;
   /** 更新时间 */
   public java.util.Date updateTime;
   /** 更新人(默认为系统,否则为管理员账号) */
   public java.lang.String updateCreator;
   /** 状态 */
   public java.lang.String state;

}