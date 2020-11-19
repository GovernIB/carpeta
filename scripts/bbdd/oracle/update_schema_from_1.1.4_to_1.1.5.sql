-- 02/11/2020 Revisió i adaptació de l'estructura de dades per soportar la funcionalitat d'Estadistiques #211
ALTER TABLE car_estadistica DROP COLUMN accesid;
ALTER TABLE car_estadistica DROP COLUMN element;
ALTER TABLE car_estadistica MODIFY entitatid NULL;
ALTER TABLE car_estadistica RENAME COLUMN accio TO tipus;
ALTER TABLE car_estadistica ADD comptador number(19,0) DEFAULT 0 not null;
ALTER TABLE car_estadistica ADD pluginid number(19,0);



--10/11/2020 Revisió i adaptació de l'estructura de dades per soportar la funcionalitat d'Auditories #216
ALTER TABLE car_auditoria DROP COLUMN element;
ALTER TABLE car_estadistica RENAME COLUMN accio TO tipus;
ALTER TABLE car_auditoria ADD ticketloginib varchar2(256 char);
ALTER TABLE car_auditoria ADD pluginid number(19,0);


--10/11/2020 Gestió de les Auditories al backoffice #204 Canviat nom de la columna
ALTER TABLE car_auditoria RENAME COLUMN ticketloginib  TO usuariclave;


--13/11/2020 Desvincular el pluginid i entitatid de la taula car_log #156
ALTER TABLE car_log DROP COLUMN entitatid;
ALTER TABLE car_log ADD entitatcodi varchar2(9 char);
ALTER TABLE car_log DROP CONSTRAINT car_log_entitat_ent_fk;
ALTER TABLE car_log DROP CONSTRAINT car_log_plugin_plu_fk;