
    alter table car_acces 
       drop constraint car_acces_entitat_entitatid_fk;

    alter table car_avis 
       drop constraint car_avis_traduccio_desc_fk;

    alter table car_avis 
       drop constraint car_avis_entitat_ent_fk;

    alter table car_avis 
       drop constraint car_avis_plugin_pfront_fk;

    alter table car_enllaz 
       drop constraint car_enllaz_entitat_ent_fk;

    alter table car_enllaz 
       drop constraint car_enllaz_fitxer_logo_fk;

    alter table car_enllaz 
       drop constraint car_enllaz_traduccio_nomid_fk;

    alter table car_enllaz 
       drop constraint car_enllaz_traduccio_urlid_fk;

    alter table car_entitat 
       drop constraint car_entitat_fitxer_css_fk;

    alter table car_entitat 
       drop constraint car_entitat_fitxer_icon_fk;

    alter table car_entitat 
       drop constraint car_entitat_traduccio_log_fk;

    alter table car_entitat 
       drop constraint car_entitat_fitxer_lcb_fk;

    alter table car_entitat 
       drop constraint car_entitat_fitxer_llf_fk;

    alter table car_entitat 
       drop constraint car_entitat_fitxer_lpb_fk;

    alter table car_entitat 
       drop constraint car_entitat_traduccio_nom_fk;

    alter table car_entitat 
       drop constraint car_entitat_plugin_login_fk;

    alter table car_plugin 
       drop constraint car_plugin_traduccio_desc_fk;

    alter table car_plugin 
       drop constraint car_plugin_traduccio_nom_fk;

    alter table car_pluginentitat 
       drop constraint car_plugent_entitat_fk;

    alter table car_pluginentitat 
       drop constraint car_plugent_plugin_fk;

    alter table car_propietatglobal 
       drop constraint car_propglob_entitat_fk;

    alter table car_traducciomap 
       drop constraint car_traducmap_traduccio_fk;

    alter table car_usuari 
       drop constraint car_usuari_entitat_last_fk;

    alter table car_usuari 
       drop constraint car_usuari_idioma_idi_fk;

    alter table car_usuarientitat 
       drop constraint car_usuent_entitat_fk;

    alter table car_usuarientitat 
       drop constraint car_usuent_usuari_fk;

    drop table if exists car_acces cascade;

    drop table if exists car_auditoria cascade;

    drop table if exists car_avis cascade;

    drop table if exists car_enllaz cascade;

    drop table if exists car_entitat cascade;

    drop table if exists car_estadistica cascade;

    drop table if exists car_fitxer cascade;

    drop table if exists car_idioma cascade;

    drop table if exists car_log cascade;

    drop table if exists car_plugin cascade;

    drop table if exists car_pluginentitat cascade;

    drop table if exists car_propietatglobal cascade;

    drop table if exists car_traduccio cascade;

    drop table if exists car_traducciomap cascade;

    drop table if exists car_usuari cascade;

    drop table if exists car_usuarientitat cascade;

    drop sequence if exists car_acces_seq;

    drop sequence if exists car_auditoria_seq;

    drop sequence if exists car_avis_seq;

    drop sequence if exists car_enllaz_seq;

    drop sequence if exists car_entitat_seq;

    drop sequence if exists car_estadistica_seq;

    drop sequence if exists car_fitxer_seq;

    drop sequence if exists car_log_seq;

    drop sequence if exists car_plugin_seq;

    drop sequence if exists car_pluginentitat_seq;

    drop sequence if exists car_propietatglobal_seq;

    drop sequence if exists car_traduccio_seq;

    drop sequence if exists car_usuari_seq;

    drop sequence if exists car_usuarientitat_seq;
