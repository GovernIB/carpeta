ALTER TABLE car_entitat
   ADD COLUMN txtinfotopes text;
ALTER TABLE car_entitat
   ADD COLUMN txtinfotopca text;

ALTER TABLE car_entitat
   ADD COLUMN txtinfobotes text;
ALTER TABLE car_entitat
   ADD COLUMN txtinfobotca text;


ALTER TABLE car_entitat
   ADD COLUMN showlastlogin boolean NOT NULL DEFAULT false;
