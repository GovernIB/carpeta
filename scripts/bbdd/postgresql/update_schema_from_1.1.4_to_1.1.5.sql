﻿-- 02/11/2020 Revisió i adaptació de l'estructura de dades per soportar la funcionalitat d'Estadistiques #211

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