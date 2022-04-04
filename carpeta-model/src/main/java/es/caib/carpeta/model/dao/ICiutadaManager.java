package es.caib.carpeta.model.dao;

import es.caib.carpeta.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface ICiutadaManager extends org.fundaciobit.genapp.common.query.ITableManager<Ciutada, Long> {


	public Ciutada create( java.lang.String _nif_, java.lang.String _llinatge1_, java.lang.String _llinatge2_, java.lang.String _nom_, boolean _empresa_, java.lang.String _representantNif_, java.lang.String _representantLlinatge1_, java.lang.String _representantLlinatge2_, java.lang.String _representantNom_, java.sql.Timestamp _dataCreacio_, java.lang.String _mobileId_) throws I18NException;

	public Ciutada findByPrimaryKey(long _ciutadaID_);

	public void delete(long _ciutadaID_);

}
