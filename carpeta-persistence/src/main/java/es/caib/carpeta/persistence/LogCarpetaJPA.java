
package es.caib.carpeta.persistence;
import es.caib.carpeta.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Lob;
import javax.persistence.GeneratedValue;
import org.hibernate.annotations.Index;
import javax.persistence.SequenceGenerator;
import org.hibernate.annotations.Type;
import javax.persistence.Id;


@SuppressWarnings("deprecation")
@Entity
@Table(name = "car_log" )
@SequenceGenerator(name="LOGCARPETA_SEQ", sequenceName="car_log_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class LogCarpetaJPA implements LogCarpeta {



private static final long serialVersionUID = 1140880713L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="LOGCARPETA_SEQ")
	@Index(name="car_log_pk_i")
	@Column(name="logid",nullable = false,length = 19)
	long logID;

	@Column(name="descripcio",nullable = false,length = 2000)
	java.lang.String descripcio;

	@Column(name="tipus",nullable = false,length = 10)
	int tipus;

	@Column(name="estat",nullable = false,length = 10)
	int estat;

	@Index(name="car_log_pluginid_fk_i")
	@Column(name="pluginid",length = 19)
	java.lang.Long pluginID;

	@Column(name="entitatcodi",length = 9)
	java.lang.String entitatCodi;

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
  public LogCarpetaJPA(long logID , java.lang.String descripcio , int tipus , int estat , java.lang.Long pluginID , java.lang.String entitatCodi , java.lang.Long temps , java.sql.Timestamp dataInici , java.lang.String peticio , java.lang.String error , java.lang.String excepcio) {
    this.logID=logID;
    this.descripcio=descripcio;
    this.tipus=tipus;
    this.estat=estat;
    this.pluginID=pluginID;
    this.entitatCodi=entitatCodi;
    this.temps=temps;
    this.dataInici=dataInici;
    this.peticio=peticio;
    this.error=error;
    this.excepcio=excepcio;
}
  /** Constructor sense valors autoincrementals */
  public LogCarpetaJPA(java.lang.String descripcio , int tipus , int estat , java.lang.Long pluginID , java.lang.String entitatCodi , java.lang.Long temps , java.sql.Timestamp dataInici , java.lang.String peticio , java.lang.String error , java.lang.String excepcio) {
    this.descripcio=descripcio;
    this.tipus=tipus;
    this.estat=estat;
    this.pluginID=pluginID;
    this.entitatCodi=entitatCodi;
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
    this.setTipus(__bean.getTipus());
    this.setEstat(__bean.getEstat());
    this.setPluginID(__bean.getPluginID());
    this.setEntitatCodi(__bean.getEntitatCodi());
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

	public java.lang.Long getPluginID() {
		return(pluginID);
	};
	public void setPluginID(java.lang.Long _pluginID_) {
		this.pluginID = _pluginID_;
	};

	public java.lang.String getEntitatCodi() {
		return(entitatCodi);
	};
	public void setEntitatCodi(java.lang.String _entitatCodi_) {
		this.entitatCodi = _entitatCodi_;
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


 // ---------------  STATIC METHODS ------------------
  public static LogCarpetaJPA toJPA(LogCarpeta __bean) {
    if (__bean == null) { return null;}
    LogCarpetaJPA __tmp = new LogCarpetaJPA();
    __tmp.setLogID(__bean.getLogID());
    __tmp.setDescripcio(__bean.getDescripcio());
    __tmp.setTipus(__bean.getTipus());
    __tmp.setEstat(__bean.getEstat());
    __tmp.setPluginID(__bean.getPluginID());
    __tmp.setEntitatCodi(__bean.getEntitatCodi());
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

    return __tmp;
  }




}
