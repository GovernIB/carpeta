
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
@SequenceGenerator(name="CARPETA_SEQ", sequenceName="car_carpeta_seq", allocationSize=1)
@javax.xml.bind.annotation.XmlRootElement
public class EstadisticaJPA implements Estadistica {



private static final long serialVersionUID = -2066559243L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="CARPETA_SEQ")
	@Index(name="car_estadistica_pk_i")
	@Column(name="estadisticaid",nullable = false,length = 19)
	long estadisticaID;

	@Index(name="car_estadistica_entitatid_fk_i")
	@Column(name="entitatid",nullable = false,length = 19)
	long entitatID;

	@Index(name="car_estadistica_accesid_fk_i")
	@Column(name="accesid",length = 19)
	java.lang.Long accesID;

	@Column(name="accio",nullable = false,length = 10)
	int accio;

	@Column(name="element",length = 255)
	java.lang.String element;

	@Column(name="dataestadistica",nullable = false,length = 29,precision = 6)
	java.sql.Timestamp dataEstadistica;



  /** Constructor Buit */
  public EstadisticaJPA() {
  }

  /** Constructor amb tots els camps  */
  public EstadisticaJPA(long estadisticaID , long entitatID , java.lang.Long accesID , int accio , java.lang.String element , java.sql.Timestamp dataEstadistica) {
    this.estadisticaID=estadisticaID;
    this.entitatID=entitatID;
    this.accesID=accesID;
    this.accio=accio;
    this.element=element;
    this.dataEstadistica=dataEstadistica;
}
  /** Constructor sense valors autoincrementals */
  public EstadisticaJPA(long entitatID , java.lang.Long accesID , int accio , java.lang.String element , java.sql.Timestamp dataEstadistica) {
    this.entitatID=entitatID;
    this.accesID=accesID;
    this.accio=accio;
    this.element=element;
    this.dataEstadistica=dataEstadistica;
}
  /** Constructor dels valors Not Null */
  public EstadisticaJPA(long estadisticaID , long entitatID , int accio , java.sql.Timestamp dataEstadistica) {
    this.estadisticaID=estadisticaID;
    this.entitatID=entitatID;
    this.accio=accio;
    this.dataEstadistica=dataEstadistica;
}
  public EstadisticaJPA(Estadistica __bean) {
    this.setEstadisticaID(__bean.getEstadisticaID());
    this.setEntitatID(__bean.getEntitatID());
    this.setAccesID(__bean.getAccesID());
    this.setAccio(__bean.getAccio());
    this.setElement(__bean.getElement());
    this.setDataEstadistica(__bean.getDataEstadistica());
	}

	public long getEstadisticaID() {
		return(estadisticaID);
	};
	public void setEstadisticaID(long _estadisticaID_) {
		this.estadisticaID = _estadisticaID_;
	};

	public long getEntitatID() {
		return(entitatID);
	};
	public void setEntitatID(long _entitatID_) {
		this.entitatID = _entitatID_;
	};

	public java.lang.Long getAccesID() {
		return(accesID);
	};
	public void setAccesID(java.lang.Long _accesID_) {
		this.accesID = _accesID_;
	};

	public int getAccio() {
		return(accio);
	};
	public void setAccio(int _accio_) {
		this.accio = _accio_;
	};

	public java.lang.String getElement() {
		return(element);
	};
	public void setElement(java.lang.String _element_) {
		this.element = _element_;
	};

	public java.sql.Timestamp getDataEstadistica() {
		return(dataEstadistica);
	};
	public void setDataEstadistica(java.sql.Timestamp _dataEstadistica_) {
		this.dataEstadistica = _dataEstadistica_;
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
	@JoinColumn(name = "entitatid", referencedColumnName ="entitatID", nullable = false, insertable=false, updatable=false)
	private EntitatJPA entitat;

	public EntitatJPA getEntitat() {
    return this.entitat;
  }

	public  void setEntitat(EntitatJPA entitat) {
    this.entitat = entitat;
  }

// IMP Field:accesid | Table: car_acces | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="car_estadis_acces_ac_fk")
	@JoinColumn(name = "accesid", referencedColumnName ="accesID", nullable = true, insertable=false, updatable=false)
	private AccesJPA acces;

	public AccesJPA getAcces() {
    return this.acces;
  }

	public  void setAcces(AccesJPA acces) {
    this.acces = acces;
  }


 // ---------------  STATIC METHODS ------------------
  public static EstadisticaJPA toJPA(Estadistica __bean) {
    if (__bean == null) { return null;}
    EstadisticaJPA __tmp = new EstadisticaJPA();
    __tmp.setEstadisticaID(__bean.getEstadisticaID());
    __tmp.setEntitatID(__bean.getEntitatID());
    __tmp.setAccesID(__bean.getAccesID());
    __tmp.setAccio(__bean.getAccio());
    __tmp.setElement(__bean.getElement());
    __tmp.setDataEstadistica(__bean.getDataEstadistica());
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
    if(!"AccesJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.acces) || org.hibernate.Hibernate.isInitialized(__jpa.getAcces()) ) ) {
      __tmp.setAcces(AccesJPA.copyJPA(__jpa.getAcces(), __alreadyCopied,"EstadisticaJPA"));
    }
    if(!"EntitatJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.entitat) || org.hibernate.Hibernate.isInitialized(__jpa.getEntitat()) ) ) {
      __tmp.setEntitat(EntitatJPA.copyJPA(__jpa.getEntitat(), __alreadyCopied,"EstadisticaJPA"));
    }

    return __tmp;
  }




}
