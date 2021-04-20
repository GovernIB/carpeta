

-- 30/11/2020  FRONT: Diàleg d'ajuda al Front #261

ALTER TABLE car_entitat
  ADD suportfaq VARCHAR2(255);
ALTER TABLE car_entitat
  ADD suportqssi VARCHAR2(255);
ALTER TABLE car_entitat
  ADD suportautenticacio VARCHAR2(255);
COMMENT ON COLUMN car_entitat.suportfaq IS 'Preguntes Freqüents';
COMMENT ON COLUMN car_entitat.suportqssi IS ' Queixes i suggeriments ';
COMMENT ON COLUMN car_entitat.suportautenticacio IS 'Suport autenticació Front';


-- 30/11/2020 CAR_AUDITORIA té una FK amb usuari #258

ALTER TABLE car_auditoria
  ADD  username VARCHAR2(255);
  
MERGE INTO car_auditoria t1 USING (SELECT * FROM car_usuari) t2 ON(t1.usuariid = t2.usuariid) WHEN MATCHED THEN UPDATE SET t1.username = t2.username;

ALTER TABLE car_auditoria DROP COLUMN usuariid;


-- 01/12/2020   Traduccio text Login

ALTER TABLE car_entitat ADD logintextid NUMBER(19,0);

ALTER TABLE car_entitat
  ADD CONSTRAINT car_entitat_traduccio_log_fk FOREIGN KEY (logintextid) REFERENCES car_traduccio (traduccioid);

create index car_entitat_logintextid_fk_i on car_entitat (logintextid);


-- 15/12/2020  Revisió i adaptació de l'estructura de dades per soportar la funcionalitat d'accessos #268
ALTER TABLE car_acces ADD tipus NUMBER(19,0) not null;
ALTER TABLE car_acces ADD pluginid NUMBER(19,0);

