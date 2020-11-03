
package es.caib.carpeta.model.bean;

import es.caib.carpeta.model.entity.Estadistica;


public class EstadisticaBean implements Estadistica {



private static final long serialVersionUID = 360955862L;

	long estadisticaID;// PK
	long entitatID;
	java.sql.Timestamp dataEstadistica;
	int tipus;
	int comptador;
	java.lang.Long pluginID;


  /** Constructor Buit */
  public EstadisticaBean() {
  }

  /** Constructor amb tots els camps  */
  public EstadisticaBean(long estadisticaID , long entitatID , java.sql.Timestamp dataEstadistica , int tipus , int comptador , java.lang.Long pluginID) {
    this.estadisticaID=estadisticaID;
    this.entitatID=entitatID;
    this.dataEstadistica=dataEstadistica;
    this.tipus=tipus;
    this.comptador=comptador;
    this.pluginID=pluginID;
}
  /** Constructor sense valors autoincrementals */
  public EstadisticaBean(long entitatID , java.sql.Timestamp dataEstadistica , int tipus , int comptador , java.lang.Long pluginID) {
    this.entitatID=entitatID;
    this.dataEstadistica=dataEstadistica;
    this.tipus=tipus;
    this.comptador=comptador;
    this.pluginID=pluginID;
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

	public long getEntitatID() {
		return(entitatID);
	};
	public void setEntitatID(long _entitatID_) {
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

	public java.lang.Long getPluginID() {
		return(pluginID);
	};
	public void setPluginID(java.lang.Long _pluginID_) {
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
