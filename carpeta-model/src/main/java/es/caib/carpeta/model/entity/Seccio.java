package es.caib.carpeta.model.entity;

public interface Seccio extends org.fundaciobit.genapp.common.IGenAppEntity {

	public long getSeccioID();
	public void setSeccioID(long _seccioID_);

	public long getNomID();
	public void setNomID(long _nomID_);

	public long getDescripcioID();
	public void setDescripcioID(long _descripcioID_);

	public java.lang.String getContext();
	public void setContext(java.lang.String _context_);

	public boolean isActiva();
	public void setActiva(boolean _activa_);

	public long getIconaID();
	public void setIconaID(long _iconaID_);

	public java.lang.Long getSeccioPareID();
	public void setSeccioPareID(java.lang.Long _seccioPareID_);

	public long getEntitatID();
	public void setEntitatID(long _entitatID_);

	public int getOrdre();
	public void setOrdre(int _ordre_);

  // Fitxer
  public <F extends Fitxer> F getIcona();


  // ======================================

}
