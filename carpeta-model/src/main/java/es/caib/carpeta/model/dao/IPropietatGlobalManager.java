package es.caib.carpeta.model.dao;

import es.caib.carpeta.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IPropietatGlobalManager extends org.fundaciobit.genapp.common.query.ITableManager<PropietatGlobal, Long> {


	public PropietatGlobal create( java.lang.String _codi_, java.lang.String _value_, java.lang.String _descripcio_, java.lang.Long _entitatID_) throws I18NException;

	public PropietatGlobal findByPrimaryKey(long _propietaGlobalID_);

	public void delete(long _propietaGlobalID_);

}
