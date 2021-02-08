
package es.caib.carpeta.persistence;
import es.caib.carpeta.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import org.hibernate.annotations.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.Set;
import java.util.HashMap;
import org.hibernate.annotations.Cascade;
import java.util.HashSet;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import org.hibernate.annotations.Index;
import javax.persistence.SequenceGenerator;
import java.util.Map;
import javax.persistence.FetchType;
import javax.persistence.ElementCollection;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import javax.persistence.Id;


@SuppressWarnings("deprecation")
@Entity
@Table(name = "car_traduccio" )
@SequenceGenerator(name="TRADUCCIO_SEQ", sequenceName="car_traduccio_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class TraduccioJPA implements Traduccio {



private static final long serialVersionUID = -326205279L;

  /**  */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="TRADUCCIO_SEQ")
	@Index(name="car_traduccio_pk_i")
	@Column(name="traduccioid",nullable = false,length = 19)
	long traduccioID;



  /** Constructor Buit */
  public TraduccioJPA() {
  }

  /** Constructor amb tots els camps  */
  public TraduccioJPA(long traduccioID) {
    this.traduccioID=traduccioID;
}
  public TraduccioJPA(Traduccio __bean) {
    this.setTraduccioID(__bean.getTraduccioID());
	}

	public long getTraduccioID() {
		return(traduccioID);
	};
	public void setTraduccioID(long _traduccioID_) {
		this.traduccioID = _traduccioID_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof Traduccio) {
      Traduccio __instance = (Traduccio)__obj;
      __result = true;
      __result = __result && (this.getTraduccioID() == __instance.getTraduccioID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// EXP  Field:descripcioid | Table: car_avis | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "descripcioID")
	private Set<AvisJPA> aviss = new HashSet<AvisJPA>(0);
	public  Set<AvisJPA> getAviss() {
    return this.aviss;
  }

	public void setAviss(Set<AvisJPA> aviss) {
	  this.aviss = aviss;
	}


// EXP  Field:nomid | Table: car_enllaz | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "nomID")
	private Set<EnllazJPA> enllaz_nomids = new HashSet<EnllazJPA>(0);
	public  Set<EnllazJPA> getEnllaz_nomids() {
    return this.enllaz_nomids;
  }

	public void setEnllaz_nomids(Set<EnllazJPA> enllaz_nomids) {
	  this.enllaz_nomids = enllaz_nomids;
	}


// EXP  Field:urlid | Table: car_enllaz | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "urlID")
	private Set<EnllazJPA> enllaz_urlids = new HashSet<EnllazJPA>(0);
	public  Set<EnllazJPA> getEnllaz_urlids() {
    return this.enllaz_urlids;
  }

	public void setEnllaz_urlids(Set<EnllazJPA> enllaz_urlids) {
	  this.enllaz_urlids = enllaz_urlids;
	}


// EXP  Field:logintextid | Table: car_entitat | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "loginTextID")
	private Set<EntitatJPA> entitat_logintextids = new HashSet<EntitatJPA>(0);
	public  Set<EntitatJPA> getEntitat_logintextids() {
    return this.entitat_logintextids;
  }

	public void setEntitat_logintextids(Set<EntitatJPA> entitat_logintextids) {
	  this.entitat_logintextids = entitat_logintextids;
	}


// EXP  Field:nomid | Table: car_entitat | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "nomID")
	private Set<EntitatJPA> entitat_nomids = new HashSet<EntitatJPA>(0);
	public  Set<EntitatJPA> getEntitat_nomids() {
    return this.entitat_nomids;
  }

	public void setEntitat_nomids(Set<EntitatJPA> entitat_nomids) {
	  this.entitat_nomids = entitat_nomids;
	}


// EXP  Field:descripcioid | Table: car_plugin | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "descripcioID")
	private Set<PluginJPA> plugin_descripcioids = new HashSet<PluginJPA>(0);
	public  Set<PluginJPA> getPlugin_descripcioids() {
    return this.plugin_descripcioids;
  }

	public void setPlugin_descripcioids(Set<PluginJPA> plugin_descripcioids) {
	  this.plugin_descripcioids = plugin_descripcioids;
	}


// EXP  Field:nomid | Table: car_plugin | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "nomID")
	private Set<PluginJPA> plugin_nomids = new HashSet<PluginJPA>(0);
	public  Set<PluginJPA> getPlugin_nomids() {
    return this.plugin_nomids;
  }

	public void setPlugin_nomids(Set<PluginJPA> plugin_nomids) {
	  this.plugin_nomids = plugin_nomids;
	}


// EXP  Field:descripcioid | Table: car_seccio | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "descripcioID")
	private Set<SeccioJPA> seccio_descripcioids = new HashSet<SeccioJPA>(0);
	public  Set<SeccioJPA> getSeccio_descripcioids() {
    return this.seccio_descripcioids;
  }

	public void setSeccio_descripcioids(Set<SeccioJPA> seccio_descripcioids) {
	  this.seccio_descripcioids = seccio_descripcioids;
	}


// EXP  Field:nomid | Table: car_seccio | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "nomID")
	private Set<SeccioJPA> seccio_nomids = new HashSet<SeccioJPA>(0);
	public  Set<SeccioJPA> getSeccio_nomids() {
    return this.seccio_nomids;
  }

	public void setSeccio_nomids(Set<SeccioJPA> seccio_nomids) {
	  this.seccio_nomids = seccio_nomids;
	}


  @ElementCollection(fetch= FetchType.EAGER, targetClass = es.caib.carpeta.persistence.TraduccioMapJPA.class)
  @Cascade(value=org.hibernate.annotations.CascadeType.ALL)
  @LazyCollection(value= LazyCollectionOption.FALSE)
  @JoinTable(name="car_traducciomap",joinColumns={@JoinColumn(name="traducciomapid")})
  @javax.persistence.MapKeyColumn(name="idiomaid")
  @ForeignKey(name="car_traducmap_traduccio_fk") 
  private Map<String, es.caib.carpeta.persistence.TraduccioMapJPA> traduccions =  new HashMap<String, es.caib.carpeta.persistence.TraduccioMapJPA>();

  public Map<String, es.caib.carpeta.persistence.TraduccioMapJPA> getTraduccions() {
    return this.traduccions;
  }

  public void setTraduccions(Map<String, es.caib.carpeta.persistence.TraduccioMapJPA> _traduccions_) {
    this.traduccions = _traduccions_;
  }

  public java.util.Set<String> getIdiomes() {
    return this.traduccions.keySet();
  }
  
  public es.caib.carpeta.persistence.TraduccioMapJPA getTraduccio(String idioma) {
    return (es.caib.carpeta.persistence.TraduccioMapJPA) traduccions.get(idioma);
  }
  
  public void addTraduccio(String idioma, es.caib.carpeta.persistence.TraduccioMapJPA traduccio) {
    if (traduccio == null) {
      traduccions.remove(idioma);
    } else {
      traduccions.put(idioma, traduccio);
    }
  }

 // ---------------  STATIC METHODS ------------------
  public static TraduccioJPA toJPA(Traduccio __bean) {
    if (__bean == null) { return null;}
    TraduccioJPA __tmp = new TraduccioJPA();
    __tmp.setTraduccioID(__bean.getTraduccioID());
		return __tmp;
	}


  public static TraduccioJPA copyJPA(TraduccioJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<TraduccioJPA> copyJPA(java.util.Set<TraduccioJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    @SuppressWarnings("unchecked")
    java.util.Set<TraduccioJPA> __tmpSet = (java.util.Set<TraduccioJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<TraduccioJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (TraduccioJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static TraduccioJPA copyJPA(TraduccioJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    TraduccioJPA __tmp = (TraduccioJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    if(!"EntitatJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.entitat_nomids) || org.hibernate.Hibernate.isInitialized(__jpa.getEntitat_nomids())) ) {
      __tmp.setEntitat_nomids(EntitatJPA.copyJPA(__jpa.getEntitat_nomids(), __alreadyCopied,"TraduccioJPA"));
    }
    if(!"SeccioJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.seccio_nomids) || org.hibernate.Hibernate.isInitialized(__jpa.getSeccio_nomids())) ) {
      __tmp.setSeccio_nomids(SeccioJPA.copyJPA(__jpa.getSeccio_nomids(), __alreadyCopied,"TraduccioJPA"));
    }
    if(!"SeccioJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.seccio_descripcioids) || org.hibernate.Hibernate.isInitialized(__jpa.getSeccio_descripcioids())) ) {
      __tmp.setSeccio_descripcioids(SeccioJPA.copyJPA(__jpa.getSeccio_descripcioids(), __alreadyCopied,"TraduccioJPA"));
    }
    if(!"PluginJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.plugin_nomids) || org.hibernate.Hibernate.isInitialized(__jpa.getPlugin_nomids())) ) {
      __tmp.setPlugin_nomids(PluginJPA.copyJPA(__jpa.getPlugin_nomids(), __alreadyCopied,"TraduccioJPA"));
    }
    if(!"EntitatJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.entitat_logintextids) || org.hibernate.Hibernate.isInitialized(__jpa.getEntitat_logintextids())) ) {
      __tmp.setEntitat_logintextids(EntitatJPA.copyJPA(__jpa.getEntitat_logintextids(), __alreadyCopied,"TraduccioJPA"));
    }
    if(!"EnllazJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.enllaz_nomids) || org.hibernate.Hibernate.isInitialized(__jpa.getEnllaz_nomids())) ) {
      __tmp.setEnllaz_nomids(EnllazJPA.copyJPA(__jpa.getEnllaz_nomids(), __alreadyCopied,"TraduccioJPA"));
    }
    if(!"EnllazJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.enllaz_urlids) || org.hibernate.Hibernate.isInitialized(__jpa.getEnllaz_urlids())) ) {
      __tmp.setEnllaz_urlids(EnllazJPA.copyJPA(__jpa.getEnllaz_urlids(), __alreadyCopied,"TraduccioJPA"));
    }
    if(!"AvisJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.aviss) || org.hibernate.Hibernate.isInitialized(__jpa.getAviss())) ) {
      __tmp.setAviss(AvisJPA.copyJPA(__jpa.getAviss(), __alreadyCopied,"TraduccioJPA"));
    }
    if(!"PluginJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.plugin_descripcioids) || org.hibernate.Hibernate.isInitialized(__jpa.getPlugin_descripcioids())) ) {
      __tmp.setPlugin_descripcioids(PluginJPA.copyJPA(__jpa.getPlugin_descripcioids(), __alreadyCopied,"TraduccioJPA"));
    }
    // Copia de beans complexes (IMP)
    // Aquesta linia s'afeix de forma manual
    __tmp.setTraduccions(new HashMap<String, TraduccioMapJPA>(__jpa.getTraduccions()));

    return __tmp;
  }




}
