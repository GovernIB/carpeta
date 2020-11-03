package es.caib.carpeta.model.entity;

public interface Estadistica extends org.fundaciobit.genapp.common.IGenAppEntity {

	public long getEstadisticaID();
	public void setEstadisticaID(long _estadisticaID_);

	public long getEntitatID();
	public void setEntitatID(long _entitatID_);

	public java.sql.Timestamp getDataEstadistica();
	public void setDataEstadistica(java.sql.Timestamp _dataEstadistica_);

	public int getTipus();
	public void setTipus(int _tipus_);

	public int getComptador();
	public void setComptador(int _comptador_);

	public java.lang.Long getPluginID();
	public void setPluginID(java.lang.Long _pluginID_);



  // ======================================

}
