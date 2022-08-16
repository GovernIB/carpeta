
package es.caib.carpeta.persistence;
import es.caib.carpeta.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.Set;
import java.util.HashSet;
import javax.persistence.GenerationType;
import javax.persistence.Index;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;
import javax.persistence.FetchType;
import javax.persistence.Id;


@Entity(name = "SeccioJPA")
@Table(name = "car_seccio" , indexes = { 
        @Index(name="car_seccio_pk_i", columnList = "seccioid"),
        @Index(name="car_seccio_nomid_fk_i", columnList = "nomid"),
        @Index(name="car_seccio_descripcioid_fk_i", columnList = "descripcioid"),
        @Index(name="car_seccio_iconaid_fk_i", columnList = "iconaid"),
        @Index(name="car_seccio_entitatid_fk_i", columnList = "entitatid")})
@SequenceGenerator(name="SECCIO_SEQ", sequenceName="car_seccio_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class SeccioJPA implements Seccio {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SECCIO_SEQ")
    @Column(name="seccioid",nullable = false,length = 19)
    long seccioID;

    @Column(name="nomid",nullable = false,length = 19)
    long nomID;

    @Column(name="descripcioid",nullable = false,length = 19)
    long descripcioID;

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SECCIO_SEQ")
    @Column(name="context",nullable = false,unique = true,length = 50)
    java.lang.String contexte;

    @Column(name="activa",nullable = false,length = 1)
    boolean activa;

    @Column(name="iconaid",nullable = false,length = 19)
    long iconaID;

    @Column(name="secciopareid",length = 19)
    java.lang.Long seccioPareID;

    @Column(name="entitatid",nullable = false,length = 19)
    long entitatID;

    @Column(name="ordre",nullable = false,length = 10)
    int ordre;



  /** Constructor Buit */
  public SeccioJPA() {
  }

  /** Constructor amb tots els camps  */
  public SeccioJPA(long seccioID , long nomID , long descripcioID , java.lang.String contexte , boolean activa , long iconaID , java.lang.Long seccioPareID , long entitatID , int ordre) {
    this.seccioID=seccioID;
    this.nomID=nomID;
    this.descripcioID=descripcioID;
    this.contexte=contexte;
    this.activa=activa;
    this.iconaID=iconaID;
    this.seccioPareID=seccioPareID;
    this.entitatID=entitatID;
    this.ordre=ordre;
}
  /** Constructor sense valors autoincrementals */
  public SeccioJPA(long nomID , long descripcioID , boolean activa , long iconaID , java.lang.Long seccioPareID , long entitatID , int ordre) {
    this.nomID=nomID;
    this.descripcioID=descripcioID;
    this.activa=activa;
    this.iconaID=iconaID;
    this.seccioPareID=seccioPareID;
    this.entitatID=entitatID;
    this.ordre=ordre;
}
  /** Constructor dels valors Not Null */
  public SeccioJPA(long seccioID , long nomID , long descripcioID , java.lang.String contexte , boolean activa , long iconaID , long entitatID , int ordre) {
    this.seccioID=seccioID;
    this.nomID=nomID;
    this.descripcioID=descripcioID;
    this.contexte=contexte;
    this.activa=activa;
    this.iconaID=iconaID;
    this.entitatID=entitatID;
    this.ordre=ordre;
}
  public SeccioJPA(Seccio __bean) {
    this.setSeccioID(__bean.getSeccioID());
    this.setNomID(__bean.getNomID());
    this.setDescripcioID(__bean.getDescripcioID());
    this.setContexte(__bean.getContexte());
    this.setActiva(__bean.isActiva());
    this.setIconaID(__bean.getIconaID());
    this.setSeccioPareID(__bean.getSeccioPareID());
    this.setEntitatID(__bean.getEntitatID());
    this.setOrdre(__bean.getOrdre());
    // Fitxer
    this.setIcona(FitxerJPA.toJPA(__bean.getIcona()));
	}

	public long getSeccioID() {
		return(seccioID);
	};
	public void setSeccioID(long _seccioID_) {
		this.seccioID = _seccioID_;
	};

	public long getNomID() {
		return(nomID);
	};
	public void setNomID(long _nomID_) {
		this.nomID = _nomID_;
	};

	public long getDescripcioID() {
		return(descripcioID);
	};
	public void setDescripcioID(long _descripcioID_) {
		this.descripcioID = _descripcioID_;
	};

	public java.lang.String getContexte() {
		return(contexte);
	};
	public void setContexte(java.lang.String _contexte_) {
		this.contexte = _contexte_;
	};

	public boolean isActiva() {
		return(activa);
	};
	public void setActiva(boolean _activa_) {
		this.activa = _activa_;
	};

	public long getIconaID() {
		return(iconaID);
	};
	public void setIconaID(long _iconaID_) {
		this.iconaID = _iconaID_;
	};

	public java.lang.Long getSeccioPareID() {
		return(seccioPareID);
	};
	public void setSeccioPareID(java.lang.Long _seccioPareID_) {
		this.seccioPareID = _seccioPareID_;
	};

	public long getEntitatID() {
		return(entitatID);
	};
	public void setEntitatID(long _entitatID_) {
		this.entitatID = _entitatID_;
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
    if (__obj != null && __obj instanceof Seccio) {
      Seccio __instance = (Seccio)__obj;
      __result = true;
      __result = __result && (this.getSeccioID() == __instance.getSeccioID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// EXP  Field:seccioid | Table: car_enllaz | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "seccio")
    private Set<EnllazJPA> enllazs = new HashSet<EnllazJPA>(0);
    public  Set<EnllazJPA> getEnllazs() {
    return this.enllazs;
  }

    public void setEnllazs(Set<EnllazJPA> enllazs) {
      this.enllazs = enllazs;
    }


// EXP  Field:seccioid | Table: car_pluginentitat | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "seccio")
    private Set<PluginEntitatJPA> pluginEntitats = new HashSet<PluginEntitatJPA>(0);
    public  Set<PluginEntitatJPA> getPluginEntitats() {
    return this.pluginEntitats;
  }

    public void setPluginEntitats(Set<PluginEntitatJPA> pluginEntitats) {
      this.pluginEntitats = pluginEntitats;
    }


// IMP Field:traduccioid | Table: car_traduccio | Type: 1  

    @ManyToOne(fetch = FetchType.EAGER, cascade=javax.persistence.CascadeType.ALL)
    @JoinColumn(name = "nomid", referencedColumnName ="traduccioID", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="car_seccio_traduccio_nom_fk"))
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
    @JoinColumn(name = "descripcioid", referencedColumnName ="traduccioID", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="car_seccio_traduccio_des_fk"))
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


// IMP Field:fitxerid | Table: car_fitxer | Type: 1  

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "iconaid", referencedColumnName ="fitxerID", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="car_seccio_fitxer_icon_fk"))
    private FitxerJPA icona;

    public FitxerJPA getIcona() {
    return this.icona;
  }

    public  void setIcona(FitxerJPA icona) {
    this.icona = icona;
  }

// IMP Field:entitatid | Table: car_entitat | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entitatid", referencedColumnName ="entitatID", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="car_seccio_entitat_ent_fk"))
    private EntitatJPA entitat;

    public EntitatJPA getEntitat() {
    return this.entitat;
  }

    public  void setEntitat(EntitatJPA entitat) {
    this.entitat = entitat;
  }


 // ---------------  STATIC METHODS ------------------
  public static SeccioJPA toJPA(Seccio __bean) {
    if (__bean == null) { return null;}
    SeccioJPA __tmp = new SeccioJPA();
    __tmp.setSeccioID(__bean.getSeccioID());
    __tmp.setNomID(__bean.getNomID());
    __tmp.setDescripcioID(__bean.getDescripcioID());
    __tmp.setContexte(__bean.getContexte());
    __tmp.setActiva(__bean.isActiva());
    __tmp.setIconaID(__bean.getIconaID());
    __tmp.setSeccioPareID(__bean.getSeccioPareID());
    __tmp.setEntitatID(__bean.getEntitatID());
    __tmp.setOrdre(__bean.getOrdre());
    // Fitxer
    __tmp.setIcona(FitxerJPA.toJPA(__bean.getIcona()));
		return __tmp;
	}


  public static SeccioJPA copyJPA(SeccioJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<SeccioJPA> copyJPA(java.util.Set<SeccioJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<SeccioJPA> __tmpSet = (java.util.Set<SeccioJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<SeccioJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (SeccioJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static SeccioJPA copyJPA(SeccioJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    SeccioJPA __tmp = (SeccioJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    if(!"PluginEntitatJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.pluginEntitats) || org.hibernate.Hibernate.isInitialized(__jpa.getPluginEntitats())) ) {
      __tmp.setPluginEntitats(PluginEntitatJPA.copyJPA(__jpa.getPluginEntitats(), __alreadyCopied,"SeccioJPA"));
    }
    if(!"EnllazJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.enllazs) || org.hibernate.Hibernate.isInitialized(__jpa.getEnllazs())) ) {
      __tmp.setEnllazs(EnllazJPA.copyJPA(__jpa.getEnllazs(), __alreadyCopied,"SeccioJPA"));
    }
    // Copia de beans complexes (IMP)
    if(!"TraduccioJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.descripcio) || org.hibernate.Hibernate.isInitialized(__jpa.getDescripcio()) ) ) {
      __tmp.setDescripcio(TraduccioJPA.copyJPA(__jpa.getDescripcio(), __alreadyCopied,"SeccioJPA"));
    }
    if(!"TraduccioJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.nom) || org.hibernate.Hibernate.isInitialized(__jpa.getNom()) ) ) {
      __tmp.setNom(TraduccioJPA.copyJPA(__jpa.getNom(), __alreadyCopied,"SeccioJPA"));
    }
    if(!"EntitatJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.entitat) || org.hibernate.Hibernate.isInitialized(__jpa.getEntitat()) ) ) {
      __tmp.setEntitat(EntitatJPA.copyJPA(__jpa.getEntitat(), __alreadyCopied,"SeccioJPA"));
    }

    return __tmp;
  }




}
