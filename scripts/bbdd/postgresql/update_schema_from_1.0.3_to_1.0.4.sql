

-- 06-10-2020 Afegir gestio d'Enllaços a Back #138

ALTER TABLE car_avis
  ADD COLUMN pluginfrontid bigint;

ALTER TABLE car_avis
  ADD CONSTRAINT car_avis_plugin_pfront_fk FOREIGN KEY (pluginfrontid) REFERENCES car_plugin (pluginid) ON UPDATE NO ACTION ON DELETE NO ACTION;

 create index car_avis_pluginfrontid_fk_i on car_avis (pluginfrontid);

ALTER TABLE car_enllaz
  ADD COLUMN logoid bigint NOT NULL;
ALTER TABLE car_enllaz
  ADD CONSTRAINT car_enllaz_fitxer_logo_fk FOREIGN KEY (logoid) REFERENCES car_fitxer (fitxerid) ON UPDATE NO ACTION ON DELETE NO ACTION;

-- Es recomanable tenir un index de la clau forania.
 create index car_enllaz_logoid_fk_i on car_enllaz (logoid);
 

-- 13/10/2020  Gestión de avisos de mantenimiento del Back #144 
 ALTER TABLE public.car_avis
  ADD COLUMN gravetat integer NOT NULL DEFAULT 1;