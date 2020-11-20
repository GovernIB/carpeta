
package es.caib.carpeta.jpa;
import es.caib.carpeta.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import org.hibernate.annotations.Index;
import javax.persistence.SequenceGenerator;
import javax.persistence.Id;


@SuppressWarnings("deprecation")
@Entity
@Table(name = "car_estadistica" )
@SequenceGenerator(name="ESTADISTICA_SEQ", sequenceName="car_estadistica_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class EstadisticaJPA implements Estadistica {



private static final long serialVersionUID = -2066559243L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ESTADISTICA_SEQ")
	@Index(name="car_estadistica_pk_i")
	@Column(name="estadisticaid",nullable = false,length = 19)
	long estadisticaID;

	@Column(name="tipus",nullable = false,length = 10)
	int tipus;

	@Column(name="dataestadistica",nullable = false,length = 29,precision = 6)
	java.sql.Timestamp dataEstadistica;

	@Column(name="comptador",nullable = false,length = 10)
	int comptador;

	@Column(name="pluginid",length = 19)
	java.lang.Long pluginID;

	@Index(name="car_estadistica_entitatid_fk_i")
	@Column(name="entitatid",length = 19)
	java.lang.Long entitatID;



  /** Constructor Buit */
  public EstadisticaJPA() {
  }

  /** Constructor amb tots els camps  */
  public EstadisticaJPA(long estadisticaID , int tipus , java.sql.Timestamp dataEstadistica , int comptador , java.lang.Long pluginID , java.lang.Long entitatID) {
    this.estadisticaID=estadisticaID;
    this.tipus=tipus;
    this.dataEstadistica=dataEstadistica;
    this.comptador=comptador;
    this.pluginID=pluginID;
    this.entitatID=entitatID;
}
  /** Constructor sense valors autoincrementals */
  public EstadisticaJPA(int tipus , java.sql.Timestamp dataEstadistica , int comptador , java.lang.Long pluginID , java.lang.Long entitatID) {
    this.tipus=tipus;
    this.dataEstadistica=dataEstadistica;
    this.comptador=comptador;
    this.pluginID=pluginID;
    this.entitatID=entitatID;
}
  /** Constructor dels valors Not Null */
  public EstadisticaJPA(long estadisticaID , int tipus , java.sql.Timestamp dataEstadistica , int comptador) {
    this.estadisticaID=estadisticaID;
    this.tipus=tipus;
    this.dataEstadistica=dataEstadistica;
    this.comptador=comptador;
}
  public EstadisticaJPA(Estadistica __bean) {
    this.setEstadisticaID(__bean.getEstadisticaID());
    this.setTipus(__bean.getTipus());
    this.setDataEstadistica(__bean.getDataEstadistica());
    this.setComptador(__bean.getComptador());
    this.setPluginID(__bean.getPluginID());
    this.setEntitatID(__bean.getEntitatID());
	}

	public long getEstadisticaID() {
		return(estadisticaID);
	};
	public void setEstadisticaID(long _estadisticaID_) {
		this.estadisticaID = _estadisticaID_;
	};

	public int getTipus() {
		return(tipus);
	};
	public void setTipus(int _tipus_) {
		this.tipus = _tipus_;
	};

	public java.sql.Timestamp getDataEstadistica() {
		return(dataEstadistica);
	};
	public void setDataEstadistica(java.sql.Timestamp _dataEstadistica_) {
		this.dataEstadistica = _dataEstadistica_;
	};

	public int getComptador() {
		return(comptador);
	};
	public void setComptador(int _comptador_) {
		this.comptador = _comptador_;
	};

	public java.lang.Long getPluginID() {
		return(pluginID);
	};
	public void setPluginID(java.lang.Long _pluginID_) {
		this.pluginID = _pluginID_;
	};

	public java.lang.Long getEntitatID() {
		return(entitatID);
	};
	public void setEntitatID(java.lang.Long _entitatID_) {
		this.entitatID = _entitatID_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof Estadistica) {
      Estadistica __instance = (Estadistica)__obj;
      __result = true;
      __result = __result && (this.getEstadisticaID() == __instance.getEstadisticaID()) ;
    } else {
      __result = false;
    }
    return __result;
  }


 // ---------------  STATIC METHODS ------------------
  public static EstadisticaJPA toJPA(Estadistica __bean) {
    if (__bean == null) { return null;}
    EstadisticaJPA __tmp = new EstadisticaJPA();
    __tmp.setEstadisticaID(__bean.getEstadisticaID());
    __tmp.setTipus(__bean.getTipus());
    __tmp.setDataEstadistica(__bean.getDataEstadistica());
    __tmp.setComptador(__bean.getComptador());
    __tmp.setPluginID(__bean.getPluginID());
    __tmp.setEntitatID(__bean.getEntitatID());
		return __tmp;
	}


  public static EstadisticaJPA copyJPA(EstadisticaJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<EstadisticaJPA> copyJPA(java.util.Set<EstadisticaJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    @SuppressWarnings("unchecked")
    java.util.Set<EstadisticaJPA> __tmpSet = (java.util.Set<EstadisticaJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<EstadisticaJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (EstadisticaJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static EstadisticaJPA copyJPA(EstadisticaJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    EstadisticaJPA __tmp = (EstadisticaJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    // Copia de beans complexes (IMP)

    return __tmp;
  }




}
