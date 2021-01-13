
package es.caib.carpeta.jpa;
import es.caib.carpeta.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import java.util.HashSet;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import org.hibernate.annotations.Index;
import javax.persistence.SequenceGenerator;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Set;
import javax.persistence.Id;


@SuppressWarnings("deprecation")
@Entity
@Table(name = "car_fitxer" )
@SequenceGenerator(name="FITXER_SEQ", sequenceName="car_fitxer_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class FitxerJPA implements Fitxer {



private static final long serialVersionUID = -252813913L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="FITXER_SEQ")
	@Index(name="car_fitxer_pk_i")
	@Column(name="fitxerid",nullable = false,length = 19)
	long fitxerID;

	@Column(name="nom",nullable = false,length = 255)
	java.lang.String nom;

	@Column(name="mime",nullable = false,length = 255)
	java.lang.String mime;

	@Column(name="tamany",nullable = false,length = 19)
	long tamany;

	@Column(name="descripcio",length = 1000)
	java.lang.String descripcio;



  /** Constructor Buit */
  public FitxerJPA() {
  }

  /** Constructor amb tots els camps  */
  public FitxerJPA(long fitxerID , java.lang.String nom , java.lang.String mime , long tamany , java.lang.String descripcio) {
    this.fitxerID=fitxerID;
    this.nom=nom;
    this.mime=mime;
    this.tamany=tamany;
    this.descripcio=descripcio;
}
  /** Constructor sense valors autoincrementals */
  public FitxerJPA(java.lang.String nom , java.lang.String mime , long tamany , java.lang.String descripcio) {
    this.nom=nom;
    this.mime=mime;
    this.tamany=tamany;
    this.descripcio=descripcio;
}
  public FitxerJPA(Fitxer __bean) {
    this.setFitxerID(__bean.getFitxerID());
    this.setNom(__bean.getNom());
    this.setMime(__bean.getMime());
    this.setTamany(__bean.getTamany());
    this.setDescripcio(__bean.getDescripcio());
    // DataHandler
    this.setData(__bean.getData());
    // EncryptedFileID
    this.setEncryptedFileID(__bean.getEncryptedFileID());
	}

	public long getFitxerID() {
		return(fitxerID);
	};
	public void setFitxerID(long _fitxerID_) {
		this.fitxerID = _fitxerID_;
	};

	public java.lang.String getNom() {
		return(nom);
	};
	public void setNom(java.lang.String _nom_) {
		this.nom = _nom_;
	};

	public java.lang.String getMime() {
		return(mime);
	};
	public void setMime(java.lang.String _mime_) {
		this.mime = _mime_;
	};

	public long getTamany() {
		return(tamany);
	};
	public void setTamany(long _tamany_) {
		this.tamany = _tamany_;
	};

	public java.lang.String getDescripcio() {
		return(descripcio);
	};
	public void setDescripcio(java.lang.String _descripcio_) {
		this.descripcio = _descripcio_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof Fitxer) {
      Fitxer __instance = (Fitxer)__obj;
      __result = true;
      __result = __result && (this.getFitxerID() == __instance.getFitxerID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// EXP  Field:logoid | Table: car_enllaz | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "logoID")
	private Set<EnllazJPA> enllazs = new HashSet<EnllazJPA>(0);
	public  Set<EnllazJPA> getEnllazs() {
    return this.enllazs;
  }

	public void setEnllazs(Set<EnllazJPA> enllazs) {
	  this.enllazs = enllazs;
	}


// EXP  Field:fitxercss | Table: car_entitat | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "fitxerCssID")
	private Set<EntitatJPA> entitat_fitxercsss = new HashSet<EntitatJPA>(0);
	public  Set<EntitatJPA> getEntitat_fitxercsss() {
    return this.entitat_fitxercsss;
  }

	public void setEntitat_fitxercsss(Set<EntitatJPA> entitat_fitxercsss) {
	  this.entitat_fitxercsss = entitat_fitxercsss;
	}


// EXP  Field:iconid | Table: car_entitat | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "iconID")
	private Set<EntitatJPA> entitat_iconids = new HashSet<EntitatJPA>(0);
	public  Set<EntitatJPA> getEntitat_iconids() {
    return this.entitat_iconids;
  }

	public void setEntitat_iconids(Set<EntitatJPA> entitat_iconids) {
	  this.entitat_iconids = entitat_iconids;
	}


// EXP  Field:logocapbackid | Table: car_entitat | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "logoCapBackID")
	private Set<EntitatJPA> entitat_logocapbackids = new HashSet<EntitatJPA>(0);
	public  Set<EntitatJPA> getEntitat_logocapbackids() {
    return this.entitat_logocapbackids;
  }

	public void setEntitat_logocapbackids(Set<EntitatJPA> entitat_logocapbackids) {
	  this.entitat_logocapbackids = entitat_logocapbackids;
	}


// EXP  Field:logolateralfrontid | Table: car_entitat | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "logoLateralFrontID")
	private Set<EntitatJPA> entitat_logolateralfrontids = new HashSet<EntitatJPA>(0);
	public  Set<EntitatJPA> getEntitat_logolateralfrontids() {
    return this.entitat_logolateralfrontids;
  }

	public void setEntitat_logolateralfrontids(Set<EntitatJPA> entitat_logolateralfrontids) {
	  this.entitat_logolateralfrontids = entitat_logolateralfrontids;
	}


// EXP  Field:logopeubackid | Table: car_entitat | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "logoPeuBackID")
	private Set<EntitatJPA> entitat_logopeubackids = new HashSet<EntitatJPA>(0);
	public  Set<EntitatJPA> getEntitat_logopeubackids() {
    return this.entitat_logopeubackids;
  }

	public void setEntitat_logopeubackids(Set<EntitatJPA> entitat_logopeubackids) {
	  this.entitat_logopeubackids = entitat_logopeubackids;
	}


// EXP  Field:logoid | Table: car_plugin | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "logoID")
	private Set<PluginJPA> plugins = new HashSet<PluginJPA>(0);
	public  Set<PluginJPA> getPlugins() {
    return this.plugins;
  }

	public void setPlugins(Set<PluginJPA> plugins) {
	  this.plugins = plugins;
	}



  @javax.persistence.Transient
  javax.activation.DataHandler data;

  public javax.activation.DataHandler getData() {
    return data;
  }

  public void setData(javax.activation.DataHandler data) {
    this.data = data;
  }

  @javax.persistence.Transient
  String encryptedFileID;

  public String getEncryptedFileID() {
    return encryptedFileID;
  }

  public void setEncryptedFileID(String encryptedFileID) {
    this.encryptedFileID = encryptedFileID;
  }


  final static ThreadLocal<String> threadLocal = new ThreadLocal<String>();

  public static void enableEncryptedFileIDGeneration() {
    threadLocal.set("");
  }

  public static void disableEncryptedFileIDGeneration() {
    threadLocal.remove();
  }

  @javax.persistence.PostPersist
  @javax.persistence.PostLoad
  void postLoad() {
    if (threadLocal.get() != null) {
      this.encryptedFileID = es.caib.carpeta.hibernate.HibernateFileUtil.encryptFileID(fitxerID);
    }
  }


 // ---------------  STATIC METHODS ------------------
  public static FitxerJPA toJPA(Fitxer __bean) {
    if (__bean == null) { return null;}
    FitxerJPA __tmp = new FitxerJPA();
    __tmp.setFitxerID(__bean.getFitxerID());
    __tmp.setNom(__bean.getNom());
    __tmp.setMime(__bean.getMime());
    __tmp.setTamany(__bean.getTamany());
    __tmp.setDescripcio(__bean.getDescripcio());
    // DataHandler
    __tmp.setData(__bean.getData());
    // EncryptedFileID
    __tmp.setEncryptedFileID(__bean.getEncryptedFileID());
		return __tmp;
	}


  public static FitxerJPA copyJPA(FitxerJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<FitxerJPA> copyJPA(java.util.Set<FitxerJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    @SuppressWarnings("unchecked")
    java.util.Set<FitxerJPA> __tmpSet = (java.util.Set<FitxerJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<FitxerJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (FitxerJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static FitxerJPA copyJPA(FitxerJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    FitxerJPA __tmp = (FitxerJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    if(!"EntitatJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.entitat_logolateralfrontids) || org.hibernate.Hibernate.isInitialized(__jpa.getEntitat_logolateralfrontids())) ) {
      __tmp.setEntitat_logolateralfrontids(EntitatJPA.copyJPA(__jpa.getEntitat_logolateralfrontids(), __alreadyCopied,"FitxerJPA"));
    }
    if(!"EntitatJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.entitat_logocapbackids) || org.hibernate.Hibernate.isInitialized(__jpa.getEntitat_logocapbackids())) ) {
      __tmp.setEntitat_logocapbackids(EntitatJPA.copyJPA(__jpa.getEntitat_logocapbackids(), __alreadyCopied,"FitxerJPA"));
    }
    if(!"EntitatJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.entitat_fitxercsss) || org.hibernate.Hibernate.isInitialized(__jpa.getEntitat_fitxercsss())) ) {
      __tmp.setEntitat_fitxercsss(EntitatJPA.copyJPA(__jpa.getEntitat_fitxercsss(), __alreadyCopied,"FitxerJPA"));
    }
    if(!"EnllazJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.enllazs) || org.hibernate.Hibernate.isInitialized(__jpa.getEnllazs())) ) {
      __tmp.setEnllazs(EnllazJPA.copyJPA(__jpa.getEnllazs(), __alreadyCopied,"FitxerJPA"));
    }
    if(!"EntitatJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.entitat_logopeubackids) || org.hibernate.Hibernate.isInitialized(__jpa.getEntitat_logopeubackids())) ) {
      __tmp.setEntitat_logopeubackids(EntitatJPA.copyJPA(__jpa.getEntitat_logopeubackids(), __alreadyCopied,"FitxerJPA"));
    }
    if(!"EntitatJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.entitat_iconids) || org.hibernate.Hibernate.isInitialized(__jpa.getEntitat_iconids())) ) {
      __tmp.setEntitat_iconids(EntitatJPA.copyJPA(__jpa.getEntitat_iconids(), __alreadyCopied,"FitxerJPA"));
    }
    if(!"PluginJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.plugins) || org.hibernate.Hibernate.isInitialized(__jpa.getPlugins())) ) {
      __tmp.setPlugins(PluginJPA.copyJPA(__jpa.getPlugins(), __alreadyCopied,"FitxerJPA"));
    }
    // Copia de beans complexes (IMP)

    return __tmp;
  }




}
