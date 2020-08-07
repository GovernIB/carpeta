
package es.caib.carpeta.model.bean;

import es.caib.carpeta.model.entity.Estadistica;


public class EstadisticaBean implements Estadistica {



private static final long serialVersionUID = 360955862L;

	long estadisticaID;// PK
	long entitatID;
	java.lang.Long accesID;
	int accio;
	java.lang.String element;
	java.sql.Timestamp dataEstadistica;


  /** Constructor Buit */
  public EstadisticaBean() {
  }

  /** Constructor amb tots els camps  */
  public EstadisticaBean(long estadisticaID , long entitatID , java.lang.Long accesID , int accio , java.lang.String element , java.sql.Timestamp dataEstadistica) {
    this.estadisticaID=estadisticaID;
    this.entitatID=entitatID;
    this.accesID=accesID;
    this.accio=accio;
    this.element=element;
    this.dataEstadistica=dataEstadistica;
}
  /** Constructor sense valors autoincrementals */
  public EstadisticaBean(long entitatID , java.lang.Long accesID , int accio , java.lang.String element , java.sql.Timestamp dataEstadistica) {
    this.entitatID=entitatID;
    this.accesID=accesID;
    this.accio=accio;
    this.element=element;
    this.dataEstadistica=dataEstadistica;
}
  /** Constructor dels valors Not Null */
  public EstadisticaBean(long estadisticaID , long entitatID , int accio , java.sql.Timestamp dataEstadistica) {
    this.estadisticaID=estadisticaID;
    this.entitatID=entitatID;
    this.accio=accio;
    this.dataEstadistica=dataEstadistica;
}
  public EstadisticaBean(Estadistica __bean) {
    this.setEstadisticaID(__bean.getEstadisticaID());
    this.setEntitatID(__bean.getEntitatID());
    this.setAccesID(__bean.getAccesID());
    this.setAccio(__bean.getAccio());
    this.setElement(__bean.getElement());
    this.setDataEstadistica(__bean.getDataEstadistica());
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

	public java.lang.Long getAccesID() {
		return(accesID);
	};
	public void setAccesID(java.lang.Long _accesID_) {
		this.accesID = _accesID_;
	};

	public int getAccio() {
		return(accio);
	};
	public void setAccio(int _accio_) {
		this.accio = _accio_;
	};

	public java.lang.String getElement() {
		return(element);
	};
	public void setElement(java.lang.String _element_) {
		this.element = _element_;
	};

	public java.sql.Timestamp getDataEstadistica() {
		return(dataEstadistica);
	};
	public void setDataEstadistica(java.sql.Timestamp _dataEstadistica_) {
		this.dataEstadistica = _dataEstadistica_;
	};



  // ======================================

  public static EstadisticaBean toBean(Estadistica __bean) {
    if (__bean == null) { return null;}
    EstadisticaBean __tmp = new EstadisticaBean();
    __tmp.setEstadisticaID(__bean.getEstadisticaID());
    __tmp.setEntitatID(__bean.getEntitatID());
    __tmp.setAccesID(__bean.getAccesID());
    __tmp.setAccio(__bean.getAccio());
    __tmp.setElement(__bean.getElement());
    __tmp.setDataEstadistica(__bean.getDataEstadistica());
		return __tmp;
	}



}
