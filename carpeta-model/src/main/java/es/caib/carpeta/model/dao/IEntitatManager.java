package es.caib.carpeta.model.dao;

import es.caib.carpeta.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IEntitatManager extends org.fundaciobit.genapp.common.query.ITableManager<Entitat, Long> {


	public Entitat create( long _nomID_, java.lang.String _codi_, java.lang.String _codiDir3_, boolean _activa_, java.lang.Long _logoMenuID_, java.lang.String _colorMenu_, java.lang.String _textePeu_, long _logoPeuID_, java.lang.String _versio_, java.lang.String _commit_, java.lang.Long _fitxerCssID_, java.lang.String _context_) throws I18NException;

	public Entitat findByPrimaryKey(long _entitatID_);

	public void delete(long _entitatID_);

}
