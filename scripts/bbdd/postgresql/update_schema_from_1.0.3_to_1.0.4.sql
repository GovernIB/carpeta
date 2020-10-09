

ALTER TABLE car_avis
  ADD COLUMN pluginfrontid bigint;

ALTER TABLE car_avis
  ADD CONSTRAINT car_avis_plugin_pfront_fk FOREIGN KEY (pluginfrontid) REFERENCES car_plugin (pluginid) ON UPDATE NO ACTION ON DELETE NO ACTION;




ALTER TABLE car_enllaz
  ADD COLUMN logoid bigint NOT NULL;
ALTER TABLE car_enllaz
  ADD CONSTRAINT car_enllaz_fitxer_logo_fk FOREIGN KEY (logoid) REFERENCES car_fitxer (fitxerid) ON UPDATE NO ACTION ON DELETE NO ACTION;
