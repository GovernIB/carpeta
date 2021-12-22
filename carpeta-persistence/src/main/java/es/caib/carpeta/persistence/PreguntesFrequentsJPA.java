
package es.caib.carpeta.persistence;
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


@Entity
@Table(name = "car_preguntesfrequents" )
@SequenceGenerator(name="PREGUNTESFREQUENTS_SEQ", sequenceName="car_preguntesfrequents_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class PreguntesFrequentsJPA implements PreguntesFrequents {



private static final long serialVersionUID = 1787206139L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PREGUNTESFREQUENTS_SEQ")
    @Index(name="car_preguntesfrequents_pk_i")
    @Column(name="preguntesfrequentsid",nullable = false,length = 19)
    long preguntesFrequentsID;

    @Index(name="car_faq_enunciatid_fk_i")
    @Column(name="enunciatid",nullable = false,length = 19)
    long enunciatID;

    @Index(name="car_faq_respostaid_fk_i")
    @Column(name="respostaid",nullable = false,length = 19)
    long respostaID;

    @Column(name="ordre",nullable = false,length = 10)
    int ordre;

    @Index(name="car_faq_entitatid_fk_i")
    @Column(name="entitatid",nullable = false,length = 19)
    long entitatID;



  /** Constructor Buit */
  public PreguntesFrequentsJPA() {
  }

  /** Constructor amb tots els camps  */
  public PreguntesFrequentsJPA(long preguntesFrequentsID , long enunciatID , long respostaID , int ordre , long entitatID) {
    this.preguntesFrequentsID=preguntesFrequentsID;
    this.enunciatID=enunciatID;
    this.respostaID=respostaID;
    this.ordre=ordre;
    this.entitatID=entitatID;
}
  /** Constructor sense valors autoincrementals */
  public PreguntesFrequentsJPA(long enunciatID , long respostaID , int ordre , long entitatID) {
    this.enunciatID=enunciatID;
    this.respostaID=respostaID;
    this.ordre=ordre;
    this.entitatID=entitatID;
}
  public PreguntesFrequentsJPA(PreguntesFrequents __bean) {
    this.setPreguntesFrequentsID(__bean.getPreguntesFrequentsID());
    this.setEnunciatID(__bean.getEnunciatID());
    this.setRespostaID(__bean.getRespostaID());
    this.setOrdre(__bean.getOrdre());
    this.setEntitatID(__bean.getEntitatID());
	}

	public long getPreguntesFrequentsID() {
		return(preguntesFrequentsID);
	};
	public void setPreguntesFrequentsID(long _preguntesFrequentsID_) {
		this.preguntesFrequentsID = _preguntesFrequentsID_;
	};

	public long getEnunciatID() {
		return(enunciatID);
	};
	public void setEnunciatID(long _enunciatID_) {
		this.enunciatID = _enunciatID_;
	};

	public long getRespostaID() {
		return(respostaID);
	};
	public void setRespostaID(long _respostaID_) {
		this.respostaID = _respostaID_;
	};

	public int getOrdre() {
		return(ordre);
	};
	public void setOrdre(int _ordre_) {
		this.ordre = _ordre_;
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
    if (__obj != null && __obj instanceof PreguntesFrequents) {
      PreguntesFrequents __instance = (PreguntesFrequents)__obj;
      __result = true;
      __result = __result && (this.getPreguntesFrequentsID() == __instance.getPreguntesFrequentsID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// IMP Field:traduccioid | Table: car_traduccio | Type: 1  

    @ManyToOne(fetch = FetchType.EAGER, cascade=javax.persistence.CascadeType.ALL)
    @ForeignKey(name="car_faq_traduccio_enun_fk")
    @JoinColumn(name = "enunciatid", referencedColumnName ="traduccioID", nullable = false, insertable=false, updatable=false)
    private TraduccioJPA enunciat;

    public TraduccioJPA getEnunciat() {
    return this.enunciat;
  }

    public  void setEnunciat(TraduccioJPA enunciat) {
    this.enunciat = enunciat;
  }

  @javax.xml.bind.annotation.XmlTransient
  public java.util.Map<String, es.caib.carpeta.persistence.TraduccioMapJPA> getEnunciatTraduccions() {
    return this.enunciat.getTraduccions();
  }

  public void setEnunciatTraduccions(java.util.Map<String, es.caib.carpeta.persistence.TraduccioMapJPA> __traduccions__) {
    this.enunciat.setTraduccions(__traduccions__);
  }


// IMP Field:traduccioid | Table: car_traduccio | Type: 1  

    @ManyToOne(fetch = FetchType.EAGER, cascade=javax.persistence.CascadeType.ALL)
    @ForeignKey(name="car_faq_traduccio_resp_fk")
    @JoinColumn(name = "respostaid", referencedColumnName ="traduccioID", nullable = false, insertable=false, updatable=false)
    private TraduccioJPA resposta;

    public TraduccioJPA getResposta() {
    return this.resposta;
  }

    public  void setResposta(TraduccioJPA resposta) {
    this.resposta = resposta;
  }

  @javax.xml.bind.annotation.XmlTransient
  public java.util.Map<String, es.caib.carpeta.persistence.TraduccioMapJPA> getRespostaTraduccions() {
    return this.resposta.getTraduccions();
  }

  public void setRespostaTraduccions(java.util.Map<String, es.caib.carpeta.persistence.TraduccioMapJPA> __traduccions__) {
    this.resposta.setTraduccions(__traduccions__);
  }


// IMP Field:entitatid | Table: car_entitat | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @ForeignKey(name="car_faq_entitat_ent_fk")
    @JoinColumn(name = "entitatid", referencedColumnName ="entitatID", nullable = false, insertable=false, updatable=false)
    private EntitatJPA entitat;

    public EntitatJPA getEntitat() {
    return this.entitat;
  }

    public  void setEntitat(EntitatJPA entitat) {
    this.entitat = entitat;
  }


 // ---------------  STATIC METHODS ------------------
  public static PreguntesFrequentsJPA toJPA(PreguntesFrequents __bean) {
    if (__bean == null) { return null;}
    PreguntesFrequentsJPA __tmp = new PreguntesFrequentsJPA();
    __tmp.setPreguntesFrequentsID(__bean.getPreguntesFrequentsID());
    __tmp.setEnunciatID(__bean.getEnunciatID());
    __tmp.setRespostaID(__bean.getRespostaID());
    __tmp.setOrdre(__bean.getOrdre());
    __tmp.setEntitatID(__bean.getEntitatID());
		return __tmp;
	}


  public static PreguntesFrequentsJPA copyJPA(PreguntesFrequentsJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<PreguntesFrequentsJPA> copyJPA(java.util.Set<PreguntesFrequentsJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<PreguntesFrequentsJPA> __tmpSet = (java.util.Set<PreguntesFrequentsJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<PreguntesFrequentsJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (PreguntesFrequentsJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static PreguntesFrequentsJPA copyJPA(PreguntesFrequentsJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    PreguntesFrequentsJPA __tmp = (PreguntesFrequentsJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    // Copia de beans complexes (IMP)
    if(!"TraduccioJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.resposta) || org.hibernate.Hibernate.isInitialized(__jpa.getResposta()) ) ) {
      __tmp.setResposta(TraduccioJPA.copyJPA(__jpa.getResposta(), __alreadyCopied,"PreguntesFrequentsJPA"));
    }
    if(!"TraduccioJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.enunciat) || org.hibernate.Hibernate.isInitialized(__jpa.getEnunciat()) ) ) {
      __tmp.setEnunciat(TraduccioJPA.copyJPA(__jpa.getEnunciat(), __alreadyCopied,"PreguntesFrequentsJPA"));
    }
    if(!"EntitatJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.entitat) || org.hibernate.Hibernate.isInitialized(__jpa.getEntitat()) ) ) {
      __tmp.setEntitat(EntitatJPA.copyJPA(__jpa.getEntitat(), __alreadyCopied,"PreguntesFrequentsJPA"));
    }

    return __tmp;
  }




}
