
package es.caib.carpeta.model.bean;

import es.caib.carpeta.model.entity.Acces;


public class AccesBean implements Acces {



private static final long serialVersionUID = -112525025L;

	long accesID;// PK
	java.lang.String nom;
	java.lang.String llinatges;
	java.lang.String nif;
	java.lang.String ip;
	java.lang.String proveidorIdentitat;
	java.lang.String nivellSeguretat;
	java.lang.Integer resultatAutenticacio;
	java.sql.Timestamp dataDarrerAcces;
	java.lang.String idioma;
	long entitatID;
	int tipus;


  /** Constructor Buit */
  public AccesBean() {
  }

  /** Constructor amb tots els camps  */
  public AccesBean(long accesID , java.lang.String nom , java.lang.String llinatges , java.lang.String nif , java.lang.String ip , java.lang.String proveidorIdentitat , java.lang.String nivellSeguretat , java.lang.Integer resultatAutenticacio , java.sql.Timestamp dataDarrerAcces , java.lang.String idioma , long entitatID , int tipus) {
    this.accesID=accesID;
    this.nom=nom;
    this.llinatges=llinatges;
    this.nif=nif;
    this.ip=ip;
    this.proveidorIdentitat=proveidorIdentitat;
    this.nivellSeguretat=nivellSeguretat;
    this.resultatAutenticacio=resultatAutenticacio;
    this.dataDarrerAcces=dataDarrerAcces;
    this.idioma=idioma;
    this.entitatID=entitatID;
    this.tipus=tipus;
}
  /** Constructor sense valors autoincrementals */
  public AccesBean(java.lang.String nom , java.lang.String llinatges , java.lang.String nif , java.lang.String ip , java.lang.String proveidorIdentitat , java.lang.String nivellSeguretat , java.lang.Integer resultatAutenticacio , java.sql.Timestamp dataDarrerAcces , java.lang.String idioma , long entitatID , int tipus) {
    this.nom=nom;
    this.llinatges=llinatges;
    this.nif=nif;
    this.ip=ip;
    this.proveidorIdentitat=proveidorIdentitat;
    this.nivellSeguretat=nivellSeguretat;
    this.resultatAutenticacio=resultatAutenticacio;
    this.dataDarrerAcces=dataDarrerAcces;
    this.idioma=idioma;
    this.entitatID=entitatID;
    this.tipus=tipus;
}
  /** Constructor dels valors Not Null */
  public AccesBean(long accesID , long entitatID , int tipus) {
    this.accesID=accesID;
    this.entitatID=entitatID;
    this.tipus=tipus;
}
  public AccesBean(Acces __bean) {
    this.setAccesID(__bean.getAccesID());
    this.setNom(__bean.getNom());
    this.setLlinatges(__bean.getLlinatges());
    this.setNif(__bean.getNif());
    this.setIp(__bean.getIp());
    this.setProveidorIdentitat(__bean.getProveidorIdentitat());
    this.setNivellSeguretat(__bean.getNivellSeguretat());
    this.setResultatAutenticacio(__bean.getResultatAutenticacio());
    this.setDataDarrerAcces(__bean.getDataDarrerAcces());
    this.setIdioma(__bean.getIdioma());
    this.setEntitatID(__bean.getEntitatID());
    this.setTipus(__bean.getTipus());
	}

	public long getAccesID() {
		return(accesID);
	};
	public void setAccesID(long _accesID_) {
		this.accesID = _accesID_;
	};

	public java.lang.String getNom() {
		return(nom);
	};
	public void setNom(java.lang.String _nom_) {
		this.nom = _nom_;
	};

	public java.lang.String getLlinatges() {
		return(llinatges);
	};
	public void setLlinatges(java.lang.String _llinatges_) {
		this.llinatges = _llinatges_;
	};

	public java.lang.String getNif() {
		return(nif);
	};
	public void setNif(java.lang.String _nif_) {
		this.nif = _nif_;
	};

	public java.lang.String getIp() {
		return(ip);
	};
	public void setIp(java.lang.String _ip_) {
		this.ip = _ip_;
	};

	public java.lang.String getProveidorIdentitat() {
		return(proveidorIdentitat);
	};
	public void setProveidorIdentitat(java.lang.String _proveidorIdentitat_) {
		this.proveidorIdentitat = _proveidorIdentitat_;
	};

	public java.lang.String getNivellSeguretat() {
		return(nivellSeguretat);
	};
	public void setNivellSeguretat(java.lang.String _nivellSeguretat_) {
		this.nivellSeguretat = _nivellSeguretat_;
	};

	public java.lang.Integer getResultatAutenticacio() {
		return(resultatAutenticacio);
	};
	public void setResultatAutenticacio(java.lang.Integer _resultatAutenticacio_) {
		this.resultatAutenticacio = _resultatAutenticacio_;
	};

	public java.sql.Timestamp getDataDarrerAcces() {
		return(dataDarrerAcces);
	};
	public void setDataDarrerAcces(java.sql.Timestamp _dataDarrerAcces_) {
		this.dataDarrerAcces = _dataDarrerAcces_;
	};

	public java.lang.String getIdioma() {
		return(idioma);
	};
	public void setIdioma(java.lang.String _idioma_) {
		this.idioma = _idioma_;
	};

	public long getEntitatID() {
		return(entitatID);
	};
	public void setEntitatID(long _entitatID_) {
		this.entitatID = _entitatID_;
	};

	public int getTipus() {
		return(tipus);
	};
	public void setTipus(int _tipus_) {
		this.tipus = _tipus_;
	};



  // ======================================

  public static AccesBean toBean(Acces __bean) {
    if (__bean == null) { return null;}
    AccesBean __tmp = new AccesBean();
    __tmp.setAccesID(__bean.getAccesID());
    __tmp.setNom(__bean.getNom());
    __tmp.setLlinatges(__bean.getLlinatges());
    __tmp.setNif(__bean.getNif());
    __tmp.setIp(__bean.getIp());
    __tmp.setProveidorIdentitat(__bean.getProveidorIdentitat());
    __tmp.setNivellSeguretat(__bean.getNivellSeguretat());
    __tmp.setResultatAutenticacio(__bean.getResultatAutenticacio());
    __tmp.setDataDarrerAcces(__bean.getDataDarrerAcces());
    __tmp.setIdioma(__bean.getIdioma());
    __tmp.setEntitatID(__bean.getEntitatID());
    __tmp.setTipus(__bean.getTipus());
		return __tmp;
	}



}
