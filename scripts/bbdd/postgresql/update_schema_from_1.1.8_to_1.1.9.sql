
BEGIN;
-- 30/03/2021 Nou camp actiu a Enllaços i Seccions #417

ALTER TABLE car_enllaz ADD COLUMN actiu boolean NOT NULL DEFAULT true;


-- 30/03/2021 Mostrar els plugins, enllaços i seccions en un ordre determinat #418

ALTER TABLE car_pluginentitat ADD COLUMN ordre integer NOT NULL DEFAULT 1;

ALTER TABLE car_enllaz ADD COLUMN ordre integer NOT NULL DEFAULT 1;

ALTER TABLE car_seccio ADD COLUMN ordre integer NOT NULL DEFAULT 1;


COMMIT;