
package es.caib.carpeta.jpa;
import es.caib.carpeta.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.Set;
import java.util.HashSet;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import org.hibernate.annotations.Index;
import javax.persistence.SequenceGenerator;
import javax.persistence.FetchType;
import javax.persistence.Id;


@SuppressWarnings("deprecation")
@Entity
@Table(name = "car_usuari" )
@SequenceGenerator(name="CARPETA_SEQ", sequenceName="car_carpeta_seq", allocationSize=1)
@javax.xml.bind.annotation.XmlRootElement
public class UsuariJPA implements Usuari {



private static final long serialVersionUID = -1105822054L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="CARPETA_SEQ")
	@Index(name="car_usuari_pk_i")
	@Column(name="usuariid",nullable = false,length = 19)
	long usuariID;

	@Column(name="username",nullable = false,length = 255)
	java.lang.String username;

	@Column(name="nom",nullable = false,length = 255)
	java.lang.String nom;

	@Column(name="llinatge1",nullable = false,length = 255)
	java.lang.String llinatge1;

	@Column(name="llinatge2",length = 255)
	java.lang.String llinatge2;

	@Column(name="email",length = 255)
	java.lang.String email;

	@Column(name="nif",length = 255)
	java.lang.String nif;

	@Column(name="idioma",nullable = false,length = 50)
	java.lang.String idioma;

	@Index(name="car_usuari_darreraentitat_fk_i")
	@Column(name="darreraentitat",length = 19)
	java.lang.Long darreraEntitat;



  /** Constructor Buit */
  public UsuariJPA() {
  }

  /** Constructor amb tots els camps  */
  public UsuariJPA(long usuariID , java.lang.String username , java.lang.String nom , java.lang.String llinatge1 , java.lang.String llinatge2 , java.lang.String email , java.lang.String nif , java.lang.String idioma , java.lang.Long darreraEntitat) {
    this.usuariID=usuariID;
    this.username=username;
    this.nom=nom;
    this.llinatge1=llinatge1;
    this.llinatge2=llinatge2;
    this.email=email;
    this.nif=nif;
    this.idioma=idioma;
    this.darreraEntitat=darreraEntitat;
}
  /** Constructor sense valors autoincrementals */
  public UsuariJPA(java.lang.String username , java.lang.String nom , java.lang.String llinatge1 , java.lang.String llinatge2 , java.lang.String email , java.lang.String nif , java.lang.String idioma , java.lang.Long darreraEntitat) {
    this.username=username;
    this.nom=nom;
    this.llinatge1=llinatge1;
    this.llinatge2=llinatge2;
    this.email=email;
    this.nif=nif;
    this.idioma=idioma;
    this.darreraEntitat=darreraEntitat;
}
  /** Constructor dels valors Not Null */
  public UsuariJPA(long usuariID , java.lang.String username , java.lang.String nom , java.lang.String llinatge1 , java.lang.String idioma) {
    this.usuariID=usuariID;
    this.username=username;
    this.nom=nom;
    this.llinatge1=llinatge1;
    this.idioma=idioma;
}
  public UsuariJPA(Usuari __bean) {
    this.setUsuariID(__bean.getUsuariID());
    this.setUsername(__bean.getUsername());
    this.setNom(__bean.getNom());
    this.setLlinatge1(__bean.getLlinatge1());
    this.setLlinatge2(__bean.getLlinatge2());
    this.setEmail(__bean.getEmail());
    this.setNif(__bean.getNif());
    this.setIdioma(__bean.getIdioma());
    this.setDarreraEntitat(__bean.getDarreraEntitat());
	}

	public long getUsuariID() {
		return(usuariID);
	};
	public void setUsuariID(long _usuariID_) {
		this.usuariID = _usuariID_;
	};

	public java.lang.String getUsername() {
		return(username);
	};
	public void setUsername(java.lang.String _username_) {
		this.username = _username_;
	};

	public java.lang.String getNom() {
		return(nom);
	};
	public void setNom(java.lang.String _nom_) {
		this.nom = _nom_;
	};

	public java.lang.String getLlinatge1() {
		return(llinatge1);
	};
	public void setLlinatge1(java.lang.String _llinatge1_) {
		this.llinatge1 = _llinatge1_;
	};

	public java.lang.String getLlinatge2() {
		return(llinatge2);
	};
	public void setLlinatge2(java.lang.String _llinatge2_) {
		this.llinatge2 = _llinatge2_;
	};

	public java.lang.String getEmail() {
		return(email);
	};
	public void setEmail(java.lang.String _email_) {
		this.email = _email_;
	};

	public java.lang.String getNif() {
		return(nif);
	};
	public void setNif(java.lang.String _nif_) {
		this.nif = _nif_;
	};

	public java.lang.String getIdioma() {
		return(idioma);
	};
	public void setIdioma(java.lang.String _idioma_) {
		this.idioma = _idioma_;
	};

	public java.lang.Long getDarreraEntitat() {
		return(darreraEntitat);
	};
	public void setDarreraEntitat(java.lang.Long _darreraEntitat_) {
		this.darreraEntitat = _darreraEntitat_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof Usuari) {
      Usuari __instance = (Usuari)__obj;
      __result = true;
      __result = __result && (this.getUsuariID() == __instance.getUsuariID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// EXP  Field:usuariid | Table: car_auditoria | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuari")
	private Set<AuditoriaJPA> auditorias = new HashSet<AuditoriaJPA>(0);
	public  Set<AuditoriaJPA> getAuditorias() {
    return this.auditorias;
  }

	public void setAuditorias(Set<AuditoriaJPA> auditorias) {
	  this.auditorias = auditorias;
	}


// EXP  Field:usuariid | Table: car_usuarientitat | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuari")
	private Set<UsuariEntitatJPA> usuariEntitats = new HashSet<UsuariEntitatJPA>(0);
	public  Set<UsuariEntitatJPA> getUsuariEntitats() {
    return this.usuariEntitats;
  }

	public void setUsuariEntitats(Set<UsuariEntitatJPA> usuariEntitats) {
	  this.usuariEntitats = usuariEntitats;
	}


// IMP Field:entitatid | Table: car_entitat | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="car_usuari_entitat_last_fk")
	@JoinColumn(name = "darreraentitat", referencedColumnName ="entitatID", nullable = true, insertable=false, updatable=false)
	private EntitatJPA entitat;

	public EntitatJPA getEntitat() {
    return this.entitat;
  }

	public  void setEntitat(EntitatJPA entitat) {
    this.entitat = entitat;
  }


 // ---------------  STATIC METHODS ------------------
  public static UsuariJPA toJPA(Usuari __bean) {
    if (__bean == null) { return null;}
    UsuariJPA __tmp = new UsuariJPA();
    __tmp.setUsuariID(__bean.getUsuariID());
    __tmp.setUsername(__bean.getUsername());
    __tmp.setNom(__bean.getNom());
    __tmp.setLlinatge1(__bean.getLlinatge1());
    __tmp.setLlinatge2(__bean.getLlinatge2());
    __tmp.setEmail(__bean.getEmail());
    __tmp.setNif(__bean.getNif());
    __tmp.setIdioma(__bean.getIdioma());
    __tmp.setDarreraEntitat(__bean.getDarreraEntitat());
		return __tmp;
	}


  public static UsuariJPA copyJPA(UsuariJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<UsuariJPA> copyJPA(java.util.Set<UsuariJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    @SuppressWarnings("unchecked")
    java.util.Set<UsuariJPA> __tmpSet = (java.util.Set<UsuariJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<UsuariJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (UsuariJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static UsuariJPA copyJPA(UsuariJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    UsuariJPA __tmp = (UsuariJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    if(!"UsuariEntitatJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.usuariEntitats) || org.hibernate.Hibernate.isInitialized(__jpa.getUsuariEntitats())) ) {
      __tmp.setUsuariEntitats(UsuariEntitatJPA.copyJPA(__jpa.getUsuariEntitats(), __alreadyCopied,"UsuariJPA"));
    }
    if(!"AuditoriaJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.auditorias) || org.hibernate.Hibernate.isInitialized(__jpa.getAuditorias())) ) {
      __tmp.setAuditorias(AuditoriaJPA.copyJPA(__jpa.getAuditorias(), __alreadyCopied,"UsuariJPA"));
    }
    // Copia de beans complexes (IMP)
    if(!"EntitatJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.entitat) || org.hibernate.Hibernate.isInitialized(__jpa.getEntitat()) ) ) {
      __tmp.setEntitat(EntitatJPA.copyJPA(__jpa.getEntitat(), __alreadyCopied,"UsuariJPA"));
    }

    return __tmp;
  }




}
