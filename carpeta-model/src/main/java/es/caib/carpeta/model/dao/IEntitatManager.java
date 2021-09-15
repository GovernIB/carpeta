package es.caib.carpeta.model.dao;

import es.caib.carpeta.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IEntitatManager extends org.fundaciobit.genapp.common.query.ITableManager<Entitat, Long> {


	public Entitat create( long _nomID_, java.lang.Long _descripcioID_, java.lang.String _codi_, java.lang.String _codiDir3_, boolean _activa_, java.lang.String _colorMenu_, long _logoCapBackID_, long _logoPeuBackID_, long _logoLateralFrontID_, java.lang.String _versio_, long _iconID_, java.lang.String _webEntitat_, java.lang.String _entitatDescFront_, java.lang.String _suportWeb_, java.lang.String _suportTelefon_, java.lang.String _suportEmail_, java.lang.String _suportFAQ_, java.lang.String _suportqssi_, java.lang.String _suportautenticacio_, java.lang.Long _pluginLoginID_, java.lang.Long _loginTextID_, java.lang.Long _fitxerCssID_, java.lang.String _context_, java.lang.String _commit_, java.lang.String _avisLegalCa_, java.lang.String _avisLegalEs_, java.lang.String _accessibilitatCa_, java.lang.String _accessibilitatEs_) throws I18NException;

	public Entitat findByPrimaryKey(long _entitatID_);

	public void delete(long _entitatID_);

}
