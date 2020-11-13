
package es.caib.carpeta.model.bean;

import es.caib.carpeta.model.entity.Estadistica;


public class EstadisticaBean implements Estadistica {



private static final long serialVersionUID = 360955862L;

	long estadisticaID;// PK
	int tipus;
	java.sql.Timestamp dataEstadistica;
	int comptador;
	java.lang.Long pluginID;
	java.lang.Long entitatID;


  /** Constructor Buit */
  public EstadisticaBean() {
  }

  /** Constructor amb tots els camps  */
  public EstadisticaBean(long estadisticaID , int tipus , java.sql.Timestamp dataEstadistica , int comptador , java.lang.Long pluginID , java.lang.Long entitatID) {
    this.estadisticaID=estadisticaID;
    this.tipus=tipus;
    this.dataEstadistica=dataEstadistica;
    this.comptador=comptador;
    this.pluginID=pluginID;
    this.entitatID=entitatID;
}
  /** Constructor sense valors autoincrementals */
  public EstadisticaBean(int tipus , java.sql.Timestamp dataEstadistica , int comptador , java.lang.Long pluginID , java.lang.Long entitatID) {
    this.tipus=tipus;
    this.dataEstadistica=dataEstadistica;
    this.comptador=comptador;
    this.pluginID=pluginID;
    this.entitatID=entitatID;
}
  /** Constructor dels valors Not Null */
  public EstadisticaBean(long estadisticaID , int tipus , java.sql.Timestamp dataEstadistica , int comptador) {
    this.estadisticaID=estadisticaID;
    this.tipus=tipus;
    this.dataEstadistica=dataEstadistica;
    this.comptador=comptador;
}
  public EstadisticaBean(Estadistica __bean) {
    this.setEstadisticaID(__bean.getEstadisticaID());
    this.setTipus(__bean.getTipus());
    this.setDataEstadistica(__bean.getDataEstadistica());
    this.setComptador(__bean.getComptador());
    this.setPluginID(__bean.getPluginID());
    this.setEntitatID(__bean.getEntitatID());
	}

	public long getEstadisticaID() {
		return(estadisticaID);
	};
	public void setEstadisticaID(long _estadisticaID_) {
		this.estadisticaID = _estadisticaID_;
	};

	public int getTipus() {
		return(tipus);
	};
	public void setTipus(int _tipus_) {
		this.tipus = _tipus_;
	};

	public java.sql.Timestamp getDataEstadistica() {
		return(dataEstadistica);
	};
	public void setDataEstadistica(java.sql.Timestamp _dataEstadistica_) {
		this.dataEstadistica = _dataEstadistica_;
	};

	public int getComptador() {
		return(comptador);
	};
	public void setComptador(int _comptador_) {
		this.comptador = _comptador_;
	};

	public java.lang.Long getPluginID() {
		return(pluginID);
	};
	public void setPluginID(java.lang.Long _pluginID_) {
		this.pluginID = _pluginID_;
	};

	public java.lang.Long getEntitatID() {
		return(entitatID);
	};
	public void setEntitatID(java.lang.Long _entitatID_) {
		this.entitatID = _entitatID_;
	};



  // ======================================

  public static EstadisticaBean toBean(Estadistica __bean) {
    if (__bean == null) { return null;}
    EstadisticaBean __tmp = new EstadisticaBean();
    __tmp.setEstadisticaID(__bean.getEstadisticaID());
    __tmp.setTipus(__bean.getTipus());
    __tmp.setDataEstadistica(__bean.getDataEstadistica());
    __tmp.setComptador(__bean.getComptador());
    __tmp.setPluginID(__bean.getPluginID());
    __tmp.setEntitatID(__bean.getEntitatID());
		return __tmp;
	}



}
