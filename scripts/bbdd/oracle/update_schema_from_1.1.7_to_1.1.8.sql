



-- 08/02/2021 Permetre Seccions en Plugins i enllaços de tipus PseudoPlugin #335

create sequence car_seccio_seq start with 1000 increment by  1;

CREATE TABLE car_seccio
(
   seccioid NUMBER(19,0) NOT NULL DEFAULT car_seccio_seq.nextval, 
   nomid NUMBER(19,0) NOT NULL, 
   descripcioid NUMBER(19,0) NOT NULL, 
   activa INTEGER NOT NULL DEFAULT 1, 
   iconaid NUMBER(19,0) NOT NULL, 
   secciopareid NUMBER(19,0)
);

ALTER TABLE car_seccio CONSTRAINT car_seccio_pk PRIMARY KEY (seccioid);
ALTER TABLE car_seccio CONSTRAINT car_seccio_traduccio_nom_fk FOREIGN KEY (nomid) REFERENCES car_traduccio (traduccioid);
ALTER TABLE car_seccio CONSTRAINT car_seccio_traduccio_des_fk FOREIGN KEY (descripcioid) REFERENCES car_traduccio (traduccioid);
ALTER TABLE car_seccio CONSTRAINT car_seccio_fitxer_icon_fk FOREIGN KEY (iconaid) REFERENCES car_fitxer (fitxerid);

create index car_seccio_pk_i on car_seccio (seccioid);

create index car_seccio_nomid_fk_i on car_seccio (nomid);

create index car_seccio_descripcioid_fk_i on car_seccio (descripcioid);

create index car_seccio_iconaid_fk_i on car_seccio (iconaid);

ALTER TABLE car_plugin  ADD COLUMN seccioid NUMBER(19,0);
ALTER TABLE car_plugin  ADD CONSTRAINT car_plugin_seccio_sec_fk FOREIGN KEY (seccioid) REFERENCES car_seccio (seccioid);
create index car_plugin_seccioid_fk_i on car_plugin (seccioid);


ALTER TABLE car_enllaz  ADD COLUMN seccioid NUMBER(19,0);
ALTER TABLE car_enllaz  ADD CONSTRAINT car_enllaz_seccio_sec_fk FOREIGN KEY (seccioid) REFERENCES car_seccio (seccioid);
create index car_enllaz_seccioid_fk_i on car_enllaz (seccioid);

ALTER TABLE car_seccio ADD COLUMN entitatid NUMBER(19,0) NOT NULL;
ALTER TABLE car_seccio ADD CONSTRAINT car_seccio_entitat_ent_fk FOREIGN KEY (entitatid) REFERENCES car_entitat (entitatid);
create index car_seccio_entitatid_fk_i on car_seccio (entitatid);

