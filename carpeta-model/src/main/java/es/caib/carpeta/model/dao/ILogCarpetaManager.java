package es.caib.carpeta.model.dao;

import es.caib.carpeta.model.entity.LogCarpeta;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface ILogCarpetaManager extends org.fundaciobit.genapp.common.query.ITableManager<LogCarpeta, Long> {


	public LogCarpeta create( java.lang.String _descripcio_, java.lang.Long _entitatID_, java.lang.Long _pluginID_, int _tipus_, int _estat_, java.lang.Long _temps_, java.sql.Timestamp _dataInici_, java.lang.String _peticio_, java.lang.String _error_, java.lang.String _excepcio_) throws I18NException;

	public LogCarpeta findByPrimaryKey(long _logID_);

	public void delete(long _logID_);

}
