
package es.caib.carpeta.jpa;
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
@SequenceGenerator(name="CARPETA_SEQ", sequenceName="car_carpeta_seq", allocationSize=1)
@javax.xml.bind.annotation.XmlRootElement
public class TraduccioJPA implements Traduccio {



private static final long serialVersionUID = -326205279L;

  /**  */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="CARPETA_SEQ")
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


// EXP  Field:nomid | Table: car_entitat | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "nomID")
	private Set<EntitatJPA> entitats = new HashSet<EntitatJPA>(0);
	public  Set<EntitatJPA> getEntitats() {
    return this.entitats;
  }

	public void setEntitats(Set<EntitatJPA> entitats) {
	  this.entitats = entitats;
	}


// EXP  Field:nomid | Table: car_plugin | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "nomID")
	private Set<PluginJPA> plugins = new HashSet<PluginJPA>(0);
	public  Set<PluginJPA> getPlugins() {
    return this.plugins;
  }

	public void setPlugins(Set<PluginJPA> plugins) {
	  this.plugins = plugins;
	}


  @ElementCollection(fetch= FetchType.EAGER, targetClass = es.caib.carpeta.jpa.TraduccioMapJPA.class)
  @Cascade(value=org.hibernate.annotations.CascadeType.ALL)
  @LazyCollection(value= LazyCollectionOption.FALSE)
  @JoinTable(name="car_traducciomap",joinColumns={@JoinColumn(name="traducciomapid")})
  @javax.persistence.MapKeyColumn(name="idiomaid")
  @ForeignKey(name="car_traducmap_traduccio_fk") 
  private Map<String, es.caib.carpeta.jpa.TraduccioMapJPA> traduccions =  new HashMap<String, es.caib.carpeta.jpa.TraduccioMapJPA>();

  public Map<String, es.caib.carpeta.jpa.TraduccioMapJPA> getTraduccions() {
    return this.traduccions;
  }

  public void setTraduccions(Map<String, es.caib.carpeta.jpa.TraduccioMapJPA> _traduccions_) {
    this.traduccions = _traduccions_;
  }

  public java.util.Set<String> getIdiomes() {
    return this.traduccions.keySet();
  }
  
  public es.caib.carpeta.jpa.TraduccioMapJPA getTraduccio(String idioma) {
    return (es.caib.carpeta.jpa.TraduccioMapJPA) traduccions.get(idioma);
  }
  
  public void addTraduccio(String idioma, es.caib.carpeta.jpa.TraduccioMapJPA traduccio) {
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
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.entitats) || org.hibernate.Hibernate.isInitialized(__jpa.getEntitats())) ) {
      __tmp.setEntitats(EntitatJPA.copyJPA(__jpa.getEntitats(), __alreadyCopied,"TraduccioJPA"));
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
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.plugins) || org.hibernate.Hibernate.isInitialized(__jpa.getPlugins())) ) {
      __tmp.setPlugins(PluginJPA.copyJPA(__jpa.getPlugins(), __alreadyCopied,"TraduccioJPA"));
    }
    // Copia de beans complexes (IMP)
    // Aquesta linia s'afeix de forma manual
    __tmp.setTraduccions(new HashMap<String, TraduccioMapJPA>(__jpa.getTraduccions()));

    return __tmp;
  }




}
