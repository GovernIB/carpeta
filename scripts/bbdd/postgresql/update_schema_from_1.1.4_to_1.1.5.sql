-- 02/11/2020 issue #211

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
