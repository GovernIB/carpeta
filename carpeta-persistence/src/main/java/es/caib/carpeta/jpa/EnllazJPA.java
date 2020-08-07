
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
@Table(name = "car_enllaz" )
@SequenceGenerator(name="CARPETA_SEQ", sequenceName="car_carpeta_seq", allocationSize=1)
@javax.xml.bind.annotation.XmlRootElement
public class EnllazJPA implements Enllaz {



private static final long serialVersionUID = -1473310087L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="CARPETA_SEQ")
	@Index(name="car_enllaz_pk_i")
	@Column(name="enllazid",nullable = false,length = 19)
	long enllazID;

	@Column(name="tipus",nullable = false,length = 10)
	int tipus;

	@Index(name="car_enllaz_nomid_fk_i")
	@Column(name="nomid",nullable = false,length = 19)
	long nomID;

	@Index(name="car_enllaz_urlid_fk_i")
	@Column(name="urlid",nullable = false,length = 19)
	long urlID;

	@Index(name="car_enllaz_entitatid_fk_i")
	@Column(name="entitatid",nullable = false,length = 19)
	long entitatID;



  /** Constructor Buit */
  public EnllazJPA() {
  }

  /** Constructor amb tots els camps  */
  public EnllazJPA(long enllazID , int tipus , long nomID , long urlID , long entitatID) {
    this.enllazID=enllazID;
    this.tipus=tipus;
    this.nomID=nomID;
    this.urlID=urlID;
    this.entitatID=entitatID;
}
  /** Constructor sense valors autoincrementals */
  public EnllazJPA(int tipus , long nomID , long urlID , long entitatID) {
    this.tipus=tipus;
    this.nomID=nomID;
    this.urlID=urlID;
    this.entitatID=entitatID;
}
  public EnllazJPA(Enllaz __bean) {
    this.setEnllazID(__bean.getEnllazID());
    this.setTipus(__bean.getTipus());
    this.setNomID(__bean.getNomID());
    this.setUrlID(__bean.getUrlID());
    this.setEntitatID(__bean.getEntitatID());
	}

	public long getEnllazID() {
		return(enllazID);
	};
	public void setEnllazID(long _enllazID_) {
		this.enllazID = _enllazID_;
	};

	public int getTipus() {
		return(tipus);
	};
	public void setTipus(int _tipus_) {
		this.tipus = _tipus_;
	};

	public long getNomID() {
		return(nomID);
	};
	public void setNomID(long _nomID_) {
		this.nomID = _nomID_;
	};

	public long getUrlID() {
		return(urlID);
	};
	public void setUrlID(long _urlID_) {
		this.urlID = _urlID_;
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
    if (__obj != null && __obj instanceof Enllaz) {
      Enllaz __instance = (Enllaz)__obj;
      __result = true;
      __result = __result && (this.getEnllazID() == __instance.getEnllazID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// IMP Field:traduccioid | Table: car_traduccio | Type: 1  

	@ManyToOne(fetch = FetchType.EAGER, cascade=javax.persistence.CascadeType.ALL)
	@ForeignKey(name="car_enllaz_traduccio_nomid_fk")
	@JoinColumn(name = "nomid", referencedColumnName ="traduccioID", nullable = false, insertable=false, updatable=false)
	private TraduccioJPA nom;

	public TraduccioJPA getNom() {
    return this.nom;
  }

	public  void setNom(TraduccioJPA nom) {
    this.nom = nom;
  }

  @javax.xml.bind.annotation.XmlTransient
  public java.util.Map<String, es.caib.carpeta.jpa.TraduccioMapJPA> getNomTraduccions() {
    return this.nom.getTraduccions();
  }

  public void setNomTraduccions(java.util.Map<String, es.caib.carpeta.jpa.TraduccioMapJPA> __traduccions__) {
    this.nom.setTraduccions(__traduccions__);
  }


// IMP Field:traduccioid | Table: car_traduccio | Type: 1  

	@ManyToOne(fetch = FetchType.EAGER, cascade=javax.persistence.CascadeType.ALL)
	@ForeignKey(name="car_enllaz_traduccio_urlid_fk")
	@JoinColumn(name = "urlid", referencedColumnName ="traduccioID", nullable = false, insertable=false, updatable=false)
	private TraduccioJPA url;

	public TraduccioJPA getUrl() {
    return this.url;
  }

	public  void setUrl(TraduccioJPA url) {
    this.url = url;
  }

  @javax.xml.bind.annotation.XmlTransient
  public java.util.Map<String, es.caib.carpeta.jpa.TraduccioMapJPA> getUrlTraduccions() {
    return this.url.getTraduccions();
  }

  public void setUrlTraduccions(java.util.Map<String, es.caib.carpeta.jpa.TraduccioMapJPA> __traduccions__) {
    this.url.setTraduccions(__traduccions__);
  }


// IMP Field:entitatid | Table: car_entitat | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="car_enllaz_entitat_ent_fk")
	@JoinColumn(name = "entitatid", referencedColumnName ="entitatID", nullable = false, insertable=false, updatable=false)
	private EntitatJPA entitat;

	public EntitatJPA getEntitat() {
    return this.entitat;
  }

	public  void setEntitat(EntitatJPA entitat) {
    this.entitat = entitat;
  }


 // ---------------  STATIC METHODS ------------------
  public static EnllazJPA toJPA(Enllaz __bean) {
    if (__bean == null) { return null;}
    EnllazJPA __tmp = new EnllazJPA();
    __tmp.setEnllazID(__bean.getEnllazID());
    __tmp.setTipus(__bean.getTipus());
    __tmp.setNomID(__bean.getNomID());
    __tmp.setUrlID(__bean.getUrlID());
    __tmp.setEntitatID(__bean.getEntitatID());
		return __tmp;
	}


  public static EnllazJPA copyJPA(EnllazJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<EnllazJPA> copyJPA(java.util.Set<EnllazJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    @SuppressWarnings("unchecked")
    java.util.Set<EnllazJPA> __tmpSet = (java.util.Set<EnllazJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<EnllazJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (EnllazJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static EnllazJPA copyJPA(EnllazJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    EnllazJPA __tmp = (EnllazJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    // Copia de beans complexes (IMP)
    if(!"TraduccioJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.nom) || org.hibernate.Hibernate.isInitialized(__jpa.getNom()) ) ) {
      __tmp.setNom(TraduccioJPA.copyJPA(__jpa.getNom(), __alreadyCopied,"EnllazJPA"));
    }
    if(!"TraduccioJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.url) || org.hibernate.Hibernate.isInitialized(__jpa.getUrl()) ) ) {
      __tmp.setUrl(TraduccioJPA.copyJPA(__jpa.getUrl(), __alreadyCopied,"EnllazJPA"));
    }
    if(!"EntitatJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.entitat) || org.hibernate.Hibernate.isInitialized(__jpa.getEntitat()) ) ) {
      __tmp.setEntitat(EntitatJPA.copyJPA(__jpa.getEntitat(), __alreadyCopied,"EnllazJPA"));
    }

    return __tmp;
  }




}
