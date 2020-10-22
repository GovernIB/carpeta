
package es.caib.carpeta.model.bean;

import es.caib.carpeta.model.entity.Avis;


public class AvisBean implements Avis {



private static final long serialVersionUID = 2079106191L;

	long avisID;// PK
	long descripcioID;
	java.lang.Long entitatID;
	java.sql.Timestamp dataInici;
	java.sql.Timestamp dataFi;
	int tipus;
	int gravetat;
	java.lang.Long pluginFrontID;


  /** Constructor Buit */
  public AvisBean() {
  }

  /** Constructor amb tots els camps  */
  public AvisBean(long avisID , long descripcioID , java.lang.Long entitatID , java.sql.Timestamp dataInici , java.sql.Timestamp dataFi , int tipus , int gravetat , java.lang.Long pluginFrontID) {
    this.avisID=avisID;
    this.descripcioID=descripcioID;
    this.entitatID=entitatID;
    this.dataInici=dataInici;
    this.dataFi=dataFi;
    this.tipus=tipus;
    this.gravetat=gravetat;
    this.pluginFrontID=pluginFrontID;
}
  /** Constructor sense valors autoincrementals */
  public AvisBean(long descripcioID , java.lang.Long entitatID , java.sql.Timestamp dataInici , java.sql.Timestamp dataFi , int tipus , int gravetat , java.lang.Long pluginFrontID) {
    this.descripcioID=descripcioID;
    this.entitatID=entitatID;
    this.dataInici=dataInici;
    this.dataFi=dataFi;
    this.tipus=tipus;
    this.gravetat=gravetat;
    this.pluginFrontID=pluginFrontID;
}
  /** Constructor dels valors Not Null */
  public AvisBean(long avisID , long descripcioID , int tipus , int gravetat) {
    this.avisID=avisID;
    this.descripcioID=descripcioID;
    this.tipus=tipus;
    this.gravetat=gravetat;
}
  public AvisBean(Avis __bean) {
    this.setAvisID(__bean.getAvisID());
    this.setDescripcioID(__bean.getDescripcioID());
    this.setEntitatID(__bean.getEntitatID());
    this.setDataInici(__bean.getDataInici());
    this.setDataFi(__bean.getDataFi());
    this.setTipus(__bean.getTipus());
    this.setGravetat(__bean.getGravetat());
    this.setPluginFrontID(__bean.getPluginFrontID());
	}

	public long getAvisID() {
		return(avisID);
	};
	public void setAvisID(long _avisID_) {
		this.avisID = _avisID_;
	};

	public long getDescripcioID() {
		return(descripcioID);
	};
	public void setDescripcioID(long _descripcioID_) {
		this.descripcioID = _descripcioID_;
	};

	public java.lang.Long getEntitatID() {
		return(entitatID);
	};
	public void setEntitatID(java.lang.Long _entitatID_) {
		this.entitatID = _entitatID_;
	};

	public java.sql.Timestamp getDataInici() {
		return(dataInici);
	};
	public void setDataInici(java.sql.Timestamp _dataInici_) {
		this.dataInici = _dataInici_;
	};

	public java.sql.Timestamp getDataFi() {
		return(dataFi);
	};
	public void setDataFi(java.sql.Timestamp _dataFi_) {
		this.dataFi = _dataFi_;
	};

	public int getTipus() {
		return(tipus);
	};
	public void setTipus(int _tipus_) {
		this.tipus = _tipus_;
	};

	public int getGravetat() {
		return(gravetat);
	};
	public void setGravetat(int _gravetat_) {
		this.gravetat = _gravetat_;
	};

	public java.lang.Long getPluginFrontID() {
		return(pluginFrontID);
	};
	public void setPluginFrontID(java.lang.Long _pluginFrontID_) {
		this.pluginFrontID = _pluginFrontID_;
	};



  // ======================================

  public static AvisBean toBean(Avis __bean) {
    if (__bean == null) { return null;}
    AvisBean __tmp = new AvisBean();
    __tmp.setAvisID(__bean.getAvisID());
    __tmp.setDescripcioID(__bean.getDescripcioID());
    __tmp.setEntitatID(__bean.getEntitatID());
    __tmp.setDataInici(__bean.getDataInici());
    __tmp.setDataFi(__bean.getDataFi());
    __tmp.setTipus(__bean.getTipus());
    __tmp.setGravetat(__bean.getGravetat());
    __tmp.setPluginFrontID(__bean.getPluginFrontID());
		return __tmp;
	}



}
