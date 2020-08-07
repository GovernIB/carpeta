
package es.caib.carpeta.model.bean;

import es.caib.carpeta.model.entity.PropietatGlobal;


public class PropietatGlobalBean implements PropietatGlobal {



private static final long serialVersionUID = 672541563L;

	long propietaGlobalID;// PK
	java.lang.String codi;
	java.lang.String value;
	java.lang.String descripcio;
	java.lang.Long entitatID;


  /** Constructor Buit */
  public PropietatGlobalBean() {
  }

  /** Constructor amb tots els camps  */
  public PropietatGlobalBean(long propietaGlobalID , java.lang.String codi , java.lang.String value , java.lang.String descripcio , java.lang.Long entitatID) {
    this.propietaGlobalID=propietaGlobalID;
    this.codi=codi;
    this.value=value;
    this.descripcio=descripcio;
    this.entitatID=entitatID;
}
  /** Constructor sense valors autoincrementals */
  public PropietatGlobalBean(java.lang.String codi , java.lang.String value , java.lang.String descripcio , java.lang.Long entitatID) {
    this.codi=codi;
    this.value=value;
    this.descripcio=descripcio;
    this.entitatID=entitatID;
}
  /** Constructor dels valors Not Null */
  public PropietatGlobalBean(long propietaGlobalID , java.lang.String codi) {
    this.propietaGlobalID=propietaGlobalID;
    this.codi=codi;
}
  public PropietatGlobalBean(PropietatGlobal __bean) {
    this.setPropietaGlobalID(__bean.getPropietaGlobalID());
    this.setCodi(__bean.getCodi());
    this.setValue(__bean.getValue());
    this.setDescripcio(__bean.getDescripcio());
    this.setEntitatID(__bean.getEntitatID());
	}

	public long getPropietaGlobalID() {
		return(propietaGlobalID);
	};
	public void setPropietaGlobalID(long _propietaGlobalID_) {
		this.propietaGlobalID = _propietaGlobalID_;
	};

	public java.lang.String getCodi() {
		return(codi);
	};
	public void setCodi(java.lang.String _codi_) {
		this.codi = _codi_;
	};

	public java.lang.String getValue() {
		return(value);
	};
	public void setValue(java.lang.String _value_) {
		this.value = _value_;
	};

	public java.lang.String getDescripcio() {
		return(descripcio);
	};
	public void setDescripcio(java.lang.String _descripcio_) {
		this.descripcio = _descripcio_;
	};

	public java.lang.Long getEntitatID() {
		return(entitatID);
	};
	public void setEntitatID(java.lang.Long _entitatID_) {
		this.entitatID = _entitatID_;
	};



  // ======================================

  public static PropietatGlobalBean toBean(PropietatGlobal __bean) {
    if (__bean == null) { return null;}
    PropietatGlobalBean __tmp = new PropietatGlobalBean();
    __tmp.setPropietaGlobalID(__bean.getPropietaGlobalID());
    __tmp.setCodi(__bean.getCodi());
    __tmp.setValue(__bean.getValue());
    __tmp.setDescripcio(__bean.getDescripcio());
    __tmp.setEntitatID(__bean.getEntitatID());
		return __tmp;
	}



}
