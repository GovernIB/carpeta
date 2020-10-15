package es.caib.carpeta.model.dao;

import es.caib.carpeta.model.entity.UsuariEntitat;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IUsuariEntitatManager extends org.fundaciobit.genapp.common.query.ITableManager<UsuariEntitat, Long> {


	public UsuariEntitat create( long _usuariID_, long _entitatID_, boolean _actiu_) throws I18NException;

	public UsuariEntitat findByPrimaryKey(long _usuariEntitatID_);

	public void delete(long _usuariEntitatID_);

}
