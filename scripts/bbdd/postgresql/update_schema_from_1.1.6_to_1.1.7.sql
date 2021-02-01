-- 13/01/2021 Es crearan auditories només de les accions del backoffice #278

ALTER TABLE car_auditoria DROP COLUMN usuariclave;
ALTER TABLE car_auditoria DROP COLUMN pluginid;

-- 13/01/2021 Nou camp icona per Plugin #297
ALTER TABLE car_plugin ADD logoid int8 NULL;
ALTER TABLE car_plugin ADD CONSTRAINT car_plugin_fitxer_logo_fk FOREIGN KEY (logoid) REFERENCES car_fitxer(fitxerid);
CREATE INDEX car_plugin_logoid_fk_i ON car_plugin using btree (logoid);

-- 14/01/2021 Refactorització d'Accesos #308
ALTER TABLE car_acces DROP COLUMN resultatautenticacio;
ALTER TABLE car_acces ADD COLUMN resultat boolean NOT NULL DEFAULT true;
ALTER TABLE car_acces ALTER COLUMN idioma SET NOT NULL;
ALTER TABLE car_acces RENAME datadarreracces  TO dataacces;
ALTER TABLE car_acces ADD COLUMN qaa integer;
ALTER TABLE car_acces RENAME nivellseguretat  TO metodeautenticacio;


-- 19/01/2021 Millores en la taula d'Auditories #304
ALTER TABLE car_auditoria ADD COLUMN objecte character varying(255);



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
ALTER TABLE car_log ALTER COLUMN error TYPE text;


