
    create table car_acces (
       accesid number(19,0) not null,
        datadarreracces timestamp,
        entitatid number(19,0) not null,
        idioma varchar2(50 char),
        ip varchar2(100 char),
        llinatges varchar2(255 char),
        nif varchar2(50 char),
        nivellseguretat varchar2(255 char),
        nom varchar2(255 char),
        proveidoridentitat varchar2(255 char),
        resultatautenticacio number(10,0)
    );

    create table car_auditoria (
       auditoriaid number(19,0) not null,
        accio number(10,0) not null,
        dataaudit timestamp not null,
        element varchar2(255 char),
        entitatid number(19,0),
        usuariid number(19,0)
    );

    create table car_avis (
       avisid number(19,0) not null,
        datafi timestamp,
        datainici timestamp,
        descripcioid number(19,0) not null,
        entitatid number(19,0) not null,
        tipus number(10,0) not null
    );

    create table car_enllaz (
       enllazid number(19,0) not null,
        entitatid number(19,0) not null,
        nomid number(19,0) not null,
        tipus number(10,0) not null,
        urlid number(19,0) not null
    );

    create table car_entitat (
       entitatid number(19,0) not null,
        activa number(1,0) not null,
        codi varchar2(30 char) not null,
        codidir3 varchar2(255 char) not null,
        colormenu varchar2(100 char) not null,
        commit varchar2(255 char),
        context varchar2(255 char),
        fitxercss number(19,0),
        logomenuid number(19,0),
        logopeuid number(19,0) not null,
        nomid number(19,0) not null,
        textepeu varchar2(4000 char),
        versio varchar2(50 char) not null
    );

    create table car_estadistica (
       estadisticaid number(19,0) not null,
        accesid number(19,0),
        accio number(10,0) not null,
        dataestadistica timestamp not null,
        element varchar2(255 char),
        entitatid number(19,0) not null
    );

    create table car_fitxer (
       fitxerid number(19,0) not null,
        descripcio varchar2(1000 char),
        mime varchar2(255 char) not null,
        nom varchar2(255 char) not null,
        tamany number(19,0) not null
    );

    create table car_idioma (
       idiomaid varchar2(5 char) not null,
        nom varchar2(50 char) not null,
        ordre number(10,0) not null,
        suportat number(1,0) not null
    );

    create table car_log (
       logid number(19,0) not null,
        datainici timestamp not null,
        descripcio varchar2(2000 char) not null,
        entitatid number(19,0),
        error varchar2(2000 char),
        estat number(10,0) not null,
        excepcio clob,
        peticio varchar2(255 char),
        pluginid number(19,0),
        temps number(19,0),
        tipus number(10,0) not null
    );

    create table car_plugin (
       pluginid number(19,0) not null,
        actiu number(1,0) not null,
        classe varchar2(255 char) not null,
        descripcioid number(19,0) not null,
        nomid number(19,0) not null,
        propietats clob,
        tipus number(10,0) not null
    );

    create table car_pluginentitat (
       pluginentitatid number(19,0) not null,
        actiu number(1,0) not null,
        entitatid number(19,0) not null,
        pluginid number(19,0) not null
    );

    create table car_propietatglobal (
       propietatglobalid number(19,0) not null,
        codi varchar2(250 char) not null,
        descripcio varchar2(1000 char),
        entitatid number(19,0),
        value varchar2(4000 char)
    );

    create table car_traduccio (
       traduccioid number(19,0) not null
    );

    create table car_traducciomap (
       traducciomapid number(19,0) not null,
        valor varchar2(4000 char),
        idiomaid varchar2(255 char) not null
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
        username varchar2(255 char) not null
    );

    create table car_usuarientitat (
       usuarientitatid number(19,0) not null,
        actiu number(1,0) not null,
        entitatid number(19,0) not null,
        usuariid number(19,0) not null
    );



