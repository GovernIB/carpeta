

-- 18/05/2021 Poder compartir títol i subtitol d'un Plugin de BBDD amb la implementació html/reactjs del Plugin #432 


ALTER TABLE car_plugin ADD titolllargid NUMBER(19,0);
ALTER TABLE car_plugin ADD CONSTRAINT car_plugin_traduccio_tllarg_fk FOREIGN KEY (titolllargid) REFERENCES car_traduccio (traduccioid);
create index car_plugin_titolllargid_fk_i on car_plugin (titolllargid);


ALTER TABLE car_plugin ADD subtitolllargid NUMBER(19,0);  
ALTER TABLE car_plugin ADD CONSTRAINT car_plugin_traduccio_stlar_fk FOREIGN KEY (subtitolllargid) REFERENCES car_traduccio (traduccioid);
create index car_plugin_subtitllargid_fk_i on car_plugin (subtitolllargid);

