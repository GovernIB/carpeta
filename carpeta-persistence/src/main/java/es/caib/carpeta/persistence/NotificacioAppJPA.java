
package es.caib.carpeta.persistence;
import es.caib.carpeta.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Index;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import org.hibernate.annotations.Type;
import javax.persistence.Id;


@Entity(name = "NotificacioAppJPA")
@Table(name = "car_notificacioapp" , indexes = { 
        @Index(name="car_notificacioapp_pk_i", columnList = "notificacioappid"),
        @Index(name="car_notifica_titolid_fk_i", columnList = "titolid"),
        @Index(name="car_notifica_missatgeid_fk_i", columnList = "missatgeid"),
        @Index(name="car_notifica_pluginid_fk_i", columnList = "frontpluginid"),
        @Index(name="car_notifica_entitatid_fk_i", columnList = "entitatid")})
@SequenceGenerator(name="NOTIFICACIOAPP_SEQ", sequenceName="car_notificacioapp_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class NotificacioAppJPA implements NotificacioApp {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="NOTIFICACIOAPP_SEQ")
    @Column(name="notificacioappid",nullable = false,length = 19)
    long notificacioAppID;

    @Column(name="codi",nullable = false,unique = true,length = 50)
    java.lang.String codi;

    @Column(name="titolid",nullable = false,length = 19)
    long titolID;

    @Column(name="missatgeid",nullable = false,length = 19)
    long missatgeID;

    @Column(name="frontpluginid",length = 19)
    java.lang.Long frontPluginID;

    @Column(name="ajuda",length = 2147483647)
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    java.lang.String ajuda;

    @Column(name="activa",nullable = false,length = 1)
    boolean activa;

    @Column(name="entitatid",nullable = false,length = 19)
    long entitatID;



  /** Constructor Buit */
  public NotificacioAppJPA() {
  }

  /** Constructor amb tots els camps  */
  public NotificacioAppJPA(long notificacioAppID , java.lang.String codi , long titolID , long missatgeID , java.lang.Long frontPluginID , java.lang.String ajuda , boolean activa , long entitatID) {
    this.notificacioAppID=notificacioAppID;
    this.codi=codi;
    this.titolID=titolID;
    this.missatgeID=missatgeID;
    this.frontPluginID=frontPluginID;
    this.ajuda=ajuda;
    this.activa=activa;
    this.entitatID=entitatID;
}
  /** Constructor sense valors autoincrementals */
  public NotificacioAppJPA(java.lang.String codi , long titolID , long missatgeID , java.lang.Long frontPluginID , java.lang.String ajuda , boolean activa , long entitatID) {
    this.codi=codi;
    this.titolID=titolID;
    this.missatgeID=missatgeID;
    this.frontPluginID=frontPluginID;
    this.ajuda=ajuda;
    this.activa=activa;
    this.entitatID=entitatID;
}
  /** Constructor dels valors Not Null */
  public NotificacioAppJPA(long notificacioAppID , java.lang.String codi , long titolID , long missatgeID , boolean activa , long entitatID) {
    this.notificacioAppID=notificacioAppID;
    this.codi=codi;
    this.titolID=titolID;
    this.missatgeID=missatgeID;
    this.activa=activa;
    this.entitatID=entitatID;
}
  public NotificacioAppJPA(NotificacioApp __bean) {
    this.setNotificacioAppID(__bean.getNotificacioAppID());
    this.setCodi(__bean.getCodi());
    this.setTitolID(__bean.getTitolID());
    this.setMissatgeID(__bean.getMissatgeID());
    this.setFrontPluginID(__bean.getFrontPluginID());
    this.setAjuda(__bean.getAjuda());
    this.setActiva(__bean.isActiva());
    this.setEntitatID(__bean.getEntitatID());
	}

	public long getNotificacioAppID() {
		return(notificacioAppID);
	};
	public void setNotificacioAppID(long _notificacioAppID_) {
		this.notificacioAppID = _notificacioAppID_;
	};

	public java.lang.String getCodi() {
		return(codi);
	};
	public void setCodi(java.lang.String _codi_) {
		this.codi = _codi_;
	};

	public long getTitolID() {
		return(titolID);
	};
	public void setTitolID(long _titolID_) {
		this.titolID = _titolID_;
	};

	public long getMissatgeID() {
		return(missatgeID);
	};
	public void setMissatgeID(long _missatgeID_) {
		this.missatgeID = _missatgeID_;
	};

	public java.lang.Long getFrontPluginID() {
		return(frontPluginID);
	};
	public void setFrontPluginID(java.lang.Long _frontPluginID_) {
		this.frontPluginID = _frontPluginID_;
	};

	public java.lang.String getAjuda() {
		return(ajuda);
	};
	public void setAjuda(java.lang.String _ajuda_) {
		this.ajuda = _ajuda_;
	};

	public boolean isActiva() {
		return(activa);
	};
	public void setActiva(boolean _activa_) {
		this.activa = _activa_;
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
    if (__obj != null && __obj instanceof NotificacioApp) {
      NotificacioApp __instance = (NotificacioApp)__obj;
      __result = true;
      __result = __result && (this.getNotificacioAppID() == __instance.getNotificacioAppID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// IMP Field:traduccioid | Table: car_traduccio | Type: 1  

    @ManyToOne(fetch = FetchType.EAGER, cascade=javax.persistence.CascadeType.ALL)
    @JoinColumn(name = "titolid", referencedColumnName ="traduccioID", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="car_notifica_traduccio_tit_fk"))
    private TraduccioJPA titol;

    public TraduccioJPA getTitol() {
    return this.titol;
  }

    public  void setTitol(TraduccioJPA titol) {
    this.titol = titol;
  }

  @javax.xml.bind.annotation.XmlTransient
  public java.util.Map<String, es.caib.carpeta.persistence.TraduccioMapJPA> getTitolTraduccions() {
    return this.titol.getTraduccions();
  }

  public void setTitolTraduccions(java.util.Map<String, es.caib.carpeta.persistence.TraduccioMapJPA> __traduccions__) {
    this.titol.setTraduccions(__traduccions__);
  }


// IMP Field:traduccioid | Table: car_traduccio | Type: 1  

    @ManyToOne(fetch = FetchType.EAGER, cascade=javax.persistence.CascadeType.ALL)
    @JoinColumn(name = "missatgeid", referencedColumnName ="traduccioID", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="car_notifica_traduccio_msg_fk"))
    private TraduccioJPA missatge;

    public TraduccioJPA getMissatge() {
    return this.missatge;
  }

    public  void setMissatge(TraduccioJPA missatge) {
    this.missatge = missatge;
  }

  @javax.xml.bind.annotation.XmlTransient
  public java.util.Map<String, es.caib.carpeta.persistence.TraduccioMapJPA> getMissatgeTraduccions() {
    return this.missatge.getTraduccions();
  }

  public void setMissatgeTraduccions(java.util.Map<String, es.caib.carpeta.persistence.TraduccioMapJPA> __traduccions__) {
    this.missatge.setTraduccions(__traduccions__);
  }


// IMP Field:pluginid | Table: car_plugin | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "frontpluginid", referencedColumnName ="pluginID", nullable = true, insertable=false, updatable=false, foreignKey=@ForeignKey(name="car_notifica_plugin_plug_fk"))
    private PluginJPA plugin;

    public PluginJPA getPlugin() {
    return this.plugin;
  }

    public  void setPlugin(PluginJPA plugin) {
    this.plugin = plugin;
  }

// IMP Field:entitatid | Table: car_entitat | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entitatid", referencedColumnName ="entitatID", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="car_notifica_entitat_ent_fk"))
    private EntitatJPA entitat;

    public EntitatJPA getEntitat() {
    return this.entitat;
  }

    public  void setEntitat(EntitatJPA entitat) {
    this.entitat = entitat;
  }


 // ---------------  STATIC METHODS ------------------
  public static NotificacioAppJPA toJPA(NotificacioApp __bean) {
    if (__bean == null) { return null;}
    NotificacioAppJPA __tmp = new NotificacioAppJPA();
    __tmp.setNotificacioAppID(__bean.getNotificacioAppID());
    __tmp.setCodi(__bean.getCodi());
    __tmp.setTitolID(__bean.getTitolID());
    __tmp.setMissatgeID(__bean.getMissatgeID());
    __tmp.setFrontPluginID(__bean.getFrontPluginID());
    __tmp.setAjuda(__bean.getAjuda());
    __tmp.setActiva(__bean.isActiva());
    __tmp.setEntitatID(__bean.getEntitatID());
		return __tmp;
	}


  public static NotificacioAppJPA copyJPA(NotificacioAppJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<NotificacioAppJPA> copyJPA(java.util.Set<NotificacioAppJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<NotificacioAppJPA> __tmpSet = (java.util.Set<NotificacioAppJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<NotificacioAppJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (NotificacioAppJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static NotificacioAppJPA copyJPA(NotificacioAppJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    NotificacioAppJPA __tmp = (NotificacioAppJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    // Copia de beans complexes (IMP)
    if(!"TraduccioJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.missatge) || org.hibernate.Hibernate.isInitialized(__jpa.getMissatge()) ) ) {
      __tmp.setMissatge(TraduccioJPA.copyJPA(__jpa.getMissatge(), __alreadyCopied,"NotificacioAppJPA"));
    }
    if(!"TraduccioJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.titol) || org.hibernate.Hibernate.isInitialized(__jpa.getTitol()) ) ) {
      __tmp.setTitol(TraduccioJPA.copyJPA(__jpa.getTitol(), __alreadyCopied,"NotificacioAppJPA"));
    }
    if(!"EntitatJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.entitat) || org.hibernate.Hibernate.isInitialized(__jpa.getEntitat()) ) ) {
      __tmp.setEntitat(EntitatJPA.copyJPA(__jpa.getEntitat(), __alreadyCopied,"NotificacioAppJPA"));
    }
    if(!"PluginJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.plugin) || org.hibernate.Hibernate.isInitialized(__jpa.getPlugin()) ) ) {
      __tmp.setPlugin(PluginJPA.copyJPA(__jpa.getPlugin(), __alreadyCopied,"NotificacioAppJPA"));
    }

    return __tmp;
  }




}
