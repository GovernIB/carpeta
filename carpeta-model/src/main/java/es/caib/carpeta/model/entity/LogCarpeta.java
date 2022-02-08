package es.caib.carpeta.model.entity;

public interface LogCarpeta extends org.fundaciobit.genapp.common.IGenAppEntity {

	public long getLogID();
	public void setLogID(long _logID_);

	public java.lang.String getDescripcio();
	public void setDescripcio(java.lang.String _descripcio_);

	public int getTipus();
	public void setTipus(int _tipus_);

	public int getEstat();
	public void setEstat(int _estat_);

	public java.lang.Long getPluginID();
	public void setPluginID(java.lang.Long _pluginID_);

	public java.lang.String getEntitatCodi();
	public void setEntitatCodi(java.lang.String _entitatCodi_);

	public java.lang.Long getTemps();
	public void setTemps(java.lang.Long _temps_);

	public java.sql.Timestamp getDataInici();
	public void setDataInici(java.sql.Timestamp _dataInici_);

	public java.lang.String getPeticio();
	public void setPeticio(java.lang.String _peticio_);

	public java.lang.String getError();
	public void setError(java.lang.String _error_);

	public java.lang.String getExcepcio();
	public void setExcepcio(java.lang.String _excepcio_);

	public java.lang.String getIdSessio();
	public void setIdSessio(java.lang.String _idSessio_);



  // ======================================

}
