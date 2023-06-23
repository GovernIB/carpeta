
package es.caib.carpeta.model.bean;

import es.caib.carpeta.model.entity.PreguntesFrequents;


public class PreguntesFrequentsBean implements PreguntesFrequents {



	long preguntesFrequentsID;// PK
	long enunciatID;
	int ordre;
	long entitatID;
	java.lang.String respostaCa;
	java.lang.String respostaEs;


  /** Constructor Buit */
  public PreguntesFrequentsBean() {
  }

  /** Constructor amb tots els camps  */
  public PreguntesFrequentsBean(long preguntesFrequentsID , long enunciatID , int ordre , long entitatID , java.lang.String respostaCa , java.lang.String respostaEs) {
    this.preguntesFrequentsID=preguntesFrequentsID;
    this.enunciatID=enunciatID;
    this.ordre=ordre;
    this.entitatID=entitatID;
    this.respostaCa=respostaCa;
    this.respostaEs=respostaEs;
}
  /** Constructor sense valors autoincrementals */
  public PreguntesFrequentsBean(long enunciatID , int ordre , long entitatID , java.lang.String respostaCa , java.lang.String respostaEs) {
    this.enunciatID=enunciatID;
    this.ordre=ordre;
    this.entitatID=entitatID;
    this.respostaCa=respostaCa;
    this.respostaEs=respostaEs;
}
  public PreguntesFrequentsBean(PreguntesFrequents __bean) {
    this.setPreguntesFrequentsID(__bean.getPreguntesFrequentsID());
    this.setEnunciatID(__bean.getEnunciatID());
    this.setOrdre(__bean.getOrdre());
    this.setEntitatID(__bean.getEntitatID());
    this.setRespostaCa(__bean.getRespostaCa());
    this.setRespostaEs(__bean.getRespostaEs());
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

	public java.lang.String getRespostaCa() {
		return(respostaCa);
	};
	public void setRespostaCa(java.lang.String _respostaCa_) {
		this.respostaCa = _respostaCa_;
	};

	public java.lang.String getRespostaEs() {
		return(respostaEs);
	};
	public void setRespostaEs(java.lang.String _respostaEs_) {
		this.respostaEs = _respostaEs_;
	};



  // ======================================

  public static PreguntesFrequentsBean toBean(PreguntesFrequents __bean) {
    if (__bean == null) { return null;}
    PreguntesFrequentsBean __tmp = new PreguntesFrequentsBean();
    __tmp.setPreguntesFrequentsID(__bean.getPreguntesFrequentsID());
    __tmp.setEnunciatID(__bean.getEnunciatID());
    __tmp.setOrdre(__bean.getOrdre());
    __tmp.setEntitatID(__bean.getEntitatID());
    __tmp.setRespostaCa(__bean.getRespostaCa());
    __tmp.setRespostaEs(__bean.getRespostaEs());
		return __tmp;
	}



}
