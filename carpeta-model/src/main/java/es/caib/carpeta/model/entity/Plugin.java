package es.caib.carpeta.model.entity;

public interface Plugin extends org.fundaciobit.genapp.common.IGenAppEntity {

	public long getPluginID();
	public void setPluginID(long _pluginID_);

	public long getNomID();
	public void setNomID(long _nomID_);

	public java.lang.Long getDescripcioID();
	public void setDescripcioID(java.lang.Long _descripcioID_);

	public java.lang.String getContext();
	public void setContext(java.lang.String _context_);

	public java.lang.Long getLogoID();
	public void setLogoID(java.lang.Long _logoID_);

	public java.lang.String getClasse();
	public void setClasse(java.lang.String _classe_);

	public java.lang.String getPropietats();
	public void setPropietats(java.lang.String _propietats_);

	public boolean isActiu();
	public void setActiu(boolean _actiu_);

	public int getTipus();
	public void setTipus(int _tipus_);

  // Fitxer
  public <F extends Fitxer> F getLogo();


  // ======================================

}
