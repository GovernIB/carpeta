
BEGIN;
-- 30/03/2021 Nou camp actiu a Enllaços i Seccions #417

ALTER TABLE car_enllaz ADD COLUMN actiu boolean NOT NULL DEFAULT true;


COMMIT;