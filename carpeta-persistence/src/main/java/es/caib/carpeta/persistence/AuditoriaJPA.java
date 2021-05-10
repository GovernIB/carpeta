
package es.caib.carpeta.persistence;
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
@Table(name = "car_auditoria" )
@SequenceGenerator(name="AUDITORIA_SEQ", sequenceName="car_auditoria_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class AuditoriaJPA implements Auditoria {



private static final long serialVersionUID = 129744773L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="AUDITORIA_SEQ")
    @Index(name="car_auditoria_pk_i")
    @Column(name="auditoriaid",nullable = false,length = 19)
    long auditoriaID;

    @Column(name="tipus",nullable = false,length = 10)
    int tipus;

    @Column(name="objecte",length = 255)
    java.lang.String objecte;

    @Column(name="dataaudit",nullable = false,length = 29,precision = 6)
    java.sql.Timestamp dataAudit;

    @Column(name="username",length = 255)
    java.lang.String username;

    @Index(name="car_auditoria_entitatid_fk_i")
    @Column(name="entitatid",length = 19)
    java.lang.Long entitatID;



  /** Constructor Buit */
  public AuditoriaJPA() {
  }

  /** Constructor amb tots els camps  */
  public AuditoriaJPA(long auditoriaID , int tipus , java.lang.String objecte , java.sql.Timestamp dataAudit , java.lang.String username , java.lang.Long entitatID) {
    this.auditoriaID=auditoriaID;
    this.tipus=tipus;
    this.objecte=objecte;
    this.dataAudit=dataAudit;
    this.username=username;
    this.entitatID=entitatID;
}
  /** Constructor sense valors autoincrementals */
  public AuditoriaJPA(int tipus , java.lang.String objecte , java.sql.Timestamp dataAudit , java.lang.String username , java.lang.Long entitatID) {
    this.tipus=tipus;
    this.objecte=objecte;
    this.dataAudit=dataAudit;
    this.username=username;
    this.entitatID=entitatID;
}
  /** Constructor dels valors Not Null */
  public AuditoriaJPA(long auditoriaID , int tipus , java.sql.Timestamp dataAudit) {
    this.auditoriaID=auditoriaID;
    this.tipus=tipus;
    this.dataAudit=dataAudit;
}
  public AuditoriaJPA(Auditoria __bean) {
    this.setAuditoriaID(__bean.getAuditoriaID());
    this.setTipus(__bean.getTipus());
    this.setObjecte(__bean.getObjecte());
    this.setDataAudit(__bean.getDataAudit());
    this.setUsername(__bean.getUsername());
    this.setEntitatID(__bean.getEntitatID());
	}

	public long getAuditoriaID() {
		return(auditoriaID);
	};
	public void setAuditoriaID(long _auditoriaID_) {
		this.auditoriaID = _auditoriaID_;
	};

	public int getTipus() {
		return(tipus);
	};
	public void setTipus(int _tipus_) {
		this.tipus = _tipus_;
	};

	public java.lang.String getObjecte() {
		return(objecte);
	};
	public void setObjecte(java.lang.String _objecte_) {
		this.objecte = _objecte_;
	};

	public java.sql.Timestamp getDataAudit() {
		return(dataAudit);
	};
	public void setDataAudit(java.sql.Timestamp _dataAudit_) {
		this.dataAudit = _dataAudit_;
	};

	public java.lang.String getUsername() {
		return(username);
	};
	public void setUsername(java.lang.String _username_) {
		this.username = _username_;
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
    if (__obj != null && __obj instanceof Auditoria) {
      Auditoria __instance = (Auditoria)__obj;
      __result = true;
      __result = __result && (this.getAuditoriaID() == __instance.getAuditoriaID()) ;
    } else {
      __result = false;
    }
    return __result;
  }


 // ---------------  STATIC METHODS ------------------
  public static AuditoriaJPA toJPA(Auditoria __bean) {
    if (__bean == null) { return null;}
    AuditoriaJPA __tmp = new AuditoriaJPA();
    __tmp.setAuditoriaID(__bean.getAuditoriaID());
    __tmp.setTipus(__bean.getTipus());
    __tmp.setObjecte(__bean.getObjecte());
    __tmp.setDataAudit(__bean.getDataAudit());
    __tmp.setUsername(__bean.getUsername());
    __tmp.setEntitatID(__bean.getEntitatID());
		return __tmp;
	}


  public static AuditoriaJPA copyJPA(AuditoriaJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<AuditoriaJPA> copyJPA(java.util.Set<AuditoriaJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    @SuppressWarnings("unchecked")
    java.util.Set<AuditoriaJPA> __tmpSet = (java.util.Set<AuditoriaJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<AuditoriaJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (AuditoriaJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static AuditoriaJPA copyJPA(AuditoriaJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    AuditoriaJPA __tmp = (AuditoriaJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    // Copia de beans complexes (IMP)

    return __tmp;
  }




}
