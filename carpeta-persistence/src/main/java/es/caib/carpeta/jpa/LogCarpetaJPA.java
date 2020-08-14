
package es.caib.carpeta.jpa;
import es.caib.carpeta.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.GeneratedValue;
import org.hibernate.annotations.Index;
import javax.persistence.SequenceGenerator;
import org.hibernate.annotations.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import org.hibernate.annotations.Type;
import javax.persistence.Id;


@SuppressWarnings("deprecation")
@Entity
@Table(name = "car_log" )
@SequenceGenerator(name="CARPETA_SEQ", sequenceName="car_carpeta_seq", allocationSize=1)
@javax.xml.bind.annotation.XmlRootElement
public class LogCarpetaJPA implements LogCarpeta {



private static final long serialVersionUID = 1140880713L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="CARPETA_SEQ")
	@Index(name="car_log_pk_i")
	@Column(name="logid",nullable = false,length = 19)
	long logID;

	@Column(name="descripcio",nullable = false,length = 2000)
	java.lang.String descripcio;

	@Index(name="car_log_entitatid_fk_i")
	@Column(name="entitatid",length = 19)
	java.lang.Long entitatID;

	@Index(name="car_log_pluginid_fk_i")
	@Column(name="pluginid",length = 19)
	java.lang.Long pluginID;

	@Column(name="tipus",nullable = false,length = 10)
	int tipus;

	@Column(name="estat",nullable = false,length = 10)
	int estat;

	@Column(name="temps",length = 19)
	java.lang.Long temps;

	@Column(name="datainici",nullable = false,length = 29,precision = 6)
	java.sql.Timestamp dataInici;

	@Column(name="peticio",length = 255)
	java.lang.String peticio;

	@Column(name="error",length = 2000)
	java.lang.String error;

	@Column(name="excepcio",length = 2147483647)
    @Lob
    @Type(type = "org.hibernate.type.TextType")
	java.lang.String excepcio;



  /** Constructor Buit */
  public LogCarpetaJPA() {
  }

  /** Constructor amb tots els camps  */
  public LogCarpetaJPA(long logID , java.lang.String descripcio , java.lang.Long entitatID , java.lang.Long pluginID , int tipus , int estat , java.lang.Long temps , java.sql.Timestamp dataInici , java.lang.String peticio , java.lang.String error , java.lang.String excepcio) {
    this.logID=logID;
    this.descripcio=descripcio;
    this.entitatID=entitatID;
    this.pluginID=pluginID;
    this.tipus=tipus;
    this.estat=estat;
    this.temps=temps;
    this.dataInici=dataInici;
    this.peticio=peticio;
    this.error=error;
    this.excepcio=excepcio;
}
  /** Constructor sense valors autoincrementals */
  public LogCarpetaJPA(java.lang.String descripcio , java.lang.Long entitatID , java.lang.Long pluginID , int tipus , int estat , java.lang.Long temps , java.sql.Timestamp dataInici , java.lang.String peticio , java.lang.String error , java.lang.String excepcio) {
    this.descripcio=descripcio;
    this.entitatID=entitatID;
    this.pluginID=pluginID;
    this.tipus=tipus;
    this.estat=estat;
    this.temps=temps;
    this.dataInici=dataInici;
    this.peticio=peticio;
    this.error=error;
    this.excepcio=excepcio;
}
  /** Constructor dels valors Not Null */
  public LogCarpetaJPA(long logID , java.lang.String descripcio , int tipus , int estat , java.sql.Timestamp dataInici) {
    this.logID=logID;
    this.descripcio=descripcio;
    this.tipus=tipus;
    this.estat=estat;
    this.dataInici=dataInici;
}
  public LogCarpetaJPA(LogCarpeta __bean) {
    this.setLogID(__bean.getLogID());
    this.setDescripcio(__bean.getDescripcio());
    this.setEntitatID(__bean.getEntitatID());
    this.setPluginID(__bean.getPluginID());
    this.setTipus(__bean.getTipus());
    this.setEstat(__bean.getEstat());
    this.setTemps(__bean.getTemps());
    this.setDataInici(__bean.getDataInici());
    this.setPeticio(__bean.getPeticio());
    this.setError(__bean.getError());
    this.setExcepcio(__bean.getExcepcio());
	}

	public long getLogID() {
		return(logID);
	};
	public void setLogID(long _logID_) {
		this.logID = _logID_;
	};

	public java.lang.String getDescripcio() {
		return(descripcio);
	};
	public void setDescripcio(java.lang.String _descripcio_) {
		this.descripcio = _descripcio_;
	};

	public java.lang.Long getEntitatID() {
		return(entitatID);
	};
	public void setEntitatID(java.lang.Long _entitatID_) {
		this.entitatID = _entitatID_;
	};

	public java.lang.Long getPluginID() {
		return(pluginID);
	};
	public void setPluginID(java.lang.Long _pluginID_) {
		this.pluginID = _pluginID_;
	};

	public int getTipus() {
		return(tipus);
	};
	public void setTipus(int _tipus_) {
		this.tipus = _tipus_;
	};

	public int getEstat() {
		return(estat);
	};
	public void setEstat(int _estat_) {
		this.estat = _estat_;
	};

	public java.lang.Long getTemps() {
		return(temps);
	};
	public void setTemps(java.lang.Long _temps_) {
		this.temps = _temps_;
	};

	public java.sql.Timestamp getDataInici() {
		return(dataInici);
	};
	public void setDataInici(java.sql.Timestamp _dataInici_) {
		this.dataInici = _dataInici_;
	};

	public java.lang.String getPeticio() {
		return(peticio);
	};
	public void setPeticio(java.lang.String _peticio_) {
		this.peticio = _peticio_;
	};

	public java.lang.String getError() {
		return(error);
	};
	public void setError(java.lang.String _error_) {
		this.error = _error_;
	};

	public java.lang.String getExcepcio() {
		return(excepcio);
	};
	public void setExcepcio(java.lang.String _excepcio_) {
		this.excepcio = _excepcio_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof LogCarpeta) {
      LogCarpeta __instance = (LogCarpeta)__obj;
      __result = true;
      __result = __result && (this.getLogID() == __instance.getLogID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// IMP Field:entitatid | Table: car_entitat | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="car_log_entitat_ent_fk")
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
	@ForeignKey(name="car_log_plugin_plu_fk")
	@JoinColumn(name = "pluginid", referencedColumnName ="pluginID", nullable = true, insertable=false, updatable=false)
	private PluginJPA plugin;

	public PluginJPA getPlugin() {
    return this.plugin;
  }

	public  void setPlugin(PluginJPA plugin) {
    this.plugin = plugin;
  }


 // ---------------  STATIC METHODS ------------------
  public static LogCarpetaJPA toJPA(LogCarpeta __bean) {
    if (__bean == null) { return null;}
    LogCarpetaJPA __tmp = new LogCarpetaJPA();
    __tmp.setLogID(__bean.getLogID());
    __tmp.setDescripcio(__bean.getDescripcio());
    __tmp.setEntitatID(__bean.getEntitatID());
    __tmp.setPluginID(__bean.getPluginID());
    __tmp.setTipus(__bean.getTipus());
    __tmp.setEstat(__bean.getEstat());
    __tmp.setTemps(__bean.getTemps());
    __tmp.setDataInici(__bean.getDataInici());
    __tmp.setPeticio(__bean.getPeticio());
    __tmp.setError(__bean.getError());
    __tmp.setExcepcio(__bean.getExcepcio());
		return __tmp;
	}


  public static LogCarpetaJPA copyJPA(LogCarpetaJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<LogCarpetaJPA> copyJPA(java.util.Set<LogCarpetaJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    @SuppressWarnings("unchecked")
    java.util.Set<LogCarpetaJPA> __tmpSet = (java.util.Set<LogCarpetaJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<LogCarpetaJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (LogCarpetaJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static LogCarpetaJPA copyJPA(LogCarpetaJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    LogCarpetaJPA __tmp = (LogCarpetaJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    // Copia de beans complexes (IMP)
    if(!"EntitatJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.entitat) || org.hibernate.Hibernate.isInitialized(__jpa.getEntitat()) ) ) {
      __tmp.setEntitat(EntitatJPA.copyJPA(__jpa.getEntitat(), __alreadyCopied,"LogCarpetaJPA"));
    }
    if(!"PluginJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.plugin) || org.hibernate.Hibernate.isInitialized(__jpa.getPlugin()) ) ) {
      __tmp.setPlugin(PluginJPA.copyJPA(__jpa.getPlugin(), __alreadyCopied,"LogCarpetaJPA"));
    }

    return __tmp;
  }




}
