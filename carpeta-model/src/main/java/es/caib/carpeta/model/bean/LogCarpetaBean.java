
package es.caib.carpeta.model.bean;

import es.caib.carpeta.model.entity.LogCarpeta;


public class LogCarpetaBean implements LogCarpeta {



private static final long serialVersionUID = 1007346690L;

	long logID;// PK
	java.lang.String descripcio;
	int tipus;
	int estat;
	java.lang.Long pluginID;
	java.lang.String entitatCodi;
	java.lang.Long temps;
	java.sql.Timestamp dataInici;
	java.lang.String peticio;
	java.lang.String error;
	java.lang.String excepcio;
	java.lang.String idSessio;


  /** Constructor Buit */
  public LogCarpetaBean() {
  }

  /** Constructor amb tots els camps  */
  public LogCarpetaBean(long logID , java.lang.String descripcio , int tipus , int estat , java.lang.Long pluginID , java.lang.String entitatCodi , java.lang.Long temps , java.sql.Timestamp dataInici , java.lang.String peticio , java.lang.String error , java.lang.String excepcio , java.lang.String idSessio) {
    this.logID=logID;
    this.descripcio=descripcio;
    this.tipus=tipus;
    this.estat=estat;
    this.pluginID=pluginID;
    this.entitatCodi=entitatCodi;
    this.temps=temps;
    this.dataInici=dataInici;
    this.peticio=peticio;
    this.error=error;
    this.excepcio=excepcio;
    this.idSessio=idSessio;
}
  /** Constructor sense valors autoincrementals */
  public LogCarpetaBean(java.lang.String descripcio , int tipus , int estat , java.lang.Long pluginID , java.lang.String entitatCodi , java.lang.Long temps , java.sql.Timestamp dataInici , java.lang.String peticio , java.lang.String error , java.lang.String excepcio , java.lang.String idSessio) {
    this.descripcio=descripcio;
    this.tipus=tipus;
    this.estat=estat;
    this.pluginID=pluginID;
    this.entitatCodi=entitatCodi;
    this.temps=temps;
    this.dataInici=dataInici;
    this.peticio=peticio;
    this.error=error;
    this.excepcio=excepcio;
    this.idSessio=idSessio;
}
  /** Constructor dels valors Not Null */
  public LogCarpetaBean(long logID , java.lang.String descripcio , int tipus , int estat , java.sql.Timestamp dataInici) {
    this.logID=logID;
    this.descripcio=descripcio;
    this.tipus=tipus;
    this.estat=estat;
    this.dataInici=dataInici;
}
  public LogCarpetaBean(LogCarpeta __bean) {
    this.setLogID(__bean.getLogID());
    this.setDescripcio(__bean.getDescripcio());
    this.setTipus(__bean.getTipus());
    this.setEstat(__bean.getEstat());
    this.setPluginID(__bean.getPluginID());
    this.setEntitatCodi(__bean.getEntitatCodi());
    this.setTemps(__bean.getTemps());
    this.setDataInici(__bean.getDataInici());
    this.setPeticio(__bean.getPeticio());
    this.setError(__bean.getError());
    this.setExcepcio(__bean.getExcepcio());
    this.setIdSessio(__bean.getIdSessio());
	}

	public long getLogID() {
		return(logID);
	};
	public void setLogID(long _logID_) {
		this.logID = _logID_;
	};

	public java.lang.String getDescripcio() {
		return(descripcio);
	};
	public void setDescripcio(java.lang.String _descripcio_) {
		this.descripcio = _descripcio_;
	};

	public int getTipus() {
		return(tipus);
	};
	public void setTipus(int _tipus_) {
		this.tipus = _tipus_;
	};

	public int getEstat() {
		return(estat);
	};
	public void setEstat(int _estat_) {
		this.estat = _estat_;
	};

	public java.lang.Long getPluginID() {
		return(pluginID);
	};
	public void setPluginID(java.lang.Long _pluginID_) {
		this.pluginID = _pluginID_;
	};

	public java.lang.String getEntitatCodi() {
		return(entitatCodi);
	};
	public void setEntitatCodi(java.lang.String _entitatCodi_) {
		this.entitatCodi = _entitatCodi_;
	};

	public java.lang.Long getTemps() {
		return(temps);
	};
	public void setTemps(java.lang.Long _temps_) {
		this.temps = _temps_;
	};

	public java.sql.Timestamp getDataInici() {
		return(dataInici);
	};
	public void setDataInici(java.sql.Timestamp _dataInici_) {
		this.dataInici = _dataInici_;
	};

	public java.lang.String getPeticio() {
		return(peticio);
	};
	public void setPeticio(java.lang.String _peticio_) {
		this.peticio = _peticio_;
	};

	public java.lang.String getError() {
		return(error);
	};
	public void setError(java.lang.String _error_) {
		this.error = _error_;
	};

	public java.lang.String getExcepcio() {
		return(excepcio);
	};
	public void setExcepcio(java.lang.String _excepcio_) {
		this.excepcio = _excepcio_;
	};

	public java.lang.String getIdSessio() {
		return(idSessio);
	};
	public void setIdSessio(java.lang.String _idSessio_) {
		this.idSessio = _idSessio_;
	};



  // ======================================

  public static LogCarpetaBean toBean(LogCarpeta __bean) {
    if (__bean == null) { return null;}
    LogCarpetaBean __tmp = new LogCarpetaBean();
    __tmp.setLogID(__bean.getLogID());
    __tmp.setDescripcio(__bean.getDescripcio());
    __tmp.setTipus(__bean.getTipus());
    __tmp.setEstat(__bean.getEstat());
    __tmp.setPluginID(__bean.getPluginID());
    __tmp.setEntitatCodi(__bean.getEntitatCodi());
    __tmp.setTemps(__bean.getTemps());
    __tmp.setDataInici(__bean.getDataInici());
    __tmp.setPeticio(__bean.getPeticio());
    __tmp.setError(__bean.getError());
    __tmp.setExcepcio(__bean.getExcepcio());
    __tmp.setIdSessio(__bean.getIdSessio());
		return __tmp;
	}



}
