
package es.caib.carpeta.jpa;
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
@Table(name = "car_entitat" )
@SequenceGenerator(name="ENTITAT_SEQ", sequenceName="car_entitat_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class EntitatJPA implements Entitat {



private static final long serialVersionUID = 489209138L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ENTITAT_SEQ")
	@Index(name="car_entitat_pk_i")
	@Column(name="entitatid",nullable = false,length = 19)
	long entitatID;

	@Index(name="car_entitat_nom_fk_i")
	@Column(name="nomid",nullable = false,length = 19)
	long nomID;

	@Column(name="codi",nullable = false,length = 30)
	java.lang.String codi;

	@Column(name="codidir3",nullable = false,length = 255)
	java.lang.String codiDir3;

	@Column(name="activa",nullable = false,length = 1)
	boolean activa;

	@Index(name="car_entitat_logomenuid_fk_i")
	@Column(name="logomenuid",length = 19)
	java.lang.Long logoMenuID;

	@Column(name="colormenu",nullable = false,length = 100)
	java.lang.String colorMenu;

	@Column(name="textepeu",length = 4000)
	java.lang.String textePeu;

	@Index(name="car_entitat_logopeuid_fk_i")
	@Column(name="logopeuid",nullable = false,length = 19)
	long logoPeuID;

	@Column(name="versio",nullable = false,length = 50)
	java.lang.String versio;

	@Column(name="commit",length = 255)
	java.lang.String commit;

	@Index(name="car_entitat_fitxercss_fk_i")
	@Column(name="fitxercss",length = 19)
	java.lang.Long fitxerCssID;

	@Column(name="context",length = 255)
	java.lang.String context;



  /** Constructor Buit */
  public EntitatJPA() {
  }

  /** Constructor amb tots els camps  */
  public EntitatJPA(long entitatID , long nomID , java.lang.String codi , java.lang.String codiDir3 , boolean activa , java.lang.Long logoMenuID , java.lang.String colorMenu , java.lang.String textePeu , long logoPeuID , java.lang.String versio , java.lang.String commit , java.lang.Long fitxerCssID , java.lang.String context) {
    this.entitatID=entitatID;
    this.nomID=nomID;
    this.codi=codi;
    this.codiDir3=codiDir3;
    this.activa=activa;
    this.logoMenuID=logoMenuID;
    this.colorMenu=colorMenu;
    this.textePeu=textePeu;
    this.logoPeuID=logoPeuID;
    this.versio=versio;
    this.commit=commit;
    this.fitxerCssID=fitxerCssID;
    this.context=context;
}
  /** Constructor sense valors autoincrementals */
  public EntitatJPA(long nomID , java.lang.String codi , java.lang.String codiDir3 , boolean activa , java.lang.Long logoMenuID , java.lang.String colorMenu , java.lang.String textePeu , long logoPeuID , java.lang.String versio , java.lang.String commit , java.lang.Long fitxerCssID , java.lang.String context) {
    this.nomID=nomID;
    this.codi=codi;
    this.codiDir3=codiDir3;
    this.activa=activa;
    this.logoMenuID=logoMenuID;
    this.colorMenu=colorMenu;
    this.textePeu=textePeu;
    this.logoPeuID=logoPeuID;
    this.versio=versio;
    this.commit=commit;
    this.fitxerCssID=fitxerCssID;
    this.context=context;
}
  /** Constructor dels valors Not Null */
  public EntitatJPA(long entitatID , long nomID , java.lang.String codi , java.lang.String codiDir3 , boolean activa , java.lang.String colorMenu , long logoPeuID , java.lang.String versio) {
    this.entitatID=entitatID;
    this.nomID=nomID;
    this.codi=codi;
    this.codiDir3=codiDir3;
    this.activa=activa;
    this.colorMenu=colorMenu;
    this.logoPeuID=logoPeuID;
    this.versio=versio;
}
  public EntitatJPA(Entitat __bean) {
    this.setEntitatID(__bean.getEntitatID());
    this.setNomID(__bean.getNomID());
    this.setCodi(__bean.getCodi());
    this.setCodiDir3(__bean.getCodiDir3());
    this.setActiva(__bean.isActiva());
    this.setLogoMenuID(__bean.getLogoMenuID());
    this.setColorMenu(__bean.getColorMenu());
    this.setTextePeu(__bean.getTextePeu());
    this.setLogoPeuID(__bean.getLogoPeuID());
    this.setVersio(__bean.getVersio());
    this.setCommit(__bean.getCommit());
    this.setFitxerCssID(__bean.getFitxerCssID());
    this.setContext(__bean.getContext());
    // Fitxer
    this.setLogoMenu(FitxerJPA.toJPA(__bean.getLogoMenu()));
    // Fitxer
    this.setLogoPeu(FitxerJPA.toJPA(__bean.getLogoPeu()));
    // Fitxer
    this.setFitxerCss(FitxerJPA.toJPA(__bean.getFitxerCss()));
	}

	public long getEntitatID() {
		return(entitatID);
	};
	public void setEntitatID(long _entitatID_) {
		this.entitatID = _entitatID_;
	};

	public long getNomID() {
		return(nomID);
	};
	public void setNomID(long _nomID_) {
		this.nomID = _nomID_;
	};

	public java.lang.String getCodi() {
		return(codi);
	};
	public void setCodi(java.lang.String _codi_) {
		this.codi = _codi_;
	};

	public java.lang.String getCodiDir3() {
		return(codiDir3);
	};
	public void setCodiDir3(java.lang.String _codiDir3_) {
		this.codiDir3 = _codiDir3_;
	};

	public boolean isActiva() {
		return(activa);
	};
	public void setActiva(boolean _activa_) {
		this.activa = _activa_;
	};

	public java.lang.Long getLogoMenuID() {
		return(logoMenuID);
	};
	public void setLogoMenuID(java.lang.Long _logoMenuID_) {
		this.logoMenuID = _logoMenuID_;
	};

	public java.lang.String getColorMenu() {
		return(colorMenu);
	};
	public void setColorMenu(java.lang.String _colorMenu_) {
		this.colorMenu = _colorMenu_;
	};

	public java.lang.String getTextePeu() {
		return(textePeu);
	};
	public void setTextePeu(java.lang.String _textePeu_) {
		this.textePeu = _textePeu_;
	};

	public long getLogoPeuID() {
		return(logoPeuID);
	};
	public void setLogoPeuID(long _logoPeuID_) {
		this.logoPeuID = _logoPeuID_;
	};

	public java.lang.String getVersio() {
		return(versio);
	};
	public void setVersio(java.lang.String _versio_) {
		this.versio = _versio_;
	};

	public java.lang.String getCommit() {
		return(commit);
	};
	public void setCommit(java.lang.String _commit_) {
		this.commit = _commit_;
	};

	public java.lang.Long getFitxerCssID() {
		return(fitxerCssID);
	};
	public void setFitxerCssID(java.lang.Long _fitxerCssID_) {
		this.fitxerCssID = _fitxerCssID_;
	};

	public java.lang.String getContext() {
		return(context);
	};
	public void setContext(java.lang.String _context_) {
		this.context = _context_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof Entitat) {
      Entitat __instance = (Entitat)__obj;
      __result = true;
      __result = __result && (this.getEntitatID() == __instance.getEntitatID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// EXP  Field:entitatid | Table: car_acces | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "entitat")
	private Set<AccesJPA> access = new HashSet<AccesJPA>(0);
	public  Set<AccesJPA> getAccess() {
    return this.access;
  }

	public void setAccess(Set<AccesJPA> access) {
	  this.access = access;
	}


// EXP  Field:entitatid | Table: car_auditoria | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "entitat")
	private Set<AuditoriaJPA> auditorias = new HashSet<AuditoriaJPA>(0);
	public  Set<AuditoriaJPA> getAuditorias() {
    return this.auditorias;
  }

	public void setAuditorias(Set<AuditoriaJPA> auditorias) {
	  this.auditorias = auditorias;
	}


// EXP  Field:entitatid | Table: car_avis | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "entitat")
	private Set<AvisJPA> aviss = new HashSet<AvisJPA>(0);
	public  Set<AvisJPA> getAviss() {
    return this.aviss;
  }

	public void setAviss(Set<AvisJPA> aviss) {
	  this.aviss = aviss;
	}


// EXP  Field:entitatid | Table: car_enllaz | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "entitat")
	private Set<EnllazJPA> enllazs = new HashSet<EnllazJPA>(0);
	public  Set<EnllazJPA> getEnllazs() {
    return this.enllazs;
  }

	public void setEnllazs(Set<EnllazJPA> enllazs) {
	  this.enllazs = enllazs;
	}


// EXP  Field:entitatid | Table: car_estadistica | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "entitat")
	private Set<EstadisticaJPA> estadisticas = new HashSet<EstadisticaJPA>(0);
	public  Set<EstadisticaJPA> getEstadisticas() {
    return this.estadisticas;
  }

	public void setEstadisticas(Set<EstadisticaJPA> estadisticas) {
	  this.estadisticas = estadisticas;
	}


// EXP  Field:entitatid | Table: car_log | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "entitat")
	private Set<LogCarpetaJPA> logCarpetas = new HashSet<LogCarpetaJPA>(0);
	public  Set<LogCarpetaJPA> getLogCarpetas() {
    return this.logCarpetas;
  }

	public void setLogCarpetas(Set<LogCarpetaJPA> logCarpetas) {
	  this.logCarpetas = logCarpetas;
	}


// EXP  Field:entitatid | Table: car_pluginentitat | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "entitat")
	private Set<PluginEntitatJPA> pluginEntitats = new HashSet<PluginEntitatJPA>(0);
	public  Set<PluginEntitatJPA> getPluginEntitats() {
    return this.pluginEntitats;
  }

	public void setPluginEntitats(Set<PluginEntitatJPA> pluginEntitats) {
	  this.pluginEntitats = pluginEntitats;
	}


// EXP  Field:entitatid | Table: car_propietatglobal | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "entitat")
	private Set<PropietatGlobalJPA> propietatGlobals = new HashSet<PropietatGlobalJPA>(0);
	public  Set<PropietatGlobalJPA> getPropietatGlobals() {
    return this.propietatGlobals;
  }

	public void setPropietatGlobals(Set<PropietatGlobalJPA> propietatGlobals) {
	  this.propietatGlobals = propietatGlobals;
	}


// EXP  Field:darreraentitat | Table: car_usuari | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "entitat")
	private Set<UsuariJPA> usuaris = new HashSet<UsuariJPA>(0);
	public  Set<UsuariJPA> getUsuaris() {
    return this.usuaris;
  }

	public void setUsuaris(Set<UsuariJPA> usuaris) {
	  this.usuaris = usuaris;
	}


// EXP  Field:entitatid | Table: car_usuarientitat | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "entitat")
	private Set<UsuariEntitatJPA> usuariEntitats = new HashSet<UsuariEntitatJPA>(0);
	public  Set<UsuariEntitatJPA> getUsuariEntitats() {
    return this.usuariEntitats;
  }

	public void setUsuariEntitats(Set<UsuariEntitatJPA> usuariEntitats) {
	  this.usuariEntitats = usuariEntitats;
	}


// IMP Field:traduccioid | Table: car_traduccio | Type: 1  

	@ManyToOne(fetch = FetchType.EAGER, cascade=javax.persistence.CascadeType.ALL)
	@ForeignKey(name="car_entitat_traduccio_nom_fk")
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


// IMP Field:fitxerid | Table: car_fitxer | Type: 1  

	@ManyToOne(fetch = FetchType.EAGER)
	@ForeignKey(name="car_entitat_fitxer_logom_fk")
	@JoinColumn(name = "logomenuid", referencedColumnName ="fitxerID", nullable = true, insertable=false, updatable=false)
	private FitxerJPA logoMenu;

	public FitxerJPA getLogoMenu() {
    return this.logoMenu;
  }

	public  void setLogoMenu(FitxerJPA logoMenu) {
    this.logoMenu = logoMenu;
  }

// IMP Field:fitxerid | Table: car_fitxer | Type: 1  

	@ManyToOne(fetch = FetchType.EAGER)
	@ForeignKey(name="car_entitat_fitxer_logop_fk")
	@JoinColumn(name = "logopeuid", referencedColumnName ="fitxerID", nullable = false, insertable=false, updatable=false)
	private FitxerJPA logoPeu;

	public FitxerJPA getLogoPeu() {
    return this.logoPeu;
  }

	public  void setLogoPeu(FitxerJPA logoPeu) {
    this.logoPeu = logoPeu;
  }

// IMP Field:fitxerid | Table: car_fitxer | Type: 1  

	@ManyToOne(fetch = FetchType.EAGER)
	@ForeignKey(name="car_entitat_fitxer_css_fk")
	@JoinColumn(name = "fitxercss", referencedColumnName ="fitxerID", nullable = true, insertable=false, updatable=false)
	private FitxerJPA fitxerCss;

	public FitxerJPA getFitxerCss() {
    return this.fitxerCss;
  }

	public  void setFitxerCss(FitxerJPA fitxerCss) {
    this.fitxerCss = fitxerCss;
  }


 // ---------------  STATIC METHODS ------------------
  public static EntitatJPA toJPA(Entitat __bean) {
    if (__bean == null) { return null;}
    EntitatJPA __tmp = new EntitatJPA();
    __tmp.setEntitatID(__bean.getEntitatID());
    __tmp.setNomID(__bean.getNomID());
    __tmp.setCodi(__bean.getCodi());
    __tmp.setCodiDir3(__bean.getCodiDir3());
    __tmp.setActiva(__bean.isActiva());
    __tmp.setLogoMenuID(__bean.getLogoMenuID());
    __tmp.setColorMenu(__bean.getColorMenu());
    __tmp.setTextePeu(__bean.getTextePeu());
    __tmp.setLogoPeuID(__bean.getLogoPeuID());
    __tmp.setVersio(__bean.getVersio());
    __tmp.setCommit(__bean.getCommit());
    __tmp.setFitxerCssID(__bean.getFitxerCssID());
    __tmp.setContext(__bean.getContext());
    // Fitxer
    __tmp.setLogoMenu(FitxerJPA.toJPA(__bean.getLogoMenu()));
    // Fitxer
    __tmp.setLogoPeu(FitxerJPA.toJPA(__bean.getLogoPeu()));
    // Fitxer
    __tmp.setFitxerCss(FitxerJPA.toJPA(__bean.getFitxerCss()));
		return __tmp;
	}


  public static EntitatJPA copyJPA(EntitatJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<EntitatJPA> copyJPA(java.util.Set<EntitatJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    @SuppressWarnings("unchecked")
    java.util.Set<EntitatJPA> __tmpSet = (java.util.Set<EntitatJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<EntitatJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (EntitatJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static EntitatJPA copyJPA(EntitatJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    EntitatJPA __tmp = (EntitatJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    if(!"LogCarpetaJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.logCarpetas) || org.hibernate.Hibernate.isInitialized(__jpa.getLogCarpetas())) ) {
      __tmp.setLogCarpetas(LogCarpetaJPA.copyJPA(__jpa.getLogCarpetas(), __alreadyCopied,"EntitatJPA"));
    }
    if(!"PluginEntitatJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.pluginEntitats) || org.hibernate.Hibernate.isInitialized(__jpa.getPluginEntitats())) ) {
      __tmp.setPluginEntitats(PluginEntitatJPA.copyJPA(__jpa.getPluginEntitats(), __alreadyCopied,"EntitatJPA"));
    }
    if(!"PropietatGlobalJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.propietatGlobals) || org.hibernate.Hibernate.isInitialized(__jpa.getPropietatGlobals())) ) {
      __tmp.setPropietatGlobals(PropietatGlobalJPA.copyJPA(__jpa.getPropietatGlobals(), __alreadyCopied,"EntitatJPA"));
    }
    if(!"EstadisticaJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.estadisticas) || org.hibernate.Hibernate.isInitialized(__jpa.getEstadisticas())) ) {
      __tmp.setEstadisticas(EstadisticaJPA.copyJPA(__jpa.getEstadisticas(), __alreadyCopied,"EntitatJPA"));
    }
    if(!"EnllazJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.enllazs) || org.hibernate.Hibernate.isInitialized(__jpa.getEnllazs())) ) {
      __tmp.setEnllazs(EnllazJPA.copyJPA(__jpa.getEnllazs(), __alreadyCopied,"EntitatJPA"));
    }
    if(!"UsuariEntitatJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.usuariEntitats) || org.hibernate.Hibernate.isInitialized(__jpa.getUsuariEntitats())) ) {
      __tmp.setUsuariEntitats(UsuariEntitatJPA.copyJPA(__jpa.getUsuariEntitats(), __alreadyCopied,"EntitatJPA"));
    }
    if(!"AccesJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.access) || org.hibernate.Hibernate.isInitialized(__jpa.getAccess())) ) {
      __tmp.setAccess(AccesJPA.copyJPA(__jpa.getAccess(), __alreadyCopied,"EntitatJPA"));
    }
    if(!"AuditoriaJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.auditorias) || org.hibernate.Hibernate.isInitialized(__jpa.getAuditorias())) ) {
      __tmp.setAuditorias(AuditoriaJPA.copyJPA(__jpa.getAuditorias(), __alreadyCopied,"EntitatJPA"));
    }
    if(!"AvisJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.aviss) || org.hibernate.Hibernate.isInitialized(__jpa.getAviss())) ) {
      __tmp.setAviss(AvisJPA.copyJPA(__jpa.getAviss(), __alreadyCopied,"EntitatJPA"));
    }
    if(!"UsuariJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.usuaris) || org.hibernate.Hibernate.isInitialized(__jpa.getUsuaris())) ) {
      __tmp.setUsuaris(UsuariJPA.copyJPA(__jpa.getUsuaris(), __alreadyCopied,"EntitatJPA"));
    }
    // Copia de beans complexes (IMP)
    if(!"TraduccioJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.nom) || org.hibernate.Hibernate.isInitialized(__jpa.getNom()) ) ) {
      __tmp.setNom(TraduccioJPA.copyJPA(__jpa.getNom(), __alreadyCopied,"EntitatJPA"));
    }

    return __tmp;
  }




}
