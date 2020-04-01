
    alter table CAR_PROCEDIMENT 
       drop constraint CAR_PROCEDIMENT_UNITAT_FK;

    drop table if exists CAR_PROCEDIMENT cascade;

    drop table if exists CAR_UNITATORGANICA cascade;

    drop sequence if exists CAR_PROCEDIMENT_SEQ;

    drop sequence if exists CAR_UNITATORGANICA_SEQ;
