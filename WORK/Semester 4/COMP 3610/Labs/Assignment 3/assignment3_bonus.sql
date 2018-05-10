/*==============================================================*/
/* DBMS name:      ORACLE Version 10gR2                         */
/* Created on:     11/27/2017 2:43:17 PM                        */
/*==============================================================*/


alter table AS3_CONTRADICITONS
   drop constraint FK_AS3_CONT_CONTRADIC_AS3_DRUG;

alter table AS3_DRUG
   drop constraint FK_AS3_DRUG_CONTRADIC_AS3_CONT;

alter table AS3_PATIENT
   drop constraint FK_AS3_PATI_HAS_AS3_NEXT;

alter table AS3_PATIENT
   drop constraint FK_AS3_PATI_TREATS_AS3_LOCA;

alter table AS3_PRESCRIBED_MEDICATION
   drop constraint FK_AS3_PRES_PRESCRIBE_AS3_PRES;

alter table AS3_PRESCRIBED_MEDICATION
   drop constraint FK_AS3_PRES_PRESCRIBE_AS3_DRUG;

alter table AS3_PRESCRIPTIONS
   drop constraint FK_AS3_PRES_IS_PRESCR_AS3_PATI;

alter table AS3_QUESTIONNAIRE
   drop constraint FK_AS3_QUES_ANSWERS_AS3_PATI;

drop index CONTRADICITONS2_FK;

drop index CONTRADICITONS_FK;

drop table AS3_CONTRADICITONS cascade constraints;

drop table AS3_DRUG cascade constraints;

drop table AS3_LOCAL_DOCTOR cascade constraints;

drop table AS3_NEXT_OF_KIN cascade constraints;

drop index TREATS_FK;

drop index HAS_FK;

drop table AS3_PATIENT cascade constraints;

drop index PRESCRIBED_MEDICATION2_FK;

drop index PRESCRIBED_MEDICATION_FK;

drop table AS3_PRESCRIBED_MEDICATION cascade constraints;

drop index IS_PRESCRIBED_FK;

drop table AS3_PRESCRIPTIONS cascade constraints;

drop index ANSWERS_FK;

drop table AS3_QUESTIONNAIRE cascade constraints;

/*==============================================================*/
/* Table: AS3_CONTRADICITONS                                    */
/*==============================================================*/
create table AS3_CONTRADICITONS  (
   DRUG_ID              VARCHAR2(10)                    not null,
   AS3_DRUG_ID          VARCHAR2(10)                    not null,
   constraint PK_AS3_CONTRADICITONS primary key (DRUG_ID, AS3_DRUG_ID)
);

/*==============================================================*/
/* Index: CONTRADICITONS_FK                                     */
/*==============================================================*/
create index CONTRADICITONS_FK on AS3_CONTRADICITONS (
   DRUG_ID ASC,
   AS3_DRUG_ID ASC
);

/*==============================================================*/
/* Index: CONTRADICITONS2_FK                                    */
/*==============================================================*/
create index CONTRADICITONS2_FK on AS3_CONTRADICITONS (
   AS3_DRUG_ID ASC
);

/*==============================================================*/
/* Table: AS3_DRUG                                              */
/*==============================================================*/
create table AS3_DRUG  (
   DRUG_ID              VARCHAR2(10)                    not null,
   AS3_DRUG_ID          VARCHAR2(10),
   DRUG_NAME            VARCHAR2(15)                    not null,
   METHOD_OF_ADMINISTRATION VARCHAR2(10)                    not null,
   DESCRIPTION          VARCHAR2(60),
   constraint PK_AS3_DRUG primary key (DRUG_ID)
);

/*==============================================================*/
/* Table: AS3_LOCAL_DOCTOR                                      */
/*==============================================================*/
create table AS3_LOCAL_DOCTOR  (
   LD_ID                NUMBER(5)                       not null,
   LD_FULL_NAME         VARCHAR2(15),
   LD_CLINIC_ADDRESS    VARCHAR2(60),
   LD_TELEPHONE_NUMBER  VARCHAR2(13),
   LD_CLINIC_NUMBER     VARCHAR2(10),
   constraint PK_AS3_LOCAL_DOCTOR primary key (LD_ID)
);

/*==============================================================*/
/* Table: AS3_NEXT_OF_KIN                                       */
/*==============================================================*/
create table AS3_NEXT_OF_KIN  (
   NOK_FULLNAME         VARCHAR2(15)                    not null,
   NOK_RELATIONSHIP     VARCHAR2(10)                    not null,
   NOK_ID               NUMBER(5)                       not null,
   NOK_ADDRESS          VARCHAR2(50),
   NOK_TEL              VARCHAR2(13),
   constraint PK_AS3_NEXT_OF_KIN primary key (NOK_ID)
);

/*==============================================================*/
/* Table: AS3_PATIENT                                           */
/*==============================================================*/
create table AS3_PATIENT  (
   PATIENT_NUMBER       VARCHAR2(10)                    not null,
   NOK_ID               NUMBER(5),
   LD_ID                NUMBER(5),
   PATIENT_FIRST_NAME   VARCHAR2(15)                    not null,
   PATIENT_LAST_NAME    VARCHAR2(15)                    not null,
   PATIENT_DOB          DATE                            not null,
   PHONE_NUMBER         VARCHAR2(13)                    not null,
   GENDER               VARCHAR2(6)                     not null,
   REGISTRATION_DATE    DATE                            not null,
   PATIENT_ADDRESS      VARCHAR2(60),
   constraint PK_AS3_PATIENT primary key (PATIENT_NUMBER)
);

/*==============================================================*/
/* Index: HAS_FK                                                */
/*==============================================================*/
create index HAS_FK on AS3_PATIENT (
   NOK_ID ASC
);

/*==============================================================*/
/* Index: TREATS_FK                                             */
/*==============================================================*/
create index TREATS_FK on AS3_PATIENT (
   LD_ID ASC
);

/*==============================================================*/
/* Table: AS3_PRESCRIBED_MEDICATION                             */
/*==============================================================*/
create table AS3_PRESCRIBED_MEDICATION  (
   PRESCRIPTIONID       VARCHAR2(10)                    not null,
   DRUG_ID              VARCHAR2(10)                    not null,
   constraint PK_AS3_PRESCRIBED_MEDICATION primary key (PRESCRIPTIONID, DRUG_ID)
);

/*==============================================================*/
/* Index: PRESCRIBED_MEDICATION_FK                              */
/*==============================================================*/
create index PRESCRIBED_MEDICATION_FK on AS3_PRESCRIBED_MEDICATION (
   PRESCRIPTIONID ASC
);

/*==============================================================*/
/* Index: PRESCRIBED_MEDICATION2_FK                             */
/*==============================================================*/
create index PRESCRIBED_MEDICATION2_FK on AS3_PRESCRIBED_MEDICATION (
   DRUG_ID ASC
);

/*==============================================================*/
/* Table: AS3_PRESCRIPTIONS                                     */
/*==============================================================*/
create table AS3_PRESCRIPTIONS  (
   PRESCRIPTIONID       VARCHAR2(10)                    not null,
   PATIENT_NUMBER       VARCHAR2(10)                    not null,
   constraint PK_AS3_PRESCRIPTIONS primary key (PRESCRIPTIONID)
);

/*==============================================================*/
/* Index: IS_PRESCRIBED_FK                                      */
/*==============================================================*/
create index IS_PRESCRIBED_FK on AS3_PRESCRIPTIONS (
   PATIENT_NUMBER ASC
);

/*==============================================================*/
/* Table: AS3_QUESTIONNAIRE                                     */
/*==============================================================*/
create table AS3_QUESTIONNAIRE  (
   QUESTIONNAIRE_ID     VARCHAR2(10)                    not null,
   PATIENT_NUMBER       VARCHAR2(10)                    not null,
   SNORING              NUMBER(1)                       not null,
   TIRED                NUMBER(1)                       not null,
   OBSERVED             NUMBER(1)                       not null,
   PRESSURE             NUMBER(1)                       not null,
   BMIGREATERTHAN35     NUMBER(1)                       not null,
   AGEOLDERTHAN50       NUMBER(1)                       not null,
   LARGENECK            NUMBER(1)                       not null,
   GENDERMALE           NUMBER(1)                       not null,
   constraint PK_AS3_QUESTIONNAIRE primary key (QUESTIONNAIRE_ID)
);

/*==============================================================*/
/* Index: ANSWERS_FK                                            */
/*==============================================================*/
create index ANSWERS_FK on AS3_QUESTIONNAIRE (
   PATIENT_NUMBER ASC
);

alter table AS3_CONTRADICITONS
   add constraint FK_AS3_CONT_CONTRADIC_AS3_DRUG foreign key (AS3_DRUG_ID)
      references AS3_DRUG (DRUG_ID);

alter table AS3_DRUG
   add constraint FK_AS3_DRUG_CONTRADIC_AS3_CONT foreign key (DRUG_ID, AS3_DRUG_ID)
      references AS3_CONTRADICITONS (DRUG_ID, AS3_DRUG_ID);

alter table AS3_PATIENT
   add constraint FK_AS3_PATI_HAS_AS3_NEXT foreign key (NOK_ID)
      references AS3_NEXT_OF_KIN (NOK_ID);

alter table AS3_PATIENT
   add constraint FK_AS3_PATI_TREATS_AS3_LOCA foreign key (LD_ID)
      references AS3_LOCAL_DOCTOR (LD_ID);

alter table AS3_PRESCRIBED_MEDICATION
   add constraint FK_AS3_PRES_PRESCRIBE_AS3_PRES foreign key (PRESCRIPTIONID)
      references AS3_PRESCRIPTIONS (PRESCRIPTIONID);

alter table AS3_PRESCRIBED_MEDICATION
   add constraint FK_AS3_PRES_PRESCRIBE_AS3_DRUG foreign key (DRUG_ID)
      references AS3_DRUG (DRUG_ID);

alter table AS3_PRESCRIPTIONS
   add constraint FK_AS3_PRES_IS_PRESCR_AS3_PATI foreign key (PATIENT_NUMBER)
      references AS3_PATIENT (PATIENT_NUMBER);

alter table AS3_QUESTIONNAIRE
   add constraint FK_AS3_QUES_ANSWERS_AS3_PATI foreign key (PATIENT_NUMBER)
      references AS3_PATIENT (PATIENT_NUMBER);

