package es.caib.carpeta.model.entity;

public interface Auditoria extends org.fundaciobit.genapp.common.IGenAppEntity {

	public long getAuditoriaID();
	public void setAuditoriaID(long _auditoriaID_);

	public int getAccio();
	public void setAccio(int _accio_);

	public java.lang.String getElement();
	public void setElement(java.lang.String _element_);

	public java.sql.Timestamp getDataAudit();
	public void setDataAudit(java.sql.Timestamp _dataAudit_);

	public java.lang.Long getEntitatID();
	public void setEntitatID(java.lang.Long _entitatID_);

	public java.lang.Long getUsuariID();
	public void setUsuariID(java.lang.Long _usuariID_);



  // ======================================

}
