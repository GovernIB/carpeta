
package es.caib.carpeta.persistence;
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
@SequenceGenerator(name="ENLLAZ_SEQ", sequenceName="car_enllaz_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class EnllazJPA implements Enllaz {



private static final long serialVersionUID = -1473310087L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ENLLAZ_SEQ")
    @Index(name="car_enllaz_pk_i")
    @Column(name="enllazid",nullable = false,length = 19)
    long enllazID;

    @Column(name="tipus",nullable = false,length = 10)
    int tipus;

    @Index(name="car_enllaz_nomid_fk_i")
    @Column(name="nomid",nullable = false,length = 19)
    long nomID;

    @Index(name="car_enllaz_descripcioid_fk_i")
    @Column(name="descripcioid",length = 19)
    java.lang.Long descripcioID;

    @Index(name="car_enllaz_urlid_fk_i")
    @Column(name="urlid",nullable = false,length = 19)
    long urlID;

    @Index(name="car_enllaz_entitatid_fk_i")
    @Column(name="entitatid",nullable = false,length = 19)
    long entitatID;

    @Index(name="car_enllaz_logoid_fk_i")
    @Column(name="logoid",nullable = false,length = 19)
    long logoID;

    @Index(name="car_enllaz_seccioid_fk_i")
    @Column(name="seccioid",length = 19)
    java.lang.Long seccioID;

    @Column(name="actiu",nullable = false,length = 1)
    boolean actiu;

    @Column(name="ordre",nullable = false,length = 10)
    int ordre;



  /** Constructor Buit */
  public EnllazJPA() {
  }

  /** Constructor amb tots els camps  */
  public EnllazJPA(long enllazID , int tipus , long nomID , java.lang.Long descripcioID , long urlID , long entitatID , long logoID , java.lang.Long seccioID , boolean actiu , int ordre) {
    this.enllazID=enllazID;
    this.tipus=tipus;
    this.nomID=nomID;
    this.descripcioID=descripcioID;
    this.urlID=urlID;
    this.entitatID=entitatID;
    this.logoID=logoID;
    this.seccioID=seccioID;
    this.actiu=actiu;
    this.ordre=ordre;
}
  /** Constructor sense valors autoincrementals */
  public EnllazJPA(int tipus , long nomID , java.lang.Long descripcioID , long urlID , long entitatID , long logoID , java.lang.Long seccioID , boolean actiu , int ordre) {
    this.tipus=tipus;
    this.nomID=nomID;
    this.descripcioID=descripcioID;
    this.urlID=urlID;
    this.entitatID=entitatID;
    this.logoID=logoID;
    this.seccioID=seccioID;
    this.actiu=actiu;
    this.ordre=ordre;
}
  /** Constructor dels valors Not Null */
  public EnllazJPA(long enllazID , int tipus , long nomID , long urlID , long entitatID , long logoID , boolean actiu , int ordre) {
    this.enllazID=enllazID;
    this.tipus=tipus;
    this.nomID=nomID;
    this.urlID=urlID;
    this.entitatID=entitatID;
    this.logoID=logoID;
    this.actiu=actiu;
    this.ordre=ordre;
}
  public EnllazJPA(Enllaz __bean) {
    this.setEnllazID(__bean.getEnllazID());
    this.setTipus(__bean.getTipus());
    this.setNomID(__bean.getNomID());
    this.setDescripcioID(__bean.getDescripcioID());
    this.setUrlID(__bean.getUrlID());
    this.setEntitatID(__bean.getEntitatID());
    this.setLogoID(__bean.getLogoID());
    this.setSeccioID(__bean.getSeccioID());
    this.setActiu(__bean.isActiu());
    this.setOrdre(__bean.getOrdre());
    // Fitxer
    this.setLogo(FitxerJPA.toJPA(__bean.getLogo()));
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

	public java.lang.Long getDescripcioID() {
		return(descripcioID);
	};
	public void setDescripcioID(java.lang.Long _descripcioID_) {
		this.descripcioID = _descripcioID_;
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

	public long getLogoID() {
		return(logoID);
	};
	public void setLogoID(long _logoID_) {
		this.logoID = _logoID_;
	};

	public java.lang.Long getSeccioID() {
		return(seccioID);
	};
	public void setSeccioID(java.lang.Long _seccioID_) {
		this.seccioID = _seccioID_;
	};

	public boolean isActiu() {
		return(actiu);
	};
	public void setActiu(boolean _actiu_) {
		this.actiu = _actiu_;
	};

	public int getOrdre() {
		return(ordre);
	};
	public void setOrdre(int _ordre_) {
		this.ordre = _ordre_;
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
  public java.util.Map<String, es.caib.carpeta.persistence.TraduccioMapJPA> getNomTraduccions() {
    return this.nom.getTraduccions();
  }

  public void setNomTraduccions(java.util.Map<String, es.caib.carpeta.persistence.TraduccioMapJPA> __traduccions__) {
    this.nom.setTraduccions(__traduccions__);
  }


// IMP Field:traduccioid | Table: car_traduccio | Type: 1  

    @ManyToOne(fetch = FetchType.EAGER, cascade=javax.persistence.CascadeType.ALL)
    @ForeignKey(name="car_enllaz_traduccio_desid_fk")
    @JoinColumn(name = "descripcioid", referencedColumnName ="traduccioID", nullable = true, insertable=false, updatable=false)
    private TraduccioJPA descripcio;

    public TraduccioJPA getDescripcio() {
    return this.descripcio;
  }

    public  void setDescripcio(TraduccioJPA descripcio) {
    this.descripcio = descripcio;
  }

  @javax.xml.bind.annotation.XmlTransient
  public java.util.Map<String, es.caib.carpeta.persistence.TraduccioMapJPA> getDescripcioTraduccions() {
    return this.descripcio.getTraduccions();
  }

  public void setDescripcioTraduccions(java.util.Map<String, es.caib.carpeta.persistence.TraduccioMapJPA> __traduccions__) {
    this.descripcio.setTraduccions(__traduccions__);
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
  public java.util.Map<String, es.caib.carpeta.persistence.TraduccioMapJPA> getUrlTraduccions() {
    return this.url.getTraduccions();
  }

  public void setUrlTraduccions(java.util.Map<String, es.caib.carpeta.persistence.TraduccioMapJPA> __traduccions__) {
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

// IMP Field:fitxerid | Table: car_fitxer | Type: 1  

    @ManyToOne(fetch = FetchType.EAGER)
    @ForeignKey(name="car_enllaz_fitxer_logo_fk")
    @JoinColumn(name = "logoid", referencedColumnName ="fitxerID", nullable = false, insertable=false, updatable=false)
    private FitxerJPA logo;

    public FitxerJPA getLogo() {
    return this.logo;
  }

    public  void setLogo(FitxerJPA logo) {
    this.logo = logo;
  }

// IMP Field:seccioid | Table: car_seccio | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @ForeignKey(name="car_enllaz_seccio_sec_fk")
    @JoinColumn(name = "seccioid", referencedColumnName ="seccioID", nullable = true, insertable=false, updatable=false)
    private SeccioJPA seccio;

    public SeccioJPA getSeccio() {
    return this.seccio;
  }

    public  void setSeccio(SeccioJPA seccio) {
    this.seccio = seccio;
  }


 // ---------------  STATIC METHODS ------------------
  public static EnllazJPA toJPA(Enllaz __bean) {
    if (__bean == null) { return null;}
    EnllazJPA __tmp = new EnllazJPA();
    __tmp.setEnllazID(__bean.getEnllazID());
    __tmp.setTipus(__bean.getTipus());
    __tmp.setNomID(__bean.getNomID());
    __tmp.setDescripcioID(__bean.getDescripcioID());
    __tmp.setUrlID(__bean.getUrlID());
    __tmp.setEntitatID(__bean.getEntitatID());
    __tmp.setLogoID(__bean.getLogoID());
    __tmp.setSeccioID(__bean.getSeccioID());
    __tmp.setActiu(__bean.isActiu());
    __tmp.setOrdre(__bean.getOrdre());
    // Fitxer
    __tmp.setLogo(FitxerJPA.toJPA(__bean.getLogo()));
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
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.descripcio) || org.hibernate.Hibernate.isInitialized(__jpa.getDescripcio()) ) ) {
      __tmp.setDescripcio(TraduccioJPA.copyJPA(__jpa.getDescripcio(), __alreadyCopied,"EnllazJPA"));
    }
    if(!"SeccioJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.seccio) || org.hibernate.Hibernate.isInitialized(__jpa.getSeccio()) ) ) {
      __tmp.setSeccio(SeccioJPA.copyJPA(__jpa.getSeccio(), __alreadyCopied,"EnllazJPA"));
    }
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
