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
ALTER TABLE public.car_auditoria ADD objecte varchar2(255 char);


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

