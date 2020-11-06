
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
@Table(name = "car_acces" )
@SequenceGenerator(name="ACCES_SEQ", sequenceName="car_acces_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class AccesJPA implements Acces {



private static final long serialVersionUID = -2081832820L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ACCES_SEQ")
	@Index(name="car_acces_pk_i")
	@Column(name="accesid",nullable = false,length = 19)
	long accesID;

	@Column(name="nom",length = 255)
	java.lang.String nom;

	@Column(name="llinatges",length = 255)
	java.lang.String llinatges;

	@Column(name="nif",length = 50)
	java.lang.String nif;

	@Column(name="ip",length = 100)
	java.lang.String ip;

	@Column(name="proveidoridentitat",length = 255)
	java.lang.String proveidorIdentitat;

	@Column(name="nivellseguretat",length = 255)
	java.lang.String nivellSeguretat;

	@Column(name="resultatautenticacio",length = 10)
	java.lang.Integer resultatAutenticacio;

	@Column(name="datadarreracces",length = 29,precision = 6)
	java.sql.Timestamp dataDarrerAcces;

  /** Hauria d'estar enllaçat amb la taula idioma */
	@Column(name="idioma",length = 50)
	java.lang.String idioma;

	@Index(name="car_acces_entitatid_fk_i")
	@Column(name="entitatid",nullable = false,length = 19)
	long entitatID;



  /** Constructor Buit */
  public AccesJPA() {
  }

  /** Constructor amb tots els camps  */
  public AccesJPA(long accesID , java.lang.String nom , java.lang.String llinatges , java.lang.String nif , java.lang.String ip , java.lang.String proveidorIdentitat , java.lang.String nivellSeguretat , java.lang.Integer resultatAutenticacio , java.sql.Timestamp dataDarrerAcces , java.lang.String idioma , long entitatID) {
    this.accesID=accesID;
    this.nom=nom;
    this.llinatges=llinatges;
    this.nif=nif;
    this.ip=ip;
    this.proveidorIdentitat=proveidorIdentitat;
    this.nivellSeguretat=nivellSeguretat;
    this.resultatAutenticacio=resultatAutenticacio;
    this.dataDarrerAcces=dataDarrerAcces;
    this.idioma=idioma;
    this.entitatID=entitatID;
}
  /** Constructor sense valors autoincrementals */
  public AccesJPA(java.lang.String nom , java.lang.String llinatges , java.lang.String nif , java.lang.String ip , java.lang.String proveidorIdentitat , java.lang.String nivellSeguretat , java.lang.Integer resultatAutenticacio , java.sql.Timestamp dataDarrerAcces , java.lang.String idioma , long entitatID) {
    this.nom=nom;
    this.llinatges=llinatges;
    this.nif=nif;
    this.ip=ip;
    this.proveidorIdentitat=proveidorIdentitat;
    this.nivellSeguretat=nivellSeguretat;
    this.resultatAutenticacio=resultatAutenticacio;
    this.dataDarrerAcces=dataDarrerAcces;
    this.idioma=idioma;
    this.entitatID=entitatID;
}
  /** Constructor dels valors Not Null */
  public AccesJPA(long accesID , long entitatID) {
    this.accesID=accesID;
    this.entitatID=entitatID;
}
  public AccesJPA(Acces __bean) {
    this.setAccesID(__bean.getAccesID());
    this.setNom(__bean.getNom());
    this.setLlinatges(__bean.getLlinatges());
    this.setNif(__bean.getNif());
    this.setIp(__bean.getIp());
    this.setProveidorIdentitat(__bean.getProveidorIdentitat());
    this.setNivellSeguretat(__bean.getNivellSeguretat());
    this.setResultatAutenticacio(__bean.getResultatAutenticacio());
    this.setDataDarrerAcces(__bean.getDataDarrerAcces());
    this.setIdioma(__bean.getIdioma());
    this.setEntitatID(__bean.getEntitatID());
	}

	public long getAccesID() {
		return(accesID);
	};
	public void setAccesID(long _accesID_) {
		this.accesID = _accesID_;
	};

	public java.lang.String getNom() {
		return(nom);
	};
	public void setNom(java.lang.String _nom_) {
		this.nom = _nom_;
	};

	public java.lang.String getLlinatges() {
		return(llinatges);
	};
	public void setLlinatges(java.lang.String _llinatges_) {
		this.llinatges = _llinatges_;
	};

	public java.lang.String getNif() {
		return(nif);
	};
	public void setNif(java.lang.String _nif_) {
		this.nif = _nif_;
	};

	public java.lang.String getIp() {
		return(ip);
	};
	public void setIp(java.lang.String _ip_) {
		this.ip = _ip_;
	};

	public java.lang.String getProveidorIdentitat() {
		return(proveidorIdentitat);
	};
	public void setProveidorIdentitat(java.lang.String _proveidorIdentitat_) {
		this.proveidorIdentitat = _proveidorIdentitat_;
	};

	public java.lang.String getNivellSeguretat() {
		return(nivellSeguretat);
	};
	public void setNivellSeguretat(java.lang.String _nivellSeguretat_) {
		this.nivellSeguretat = _nivellSeguretat_;
	};

	public java.lang.Integer getResultatAutenticacio() {
		return(resultatAutenticacio);
	};
	public void setResultatAutenticacio(java.lang.Integer _resultatAutenticacio_) {
		this.resultatAutenticacio = _resultatAutenticacio_;
	};

	public java.sql.Timestamp getDataDarrerAcces() {
		return(dataDarrerAcces);
	};
	public void setDataDarrerAcces(java.sql.Timestamp _dataDarrerAcces_) {
		this.dataDarrerAcces = _dataDarrerAcces_;
	};

	public java.lang.String getIdioma() {
		return(idioma);
	};
	public void setIdioma(java.lang.String _idioma_) {
		this.idioma = _idioma_;
	};

	public long getEntitatID() {
		return(entitatID);
	};
	public void setEntitatID(long _entitatID_) {
		this.entitatID = _entitatID_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof Acces) {
      Acces __instance = (Acces)__obj;
      __result = true;
      __result = __result && (this.getAccesID() == __instance.getAccesID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// IMP Field:entitatid | Table: car_entitat | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="car_acces_entitat_entitatid_fk")
	@JoinColumn(name = "entitatid", referencedColumnName ="entitatID", nullable = false, insertable=false, updatable=false)
	private EntitatJPA entitat;

	public EntitatJPA getEntitat() {
    return this.entitat;
  }

	public  void setEntitat(EntitatJPA entitat) {
    this.entitat = entitat;
  }


 // ---------------  STATIC METHODS ------------------
  public static AccesJPA toJPA(Acces __bean) {
    if (__bean == null) { return null;}
    AccesJPA __tmp = new AccesJPA();
    __tmp.setAccesID(__bean.getAccesID());
    __tmp.setNom(__bean.getNom());
    __tmp.setLlinatges(__bean.getLlinatges());
    __tmp.setNif(__bean.getNif());
    __tmp.setIp(__bean.getIp());
    __tmp.setProveidorIdentitat(__bean.getProveidorIdentitat());
    __tmp.setNivellSeguretat(__bean.getNivellSeguretat());
    __tmp.setResultatAutenticacio(__bean.getResultatAutenticacio());
    __tmp.setDataDarrerAcces(__bean.getDataDarrerAcces());
    __tmp.setIdioma(__bean.getIdioma());
    __tmp.setEntitatID(__bean.getEntitatID());
		return __tmp;
	}


  public static AccesJPA copyJPA(AccesJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<AccesJPA> copyJPA(java.util.Set<AccesJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    @SuppressWarnings("unchecked")
    java.util.Set<AccesJPA> __tmpSet = (java.util.Set<AccesJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<AccesJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (AccesJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static AccesJPA copyJPA(AccesJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    AccesJPA __tmp = (AccesJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    // Copia de beans complexes (IMP)
    if(!"EntitatJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.entitat) || org.hibernate.Hibernate.isInitialized(__jpa.getEntitat()) ) ) {
      __tmp.setEntitat(EntitatJPA.copyJPA(__jpa.getEntitat(), __alreadyCopied,"AccesJPA"));
    }

    return __tmp;
  }




}
