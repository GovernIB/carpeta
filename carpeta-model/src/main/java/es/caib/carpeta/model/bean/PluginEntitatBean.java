
package es.caib.carpeta.model.bean;

import es.caib.carpeta.model.entity.PluginEntitat;


public class PluginEntitatBean implements PluginEntitat {



private static final long serialVersionUID = -98501626L;

	long pluginEntitatID;// PK
	long pluginID;
	long entitatID;
	boolean actiu;
	java.lang.Long seccioID;
	int ordre;


  /** Constructor Buit */
  public PluginEntitatBean() {
  }

  /** Constructor amb tots els camps  */
  public PluginEntitatBean(long pluginEntitatID , long pluginID , long entitatID , boolean actiu , java.lang.Long seccioID , int ordre) {
    this.pluginEntitatID=pluginEntitatID;
    this.pluginID=pluginID;
    this.entitatID=entitatID;
    this.actiu=actiu;
    this.seccioID=seccioID;
    this.ordre=ordre;
}
  /** Constructor sense valors autoincrementals */
  public PluginEntitatBean(long pluginID , long entitatID , boolean actiu , java.lang.Long seccioID , int ordre) {
    this.pluginID=pluginID;
    this.entitatID=entitatID;
    this.actiu=actiu;
    this.seccioID=seccioID;
    this.ordre=ordre;
}
  public PluginEntitatBean(PluginEntitat __bean) {
    this.setPluginEntitatID(__bean.getPluginEntitatID());
    this.setPluginID(__bean.getPluginID());
    this.setEntitatID(__bean.getEntitatID());
    this.setActiu(__bean.isActiu());
    this.setSeccioID(__bean.getSeccioID());
    this.setOrdre(__bean.getOrdre());
	}

	public long getPluginEntitatID() {
		return(pluginEntitatID);
	};
	public void setPluginEntitatID(long _pluginEntitatID_) {
		this.pluginEntitatID = _pluginEntitatID_;
	};

	public long getPluginID() {
		return(pluginID);
	};
	public void setPluginID(long _pluginID_) {
		this.pluginID = _pluginID_;
	};

	public long getEntitatID() {
		return(entitatID);
	};
	public void setEntitatID(long _entitatID_) {
		this.entitatID = _entitatID_;
	};

	public boolean isActiu() {
		return(actiu);
	};
	public void setActiu(boolean _actiu_) {
		this.actiu = _actiu_;
	};

	public java.lang.Long getSeccioID() {
		return(seccioID);
	};
	public void setSeccioID(java.lang.Long _seccioID_) {
		this.seccioID = _seccioID_;
	};

	public int getOrdre() {
		return(ordre);
	};
	public void setOrdre(int _ordre_) {
		this.ordre = _ordre_;
	};



  // ======================================

  public static PluginEntitatBean toBean(PluginEntitat __bean) {
    if (__bean == null) { return null;}
    PluginEntitatBean __tmp = new PluginEntitatBean();
    __tmp.setPluginEntitatID(__bean.getPluginEntitatID());
    __tmp.setPluginID(__bean.getPluginID());
    __tmp.setEntitatID(__bean.getEntitatID());
    __tmp.setActiu(__bean.isActiu());
    __tmp.setSeccioID(__bean.getSeccioID());
    __tmp.setOrdre(__bean.getOrdre());
		return __tmp;
	}



}
