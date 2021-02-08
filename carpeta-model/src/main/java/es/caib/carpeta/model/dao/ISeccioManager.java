package es.caib.carpeta.model.dao;

import es.caib.carpeta.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface ISeccioManager extends org.fundaciobit.genapp.common.query.ITableManager<Seccio, Long> {


	public Seccio create( long _nomID_, long _descripcioID_, boolean _activa_, long _iconaID_, java.lang.Long _seccioPareID_, long _entitatID_) throws I18NException;

	public Seccio findByPrimaryKey(long _seccioID_);

	public void delete(long _seccioID_);

}
