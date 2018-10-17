drop table if exists t_out_user;

/*==============================================================*/
/* Table: t_out_user                                            */
/*==============================================================*/
create table t_out_user
(
   id                   int not null auto_increment,
   name                 varchar(40),
   phone                varchar(20),
   open_id              varchar(40),
   create_date          datetime,
   primary key (id)
)auto_increment=1001;


drop table if exists t_inner_user;

/*==============================================================*/
/* Table: t_inner_user                                          */
/*==============================================================*/
create table t_inner_user
(
   id                   int not null,
   user_name             varchar(40),
   password             varchar(60),
   nick_name             varchar(40),
   create_date          datetime,
   role_id              int,
   primary key (id)
)auto_increment=1001;


drop table if exists t_catalog;

/*==============================================================*/
/* Table: t_catalog                                             */
/*==============================================================*/
create table t_catalog
(
   id                   int not null,
   parent_Id            int,
   name            VARCHAR(20),
   bus_code           VARCHAR(20),
   primary key (id)
)auto_increment=1001;


drop table if exists t_audit;

/*==============================================================*/
/* Table: t_audit                                               */
/*==============================================================*/
create table t_audit
(
   id                   int not null,
   order_no             varchar(18),
   bus_type             int,
   out_user_id          int,
   audit_user_id        int,
   state                 int,
   create_date          datetime,
   audit_date           datetime,
   dir_path             varchar(100),
   remark              varchar(1000),
   primary key (id)
)auto_increment=1001;


drop table if exists t_audit_item;

/*==============================================================*/
/* Table: t_audit_item                                          */
/*==============================================================*/
create table t_audit_item
(
   id                   int not null,
   order_no             varchar(18),
   item_type            int,
   file_type            int,
   file_name             varchar(40),
   path                 varchar(100),
   primary key (id)
)auto_increment=1001;


drop table if exists t_req_cert;

/*==============================================================*/
/* Table: t_req_cert                                            */
/*==============================================================*/
create table t_req_cert
(
   id                   int not null,
   audit_item_id        int,
   app_type             varchar(200),
   ob_name1             varchar(40),
   card_type1           int,
   card_no1             varchar(40),
   adress1              varchar(200),
   postal_code1         varchar(20),
   ob_name2             varchar(40),
   card_type2           int,
   card_no2             varchar(40),
   adress2              varchar(200),
   postal_code2         varchar(20),
   location             varchar(200),
   est_type             varchar(200),
   acreage              varchar(200),
   purpose              varchar(256),
   certificate_no       varchar(200),
   app_form             int,
   app_cert             int,
   remark               varchar(256),
   applicant1           varchar(40),
   agent1               varchar(40),
   applicant2           varchar(40),
   agent2               varchar(40),
   primary key (id)
)auto_increment=1001;


drop table if exists t_marriage;

/*==============================================================*/
/* Table: t_marriage                                            */
/*==============================================================*/
create table t_marriage
(
   id                   int,
   audit_item_id        int,
   cur_name             varchar(40),
   card_no              varchar(40),
   pro_year             varchar(10),
   pro_month            varchar(10),
   pro_day              varchar(10),
   mar_type             int,
   Promise_name         varchar(40),
   year                 varchar(10),
   month                varchar(10),
   day                  varchar(10),
   primary key (id)
)auto_increment=1001;


drop table if exists t_recognizance;

/*==============================================================*/
/* Table: t_recognizance                                        */
/*==============================================================*/
create table t_recognizance
(
   id                   int,
   audit_item_id       int,
   card_no              varchar(40),
   town                 varchar(40),
   road                 varchar(100),
   num                  varchar(40),
   Building             varchar(10),
   unit                 varchar(10),
   floor                varchar(10),
   room                 varchar(10),
   name                 varchar(40),
   year                 varchar(10),
   month                varchar(10),
   day                  varchar(10),
   primary key (id)
)auto_increment=1001;



drop table if exists t_wx_send;

/*==============================================================*/
/* Table: t_wx_send                                             */
/*==============================================================*/
create table t_wx_send
(
   id                   int,
   audit_id             int,
   out_user_id           int,
   cotent               varchar(255),
   create_date          datetime,
   send_date            datetime,
   template_id          varchar(100),
   primary key (id)
)auto_increment=1001;

drop table if exists t_wx_send_his;

/*==============================================================*/
/* Table: t_wx_send_his                                         */
/*==============================================================*/
create table t_wx_send_his
(
   id                   int,
   audit_id             int,
   outuser_id           int,
   cotent               varchar(255),
   create_date          datetime,
   send_date            datetime,
   template_id          varchar(100),
   finish_date          datetime,
   state                int,
   send_log             varchar(1000),
   primary key (id)
)auto_increment=1001;