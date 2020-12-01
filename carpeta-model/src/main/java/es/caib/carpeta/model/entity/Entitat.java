package es.caib.carpeta.model.entity;

public interface Entitat extends org.fundaciobit.genapp.common.IGenAppEntity {

	public long getEntitatID();
	public void setEntitatID(long _entitatID_);

	public long getNomID();
	public void setNomID(long _nomID_);

	public java.lang.String getCodi();
	public void setCodi(java.lang.String _codi_);

	public java.lang.String getCodiDir3();
	public void setCodiDir3(java.lang.String _codiDir3_);

	public boolean isActiva();
	public void setActiva(boolean _activa_);

	public java.lang.String getColorMenu();
	public void setColorMenu(java.lang.String _colorMenu_);

	public long getLogoCapBackID();
	public void setLogoCapBackID(long _logoCapBackID_);

	public long getLogoPeuBackID();
	public void setLogoPeuBackID(long _logoPeuBackID_);

	public long getLogoLateralFrontID();
	public void setLogoLateralFrontID(long _logoLateralFrontID_);

	public java.lang.String getVersio();
	public void setVersio(java.lang.String _versio_);

	public long getIconID();
	public void setIconID(long _iconID_);

	public java.lang.String getWebEntitat();
	public void setWebEntitat(java.lang.String _webEntitat_);

	public java.lang.String getEntitatDescFront();
	public void setEntitatDescFront(java.lang.String _entitatDescFront_);

	public java.lang.String getSuportWeb();
	public void setSuportWeb(java.lang.String _suportWeb_);

	public java.lang.String getSuportTelefon();
	public void setSuportTelefon(java.lang.String _suportTelefon_);

	public java.lang.String getSuportEmail();
	public void setSuportEmail(java.lang.String _suportEmail_);

	public java.lang.String getSuportFAQ();
	public void setSuportFAQ(java.lang.String _suportFAQ_);

	public java.lang.String getSuportqssi();
	public void setSuportqssi(java.lang.String _suportqssi_);

	public java.lang.String getSuportautenticacio();
	public void setSuportautenticacio(java.lang.String _suportautenticacio_);

	public java.lang.Long getPluginLoginID();
	public void setPluginLoginID(java.lang.Long _pluginLoginID_);

	public java.lang.Long getLoginTextID();
	public void setLoginTextID(java.lang.Long _loginTextID_);

	public java.lang.Long getFitxerCssID();
	public void setFitxerCssID(java.lang.Long _fitxerCssID_);

	public java.lang.String getContext();
	public void setContext(java.lang.String _context_);

	public java.lang.String getCommit();
	public void setCommit(java.lang.String _commit_);

  // Fitxer
  public <F extends Fitxer> F getLogoCapBack();
  // Fitxer
  public <F extends Fitxer> F getLogoPeuBack();
  // Fitxer
  public <F extends Fitxer> F getLogoLateralFront();
  // Fitxer
  public <F extends Fitxer> F getIcon();
  // Fitxer
  public <F extends Fitxer> F getFitxerCss();


  // ======================================

}
