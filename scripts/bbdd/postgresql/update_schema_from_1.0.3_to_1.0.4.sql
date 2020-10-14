

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

 create index car_enllaz_logoid_fk_i on car_enllaz (logoid);
 

-- 13/10/2020  Gestión de avisos de mantenimiento del Back #144 
 ALTER TABLE car_avis  ADD COLUMN gravetat integer NOT NULL DEFAULT 1;
 
 
 
-- 14/10/2020  Afegir més dades a la taula d'Entitat #147 
  
ALTER TABLE car_entitat
  ADD COLUMN iconid bigint NOT NULL;
ALTER TABLE car_entitat
  ADD CONSTRAINT car_entitat_fitxer_icon_fk FOREIGN KEY (iconid) REFERENCES car_fitxer (fitxerid) ON UPDATE NO ACTION ON DELETE NO ACTION;


ALTER TABLE car_entitat
  ADD COLUMN suportweb character varying(255);
ALTER TABLE car_entitat
  ADD COLUMN suportemail character varying(255);
ALTER TABLE car_entitat
  ADD COLUMN suporttelefon character varying(255);
ALTER TABLE car_entitat
  ADD COLUMN entitatdescfront character varying(4000) NOT NULL;
ALTER TABLE car_entitat
  ADD COLUMN webentitat character varying(255) NOT NULL;
  
  ALTER TABLE car_entitat
  ADD COLUMN pluginloginid bigint;
ALTER TABLE car_entitat
  ADD CONSTRAINT car_entitat_plugin_login_fk FOREIGN KEY (pluginloginid) REFERENCES car_plugin (pluginid) ON UPDATE NO ACTION ON DELETE NO ACTION;
  
  
  
create index car_entitat_logocapback_fk_i on car_entitat (logocapbackid);

create index car_entitat_logopeuback_fk_i on car_entitat (logopeubackid);

create index car_entitat_logolatfront_fk_i on car_entitat (logolateralfrontid);

create index car_entitat_iconid_fk_i on car_entitat (iconid);

create index car_entitat_pluginloginid_fk_i on car_entitat (pluginloginid);