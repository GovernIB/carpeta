

-- 30/11/2020  FRONT: Diàleg d'ajuda al Front #261

ALTER TABLE car_entitat
  ADD suportfaq VARCHAR2(255);
ALTER TABLE car_entitat
  ADD suportqssi VARCHAR2(255);
ALTER TABLE car_entitat
  ADD suportautenticacio VARCHAR2(255);
COMMENT ON COLUMN car_entitat.suportfaq IS 'Preguntes Freqüents';
COMMENT ON COLUMN car_entitat.suportqssi IS ' Queixes i suggeriments ';
COMMENT ON COLUMN car_entitat.suportautenticacio IS 'Suport autenticació Front';


-- 30/11/2020 CAR_AUDITORIA té una FK amb usuari #258

ALTER TABLE car_auditoria
  ADD  username VARCHAR2(255);
  
MERGE INTO car_auditoria t1 USING (SELECT * FROM car_usuari) t2 ON(t1.usuariid = t2.usuariid) WHEN MATCHED THEN UPDATE SET t1.username = t2.username;

ALTER TABLE car_auditoria DROP COLUMN usuariid;


-- 01/12/2020   Traduccio text Login

ALTER TABLE car_entitat ADD logintextid NUMBER(19,0);

ALTER TABLE car_entitat
  ADD CONSTRAINT car_entitat_traduccio_log_fk FOREIGN KEY (logintextid) REFERENCES car_traduccio (traduccioid);

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



INSERT INTO car_plugin(pluginid, nomid, classe, tipus, propietats, actiu, descripcioid) VALUES (3, 66, 'org.fundaciobit.pluginsib.carpetafront.notib.NotibCarpetaFrontPlugin', 1, '# Class => org.fundaciobit.pluginsib.carpetafront.notib.NotibCarpetaFrontPlugin

# Common
es.caib.carpeta.pluginsib.carpetafront.notib.development=true

#
# Sistra 1
es.caib.carpeta.pluginsib.carpetafront.notib.sistra1.url=[=SP["es.caib.carpeta.pluginsib.carpetafront.sistra1.url"]]
es.caib.carpeta.pluginsib.carpetafront.notib.sistra1.user=[=SP["es.caib.carpeta.pluginsib.carpetafront.sistra1.user"]]
es.caib.carpeta.pluginsib.carpetafront.notib.sistra1.pass=[=SP["es.caib.carpeta.pluginsib.carpetafront.sistra1.pass"]]

# NOTIFICACIONES
es.caib.carpeta.pluginsib.carpetafront.notib.notificaciones.url=https://sede.administracion.gob.es/carpeta/notificaciones/notifica/consultaNotificaciones.htm', true, 76);