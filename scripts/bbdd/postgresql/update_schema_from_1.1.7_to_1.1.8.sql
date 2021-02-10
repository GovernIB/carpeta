

-- 08/02/2021 Permetre Seccions en Plugins i enllaços de tipus PseudoPlugin #335

create sequence car_seccio_seq start with 1000 increment by  1;

CREATE TABLE car_seccio
(
   seccioid bigint NOT NULL DEFAULT nextval('car_seccio_seq'), 
   nomid bigint NOT NULL, 
   descripcioid bigint NOT NULL, 
   activa boolean NOT NULL DEFAULT true, 
   iconaid bigint NOT NULL, 
   secciopareid bigint, 
   CONSTRAINT car_seccio_pk PRIMARY KEY (seccioid), 
   CONSTRAINT car_seccio_traduccio_nom_fk FOREIGN KEY (nomid) REFERENCES car_traduccio (traduccioid) ON UPDATE NO ACTION ON DELETE NO ACTION, 
   CONSTRAINT car_seccio_traduccio_des_fk FOREIGN KEY (descripcioid) REFERENCES car_traduccio (traduccioid) ON UPDATE NO ACTION ON DELETE NO ACTION, 
   CONSTRAINT car_seccio_fitxer_icon_fk FOREIGN KEY (iconaid) REFERENCES car_fitxer (fitxerid) ON UPDATE NO ACTION ON DELETE NO ACTION
);

create index car_seccio_pk_i on car_seccio (seccioid);

create index car_seccio_nomid_fk_i on car_seccio (nomid);

create index car_seccio_descripcioid_fk_i on car_seccio (descripcioid);

create index car_seccio_iconaid_fk_i on car_seccio (iconaid);

ALTER TABLE car_enllaz  ADD COLUMN seccioid bigint;
ALTER TABLE car_enllaz  ADD CONSTRAINT car_enllaz_seccio_sec_fk FOREIGN KEY (seccioid) REFERENCES car_seccio (seccioid) ON UPDATE NO ACTION ON DELETE NO ACTION;
create index car_enllaz_seccioid_fk_i on car_enllaz (seccioid);

ALTER TABLE car_seccio ADD COLUMN entitatid bigint NOT NULL;
ALTER TABLE car_seccio ADD CONSTRAINT car_seccio_entitat_ent_fk FOREIGN KEY (entitatid) REFERENCES car_entitat (entitatid) ON UPDATE NO ACTION ON DELETE NO ACTION;
create index car_seccio_entitatid_fk_i on car_seccio (entitatid);

ALTER TABLE car_pluginentitat  ADD COLUMN seccioid bigint;
ALTER TABLE car_pluginentitat  ADD CONSTRAINT car_plugent_seccio_sec_fk FOREIGN KEY (seccioid) REFERENCES car_seccio (seccioid) ON UPDATE NO ACTION ON DELETE NO ACTION;
create index car_plugent_seccioid_fk_i on car_pluginentitat (seccioid);
