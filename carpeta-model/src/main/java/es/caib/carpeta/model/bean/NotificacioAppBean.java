
package es.caib.carpeta.model.bean;

import es.caib.carpeta.model.entity.NotificacioApp;


public class NotificacioAppBean implements NotificacioApp {



	long notificacioAppID;// PK
	java.lang.String codi;
	long titolID;
	long missatgeID;
	java.lang.Long frontPluginID;
	java.lang.String ajuda;
	boolean activa;
	long entitatID;


  /** Constructor Buit */
  public NotificacioAppBean() {
  }

  /** Constructor amb tots els camps  */
  public NotificacioAppBean(long notificacioAppID , java.lang.String codi , long titolID , long missatgeID , java.lang.Long frontPluginID , java.lang.String ajuda , boolean activa , long entitatID) {
    this.notificacioAppID=notificacioAppID;
    this.codi=codi;
    this.titolID=titolID;
    this.missatgeID=missatgeID;
    this.frontPluginID=frontPluginID;
    this.ajuda=ajuda;
    this.activa=activa;
    this.entitatID=entitatID;
}
  /** Constructor sense valors autoincrementals */
  public NotificacioAppBean(java.lang.String codi , long titolID , long missatgeID , java.lang.Long frontPluginID , java.lang.String ajuda , boolean activa , long entitatID) {
    this.codi=codi;
    this.titolID=titolID;
    this.missatgeID=missatgeID;
    this.frontPluginID=frontPluginID;
    this.ajuda=ajuda;
    this.activa=activa;
    this.entitatID=entitatID;
}
  /** Constructor dels valors Not Null */
  public NotificacioAppBean(long notificacioAppID , java.lang.String codi , long titolID , long missatgeID , boolean activa , long entitatID) {
    this.notificacioAppID=notificacioAppID;
    this.codi=codi;
    this.titolID=titolID;
    this.missatgeID=missatgeID;
    this.activa=activa;
    this.entitatID=entitatID;
}
  public NotificacioAppBean(NotificacioApp __bean) {
    this.setNotificacioAppID(__bean.getNotificacioAppID());
    this.setCodi(__bean.getCodi());
    this.setTitolID(__bean.getTitolID());
    this.setMissatgeID(__bean.getMissatgeID());
    this.setFrontPluginID(__bean.getFrontPluginID());
    this.setAjuda(__bean.getAjuda());
    this.setActiva(__bean.isActiva());
    this.setEntitatID(__bean.getEntitatID());
	}

	public long getNotificacioAppID() {
		return(notificacioAppID);
	};
	public void setNotificacioAppID(long _notificacioAppID_) {
		this.notificacioAppID = _notificacioAppID_;
	};

	public java.lang.String getCodi() {
		return(codi);
	};
	public void setCodi(java.lang.String _codi_) {
		this.codi = _codi_;
	};

	public long getTitolID() {
		return(titolID);
	};
	public void setTitolID(long _titolID_) {
		this.titolID = _titolID_;
	};

	public long getMissatgeID() {
		return(missatgeID);
	};
	public void setMissatgeID(long _missatgeID_) {
		this.missatgeID = _missatgeID_;
	};

	public java.lang.Long getFrontPluginID() {
		return(frontPluginID);
	};
	public void setFrontPluginID(java.lang.Long _frontPluginID_) {
		this.frontPluginID = _frontPluginID_;
	};

	public java.lang.String getAjuda() {
		return(ajuda);
	};
	public void setAjuda(java.lang.String _ajuda_) {
		this.ajuda = _ajuda_;
	};

	public boolean isActiva() {
		return(activa);
	};
	public void setActiva(boolean _activa_) {
		this.activa = _activa_;
	};

	public long getEntitatID() {
		return(entitatID);
	};
	public void setEntitatID(long _entitatID_) {
		this.entitatID = _entitatID_;
	};



  // ======================================

  public static NotificacioAppBean toBean(NotificacioApp __bean) {
    if (__bean == null) { return null;}
    NotificacioAppBean __tmp = new NotificacioAppBean();
    __tmp.setNotificacioAppID(__bean.getNotificacioAppID());
    __tmp.setCodi(__bean.getCodi());
    __tmp.setTitolID(__bean.getTitolID());
    __tmp.setMissatgeID(__bean.getMissatgeID());
    __tmp.setFrontPluginID(__bean.getFrontPluginID());
    __tmp.setAjuda(__bean.getAjuda());
    __tmp.setActiva(__bean.isActiva());
    __tmp.setEntitatID(__bean.getEntitatID());
		return __tmp;
	}



}
