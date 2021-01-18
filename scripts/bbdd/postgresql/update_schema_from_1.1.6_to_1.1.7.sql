-- 13/01/2021 Es crearan auditories només de les accions del backoffice #278

ALTER TABLE car_auditoria DROP COLUMN usuariclave;
ALTER TABLE car_auditoria DROP COLUMN pluginid;

-- 13/01/2021 Nou camp icona per Plugin #297
ALTER TABLE car_plugin ADD logoid int8 NULL;
ALTER TABLE car_plugin ADD CONSTRAINT car_plugin_fitxer_logo_fk FOREIGN KEY (logoid) REFERENCES car_fitxer(fitxerid);
CREATE INDEX car_plugin_logoid_fk_i ON car_plugin using btree (logoid);

--14/01/2021 Refactorització d'Accesos #308
ALTER TABLE car_acces DROP COLUMN resultatautenticacio;
ALTER TABLE car_acces ADD COLUMN resultat boolean NOT NULL;
ALTER TABLE car_acces ALTER COLUMN idioma SET NOT NULL;
ALTER TABLE car_acces RENAME datadarreracces  TO dataacces;
ALTER TABLE car_acces ADD COLUMN qaa integer NOT NULL;
ALTER TABLE car_acces RENAME nivellseguretat  TO metodeautenticacio;
ALTER TABLE car_acces ALTER COLUMN resultat SET DEFAULT true;
ALTER TABLE car_acces ALTER COLUMN qaa DROP NOT NULL;
