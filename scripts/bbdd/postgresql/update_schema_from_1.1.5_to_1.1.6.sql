

-- 30/11/2020  FRONT: Diàleg d'ajuda al Front #261

ALTER TABLE car_entitat
  ADD COLUMN suportfaq character varying(255);
ALTER TABLE car_entitat
  ADD COLUMN suportqssi character varying(255);
ALTER TABLE car_entitat
  ADD COLUMN suportautenticacio character varying(255);
COMMENT ON COLUMN car_entitat.suportfaq IS 'Preguntes Freqüents';
COMMENT ON COLUMN car_entitat.suportqssi IS ' Queixes i suggeriments ';
COMMENT ON COLUMN car_entitat.suportautenticacio IS 'Suport autenticació Front';


-- 30/11/2020 CAR_AUDITORIA té una FK amb usuari #258

ALTER TABLE car_auditoria
  ADD COLUMN username character varying(255);
  
UPDATE car_auditoria t1 SET username=t2.username
FROM   car_usuari t2
JOIN   car_usuari t3 USING (usuariid)
WHERE  t1.usuariid=t2.usuariid;

ALTER TABLE car_auditoria DROP COLUMN usuariid;


-- 01/12/2020   Traduccio text Login

ALTER TABLE car_entitat
  ADD COLUMN logintextid bigint;
ALTER TABLE car_entitat
  ADD CONSTRAINT car_entitat_traduccio_log_fk FOREIGN KEY (logintextid) REFERENCES car_traduccio (traduccioid) ON UPDATE NO ACTION ON DELETE NO ACTION;

create index car_entitat_logintextid_fk_i on car_entitat (logintextid);