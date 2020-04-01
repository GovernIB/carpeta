
    create sequence CAR_PROCEDIMENT_SEQ start with 1 increment by  1;
    create sequence CAR_UNITATORGANICA_SEQ start with 1 increment by  1;

    create table CAR_PROCEDIMENT (
        ID number(19,0) not null,
        CODISIA varchar2(8 char) not null,
        NOM varchar2(50 char) not null,
        UNITATORGANICAID number(19,0) not null
    );

    create table CAR_UNITATORGANICA (
        ID number(19,0) not null,
        CODIDIR3 varchar2(9 char) not null,
        DATACREACIO date not null,
        ESTAT number(10,0) not null,
        NOM varchar2(50 char) not null
    );

    create index CAR_PROCEDIMENT_PK_I on CAR_PROCEDIMENT (ID);
    create index CAR_PROCEDIMENT_CODISIA_UK_I on CAR_PROCEDIMENT (CODISIA);
    create index CAR_PROCEDIMENT_UNITAT_FK_I on CAR_PROCEDIMENT (UNITATORGANICAID);

    alter table CAR_PROCEDIMENT
        add constraint CAR_PROCEDIMENT_PK primary key (ID);

    alter table CAR_PROCEDIMENT
        add constraint CAR_PROCEDIMENT_CODISIA_UK unique (CODISIA);

    create index CAR_UNITAT_PK_I on CAR_UNITATORGANICA (ID);
    create index CAR_UNITAT_CODIDIR3_UK_I on CAR_UNITATORGANICA (CODIDIR3);

    alter table CAR_UNITATORGANICA
        add constraint CAR_UNITAT_PK primary key (ID);

    alter table CAR_UNITATORGANICA
        add constraint CAR_UNITAT_CODIDIR3_UK unique (CODIDIR3);

    alter table CAR_PROCEDIMENT
        add constraint CAR_PROCEDIMENT_UNITAT_FK
        foreign key (UNITATORGANICAID)
        references CAR_UNITATORGANICA;

    -- Grants per l'usuari www_carpeta
    -- seqüències
    GRANT SELECT, ALTER ON CAR_PROCEDIMENT_SEQ TO WWW_CARPETA;
    GRANT SELECT, ALTER ON CAR_UNITATORGANICA_SEQ TO WWW_CARPETA;
    -- taules
    GRANT SELECT, INSERT, UPDATE, DELETE ON CAR_PROCEDIMENT TO WWW_CARPETA;
    GRANT SELECT, INSERT, UPDATE, DELETE ON CAR_UNITATORGANICA TO WWW_CARPETA;


