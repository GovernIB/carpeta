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


--29/01/2021 Taula de Logs i Accessos a un altre TableSpace #315
ALTER TABLE car_log RENAME COLUMN error TO error2;
ALTER TABLE car_log ADD (error CLOB);
UPDATE car_log SET error=error2;
ALTER TABLE car_log DROP COLUMN error2;

ALTER INDEX CAR_LOG_PK REBUILD;




