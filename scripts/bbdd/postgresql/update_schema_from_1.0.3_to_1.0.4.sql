

ALTER TABLE car_avis
  ADD COLUMN pluginfrontid bigint;

ALTER TABLE car_avis
  ADD CONSTRAINT car_avis_plugin_pfront_fk FOREIGN KEY (pluginfrontid) REFERENCES car_plugin (pluginid) ON UPDATE NO ACTION ON DELETE NO ACTION;

