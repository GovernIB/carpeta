package es.caib.carpeta.model.dao;

import es.caib.carpeta.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface ILogCarpetaManager extends org.fundaciobit.genapp.common.query.ITableManager<LogCarpeta, Long> {


	public LogCarpeta create( java.lang.String _descripcio_, int _tipus_, int _estat_, java.lang.Long _pluginID_, java.lang.String _entitatCodi_, java.lang.Long _temps_, java.sql.Timestamp _dataInici_, java.lang.String _peticio_, java.lang.String _error_, java.lang.String _excepcio_) throws I18NException;

	public LogCarpeta findByPrimaryKey(long _logID_);

	public void delete(long _logID_);

}
