package es.caib.carpeta.model.entity;

public interface Auditoria extends org.fundaciobit.genapp.common.IGenAppEntity {

	public long getAuditoriaID();
	public void setAuditoriaID(long _auditoriaID_);

	public java.sql.Timestamp getDataAudit();
	public void setDataAudit(java.sql.Timestamp _dataAudit_);

	public int getTipus();
	public void setTipus(int _tipus_);

	public java.lang.Long getEntitatID();
	public void setEntitatID(java.lang.Long _entitatID_);

	public java.lang.Long getUsuariID();
	public void setUsuariID(java.lang.Long _usuariID_);

	public java.lang.String getTicketLoginIB();
	public void setTicketLoginIB(java.lang.String _ticketLoginIB_);

	public java.lang.Integer getPluginID();
	public void setPluginID(java.lang.Integer _pluginID_);



  // ======================================

}
