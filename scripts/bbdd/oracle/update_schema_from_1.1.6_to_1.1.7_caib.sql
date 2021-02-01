-- 13/01/2021 Es crearan auditories només de les accions del backoffice #278

ALTER TABLE car_auditoria DROP COLUMN usuariclave;
ALTER TABLE car_auditoria DROP COLUMN pluginid;

-- 13/01/2021 Nou camp icona per Plugin #297
ALTER TABLE car_plugin ADD logoid NUMBER(19,0);

ALTER TABLE car_plugin
  ADD CONSTRAINT car_plugin_fitxer_logo_fk FOREIGN KEY (logoid) REFERENCES car_fitxer (fitxerid);

CREATE INDEX car_plugin_logoid_fk_i on car_plugin (logoid);

--14/01/2021 Refactorització d'Accesos #308
ALTER TABLE car_acces DROP COLUMN resultatautenticacio;
ALTER TABLE car_acces ADD resultat NUMBER(1,0) NOT NULL default 1;
ALTER TABLE car_acces MODIFY idioma NOT NULL;
ALTER TABLE car_acces RENAME COLUMN datadarreracces TO dataacces;
ALTER TABLE car_acces ADD qaa NUMBER(1,0);
ALTER TABLE car_acces RENAME COLUMN nivellseguretat  TO metodeautenticacio;

--19/01/2021 Millores en la taula d'Auditories #304
ALTER TABLE car_auditoria ADD objecte varchar2(255 char);


-- 26/01/2021 Dades Personals de l'usuari emprant PINBAL #290
UPDATE car_plugin SET  propietats='# Class => org.fundaciobit.pluginsib.carpetafront.dadespersonalsreact.DadesPersonalsReactCarpetaFrontPlugin

es.caib.carpeta.pluginsib.carpetafront.dadespersonals.pinbal.baseurl=[=SP["es.caib.carpeta.pluginsib.carpetafront.dadespersonals.pinbal.baseurl"]]
es.caib.carpeta.pluginsib.carpetafront.dadespersonals.pinbal.username=[=SP["es.caib.carpeta.pluginsib.carpetafront.dadespersonals.pinbal.username"]]
es.caib.carpeta.pluginsib.carpetafront.dadespersonals.pinbal.password=[=SP["es.caib.carpeta.pluginsib.carpetafront.dadespersonals.pinbal.password"]]

# Necessari en DEV i PRE. NO es fan cridades reals sinó d,un joc de proves
# Exemples:   - 30000056Y, FUSTER
#             - 41107605G, JAUME
es.caib.carpeta.pluginsib.carpetafront.dadespersonals.pinbal.testnif=30000056Y
es.caib.carpeta.pluginsib.carpetafront.dadespersonals.pinbal.testsurname=FUSTER'  WHERE pluginid=85;


--29/01/2021 Taula de Logs i Accessos a un altre TableSpace #315
ALTER TABLE car_log RENAME COLUMN error TO error2;
ALTER TABLE car_log ADD (error CLOB);
alter table car_log move lob (error) store as CAR_LOG_ERROR_lob (tablespace carpeta_lob index CAR_LOG_ERROR_lob_i);
UPDATE car_log SET error=error2;
ALTER TABLE car_log DROP COLUMN error2;



alter table car_log move lob (excepcio) store as CAR_LOG_EXCEPCIO_lob (tablespace carpeta_lob index CAR_LOG_EXCEPCIO_lob_i);

ALTER INDEX CAR_LOG_PK REBUILD;



alter table car_plugin move lob (propietats) store as CAR_LOG_PROPIETATS_lob (tablespace carpeta_lob index CAR_LOG_PROPIETATS_lob_i);


ALTER INDEX CAR_PLUGIN_PK REBUILD;


ALTER TABLE CAR_ACCES MOVE TABLESPACE CARPETA_DADES;
ALTER INDEX CAR_ACCES_PK REBUILD;




