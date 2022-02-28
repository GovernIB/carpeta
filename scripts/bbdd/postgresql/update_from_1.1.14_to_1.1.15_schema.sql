BEGIN;

--   Crear una taula per guardar les preguntes freqüents #622 


CREATE SEQUENCE car_preguntesfrequents_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1  CACHE 1;


CREATE TABLE car_preguntesfrequents
(
   preguntesfrequentsid bigint NOT NULL DEFAULT nextval('car_preguntesfrequents_seq'), 
   enunciatid bigint NOT NULL, 
   respostaid bigint NOT NULL, 
   ordre integer NOT NULL, 
   entitatid bigint NOT NULL, 
   CONSTRAINT car_preguntesfrequents_pk PRIMARY KEY (preguntesfrequentsid), 
   CONSTRAINT car_faq_traduccio_enun_fk FOREIGN KEY (enunciatid) REFERENCES car_traduccio (traduccioid) ON UPDATE NO ACTION ON DELETE NO ACTION, 
   CONSTRAINT car_faq_traduccio_resp_fk FOREIGN KEY (respostaid) REFERENCES car_traduccio (traduccioid) ON UPDATE NO ACTION ON DELETE NO ACTION, 
   CONSTRAINT car_faq_entitat_ent_fk FOREIGN KEY (entitatid) REFERENCES car_entitat (entitatid) ON UPDATE NO ACTION ON DELETE NO ACTION
);


create index car_preguntesfrequents_pk_i on car_preguntesfrequents (preguntesfrequentsid);

create index car_faq_enunciatid_fk_i on car_preguntesfrequents (enunciatid);

create index car_faq_respostaid_fk_i on car_preguntesfrequents (respostaid);

create index car_faq_entitatid_fk_i on car_preguntesfrequents (entitatid);



ALTER TABLE car_acces ADD COLUMN idsessio character varying(255);

--   Millores a les cerques dins Accessos i Logs del Backoffice #633
ALTER TABLE car_log ADD COLUMN idsessio text;


COMMIT;