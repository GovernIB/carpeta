
package es.caib.carpeta.model.bean;

import es.caib.carpeta.model.entity.Enllaz;


public class EnllazBean implements Enllaz {



private static final long serialVersionUID = 1571810514L;

	long enllazID;// PK
	int tipus;
	long nomID;
	java.lang.Long descripcioID;
	long urlID;
	long entitatID;
	long logoID;
	java.lang.Long seccioID;
	boolean actiu;


  /** Constructor Buit */
  public EnllazBean() {
  }

  /** Constructor amb tots els camps  */
  public EnllazBean(long enllazID , int tipus , long nomID , java.lang.Long descripcioID , long urlID , long entitatID , long logoID , java.lang.Long seccioID , boolean actiu) {
    this.enllazID=enllazID;
    this.tipus=tipus;
    this.nomID=nomID;
    this.descripcioID=descripcioID;
    this.urlID=urlID;
    this.entitatID=entitatID;
    this.logoID=logoID;
    this.seccioID=seccioID;
    this.actiu=actiu;
}
  /** Constructor sense valors autoincrementals */
  public EnllazBean(int tipus , long nomID , java.lang.Long descripcioID , long urlID , long entitatID , long logoID , java.lang.Long seccioID , boolean actiu) {
    this.tipus=tipus;
    this.nomID=nomID;
    this.descripcioID=descripcioID;
    this.urlID=urlID;
    this.entitatID=entitatID;
    this.logoID=logoID;
    this.seccioID=seccioID;
    this.actiu=actiu;
}
  /** Constructor dels valors Not Null */
  public EnllazBean(long enllazID , int tipus , long nomID , long urlID , long entitatID , long logoID , boolean actiu) {
    this.enllazID=enllazID;
    this.tipus=tipus;
    this.nomID=nomID;
    this.urlID=urlID;
    this.entitatID=entitatID;
    this.logoID=logoID;
    this.actiu=actiu;
}
  public EnllazBean(Enllaz __bean) {
    this.setEnllazID(__bean.getEnllazID());
    this.setTipus(__bean.getTipus());
    this.setNomID(__bean.getNomID());
    this.setDescripcioID(__bean.getDescripcioID());
    this.setUrlID(__bean.getUrlID());
    this.setEntitatID(__bean.getEntitatID());
    this.setLogoID(__bean.getLogoID());
    this.setSeccioID(__bean.getSeccioID());
    this.setActiu(__bean.isActiu());
    // Fitxer
    this.setLogo(FitxerBean.toBean(__bean.getLogo()));
	}

	public long getEnllazID() {
		return(enllazID);
	};
	public void setEnllazID(long _enllazID_) {
		this.enllazID = _enllazID_;
	};

	public int getTipus() {
		return(tipus);
	};
	public void setTipus(int _tipus_) {
		this.tipus = _tipus_;
	};

	public long getNomID() {
		return(nomID);
	};
	public void setNomID(long _nomID_) {
		this.nomID = _nomID_;
	};

	public java.lang.Long getDescripcioID() {
		return(descripcioID);
	};
	public void setDescripcioID(java.lang.Long _descripcioID_) {
		this.descripcioID = _descripcioID_;
	};

	public long getUrlID() {
		return(urlID);
	};
	public void setUrlID(long _urlID_) {
		this.urlID = _urlID_;
	};

	public long getEntitatID() {
		return(entitatID);
	};
	public void setEntitatID(long _entitatID_) {
		this.entitatID = _entitatID_;
	};

	public long getLogoID() {
		return(logoID);
	};
	public void setLogoID(long _logoID_) {
		this.logoID = _logoID_;
	};

	public java.lang.Long getSeccioID() {
		return(seccioID);
	};
	public void setSeccioID(java.lang.Long _seccioID_) {
		this.seccioID = _seccioID_;
	};

	public boolean isActiu() {
		return(actiu);
	};
	public void setActiu(boolean _actiu_) {
		this.actiu = _actiu_;
	};



  // ======================================

  public static EnllazBean toBean(Enllaz __bean) {
    if (__bean == null) { return null;}
    EnllazBean __tmp = new EnllazBean();
    __tmp.setEnllazID(__bean.getEnllazID());
    __tmp.setTipus(__bean.getTipus());
    __tmp.setNomID(__bean.getNomID());
    __tmp.setDescripcioID(__bean.getDescripcioID());
    __tmp.setUrlID(__bean.getUrlID());
    __tmp.setEntitatID(__bean.getEntitatID());
    __tmp.setLogoID(__bean.getLogoID());
    __tmp.setSeccioID(__bean.getSeccioID());
    __tmp.setActiu(__bean.isActiu());
    // Fitxer
    __tmp.setLogo(FitxerBean.toBean(__bean.getLogo()));
		return __tmp;
	}

  protected FitxerBean logo;
  public FitxerBean getLogo() {
    return logo;
  }
  public void setLogo(FitxerBean __field) {
    this. logo = __field;
  }


}
