package es.caib.carpeta.model.entity;

public interface Enllaz extends org.fundaciobit.genapp.common.IGenAppEntity {

	public long getEnllazID();
	public void setEnllazID(long _enllazID_);

	public int getTipus();
	public void setTipus(int _tipus_);

	public long getNomID();
	public void setNomID(long _nomID_);

	public long getUrlID();
	public void setUrlID(long _urlID_);

	public long getEntitatID();
	public void setEntitatID(long _entitatID_);

	public long getLogoID();
	public void setLogoID(long _logoID_);

	public java.lang.Long getSeccioID();
	public void setSeccioID(java.lang.Long _seccioID_);

  // Fitxer
  public <F extends Fitxer> F getLogo();


  // ======================================

}
