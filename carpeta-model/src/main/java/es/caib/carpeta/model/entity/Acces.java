package es.caib.carpeta.model.entity;

public interface Acces extends org.fundaciobit.genapp.common.IGenAppEntity {

	public long getAccesID();
	public void setAccesID(long _accesID_);

	public int getTipus();
	public void setTipus(int _tipus_);

	public java.lang.String getNom();
	public void setNom(java.lang.String _nom_);

	public java.lang.String getLlinatges();
	public void setLlinatges(java.lang.String _llinatges_);

	public java.lang.String getNif();
	public void setNif(java.lang.String _nif_);

	public java.lang.String getIp();
	public void setIp(java.lang.String _ip_);

	public java.lang.String getProveidorIdentitat();
	public void setProveidorIdentitat(java.lang.String _proveidorIdentitat_);

	public java.lang.String getMetodeAutenticacio();
	public void setMetodeAutenticacio(java.lang.String _metodeAutenticacio_);

	public int getQaa();
	public void setQaa(int _qaa_);

	public java.sql.Timestamp getDataAcces();
	public void setDataAcces(java.sql.Timestamp _dataAcces_);

	public java.lang.Long getPluginID();
	public void setPluginID(java.lang.Long _pluginID_);

	public long getEntitatID();
	public void setEntitatID(long _entitatID_);

	public java.lang.String getIdioma();
	public void setIdioma(java.lang.String _idioma_);

	public boolean isResultat();
	public void setResultat(boolean _resultat_);



  // ======================================

}
