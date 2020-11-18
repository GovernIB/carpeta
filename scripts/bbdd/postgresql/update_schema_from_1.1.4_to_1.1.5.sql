-- 02/11/2020 Revisió i adaptació de l'estructura de dades per soportar la funcionalitat d'Estadistiques #211

ALTER TABLE public.car_estadistica
  DROP COLUMN accesid;
ALTER TABLE public.car_estadistica
  DROP COLUMN element;
ALTER TABLE public.car_estadistica
   ALTER COLUMN entitatid DROP NOT NULL;
ALTER TABLE public.car_estadistica RENAME accio  TO tipus;
ALTER TABLE public.car_estadistica
  ADD COLUMN comptador integer NOT NULL DEFAULT 0;
ALTER TABLE public.car_estadistica
  ADD COLUMN pluginid bigint;



--10/11/2020 Revisió i adaptació de l'estructura de dades per soportar la funcionalitat d'Auditories #216

ALTER TABLE public.car_auditoria
  DROP COLUMN element;
ALTER TABLE public.car_auditoria RENAME accio  TO tipus;
ALTER TABLE public.car_auditoria
  ADD COLUMN ticketloginib character varying(256);
ALTER TABLE public.car_auditoria
  ADD COLUMN pluginid integer;


--10/11/2020 Gestió de les Auditories al backoffice #204 Canviat nom de la columna
ALTER TABLE public.car_auditoria RENAME ticketloginib  TO "usuariclave";


--13/11/2020 Desvincular el pluginid i entitatid de la taula car_log #156
ALTER TABLE public.car_log
  DROP COLUMN entitatcodi;
ALTER TABLE public.car_log
  ADD COLUMN entitatcodi character varying(9);
ALTER TABLE public.car_log RENAME entitatid  TO "entitatcodi";
ALTER TABLE public.car_log
  DROP CONSTRAINT car_log_entitat_ent_fk;
ALTER TABLE public.car_log
  DROP CONSTRAINT car_log_plugin_plu_fk;


