/*==============================================================*/
/* DBMS name:      ORACLE Version 10gR2                         */
/* Created on:     11/9/2017 11:12:19 AM                        */
/*==============================================================*/


alter table LAB_6RESERVATIONS
   drop constraint FK_LAB_6RES_HAS_LAB_6VEH;

alter table LAB_6RESERVATIONS
   drop constraint FK_LAB_6RES_MAKES_LAB_6CLI;

drop table LAB_6CLIENT cascade constraints;

drop index HAS_FK;

drop index MAKES_FK;

drop table LAB_6RESERVATIONS cascade constraints;

drop table LAB_6VEHICLE cascade constraints;

/*==============================================================*/
/* Table: LAB_6CLIENT                                           */
/*==============================================================*/
create table LAB_6CLIENT  (
   C_ID                 NUMBER(10)                      not null,
   F_NAME               VARCHAR2(20)                    not null,
   L_NAME               VARCHAR2(20)                    not null,
   DOB                  DATE                            not null,
   CITY                 VARCHAR2(20)                    not null,
   GENDER               CHAR(1),
   constraint PK_LAB_6CLIENT primary key (C_ID)
);

/*==============================================================*/
/* Table: LAB_6RESERVATIONS                                     */
/*==============================================================*/
create table LAB_6RESERVATIONS  (
   R_ID                 NUMBER(10)                      not null,
   V_ID                 NUMBER(12)                      not null,
   C_ID                 NUMBER(10)                      not null,
   START_DATE           DATE                            not null,
   END_DATE             DATE                            not null,
   constraint PK_LAB_6RESERVATIONS primary key (R_ID)
);

/*==============================================================*/
/* Index: MAKES_FK                                              */
/*==============================================================*/
create index MAKES_FK on LAB_6RESERVATIONS (
   C_ID ASC
);

/*==============================================================*/
/* Index: HAS_FK                                                */
/*==============================================================*/
create index HAS_FK on LAB_6RESERVATIONS (
   V_ID ASC
);


/*==============================================================*/
/* Table: LAB_6VEHICLE                                          */
/*==============================================================*/
create table LAB_6VEHICLE  (
   V_ID                 NUMBER(12)                      not null,
   VIN                  CHAR(17)                        not null,
   V_MAKE               VARCHAR2(15)                    not null,
   COST_PER_DAY         NUMBER(5,2)                     not null,
   constraint PK_LAB_6VEHICLE primary key (V_ID)
);

alter table LAB_6RESERVATIONS
   add constraint FK_LAB_6RES_HAS_LAB_6VEH foreign key (V_ID)
      references LAB_6VEHICLE (V_ID);

alter table LAB_6RESERVATIONS
   add constraint FK_LAB_6RES_MAKES_LAB_6CLI foreign key (C_ID)
      references LAB_6CLIENT (C_ID);

