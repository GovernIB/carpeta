create sequence car_acces_seq start with 1000 increment by  1;
create sequence car_auditoria_seq start with 1000 increment by  1;
create sequence car_avis_seq start with 1000 increment by  1;
create sequence car_enllaz_seq start with 1000 increment by  1;
create sequence car_entitat_seq start with 1000 increment by  1;
create sequence car_estadistica_seq start with 1000 increment by  1;
create sequence car_fitxer_seq start with 1000 increment by  1;
create sequence car_log_seq start with 1000 increment by  1;
create sequence car_plugin_seq start with 1000 increment by  1;
create sequence car_pluginentitat_seq start with 1000 increment by  1;
create sequence car_propietatglobal_seq start with 1000 increment by  1;
create sequence car_seccio_seq start with 1000 increment by  1;
create sequence car_traduccio_seq start with 1000 increment by  1;
create sequence car_usuari_seq start with 1000 increment by  1;
create sequence car_usuarientitat_seq start with 1000 increment by  1;

    create table car_acces (
       accesid number(19,0) not null,
        dataacces timestamp,
        entitatid number(19,0) not null,
        idioma varchar2(50 char),
        ip varchar2(100 char),
        llinatges varchar2(255 char),
        metodeautenticacio varchar2(255 char),
        nif varchar2(50 char),
        nom varchar2(255 char),
        pluginid number(19,0),
        proveidoridentitat varchar2(255 char),
        qaa number(10,0),
        resultat number(1,0) not null,
        tipus number(10,0) not null,
        primary key (accesid)
    );

    create table car_auditoria (
       auditoriaid number(19,0) not null,
        dataaudit timestamp not null,
        entitatid number(19,0),
        objecte varchar2(255 char),
        tipus number(10,0) not null,
        username varchar2(255 char),
        primary key (auditoriaid)
    );

    create table car_avis (
       avisid number(19,0) not null,
        datafi timestamp,
        datainici timestamp,
        descripcioid number(19,0) not null,
        entitatid number(19,0),
        gravetat number(10,0) not null,
        pluginfrontid number(19,0),
        tipus number(10,0) not null,
        primary key (avisid)
    );

    create table car_enllaz (
       enllazid number(19,0) not null,
        descripcioid number(19,0),
        entitatid number(19,0) not null,
        logoid number(19,0) not null,
        nomid number(19,0) not null,
        seccioid number(19,0),
        tipus number(10,0) not null,
        urlid number(19,0) not null,
        primary key (enllazid)
    );

    create table car_entitat (
       entitatid number(19,0) not null,
        activa number(1,0) not null,
        codi varchar2(30 char) not null,
        codidir3 varchar2(255 char) not null,
        colormenu varchar2(100 char) not null,
        commit varchar2(255 char),
        context varchar2(255 char),
        entitatdescfront varchar2(4000 char) not null,
        fitxercss number(19,0),
        iconid number(19,0) not null,
        logintextid number(19,0),
        logocapbackid number(19,0) not null,
        logolateralfrontid number(19,0) not null,
        logopeubackid number(19,0) not null,
        nomid number(19,0) not null,
        pluginloginid number(19,0),
        suportemail varchar2(255 char),
        suportfaq varchar2(255 char),
        suporttelefon varchar2(255 char),
        suportweb varchar2(255 char),
        suportautenticacio varchar2(255 char),
        suportqssi varchar2(255 char),
        versio varchar2(50 char) not null,
        webentitat varchar2(255 char) not null,
        primary key (entitatid)
    );

    create table car_estadistica (
       estadisticaid number(19,0) not null,
        comptador number(10,0) not null,
        dataestadistica timestamp not null,
        entitatid number(19,0),
        pluginid number(19,0),
        tipus number(10,0) not null,
        primary key (estadisticaid)
    );

    create table car_fitxer (
       fitxerid number(19,0) not null,
        descripcio varchar2(1000 char),
        mime varchar2(255 char) not null,
        nom varchar2(255 char) not null,
        tamany number(19,0) not null,
        primary key (fitxerid)
    );

    create table car_idioma (
       idiomaid varchar2(5 char) not null,
        nom varchar2(50 char) not null,
        ordre number(10,0) not null,
        suportat number(1,0) not null,
        primary key (idiomaid)
    );

    create table car_log (
       logid number(19,0) not null,
        datainici timestamp not null,
        descripcio varchar2(2000 char) not null,
        entitatcodi varchar2(9 char),
        error long,
        estat number(10,0) not null,
        excepcio long,
        peticio varchar2(255 char),
        pluginid number(19,0),
        temps number(19,0),
        tipus number(10,0) not null,
        primary key (logid)
    );

    create table car_plugin (
       pluginid number(19,0) not null,
        actiu number(1,0) not null,
        classe varchar2(255 char) not null,
        context varchar2(50 char),
        descripcioid number(19,0) not null,
        logoid number(19,0),
        nomid number(19,0) not null,
        propietats long,
        tipus number(10,0) not null,
        primary key (pluginid)
    );

    create table car_pluginentitat (
       pluginentitatid number(19,0) not null,
        actiu number(1,0) not null,
        entitatid number(19,0) not null,
        pluginid number(19,0) not null,
        seccioid number(19,0),
        primary key (pluginentitatid)
    );

    create table car_propietatglobal (
       propietatglobalid number(19,0) not null,
        codi varchar2(250 char) not null,
        descripcio varchar2(1000 char),
        entitatid number(19,0),
        value varchar2(4000 char),
        primary key (propietatglobalid)
    );

    create table car_seccio (
       seccioid number(19,0) not null,
        activa number(1,0) not null,
        context varchar2(50 char) not null,
        descripcioid number(19,0) not null,
        entitatid number(19,0) not null,
        iconaid number(19,0) not null,
        nomid number(19,0) not null,
        secciopareid number(19,0),
        primary key (seccioid)
    );

    create table car_traduccio (
       traduccioid number(19,0) not null,
        primary key (traduccioid)
    );

    create table car_traducciomap (
       traducciomapid number(19,0) not null,
        valor varchar2(4000 char),
        idiomaid varchar2(255 char) not null,
        primary key (traducciomapid, idiomaid)
    );

    create table car_usuari (
       usuariid number(19,0) not null,
        darreraentitat number(19,0),
        email varchar2(255 char),
        idiomaid varchar2(5 char) not null,
        llinatge1 varchar2(255 char) not null,
        llinatge2 varchar2(255 char),
        nif varchar2(255 char),
        nom varchar2(255 char) not null,
        username varchar2(255 char) not null,
        primary key (usuariid)
    );

    create table car_usuarientitat (
       usuarientitatid number(19,0) not null,
        actiu number(1,0) not null,
        entitatid number(19,0) not null,
        usuariid number(19,0) not null,
        primary key (usuarientitatid)
    );
create index car_acces_pk_i on car_acces (accesid);
create index car_acces_entitatid_fk_i on car_acces (entitatid);
create index car_auditoria_pk_i on car_auditoria (auditoriaid);
create index car_auditoria_entitatid_fk_i on car_auditoria (entitatid);
create index car_avis_pk_i on car_avis (avisid);
create index car_avis_descripcioid_fk_i on car_avis (descripcioid);
create index car_avis_entitatid_fk_i on car_avis (entitatid);
create index car_avis_pluginfrontid_fk_i on car_avis (pluginfrontid);
create index car_enllaz_pk_i on car_enllaz (enllazid);
create index car_enllaz_descripcioid_fk_i on car_enllaz (descripcioid);
create index car_enllaz_entitatid_fk_i on car_enllaz (entitatid);
create index car_enllaz_logoid_fk_i on car_enllaz (logoid);
create index car_enllaz_nomid_fk_i on car_enllaz (nomid);
create index car_enllaz_seccioid_fk_i on car_enllaz (seccioid);
create index car_enllaz_urlid_fk_i on car_enllaz (urlid);
create index car_entitat_pk_i on car_entitat (entitatid);
create index car_entitat_fitxercss_fk_i on car_entitat (fitxercss);
create index car_entitat_iconid_fk_i on car_entitat (iconid);
create index car_entitat_logintextid_fk_i on car_entitat (logintextid);
create index car_entitat_logocapback_fk_i on car_entitat (logocapbackid);
create index car_entitat_logolatfront_fk_i on car_entitat (logolateralfrontid);
create index car_entitat_logopeuback_fk_i on car_entitat (logopeubackid);
create index car_entitat_nom_fk_i on car_entitat (nomid);
create index car_entitat_pluginloginid_fk_i on car_entitat (pluginloginid);
create index car_estadistica_pk_i on car_estadistica (estadisticaid);
create index car_estadistica_entitatid_fk_i on car_estadistica (entitatid);
create index car_fitxer_pk_i on car_fitxer (fitxerid);
create index car_idioma_pk_i on car_idioma (idiomaid);
create index car_log_pk_i on car_log (logid);
create index car_log_pluginid_fk_i on car_log (pluginid);
create index car_plugin_pk_i on car_plugin (pluginid);
create index car_plugin_descripcioid_fk_i on car_plugin (descripcioid);
create index car_plugin_logoid_fk_i on car_plugin (logoid);
create index car_plugin_nomid_fk_i on car_plugin (nomid);
create index car_pluginentitat_pk_i on car_pluginentitat (pluginentitatid);
create index car_plugent_entitatid_fk_i on car_pluginentitat (entitatid);
create index car_plugent_pluginid_fk_i on car_pluginentitat (pluginid);
create index car_plugent_seccioid_fk_i on car_pluginentitat (seccioid);

    alter table car_pluginentitat 
       add constraint car_plugent_plug_ent_uk unique (pluginid, entitatid);
create index car_propietatglobal_pk_i on car_propietatglobal (propietatglobalid);
create index car_propglob_entitatid_fk_i on car_propietatglobal (entitatid);
create index car_seccio_pk_i on car_seccio (seccioid);
create index car_seccio_descripcioid_fk_i on car_seccio (descripcioid);
create index car_seccio_entitatid_fk_i on car_seccio (entitatid);
create index car_seccio_iconaid_fk_i on car_seccio (iconaid);
create index car_seccio_nomid_fk_i on car_seccio (nomid);

    alter table car_seccio 
       add constraint UK_kow6m5nai4e8hi4kh6hg4vej8 unique (context);
create index car_traduccio_pk_i on car_traduccio (traduccioid);
create index car_usuari_pk_i on car_usuari (usuariid);
create index car_usuari_darreraentitat_fk_i on car_usuari (darreraentitat);
create index car_usuari_idiomaid_fk_i on car_usuari (idiomaid);

    alter table car_usuari 
       add constraint UK_tdrt8u1p7bryex2oy8ogbpufq unique (nif);

    alter table car_usuari 
       add constraint UK_cirfvhc2yliu7g83kp3uo6mw0 unique (username);
create index car_usuarientitat_pk_i on car_usuarientitat (usuarientitatid);
create index car_usuent_entitatid_fk_i on car_usuarientitat (entitatid);
create index car_usuent_usuariid_fk_i on car_usuarientitat (usuariid);

    alter table car_usuarientitat 
       add constraint car_usuent_usu_ent_uk unique (usuariid, entitatid);

    alter table car_acces 
       add constraint car_acces_entitat_entitatid_fk 
       foreign key (entitatid) 
       references car_entitat;

    alter table car_avis 
       add constraint car_avis_traduccio_desc_fk 
       foreign key (descripcioid) 
       references car_traduccio;

    alter table car_avis 
       add constraint car_avis_entitat_ent_fk 
       foreign key (entitatid) 
       references car_entitat;

    alter table car_avis 
       add constraint car_avis_plugin_pfront_fk 
       foreign key (pluginfrontid) 
       references car_plugin;

    alter table car_enllaz 
       add constraint car_enllaz_traduccio_desid_fk 
       foreign key (descripcioid) 
       references car_traduccio;

    alter table car_enllaz 
       add constraint car_enllaz_entitat_ent_fk 
       foreign key (entitatid) 
       references car_entitat;

    alter table car_enllaz 
       add constraint car_enllaz_fitxer_logo_fk 
       foreign key (logoid) 
       references car_fitxer;

    alter table car_enllaz 
       add constraint car_enllaz_traduccio_nomid_fk 
       foreign key (nomid) 
       references car_traduccio;

    alter table car_enllaz 
       add constraint car_enllaz_seccio_sec_fk 
       foreign key (seccioid) 
       references car_seccio;

    alter table car_enllaz 
       add constraint car_enllaz_traduccio_urlid_fk 
       foreign key (urlid) 
       references car_traduccio;

    alter table car_entitat 
       add constraint car_entitat_fitxer_css_fk 
       foreign key (fitxercss) 
       references car_fitxer;

    alter table car_entitat 
       add constraint car_entitat_fitxer_icon_fk 
       foreign key (iconid) 
       references car_fitxer;

    alter table car_entitat 
       add constraint car_entitat_traduccio_log_fk 
       foreign key (logintextid) 
       references car_traduccio;

    alter table car_entitat 
       add constraint car_entitat_fitxer_lcb_fk 
       foreign key (logocapbackid) 
       references car_fitxer;

    alter table car_entitat 
       add constraint car_entitat_fitxer_llf_fk 
       foreign key (logolateralfrontid) 
       references car_fitxer;

    alter table car_entitat 
       add constraint car_entitat_fitxer_lpb_fk 
       foreign key (logopeubackid) 
       references car_fitxer;

    alter table car_entitat 
       add constraint car_entitat_traduccio_nom_fk 
       foreign key (nomid) 
       references car_traduccio;

    alter table car_entitat 
       add constraint car_entitat_plugin_login_fk 
       foreign key (pluginloginid) 
       references car_plugin;

    alter table car_plugin 
       add constraint car_plugin_traduccio_desc_fk 
       foreign key (descripcioid) 
       references car_traduccio;

    alter table car_plugin 
       add constraint car_plugin_fitxer_logo_fk 
       foreign key (logoid) 
       references car_fitxer;

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

    alter table car_pluginentitat 
       add constraint car_plugent_seccio_sec_fk 
       foreign key (seccioid) 
       references car_seccio;

    alter table car_propietatglobal 
       add constraint car_propglob_entitat_fk 
       foreign key (entitatid) 
       references car_entitat;

    alter table car_seccio 
       add constraint car_seccio_traduccio_des_fk 
       foreign key (descripcioid) 
       references car_traduccio;

    alter table car_seccio 
       add constraint car_seccio_entitat_ent_fk 
       foreign key (entitatid) 
       references car_entitat;

    alter table car_seccio 
       add constraint car_seccio_fitxer_icon_fk 
       foreign key (iconaid) 
       references car_fitxer;

    alter table car_seccio 
       add constraint car_seccio_traduccio_nom_fk 
       foreign key (nomid) 
       references car_traduccio;

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
