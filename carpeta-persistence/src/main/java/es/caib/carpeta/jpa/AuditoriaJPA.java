
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

	@Column(name="dataaudit",nullable = false,length = 29,precision = 6)
	java.sql.Timestamp dataAudit;

	@Column(name="tipus",nullable = false,length = 10)
	int tipus;

	@Index(name="car_auditoria_usuariid_fk_i")
	@Column(name="usuariid",length = 19)
	java.lang.Long usuariID;

	@Column(name="usuariclave",length = 256)
	java.lang.String usuariClave;

	@Index(name="car_auditoria_entitatid_fk_i")
	@Column(name="entitatid",length = 19)
	java.lang.Long entitatID;

	@Column(name="pluginid",length = 10)
	java.lang.Long pluginID;



  /** Constructor Buit */
  public AuditoriaJPA() {
  }

  /** Constructor amb tots els camps  */
  public AuditoriaJPA(long auditoriaID , java.sql.Timestamp dataAudit , int tipus , java.lang.Long usuariID , java.lang.String usuariClave , java.lang.Long entitatID , java.lang.Long pluginID) {
    this.auditoriaID=auditoriaID;
    this.dataAudit=dataAudit;
    this.tipus=tipus;
    this.usuariID=usuariID;
    this.usuariClave=usuariClave;
    this.entitatID=entitatID;
    this.pluginID=pluginID;
}
  /** Constructor sense valors autoincrementals */
  public AuditoriaJPA(java.sql.Timestamp dataAudit , int tipus , java.lang.Long usuariID , java.lang.String usuariClave , java.lang.Long entitatID , java.lang.Long pluginID) {
    this.dataAudit=dataAudit;
    this.tipus=tipus;
    this.usuariID=usuariID;
    this.usuariClave=usuariClave;
    this.entitatID=entitatID;
    this.pluginID=pluginID;
}
  /** Constructor dels valors Not Null */
  public AuditoriaJPA(long auditoriaID , java.sql.Timestamp dataAudit , int tipus) {
    this.auditoriaID=auditoriaID;
    this.dataAudit=dataAudit;
    this.tipus=tipus;
}
  public AuditoriaJPA(Auditoria __bean) {
    this.setAuditoriaID(__bean.getAuditoriaID());
    this.setDataAudit(__bean.getDataAudit());
    this.setTipus(__bean.getTipus());
    this.setUsuariID(__bean.getUsuariID());
    this.setUsuariClave(__bean.getUsuariClave());
    this.setEntitatID(__bean.getEntitatID());
    this.setPluginID(__bean.getPluginID());
	}

	public long getAuditoriaID() {
		return(auditoriaID);
	};
	public void setAuditoriaID(long _auditoriaID_) {
		this.auditoriaID = _auditoriaID_;
	};

	public java.sql.Timestamp getDataAudit() {
		return(dataAudit);
	};
	public void setDataAudit(java.sql.Timestamp _dataAudit_) {
		this.dataAudit = _dataAudit_;
	};

	public int getTipus() {
		return(tipus);
	};
	public void setTipus(int _tipus_) {
		this.tipus = _tipus_;
	};

	public java.lang.Long getUsuariID() {
		return(usuariID);
	};
	public void setUsuariID(java.lang.Long _usuariID_) {
		this.usuariID = _usuariID_;
	};

	public java.lang.String getUsuariClave() {
		return(usuariClave);
	};
	public void setUsuariClave(java.lang.String _usuariClave_) {
		this.usuariClave = _usuariClave_;
	};

	public java.lang.Long getEntitatID() {
		return(entitatID);
	};
	public void setEntitatID(java.lang.Long _entitatID_) {
		this.entitatID = _entitatID_;
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
    if (__obj != null && __obj instanceof Auditoria) {
      Auditoria __instance = (Auditoria)__obj;
      __result = true;
      __result = __result && (this.getAuditoriaID() == __instance.getAuditoriaID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// IMP Field:usuariid | Table: car_usuari | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="car_audit_usuari_usu_fk")
	@JoinColumn(name = "usuariid", referencedColumnName ="usuariID", nullable = true, insertable=false, updatable=false)
	private UsuariJPA usuari;

	public UsuariJPA getUsuari() {
    return this.usuari;
  }

	public  void setUsuari(UsuariJPA usuari) {
    this.usuari = usuari;
  }


 // ---------------  STATIC METHODS ------------------
  public static AuditoriaJPA toJPA(Auditoria __bean) {
    if (__bean == null) { return null;}
    AuditoriaJPA __tmp = new AuditoriaJPA();
    __tmp.setAuditoriaID(__bean.getAuditoriaID());
    __tmp.setDataAudit(__bean.getDataAudit());
    __tmp.setTipus(__bean.getTipus());
    __tmp.setUsuariID(__bean.getUsuariID());
    __tmp.setUsuariClave(__bean.getUsuariClave());
    __tmp.setEntitatID(__bean.getEntitatID());
    __tmp.setPluginID(__bean.getPluginID());
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
    if(!"UsuariJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.usuari) || org.hibernate.Hibernate.isInitialized(__jpa.getUsuari()) ) ) {
      __tmp.setUsuari(UsuariJPA.copyJPA(__jpa.getUsuari(), __alreadyCopied,"AuditoriaJPA"));
    }

    return __tmp;
  }




}
