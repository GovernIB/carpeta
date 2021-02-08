
package es.caib.carpeta.model.bean;

import es.caib.carpeta.model.entity.Seccio;


public class SeccioBean implements Seccio {



private static final long serialVersionUID = 223694536L;

	long seccioID;// PK
	long nomID;
	long descripcioID;
	boolean activa;
	long iconaID;
	java.lang.Long seccioPareID;
	long entitatID;


  /** Constructor Buit */
  public SeccioBean() {
  }

  /** Constructor amb tots els camps  */
  public SeccioBean(long seccioID , long nomID , long descripcioID , boolean activa , long iconaID , java.lang.Long seccioPareID , long entitatID) {
    this.seccioID=seccioID;
    this.nomID=nomID;
    this.descripcioID=descripcioID;
    this.activa=activa;
    this.iconaID=iconaID;
    this.seccioPareID=seccioPareID;
    this.entitatID=entitatID;
}
  /** Constructor sense valors autoincrementals */
  public SeccioBean(long nomID , long descripcioID , boolean activa , long iconaID , java.lang.Long seccioPareID , long entitatID) {
    this.nomID=nomID;
    this.descripcioID=descripcioID;
    this.activa=activa;
    this.iconaID=iconaID;
    this.seccioPareID=seccioPareID;
    this.entitatID=entitatID;
}
  public SeccioBean(Seccio __bean) {
    this.setSeccioID(__bean.getSeccioID());
    this.setNomID(__bean.getNomID());
    this.setDescripcioID(__bean.getDescripcioID());
    this.setActiva(__bean.isActiva());
    this.setIconaID(__bean.getIconaID());
    this.setSeccioPareID(__bean.getSeccioPareID());
    this.setEntitatID(__bean.getEntitatID());
    // Fitxer
    this.setIcona(FitxerBean.toBean(__bean.getIcona()));
	}

	public long getSeccioID() {
		return(seccioID);
	};
	public void setSeccioID(long _seccioID_) {
		this.seccioID = _seccioID_;
	};

	public long getNomID() {
		return(nomID);
	};
	public void setNomID(long _nomID_) {
		this.nomID = _nomID_;
	};

	public long getDescripcioID() {
		return(descripcioID);
	};
	public void setDescripcioID(long _descripcioID_) {
		this.descripcioID = _descripcioID_;
	};

	public boolean isActiva() {
		return(activa);
	};
	public void setActiva(boolean _activa_) {
		this.activa = _activa_;
	};

	public long getIconaID() {
		return(iconaID);
	};
	public void setIconaID(long _iconaID_) {
		this.iconaID = _iconaID_;
	};

	public java.lang.Long getSeccioPareID() {
		return(seccioPareID);
	};
	public void setSeccioPareID(java.lang.Long _seccioPareID_) {
		this.seccioPareID = _seccioPareID_;
	};

	public long getEntitatID() {
		return(entitatID);
	};
	public void setEntitatID(long _entitatID_) {
		this.entitatID = _entitatID_;
	};



  // ======================================

  public static SeccioBean toBean(Seccio __bean) {
    if (__bean == null) { return null;}
    SeccioBean __tmp = new SeccioBean();
    __tmp.setSeccioID(__bean.getSeccioID());
    __tmp.setNomID(__bean.getNomID());
    __tmp.setDescripcioID(__bean.getDescripcioID());
    __tmp.setActiva(__bean.isActiva());
    __tmp.setIconaID(__bean.getIconaID());
    __tmp.setSeccioPareID(__bean.getSeccioPareID());
    __tmp.setEntitatID(__bean.getEntitatID());
    // Fitxer
    __tmp.setIcona(FitxerBean.toBean(__bean.getIcona()));
		return __tmp;
	}

  protected FitxerBean icona;
  public FitxerBean getIcona() {
    return icona;
  }
  public void setIcona(FitxerBean __field) {
    this. icona = __field;
  }


}
