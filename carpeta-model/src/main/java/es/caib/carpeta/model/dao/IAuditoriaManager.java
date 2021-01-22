package es.caib.carpeta.model.dao;

import es.caib.carpeta.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IAuditoriaManager extends org.fundaciobit.genapp.common.query.ITableManager<Auditoria, Long> {


	public Auditoria create( int _tipus_, java.lang.String _objecte_, java.sql.Timestamp _dataAudit_, java.lang.String _username_, java.lang.Long _entitatID_) throws I18NException;

	public Auditoria findByPrimaryKey(long _auditoriaID_);

	public void delete(long _auditoriaID_);

}
