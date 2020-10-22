
package es.caib.carpeta.jpa;
import es.caib.carpeta.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.UniqueConstraint;
import javax.persistence.GeneratedValue;
import org.hibernate.annotations.Index;
import javax.persistence.SequenceGenerator;
import org.hibernate.annotations.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import javax.persistence.Id;


@SuppressWarnings("deprecation")
@Entity
@Table(name = "car_usuarientitat"  , uniqueConstraints = {
            @UniqueConstraint(name="car_usuent_usu_ent_uk", columnNames={"usuariid","entitatid"}) } )
@SequenceGenerator(name="USUARIENTITAT_SEQ", sequenceName="car_usuarientitat_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class UsuariEntitatJPA implements UsuariEntitat {



private static final long serialVersionUID = 2031334771L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="USUARIENTITAT_SEQ")
	@Index(name="car_usuarientitat_pk_i")
	@Column(name="usuarientitatid",nullable = false,length = 19)
	long usuariEntitatID;

	@Index(name="car_usuent_usuariid_fk_i")
	@Column(name="usuariid",nullable = false,length = 19)
	long usuariID;

	@Index(name="car_usuent_entitatid_fk_i")
	@Column(name="entitatid",nullable = false,length = 19)
	long entitatID;

	@Column(name="actiu",nullable = false,length = 1)
	boolean actiu;



  /** Constructor Buit */
  public UsuariEntitatJPA() {
  }

  /** Constructor amb tots els camps  */
  public UsuariEntitatJPA(long usuariEntitatID , long usuariID , long entitatID , boolean actiu) {
    this.usuariEntitatID=usuariEntitatID;
    this.usuariID=usuariID;
    this.entitatID=entitatID;
    this.actiu=actiu;
}
  /** Constructor sense valors autoincrementals */
  public UsuariEntitatJPA(long usuariID , long entitatID , boolean actiu) {
    this.usuariID=usuariID;
    this.entitatID=entitatID;
    this.actiu=actiu;
}
  public UsuariEntitatJPA(UsuariEntitat __bean) {
    this.setUsuariEntitatID(__bean.getUsuariEntitatID());
    this.setUsuariID(__bean.getUsuariID());
    this.setEntitatID(__bean.getEntitatID());
    this.setActiu(__bean.isActiu());
	}

	public long getUsuariEntitatID() {
		return(usuariEntitatID);
	};
	public void setUsuariEntitatID(long _usuariEntitatID_) {
		this.usuariEntitatID = _usuariEntitatID_;
	};

	public long getUsuariID() {
		return(usuariID);
	};
	public void setUsuariID(long _usuariID_) {
		this.usuariID = _usuariID_;
	};

	public long getEntitatID() {
		return(entitatID);
	};
	public void setEntitatID(long _entitatID_) {
		this.entitatID = _entitatID_;
	};

	public boolean isActiu() {
		return(actiu);
	};
	public void setActiu(boolean _actiu_) {
		this.actiu = _actiu_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof UsuariEntitat) {
      UsuariEntitat __instance = (UsuariEntitat)__obj;
      __result = true;
      __result = __result && (this.getUsuariEntitatID() == __instance.getUsuariEntitatID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// IMP Field:usuariid | Table: car_usuari | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="car_usuent_usuari_fk")
	@JoinColumn(name = "usuariid", referencedColumnName ="usuariID", nullable = false, insertable=false, updatable=false)
	private UsuariJPA usuari;

	public UsuariJPA getUsuari() {
    return this.usuari;
  }

	public  void setUsuari(UsuariJPA usuari) {
    this.usuari = usuari;
  }

// IMP Field:entitatid | Table: car_entitat | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="car_usuent_entitat_fk")
	@JoinColumn(name = "entitatid", referencedColumnName ="entitatID", nullable = false, insertable=false, updatable=false)
	private EntitatJPA entitat;

	public EntitatJPA getEntitat() {
    return this.entitat;
  }

	public  void setEntitat(EntitatJPA entitat) {
    this.entitat = entitat;
  }


 // ---------------  STATIC METHODS ------------------
  public static UsuariEntitatJPA toJPA(UsuariEntitat __bean) {
    if (__bean == null) { return null;}
    UsuariEntitatJPA __tmp = new UsuariEntitatJPA();
    __tmp.setUsuariEntitatID(__bean.getUsuariEntitatID());
    __tmp.setUsuariID(__bean.getUsuariID());
    __tmp.setEntitatID(__bean.getEntitatID());
    __tmp.setActiu(__bean.isActiu());
		return __tmp;
	}


  public static UsuariEntitatJPA copyJPA(UsuariEntitatJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<UsuariEntitatJPA> copyJPA(java.util.Set<UsuariEntitatJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    @SuppressWarnings("unchecked")
    java.util.Set<UsuariEntitatJPA> __tmpSet = (java.util.Set<UsuariEntitatJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<UsuariEntitatJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (UsuariEntitatJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static UsuariEntitatJPA copyJPA(UsuariEntitatJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    UsuariEntitatJPA __tmp = (UsuariEntitatJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    // Copia de beans complexes (IMP)
    if(!"UsuariJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.usuari) || org.hibernate.Hibernate.isInitialized(__jpa.getUsuari()) ) ) {
      __tmp.setUsuari(UsuariJPA.copyJPA(__jpa.getUsuari(), __alreadyCopied,"UsuariEntitatJPA"));
    }
    if(!"EntitatJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.entitat) || org.hibernate.Hibernate.isInitialized(__jpa.getEntitat()) ) ) {
      __tmp.setEntitat(EntitatJPA.copyJPA(__jpa.getEntitat(), __alreadyCopied,"UsuariEntitatJPA"));
    }

    return __tmp;
  }




}
