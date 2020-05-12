
    create sequence CAR_PROCEDIMENT_SEQ start 1 increment 1;
    create sequence CAR_UNITATORGANICA_SEQ start 1 increment 1;

    create table CAR_PROCEDIMENT (
       ID int8 not null,
        CODISIA varchar(8) not null,
        NOM varchar(50) not null,
        UNITATORGANICAID int8 not null,
        constraint CAR_PROCEDIMENT_PK primary key (ID)
    );

    create table CAR_UNITATORGANICA (
       ID int8 not null,
        CODIDIR3 varchar(9) not null,
        DATACREACIO date not null,
        ESTAT int4 not null,
        NOM varchar(50) not null,
        constraint CAR_UNITAT_PK primary key (ID)
    );

    create index CAR_PROCEDIMENT_PK_I on CAR_PROCEDIMENT (ID);
    create index CAR_PROCEDIMENT_CODISIA_UK_I on CAR_PROCEDIMENT (CODISIA);
    create index CAR_PROCEDIMENT_UNITAT_FK_I on CAR_PROCEDIMENT (UNITATORGANICAID);

    alter table CAR_PROCEDIMENT 
       add constraint CAR_PROCEDIMENT_CODISIA_UK unique (CODISIA);

    create index CAR_UNITAT_PK_I on CAR_UNITATORGANICA (ID);
    create index CAR_UNITAT_CODIDIR3_UK_I on CAR_UNITATORGANICA (CODIDIR3);

    alter table CAR_UNITATORGANICA 
       add constraint CAR_UNITAT_CODIDIR3_UK unique (CODIDIR3);

    alter table CAR_PROCEDIMENT 
       add constraint CAR_PROCEDIMENT_UNITAT_FK 
       foreign key (UNITATORGANICAID) 
       references CAR_UNITATORGANICA;
