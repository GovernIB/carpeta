
package es.caib.carpeta.model.bean;

import es.caib.carpeta.model.entity.Auditoria;


public class AuditoriaBean implements Auditoria {



	long auditoriaID;// PK
	int tipus;
	java.lang.String objecte;
	java.sql.Timestamp dataAudit;
	java.lang.String username;
	java.lang.Long entitatID;


  /** Constructor Buit */
  public AuditoriaBean() {
  }

  /** Constructor amb tots els camps  */
  public AuditoriaBean(long auditoriaID , int tipus , java.lang.String objecte , java.sql.Timestamp dataAudit , java.lang.String username , java.lang.Long entitatID) {
    this.auditoriaID=auditoriaID;
    this.tipus=tipus;
    this.objecte=objecte;
    this.dataAudit=dataAudit;
    this.username=username;
    this.entitatID=entitatID;
}
  /** Constructor sense valors autoincrementals */
  public AuditoriaBean(int tipus , java.lang.String objecte , java.sql.Timestamp dataAudit , java.lang.String username , java.lang.Long entitatID) {
    this.tipus=tipus;
    this.objecte=objecte;
    this.dataAudit=dataAudit;
    this.username=username;
    this.entitatID=entitatID;
}
  /** Constructor dels valors Not Null */
  public AuditoriaBean(long auditoriaID , int tipus , java.sql.Timestamp dataAudit) {
    this.auditoriaID=auditoriaID;
    this.tipus=tipus;
    this.dataAudit=dataAudit;
}
  public AuditoriaBean(Auditoria __bean) {
    this.setAuditoriaID(__bean.getAuditoriaID());
    this.setTipus(__bean.getTipus());
    this.setObjecte(__bean.getObjecte());
    this.setDataAudit(__bean.getDataAudit());
    this.setUsername(__bean.getUsername());
    this.setEntitatID(__bean.getEntitatID());
	}

	public long getAuditoriaID() {
		return(auditoriaID);
	};
	public void setAuditoriaID(long _auditoriaID_) {
		this.auditoriaID = _auditoriaID_;
	};

	public int getTipus() {
		return(tipus);
	};
	public void setTipus(int _tipus_) {
		this.tipus = _tipus_;
	};

	public java.lang.String getObjecte() {
		return(objecte);
	};
	public void setObjecte(java.lang.String _objecte_) {
		this.objecte = _objecte_;
	};

	public java.sql.Timestamp getDataAudit() {
		return(dataAudit);
	};
	public void setDataAudit(java.sql.Timestamp _dataAudit_) {
		this.dataAudit = _dataAudit_;
	};

	public java.lang.String getUsername() {
		return(username);
	};
	public void setUsername(java.lang.String _username_) {
		this.username = _username_;
	};

	public java.lang.Long getEntitatID() {
		return(entitatID);
	};
	public void setEntitatID(java.lang.Long _entitatID_) {
		this.entitatID = _entitatID_;
	};



  // ======================================

  public static AuditoriaBean toBean(Auditoria __bean) {
    if (__bean == null) { return null;}
    AuditoriaBean __tmp = new AuditoriaBean();
    __tmp.setAuditoriaID(__bean.getAuditoriaID());
    __tmp.setTipus(__bean.getTipus());
    __tmp.setObjecte(__bean.getObjecte());
    __tmp.setDataAudit(__bean.getDataAudit());
    __tmp.setUsername(__bean.getUsername());
    __tmp.setEntitatID(__bean.getEntitatID());
		return __tmp;
	}



}
