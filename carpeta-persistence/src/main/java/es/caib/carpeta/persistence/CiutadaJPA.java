
package es.caib.carpeta.persistence;
import es.caib.carpeta.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.UniqueConstraint;
import javax.persistence.GeneratedValue;
import org.hibernate.annotations.Index;
import javax.persistence.SequenceGenerator;
import javax.persistence.Id;


@Entity
@Table(name = "car_ciutada"  , uniqueConstraints = {
            @UniqueConstraint(name="car_ciutada_nif_rnif_uk", columnNames={"nif","representantnif"}) } )
@SequenceGenerator(name="CIUTADA_SEQ", sequenceName="car_ciutada_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class CiutadaJPA implements Ciutada {



private static final long serialVersionUID = -591726526L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="CIUTADA_SEQ")
    @Index(name="car_ciutada_pk_i")
    @Column(name="ciutadaid",nullable = false,length = 19)
    long ciutadaID;

    @Column(name="nif",nullable = false,length = 100)
    java.lang.String nif;

    @Column(name="llinatge1",length = 255)
    java.lang.String llinatge1;

    @Column(name="llinatge2",length = 255)
    java.lang.String llinatge2;

    @Column(name="nom",length = 255)
    java.lang.String nom;

    @Column(name="empresa",nullable = false,length = 1)
    boolean empresa;

    @Column(name="representantnif",length = 100)
    java.lang.String representantNif;

    @Column(name="representantllinatge1",length = 255)
    java.lang.String representantLlinatge1;

    @Column(name="representantllinatge2",length = 255)
    java.lang.String representantLlinatge2;

    @Column(name="representantnom",length = 255)
    java.lang.String representantNom;

    @Column(name="datacreacio",nullable = false,length = 29,precision = 6)
    java.sql.Timestamp dataCreacio;

    @Column(name="mobileid",length = 255)
    java.lang.String mobileId;



  /** Constructor Buit */
  public CiutadaJPA() {
  }

  /** Constructor amb tots els camps  */
  public CiutadaJPA(long ciutadaID , java.lang.String nif , java.lang.String llinatge1 , java.lang.String llinatge2 , java.lang.String nom , boolean empresa , java.lang.String representantNif , java.lang.String representantLlinatge1 , java.lang.String representantLlinatge2 , java.lang.String representantNom , java.sql.Timestamp dataCreacio , java.lang.String mobileId) {
    this.ciutadaID=ciutadaID;
    this.nif=nif;
    this.llinatge1=llinatge1;
    this.llinatge2=llinatge2;
    this.nom=nom;
    this.empresa=empresa;
    this.representantNif=representantNif;
    this.representantLlinatge1=representantLlinatge1;
    this.representantLlinatge2=representantLlinatge2;
    this.representantNom=representantNom;
    this.dataCreacio=dataCreacio;
    this.mobileId=mobileId;
}
  /** Constructor sense valors autoincrementals */
  public CiutadaJPA(java.lang.String nif , java.lang.String llinatge1 , java.lang.String llinatge2 , java.lang.String nom , boolean empresa , java.lang.String representantNif , java.lang.String representantLlinatge1 , java.lang.String representantLlinatge2 , java.lang.String representantNom , java.sql.Timestamp dataCreacio , java.lang.String mobileId) {
    this.nif=nif;
    this.llinatge1=llinatge1;
    this.llinatge2=llinatge2;
    this.nom=nom;
    this.empresa=empresa;
    this.representantNif=representantNif;
    this.representantLlinatge1=representantLlinatge1;
    this.representantLlinatge2=representantLlinatge2;
    this.representantNom=representantNom;
    this.dataCreacio=dataCreacio;
    this.mobileId=mobileId;
}
  /** Constructor dels valors Not Null */
  public CiutadaJPA(long ciutadaID , java.lang.String nif , boolean empresa , java.sql.Timestamp dataCreacio) {
    this.ciutadaID=ciutadaID;
    this.nif=nif;
    this.empresa=empresa;
    this.dataCreacio=dataCreacio;
}
  public CiutadaJPA(Ciutada __bean) {
    this.setCiutadaID(__bean.getCiutadaID());
    this.setNif(__bean.getNif());
    this.setLlinatge1(__bean.getLlinatge1());
    this.setLlinatge2(__bean.getLlinatge2());
    this.setNom(__bean.getNom());
    this.setEmpresa(__bean.isEmpresa());
    this.setRepresentantNif(__bean.getRepresentantNif());
    this.setRepresentantLlinatge1(__bean.getRepresentantLlinatge1());
    this.setRepresentantLlinatge2(__bean.getRepresentantLlinatge2());
    this.setRepresentantNom(__bean.getRepresentantNom());
    this.setDataCreacio(__bean.getDataCreacio());
    this.setMobileId(__bean.getMobileId());
	}

	public long getCiutadaID() {
		return(ciutadaID);
	};
	public void setCiutadaID(long _ciutadaID_) {
		this.ciutadaID = _ciutadaID_;
	};

	public java.lang.String getNif() {
		return(nif);
	};
	public void setNif(java.lang.String _nif_) {
		this.nif = _nif_;
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

	public java.lang.String getNom() {
		return(nom);
	};
	public void setNom(java.lang.String _nom_) {
		this.nom = _nom_;
	};

	public boolean isEmpresa() {
		return(empresa);
	};
	public void setEmpresa(boolean _empresa_) {
		this.empresa = _empresa_;
	};

	public java.lang.String getRepresentantNif() {
		return(representantNif);
	};
	public void setRepresentantNif(java.lang.String _representantNif_) {
		this.representantNif = _representantNif_;
	};

	public java.lang.String getRepresentantLlinatge1() {
		return(representantLlinatge1);
	};
	public void setRepresentantLlinatge1(java.lang.String _representantLlinatge1_) {
		this.representantLlinatge1 = _representantLlinatge1_;
	};

	public java.lang.String getRepresentantLlinatge2() {
		return(representantLlinatge2);
	};
	public void setRepresentantLlinatge2(java.lang.String _representantLlinatge2_) {
		this.representantLlinatge2 = _representantLlinatge2_;
	};

	public java.lang.String getRepresentantNom() {
		return(representantNom);
	};
	public void setRepresentantNom(java.lang.String _representantNom_) {
		this.representantNom = _representantNom_;
	};

	public java.sql.Timestamp getDataCreacio() {
		return(dataCreacio);
	};
	public void setDataCreacio(java.sql.Timestamp _dataCreacio_) {
		this.dataCreacio = _dataCreacio_;
	};

	public java.lang.String getMobileId() {
		return(mobileId);
	};
	public void setMobileId(java.lang.String _mobileId_) {
		this.mobileId = _mobileId_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof Ciutada) {
      Ciutada __instance = (Ciutada)__obj;
      __result = true;
      __result = __result && (this.getCiutadaID() == __instance.getCiutadaID()) ;
    } else {
      __result = false;
    }
    return __result;
  }


 // ---------------  STATIC METHODS ------------------
  public static CiutadaJPA toJPA(Ciutada __bean) {
    if (__bean == null) { return null;}
    CiutadaJPA __tmp = new CiutadaJPA();
    __tmp.setCiutadaID(__bean.getCiutadaID());
    __tmp.setNif(__bean.getNif());
    __tmp.setLlinatge1(__bean.getLlinatge1());
    __tmp.setLlinatge2(__bean.getLlinatge2());
    __tmp.setNom(__bean.getNom());
    __tmp.setEmpresa(__bean.isEmpresa());
    __tmp.setRepresentantNif(__bean.getRepresentantNif());
    __tmp.setRepresentantLlinatge1(__bean.getRepresentantLlinatge1());
    __tmp.setRepresentantLlinatge2(__bean.getRepresentantLlinatge2());
    __tmp.setRepresentantNom(__bean.getRepresentantNom());
    __tmp.setDataCreacio(__bean.getDataCreacio());
    __tmp.setMobileId(__bean.getMobileId());
		return __tmp;
	}


  public static CiutadaJPA copyJPA(CiutadaJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<CiutadaJPA> copyJPA(java.util.Set<CiutadaJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<CiutadaJPA> __tmpSet = (java.util.Set<CiutadaJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<CiutadaJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (CiutadaJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static CiutadaJPA copyJPA(CiutadaJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    CiutadaJPA __tmp = (CiutadaJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    // Copia de beans complexes (IMP)

    return __tmp;
  }




}
