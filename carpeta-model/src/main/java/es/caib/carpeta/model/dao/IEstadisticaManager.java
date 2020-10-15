package es.caib.carpeta.model.dao;

import es.caib.carpeta.model.entity.Estadistica;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IEstadisticaManager extends org.fundaciobit.genapp.common.query.ITableManager<Estadistica, Long> {


	public Estadistica create( long _entitatID_, java.lang.Long _accesID_, int _accio_, java.lang.String _element_, java.sql.Timestamp _dataEstadistica_) throws I18NException;

	public Estadistica findByPrimaryKey(long _estadisticaID_);

	public void delete(long _estadisticaID_);

}
