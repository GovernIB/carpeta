
package es.caib.carpeta.persistence;
import es.caib.carpeta.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.UniqueConstraint;
import javax.persistence.GeneratedValue;
import org.hibernate.annotations.Index;
import javax.persistence.SequenceGenerator;
import org.hibernate.annotations.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import javax.persistence.Id;


@SuppressWarnings("deprecation")
@Entity
@Table(name = "car_pluginentitat"  , uniqueConstraints = {
            @UniqueConstraint(name="car_plugent_plug_ent_uk", columnNames={"pluginid","entitatid"}) } )
@SequenceGenerator(name="PLUGINENTITAT_SEQ", sequenceName="car_pluginentitat_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class PluginEntitatJPA implements PluginEntitat {



private static final long serialVersionUID = -695907131L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PLUGINENTITAT_SEQ")
	@Index(name="car_pluginentitat_pk_i")
	@Column(name="pluginentitatid",nullable = false,length = 19)
	long pluginEntitatID;

	@Index(name="car_plugent_pluginid_fk_i")
	@Column(name="pluginid",nullable = false,length = 19)
	long pluginID;

	@Index(name="car_plugent_entitatid_fk_i")
	@Column(name="entitatid",nullable = false,length = 19)
	long entitatID;

	@Column(name="actiu",nullable = false,length = 1)
	boolean actiu;



  /** Constructor Buit */
  public PluginEntitatJPA() {
  }

  /** Constructor amb tots els camps  */
  public PluginEntitatJPA(long pluginEntitatID , long pluginID , long entitatID , boolean actiu) {
    this.pluginEntitatID=pluginEntitatID;
    this.pluginID=pluginID;
    this.entitatID=entitatID;
    this.actiu=actiu;
}
  /** Constructor sense valors autoincrementals */
  public PluginEntitatJPA(long pluginID , long entitatID , boolean actiu) {
    this.pluginID=pluginID;
    this.entitatID=entitatID;
    this.actiu=actiu;
}
  public PluginEntitatJPA(PluginEntitat __bean) {
    this.setPluginEntitatID(__bean.getPluginEntitatID());
    this.setPluginID(__bean.getPluginID());
    this.setEntitatID(__bean.getEntitatID());
    this.setActiu(__bean.isActiu());
	}

	public long getPluginEntitatID() {
		return(pluginEntitatID);
	};
	public void setPluginEntitatID(long _pluginEntitatID_) {
		this.pluginEntitatID = _pluginEntitatID_;
	};

	public long getPluginID() {
		return(pluginID);
	};
	public void setPluginID(long _pluginID_) {
		this.pluginID = _pluginID_;
	};

	public long getEntitatID() {
		return(entitatID);
	};
	public void setEntitatID(long _entitatID_) {
		this.entitatID = _entitatID_;
	};

	public boolean isActiu() {
		return(actiu);
	};
	public void setActiu(boolean _actiu_) {
		this.actiu = _actiu_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof PluginEntitat) {
      PluginEntitat __instance = (PluginEntitat)__obj;
      __result = true;
      __result = __result && (this.getPluginEntitatID() == __instance.getPluginEntitatID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// IMP Field:pluginid | Table: car_plugin | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="car_plugent_plugin_fk")
	@JoinColumn(name = "pluginid", referencedColumnName ="pluginID", nullable = false, insertable=false, updatable=false)
	private PluginJPA plugin;

	public PluginJPA getPlugin() {
    return this.plugin;
  }

	public  void setPlugin(PluginJPA plugin) {
    this.plugin = plugin;
  }

// IMP Field:entitatid | Table: car_entitat | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="car_plugent_entitat_fk")
	@JoinColumn(name = "entitatid", referencedColumnName ="entitatID", nullable = false, insertable=false, updatable=false)
	private EntitatJPA entitat;

	public EntitatJPA getEntitat() {
    return this.entitat;
  }

	public  void setEntitat(EntitatJPA entitat) {
    this.entitat = entitat;
  }


 // ---------------  STATIC METHODS ------------------
  public static PluginEntitatJPA toJPA(PluginEntitat __bean) {
    if (__bean == null) { return null;}
    PluginEntitatJPA __tmp = new PluginEntitatJPA();
    __tmp.setPluginEntitatID(__bean.getPluginEntitatID());
    __tmp.setPluginID(__bean.getPluginID());
    __tmp.setEntitatID(__bean.getEntitatID());
    __tmp.setActiu(__bean.isActiu());
		return __tmp;
	}


  public static PluginEntitatJPA copyJPA(PluginEntitatJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<PluginEntitatJPA> copyJPA(java.util.Set<PluginEntitatJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    @SuppressWarnings("unchecked")
    java.util.Set<PluginEntitatJPA> __tmpSet = (java.util.Set<PluginEntitatJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<PluginEntitatJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (PluginEntitatJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static PluginEntitatJPA copyJPA(PluginEntitatJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    PluginEntitatJPA __tmp = (PluginEntitatJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    // Copia de beans complexes (IMP)
    if(!"EntitatJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.entitat) || org.hibernate.Hibernate.isInitialized(__jpa.getEntitat()) ) ) {
      __tmp.setEntitat(EntitatJPA.copyJPA(__jpa.getEntitat(), __alreadyCopied,"PluginEntitatJPA"));
    }
    if(!"PluginJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.plugin) || org.hibernate.Hibernate.isInitialized(__jpa.getPlugin()) ) ) {
      __tmp.setPlugin(PluginJPA.copyJPA(__jpa.getPlugin(), __alreadyCopied,"PluginEntitatJPA"));
    }

    return __tmp;
  }




}
