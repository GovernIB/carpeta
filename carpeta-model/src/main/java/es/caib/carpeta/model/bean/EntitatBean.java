
package es.caib.carpeta.model.bean;

import es.caib.carpeta.model.entity.Entitat;


public class EntitatBean implements Entitat {



private static final long serialVersionUID = -2014602951L;

	long entitatID;// PK
	long nomID;
	java.lang.Long descripcioID;
	java.lang.String codi;
	java.lang.String codiDir3;
	boolean activa;
	java.lang.String colorMenu;
	long logoCapBackID;
	long logoPeuBackID;
	long logoLateralFrontID;
	java.lang.String versio;
	long iconID;
	java.lang.String webEntitat;
	java.lang.String entitatDescFront;
	java.lang.String suportWeb;
	java.lang.String suportTelefon;
	java.lang.String suportEmail;
	java.lang.String suportFAQ;
	java.lang.String suportqssi;
	java.lang.String suportautenticacio;
	java.lang.Long pluginLoginID;
	java.lang.Long loginTextID;
	java.lang.Long fitxerCssID;
	java.lang.String context;
	java.lang.String commit;


  /** Constructor Buit */
  public EntitatBean() {
  }

  /** Constructor amb tots els camps  */
  public EntitatBean(long entitatID , long nomID , java.lang.Long descripcioID , java.lang.String codi , java.lang.String codiDir3 , boolean activa , java.lang.String colorMenu , long logoCapBackID , long logoPeuBackID , long logoLateralFrontID , java.lang.String versio , long iconID , java.lang.String webEntitat , java.lang.String entitatDescFront , java.lang.String suportWeb , java.lang.String suportTelefon , java.lang.String suportEmail , java.lang.String suportFAQ , java.lang.String suportqssi , java.lang.String suportautenticacio , java.lang.Long pluginLoginID , java.lang.Long loginTextID , java.lang.Long fitxerCssID , java.lang.String context , java.lang.String commit) {
    this.entitatID=entitatID;
    this.nomID=nomID;
    this.descripcioID=descripcioID;
    this.codi=codi;
    this.codiDir3=codiDir3;
    this.activa=activa;
    this.colorMenu=colorMenu;
    this.logoCapBackID=logoCapBackID;
    this.logoPeuBackID=logoPeuBackID;
    this.logoLateralFrontID=logoLateralFrontID;
    this.versio=versio;
    this.iconID=iconID;
    this.webEntitat=webEntitat;
    this.entitatDescFront=entitatDescFront;
    this.suportWeb=suportWeb;
    this.suportTelefon=suportTelefon;
    this.suportEmail=suportEmail;
    this.suportFAQ=suportFAQ;
    this.suportqssi=suportqssi;
    this.suportautenticacio=suportautenticacio;
    this.pluginLoginID=pluginLoginID;
    this.loginTextID=loginTextID;
    this.fitxerCssID=fitxerCssID;
    this.context=context;
    this.commit=commit;
}
  /** Constructor sense valors autoincrementals */
  public EntitatBean(long nomID , java.lang.Long descripcioID , java.lang.String codi , java.lang.String codiDir3 , boolean activa , java.lang.String colorMenu , long logoCapBackID , long logoPeuBackID , long logoLateralFrontID , java.lang.String versio , long iconID , java.lang.String webEntitat , java.lang.String entitatDescFront , java.lang.String suportWeb , java.lang.String suportTelefon , java.lang.String suportEmail , java.lang.String suportFAQ , java.lang.String suportqssi , java.lang.String suportautenticacio , java.lang.Long pluginLoginID , java.lang.Long loginTextID , java.lang.Long fitxerCssID , java.lang.String context , java.lang.String commit) {
    this.nomID=nomID;
    this.descripcioID=descripcioID;
    this.codi=codi;
    this.codiDir3=codiDir3;
    this.activa=activa;
    this.colorMenu=colorMenu;
    this.logoCapBackID=logoCapBackID;
    this.logoPeuBackID=logoPeuBackID;
    this.logoLateralFrontID=logoLateralFrontID;
    this.versio=versio;
    this.iconID=iconID;
    this.webEntitat=webEntitat;
    this.entitatDescFront=entitatDescFront;
    this.suportWeb=suportWeb;
    this.suportTelefon=suportTelefon;
    this.suportEmail=suportEmail;
    this.suportFAQ=suportFAQ;
    this.suportqssi=suportqssi;
    this.suportautenticacio=suportautenticacio;
    this.pluginLoginID=pluginLoginID;
    this.loginTextID=loginTextID;
    this.fitxerCssID=fitxerCssID;
    this.context=context;
    this.commit=commit;
}
  /** Constructor dels valors Not Null */
  public EntitatBean(long entitatID , long nomID , java.lang.String codi , java.lang.String codiDir3 , boolean activa , java.lang.String colorMenu , long logoCapBackID , long logoPeuBackID , long logoLateralFrontID , java.lang.String versio , long iconID , java.lang.String webEntitat , java.lang.String entitatDescFront) {
    this.entitatID=entitatID;
    this.nomID=nomID;
    this.codi=codi;
    this.codiDir3=codiDir3;
    this.activa=activa;
    this.colorMenu=colorMenu;
    this.logoCapBackID=logoCapBackID;
    this.logoPeuBackID=logoPeuBackID;
    this.logoLateralFrontID=logoLateralFrontID;
    this.versio=versio;
    this.iconID=iconID;
    this.webEntitat=webEntitat;
    this.entitatDescFront=entitatDescFront;
}
  public EntitatBean(Entitat __bean) {
    this.setEntitatID(__bean.getEntitatID());
    this.setNomID(__bean.getNomID());
    this.setDescripcioID(__bean.getDescripcioID());
    this.setCodi(__bean.getCodi());
    this.setCodiDir3(__bean.getCodiDir3());
    this.setActiva(__bean.isActiva());
    this.setColorMenu(__bean.getColorMenu());
    this.setLogoCapBackID(__bean.getLogoCapBackID());
    this.setLogoPeuBackID(__bean.getLogoPeuBackID());
    this.setLogoLateralFrontID(__bean.getLogoLateralFrontID());
    this.setVersio(__bean.getVersio());
    this.setIconID(__bean.getIconID());
    this.setWebEntitat(__bean.getWebEntitat());
    this.setEntitatDescFront(__bean.getEntitatDescFront());
    this.setSuportWeb(__bean.getSuportWeb());
    this.setSuportTelefon(__bean.getSuportTelefon());
    this.setSuportEmail(__bean.getSuportEmail());
    this.setSuportFAQ(__bean.getSuportFAQ());
    this.setSuportqssi(__bean.getSuportqssi());
    this.setSuportautenticacio(__bean.getSuportautenticacio());
    this.setPluginLoginID(__bean.getPluginLoginID());
    this.setLoginTextID(__bean.getLoginTextID());
    this.setFitxerCssID(__bean.getFitxerCssID());
    this.setContext(__bean.getContext());
    this.setCommit(__bean.getCommit());
    // Fitxer
    this.setLogoCapBack(FitxerBean.toBean(__bean.getLogoCapBack()));
    // Fitxer
    this.setLogoPeuBack(FitxerBean.toBean(__bean.getLogoPeuBack()));
    // Fitxer
    this.setLogoLateralFront(FitxerBean.toBean(__bean.getLogoLateralFront()));
    // Fitxer
    this.setIcon(FitxerBean.toBean(__bean.getIcon()));
    // Fitxer
    this.setFitxerCss(FitxerBean.toBean(__bean.getFitxerCss()));
	}

	public long getEntitatID() {
		return(entitatID);
	};
	public void setEntitatID(long _entitatID_) {
		this.entitatID = _entitatID_;
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

	public java.lang.String getCodi() {
		return(codi);
	};
	public void setCodi(java.lang.String _codi_) {
		this.codi = _codi_;
	};

	public java.lang.String getCodiDir3() {
		return(codiDir3);
	};
	public void setCodiDir3(java.lang.String _codiDir3_) {
		this.codiDir3 = _codiDir3_;
	};

	public boolean isActiva() {
		return(activa);
	};
	public void setActiva(boolean _activa_) {
		this.activa = _activa_;
	};

	public java.lang.String getColorMenu() {
		return(colorMenu);
	};
	public void setColorMenu(java.lang.String _colorMenu_) {
		this.colorMenu = _colorMenu_;
	};

	public long getLogoCapBackID() {
		return(logoCapBackID);
	};
	public void setLogoCapBackID(long _logoCapBackID_) {
		this.logoCapBackID = _logoCapBackID_;
	};

	public long getLogoPeuBackID() {
		return(logoPeuBackID);
	};
	public void setLogoPeuBackID(long _logoPeuBackID_) {
		this.logoPeuBackID = _logoPeuBackID_;
	};

	public long getLogoLateralFrontID() {
		return(logoLateralFrontID);
	};
	public void setLogoLateralFrontID(long _logoLateralFrontID_) {
		this.logoLateralFrontID = _logoLateralFrontID_;
	};

	public java.lang.String getVersio() {
		return(versio);
	};
	public void setVersio(java.lang.String _versio_) {
		this.versio = _versio_;
	};

	public long getIconID() {
		return(iconID);
	};
	public void setIconID(long _iconID_) {
		this.iconID = _iconID_;
	};

	public java.lang.String getWebEntitat() {
		return(webEntitat);
	};
	public void setWebEntitat(java.lang.String _webEntitat_) {
		this.webEntitat = _webEntitat_;
	};

	public java.lang.String getEntitatDescFront() {
		return(entitatDescFront);
	};
	public void setEntitatDescFront(java.lang.String _entitatDescFront_) {
		this.entitatDescFront = _entitatDescFront_;
	};

	public java.lang.String getSuportWeb() {
		return(suportWeb);
	};
	public void setSuportWeb(java.lang.String _suportWeb_) {
		this.suportWeb = _suportWeb_;
	};

	public java.lang.String getSuportTelefon() {
		return(suportTelefon);
	};
	public void setSuportTelefon(java.lang.String _suportTelefon_) {
		this.suportTelefon = _suportTelefon_;
	};

	public java.lang.String getSuportEmail() {
		return(suportEmail);
	};
	public void setSuportEmail(java.lang.String _suportEmail_) {
		this.suportEmail = _suportEmail_;
	};

	public java.lang.String getSuportFAQ() {
		return(suportFAQ);
	};
	public void setSuportFAQ(java.lang.String _suportFAQ_) {
		this.suportFAQ = _suportFAQ_;
	};

	public java.lang.String getSuportqssi() {
		return(suportqssi);
	};
	public void setSuportqssi(java.lang.String _suportqssi_) {
		this.suportqssi = _suportqssi_;
	};

	public java.lang.String getSuportautenticacio() {
		return(suportautenticacio);
	};
	public void setSuportautenticacio(java.lang.String _suportautenticacio_) {
		this.suportautenticacio = _suportautenticacio_;
	};

	public java.lang.Long getPluginLoginID() {
		return(pluginLoginID);
	};
	public void setPluginLoginID(java.lang.Long _pluginLoginID_) {
		this.pluginLoginID = _pluginLoginID_;
	};

	public java.lang.Long getLoginTextID() {
		return(loginTextID);
	};
	public void setLoginTextID(java.lang.Long _loginTextID_) {
		this.loginTextID = _loginTextID_;
	};

	public java.lang.Long getFitxerCssID() {
		return(fitxerCssID);
	};
	public void setFitxerCssID(java.lang.Long _fitxerCssID_) {
		this.fitxerCssID = _fitxerCssID_;
	};

	public java.lang.String getContext() {
		return(context);
	};
	public void setContext(java.lang.String _context_) {
		this.context = _context_;
	};

	public java.lang.String getCommit() {
		return(commit);
	};
	public void setCommit(java.lang.String _commit_) {
		this.commit = _commit_;
	};



  // ======================================

  public static EntitatBean toBean(Entitat __bean) {
    if (__bean == null) { return null;}
    EntitatBean __tmp = new EntitatBean();
    __tmp.setEntitatID(__bean.getEntitatID());
    __tmp.setNomID(__bean.getNomID());
    __tmp.setDescripcioID(__bean.getDescripcioID());
    __tmp.setCodi(__bean.getCodi());
    __tmp.setCodiDir3(__bean.getCodiDir3());
    __tmp.setActiva(__bean.isActiva());
    __tmp.setColorMenu(__bean.getColorMenu());
    __tmp.setLogoCapBackID(__bean.getLogoCapBackID());
    __tmp.setLogoPeuBackID(__bean.getLogoPeuBackID());
    __tmp.setLogoLateralFrontID(__bean.getLogoLateralFrontID());
    __tmp.setVersio(__bean.getVersio());
    __tmp.setIconID(__bean.getIconID());
    __tmp.setWebEntitat(__bean.getWebEntitat());
    __tmp.setEntitatDescFront(__bean.getEntitatDescFront());
    __tmp.setSuportWeb(__bean.getSuportWeb());
    __tmp.setSuportTelefon(__bean.getSuportTelefon());
    __tmp.setSuportEmail(__bean.getSuportEmail());
    __tmp.setSuportFAQ(__bean.getSuportFAQ());
    __tmp.setSuportqssi(__bean.getSuportqssi());
    __tmp.setSuportautenticacio(__bean.getSuportautenticacio());
    __tmp.setPluginLoginID(__bean.getPluginLoginID());
    __tmp.setLoginTextID(__bean.getLoginTextID());
    __tmp.setFitxerCssID(__bean.getFitxerCssID());
    __tmp.setContext(__bean.getContext());
    __tmp.setCommit(__bean.getCommit());
    // Fitxer
    __tmp.setLogoCapBack(FitxerBean.toBean(__bean.getLogoCapBack()));
    // Fitxer
    __tmp.setLogoPeuBack(FitxerBean.toBean(__bean.getLogoPeuBack()));
    // Fitxer
    __tmp.setLogoLateralFront(FitxerBean.toBean(__bean.getLogoLateralFront()));
    // Fitxer
    __tmp.setIcon(FitxerBean.toBean(__bean.getIcon()));
    // Fitxer
    __tmp.setFitxerCss(FitxerBean.toBean(__bean.getFitxerCss()));
		return __tmp;
	}

  protected FitxerBean logoCapBack;
  public FitxerBean getLogoCapBack() {
    return logoCapBack;
  }
  public void setLogoCapBack(FitxerBean __field) {
    this. logoCapBack = __field;
  }
  protected FitxerBean logoPeuBack;
  public FitxerBean getLogoPeuBack() {
    return logoPeuBack;
  }
  public void setLogoPeuBack(FitxerBean __field) {
    this. logoPeuBack = __field;
  }
  protected FitxerBean logoLateralFront;
  public FitxerBean getLogoLateralFront() {
    return logoLateralFront;
  }
  public void setLogoLateralFront(FitxerBean __field) {
    this. logoLateralFront = __field;
  }
  protected FitxerBean icon;
  public FitxerBean getIcon() {
    return icon;
  }
  public void setIcon(FitxerBean __field) {
    this. icon = __field;
  }
  protected FitxerBean fitxerCss;
  public FitxerBean getFitxerCss() {
    return fitxerCss;
  }
  public void setFitxerCss(FitxerBean __field) {
    this. fitxerCss = __field;
  }


}
