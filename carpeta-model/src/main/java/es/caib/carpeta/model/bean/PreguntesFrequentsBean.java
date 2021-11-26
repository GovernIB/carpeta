
package es.caib.carpeta.model.bean;

import es.caib.carpeta.model.entity.PreguntesFrequents;


public class PreguntesFrequentsBean implements PreguntesFrequents {



private static final long serialVersionUID = -431401584L;

	long preguntesFrequentsID;// PK
	long enunciatID;
	long respostaID;
	int ordre;
	long entitatID;


  /** Constructor Buit */
  public PreguntesFrequentsBean() {
  }

  /** Constructor amb tots els camps  */
  public PreguntesFrequentsBean(long preguntesFrequentsID , long enunciatID , long respostaID , int ordre , long entitatID) {
    this.preguntesFrequentsID=preguntesFrequentsID;
    this.enunciatID=enunciatID;
    this.respostaID=respostaID;
    this.ordre=ordre;
    this.entitatID=entitatID;
}
  /** Constructor sense valors autoincrementals */
  public PreguntesFrequentsBean(long enunciatID , long respostaID , int ordre , long entitatID) {
    this.enunciatID=enunciatID;
    this.respostaID=respostaID;
    this.ordre=ordre;
    this.entitatID=entitatID;
}
  public PreguntesFrequentsBean(PreguntesFrequents __bean) {
    this.setPreguntesFrequentsID(__bean.getPreguntesFrequentsID());
    this.setEnunciatID(__bean.getEnunciatID());
    this.setRespostaID(__bean.getRespostaID());
    this.setOrdre(__bean.getOrdre());
    this.setEntitatID(__bean.getEntitatID());
	}

	public long getPreguntesFrequentsID() {
		return(preguntesFrequentsID);
	};
	public void setPreguntesFrequentsID(long _preguntesFrequentsID_) {
		this.preguntesFrequentsID = _preguntesFrequentsID_;
	};

	public long getEnunciatID() {
		return(enunciatID);
	};
	public void setEnunciatID(long _enunciatID_) {
		this.enunciatID = _enunciatID_;
	};

	public long getRespostaID() {
		return(respostaID);
	};
	public void setRespostaID(long _respostaID_) {
		this.respostaID = _respostaID_;
	};

	public int getOrdre() {
		return(ordre);
	};
	public void setOrdre(int _ordre_) {
		this.ordre = _ordre_;
	};

	public long getEntitatID() {
		return(entitatID);
	};
	public void setEntitatID(long _entitatID_) {
		this.entitatID = _entitatID_;
	};



  // ======================================

  public static PreguntesFrequentsBean toBean(PreguntesFrequents __bean) {
    if (__bean == null) { return null;}
    PreguntesFrequentsBean __tmp = new PreguntesFrequentsBean();
    __tmp.setPreguntesFrequentsID(__bean.getPreguntesFrequentsID());
    __tmp.setEnunciatID(__bean.getEnunciatID());
    __tmp.setRespostaID(__bean.getRespostaID());
    __tmp.setOrdre(__bean.getOrdre());
    __tmp.setEntitatID(__bean.getEntitatID());
		return __tmp;
	}



}
