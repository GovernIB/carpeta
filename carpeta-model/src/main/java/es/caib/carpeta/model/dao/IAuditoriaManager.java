package es.caib.carpeta.model.dao;

import es.caib.carpeta.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IAuditoriaManager extends org.fundaciobit.genapp.common.query.ITableManager<Auditoria, Long> {


	public Auditoria create( java.sql.Timestamp _dataAudit_, int _tipus_, java.lang.Long _entitatID_, java.lang.Long _usuariID_, java.lang.String _ticketLoginIB_, java.lang.Integer _pluginID_) throws I18NException;

	public Auditoria findByPrimaryKey(long _auditoriaID_);

	public void delete(long _auditoriaID_);

}
