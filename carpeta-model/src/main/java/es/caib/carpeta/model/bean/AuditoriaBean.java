
package es.caib.carpeta.model.bean;

import es.caib.carpeta.model.entity.Auditoria;


public class AuditoriaBean implements Auditoria {



private static final long serialVersionUID = -273096378L;

	long auditoriaID;// PK
	java.sql.Timestamp dataAudit;
	int tipus;
	java.lang.Long entitatID;
	java.lang.Long usuariID;
	java.lang.String ticketLoginIB;
	java.lang.Integer pluginID;


  /** Constructor Buit */
  public AuditoriaBean() {
  }

  /** Constructor amb tots els camps  */
  public AuditoriaBean(long auditoriaID , java.sql.Timestamp dataAudit , int tipus , java.lang.Long entitatID , java.lang.Long usuariID , java.lang.String ticketLoginIB , java.lang.Integer pluginID) {
    this.auditoriaID=auditoriaID;
    this.dataAudit=dataAudit;
    this.tipus=tipus;
    this.entitatID=entitatID;
    this.usuariID=usuariID;
    this.ticketLoginIB=ticketLoginIB;
    this.pluginID=pluginID;
}
  /** Constructor sense valors autoincrementals */
  public AuditoriaBean(java.sql.Timestamp dataAudit , int tipus , java.lang.Long entitatID , java.lang.Long usuariID , java.lang.String ticketLoginIB , java.lang.Integer pluginID) {
    this.dataAudit=dataAudit;
    this.tipus=tipus;
    this.entitatID=entitatID;
    this.usuariID=usuariID;
    this.ticketLoginIB=ticketLoginIB;
    this.pluginID=pluginID;
}
  /** Constructor dels valors Not Null */
  public AuditoriaBean(long auditoriaID , java.sql.Timestamp dataAudit , int tipus) {
    this.auditoriaID=auditoriaID;
    this.dataAudit=dataAudit;
    this.tipus=tipus;
}
  public AuditoriaBean(Auditoria __bean) {
    this.setAuditoriaID(__bean.getAuditoriaID());
    this.setDataAudit(__bean.getDataAudit());
    this.setTipus(__bean.getTipus());
    this.setEntitatID(__bean.getEntitatID());
    this.setUsuariID(__bean.getUsuariID());
    this.setTicketLoginIB(__bean.getTicketLoginIB());
    this.setPluginID(__bean.getPluginID());
	}

	public long getAuditoriaID() {
		return(auditoriaID);
	};
	public void setAuditoriaID(long _auditoriaID_) {
		this.auditoriaID = _auditoriaID_;
	};

	public java.sql.Timestamp getDataAudit() {
		return(dataAudit);
	};
	public void setDataAudit(java.sql.Timestamp _dataAudit_) {
		this.dataAudit = _dataAudit_;
	};

	public int getTipus() {
		return(tipus);
	};
	public void setTipus(int _tipus_) {
		this.tipus = _tipus_;
	};

	public java.lang.Long getEntitatID() {
		return(entitatID);
	};
	public void setEntitatID(java.lang.Long _entitatID_) {
		this.entitatID = _entitatID_;
	};

	public java.lang.Long getUsuariID() {
		return(usuariID);
	};
	public void setUsuariID(java.lang.Long _usuariID_) {
		this.usuariID = _usuariID_;
	};

	public java.lang.String getTicketLoginIB() {
		return(ticketLoginIB);
	};
	public void setTicketLoginIB(java.lang.String _ticketLoginIB_) {
		this.ticketLoginIB = _ticketLoginIB_;
	};

	public java.lang.Integer getPluginID() {
		return(pluginID);
	};
	public void setPluginID(java.lang.Integer _pluginID_) {
		this.pluginID = _pluginID_;
	};



  // ======================================

  public static AuditoriaBean toBean(Auditoria __bean) {
    if (__bean == null) { return null;}
    AuditoriaBean __tmp = new AuditoriaBean();
    __tmp.setAuditoriaID(__bean.getAuditoriaID());
    __tmp.setDataAudit(__bean.getDataAudit());
    __tmp.setTipus(__bean.getTipus());
    __tmp.setEntitatID(__bean.getEntitatID());
    __tmp.setUsuariID(__bean.getUsuariID());
    __tmp.setTicketLoginIB(__bean.getTicketLoginIB());
    __tmp.setPluginID(__bean.getPluginID());
		return __tmp;
	}



}
