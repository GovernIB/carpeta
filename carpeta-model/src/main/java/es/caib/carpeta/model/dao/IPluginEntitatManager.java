package es.caib.carpeta.model.dao;

import es.caib.carpeta.model.entity.PluginEntitat;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IPluginEntitatManager extends org.fundaciobit.genapp.common.query.ITableManager<PluginEntitat, Long> {


	public PluginEntitat create( long _pluginID_, long _entitatID_, boolean _actiu_) throws I18NException;

	public PluginEntitat findByPrimaryKey(long _pluginEntitatID_);

	public void delete(long _pluginEntitatID_);

}
