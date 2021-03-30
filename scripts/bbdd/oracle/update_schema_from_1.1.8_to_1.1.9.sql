
BEGIN;
-- 30/03/2021 Nou camp actiu a Enllaços i Seccions #417

ALTER TABLE car_enllaz ADD COLUMN actiu number(1,0) not null DEFAULT 1;

-- 30/03/2021 Mostrar els plugins, enllaços i seccions en un ordre determinat #418

ALTER TABLE car_pluginentitat ADD COLUMN ordre number(10,0) NOT NULL DEFAULT 1;

ALTER TABLE car_enllaz ADD COLUMN ordre number(10,0) NOT NULL DEFAULT 1;

ALTER TABLE car_seccio ADD COLUMN ordre number(10,0) NOT NULL DEFAULT 1;


-- 30/03/2021  Crear nou camp Descripció a Entitat #435

ALTER TABLE car_entitat ADD COLUMN descripcioid number(19,0);

ALTER TABLE car_entitat ADD CONSTRAINT car_entitat_traduccio_des_fk FOREIGN KEY (descripcioid) REFERENCES car_traduccio (traduccioid);

create index car_entitat_descripcioid_fk_i on car_entitat (descripcioid);


COMMIT;