BEGIN;

-- 30/11/2020  FRONT: Diàleg d'ajuda al Front #261

ALTER TABLE car_entitat
  ADD COLUMN suportfaq character varying(255);
ALTER TABLE car_entitat
  ADD COLUMN suportqssi character varying(255);
ALTER TABLE car_entitat
  ADD COLUMN suportautenticacio character varying(255);
COMMENT ON COLUMN car_entitat.suportfaq IS 'Preguntes Freqüents';
COMMENT ON COLUMN car_entitat.suportqssi IS ' Queixes i suggeriments ';
COMMENT ON COLUMN car_entitat.suportautenticacio IS 'Suport autenticació Front';


-- 30/11/2020 CAR_AUDITORIA té una FK amb usuari #258

ALTER TABLE car_auditoria
  ADD COLUMN username character varying(255);
  
UPDATE car_auditoria t1 SET username=t2.username
FROM   car_usuari t2
JOIN   car_usuari t3 USING (usuariid)
WHERE  t1.usuariid=t2.usuariid;

ALTER TABLE car_auditoria DROP COLUMN usuariid;


-- 01/12/2020   Traduccio text Login

ALTER TABLE car_entitat
  ADD COLUMN logintextid bigint;
ALTER TABLE car_entitat
  ADD CONSTRAINT car_entitat_traduccio_log_fk FOREIGN KEY (logintextid) REFERENCES car_traduccio (traduccioid) ON UPDATE NO ACTION ON DELETE NO ACTION;

create index car_entitat_logintextid_fk_i on car_entitat (logintextid);


-- 14/12/2020 Implementar Plugin de Notificacions #270 

INSERT INTO car_traduccio VALUES (66);
INSERT INTO car_traduccio VALUES (76);

INSERT INTO car_traducciomap VALUES (66, 'en', 'Notifications');
INSERT INTO car_traducciomap VALUES (66, 'ca', 'Notificacions');
INSERT INTO car_traducciomap VALUES (66, 'es', 'Notificaciones');
INSERT INTO car_traducciomap VALUES (76, 'en', 'Notifications');
INSERT INTO car_traducciomap VALUES (76, 'ca', 'Notificacions');
INSERT INTO car_traducciomap VALUES (76, 'es', 'Notificaciones');



INSERT INTO car_plugin(nomid, classe, tipus, propietats, actiu, descripcioid) VALUES (66, 'org.fundaciobit.pluginsib.carpetafront.notib.NotibCarpetaFrontPlugin', 1, '# Class => org.fundaciobit.pluginsib.carpetafront.notib.NotibCarpetaFrontPlugin

# Common
es.caib.carpeta.pluginsib.carpetafront.notib.development=true

#
# Sistra 1
es.caib.carpeta.pluginsib.carpetafront.notib.sistra1.url=[=SP["es.caib.carpeta.pluginsib.carpetafront.sistra1.url"]]
es.caib.carpeta.pluginsib.carpetafront.notib.sistra1.user=[=SP["es.caib.carpeta.pluginsib.carpetafront.sistra1.user"]]
es.caib.carpeta.pluginsib.carpetafront.notib.sistra1.pass=[=SP["es.caib.carpeta.pluginsib.carpetafront.sistra1.pass"]]

# NOTIFICACIONES
es.caib.carpeta.pluginsib.carpetafront.notib.notificaciones.url=https://sede.administracion.gob.es/carpeta/notificaciones/notifica/consultaNotificaciones.htm', true, 76);

-- 15/12/2020  Revisió i adaptació de l'estructura de dades per soportar la funcionalitat d'accessos #268
ALTER TABLE car_acces ADD COLUMN tipus integer NOT NULL;
ALTER TABLE car_acces ADD COLUMN pluginid integer;


-- 21/12/2020 Refactoritzar estadístiques #286
TRUNCATE TABLE car_estadistica;

-- 22/12/2020  Implementar Plugin de Dades Personals #283 
INSERT INTO car_traduccio VALUES (86);
INSERT INTO car_traduccio VALUES (87);

INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor) VALUES (86, 'en', 'Personal Information');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor) VALUES (86, 'ca', 'Dades Personals');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor) VALUES (86, 'es', 'Datos Personales');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor) VALUES (87, 'en', 'Personal Information');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor) VALUES (87, 'ca', 'Dades Personals');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor) VALUES (87, 'es', 'Datos Personales');


INSERT INTO car_plugin(pluginid, nomid, classe, tipus, propietats, actiu, descripcioid) VALUES (85, 86, 'org.fundaciobit.pluginsib.carpetafront.dadespersonalsreact.DadesPersonalsReactCarpetaFrontPlugin', 1, NULL, true, 87);
COMMIT;



