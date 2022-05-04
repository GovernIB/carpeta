
package es.caib.carpeta.model.bean;

import es.caib.carpeta.model.entity.PreguntesFrequents;


public class PreguntesFrequentsBean implements PreguntesFrequents {



private static final long serialVersionUID = -431401584L;

	long preguntesFrequentsID;// PK
	long enunciatID;
	long respostaID;
	int ordre;
	long entitatID;
	java.lang.Long fitxer1ID;
	java.lang.Long fitxer2ID;
	java.lang.Long fitxer3ID;


  /** Constructor Buit */
  public PreguntesFrequentsBean() {
  }

  /** Constructor amb tots els camps  */
  public PreguntesFrequentsBean(long preguntesFrequentsID , long enunciatID , long respostaID , int ordre , long entitatID , java.lang.Long fitxer1ID , java.lang.Long fitxer2ID , java.lang.Long fitxer3ID) {
    this.preguntesFrequentsID=preguntesFrequentsID;
    this.enunciatID=enunciatID;
    this.respostaID=respostaID;
    this.ordre=ordre;
    this.entitatID=entitatID;
    this.fitxer1ID=fitxer1ID;
    this.fitxer2ID=fitxer2ID;
    this.fitxer3ID=fitxer3ID;
}
  /** Constructor sense valors autoincrementals */
  public PreguntesFrequentsBean(long enunciatID , long respostaID , int ordre , long entitatID , java.lang.Long fitxer1ID , java.lang.Long fitxer2ID , java.lang.Long fitxer3ID) {
    this.enunciatID=enunciatID;
    this.respostaID=respostaID;
    this.ordre=ordre;
    this.entitatID=entitatID;
    this.fitxer1ID=fitxer1ID;
    this.fitxer2ID=fitxer2ID;
    this.fitxer3ID=fitxer3ID;
}
  /** Constructor dels valors Not Null */
  public PreguntesFrequentsBean(long preguntesFrequentsID , long enunciatID , long respostaID , int ordre , long entitatID) {
    this.preguntesFrequentsID=preguntesFrequentsID;
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
    this.setFitxer1ID(__bean.getFitxer1ID());
    this.setFitxer2ID(__bean.getFitxer2ID());
    this.setFitxer3ID(__bean.getFitxer3ID());
    // Fitxer
    this.setFitxer1(FitxerBean.toBean(__bean.getFitxer1()));
    // Fitxer
    this.setFitxer2(FitxerBean.toBean(__bean.getFitxer2()));
    // Fitxer
    this.setFitxer3(FitxerBean.toBean(__bean.getFitxer3()));
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

	public java.lang.Long getFitxer1ID() {
		return(fitxer1ID);
	};
	public void setFitxer1ID(java.lang.Long _fitxer1ID_) {
		this.fitxer1ID = _fitxer1ID_;
	};

	public java.lang.Long getFitxer2ID() {
		return(fitxer2ID);
	};
	public void setFitxer2ID(java.lang.Long _fitxer2ID_) {
		this.fitxer2ID = _fitxer2ID_;
	};

	public java.lang.Long getFitxer3ID() {
		return(fitxer3ID);
	};
	public void setFitxer3ID(java.lang.Long _fitxer3ID_) {
		this.fitxer3ID = _fitxer3ID_;
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
    __tmp.setFitxer1ID(__bean.getFitxer1ID());
    __tmp.setFitxer2ID(__bean.getFitxer2ID());
    __tmp.setFitxer3ID(__bean.getFitxer3ID());
    // Fitxer
    __tmp.setFitxer1(FitxerBean.toBean(__bean.getFitxer1()));
    // Fitxer
    __tmp.setFitxer2(FitxerBean.toBean(__bean.getFitxer2()));
    // Fitxer
    __tmp.setFitxer3(FitxerBean.toBean(__bean.getFitxer3()));
		return __tmp;
	}

  protected FitxerBean fitxer1;
  public FitxerBean getFitxer1() {
    return fitxer1;
  }
  public void setFitxer1(FitxerBean __field) {
    this. fitxer1 = __field;
  }
  protected FitxerBean fitxer2;
  public FitxerBean getFitxer2() {
    return fitxer2;
  }
  public void setFitxer2(FitxerBean __field) {
    this. fitxer2 = __field;
  }
  protected FitxerBean fitxer3;
  public FitxerBean getFitxer3() {
    return fitxer3;
  }
  public void setFitxer3(FitxerBean __field) {
    this. fitxer3 = __field;
  }


}
