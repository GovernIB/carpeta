package es.caib.carpeta.model.entity;

public interface Ciutada extends org.fundaciobit.genapp.common.IGenAppEntity {

	public long getCiutadaID();
	public void setCiutadaID(long _ciutadaID_);

	public java.lang.String getNif();
	public void setNif(java.lang.String _nif_);

	public java.lang.String getLlinatge1();
	public void setLlinatge1(java.lang.String _llinatge1_);

	public java.lang.String getLlinatge2();
	public void setLlinatge2(java.lang.String _llinatge2_);

	public java.lang.String getNom();
	public void setNom(java.lang.String _nom_);

	public boolean isEmpresa();
	public void setEmpresa(boolean _empresa_);

	public java.lang.String getRepresentantNif();
	public void setRepresentantNif(java.lang.String _representantNif_);

	public java.lang.String getRepresentantLlinatge1();
	public void setRepresentantLlinatge1(java.lang.String _representantLlinatge1_);

	public java.lang.String getRepresentantLlinatge2();
	public void setRepresentantLlinatge2(java.lang.String _representantLlinatge2_);

	public java.lang.String getRepresentantNom();
	public void setRepresentantNom(java.lang.String _representantNom_);

	public java.sql.Timestamp getDataCreacio();
	public void setDataCreacio(java.sql.Timestamp _dataCreacio_);

	public java.lang.String getMobileId();
	public void setMobileId(java.lang.String _mobileId_);



  // ======================================

}
