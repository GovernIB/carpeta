create sequence CAR_ACCESO_SEQ start with 1 increment by  1;
create sequence CAR_ARCHIVO_SEQ start with 1 increment by  1;
create sequence CAR_AUDITORIA_SEQ start with 1 increment by  1;
create sequence CAR_AVISO_SEQ start with 1 increment by  1;
create sequence CAR_ENLACE_SEQ start with 1 increment by  1;
create sequence CAR_ENTIDAD_SEQ start with 1 increment by  1;
create sequence CAR_ESTADISTICA_SEQ start with 1 increment by  1;
create sequence CAR_LOG_SEQ start with 1 increment by  1;
create sequence CAR_PLUGIN_SEQ start with 1 increment by  1;
create sequence CAR_PROPGLOBAL_SEQ start with 1 increment by  1;
create sequence CAR_PROPPLUGIN_SEQ start with 1 increment by  1;
create sequence CAR_USUARIO_SEQ start with 1 increment by  1;
create sequence CAR_USUARIOENTIDAD_SEQ start with 1 increment by  1;

    create table CAR_ACCESO (
       ID number(19,0) not null,
        APELLIDOS varchar2(255 char) not null,
        DIRECCION_IP varchar2(255 char) not null,
        FECHA_ULTIMO_ACCESO timestamp not null,
        IDIOMA varchar2(255 char) not null,
        NIF varchar2(9 char) not null,
        NIVEL_SEGURIDAD varchar2(255 char),
        NOMBRE varchar2(255 char) not null,
        PROVEEDOR_ENTIDAD varchar2(255 char),
        RESULT_AUTENTICACION varchar2(255 char),
        ENTIDAD number(19,0) not null,
        primary key (ID)
    );

    create table CAR_ARCHIVO (
       ID number(19,0) not null,
        MIME varchar2(255 char) not null,
        NOMBRE varchar2(255 char) not null,
        TAMANO number(19,0) not null,
        primary key (ID)
    );

    create table CAR_AUDITORIA (
       ID number(19,0) not null,
        ACCION varchar2(255 char) not null,
        ELEMENTO varchar2(255 char) not null,
        FECHA timestamp not null,
        ENTIDAD number(19,0) not null,
        USUARIO number(19,0) not null,
        primary key (ID)
    );

    create table CAR_AVISO (
       ID number(19,0) not null,
        FECHA_FIN timestamp not null,
        FECHA_INICIO timestamp not null,
        TIPO varchar2(255 char) not null,
        ENTIDAD number(19,0) not null,
        primary key (ID)
    );

    create table CAR_ENLACE (
       ID number(19,0) not null,
        TIPO varchar2(255 char) not null,
        ENTIDAD number(19,0) not null,
        primary key (ID)
    );

    create table CAR_ENTIDAD (
       ID number(19,0) not null,
        ACTIVO number(1,0) not null,
        CODIGODIR3 varchar2(9 char) not null,
        COLORMENU varchar2(255 char),
        COMIT varchar2(255 char),
        CONTEXTO varchar2(255 char) not null,
        TEXTOPIE varchar2(4000 char),
        VERSION varchar2(255 char),
        FICHEROCSS number(19,0),
        LOGOMENU number(19,0),
        LOGOPIE number(19,0),
        primary key (ID)
    );

    create table CAR_ESTADISTICA (
       ID number(19,0) not null,
        ACCION varchar2(255 char) not null,
        ELEMENTO varchar2(255 char) not null,
        FECHA timestamp not null,
        ACCESO number(19,0) not null,
        ENTIDAD number(19,0) not null,
        primary key (ID)
    );

    create table CAR_LOG (
       ID number(19,0) not null,
        DESCRIPCION varchar2(255 char),
        ERROR varchar2(255 char),
        ESTADO varchar2(255 char) not null,
        EXCEPCION varchar2(255 char),
        FECHA timestamp not null,
        PETICION varchar2(255 char),
        TIEMPO number(19,0) not null,
        TIPO varchar2(255 char) not null,
        ENTIDAD number(19,0) not null,
        PLUGIN number(19,0) not null,
        primary key (ID)
    );

    create table CAR_PLUGIN (
       ID number(19,0) not null,
        CLASE varchar2(255 char) not null,
        ESTADO varchar2(255 char) not null,
        PREFIJO_ENTIDAD varchar2(255 char),
        PREFIJO_PROPS varchar2(255 char),
        TIPO varchar2(255 char) not null,
        ENTIDAD number(19,0),
        primary key (ID)
    );

    create table CAR_PROPIEDAD_GLOBAL (
       ID number(19,0) not null,
        CODIGO varchar2(255 char) not null,
        DESCRIPCION varchar2(255 char),
        VALOR varchar2(255 char),
        ENTIDAD number(19,0) not null,
        primary key (ID)
    );

    create table CAR_PROPIEDAD_PLUGIN (
       ID number(19,0) not null,
        CODIGO varchar2(255 char) not null,
        DESCRIPCION varchar2(255 char),
        VALOR varchar2(255 char),
        PLUGIN number(19,0) not null,
        primary key (ID)
    );

    create table CAR_TRA_AVISO (
       IDAVISO number(19,0) not null,
        TEXTO_AVISO varchar2(255 char) not null,
        LANG varchar2(255 char) not null,
        primary key (IDAVISO, LANG)
    );

    create table CAR_TRA_ENLACE (
       IDENLACE number(19,0) not null,
        URL varchar2(255 char) not null,
        LANG varchar2(255 char) not null,
        primary key (IDENLACE, LANG)
    );

    create table CAR_TRA_ENTIDAD (
       IDENTIDAD number(19,0) not null,
        DESCRIPCION varchar2(255 char),
        nombre varchar2(255 char),
        LANG varchar2(255 char) not null,
        primary key (IDENTIDAD, LANG)
    );

    create table CAR_TRA_PLUGIN (
       IDPLUGIN number(19,0) not null,
        DESCRIPCION varchar2(255 char),
        nombre varchar2(255 char),
        LANG varchar2(255 char) not null,
        primary key (IDPLUGIN, LANG)
    );

    create table CAR_USUARIO (
       ID number(19,0) not null,
        APELLIDO1 varchar2(255 char),
        APELLIDO2 varchar2(255 char),
        DOCUMENTO varchar2(255 char),
        EMAIL varchar2(255 char) not null,
        IDIOMA varchar2(255 char) not null,
        NOMBRE varchar2(255 char) not null,
        TIPO number(10,0) not null,
        USERNAME varchar2(255 char) not null,
        primary key (ID)
    );

    create table CAR_USUARIOENTIDAD (
       ID number(19,0) not null,
        ACTIVO number(1,0) not null,
        ADMINENTIDAD number(1,0) not null,
        ENTIDAD number(19,0) not null,
        ULTIMA_ENTIDAD number(19,0),
        USUARIO number(19,0) not null,
        primary key (ID)
    );
create index CAR_ACCESO_PK_I on CAR_ACCESO (ID);
create index CAR_ACCESO_ENTIDAD_FK_I on CAR_ACCESO (ENTIDAD);
create index CAR_ARCHIVO_PK_I on CAR_ARCHIVO (ID);
create index CAR_AUDITORIA_PK_I on CAR_AUDITORIA (ID);
create index CAR_AUDIT_USUARIO_FK_I on CAR_AUDITORIA (USUARIO);
create index CAR_AUDIT_ENTIDAD_FK_I on CAR_AUDITORIA (ENTIDAD);
create index CAR_AVISO_PK_I on CAR_AVISO (ID);
create index CAR_AVISO_ENTIDAD_FK_I on CAR_AVISO (ENTIDAD);
create index CAR_ENLACE_PK_I on CAR_ENLACE (ID);
create index CAR_ENLACE_ENTIDAD_FK_I on CAR_ENLACE (ENTIDAD);
create index CAR_ENTIDAD_PK_I on CAR_ENTIDAD (ID);
create index CAR_ENTIDAD_CODIGODIR3_UK_I on CAR_ENTIDAD (CODIGODIR3);
create index CAR_ENTIDAD_LOGOMENU_FK_I on CAR_ENTIDAD (LOGOMENU);
create index CAR_ENTIDAD_LOGOPIE_FK_I on CAR_ENTIDAD (LOGOPIE);
create index CAR_ENTIDAD_FICHEROCSS_FK_I on CAR_ENTIDAD (FICHEROCSS);

    alter table CAR_ENTIDAD 
       add constraint UK_4ebo6icnww42h20lf6y38vkte unique (CODIGODIR3);
create index CAR_ESTADISTICA_PK_I on CAR_ESTADISTICA (ID);
create index CAR_ESTAD_ACCESO_FK_I on CAR_ESTADISTICA (ACCESO);
create index CAR_ESTAD_ENTIDAD_FK_I on CAR_ESTADISTICA (ENTIDAD);
create index CAR_LOG_PK_I on CAR_LOG (ID);
create index CAR_LOG_PLUGIN_FK_I on CAR_LOG (PLUGIN);
create index CAR_LOG_ENTIDAD_FK_I on CAR_LOG (ENTIDAD);
create index CAR_PLUGIN_PK_I on CAR_PLUGIN (ID);
create index CAR_PLUGIN_ENTIDAD_FK_I on CAR_PLUGIN (ENTIDAD);
create index CAR_PROPGLOBAL_PK_I on CAR_PROPIEDAD_GLOBAL (ID);
create index CAR_PROPGLOBAL_CODIGO_UK_I on CAR_PROPIEDAD_GLOBAL (CODIGO);
create index CAR_PROPGLOB_ENTIDAD_FK_I on CAR_PROPIEDAD_GLOBAL (ENTIDAD);

    alter table CAR_PROPIEDAD_GLOBAL 
       add constraint UK_d42vgayqtp4l97ltacw9f2bur unique (CODIGO);
create index CAR_PROPPLUGIN_PK_I on CAR_PROPIEDAD_PLUGIN (ID);
create index CAR_PROPPLUGIN_CODIGO_UK_I on CAR_PROPIEDAD_PLUGIN (CODIGO);
create index CAR_PROPPLUG_PLUGIN_FK_I on CAR_PROPIEDAD_PLUGIN (PLUGIN);

    alter table CAR_PROPIEDAD_PLUGIN 
       add constraint UK_7k170hvydh8vk73rnbcb7mn27 unique (CODIGO);
create index CAR_USUARIO_PK_I on CAR_USUARIO (ID);
create index CAR_USERNAME_UK_I on CAR_USUARIO (USERNAME);

    alter table CAR_USUARIO 
       add constraint UK_90rvgbpbebcc9o6r123onct5q unique (USERNAME);
create index CAR_USUARIOENTIDAD_PK_I on CAR_USUARIOENTIDAD (ID);
create index CAR_USUENT_USUARIO_FK_I on CAR_USUARIOENTIDAD (USUARIO);
create index CAR_USUENT_ENTIDAD_FK_I on CAR_USUARIOENTIDAD (ENTIDAD);
create index CAR_USUENT_ULTENT_FK_I on CAR_USUARIOENTIDAD (ULTIMA_ENTIDAD);

    alter table CAR_ACCESO 
       add constraint CAR_ACCESO_ENTIDAD_FK 
       foreign key (ENTIDAD) 
       references CAR_ENTIDAD;

    alter table CAR_AUDITORIA 
       add constraint CAR_AUDIT_ENTIDAD_FK 
       foreign key (ENTIDAD) 
       references CAR_ENTIDAD;

    alter table CAR_AUDITORIA 
       add constraint CAR_AUDIT_USUARIO_FK 
       foreign key (USUARIO) 
       references CAR_USUARIO;

    alter table CAR_AVISO 
       add constraint CAR_AVISO_ENTIDAD_FK 
       foreign key (ENTIDAD) 
       references CAR_ENTIDAD;

    alter table CAR_ENLACE 
       add constraint CAR_ENLACE_ENTIDAD_FK 
       foreign key (ENTIDAD) 
       references CAR_ENTIDAD;

    alter table CAR_ENTIDAD 
       add constraint CAR_ENTIDAD_FICHEROCSS_FK 
       foreign key (FICHEROCSS) 
       references CAR_ARCHIVO;

    alter table CAR_ENTIDAD 
       add constraint CAR_ENTIDAD_LOGOMENU_FK 
       foreign key (LOGOMENU) 
       references CAR_ARCHIVO;

    alter table CAR_ENTIDAD 
       add constraint CAR_ENTIDAD_LOGOPIE_FK 
       foreign key (LOGOPIE) 
       references CAR_ARCHIVO;

    alter table CAR_ESTADISTICA 
       add constraint CAR_ESTAD_ACCESO_FK 
       foreign key (ACCESO) 
       references CAR_ACCESO;

    alter table CAR_ESTADISTICA 
       add constraint CAR_ESTAD_ENTIDAD_FK 
       foreign key (ENTIDAD) 
       references CAR_ENTIDAD;

    alter table CAR_LOG 
       add constraint CAR_LOG_ENTIDAD_FK 
       foreign key (ENTIDAD) 
       references CAR_ENTIDAD;

    alter table CAR_LOG 
       add constraint CAR_LOG_PLUGIN_FK 
       foreign key (PLUGIN) 
       references CAR_PLUGIN;

    alter table CAR_PLUGIN 
       add constraint CAR_PLUGIN_ENTIDAD_FK 
       foreign key (ENTIDAD) 
       references CAR_ENTIDAD;

    alter table CAR_PROPIEDAD_GLOBAL 
       add constraint CAR_PROPGLOB_ENTIDAD_FK 
       foreign key (ENTIDAD) 
       references CAR_ENTIDAD;

    alter table CAR_PROPIEDAD_PLUGIN 
       add constraint CAR_PROPPLUG_PLUGIN_FK 
       foreign key (PLUGIN) 
       references CAR_PLUGIN;

    alter table CAR_TRA_AVISO 
       add constraint CAR_AVISO_TRAAVISO_FK 
       foreign key (IDAVISO) 
       references CAR_AVISO;

    alter table CAR_TRA_ENLACE 
       add constraint CAR_ENLACE_TRAENLAC_FK 
       foreign key (IDENLACE) 
       references CAR_ENLACE;

    alter table CAR_TRA_ENTIDAD 
       add constraint CAR_ENTIDAD_TRAENTID_FK 
       foreign key (IDENTIDAD) 
       references CAR_ENTIDAD;

    alter table CAR_TRA_PLUGIN 
       add constraint CAR_PLUGIN_TRAPLUG_FK 
       foreign key (IDPLUGIN) 
       references CAR_PLUGIN;

    alter table CAR_USUARIOENTIDAD 
       add constraint CAR_USUENT_ENTIDAD_FK 
       foreign key (ENTIDAD) 
       references CAR_ENTIDAD;

    alter table CAR_USUARIOENTIDAD 
       add constraint CAR_USUENT_ULTENT_FK 
       foreign key (ULTIMA_ENTIDAD) 
       references CAR_ENTIDAD;

    alter table CAR_USUARIOENTIDAD 
       add constraint CAR_USUENT_USUARIO_FK 
       foreign key (USUARIO) 
       references CAR_USUARIO;
