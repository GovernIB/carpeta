package es.caib.carpeta.model.entity;

public interface Plugin extends org.fundaciobit.genapp.common.IGenAppEntity {

	public long getPluginID();
	public void setPluginID(long _pluginID_);

	public long getNomID();
	public void setNomID(long _nomID_);

	public java.lang.String getClasse();
	public void setClasse(java.lang.String _classe_);

	public java.lang.String getPropietats();
	public void setPropietats(java.lang.String _propietats_);

	public boolean isActiu();
	public void setActiu(boolean _actiu_);

	public int getTipus();
	public void setTipus(int _tipus_);



  // ======================================

}
