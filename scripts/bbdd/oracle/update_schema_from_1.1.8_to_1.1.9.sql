﻿

-- 30/03/2021 Nou camp actiu a Enllaços i Seccions #417

ALTER TABLE car_enllaz ADD actiu number(1,0) DEFAULT 1 NOT NULL;

-- 30/03/2021 Mostrar els plugins, enllaços i seccions en un ordre determinat #418

ALTER TABLE car_pluginentitat ADD ordre number(10,0) DEFAULT 1 NOT NULL;

ALTER TABLE car_enllaz ADD ordre number(10,0) DEFAULT 1 NOT NULL;

ALTER TABLE car_seccio ADD ordre number(10,0) DEFAULT 1 NOT NULL;




-- 30/03/2021  Crear nou camp Descripció a Entitat #435

ALTER TABLE car_entitat ADD descripcioid number(19,0);

ALTER TABLE car_entitat ADD CONSTRAINT car_entitat_traduccio_des_fk FOREIGN KEY (descripcioid) REFERENCES car_traduccio (traduccioid);

create index car_entitat_descripcioid_fk_i on car_entitat (descripcioid);

-- 07/04/2021  Procés automàtic per esborrar els logs de forma periòdica #344 

INSERT INTO car_propietatglobal (propietatglobalid, codi, value, descripcio, entitatid) VALUES (101, 'es.caib.carpeta.esborrarlogs.hora', '4', '<p>Valor enter, entre les 0 i 23 que representa l''hora en format 24H que volem que s''executi la tasca d''esborrar logs. <br /><br />IMPORTANT: Si es fa un canvi en aquest valor, s''ha de reiniciar el Jboss per a que tingui efecte.</p>', NULL);

INSERT INTO car_propietatglobal (propietatglobalid, codi, value, descripcio, entitatid) VALUES (102, 'es.caib.carpeta.esborrarlogs.dies', '90', '<p>Valor enter que representa el n&uacute;mero de dies que volem mantenir els logs. <br /><br />IMPORTANT: Si es fa un canvi en aquest valor, s''ha de reiniciar el Jboss per a que tingui efecte.</p>', NULL);


