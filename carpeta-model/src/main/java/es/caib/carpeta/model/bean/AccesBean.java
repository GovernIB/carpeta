
package es.caib.carpeta.model.bean;

import es.caib.carpeta.model.entity.Acces;


public class AccesBean implements Acces {



private static final long serialVersionUID = -112525025L;

	long accesID;// PK
	int tipus;
	java.lang.String nom;
	java.lang.String llinatges;
	java.lang.String nif;
	java.lang.String ip;
	java.lang.String proveidorIdentitat;
	java.lang.String metodeAutenticacio;
	java.lang.Integer qaa;
	java.sql.Timestamp dataAcces;
	java.lang.Long pluginID;
	long entitatID;
	java.lang.String idioma;
	boolean resultat;
	java.lang.String idsessio;


  /** Constructor Buit */
  public AccesBean() {
  }

  /** Constructor amb tots els camps  */
  public AccesBean(long accesID , int tipus , java.lang.String nom , java.lang.String llinatges , java.lang.String nif , java.lang.String ip , java.lang.String proveidorIdentitat , java.lang.String metodeAutenticacio , java.lang.Integer qaa , java.sql.Timestamp dataAcces , java.lang.Long pluginID , long entitatID , java.lang.String idioma , boolean resultat , java.lang.String idsessio) {
    this.accesID=accesID;
    this.tipus=tipus;
    this.nom=nom;
    this.llinatges=llinatges;
    this.nif=nif;
    this.ip=ip;
    this.proveidorIdentitat=proveidorIdentitat;
    this.metodeAutenticacio=metodeAutenticacio;
    this.qaa=qaa;
    this.dataAcces=dataAcces;
    this.pluginID=pluginID;
    this.entitatID=entitatID;
    this.idioma=idioma;
    this.resultat=resultat;
    this.idsessio=idsessio;
}
  /** Constructor sense valors autoincrementals */
  public AccesBean(int tipus , java.lang.String nom , java.lang.String llinatges , java.lang.String nif , java.lang.String ip , java.lang.String proveidorIdentitat , java.lang.String metodeAutenticacio , java.lang.Integer qaa , java.sql.Timestamp dataAcces , java.lang.Long pluginID , long entitatID , java.lang.String idioma , boolean resultat , java.lang.String idsessio) {
    this.tipus=tipus;
    this.nom=nom;
    this.llinatges=llinatges;
    this.nif=nif;
    this.ip=ip;
    this.proveidorIdentitat=proveidorIdentitat;
    this.metodeAutenticacio=metodeAutenticacio;
    this.qaa=qaa;
    this.dataAcces=dataAcces;
    this.pluginID=pluginID;
    this.entitatID=entitatID;
    this.idioma=idioma;
    this.resultat=resultat;
    this.idsessio=idsessio;
}
  /** Constructor dels valors Not Null */
  public AccesBean(long accesID , int tipus , long entitatID , boolean resultat) {
    this.accesID=accesID;
    this.tipus=tipus;
    this.entitatID=entitatID;
    this.resultat=resultat;
}
  public AccesBean(Acces __bean) {
    this.setAccesID(__bean.getAccesID());
    this.setTipus(__bean.getTipus());
    this.setNom(__bean.getNom());
    this.setLlinatges(__bean.getLlinatges());
    this.setNif(__bean.getNif());
    this.setIp(__bean.getIp());
    this.setProveidorIdentitat(__bean.getProveidorIdentitat());
    this.setMetodeAutenticacio(__bean.getMetodeAutenticacio());
    this.setQaa(__bean.getQaa());
    this.setDataAcces(__bean.getDataAcces());
    this.setPluginID(__bean.getPluginID());
    this.setEntitatID(__bean.getEntitatID());
    this.setIdioma(__bean.getIdioma());
    this.setResultat(__bean.isResultat());
    this.setIdsessio(__bean.getIdsessio());
	}

	public long getAccesID() {
		return(accesID);
	};
	public void setAccesID(long _accesID_) {
		this.accesID = _accesID_;
	};

	public int getTipus() {
		return(tipus);
	};
	public void setTipus(int _tipus_) {
		this.tipus = _tipus_;
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

	public java.lang.String getMetodeAutenticacio() {
		return(metodeAutenticacio);
	};
	public void setMetodeAutenticacio(java.lang.String _metodeAutenticacio_) {
		this.metodeAutenticacio = _metodeAutenticacio_;
	};

	public java.lang.Integer getQaa() {
		return(qaa);
	};
	public void setQaa(java.lang.Integer _qaa_) {
		this.qaa = _qaa_;
	};

	public java.sql.Timestamp getDataAcces() {
		return(dataAcces);
	};
	public void setDataAcces(java.sql.Timestamp _dataAcces_) {
		this.dataAcces = _dataAcces_;
	};

	public java.lang.Long getPluginID() {
		return(pluginID);
	};
	public void setPluginID(java.lang.Long _pluginID_) {
		this.pluginID = _pluginID_;
	};

	public long getEntitatID() {
		return(entitatID);
	};
	public void setEntitatID(long _entitatID_) {
		this.entitatID = _entitatID_;
	};

	public java.lang.String getIdioma() {
		return(idioma);
	};
	public void setIdioma(java.lang.String _idioma_) {
		this.idioma = _idioma_;
	};

	public boolean isResultat() {
		return(resultat);
	};
	public void setResultat(boolean _resultat_) {
		this.resultat = _resultat_;
	};

	public java.lang.String getIdsessio() {
		return(idsessio);
	};
	public void setIdsessio(java.lang.String _idsessio_) {
		this.idsessio = _idsessio_;
	};



  // ======================================

  public static AccesBean toBean(Acces __bean) {
    if (__bean == null) { return null;}
    AccesBean __tmp = new AccesBean();
    __tmp.setAccesID(__bean.getAccesID());
    __tmp.setTipus(__bean.getTipus());
    __tmp.setNom(__bean.getNom());
    __tmp.setLlinatges(__bean.getLlinatges());
    __tmp.setNif(__bean.getNif());
    __tmp.setIp(__bean.getIp());
    __tmp.setProveidorIdentitat(__bean.getProveidorIdentitat());
    __tmp.setMetodeAutenticacio(__bean.getMetodeAutenticacio());
    __tmp.setQaa(__bean.getQaa());
    __tmp.setDataAcces(__bean.getDataAcces());
    __tmp.setPluginID(__bean.getPluginID());
    __tmp.setEntitatID(__bean.getEntitatID());
    __tmp.setIdioma(__bean.getIdioma());
    __tmp.setResultat(__bean.isResultat());
    __tmp.setIdsessio(__bean.getIdsessio());
		return __tmp;
	}



}
