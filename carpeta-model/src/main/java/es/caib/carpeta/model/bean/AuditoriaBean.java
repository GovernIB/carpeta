
package es.caib.carpeta.model.bean;

import es.caib.carpeta.model.entity.Auditoria;


public class AuditoriaBean implements Auditoria {



private static final long serialVersionUID = -273096378L;

	long auditoriaID;// PK
	int accio;
	java.lang.String element;
	java.sql.Timestamp dataAudit;
	java.lang.Long entitatID;
	java.lang.Long usuariID;


  /** Constructor Buit */
  public AuditoriaBean() {
  }

  /** Constructor amb tots els camps  */
  public AuditoriaBean(long auditoriaID , int accio , java.lang.String element , java.sql.Timestamp dataAudit , java.lang.Long entitatID , java.lang.Long usuariID) {
    this.auditoriaID=auditoriaID;
    this.accio=accio;
    this.element=element;
    this.dataAudit=dataAudit;
    this.entitatID=entitatID;
    this.usuariID=usuariID;
}
  /** Constructor sense valors autoincrementals */
  public AuditoriaBean(int accio , java.lang.String element , java.sql.Timestamp dataAudit , java.lang.Long entitatID , java.lang.Long usuariID) {
    this.accio=accio;
    this.element=element;
    this.dataAudit=dataAudit;
    this.entitatID=entitatID;
    this.usuariID=usuariID;
}
  /** Constructor dels valors Not Null */
  public AuditoriaBean(long auditoriaID , int accio , java.sql.Timestamp dataAudit) {
    this.auditoriaID=auditoriaID;
    this.accio=accio;
    this.dataAudit=dataAudit;
}
  public AuditoriaBean(Auditoria __bean) {
    this.setAuditoriaID(__bean.getAuditoriaID());
    this.setAccio(__bean.getAccio());
    this.setElement(__bean.getElement());
    this.setDataAudit(__bean.getDataAudit());
    this.setEntitatID(__bean.getEntitatID());
    this.setUsuariID(__bean.getUsuariID());
	}

	public long getAuditoriaID() {
		return(auditoriaID);
	};
	public void setAuditoriaID(long _auditoriaID_) {
		this.auditoriaID = _auditoriaID_;
	};

	public int getAccio() {
		return(accio);
	};
	public void setAccio(int _accio_) {
		this.accio = _accio_;
	};

	public java.lang.String getElement() {
		return(element);
	};
	public void setElement(java.lang.String _element_) {
		this.element = _element_;
	};

	public java.sql.Timestamp getDataAudit() {
		return(dataAudit);
	};
	public void setDataAudit(java.sql.Timestamp _dataAudit_) {
		this.dataAudit = _dataAudit_;
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



  // ======================================

  public static AuditoriaBean toBean(Auditoria __bean) {
    if (__bean == null) { return null;}
    AuditoriaBean __tmp = new AuditoriaBean();
    __tmp.setAuditoriaID(__bean.getAuditoriaID());
    __tmp.setAccio(__bean.getAccio());
    __tmp.setElement(__bean.getElement());
    __tmp.setDataAudit(__bean.getDataAudit());
    __tmp.setEntitatID(__bean.getEntitatID());
    __tmp.setUsuariID(__bean.getUsuariID());
		return __tmp;
	}



}
