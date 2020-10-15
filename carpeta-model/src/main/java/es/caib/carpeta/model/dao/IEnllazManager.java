package es.caib.carpeta.model.dao;

import es.caib.carpeta.model.entity.Enllaz;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IEnllazManager extends org.fundaciobit.genapp.common.query.ITableManager<Enllaz, Long> {


	public Enllaz create( int _tipus_, long _nomID_, long _urlID_, long _entitatID_, long _logoID_) throws I18NException;

	public Enllaz findByPrimaryKey(long _enllazID_);

	public void delete(long _enllazID_);

}
