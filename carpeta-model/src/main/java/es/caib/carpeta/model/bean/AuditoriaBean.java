
package es.caib.carpeta.model.bean;

import es.caib.carpeta.model.entity.Auditoria;


public class AuditoriaBean implements Auditoria {



private static final long serialVersionUID = -273096378L;

	long auditoriaID;// PK
	java.sql.Timestamp dataAudit;
	int tipus;
	java.lang.String username;
	java.lang.String usuariClave;
	java.lang.Long entitatID;
	java.lang.Long pluginID;


  /** Constructor Buit */
  public AuditoriaBean() {
  }

  /** Constructor amb tots els camps  */
  public AuditoriaBean(long auditoriaID , java.sql.Timestamp dataAudit , int tipus , java.lang.String username , java.lang.String usuariClave , java.lang.Long entitatID , java.lang.Long pluginID) {
    this.auditoriaID=auditoriaID;
    this.dataAudit=dataAudit;
    this.tipus=tipus;
    this.username=username;
    this.usuariClave=usuariClave;
    this.entitatID=entitatID;
    this.pluginID=pluginID;
}
  /** Constructor sense valors autoincrementals */
  public AuditoriaBean(java.sql.Timestamp dataAudit , int tipus , java.lang.String username , java.lang.String usuariClave , java.lang.Long entitatID , java.lang.Long pluginID) {
    this.dataAudit=dataAudit;
    this.tipus=tipus;
    this.username=username;
    this.usuariClave=usuariClave;
    this.entitatID=entitatID;
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
    this.setUsername(__bean.getUsername());
    this.setUsuariClave(__bean.getUsuariClave());
    this.setEntitatID(__bean.getEntitatID());
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

	public java.lang.String getUsername() {
		return(username);
	};
	public void setUsername(java.lang.String _username_) {
		this.username = _username_;
	};

	public java.lang.String getUsuariClave() {
		return(usuariClave);
	};
	public void setUsuariClave(java.lang.String _usuariClave_) {
		this.usuariClave = _usuariClave_;
	};

	public java.lang.Long getEntitatID() {
		return(entitatID);
	};
	public void setEntitatID(java.lang.Long _entitatID_) {
		this.entitatID = _entitatID_;
	};

	public java.lang.Long getPluginID() {
		return(pluginID);
	};
	public void setPluginID(java.lang.Long _pluginID_) {
		this.pluginID = _pluginID_;
	};



  // ======================================

  public static AuditoriaBean toBean(Auditoria __bean) {
    if (__bean == null) { return null;}
    AuditoriaBean __tmp = new AuditoriaBean();
    __tmp.setAuditoriaID(__bean.getAuditoriaID());
    __tmp.setDataAudit(__bean.getDataAudit());
    __tmp.setTipus(__bean.getTipus());
    __tmp.setUsername(__bean.getUsername());
    __tmp.setUsuariClave(__bean.getUsuariClave());
    __tmp.setEntitatID(__bean.getEntitatID());
    __tmp.setPluginID(__bean.getPluginID());
		return __tmp;
	}



}
