BEGIN;

-- 18/05/2021 Poder compartir títol i subtitol d'un Plugin de BBDD amb la implementació html/reactjs del Plugin #432 

ALTER TABLE car_plugin ADD COLUMN titolllargid bigint;
ALTER TABLE car_plugin ADD CONSTRAINT car_plugin_traduccio_tllarg_fk FOREIGN KEY (titolllargid) REFERENCES car_traduccio (traduccioid) ON UPDATE NO ACTION ON DELETE NO ACTION;
create index car_plugin_titolllargid_fk_i on car_plugin (titolllargid);


ALTER TABLE car_plugin ADD COLUMN subtitolllargid bigint;  
ALTER TABLE car_plugin ADD CONSTRAINT car_plugin_traduccio_stlar_fk FOREIGN KEY (subtitolllargid) REFERENCES car_traduccio (traduccioid) ON UPDATE NO ACTION ON DELETE NO ACTION;
create index car_plugin_subtitllargid_fk_i on car_plugin (subtitolllargid);

COMMIT;