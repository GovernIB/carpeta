
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
@Table(name = "car_avis" )
@SequenceGenerator(name="AVIS_SEQ", sequenceName="car_avis_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class AvisJPA implements Avis {



private static final long serialVersionUID = 1036906268L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="AVIS_SEQ")
	@Index(name="car_avis_pk_i")
	@Column(name="avisid",nullable = false,length = 19)
	long avisID;

	@Index(name="car_avis_descripcioid_fk_i")
	@Column(name="descripcioid",nullable = false,length = 19)
	long descripcioID;

	@Index(name="car_avis_entitatid_fk_i")
	@Column(name="entitatid",length = 19)
	java.lang.Long entitatID;

	@Column(name="datainici",length = 29,precision = 6)
	java.sql.Timestamp dataInici;

	@Column(name="datafi",length = 29,precision = 6)
	java.sql.Timestamp dataFi;

	@Column(name="tipus",nullable = false,length = 10)
	int tipus;

	@Column(name="gravetat",nullable = false,length = 10)
	int gravetat;

	@Index(name="car_avis_pluginfrontid_fk_i")
	@Column(name="pluginfrontid",length = 19)
	java.lang.Long pluginFrontID;



  /** Constructor Buit */
  public AvisJPA() {
  }

  /** Constructor amb tots els camps  */
  public AvisJPA(long avisID , long descripcioID , java.lang.Long entitatID , java.sql.Timestamp dataInici , java.sql.Timestamp dataFi , int tipus , int gravetat , java.lang.Long pluginFrontID) {
    this.avisID=avisID;
    this.descripcioID=descripcioID;
    this.entitatID=entitatID;
    this.dataInici=dataInici;
    this.dataFi=dataFi;
    this.tipus=tipus;
    this.gravetat=gravetat;
    this.pluginFrontID=pluginFrontID;
}
  /** Constructor sense valors autoincrementals */
  public AvisJPA(long descripcioID , java.lang.Long entitatID , java.sql.Timestamp dataInici , java.sql.Timestamp dataFi , int tipus , int gravetat , java.lang.Long pluginFrontID) {
    this.descripcioID=descripcioID;
    this.entitatID=entitatID;
    this.dataInici=dataInici;
    this.dataFi=dataFi;
    this.tipus=tipus;
    this.gravetat=gravetat;
    this.pluginFrontID=pluginFrontID;
}
  /** Constructor dels valors Not Null */
  public AvisJPA(long avisID , long descripcioID , int tipus , int gravetat) {
    this.avisID=avisID;
    this.descripcioID=descripcioID;
    this.tipus=tipus;
    this.gravetat=gravetat;
}
  public AvisJPA(Avis __bean) {
    this.setAvisID(__bean.getAvisID());
    this.setDescripcioID(__bean.getDescripcioID());
    this.setEntitatID(__bean.getEntitatID());
    this.setDataInici(__bean.getDataInici());
    this.setDataFi(__bean.getDataFi());
    this.setTipus(__bean.getTipus());
    this.setGravetat(__bean.getGravetat());
    this.setPluginFrontID(__bean.getPluginFrontID());
	}

	public long getAvisID() {
		return(avisID);
	};
	public void setAvisID(long _avisID_) {
		this.avisID = _avisID_;
	};

	public long getDescripcioID() {
		return(descripcioID);
	};
	public void setDescripcioID(long _descripcioID_) {
		this.descripcioID = _descripcioID_;
	};

	public java.lang.Long getEntitatID() {
		return(entitatID);
	};
	public void setEntitatID(java.lang.Long _entitatID_) {
		this.entitatID = _entitatID_;
	};

	public java.sql.Timestamp getDataInici() {
		return(dataInici);
	};
	public void setDataInici(java.sql.Timestamp _dataInici_) {
		this.dataInici = _dataInici_;
	};

	public java.sql.Timestamp getDataFi() {
		return(dataFi);
	};
	public void setDataFi(java.sql.Timestamp _dataFi_) {
		this.dataFi = _dataFi_;
	};

	public int getTipus() {
		return(tipus);
	};
	public void setTipus(int _tipus_) {
		this.tipus = _tipus_;
	};

	public int getGravetat() {
		return(gravetat);
	};
	public void setGravetat(int _gravetat_) {
		this.gravetat = _gravetat_;
	};

	public java.lang.Long getPluginFrontID() {
		return(pluginFrontID);
	};
	public void setPluginFrontID(java.lang.Long _pluginFrontID_) {
		this.pluginFrontID = _pluginFrontID_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof Avis) {
      Avis __instance = (Avis)__obj;
      __result = true;
      __result = __result && (this.getAvisID() == __instance.getAvisID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// IMP Field:traduccioid | Table: car_traduccio | Type: 1  

	@ManyToOne(fetch = FetchType.EAGER, cascade=javax.persistence.CascadeType.ALL)
	@ForeignKey(name="car_avis_traduccio_desc_fk")
	@JoinColumn(name = "descripcioid", referencedColumnName ="traduccioID", nullable = false, insertable=false, updatable=false)
	private TraduccioJPA descripcio;

	public TraduccioJPA getDescripcio() {
    return this.descripcio;
  }

	public  void setDescripcio(TraduccioJPA descripcio) {
    this.descripcio = descripcio;
  }

  @javax.xml.bind.annotation.XmlTransient
  public java.util.Map<String, es.caib.carpeta.jpa.TraduccioMapJPA> getDescripcioTraduccions() {
    return this.descripcio.getTraduccions();
  }

  public void setDescripcioTraduccions(java.util.Map<String, es.caib.carpeta.jpa.TraduccioMapJPA> __traduccions__) {
    this.descripcio.setTraduccions(__traduccions__);
  }


// IMP Field:entitatid | Table: car_entitat | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="car_avis_entitat_ent_fk")
	@JoinColumn(name = "entitatid", referencedColumnName ="entitatID", nullable = true, insertable=false, updatable=false)
	private EntitatJPA entitat;

	public EntitatJPA getEntitat() {
    return this.entitat;
  }

	public  void setEntitat(EntitatJPA entitat) {
    this.entitat = entitat;
  }

// IMP Field:pluginid | Table: car_plugin | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="car_avis_plugin_pfront_fk")
	@JoinColumn(name = "pluginfrontid", referencedColumnName ="pluginID", nullable = true, insertable=false, updatable=false)
	private PluginJPA plugin;

	public PluginJPA getPlugin() {
    return this.plugin;
  }

	public  void setPlugin(PluginJPA plugin) {
    this.plugin = plugin;
  }


 // ---------------  STATIC METHODS ------------------
  public static AvisJPA toJPA(Avis __bean) {
    if (__bean == null) { return null;}
    AvisJPA __tmp = new AvisJPA();
    __tmp.setAvisID(__bean.getAvisID());
    __tmp.setDescripcioID(__bean.getDescripcioID());
    __tmp.setEntitatID(__bean.getEntitatID());
    __tmp.setDataInici(__bean.getDataInici());
    __tmp.setDataFi(__bean.getDataFi());
    __tmp.setTipus(__bean.getTipus());
    __tmp.setGravetat(__bean.getGravetat());
    __tmp.setPluginFrontID(__bean.getPluginFrontID());
		return __tmp;
	}


  public static AvisJPA copyJPA(AvisJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<AvisJPA> copyJPA(java.util.Set<AvisJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    @SuppressWarnings("unchecked")
    java.util.Set<AvisJPA> __tmpSet = (java.util.Set<AvisJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<AvisJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (AvisJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static AvisJPA copyJPA(AvisJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    AvisJPA __tmp = (AvisJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    // Copia de beans complexes (IMP)
    if(!"TraduccioJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.descripcio) || org.hibernate.Hibernate.isInitialized(__jpa.getDescripcio()) ) ) {
      __tmp.setDescripcio(TraduccioJPA.copyJPA(__jpa.getDescripcio(), __alreadyCopied,"AvisJPA"));
    }
    if(!"EntitatJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.entitat) || org.hibernate.Hibernate.isInitialized(__jpa.getEntitat()) ) ) {
      __tmp.setEntitat(EntitatJPA.copyJPA(__jpa.getEntitat(), __alreadyCopied,"AvisJPA"));
    }
    if(!"PluginJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.plugin) || org.hibernate.Hibernate.isInitialized(__jpa.getPlugin()) ) ) {
      __tmp.setPlugin(PluginJPA.copyJPA(__jpa.getPlugin(), __alreadyCopied,"AvisJPA"));
    }

    return __tmp;
  }




}
