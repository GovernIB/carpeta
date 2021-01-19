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

