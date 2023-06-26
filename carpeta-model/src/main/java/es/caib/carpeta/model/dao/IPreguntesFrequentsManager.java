package es.caib.carpeta.model.dao;

import es.caib.carpeta.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IPreguntesFrequentsManager extends org.fundaciobit.genapp.common.query.ITableManager<PreguntesFrequents, Long> {


	public PreguntesFrequents create( long _enunciatID_, int _ordre_, long _entitatID_, java.lang.String _respostaCa_, java.lang.String _respostaEs_) throws I18NException;

	public PreguntesFrequents findByPrimaryKey(long _preguntesFrequentsID_);

	public void delete(long _preguntesFrequentsID_);

}
