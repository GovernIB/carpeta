
package es.caib.carpeta.jpa;
import es.caib.carpeta.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.GeneratedValue;
import org.hibernate.annotations.Index;
import javax.persistence.SequenceGenerator;
import org.hibernate.annotations.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
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

	@Index(name="car_estadistica_entitatid_fk_i")
	@Column(name="entitatid",length = 19)
	java.lang.Long entitatID;

	@Column(name="dataestadistica",nullable = false,length = 29,precision = 6)
	java.sql.Timestamp dataEstadistica;

	@Column(name="tipus",nullable = false,length = 10)
	int tipus;

	@Column(name="comptador",nullable = false,length = 10)
	int comptador;

	@Column(name="pluginid",length = 19)
	java.lang.Long pluginID;



  /** Constructor Buit */
  public EstadisticaJPA() {
  }

  /** Constructor amb tots els camps  */
  public EstadisticaJPA(long estadisticaID , java.lang.Long entitatID , java.sql.Timestamp dataEstadistica , int tipus , int comptador , java.lang.Long pluginID) {
    this.estadisticaID=estadisticaID;
    this.entitatID=entitatID;
    this.dataEstadistica=dataEstadistica;
    this.tipus=tipus;
    this.comptador=comptador;
    this.pluginID=pluginID;
}
  /** Constructor sense valors autoincrementals */
  public EstadisticaJPA(java.lang.Long entitatID , java.sql.Timestamp dataEstadistica , int tipus , int comptador , java.lang.Long pluginID) {
    this.entitatID=entitatID;
    this.dataEstadistica=dataEstadistica;
    this.tipus=tipus;
    this.comptador=comptador;
    this.pluginID=pluginID;
}
  /** Constructor dels valors Not Null */
  public EstadisticaJPA(long estadisticaID , java.sql.Timestamp dataEstadistica , int tipus , int comptador) {
    this.estadisticaID=estadisticaID;
    this.dataEstadistica=dataEstadistica;
    this.tipus=tipus;
    this.comptador=comptador;
}
  public EstadisticaJPA(Estadistica __bean) {
    this.setEstadisticaID(__bean.getEstadisticaID());
    this.setEntitatID(__bean.getEntitatID());
    this.setDataEstadistica(__bean.getDataEstadistica());
    this.setTipus(__bean.getTipus());
    this.setComptador(__bean.getComptador());
    this.setPluginID(__bean.getPluginID());
	}

	public long getEstadisticaID() {
		return(estadisticaID);
	};
	public void setEstadisticaID(long _estadisticaID_) {
		this.estadisticaID = _estadisticaID_;
	};

	public java.lang.Long getEntitatID() {
		return(entitatID);
	};
	public void setEntitatID(java.lang.Long _entitatID_) {
		this.entitatID = _entitatID_;
	};

	public java.sql.Timestamp getDataEstadistica() {
		return(dataEstadistica);
	};
	public void setDataEstadistica(java.sql.Timestamp _dataEstadistica_) {
		this.dataEstadistica = _dataEstadistica_;
	};

	public int getTipus() {
		return(tipus);
	};
	public void setTipus(int _tipus_) {
		this.tipus = _tipus_;
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

// IMP Field:entitatid | Table: car_entitat | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="car_estadis_entitat_ent_fk")
	@JoinColumn(name = "entitatid", referencedColumnName ="entitatID", nullable = true, insertable=false, updatable=false)
	private EntitatJPA entitat;

	public EntitatJPA getEntitat() {
    return this.entitat;
  }

	public  void setEntitat(EntitatJPA entitat) {
    this.entitat = entitat;
  }


 // ---------------  STATIC METHODS ------------------
  public static EstadisticaJPA toJPA(Estadistica __bean) {
    if (__bean == null) { return null;}
    EstadisticaJPA __tmp = new EstadisticaJPA();
    __tmp.setEstadisticaID(__bean.getEstadisticaID());
    __tmp.setEntitatID(__bean.getEntitatID());
    __tmp.setDataEstadistica(__bean.getDataEstadistica());
    __tmp.setTipus(__bean.getTipus());
    __tmp.setComptador(__bean.getComptador());
    __tmp.setPluginID(__bean.getPluginID());
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
    if(!"EntitatJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.entitat) || org.hibernate.Hibernate.isInitialized(__jpa.getEntitat()) ) ) {
      __tmp.setEntitat(EntitatJPA.copyJPA(__jpa.getEntitat(), __alreadyCopied,"EstadisticaJPA"));
    }

    return __tmp;
  }




}
