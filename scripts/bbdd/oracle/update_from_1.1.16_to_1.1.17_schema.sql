
-- 04/05/2022 Nous camps fitxers per FAQ (Poder afegir captures de pantalla a les FAQ al backoffice #630)

ALTER TABLE car_preguntesfrequents ADD fitxer1id NUMBER(19,0);
ALTER TABLE car_preguntesfrequents ADD CONSTRAINT car_faq_fitxer_fitxer1_fk FOREIGN KEY (fitxer1id) REFERENCES car_fitxer (fitxerid);
CREATE INDEX car_faq_fitxer1id_fk_i on car_preguntesfrequents (fitxer1id);

ALTER TABLE car_preguntesfrequents ADD fitxer2id NUMBER(19,0);
ALTER TABLE car_preguntesfrequents ADD CONSTRAINT car_faq_fitxer_fitxer2_fk FOREIGN KEY (fitxer2id) REFERENCES car_fitxer (fitxerid);
CREATE INDEX car_faq_fitxer2id_fk_i on car_preguntesfrequents (fitxer2id);

ALTER TABLE car_preguntesfrequents ADD fitxer3id NUMBER(19,0);
ALTER TABLE car_preguntesfrequents ADD CONSTRAINT car_faq_fitxer_fitxer3_fk FOREIGN KEY (fitxer3id) REFERENCES car_fitxer (fitxerid);
CREATE INDEX car_faq_fitxer3id_fk_i on car_preguntesfrequents (fitxer3id);



--  2022-05-11 Millores en la gestio de Notificacions Push a l'APP #659 


CREATE SEQUENCE car_notificacioapp_seq START WITH 1000 INCREMENT BY 1;


CREATE TABLE car_notificacioapp
(
   notificacioappid number(19,0) NOT NULL DEFAULT car_notificacioapp_seq.nextval, 
   codi varchar2(50) NOT NULL, 
   titolid number(19,0) NOT NULL, 
   missatgeid number(19,0) NOT NULL, 
   frontpluginid number(19,0), 
   activa number(1,0) NOT NULL, 
   ajuda clob,
   entitatid number(19,0) NOT NULL
);


CREATE TABLE car_notificacioapp ADD CONSTRAINT car_notificacioapp_pk PRIMARY KEY (notificacioappid);
CREATE TABLE car_notificacioapp ADD CONSTRAINT car_notifica_traduccio_tit_fk FOREIGN KEY (titolid) REFERENCES car_traduccio (traduccioid); 
CREATE TABLE car_notificacioapp ADD CONSTRAINT car_notifica_traduccio_msg_fk FOREIGN KEY (missatgeid) REFERENCES car_traduccio (traduccioid); 
CREATE TABLE car_notificacioapp ADD CONSTRAINT car_notifica_plugin_plug_fk FOREIGN KEY (frontpluginid) REFERENCES car_plugin (pluginid);
CREATE TABLE car_notificacioapp ADD CONSTRAINT car_notifica_entitat_ent_fk FOREIGN KEY (entitatid) REFERENCES car_entitat (entitatid);
CREATE TABLE car_notificacioapp ADD CONSTRAINT car_notifica_codi_uk UNIQUE (codi);


create index car_notificacioapp_pk_i on car_notificacioapp (notificacioappid);
create index car_notifica_titolid_fk_i on car_notificacioapp (titolid);
create index car_notifica_missatgeid_fk_i on car_notificacioapp (missatgeid);
create index car_notifica_pluginid_fk_i on car_notificacioapp (frontpluginid);
create index car_notifica_entitatid_fk_i on car_notificacioapp (entitatid);


grant select on car_notificacioapp_seq to www_carpeta;
grant select,insert,delete,update on car_notificacioapp to www_carpeta;