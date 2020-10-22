package es.caib.carpeta.model.entity;

public interface Avis extends org.fundaciobit.genapp.common.IGenAppEntity {

	public long getAvisID();
	public void setAvisID(long _avisID_);

	public long getDescripcioID();
	public void setDescripcioID(long _descripcioID_);

	public java.lang.Long getEntitatID();
	public void setEntitatID(java.lang.Long _entitatID_);

	public java.sql.Timestamp getDataInici();
	public void setDataInici(java.sql.Timestamp _dataInici_);

	public java.sql.Timestamp getDataFi();
	public void setDataFi(java.sql.Timestamp _dataFi_);

	public int getTipus();
	public void setTipus(int _tipus_);

	public int getGravetat();
	public void setGravetat(int _gravetat_);

	public java.lang.Long getPluginFrontID();
	public void setPluginFrontID(java.lang.Long _pluginFrontID_);



  // ======================================

}
