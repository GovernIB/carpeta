package es.caib.carpeta.model.entity;

public interface Auditoria extends org.fundaciobit.genapp.common.IGenAppEntity {

	public long getAuditoriaID();
	public void setAuditoriaID(long _auditoriaID_);

	public java.sql.Timestamp getDataAudit();
	public void setDataAudit(java.sql.Timestamp _dataAudit_);

	public int getTipus();
	public void setTipus(int _tipus_);

	public java.lang.String getUsername();
	public void setUsername(java.lang.String _username_);

	public java.lang.Long getEntitatID();
	public void setEntitatID(java.lang.Long _entitatID_);



  // ======================================

}
