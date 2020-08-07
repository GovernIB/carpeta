
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
@Table(name = "car_propietatglobal" )
@SequenceGenerator(name="CARPETA_SEQ", sequenceName="car_carpeta_seq", allocationSize=1)
@javax.xml.bind.annotation.XmlRootElement
public class PropietatGlobalJPA implements PropietatGlobal {



private static final long serialVersionUID = 1545722544L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="CARPETA_SEQ")
	@Index(name="car_propietatglobal_pk_i")
	@Column(name="propietatglobalid",nullable = false,length = 19)
	long propietaGlobalID;

	@Column(name="codi",nullable = false,length = 250)
	java.lang.String codi;

	@Column(name="value",length = 4000)
	java.lang.String value;

	@Column(name="descripcio",length = 1000)
	java.lang.String descripcio;

	@Index(name="car_propglob_entitatid_fk_i")
	@Column(name="entitatid",length = 19)
	java.lang.Long entitatID;



  /** Constructor Buit */
  public PropietatGlobalJPA() {
  }

  /** Constructor amb tots els camps  */
  public PropietatGlobalJPA(long propietaGlobalID , java.lang.String codi , java.lang.String value , java.lang.String descripcio , java.lang.Long entitatID) {
    this.propietaGlobalID=propietaGlobalID;
    this.codi=codi;
    this.value=value;
    this.descripcio=descripcio;
    this.entitatID=entitatID;
}
  /** Constructor sense valors autoincrementals */
  public PropietatGlobalJPA(java.lang.String codi , java.lang.String value , java.lang.String descripcio , java.lang.Long entitatID) {
    this.codi=codi;
    this.value=value;
    this.descripcio=descripcio;
    this.entitatID=entitatID;
}
  /** Constructor dels valors Not Null */
  public PropietatGlobalJPA(long propietaGlobalID , java.lang.String codi) {
    this.propietaGlobalID=propietaGlobalID;
    this.codi=codi;
}
  public PropietatGlobalJPA(PropietatGlobal __bean) {
    this.setPropietaGlobalID(__bean.getPropietaGlobalID());
    this.setCodi(__bean.getCodi());
    this.setValue(__bean.getValue());
    this.setDescripcio(__bean.getDescripcio());
    this.setEntitatID(__bean.getEntitatID());
	}

	public long getPropietaGlobalID() {
		return(propietaGlobalID);
	};
	public void setPropietaGlobalID(long _propietaGlobalID_) {
		this.propietaGlobalID = _propietaGlobalID_;
	};

	public java.lang.String getCodi() {
		return(codi);
	};
	public void setCodi(java.lang.String _codi_) {
		this.codi = _codi_;
	};

	public java.lang.String getValue() {
		return(value);
	};
	public void setValue(java.lang.String _value_) {
		this.value = _value_;
	};

	public java.lang.String getDescripcio() {
		return(descripcio);
	};
	public void setDescripcio(java.lang.String _descripcio_) {
		this.descripcio = _descripcio_;
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
    if (__obj != null && __obj instanceof PropietatGlobal) {
      PropietatGlobal __instance = (PropietatGlobal)__obj;
      __result = true;
      __result = __result && (this.getPropietaGlobalID() == __instance.getPropietaGlobalID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// IMP Field:entitatid | Table: car_entitat | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="car_propglob_entitat_fk")
	@JoinColumn(name = "entitatid", referencedColumnName ="entitatID", nullable = true, insertable=false, updatable=false)
	private EntitatJPA entitat;

	public EntitatJPA getEntitat() {
    return this.entitat;
  }

	public  void setEntitat(EntitatJPA entitat) {
    this.entitat = entitat;
  }


 // ---------------  STATIC METHODS ------------------
  public static PropietatGlobalJPA toJPA(PropietatGlobal __bean) {
    if (__bean == null) { return null;}
    PropietatGlobalJPA __tmp = new PropietatGlobalJPA();
    __tmp.setPropietaGlobalID(__bean.getPropietaGlobalID());
    __tmp.setCodi(__bean.getCodi());
    __tmp.setValue(__bean.getValue());
    __tmp.setDescripcio(__bean.getDescripcio());
    __tmp.setEntitatID(__bean.getEntitatID());
		return __tmp;
	}


  public static PropietatGlobalJPA copyJPA(PropietatGlobalJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<PropietatGlobalJPA> copyJPA(java.util.Set<PropietatGlobalJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    @SuppressWarnings("unchecked")
    java.util.Set<PropietatGlobalJPA> __tmpSet = (java.util.Set<PropietatGlobalJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<PropietatGlobalJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (PropietatGlobalJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static PropietatGlobalJPA copyJPA(PropietatGlobalJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    PropietatGlobalJPA __tmp = (PropietatGlobalJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    // Copia de beans complexes (IMP)
    if(!"EntitatJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.entitat) || org.hibernate.Hibernate.isInitialized(__jpa.getEntitat()) ) ) {
      __tmp.setEntitat(EntitatJPA.copyJPA(__jpa.getEntitat(), __alreadyCopied,"PropietatGlobalJPA"));
    }

    return __tmp;
  }




}
