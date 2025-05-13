ALTER TABLE car_entitat ADD txtinfotopes CLOB;
ALTER TABLE car_entitat ADD txtinfotopca CLOB;
ALTER TABLE car_entitat ADD txtinfobotes CLOB;
ALTER TABLE car_entitat ADD txtinfobotca CLOB;


ALTER TABLE car_entitat
   ADD (showlastlogin NUMBER(1) DEFAULT 0 NOT NULL);