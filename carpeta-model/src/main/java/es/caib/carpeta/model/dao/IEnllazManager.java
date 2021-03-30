package es.caib.carpeta.model.dao;

import es.caib.carpeta.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IEnllazManager extends org.fundaciobit.genapp.common.query.ITableManager<Enllaz, Long> {


	public Enllaz create( int _tipus_, long _nomID_, java.lang.Long _descripcioID_, long _urlID_, long _entitatID_, long _logoID_, java.lang.Long _seccioID_, boolean _actiu_, int _ordre_) throws I18NException;

	public Enllaz findByPrimaryKey(long _enllazID_);

	public void delete(long _enllazID_);

}
