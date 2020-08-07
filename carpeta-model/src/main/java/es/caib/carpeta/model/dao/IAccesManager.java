package es.caib.carpeta.model.dao;

import es.caib.carpeta.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IAccesManager extends org.fundaciobit.genapp.common.query.ITableManager<Acces, Long> {


	public Acces create( java.lang.String _nom_, java.lang.String _llinatges_, java.lang.String _nif_, java.lang.String _ip_, java.lang.String _proveidorIdentitat_, java.lang.String _nivellSeguretat_, java.lang.Integer _resultatAutenticacio_, java.sql.Timestamp _dataDarrerAcces_, java.lang.String _idioma_, long _entitatID_) throws I18NException;

	public Acces findByPrimaryKey(long _accesID_);

	public void delete(long _accesID_);

}
