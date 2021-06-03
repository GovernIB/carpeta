
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
        tipus number(10,0) not null
    );

    create table car_auditoria (
       auditoriaid number(19,0) not null,
        dataaudit timestamp not null,
        entitatid number(19,0),
        objecte varchar2(255 char),
        tipus number(10,0) not null,
        username varchar2(255 char)
    );

    create table car_avis (
       avisid number(19,0) not null,
        datafi timestamp,
        datainici timestamp,
        descripcioid number(19,0) not null,
        entitatid number(19,0),
        gravetat number(10,0) not null,
        pluginfrontid number(19,0),
        tipus number(10,0) not null
    );

    create table car_enllaz (
       enllazid number(19,0) not null,
        actiu number(1,0) not null,
        descripcioid number(19,0),
        entitatid number(19,0) not null,
        logoid number(19,0) not null,
        nomid number(19,0) not null,
        ordre number(10,0) not null,
        seccioid number(19,0),
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
        descripcioid number(19,0),
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
        webentitat varchar2(255 char) not null
    );

    create table car_estadistica (
       estadisticaid number(19,0) not null,
        comptador number(10,0) not null,
        dataestadistica timestamp not null,
        entitatid number(19,0),
        pluginid number(19,0),
        tipus number(10,0) not null
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
        entitatcodi varchar2(9 char),
        error clob,
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
        context varchar2(50 char),
        descripcioid number(19,0) not null,
        logoid number(19,0),
        nomid number(19,0) not null,
        propietats clob,
        subtitolllargid number(19,0),
        tipus number(10,0) not null,
        titolllargid number(19,0)
    );

    create table car_pluginentitat (
       pluginentitatid number(19,0) not null,
        actiu number(1,0) not null,
        entitatid number(19,0) not null,
        ordre number(10,0) not null,
        pluginid number(19,0) not null,
        seccioid number(19,0)
    );

    create table car_propietatglobal (
       propietatglobalid number(19,0) not null,
        codi varchar2(250 char) not null,
        descripcio varchar2(1000 char),
        entitatid number(19,0),
        value varchar2(4000 char)
    );

    create table car_seccio (
       seccioid number(19,0) not null,
        activa number(1,0) not null,
        context varchar2(50 char) not null,
        descripcioid number(19,0) not null,
        entitatid number(19,0) not null,
        iconaid number(19,0) not null,
        nomid number(19,0) not null,
        ordre number(10,0) not null,
        secciopareid number(19,0)
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




