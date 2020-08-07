package es.caib.carpeta.model.entity;

public interface Estadistica extends org.fundaciobit.genapp.common.IGenAppEntity {

	public long getEstadisticaID();
	public void setEstadisticaID(long _estadisticaID_);

	public long getEntitatID();
	public void setEntitatID(long _entitatID_);

	public java.lang.Long getAccesID();
	public void setAccesID(java.lang.Long _accesID_);

	public int getAccio();
	public void setAccio(int _accio_);

	public java.lang.String getElement();
	public void setElement(java.lang.String _element_);

	public java.sql.Timestamp getDataEstadistica();
	public void setDataEstadistica(java.sql.Timestamp _dataEstadistica_);



  // ======================================

}
