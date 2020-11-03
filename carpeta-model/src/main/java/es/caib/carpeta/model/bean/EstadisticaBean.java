
package es.caib.carpeta.model.bean;

import es.caib.carpeta.model.entity.Estadistica;


public class EstadisticaBean implements Estadistica {



private static final long serialVersionUID = 360955862L;

	long estadisticaID;// PK
	java.lang.Long entitatID;
	java.sql.Timestamp dataEstadistica;
	int tipus;
	int comptador;
	java.lang.Integer pluginID;


  /** Constructor Buit */
  public EstadisticaBean() {
  }

  /** Constructor amb tots els camps  */
  public EstadisticaBean(long estadisticaID , java.lang.Long entitatID , java.sql.Timestamp dataEstadistica , int tipus , int comptador , java.lang.Integer pluginID) {
    this.estadisticaID=estadisticaID;
    this.entitatID=entitatID;
    this.dataEstadistica=dataEstadistica;
    this.tipus=tipus;
    this.comptador=comptador;
    this.pluginID=pluginID;
}
  /** Constructor sense valors autoincrementals */
  public EstadisticaBean(java.lang.Long entitatID , java.sql.Timestamp dataEstadistica , int tipus , int comptador , java.lang.Integer pluginID) {
    this.entitatID=entitatID;
    this.dataEstadistica=dataEstadistica;
    this.tipus=tipus;
    this.comptador=comptador;
    this.pluginID=pluginID;
}
  /** Constructor dels valors Not Null */
  public EstadisticaBean(long estadisticaID , java.sql.Timestamp dataEstadistica , int tipus , int comptador) {
    this.estadisticaID=estadisticaID;
    this.dataEstadistica=dataEstadistica;
    this.tipus=tipus;
    this.comptador=comptador;
}
  public EstadisticaBean(Estadistica __bean) {
    this.setEstadisticaID(__bean.getEstadisticaID());
    this.setEntitatID(__bean.getEntitatID());
    this.setDataEstadistica(__bean.getDataEstadistica());
    this.setTipus(__bean.getTipus());
    this.setComptador(__bean.getComptador());
    this.setPluginID(__bean.getPluginID());
	}

	public long getEstadisticaID() {
		return(estadisticaID);
	};
	public void setEstadisticaID(long _estadisticaID_) {
		this.estadisticaID = _estadisticaID_;
	};

	public java.lang.Long getEntitatID() {
		return(entitatID);
	};
	public void setEntitatID(java.lang.Long _entitatID_) {
		this.entitatID = _entitatID_;
	};

	public java.sql.Timestamp getDataEstadistica() {
		return(dataEstadistica);
	};
	public void setDataEstadistica(java.sql.Timestamp _dataEstadistica_) {
		this.dataEstadistica = _dataEstadistica_;
	};

	public int getTipus() {
		return(tipus);
	};
	public void setTipus(int _tipus_) {
		this.tipus = _tipus_;
	};

	public int getComptador() {
		return(comptador);
	};
	public void setComptador(int _comptador_) {
		this.comptador = _comptador_;
	};

	public java.lang.Integer getPluginID() {
		return(pluginID);
	};
	public void setPluginID(java.lang.Integer _pluginID_) {
		this.pluginID = _pluginID_;
	};



  // ======================================

  public static EstadisticaBean toBean(Estadistica __bean) {
    if (__bean == null) { return null;}
    EstadisticaBean __tmp = new EstadisticaBean();
    __tmp.setEstadisticaID(__bean.getEstadisticaID());
    __tmp.setEntitatID(__bean.getEntitatID());
    __tmp.setDataEstadistica(__bean.getDataEstadistica());
    __tmp.setTipus(__bean.getTipus());
    __tmp.setComptador(__bean.getComptador());
    __tmp.setPluginID(__bean.getPluginID());
		return __tmp;
	}



}
