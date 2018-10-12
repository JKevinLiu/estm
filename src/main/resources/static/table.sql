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


drop table if exists t_audit;

/*==============================================================*/
/* Table: t_audit                                               */
/*==============================================================*/
create table t_audit
(
   id                   int not null,
   order_no             varchar(18),
   bus_type             varchar(80),
   out_user_id          int,
   audit_user_id        int,
   state                int,
   create_date          datetime,
   audit_date           datetime,
   dir_path             varchar(100),
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
   passed               int,
   reson                varchar(1000),
   primary key (id)
)auto_increment=1001;
