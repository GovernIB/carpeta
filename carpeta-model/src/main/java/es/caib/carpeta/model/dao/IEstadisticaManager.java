package es.caib.carpeta.model.dao;

import es.caib.carpeta.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IEstadisticaManager extends org.fundaciobit.genapp.common.query.ITableManager<Estadistica, Long> {


	public Estadistica create( java.lang.Long _entitatID_, java.sql.Timestamp _dataEstadistica_, int _tipus_, int _comptador_, java.lang.Long _pluginID_) throws I18NException;

	public Estadistica findByPrimaryKey(long _estadisticaID_);

	public void delete(long _estadisticaID_);

}
