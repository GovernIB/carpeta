-- create index car_acces_pk_i on car_acces (accesid);
-- create index car_auditoria_pk_i on car_auditoria (auditoriaid);
-- create index car_avis_pk_i on car_avis (avisid);
create index car_avis_entitatid_fk_i on car_avis (entitatid);
-- create index car_enllaz_pk_i on car_enllaz (enllazid);
create index car_enllaz_entitatid_fk_i on car_enllaz (entitatid);
create index car_enllaz_nomid_fk_i on car_enllaz (nomid);
create index car_enllaz_urlid_fk_i on car_enllaz (urlid);
create index car_entitat_descripcioid_fk_i on car_entitat (descripcioid);
create index car_entitat_iconid_fk_i on car_entitat (iconid);
create index car_entitat_logocapback_fk_i on car_entitat (logocapbackid);
create index car_entitat_logopeuback_fk_i on car_entitat (logopeubackid);
create index car_entitat_pluginloginid_fk_i on car_entitat (pluginloginid);
create index car_estadistica_entitatid_fk_i on car_estadistica (entitatid);
-- create index car_idioma_pk_i on car_idioma (idiomaid);
create index car_log_pluginid_fk_i on car_log (pluginid);
create index car_plugin_descripcioid_fk_i on car_plugin (descripcioid);
create index car_plugin_nomid_fk_i on car_plugin (nomid);
create index car_plugin_titolllargid_fk_i on car_plugin (titolllargid);
create index car_plugent_entitatid_fk_i on car_pluginentitat (entitatid);
create index car_plugent_seccioid_fk_i on car_pluginentitat (seccioid);
create index car_propglob_entitatid_fk_i on car_propietatglobal (entitatid);
create index car_seccio_descripcioid_fk_i on car_seccio (descripcioid);
create index car_seccio_iconaid_fk_i on car_seccio (iconaid);
-- create index car_usuari_pk_i on car_usuari (usuariid);
create index car_usuari_idiomaid_fk_i on car_usuari (idiomaid);
create index car_usuent_entitatid_fk_i on car_usuarientitat (entitatid);
