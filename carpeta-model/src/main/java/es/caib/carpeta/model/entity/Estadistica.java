package es.caib.carpeta.model.entity;

public interface Estadistica extends org.fundaciobit.genapp.common.IGenAppEntity {

	public long getEstadisticaID();
	public void setEstadisticaID(long _estadisticaID_);

	public int getTipus();
	public void setTipus(int _tipus_);

	public java.sql.Timestamp getDataEstadistica();
	public void setDataEstadistica(java.sql.Timestamp _dataEstadistica_);

	public java.lang.Long getPluginID();
	public void setPluginID(java.lang.Long _pluginID_);

	public int getComptador();
	public void setComptador(int _comptador_);

	public java.lang.Long getEntitatID();
	public void setEntitatID(java.lang.Long _entitatID_);



  // ======================================

}
