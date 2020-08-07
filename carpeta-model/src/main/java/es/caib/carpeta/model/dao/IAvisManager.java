package es.caib.carpeta.model.dao;

import es.caib.carpeta.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IAvisManager extends org.fundaciobit.genapp.common.query.ITableManager<Avis, Long> {


	public Avis create( long _descripcioID_, long _entitatID_, java.sql.Timestamp _dataInici_, java.sql.Timestamp _dataFi_, int _tipus_) throws I18NException;

	public Avis findByPrimaryKey(long _avisID_);

	public void delete(long _avisID_);

}
