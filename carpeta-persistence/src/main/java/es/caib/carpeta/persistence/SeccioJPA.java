
package es.caib.carpeta.persistence;
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
@Table(name = "car_seccio" )
@SequenceGenerator(name="SECCIO_SEQ", sequenceName="car_seccio_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class SeccioJPA implements Seccio {



private static final long serialVersionUID = 838506947L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SECCIO_SEQ")
	@Index(name="car_seccio_pk_i")
	@Column(name="seccioid",nullable = false,length = 19)
	long seccioID;

	@Index(name="car_seccio_nomid_fk_i")
	@Column(name="nomid",nullable = false,length = 19)
	long nomID;

	@Index(name="car_seccio_descripcioid_fk_i")
	@Column(name="descripcioid",nullable = false,length = 19)
	long descripcioID;

	@Column(name="activa",nullable = false,length = 1)
	boolean activa;

	@Index(name="car_seccio_iconaid_fk_i")
	@Column(name="iconaid",nullable = false,length = 19)
	long iconaID;

	@Column(name="secciopareid",length = 19)
	java.lang.Long seccioPareID;



  /** Constructor Buit */
  public SeccioJPA() {
  }

  /** Constructor amb tots els camps  */
  public SeccioJPA(long seccioID , long nomID , long descripcioID , boolean activa , long iconaID , java.lang.Long seccioPareID) {
    this.seccioID=seccioID;
    this.nomID=nomID;
    this.descripcioID=descripcioID;
    this.activa=activa;
    this.iconaID=iconaID;
    this.seccioPareID=seccioPareID;
}
  /** Constructor sense valors autoincrementals */
  public SeccioJPA(long nomID , long descripcioID , boolean activa , long iconaID , java.lang.Long seccioPareID) {
    this.nomID=nomID;
    this.descripcioID=descripcioID;
    this.activa=activa;
    this.iconaID=iconaID;
    this.seccioPareID=seccioPareID;
}
  public SeccioJPA(Seccio __bean) {
    this.setSeccioID(__bean.getSeccioID());
    this.setNomID(__bean.getNomID());
    this.setDescripcioID(__bean.getDescripcioID());
    this.setActiva(__bean.isActiva());
    this.setIconaID(__bean.getIconaID());
    this.setSeccioPareID(__bean.getSeccioPareID());
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


// EXP  Field:seccioid | Table: car_plugin | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "seccio")
	private Set<PluginJPA> plugins = new HashSet<PluginJPA>(0);
	public  Set<PluginJPA> getPlugins() {
    return this.plugins;
  }

	public void setPlugins(Set<PluginJPA> plugins) {
	  this.plugins = plugins;
	}


// IMP Field:traduccioid | Table: car_traduccio | Type: 1  

	@ManyToOne(fetch = FetchType.EAGER, cascade=javax.persistence.CascadeType.ALL)
	@ForeignKey(name="car_seccio_traduccio_nom_fk")
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
	@ForeignKey(name="car_seccio_traduccio_des_fk")
	@JoinColumn(name = "descripcioid", referencedColumnName ="traduccioID", nullable = false, insertable=false, updatable=false)
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
	@ForeignKey(name="car_seccio_fitxer_icon_fk")
	@JoinColumn(name = "iconaid", referencedColumnName ="fitxerID", nullable = false, insertable=false, updatable=false)
	private FitxerJPA icona;

	public FitxerJPA getIcona() {
    return this.icona;
  }

	public  void setIcona(FitxerJPA icona) {
    this.icona = icona;
  }


 // ---------------  STATIC METHODS ------------------
  public static SeccioJPA toJPA(Seccio __bean) {
    if (__bean == null) { return null;}
    SeccioJPA __tmp = new SeccioJPA();
    __tmp.setSeccioID(__bean.getSeccioID());
    __tmp.setNomID(__bean.getNomID());
    __tmp.setDescripcioID(__bean.getDescripcioID());
    __tmp.setActiva(__bean.isActiva());
    __tmp.setIconaID(__bean.getIconaID());
    __tmp.setSeccioPareID(__bean.getSeccioPareID());
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
    @SuppressWarnings("unchecked")
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
    if(!"EnllazJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.enllazs) || org.hibernate.Hibernate.isInitialized(__jpa.getEnllazs())) ) {
      __tmp.setEnllazs(EnllazJPA.copyJPA(__jpa.getEnllazs(), __alreadyCopied,"SeccioJPA"));
    }
    if(!"PluginJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.plugins) || org.hibernate.Hibernate.isInitialized(__jpa.getPlugins())) ) {
      __tmp.setPlugins(PluginJPA.copyJPA(__jpa.getPlugins(), __alreadyCopied,"SeccioJPA"));
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

    return __tmp;
  }




}
