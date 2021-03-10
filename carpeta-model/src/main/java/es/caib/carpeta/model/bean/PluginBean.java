
package es.caib.carpeta.model.bean;

import es.caib.carpeta.model.entity.Plugin;


public class PluginBean implements Plugin {



private static final long serialVersionUID = 1605894563L;

	long pluginID;// PK
	long nomID;
	java.lang.Long descripcioID;
	java.lang.String context;
	java.lang.Long logoID;
	java.lang.String classe;
	java.lang.String propietats;
	boolean actiu;
	int tipus;


  /** Constructor Buit */
  public PluginBean() {
  }

  /** Constructor amb tots els camps  */
  public PluginBean(long pluginID , long nomID , java.lang.Long descripcioID , java.lang.String context , java.lang.Long logoID , java.lang.String classe , java.lang.String propietats , boolean actiu , int tipus) {
    this.pluginID=pluginID;
    this.nomID=nomID;
    this.descripcioID=descripcioID;
    this.context=context;
    this.logoID=logoID;
    this.classe=classe;
    this.propietats=propietats;
    this.actiu=actiu;
    this.tipus=tipus;
}
  /** Constructor sense valors autoincrementals */
  public PluginBean(long nomID , java.lang.Long descripcioID , java.lang.String context , java.lang.Long logoID , java.lang.String classe , java.lang.String propietats , boolean actiu , int tipus) {
    this.nomID=nomID;
    this.descripcioID=descripcioID;
    this.context=context;
    this.logoID=logoID;
    this.classe=classe;
    this.propietats=propietats;
    this.actiu=actiu;
    this.tipus=tipus;
}
  /** Constructor dels valors Not Null */
  public PluginBean(long pluginID , long nomID , java.lang.Long descripcioID , java.lang.String classe , boolean actiu , int tipus) {
    this.pluginID=pluginID;
    this.nomID=nomID;
    this.descripcioID=descripcioID;
    this.classe=classe;
    this.actiu=actiu;
    this.tipus=tipus;
}
  public PluginBean(Plugin __bean) {
    this.setPluginID(__bean.getPluginID());
    this.setNomID(__bean.getNomID());
    this.setDescripcioID(__bean.getDescripcioID());
    this.setContext(__bean.getContext());
    this.setLogoID(__bean.getLogoID());
    this.setClasse(__bean.getClasse());
    this.setPropietats(__bean.getPropietats());
    this.setActiu(__bean.isActiu());
    this.setTipus(__bean.getTipus());
    // Fitxer
    this.setLogo(FitxerBean.toBean(__bean.getLogo()));
	}

	public long getPluginID() {
		return(pluginID);
	};
	public void setPluginID(long _pluginID_) {
		this.pluginID = _pluginID_;
	};

	public long getNomID() {
		return(nomID);
	};
	public void setNomID(long _nomID_) {
		this.nomID = _nomID_;
	};

	public java.lang.Long getDescripcioID() {
		return(descripcioID);
	};
	public void setDescripcioID(java.lang.Long _descripcioID_) {
		this.descripcioID = _descripcioID_;
	};

	public java.lang.String getContext() {
		return(context);
	};
	public void setContext(java.lang.String _context_) {
		this.context = _context_;
	};

	public java.lang.Long getLogoID() {
		return(logoID);
	};
	public void setLogoID(java.lang.Long _logoID_) {
		this.logoID = _logoID_;
	};

	public java.lang.String getClasse() {
		return(classe);
	};
	public void setClasse(java.lang.String _classe_) {
		this.classe = _classe_;
	};

	public java.lang.String getPropietats() {
		return(propietats);
	};
	public void setPropietats(java.lang.String _propietats_) {
		this.propietats = _propietats_;
	};

	public boolean isActiu() {
		return(actiu);
	};
	public void setActiu(boolean _actiu_) {
		this.actiu = _actiu_;
	};

	public int getTipus() {
		return(tipus);
	};
	public void setTipus(int _tipus_) {
		this.tipus = _tipus_;
	};



  // ======================================

  public static PluginBean toBean(Plugin __bean) {
    if (__bean == null) { return null;}
    PluginBean __tmp = new PluginBean();
    __tmp.setPluginID(__bean.getPluginID());
    __tmp.setNomID(__bean.getNomID());
    __tmp.setDescripcioID(__bean.getDescripcioID());
    __tmp.setContext(__bean.getContext());
    __tmp.setLogoID(__bean.getLogoID());
    __tmp.setClasse(__bean.getClasse());
    __tmp.setPropietats(__bean.getPropietats());
    __tmp.setActiu(__bean.isActiu());
    __tmp.setTipus(__bean.getTipus());
    // Fitxer
    __tmp.setLogo(FitxerBean.toBean(__bean.getLogo()));
		return __tmp;
	}

  protected FitxerBean logo;
  public FitxerBean getLogo() {
    return logo;
  }
  public void setLogo(FitxerBean __field) {
    this. logo = __field;
  }


}
