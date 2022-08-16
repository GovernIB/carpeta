
package es.caib.carpeta.persistence;
import es.caib.carpeta.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.Set;
import org.hibernate.annotations.Type;
import java.util.HashSet;
import javax.persistence.GenerationType;
import javax.persistence.Index;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;
import javax.persistence.FetchType;
import javax.persistence.Id;


@Entity(name = "EntitatJPA")
@Table(name = "car_entitat" , indexes = { 
        @Index(name="car_entitat_pk_i", columnList = "entitatid"),
        @Index(name="car_entitat_nom_fk_i", columnList = "nomid"),
        @Index(name="car_entitat_descripcioid_fk_i", columnList = "descripcioid"),
        @Index(name="car_entitat_logocapback_fk_i", columnList = "logocapbackid"),
        @Index(name="car_entitat_logopeuback_fk_i", columnList = "logopeubackid"),
        @Index(name="car_entitat_logolatfront_fk_i", columnList = "logolateralfrontid"),
        @Index(name="car_entitat_iconid_fk_i", columnList = "iconid"),
        @Index(name="car_entitat_pluginloginid_fk_i", columnList = "pluginloginid"),
        @Index(name="car_entitat_logintextid_fk_i", columnList = "logintextid"),
        @Index(name="car_entitat_fitxercss_fk_i", columnList = "fitxercss")})
@SequenceGenerator(name="ENTITAT_SEQ", sequenceName="car_entitat_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class EntitatJPA implements Entitat {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ENTITAT_SEQ")
    @Column(name="entitatid",nullable = false,length = 19)
    long entitatID;

    @Column(name="nomid",nullable = false,length = 19)
    long nomID;

    @Column(name="descripcioid",length = 19)
    java.lang.Long descripcioID;

    @Column(name="codi",nullable = false,length = 30)
    java.lang.String codi;

    @Column(name="codidir3",nullable = false,length = 255)
    java.lang.String codiDir3;

    @Column(name="activa",nullable = false,length = 1)
    boolean activa;

    @Column(name="colormenu",nullable = false,length = 100)
    java.lang.String colorMenu;

    @Column(name="logocapbackid",nullable = false,length = 19)
    long logoCapBackID;

    @Column(name="logopeubackid",nullable = false,length = 19)
    long logoPeuBackID;

    @Column(name="logolateralfrontid",nullable = false,length = 19)
    long logoLateralFrontID;

    @Column(name="versio",nullable = false,length = 50)
    java.lang.String versio;

    @Column(name="iconid",nullable = false,length = 19)
    long iconID;

    @Column(name="webentitat",nullable = false,length = 255)
    java.lang.String webEntitat;

    @Column(name="entitatdescfront",nullable = false,length = 4000)
    java.lang.String entitatDescFront;

    @Column(name="suportweb",length = 255)
    java.lang.String suportWeb;

    @Column(name="suporttelefon",length = 255)
    java.lang.String suportTelefon;

    @Column(name="suportemail",length = 255)
    java.lang.String suportEmail;

  /** Preguntes Freqüents */
    @Column(name="suportfaq",length = 255)
    java.lang.String suportFAQ;

  /**  Queixes i suggeriments  */
    @Column(name="suportqssi",length = 255)
    java.lang.String suportqssi;

  /** Suport autenticació Front */
    @Column(name="suportautenticacio",length = 255)
    java.lang.String suportautenticacio;

    @Column(name="pluginloginid",length = 19)
    java.lang.Long pluginLoginID;

    @Column(name="logintextid",length = 19)
    java.lang.Long loginTextID;

    @Column(name="fitxercss",length = 19)
    java.lang.Long fitxerCssID;

    @Column(name="context",length = 255)
    java.lang.String context;

    @Column(name="commit",length = 255)
    java.lang.String commit;

    @Column(name="avislegalca",length = 2147483647)
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    java.lang.String avisLegalCa;

    @Column(name="avislegales",length = 2147483647)
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    java.lang.String avisLegalEs;

    @Column(name="accessibilitatca",length = 2147483647)
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    java.lang.String accessibilitatCa;

    @Column(name="accessibilitates",length = 2147483647)
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    java.lang.String accessibilitatEs;



  /** Constructor Buit */
  public EntitatJPA() {
  }

  /** Constructor amb tots els camps  */
  public EntitatJPA(long entitatID , long nomID , java.lang.Long descripcioID , java.lang.String codi , java.lang.String codiDir3 , boolean activa , java.lang.String colorMenu , long logoCapBackID , long logoPeuBackID , long logoLateralFrontID , java.lang.String versio , long iconID , java.lang.String webEntitat , java.lang.String entitatDescFront , java.lang.String suportWeb , java.lang.String suportTelefon , java.lang.String suportEmail , java.lang.String suportFAQ , java.lang.String suportqssi , java.lang.String suportautenticacio , java.lang.Long pluginLoginID , java.lang.Long loginTextID , java.lang.Long fitxerCssID , java.lang.String context , java.lang.String commit , java.lang.String avisLegalCa , java.lang.String avisLegalEs , java.lang.String accessibilitatCa , java.lang.String accessibilitatEs) {
    this.entitatID=entitatID;
    this.nomID=nomID;
    this.descripcioID=descripcioID;
    this.codi=codi;
    this.codiDir3=codiDir3;
    this.activa=activa;
    this.colorMenu=colorMenu;
    this.logoCapBackID=logoCapBackID;
    this.logoPeuBackID=logoPeuBackID;
    this.logoLateralFrontID=logoLateralFrontID;
    this.versio=versio;
    this.iconID=iconID;
    this.webEntitat=webEntitat;
    this.entitatDescFront=entitatDescFront;
    this.suportWeb=suportWeb;
    this.suportTelefon=suportTelefon;
    this.suportEmail=suportEmail;
    this.suportFAQ=suportFAQ;
    this.suportqssi=suportqssi;
    this.suportautenticacio=suportautenticacio;
    this.pluginLoginID=pluginLoginID;
    this.loginTextID=loginTextID;
    this.fitxerCssID=fitxerCssID;
    this.context=context;
    this.commit=commit;
    this.avisLegalCa=avisLegalCa;
    this.avisLegalEs=avisLegalEs;
    this.accessibilitatCa=accessibilitatCa;
    this.accessibilitatEs=accessibilitatEs;
}
  /** Constructor sense valors autoincrementals */
  public EntitatJPA(long nomID , java.lang.Long descripcioID , java.lang.String codi , java.lang.String codiDir3 , boolean activa , java.lang.String colorMenu , long logoCapBackID , long logoPeuBackID , long logoLateralFrontID , java.lang.String versio , long iconID , java.lang.String webEntitat , java.lang.String entitatDescFront , java.lang.String suportWeb , java.lang.String suportTelefon , java.lang.String suportEmail , java.lang.String suportFAQ , java.lang.String suportqssi , java.lang.String suportautenticacio , java.lang.Long pluginLoginID , java.lang.Long loginTextID , java.lang.Long fitxerCssID , java.lang.String context , java.lang.String commit , java.lang.String avisLegalCa , java.lang.String avisLegalEs , java.lang.String accessibilitatCa , java.lang.String accessibilitatEs) {
    this.nomID=nomID;
    this.descripcioID=descripcioID;
    this.codi=codi;
    this.codiDir3=codiDir3;
    this.activa=activa;
    this.colorMenu=colorMenu;
    this.logoCapBackID=logoCapBackID;
    this.logoPeuBackID=logoPeuBackID;
    this.logoLateralFrontID=logoLateralFrontID;
    this.versio=versio;
    this.iconID=iconID;
    this.webEntitat=webEntitat;
    this.entitatDescFront=entitatDescFront;
    this.suportWeb=suportWeb;
    this.suportTelefon=suportTelefon;
    this.suportEmail=suportEmail;
    this.suportFAQ=suportFAQ;
    this.suportqssi=suportqssi;
    this.suportautenticacio=suportautenticacio;
    this.pluginLoginID=pluginLoginID;
    this.loginTextID=loginTextID;
    this.fitxerCssID=fitxerCssID;
    this.context=context;
    this.commit=commit;
    this.avisLegalCa=avisLegalCa;
    this.avisLegalEs=avisLegalEs;
    this.accessibilitatCa=accessibilitatCa;
    this.accessibilitatEs=accessibilitatEs;
}
  /** Constructor dels valors Not Null */
  public EntitatJPA(long entitatID , long nomID , java.lang.String codi , java.lang.String codiDir3 , boolean activa , java.lang.String colorMenu , long logoCapBackID , long logoPeuBackID , long logoLateralFrontID , java.lang.String versio , long iconID , java.lang.String webEntitat , java.lang.String entitatDescFront) {
    this.entitatID=entitatID;
    this.nomID=nomID;
    this.codi=codi;
    this.codiDir3=codiDir3;
    this.activa=activa;
    this.colorMenu=colorMenu;
    this.logoCapBackID=logoCapBackID;
    this.logoPeuBackID=logoPeuBackID;
    this.logoLateralFrontID=logoLateralFrontID;
    this.versio=versio;
    this.iconID=iconID;
    this.webEntitat=webEntitat;
    this.entitatDescFront=entitatDescFront;
}
  public EntitatJPA(Entitat __bean) {
    this.setEntitatID(__bean.getEntitatID());
    this.setNomID(__bean.getNomID());
    this.setDescripcioID(__bean.getDescripcioID());
    this.setCodi(__bean.getCodi());
    this.setCodiDir3(__bean.getCodiDir3());
    this.setActiva(__bean.isActiva());
    this.setColorMenu(__bean.getColorMenu());
    this.setLogoCapBackID(__bean.getLogoCapBackID());
    this.setLogoPeuBackID(__bean.getLogoPeuBackID());
    this.setLogoLateralFrontID(__bean.getLogoLateralFrontID());
    this.setVersio(__bean.getVersio());
    this.setIconID(__bean.getIconID());
    this.setWebEntitat(__bean.getWebEntitat());
    this.setEntitatDescFront(__bean.getEntitatDescFront());
    this.setSuportWeb(__bean.getSuportWeb());
    this.setSuportTelefon(__bean.getSuportTelefon());
    this.setSuportEmail(__bean.getSuportEmail());
    this.setSuportFAQ(__bean.getSuportFAQ());
    this.setSuportqssi(__bean.getSuportqssi());
    this.setSuportautenticacio(__bean.getSuportautenticacio());
    this.setPluginLoginID(__bean.getPluginLoginID());
    this.setLoginTextID(__bean.getLoginTextID());
    this.setFitxerCssID(__bean.getFitxerCssID());
    this.setContext(__bean.getContext());
    this.setCommit(__bean.getCommit());
    this.setAvisLegalCa(__bean.getAvisLegalCa());
    this.setAvisLegalEs(__bean.getAvisLegalEs());
    this.setAccessibilitatCa(__bean.getAccessibilitatCa());
    this.setAccessibilitatEs(__bean.getAccessibilitatEs());
    // Fitxer
    this.setLogoCapBack(FitxerJPA.toJPA(__bean.getLogoCapBack()));
    // Fitxer
    this.setLogoPeuBack(FitxerJPA.toJPA(__bean.getLogoPeuBack()));
    // Fitxer
    this.setLogoLateralFront(FitxerJPA.toJPA(__bean.getLogoLateralFront()));
    // Fitxer
    this.setIcon(FitxerJPA.toJPA(__bean.getIcon()));
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

	public java.lang.Long getDescripcioID() {
		return(descripcioID);
	};
	public void setDescripcioID(java.lang.Long _descripcioID_) {
		this.descripcioID = _descripcioID_;
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

	public java.lang.String getColorMenu() {
		return(colorMenu);
	};
	public void setColorMenu(java.lang.String _colorMenu_) {
		this.colorMenu = _colorMenu_;
	};

	public long getLogoCapBackID() {
		return(logoCapBackID);
	};
	public void setLogoCapBackID(long _logoCapBackID_) {
		this.logoCapBackID = _logoCapBackID_;
	};

	public long getLogoPeuBackID() {
		return(logoPeuBackID);
	};
	public void setLogoPeuBackID(long _logoPeuBackID_) {
		this.logoPeuBackID = _logoPeuBackID_;
	};

	public long getLogoLateralFrontID() {
		return(logoLateralFrontID);
	};
	public void setLogoLateralFrontID(long _logoLateralFrontID_) {
		this.logoLateralFrontID = _logoLateralFrontID_;
	};

	public java.lang.String getVersio() {
		return(versio);
	};
	public void setVersio(java.lang.String _versio_) {
		this.versio = _versio_;
	};

	public long getIconID() {
		return(iconID);
	};
	public void setIconID(long _iconID_) {
		this.iconID = _iconID_;
	};

	public java.lang.String getWebEntitat() {
		return(webEntitat);
	};
	public void setWebEntitat(java.lang.String _webEntitat_) {
		this.webEntitat = _webEntitat_;
	};

	public java.lang.String getEntitatDescFront() {
		return(entitatDescFront);
	};
	public void setEntitatDescFront(java.lang.String _entitatDescFront_) {
		this.entitatDescFront = _entitatDescFront_;
	};

	public java.lang.String getSuportWeb() {
		return(suportWeb);
	};
	public void setSuportWeb(java.lang.String _suportWeb_) {
		this.suportWeb = _suportWeb_;
	};

	public java.lang.String getSuportTelefon() {
		return(suportTelefon);
	};
	public void setSuportTelefon(java.lang.String _suportTelefon_) {
		this.suportTelefon = _suportTelefon_;
	};

	public java.lang.String getSuportEmail() {
		return(suportEmail);
	};
	public void setSuportEmail(java.lang.String _suportEmail_) {
		this.suportEmail = _suportEmail_;
	};

	public java.lang.String getSuportFAQ() {
		return(suportFAQ);
	};
	public void setSuportFAQ(java.lang.String _suportFAQ_) {
		this.suportFAQ = _suportFAQ_;
	};

	public java.lang.String getSuportqssi() {
		return(suportqssi);
	};
	public void setSuportqssi(java.lang.String _suportqssi_) {
		this.suportqssi = _suportqssi_;
	};

	public java.lang.String getSuportautenticacio() {
		return(suportautenticacio);
	};
	public void setSuportautenticacio(java.lang.String _suportautenticacio_) {
		this.suportautenticacio = _suportautenticacio_;
	};

	public java.lang.Long getPluginLoginID() {
		return(pluginLoginID);
	};
	public void setPluginLoginID(java.lang.Long _pluginLoginID_) {
		this.pluginLoginID = _pluginLoginID_;
	};

	public java.lang.Long getLoginTextID() {
		return(loginTextID);
	};
	public void setLoginTextID(java.lang.Long _loginTextID_) {
		this.loginTextID = _loginTextID_;
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

	public java.lang.String getCommit() {
		return(commit);
	};
	public void setCommit(java.lang.String _commit_) {
		this.commit = _commit_;
	};

	public java.lang.String getAvisLegalCa() {
		return(avisLegalCa);
	};
	public void setAvisLegalCa(java.lang.String _avisLegalCa_) {
		this.avisLegalCa = _avisLegalCa_;
	};

	public java.lang.String getAvisLegalEs() {
		return(avisLegalEs);
	};
	public void setAvisLegalEs(java.lang.String _avisLegalEs_) {
		this.avisLegalEs = _avisLegalEs_;
	};

	public java.lang.String getAccessibilitatCa() {
		return(accessibilitatCa);
	};
	public void setAccessibilitatCa(java.lang.String _accessibilitatCa_) {
		this.accessibilitatCa = _accessibilitatCa_;
	};

	public java.lang.String getAccessibilitatEs() {
		return(accessibilitatEs);
	};
	public void setAccessibilitatEs(java.lang.String _accessibilitatEs_) {
		this.accessibilitatEs = _accessibilitatEs_;
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


// EXP  Field:entitatid | Table: car_notificacioapp | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "entitat")
    private Set<NotificacioAppJPA> notificacioApps = new HashSet<NotificacioAppJPA>(0);
    public  Set<NotificacioAppJPA> getNotificacioApps() {
    return this.notificacioApps;
  }

    public void setNotificacioApps(Set<NotificacioAppJPA> notificacioApps) {
      this.notificacioApps = notificacioApps;
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


// EXP  Field:entitatid | Table: car_preguntesfrequents | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "entitat")
    private Set<PreguntesFrequentsJPA> preguntesFrequentss = new HashSet<PreguntesFrequentsJPA>(0);
    public  Set<PreguntesFrequentsJPA> getPreguntesFrequentss() {
    return this.preguntesFrequentss;
  }

    public void setPreguntesFrequentss(Set<PreguntesFrequentsJPA> preguntesFrequentss) {
      this.preguntesFrequentss = preguntesFrequentss;
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


// EXP  Field:entitatid | Table: car_seccio | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "entitat")
    private Set<SeccioJPA> seccios = new HashSet<SeccioJPA>(0);
    public  Set<SeccioJPA> getSeccios() {
    return this.seccios;
  }

    public void setSeccios(Set<SeccioJPA> seccios) {
      this.seccios = seccios;
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
    @JoinColumn(name = "nomid", referencedColumnName ="traduccioID", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="car_entitat_traduccio_nom_fk"))
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
    @JoinColumn(name = "descripcioid", referencedColumnName ="traduccioID", nullable = true, insertable=false, updatable=false, foreignKey=@ForeignKey(name="car_entitat_traduccio_des_fk"))
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
    @JoinColumn(name = "logocapbackid", referencedColumnName ="fitxerID", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="car_entitat_fitxer_lcb_fk"))
    private FitxerJPA logoCapBack;

    public FitxerJPA getLogoCapBack() {
    return this.logoCapBack;
  }

    public  void setLogoCapBack(FitxerJPA logoCapBack) {
    this.logoCapBack = logoCapBack;
  }

// IMP Field:fitxerid | Table: car_fitxer | Type: 1  

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "logopeubackid", referencedColumnName ="fitxerID", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="car_entitat_fitxer_lpb_fk"))
    private FitxerJPA logoPeuBack;

    public FitxerJPA getLogoPeuBack() {
    return this.logoPeuBack;
  }

    public  void setLogoPeuBack(FitxerJPA logoPeuBack) {
    this.logoPeuBack = logoPeuBack;
  }

// IMP Field:fitxerid | Table: car_fitxer | Type: 1  

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "logolateralfrontid", referencedColumnName ="fitxerID", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="car_entitat_fitxer_llf_fk"))
    private FitxerJPA logoLateralFront;

    public FitxerJPA getLogoLateralFront() {
    return this.logoLateralFront;
  }

    public  void setLogoLateralFront(FitxerJPA logoLateralFront) {
    this.logoLateralFront = logoLateralFront;
  }

// IMP Field:fitxerid | Table: car_fitxer | Type: 1  

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "iconid", referencedColumnName ="fitxerID", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="car_entitat_fitxer_icon_fk"))
    private FitxerJPA icon;

    public FitxerJPA getIcon() {
    return this.icon;
  }

    public  void setIcon(FitxerJPA icon) {
    this.icon = icon;
  }

// IMP Field:pluginid | Table: car_plugin | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pluginloginid", referencedColumnName ="pluginID", nullable = true, insertable=false, updatable=false, foreignKey=@ForeignKey(name="car_entitat_plugin_login_fk"))
    private PluginJPA plugin;

    public PluginJPA getPlugin() {
    return this.plugin;
  }

    public  void setPlugin(PluginJPA plugin) {
    this.plugin = plugin;
  }

// IMP Field:traduccioid | Table: car_traduccio | Type: 1  

    @ManyToOne(fetch = FetchType.EAGER, cascade=javax.persistence.CascadeType.ALL)
    @JoinColumn(name = "logintextid", referencedColumnName ="traduccioID", nullable = true, insertable=false, updatable=false, foreignKey=@ForeignKey(name="car_entitat_traduccio_log_fk"))
    private TraduccioJPA loginText;

    public TraduccioJPA getLoginText() {
    return this.loginText;
  }

    public  void setLoginText(TraduccioJPA loginText) {
    this.loginText = loginText;
  }

  @javax.xml.bind.annotation.XmlTransient
  public java.util.Map<String, es.caib.carpeta.persistence.TraduccioMapJPA> getLoginTextTraduccions() {
    return this.loginText.getTraduccions();
  }

  public void setLoginTextTraduccions(java.util.Map<String, es.caib.carpeta.persistence.TraduccioMapJPA> __traduccions__) {
    this.loginText.setTraduccions(__traduccions__);
  }


// IMP Field:fitxerid | Table: car_fitxer | Type: 1  

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fitxercss", referencedColumnName ="fitxerID", nullable = true, insertable=false, updatable=false, foreignKey=@ForeignKey(name="car_entitat_fitxer_css_fk"))
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
    __tmp.setDescripcioID(__bean.getDescripcioID());
    __tmp.setCodi(__bean.getCodi());
    __tmp.setCodiDir3(__bean.getCodiDir3());
    __tmp.setActiva(__bean.isActiva());
    __tmp.setColorMenu(__bean.getColorMenu());
    __tmp.setLogoCapBackID(__bean.getLogoCapBackID());
    __tmp.setLogoPeuBackID(__bean.getLogoPeuBackID());
    __tmp.setLogoLateralFrontID(__bean.getLogoLateralFrontID());
    __tmp.setVersio(__bean.getVersio());
    __tmp.setIconID(__bean.getIconID());
    __tmp.setWebEntitat(__bean.getWebEntitat());
    __tmp.setEntitatDescFront(__bean.getEntitatDescFront());
    __tmp.setSuportWeb(__bean.getSuportWeb());
    __tmp.setSuportTelefon(__bean.getSuportTelefon());
    __tmp.setSuportEmail(__bean.getSuportEmail());
    __tmp.setSuportFAQ(__bean.getSuportFAQ());
    __tmp.setSuportqssi(__bean.getSuportqssi());
    __tmp.setSuportautenticacio(__bean.getSuportautenticacio());
    __tmp.setPluginLoginID(__bean.getPluginLoginID());
    __tmp.setLoginTextID(__bean.getLoginTextID());
    __tmp.setFitxerCssID(__bean.getFitxerCssID());
    __tmp.setContext(__bean.getContext());
    __tmp.setCommit(__bean.getCommit());
    __tmp.setAvisLegalCa(__bean.getAvisLegalCa());
    __tmp.setAvisLegalEs(__bean.getAvisLegalEs());
    __tmp.setAccessibilitatCa(__bean.getAccessibilitatCa());
    __tmp.setAccessibilitatEs(__bean.getAccessibilitatEs());
    // Fitxer
    __tmp.setLogoCapBack(FitxerJPA.toJPA(__bean.getLogoCapBack()));
    // Fitxer
    __tmp.setLogoPeuBack(FitxerJPA.toJPA(__bean.getLogoPeuBack()));
    // Fitxer
    __tmp.setLogoLateralFront(FitxerJPA.toJPA(__bean.getLogoLateralFront()));
    // Fitxer
    __tmp.setIcon(FitxerJPA.toJPA(__bean.getIcon()));
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
    if(!"PluginEntitatJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.pluginEntitats) || org.hibernate.Hibernate.isInitialized(__jpa.getPluginEntitats())) ) {
      __tmp.setPluginEntitats(PluginEntitatJPA.copyJPA(__jpa.getPluginEntitats(), __alreadyCopied,"EntitatJPA"));
    }
    if(!"PropietatGlobalJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.propietatGlobals) || org.hibernate.Hibernate.isInitialized(__jpa.getPropietatGlobals())) ) {
      __tmp.setPropietatGlobals(PropietatGlobalJPA.copyJPA(__jpa.getPropietatGlobals(), __alreadyCopied,"EntitatJPA"));
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
    if(!"SeccioJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.seccios) || org.hibernate.Hibernate.isInitialized(__jpa.getSeccios())) ) {
      __tmp.setSeccios(SeccioJPA.copyJPA(__jpa.getSeccios(), __alreadyCopied,"EntitatJPA"));
    }
    if(!"PreguntesFrequentsJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.preguntesFrequentss) || org.hibernate.Hibernate.isInitialized(__jpa.getPreguntesFrequentss())) ) {
      __tmp.setPreguntesFrequentss(PreguntesFrequentsJPA.copyJPA(__jpa.getPreguntesFrequentss(), __alreadyCopied,"EntitatJPA"));
    }
    if(!"NotificacioAppJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.notificacioApps) || org.hibernate.Hibernate.isInitialized(__jpa.getNotificacioApps())) ) {
      __tmp.setNotificacioApps(NotificacioAppJPA.copyJPA(__jpa.getNotificacioApps(), __alreadyCopied,"EntitatJPA"));
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
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.loginText) || org.hibernate.Hibernate.isInitialized(__jpa.getLoginText()) ) ) {
      __tmp.setLoginText(TraduccioJPA.copyJPA(__jpa.getLoginText(), __alreadyCopied,"EntitatJPA"));
    }
    if(!"TraduccioJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.descripcio) || org.hibernate.Hibernate.isInitialized(__jpa.getDescripcio()) ) ) {
      __tmp.setDescripcio(TraduccioJPA.copyJPA(__jpa.getDescripcio(), __alreadyCopied,"EntitatJPA"));
    }
    if(!"TraduccioJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.nom) || org.hibernate.Hibernate.isInitialized(__jpa.getNom()) ) ) {
      __tmp.setNom(TraduccioJPA.copyJPA(__jpa.getNom(), __alreadyCopied,"EntitatJPA"));
    }
    if(!"PluginJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.plugin) || org.hibernate.Hibernate.isInitialized(__jpa.getPlugin()) ) ) {
      __tmp.setPlugin(PluginJPA.copyJPA(__jpa.getPlugin(), __alreadyCopied,"EntitatJPA"));
    }

    return __tmp;
  }




}
