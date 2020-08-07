
package es.caib.carpeta.model.bean;

import es.caib.carpeta.model.entity.Enllaz;


public class EnllazBean implements Enllaz {



private static final long serialVersionUID = 1571810514L;

	long enllazID;// PK
	int tipus;
	long nomID;
	long urlID;
	long entitatID;


  /** Constructor Buit */
  public EnllazBean() {
  }

  /** Constructor amb tots els camps  */
  public EnllazBean(long enllazID , int tipus , long nomID , long urlID , long entitatID) {
    this.enllazID=enllazID;
    this.tipus=tipus;
    this.nomID=nomID;
    this.urlID=urlID;
    this.entitatID=entitatID;
}
  /** Constructor sense valors autoincrementals */
  public EnllazBean(int tipus , long nomID , long urlID , long entitatID) {
    this.tipus=tipus;
    this.nomID=nomID;
    this.urlID=urlID;
    this.entitatID=entitatID;
}
  public EnllazBean(Enllaz __bean) {
    this.setEnllazID(__bean.getEnllazID());
    this.setTipus(__bean.getTipus());
    this.setNomID(__bean.getNomID());
    this.setUrlID(__bean.getUrlID());
    this.setEntitatID(__bean.getEntitatID());
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



  // ======================================

  public static EnllazBean toBean(Enllaz __bean) {
    if (__bean == null) { return null;}
    EnllazBean __tmp = new EnllazBean();
    __tmp.setEnllazID(__bean.getEnllazID());
    __tmp.setTipus(__bean.getTipus());
    __tmp.setNomID(__bean.getNomID());
    __tmp.setUrlID(__bean.getUrlID());
    __tmp.setEntitatID(__bean.getEntitatID());
		return __tmp;
	}



}
