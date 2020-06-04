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

    alter table CAR_USUARIO 
       add constraint CAR_USUARIO_ENTIDAD_FK 
       foreign key (ULTIMA_ENTIDAD) 
       references CAR_ENTIDAD;

    alter table CAR_USUARIOENTIDAD 
       add constraint CAR_USUENT_ENTIDAD_FK 
       foreign key (ENTIDAD) 
       references CAR_ENTIDAD;

    alter table CAR_USUARIOENTIDAD 
       add constraint CAR_USUENT_USUARIO_FK 
       foreign key (USUARIO) 
       references CAR_USUARIO;
	   
	   
	   -- INICI UNIQUES
alter table CAR_ENTIDAD
    add constraint CAR_ENTIDAD_CODIDIR3_UK unique (CODIGODIR3);
alter table CAR_PROPIEDAD_GLOBAL
    add constraint CAR_PROPIEDAD_GLOBAL_CODIGO_UK unique (CODIGO);
alter table CAR_PROPIEDAD_PLUGIN
    add constraint CAR_PROPIEDAD_PLUGIN_CODIGO_UK unique (CODIGO);
alter table CAR_USUARIO
    add constraint CAR_USUARIO_USERNAME_UK unique (USERNAME);
-- FI UNIQUES


