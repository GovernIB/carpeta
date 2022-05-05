package es.caib.carpeta.model.dao;

import es.caib.carpeta.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface INotificacioAppManager extends org.fundaciobit.genapp.common.query.ITableManager<NotificacioApp, Long> {


	public NotificacioApp create( java.lang.String _codi_, long _titolID_, long _missatgeID_, java.lang.Long _frontPluginID_, java.lang.String _ajuda_, boolean _activa_) throws I18NException;

	public NotificacioApp findByPrimaryKey(long _notificacioAppID_);

	public void delete(long _notificacioAppID_);

}
