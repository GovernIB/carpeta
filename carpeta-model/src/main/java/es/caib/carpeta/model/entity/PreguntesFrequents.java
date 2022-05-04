package es.caib.carpeta.model.entity;

public interface PreguntesFrequents extends org.fundaciobit.genapp.common.IGenAppEntity {

	public long getPreguntesFrequentsID();
	public void setPreguntesFrequentsID(long _preguntesFrequentsID_);

	public long getEnunciatID();
	public void setEnunciatID(long _enunciatID_);

	public long getRespostaID();
	public void setRespostaID(long _respostaID_);

	public int getOrdre();
	public void setOrdre(int _ordre_);

	public long getEntitatID();
	public void setEntitatID(long _entitatID_);

	public java.lang.Long getFitxer1ID();
	public void setFitxer1ID(java.lang.Long _fitxer1ID_);

	public java.lang.Long getFitxer2ID();
	public void setFitxer2ID(java.lang.Long _fitxer2ID_);

	public java.lang.Long getFitxer3ID();
	public void setFitxer3ID(java.lang.Long _fitxer3ID_);

  // Fitxer
  public <F extends Fitxer> F getFitxer1();
  // Fitxer
  public <F extends Fitxer> F getFitxer2();
  // Fitxer
  public <F extends Fitxer> F getFitxer3();


  // ======================================

}
