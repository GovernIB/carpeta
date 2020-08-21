
 -- INICI PKs
    alter table car_acces add constraint car_acces_pk primary key (accesid);

    alter table car_auditoria add constraint car_auditoria_pk primary key (auditoriaid);

    alter table car_avis add constraint car_avis_pk primary key (avisid);

    alter table car_enllaz add constraint car_enllaz_pk primary key (enllazid);

    alter table car_entitat add constraint car_entitat_pk primary key (entitatid);

    alter table car_estadistica add constraint car_estadistica_pk primary key (estadisticaid);

    alter table car_fitxer add constraint car_fitxer_pk primary key (fitxerid);

    alter table car_idioma add constraint car_idioma_pk primary key (idiomaid);

    alter table car_log add constraint car_log_pk primary key (logid);

    alter table car_plugin add constraint car_plugin_pk primary key (pluginid);

    alter table car_pluginentitat add constraint car_pluginentitat_pk primary key (pluginentitatid);

    alter table car_propietatglobal add constraint car_propietatglobal_pk primary key (propietatglobalid);

    alter table car_traduccio add constraint car_traduccio_pk primary key (traduccioid);

    alter table car_traducciomap add constraint car_traducmap_pk primary key (traducciomapid, idiomaid);

    alter table car_usuari add constraint car_usuari_pk primary key (usuariid);

    alter table car_usuarientitat add constraint car_usuarientitat_pk primary key (usuarientitatid);

 -- FINAL PKs


 -- INICI FKs

    alter table car_acces 
       add constraint car_acces_entitat_entitatid_fk 
       foreign key (entitatid) 
       references car_entitat;

    alter table car_auditoria 
       add constraint car_audit_entitat_ent_fk 
       foreign key (entitatid) 
       references car_entitat;

    alter table car_auditoria 
       add constraint car_audit_usuari_usu_fk 
       foreign key (usuariid) 
       references car_usuari;

    alter table car_avis 
       add constraint car_avis_traduccio_desc_fk 
       foreign key (descripcioid) 
       references car_traduccio;

    alter table car_avis 
       add constraint car_avis_entitat_ent_fk 
       foreign key (entitatid) 
       references car_entitat;

    alter table car_enllaz 
       add constraint car_enllaz_entitat_ent_fk 
       foreign key (entitatid) 
       references car_entitat;

    alter table car_enllaz 
       add constraint car_enllaz_traduccio_nomid_fk 
       foreign key (nomid) 
       references car_traduccio;

    alter table car_enllaz 
       add constraint car_enllaz_traduccio_urlid_fk 
       foreign key (urlid) 
       references car_traduccio;

    alter table car_entitat 
       add constraint car_entitat_fitxer_css_fk 
       foreign key (fitxercss) 
       references car_fitxer;

    alter table car_entitat 
       add constraint car_entitat_fitxer_logom_fk 
       foreign key (logomenuid) 
       references car_fitxer;

    alter table car_entitat 
       add constraint car_entitat_fitxer_logop_fk 
       foreign key (logopeuid) 
       references car_fitxer;

    alter table car_entitat 
       add constraint car_entitat_traduccio_nom_fk 
       foreign key (nomid) 
       references car_traduccio;

    alter table car_estadistica 
       add constraint car_estadis_acces_ac_fk 
       foreign key (accesid) 
       references car_acces;

    alter table car_estadistica 
       add constraint car_estadis_entitat_ent_fk 
       foreign key (entitatid) 
       references car_entitat;

    alter table car_log 
       add constraint car_log_entitat_ent_fk 
       foreign key (entitatid) 
       references car_entitat;

    alter table car_log 
       add constraint car_log_plugin_plu_fk 
       foreign key (pluginid) 
       references car_plugin;

    alter table car_plugin 
       add constraint car_plugin_traduccio_desc_fk 
       foreign key (descripcioid) 
       references car_traduccio;

    alter table car_plugin 
       add constraint car_plugin_traduccio_nom_fk 
       foreign key (nomid) 
       references car_traduccio;

    alter table car_pluginentitat 
       add constraint car_plugent_entitat_fk 
       foreign key (entitatid) 
       references car_entitat;

    alter table car_pluginentitat 
       add constraint car_plugent_plugin_fk 
       foreign key (pluginid) 
       references car_plugin;

    alter table car_propietatglobal 
       add constraint car_propglob_entitat_fk 
       foreign key (entitatid) 
       references car_entitat;

    alter table car_traducciomap 
       add constraint car_traducmap_traduccio_fk 
       foreign key (traducciomapid) 
       references car_traduccio;

    alter table car_usuari 
       add constraint car_usuari_entitat_last_fk 
       foreign key (darreraentitat) 
       references car_entitat;

    alter table car_usuari 
       add constraint car_usuari_idioma_idi_fk 
       foreign key (idiomaid) 
       references car_idioma;

    alter table car_usuarientitat 
       add constraint car_usuent_entitat_fk 
       foreign key (entitatid) 
       references car_entitat;

    alter table car_usuarientitat 
       add constraint car_usuent_usuari_fk 
       foreign key (usuariid) 
       references car_usuari;
 -- FINAL FKs


 -- INICI UNIQUEs

    alter table car_pluginentitat 
       add constraint car_plugent_plug_ent_uk unique (pluginid, entitatid);

    alter table car_usuarientitat 
       add constraint car_usuent_usu_ent_uk unique (usuariid, entitatid);
 -- FINAL UNIQUEs

