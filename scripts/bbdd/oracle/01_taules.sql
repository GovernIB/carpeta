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

        CONSTRAINT CAR_ACCESO_PK primary key (ID)
    );

    create table CAR_ARCHIVO (
       ID number(19,0) not null,
        MIME varchar2(255 char) not null,
        NOMBRE varchar2(255 char) not null,
        TAMANO number(19,0) not null,

        CONSTRAINT CAR_ARCHIVO_PK primary key (ID)
    );

    create table CAR_AUDITORIA (
       ID number(19,0) not null,
        ACCION varchar2(255 char) not null,
        ELEMENTO varchar2(255 char) not null,
        FECHA timestamp not null,
        ENTIDAD number(19,0) not null,
        USUARIO number(19,0) not null,

        CONSTRAINT CAR_AUDITORIA_PK primary key (ID)
    );

    create table CAR_AVISO (
       ID number(19,0) not null,
        FECHA_FIN timestamp not null,
        FECHA_INICIO timestamp not null,
        TIPO varchar2(255 char) not null,
        ENTIDAD number(19,0) not null,

       CONSTRAINT CAR_AVISO_PK primary key (ID)
    );

    create table CAR_ENLACE (
       ID number(19,0) not null,
        TIPO varchar2(255 char) not null,
        ENTIDAD number(19,0) not null,

       CONSTRAINT CAR_ENLACE_PK primary key (ID)
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

       CONSTRAINT CAR_ENTIDAD_PK primary key (ID)
    );

    create table CAR_ESTADISTICA (
       ID number(19,0) not null,
        ACCION varchar2(255 char) not null,
        ELEMENTO varchar2(255 char) not null,
        FECHA timestamp not null,
        ACCESO number(19,0) not null,
        ENTIDAD number(19,0) not null,

       CONSTRAINT CAR_ESTADISTICA_PK primary key (ID)
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

       CONSTRAINT CAR_LOG_PK primary key (ID)
    );

    create table CAR_PLUGIN (
       ID number(19,0) not null,
        CLASE varchar2(255 char) not null,
        ESTADO varchar2(255 char) not null,
        PREFIJO_ENTIDAD varchar2(255 char) not null,
        PREFIJO_PROPS varchar2(255 char) not null,
        TIPO varchar2(255 char) not null,
        ENTIDAD number(19,0) not null,

       CONSTRAINT CAR_PLUGIN_PK primary key (ID)
    );

    create table CAR_PROPIEDAD_GLOBAL (
       ID number(19,0) not null,
        CODIGO varchar2(255 char) not null,
        DESCRIPCION varchar2(255 char),
        VALOR varchar2(255 char),
        ENTIDAD number(19,0) not null,

       CONSTRAINT CAR_PROPIEDAD_GLOBAL_PK primary key (ID)
    );

    create table CAR_PROPIEDAD_PLUGIN (
       ID number(19,0) not null,
        CODIGO varchar2(255 char) not null,
        DESCRIPCION varchar2(255 char),
        VALOR varchar2(255 char),
        PLUGIN number(19,0) not null,

       CONSTRAINT CAR_PROPIEDAD_PLUGIN_PK primary key (ID)
    );

    create table CAR_TRA_AVISO (
       IDAVISO number(19,0) not null,
        TEXTO_AVISO varchar2(255 char) not null,
        LANG varchar2(255 char) not null,

       CONSTRAINT CAR_TRA_AVISO_PK primary key (IDAVISO, LANG)
    );

    create table CAR_TRA_ENLACE (
       IDENLACE number(19,0) not null,
        URL varchar2(255 char) not null,
        LANG varchar2(255 char) not null,

       CONSTRAINT CAR_TRA_ENLACE_PK primary key (IDENLACE, LANG)
    );

    create table CAR_TRA_ENTIDAD (
       IDENTIDAD number(19,0) not null,
        DESCRIPCION varchar2(255 char),
        nombre varchar2(255 char),
        LANG varchar2(255 char) not null,

       CONSTRAINT CAR_TRA_ENTIDAD_PK primary key (IDENTIDAD, LANG)
    );

    create table CAR_TRA_PLUGIN (
       IDPLUGIN number(19,0) not null,
        DESCRIPCION varchar2(255 char),
        nombre varchar2(255 char),
        LANG varchar2(255 char) not null,

       CONSTRAINT CAR_TRA_PLUGIN_PK primary key (IDPLUGIN, LANG)
    );

    create table CAR_USUARIO (
       ID number(19,0) not null,
        APELLIDO1 varchar2(255 char),
        APELLIDO2 varchar2(255 char),
        EMAIL varchar2(255 char) not null,
        NOMBRE varchar2(255 char) not null,
        USERNAME varchar2(255 char) not null,
        ULTIMA_ENTIDAD number(19,0) not null,

       CONSTRAINT CAR_USUARIO_PK primary key (ID)
    );

    create table CAR_USUARIOENTIDAD (
       ID number(19,0) not null,
        ACTIVO number(1,0) not null,
        ENTIDAD number(19,0) not null,
        USUARIO number(19,0) not null,

       CONSTRAINT CAR_USUARIOENTIDAD_PK primary key (ID)
    );