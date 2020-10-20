package es.caib.carpeta.model.dao;

import es.caib.carpeta.model.entity.Entitat;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IEntitatManager extends org.fundaciobit.genapp.common.query.ITableManager<Entitat, Long> {


	public Entitat create( long _nomID_, java.lang.String _codi_, java.lang.String _codiDir3_, boolean _activa_, java.lang.String _colorMenu_, long _logoCapBackID_, long _logoPeuBackID_, long _logoLateralFrontID_, java.lang.String _versio_, long _iconID_, java.lang.String _webEntitat_, java.lang.String _entitatDescFront_, java.lang.String _suportWeb_, java.lang.String _suportTelefon_, java.lang.String _suportEmail_, java.lang.Long _pluginLoginID_, java.lang.Long _fitxerCssID_, java.lang.String _context_, java.lang.String _commit_) throws I18NException;

	public Entitat findByPrimaryKey(long _entitatID_);

	public void delete(long _entitatID_);

}
