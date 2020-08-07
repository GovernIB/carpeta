
package es.caib.carpeta.model.bean;

import es.caib.carpeta.model.entity.Entitat;


public class EntitatBean implements Entitat {



private static final long serialVersionUID = -2014602951L;

	long entitatID;// PK
	long nomID;
	java.lang.String codi;
	java.lang.String codiDir3;
	boolean activa;
	java.lang.Long logoMenuID;
	java.lang.String colorMenu;
	java.lang.String textePeu;
	long logoPeuID;
	java.lang.String versio;
	java.lang.String commit;
	java.lang.Long fitxerCssID;
	java.lang.String context;


  /** Constructor Buit */
  public EntitatBean() {
  }

  /** Constructor amb tots els camps  */
  public EntitatBean(long entitatID , long nomID , java.lang.String codi , java.lang.String codiDir3 , boolean activa , java.lang.Long logoMenuID , java.lang.String colorMenu , java.lang.String textePeu , long logoPeuID , java.lang.String versio , java.lang.String commit , java.lang.Long fitxerCssID , java.lang.String context) {
    this.entitatID=entitatID;
    this.nomID=nomID;
    this.codi=codi;
    this.codiDir3=codiDir3;
    this.activa=activa;
    this.logoMenuID=logoMenuID;
    this.colorMenu=colorMenu;
    this.textePeu=textePeu;
    this.logoPeuID=logoPeuID;
    this.versio=versio;
    this.commit=commit;
    this.fitxerCssID=fitxerCssID;
    this.context=context;
}
  /** Constructor sense valors autoincrementals */
  public EntitatBean(long nomID , java.lang.String codi , java.lang.String codiDir3 , boolean activa , java.lang.Long logoMenuID , java.lang.String colorMenu , java.lang.String textePeu , long logoPeuID , java.lang.String versio , java.lang.String commit , java.lang.Long fitxerCssID , java.lang.String context) {
    this.nomID=nomID;
    this.codi=codi;
    this.codiDir3=codiDir3;
    this.activa=activa;
    this.logoMenuID=logoMenuID;
    this.colorMenu=colorMenu;
    this.textePeu=textePeu;
    this.logoPeuID=logoPeuID;
    this.versio=versio;
    this.commit=commit;
    this.fitxerCssID=fitxerCssID;
    this.context=context;
}
  /** Constructor dels valors Not Null */
  public EntitatBean(long entitatID , long nomID , java.lang.String codi , java.lang.String codiDir3 , boolean activa , java.lang.String colorMenu , long logoPeuID , java.lang.String versio) {
    this.entitatID=entitatID;
    this.nomID=nomID;
    this.codi=codi;
    this.codiDir3=codiDir3;
    this.activa=activa;
    this.colorMenu=colorMenu;
    this.logoPeuID=logoPeuID;
    this.versio=versio;
}
  public EntitatBean(Entitat __bean) {
    this.setEntitatID(__bean.getEntitatID());
    this.setNomID(__bean.getNomID());
    this.setCodi(__bean.getCodi());
    this.setCodiDir3(__bean.getCodiDir3());
    this.setActiva(__bean.isActiva());
    this.setLogoMenuID(__bean.getLogoMenuID());
    this.setColorMenu(__bean.getColorMenu());
    this.setTextePeu(__bean.getTextePeu());
    this.setLogoPeuID(__bean.getLogoPeuID());
    this.setVersio(__bean.getVersio());
    this.setCommit(__bean.getCommit());
    this.setFitxerCssID(__bean.getFitxerCssID());
    this.setContext(__bean.getContext());
    // Fitxer
    this.setLogoMenu(FitxerBean.toBean(__bean.getLogoMenu()));
    // Fitxer
    this.setLogoPeu(FitxerBean.toBean(__bean.getLogoPeu()));
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

	public java.lang.Long getLogoMenuID() {
		return(logoMenuID);
	};
	public void setLogoMenuID(java.lang.Long _logoMenuID_) {
		this.logoMenuID = _logoMenuID_;
	};

	public java.lang.String getColorMenu() {
		return(colorMenu);
	};
	public void setColorMenu(java.lang.String _colorMenu_) {
		this.colorMenu = _colorMenu_;
	};

	public java.lang.String getTextePeu() {
		return(textePeu);
	};
	public void setTextePeu(java.lang.String _textePeu_) {
		this.textePeu = _textePeu_;
	};

	public long getLogoPeuID() {
		return(logoPeuID);
	};
	public void setLogoPeuID(long _logoPeuID_) {
		this.logoPeuID = _logoPeuID_;
	};

	public java.lang.String getVersio() {
		return(versio);
	};
	public void setVersio(java.lang.String _versio_) {
		this.versio = _versio_;
	};

	public java.lang.String getCommit() {
		return(commit);
	};
	public void setCommit(java.lang.String _commit_) {
		this.commit = _commit_;
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



  // ======================================

  public static EntitatBean toBean(Entitat __bean) {
    if (__bean == null) { return null;}
    EntitatBean __tmp = new EntitatBean();
    __tmp.setEntitatID(__bean.getEntitatID());
    __tmp.setNomID(__bean.getNomID());
    __tmp.setCodi(__bean.getCodi());
    __tmp.setCodiDir3(__bean.getCodiDir3());
    __tmp.setActiva(__bean.isActiva());
    __tmp.setLogoMenuID(__bean.getLogoMenuID());
    __tmp.setColorMenu(__bean.getColorMenu());
    __tmp.setTextePeu(__bean.getTextePeu());
    __tmp.setLogoPeuID(__bean.getLogoPeuID());
    __tmp.setVersio(__bean.getVersio());
    __tmp.setCommit(__bean.getCommit());
    __tmp.setFitxerCssID(__bean.getFitxerCssID());
    __tmp.setContext(__bean.getContext());
    // Fitxer
    __tmp.setLogoMenu(FitxerBean.toBean(__bean.getLogoMenu()));
    // Fitxer
    __tmp.setLogoPeu(FitxerBean.toBean(__bean.getLogoPeu()));
    // Fitxer
    __tmp.setFitxerCss(FitxerBean.toBean(__bean.getFitxerCss()));
		return __tmp;
	}

  protected FitxerBean logoMenu;
  public FitxerBean getLogoMenu() {
    return logoMenu;
  }
  public void setLogoMenu(FitxerBean __field) {
    this. logoMenu = __field;
  }
  protected FitxerBean logoPeu;
  public FitxerBean getLogoPeu() {
    return logoPeu;
  }
  public void setLogoPeu(FitxerBean __field) {
    this. logoPeu = __field;
  }
  protected FitxerBean fitxerCss;
  public FitxerBean getFitxerCss() {
    return fitxerCss;
  }
  public void setFitxerCss(FitxerBean __field) {
    this. fitxerCss = __field;
  }


}
