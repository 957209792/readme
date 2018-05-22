/*==============================================================*/
/* DBMS name:      ORACLE Version 11g                           */
/* Created on:     2018/5/15 10:27:56                           */
/*==============================================================*/


drop table ADMIN cascade constraints;

drop table ADMIN_AUTH cascade constraints;

drop table ADMIN_OPERATE_RECORD cascade constraints;

drop table AUTH cascade constraints;

drop table COMMODITY cascade constraints;

drop table DATA_SCRE_METHOD cascade constraints;

drop table EMAIL cascade constraints;

drop table IP_BLOCK cascade constraints;

drop table MESSAGE cascade constraints;

drop table "ORDER" cascade constraints;

drop table ORDER_DETAILS cascade constraints;

drop table PAYMENT_METHOD cascade constraints;

drop table PAYMENT_RECORD cascade constraints;

drop table RECEIVING_ADDRESS cascade constraints;

drop table SHOPING_CART cascade constraints;

drop table SYSTEM_SETUP cascade constraints;

drop table USERS cascade constraints;

/*==============================================================*/
/* Table: ADMIN                                                 */
/*==============================================================*/
create table ADMIN 
(
   A_ID                 INTEGER              not null,
   A_ACCOUNT            VARCHAR(128),
   A_PASSWORD           VARCHAR(128),
   A_EAMIL              TIMESTAMP,
   A_PHONE              NUMBER,
   A_HEAD               TIMESTAMP,
   CREATE_TIME          TIMESTAMP,
   CREATOR              VARCHAR(128),
   UPDATE_TIME          TIMESTAMP,
   UPDATE_CREATOR       TIMESTAMP,
   STATE                VARCHAR(32)
);

comment on table ADMIN is
'后台管理员';

comment on column ADMIN.A_ACCOUNT is
'管理员登录账号';

comment on column ADMIN.A_PASSWORD is
'管理员登录密码';

comment on column ADMIN.A_EAMIL is
'管理员邮箱';

comment on column ADMIN.A_PHONE is
'管理员手机';

comment on column ADMIN.A_HEAD is
'管理员头像';

comment on column ADMIN.CREATE_TIME is
'创建时间';

comment on column ADMIN.CREATOR is
'创建人默认为系统)';

comment on column ADMIN.UPDATE_TIME is
'更新时间';

comment on column ADMIN.UPDATE_CREATOR is
'更新时间';

comment on column ADMIN.STATE is
'状态';

/*==============================================================*/
/* Table: ADMIN_AUTH                                            */
/*==============================================================*/
create table ADMIN_AUTH 
(
   AA_ID                NUMBER               not null,
   ADMIN_ID             NUMBER               not null,
   AUTH_ID              NUMBER               not null,
   constraint PK_ADMIN_AUTH primary key (AA_ID)
);

comment on table ADMIN_AUTH is
'管理员权限表';

/*==============================================================*/
/* Table: ADMIN_OPERATE_RECORD                                  */
/*==============================================================*/
create table ADMIN_OPERATE_RECORD 
(
   AOR_ID               NUMBER               not null,
   CREATOR              VARCHAR2(128),
   OPERATE_TARGET       VARCHAR2(128),
   OPERATE_TIME         TIMESTAMP,
   OPERATE_DETAIL       VARCHAR2(128),
   constraint PK_ADMIN_OPERATE_RECORD primary key (AOR_ID)
);

comment on table ADMIN_OPERATE_RECORD is
'管理员操作记录';

comment on column ADMIN_OPERATE_RECORD.CREATOR is
'操作人(管理员账号)';

comment on column ADMIN_OPERATE_RECORD.OPERATE_TARGET is
'操作目标(例如:添加商品,来源于权限表数据)';

comment on column ADMIN_OPERATE_RECORD.OPERATE_TIME is
'操作时间';

comment on column ADMIN_OPERATE_RECORD.OPERATE_DETAIL is
'操作详情';

/*==============================================================*/
/* Table: AUTH                                                  */
/*==============================================================*/
create table AUTH 
(
   AUTH_ID              NUMBER               not null,
   AUTH_NAME            VARCHAR2(128),
   AUTH_CODE            VARCHAR(68),
   AUTH_URL             VARCHAR2(128),
   CREATE_TIME          TIMESTAMP,
   CREATOR              VARCHAR2(128),
   UPDATE_TIME          TIMESTAMP,
   UPDATE_CREATOR       VARCHAR2(128),
   STATE                VARCHAR2(32),
   constraint PK_AUTH primary key (AUTH_ID)
);

comment on table AUTH is
'管理后台系统权限';

comment on column AUTH.AUTH_ID is
'权限ID';

comment on column AUTH.AUTH_NAME is
'权限名称';

comment on column AUTH.AUTH_CODE is
'权限代码';

comment on column AUTH.AUTH_URL is
'权限URL';

comment on column AUTH.CREATE_TIME is
'创建时间';

comment on column AUTH.CREATOR is
'创建人(默认为系统,否则为管理员)';

comment on column AUTH.UPDATE_TIME is
'更新时间';

comment on column AUTH.UPDATE_CREATOR is
'更新人(默认为系统,否则为管理员账号)';

comment on column AUTH.STATE is
'状态';

/*==============================================================*/
/* Table: COMMODITY                                             */
/*==============================================================*/
create table COMMODITY 
(
   C_ID                 NUMBER               not null,
   C_NAME               VARCHAR(128),
   C_URL                VARCHAR(128),
   C_DESCRIBE           VARCHAR(128),
   C_KEYWORDS           VARCHAR(128),
   C_PRICE              VARCHAR(128),
   C_PIC                FLOAT,
   C_STATE              VARCHAR(32),
   CREATE_TIME          TIMESTAMP,
   CREATOR              VARCHAR(128),
   UPDATE_TIME          TIMESTAMP,
   UPDATE_CREATOR       VARCHAR(128),
   constraint PK_COMMODITY primary key (C_ID)
);

comment on table COMMODITY is
'商品';

comment on column COMMODITY.C_ID is
'商品ID';

comment on column COMMODITY.C_NAME is
'商品名称';

comment on column COMMODITY.C_URL is
'商品地址';

comment on column COMMODITY.C_DESCRIBE is
'商品描述';

comment on column COMMODITY.C_KEYWORDS is
'商品关键字';

comment on column COMMODITY.C_PRICE is
'商品价格';

comment on column COMMODITY.C_PIC is
'商品图片: 图片存储的相对路径或绝对路径';

comment on column COMMODITY.C_STATE is
'商品状态: 新建/上架/下架/删除';

comment on column COMMODITY.CREATE_TIME is
'创建时间(当前系统时间)';

comment on column COMMODITY.CREATOR is
'创建人(管理员账号)';

comment on column COMMODITY.UPDATE_TIME is
'更新时间(当前系统时间)';

comment on column COMMODITY.UPDATE_CREATOR is
'更新人(管理员账号)';

/*==============================================================*/
/* Table: DATA_SCRE_METHOD                                      */
/*==============================================================*/
create table DATA_SCRE_METHOD 
(
   DSM_ID               NUMBER               not null,
   DSM_METHOD           VARCHAR(52),
   CREATE_TIME          TIMESTAMP,
   CREATOR              VARCHAR(128),
   UPDATE_TIME          TIMESTAMP,
   UPDATE_CREATOR       VARCHAR(128),
   STATE                VARCHAR(32),
   constraint PK_DATA_SCRE_METHOD primary key (DSM_ID)
);

comment on table DATA_SCRE_METHOD is
'数据加密方式';

comment on column DATA_SCRE_METHOD.DSM_ID is
'ID';

comment on column DATA_SCRE_METHOD.DSM_METHOD is
'加密方式:MD5';

comment on column DATA_SCRE_METHOD.CREATE_TIME is
'创建时间';

comment on column DATA_SCRE_METHOD.CREATOR is
'创建人: 管理员账号';

comment on column DATA_SCRE_METHOD.UPDATE_TIME is
'更新时间';

comment on column DATA_SCRE_METHOD.UPDATE_CREATOR is
'更新人:管理员账号';

comment on column DATA_SCRE_METHOD.STATE is
'状态';

/*==============================================================*/
/* Table: EMAIL                                                 */
/*==============================================================*/
create table EMAIL 
(
   E_ID                 NUMBER               not null,
   E_TITLE              VARCHAR2(128),
   E_CONTENT            VARCHAR(128),
   E_EMAIL              VARCHAR(128),
   E_SEND_TIME          TIMESTAMP,
   CREATE_TIME          TIMESTAMP,
   CREATOR              VARCHAR(128),
   E_SEND_STATE         VARCHAR(32),
   STATE                VARCHAR(32),
   E_TYPE               VARCHAR(32),
   constraint PK_EMAIL primary key (E_ID)
);

comment on table EMAIL is
'系统邮件，注册邮件，订单邮件';

/*==============================================================*/
/* Table: IP_BLOCK                                              */
/*==============================================================*/
create table IP_BLOCK 
(
   IB_ID                NUMBER               not null,
   IB_IP                VARCHAR2(32),
   CREATE_TIME          TIMESTAMP,
   CREATOR              VARCHAR(128),
   UPDATE_TIME          TIMESTAMP,
   UPDATE_CREATOR       VARCHAR(128),
   STATE                VARCHAR(32),
   constraint PK_IP_BLOCK primary key (IB_ID)
);

comment on table IP_BLOCK is
'IP黑名单';

comment on column IP_BLOCK.IB_ID is
'ID';

comment on column IP_BLOCK.IB_IP is
'IP地址';

comment on column IP_BLOCK.CREATE_TIME is
'创建时间';

comment on column IP_BLOCK.CREATOR is
'创建人';

comment on column IP_BLOCK.UPDATE_TIME is
'更新时间';

comment on column IP_BLOCK.UPDATE_CREATOR is
'更新人';

comment on column IP_BLOCK.STATE is
'状态';

/*==============================================================*/
/* Table: MESSAGE                                               */
/*==============================================================*/
create table MESSAGE 
(
   M_ID                 NUMBER               not null,
   M_TITLE              VARCHAR(128),
   M_CONTENT            VARCHAR(128),
   M_PHONE              NUMBER,
   M_SEND_TIME          TIMESTAMP,
   M_SEND_STATE         VARCHAR(32),
   M_TYPE               VARCHAR(32),
   CREATE_TIME          TIMESTAMP,
   CREATOR              VARCHAR(128),
   STATE                VARCHAR(32),
   constraint PK_MESSAGE primary key (M_ID)
);

comment on table MESSAGE is
'注册短信，订单短信，支付短信，验证码短信';

/*==============================================================*/
/* Table: "ORDER"                                               */
/*==============================================================*/
create table "ORDER" 
(
   O_ID                 NUMBER               not null,
   O_PRICE              FLOAT,
   CREATE_TIME          TIMESTAMP,
   O_STATE              CLOB,
   U_ID                 NUMBER,
   O_NUMBER             VARCHAR2(52),
   constraint PK_ORDER primary key (O_ID)
);

comment on table "ORDER" is
'订单';

comment on column "ORDER".O_ID is
'订单ID';

comment on column "ORDER".U_ID is
'用户ID';

comment on column "ORDER".O_NUMBER is
'订单号:系统生成';

/*==============================================================*/
/* Table: ORDER_DETAILS                                         */
/*==============================================================*/
create table ORDER_DETAILS 
(
   OD_ID                NUMBER               not null,
   C_ID                 NUMBER,
   C_NUMBER             NUMBER,
   C_PRICE              FLOAT,
   STATE                VARCHAR(32),
   O_ID                 NUMBER,
   CREATE_TIME          TIMESTAMP,
   constraint PK_ORDER_DETAILS primary key (OD_ID)
);

comment on table ORDER_DETAILS is
'订单详情';

comment on column ORDER_DETAILS.OD_ID is
'订单详情ID';

comment on column ORDER_DETAILS.C_ID is
'商品ID';

comment on column ORDER_DETAILS.C_NUMBER is
'商品数量';

comment on column ORDER_DETAILS.C_PRICE is
'商品单价';

comment on column ORDER_DETAILS.STATE is
'状态';

comment on column ORDER_DETAILS.O_ID is
'订单ID';

comment on column ORDER_DETAILS.CREATE_TIME is
'创建时间';

/*==============================================================*/
/* Table: PAYMENT_METHOD                                        */
/*==============================================================*/
create table PAYMENT_METHOD 
(
   PM_ID                NUMBER               not null,
   PM_NAME              VARCHAR(68),
   PM_KEY               VARCHAR(68),
   PM_NOTICE            VARCHAR(68),
   PM_URL               VARCHAR(68),
   PM_TOKEN             VARCHAR(68),
   PM_OTHER             VARCHAR(68),
   STATE                VARCHAR(32),
   CREATE_TIME          TIMESTAMP,
   CREATOR              VARCHAR(128),
   UPDATE_TIME          TIMESTAMP,
   UPDATE_CREATOR       VARCHAR(128),
   constraint PK_PAYMENT_METHOD primary key (PM_ID)
);

comment on table PAYMENT_METHOD is
'支付方式，支付平台，支付渠道';

comment on column PAYMENT_METHOD.PM_ID is
'ID';

comment on column PAYMENT_METHOD.PM_NAME is
'支付方式名称';

comment on column PAYMENT_METHOD.PM_KEY is
'支付KEY';

comment on column PAYMENT_METHOD.PM_NOTICE is
'支付通知URL';

comment on column PAYMENT_METHOD.PM_URL is
'支付URL';

comment on column PAYMENT_METHOD.PM_TOKEN is
'支付Token';

comment on column PAYMENT_METHOD.PM_OTHER is
'支付其他参数';

comment on column PAYMENT_METHOD.STATE is
'状态';

comment on column PAYMENT_METHOD.CREATE_TIME is
'创建时间';

comment on column PAYMENT_METHOD.CREATOR is
'创建人';

comment on column PAYMENT_METHOD.UPDATE_TIME is
'更新时间';

comment on column PAYMENT_METHOD.UPDATE_CREATOR is
'更新人';

/*==============================================================*/
/* Table: PAYMENT_RECORD                                        */
/*==============================================================*/
create table PAYMENT_RECORD 
(
   PR_ID                NUMBER               not null,
   PAYMENT_METHOD       VARCHAR(32),
   PAYMENT_STATE        VARCHAR(32),
   PAYMENT_TIME         TIMESTAMP,
   PAYMENT_TOTAL        FLOAT,
   PAYMENT_NUMBER       NUMBER,
   PAYMENT_OTHER        VARCHAR(68),
   STATE                VARCHAR(32),
   O_ID                 NUMBER,
   CREATE_TIME          TIMESTAMP,
   constraint PK_PAYMENT_RECORD primary key (PR_ID)
);

comment on table PAYMENT_RECORD is
'支付记录';

comment on column PAYMENT_RECORD.PAYMENT_METHOD is
'支付方式';

comment on column PAYMENT_RECORD.PAYMENT_STATE is
'支付状态';

comment on column PAYMENT_RECORD.PAYMENT_TIME is
'支付时间';

comment on column PAYMENT_RECORD.PAYMENT_TOTAL is
'支付总金额';

comment on column PAYMENT_RECORD.PAYMENT_NUMBER is
'商品数量';

comment on column PAYMENT_RECORD.PAYMENT_OTHER is
'支付其他';

comment on column PAYMENT_RECORD.STATE is
'状态';

comment on column PAYMENT_RECORD.O_ID is
'订单ID';

comment on column PAYMENT_RECORD.CREATE_TIME is
'创建时间';

/*==============================================================*/
/* Table: RECEIVING_ADDRESS                                     */
/*==============================================================*/
create table RECEIVING_ADDRESS 
(
   RA_ID                NUMBER               not null,
   RA_COUNTRY           VARCHAR(32),
   RA_PROVINCE          VARCHAR(32),
   RA_CITY              VARCHAR(32),
   RA_DETAIL            VARCHAR(32),
   RA_PERSON            VARCHAR(52),
   RA_PHONE             NUMBER,
   STATE                VARCHAR(32),
   U_ID                 NUMBER,
   CREATE_TIME          TIMESTAMP,
   UPDATE_TIME          TIMESTAMP,
   constraint PK_RECEIVING_ADDRESS primary key (RA_ID)
);

comment on table RECEIVING_ADDRESS is
'收货地址';

comment on column RECEIVING_ADDRESS.RA_ID is
'ID';

comment on column RECEIVING_ADDRESS.RA_COUNTRY is
'国家';

comment on column RECEIVING_ADDRESS.RA_PROVINCE is
'省';

comment on column RECEIVING_ADDRESS.RA_CITY is
'市';

comment on column RECEIVING_ADDRESS.RA_DETAIL is
'详细地址';

comment on column RECEIVING_ADDRESS.RA_PERSON is
'收货人姓名';

comment on column RECEIVING_ADDRESS.RA_PHONE is
'收货人手机';

comment on column RECEIVING_ADDRESS.STATE is
'状态';

comment on column RECEIVING_ADDRESS.U_ID is
'用户ID';

comment on column RECEIVING_ADDRESS.CREATE_TIME is
'创建时间';

comment on column RECEIVING_ADDRESS.UPDATE_TIME is
'更新时间';

/*==============================================================*/
/* Table: SHOPING_CART                                          */
/*==============================================================*/
create table SHOPING_CART 
(
   SC_ID                NUMBER               not null,
   C_ID                 NUMBER,
   C_NUMBER             NUMBER,
   C_PRICE              FLOAT,
   C_NAME               VARCHAR2(128),
   U_ID                 NUMBER,
   constraint PK_SHOPING_CART primary key (SC_ID)
);

comment on table SHOPING_CART is
'购物车';

comment on column SHOPING_CART.SC_ID is
'购物车主键';

comment on column SHOPING_CART.C_ID is
'商品ID';

comment on column SHOPING_CART.C_NUMBER is
'商品数量';

comment on column SHOPING_CART.C_PRICE is
'商品单价';

comment on column SHOPING_CART.C_NAME is
'商品名称';

comment on column SHOPING_CART.U_ID is
'用户ID';

/*==============================================================*/
/* Table: SYSTEM_SETUP                                          */
/*==============================================================*/
create table SYSTEM_SETUP 
(
   SS_ID                NUMBER               not null,
   SS_NAME              VARCHAR2(128),
   SS_HOME              VARCHAR2(128),
   SS_KETWORDS          VARCHAR2(128),
   SS_DESCRIBE          VARCHAR2(128),
   SS_DOMAIN            VARCHAR2(128),
   SS_IP                VARCHAR2(32),
   CREATE_TIME          TIMESTAMP,
   CREATOR              VARCHAR2(128),
   UPDATE_TIME          TIMESTAMP,
   UPDATE_CREATOR       VARCHAR(128),
   STATE                VARCHAR2(32),
   constraint PK_SYSTEM_SETUP primary key (SS_ID)
);

comment on table SYSTEM_SETUP is
'系统设置';

comment on column SYSTEM_SETUP.SS_ID is
'ID';

comment on column SYSTEM_SETUP.SS_NAME is
'系统名称';

comment on column SYSTEM_SETUP.SS_HOME is
'系统首页';

comment on column SYSTEM_SETUP.SS_KETWORDS is
'系统关键字';

comment on column SYSTEM_SETUP.SS_DESCRIBE is
'系统描述';

comment on column SYSTEM_SETUP.SS_DOMAIN is
'系统域名';

comment on column SYSTEM_SETUP.SS_IP is
'系统IP';

comment on column SYSTEM_SETUP.CREATE_TIME is
'创建时间';

comment on column SYSTEM_SETUP.CREATOR is
'创建人(管理员账号)';

comment on column SYSTEM_SETUP.UPDATE_TIME is
'更新时间';

comment on column SYSTEM_SETUP.UPDATE_CREATOR is
'更新人';

comment on column SYSTEM_SETUP.STATE is
'状态';

/*==============================================================*/
/* Table: USERS                                                 */
/*==============================================================*/
create table USERS 
(
   "UID"                NUMBER               not null,
   UNAME                VARCHAR2(98)         not null,
   UACCOUNT             VARCHAR2(98)         not null,
   UPASSWORD            VARCHAR2(98)         not null,
   USEX                 VARCHAR2(8),
   UAGE                 NUMBER,
   UHEAD                VARCHAR2(128),
   UEMAIL               VARCHAR2(128),
   UPHONE               NUMBER,
   STATE                VARCHAR2(32),
   CREATETIME           TIMESTAMP,
   constraint PK_USERS primary key ("UID")
);

comment on table USERS is
'用户';

comment on column USERS."UID" is
'用户ID';

comment on column USERS.UNAME is
'用户名';

comment on column USERS.UACCOUNT is
'用户登录账号';

comment on column USERS.UPASSWORD is
'用户登录密码';

comment on column USERS.USEX is
'用户性别';

comment on column USERS.UAGE is
'用户年龄';

comment on column USERS.UHEAD is
'用户头像';

comment on column USERS.UEMAIL is
'用户邮箱';

comment on column USERS.UPHONE is
'用户手机';

comment on column USERS.STATE is
'用户状态';

comment on column USERS.CREATETIME is
'注册时间';

