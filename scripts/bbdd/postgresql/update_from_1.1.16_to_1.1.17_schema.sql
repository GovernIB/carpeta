BEGIN;

-- 04/05/2022 Nous camps fitxers per FAQ (Poder afegir captures de pantalla a les FAQ al backoffice #630)

ALTER TABLE car_preguntesfrequents ADD fitxer1id int8 NULL;
ALTER TABLE car_preguntesfrequents ADD CONSTRAINT car_faq_fitxer_fitxer1_fk FOREIGN KEY (fitxer1id) REFERENCES car_fitxer(fitxerid);
CREATE INDEX car_faq_fitxer1id_fk_i ON car_preguntesfrequents using btree (fitxer1id);

ALTER TABLE car_preguntesfrequents ADD fitxer2id int8 NULL;
ALTER TABLE car_preguntesfrequents ADD CONSTRAINT car_faq_fitxer_fitxer2_fk FOREIGN KEY (fitxer2id) REFERENCES car_fitxer(fitxerid);
CREATE INDEX car_faq_fitxer2id_fk_i ON car_preguntesfrequents using btree (fitxer2id);

ALTER TABLE car_preguntesfrequents ADD fitxer3id int8 NULL;
ALTER TABLE car_preguntesfrequents ADD CONSTRAINT car_faq_fitxer_fitxer3_fk FOREIGN KEY (fitxer3id) REFERENCES car_fitxer(fitxerid);
CREATE INDEX car_faq_fitxer3id_fk_i ON car_preguntesfrequents using btree (fitxer3id);





--  Millores en la gestio de Notificacions Push a l'APP #659 


CREATE SEQUENCE car_notificacioapp_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;

CREATE TABLE car_notificacioapp
(
   notificacioappid bigint NOT NULL DEFAULT nextval('car_notificacioapp_seq'), 
   codi character varying(50) NOT NULL, 
   titolid bigint NOT NULL, 
   missatgeid bigint NOT NULL, 
   frontpluginid bigint, 
   activa boolean NOT NULL, 
   ajuda text,
   CONSTRAINT car_notificacioapp_pk PRIMARY KEY (notificacioappid), 
   CONSTRAINT car_notifica_traduccio_tit_fk FOREIGN KEY (titolid) REFERENCES car_traduccio (traduccioid) ON UPDATE NO ACTION ON DELETE NO ACTION, 
   CONSTRAINT car_notifica_traduccio_msg_fk FOREIGN KEY (missatgeid) REFERENCES car_traduccio (traduccioid) ON UPDATE NO ACTION ON DELETE NO ACTION, 
   CONSTRAINT car_notifica_plugin_plug_fk FOREIGN KEY (frontpluginid) REFERENCES car_plugin (pluginid) ON UPDATE NO ACTION ON DELETE NO ACTION, 
   CONSTRAINT car_notifica_codi_uk UNIQUE (codi)
);


create index car_notificacioapp_pk_i on car_notificacioapp (notificacioappid);
create index car_notifica_titolid_fk_i on car_notificacioapp (titolid);
create index car_notifica_missatgeid_fk_i on car_notificacioapp (missatgeid);
create index car_notifica_pluginid_fk_i on car_notificacioapp (frontpluginid);

COMMIT;