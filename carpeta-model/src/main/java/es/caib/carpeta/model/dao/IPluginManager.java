package es.caib.carpeta.model.dao;

import es.caib.carpeta.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IPluginManager extends org.fundaciobit.genapp.common.query.ITableManager<Plugin, Long> {


	public Plugin create( long _nomID_, java.lang.Long _descripcioID_, java.lang.String _context_, java.lang.Long _logoID_, java.lang.String _classe_, java.lang.String _propietats_, boolean _actiu_, int _tipus_) throws I18NException;

	public Plugin findByPrimaryKey(long _pluginID_);

	public void delete(long _pluginID_);

}
