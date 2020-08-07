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

	public java.lang.Long getLogoMenuID();
	public void setLogoMenuID(java.lang.Long _logoMenuID_);

	public java.lang.String getColorMenu();
	public void setColorMenu(java.lang.String _colorMenu_);

	public java.lang.String getTextePeu();
	public void setTextePeu(java.lang.String _textePeu_);

	public long getLogoPeuID();
	public void setLogoPeuID(long _logoPeuID_);

	public java.lang.String getVersio();
	public void setVersio(java.lang.String _versio_);

	public java.lang.String getCommit();
	public void setCommit(java.lang.String _commit_);

	public java.lang.Long getFitxerCssID();
	public void setFitxerCssID(java.lang.Long _fitxerCssID_);

	public java.lang.String getContext();
	public void setContext(java.lang.String _context_);

  // Fitxer
  public <F extends Fitxer> F getLogoMenu();
  // Fitxer
  public <F extends Fitxer> F getLogoPeu();
  // Fitxer
  public <F extends Fitxer> F getFitxerCss();


  // ======================================

}
