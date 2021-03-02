



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

ALTER TABLE car_enllaz  ADD COLUMN seccioid NUMBER(19,0);
ALTER TABLE car_enllaz  ADD CONSTRAINT car_enllaz_seccio_sec_fk FOREIGN KEY (seccioid) REFERENCES car_seccio (seccioid);
create index car_enllaz_seccioid_fk_i on car_enllaz (seccioid);

ALTER TABLE car_seccio ADD COLUMN entitatid NUMBER(19,0) NOT NULL;
ALTER TABLE car_seccio ADD CONSTRAINT car_seccio_entitat_ent_fk FOREIGN KEY (entitatid) REFERENCES car_entitat (entitatid);
create index car_seccio_entitatid_fk_i on car_seccio (entitatid);

ALTER TABLE car_pluginentitat  ADD COLUMN seccioid NUMBER(19,0);
ALTER TABLE car_pluginentitat  ADD CONSTRAINT car_plugent_seccio_sec_fk FOREIGN KEY (seccioid) REFERENCES car_seccio (seccioid);
create index car_plugent_seccioid_fk_i on car_pluginentitat (seccioid);



-- 15/02/2021 Afegir un camp descripció traduïble a la taula d'enllaz #351 

ALTER TABLE car_enllaz ADD COLUMN descripcioid NUMBER(19,0);
ALTER TABLE car_enllaz ADD CONSTRAINT car_enllaz_traduccio_desid_fk FOREIGN KEY (descripcioid) REFERENCES car_traduccio (traduccioid);
create index car_enllaz_descripcioid_fk_i on car_enllaz (descripcioid);


--11/02/2021 Nou plugin de Registre atacants als nous mètodes de RegWeb 3.2 #333
INSERT INTO car_traduccio VALUES (96);
INSERT INTO car_traduccio VALUES (97);

INSERT INTO car_traducciomap VALUES (96, 'en', 'Regweb 3.2');
INSERT INTO car_traducciomap VALUES (96, 'ca', 'Regweb 3.2');
INSERT INTO car_traducciomap VALUES (96, 'es', 'Regweb 3.2');
INSERT INTO car_traducciomap VALUES (97, 'en', 'Regweb 3.2');
INSERT INTO car_traducciomap VALUES (97, 'ca', 'Regweb 3.2');
INSERT INTO car_traducciomap VALUES (97, 'es', 'Regweb 3.2');



INSERT INTO car_plugin(nomid, classe, tipus, propietats, actiu, descripcioid) VALUES (96, 'org.fundaciobit.pluginsib.carpetafront.regweb32.Regweb32CarpetaFrontPlugin', 1, '# Common
es.caib.carpeta.pluginsib.carpetafront.regweb32.development=true
es.caib.carpeta.pluginsib.carpetafront.regweb32.concsv.url=https://dev.caib.es/concsv/view.xhtml?hash=
# Regweb3.2
es.caib.carpeta.pluginsib.carpetafront.regweb32.url=[=SP["es.caib.carpeta.pluginsib.carpetafront.regweb32.url"]]
es.caib.carpeta.pluginsib.carpetafront.regweb32.user=[=SP["es.caib.carpeta.pluginsib.carpetafront.regweb32.user"]]
es.caib.carpeta.pluginsib.carpetafront.regweb32.pass=[=SP["es.caib.carpeta.pluginsib.carpetafront.regweb32.pass"]]
es.caib.carpeta.pluginsib.carpetafront.regweb32.entidad=A04003003', 1, 97);

