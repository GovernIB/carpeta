
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


@Entity(name = "PreguntesFrequentsJPA")
@Table(name = "car_preguntesfrequents" , indexes = { 
        @Index(name="car_preguntesfrequents_pk_i", columnList = "preguntesfrequentsid"),
        @Index(name="car_faq_enunciatid_fk_i", columnList = "enunciatid"),
        @Index(name="car_faq_entitatid_fk_i", columnList = "entitatid")})
@SequenceGenerator(name="PREGUNTESFREQUENTS_SEQ", sequenceName="car_preguntesfrequents_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class PreguntesFrequentsJPA implements PreguntesFrequents {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PREGUNTESFREQUENTS_SEQ")
    @Column(name="preguntesfrequentsid",nullable = false,length = 19)
    long preguntesFrequentsID;

    @Column(name="enunciatid",nullable = false,length = 19)
    long enunciatID;

    @Column(name="ordre",nullable = false,length = 10)
    int ordre;

    @Column(name="entitatid",nullable = false,length = 19)
    long entitatID;

    @Column(name="respostaca",nullable = false,length = 2147483647)
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    java.lang.String respostaCa;

    @Column(name="respostaes",nullable = false,length = 2147483647)
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    java.lang.String respostaEs;



  /** Constructor Buit */
  public PreguntesFrequentsJPA() {
  }

  /** Constructor amb tots els camps  */
  public PreguntesFrequentsJPA(long preguntesFrequentsID , long enunciatID , int ordre , long entitatID , java.lang.String respostaCa , java.lang.String respostaEs) {
    this.preguntesFrequentsID=preguntesFrequentsID;
    this.enunciatID=enunciatID;
    this.ordre=ordre;
    this.entitatID=entitatID;
    this.respostaCa=respostaCa;
    this.respostaEs=respostaEs;
}
  /** Constructor sense valors autoincrementals */
  public PreguntesFrequentsJPA(long enunciatID , int ordre , long entitatID , java.lang.String respostaCa , java.lang.String respostaEs) {
    this.enunciatID=enunciatID;
    this.ordre=ordre;
    this.entitatID=entitatID;
    this.respostaCa=respostaCa;
    this.respostaEs=respostaEs;
}
  public PreguntesFrequentsJPA(PreguntesFrequents __bean) {
    this.setPreguntesFrequentsID(__bean.getPreguntesFrequentsID());
    this.setEnunciatID(__bean.getEnunciatID());
    this.setOrdre(__bean.getOrdre());
    this.setEntitatID(__bean.getEntitatID());
    this.setRespostaCa(__bean.getRespostaCa());
    this.setRespostaEs(__bean.getRespostaEs());
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

	public java.lang.String getRespostaCa() {
		return(respostaCa);
	};
	public void setRespostaCa(java.lang.String _respostaCa_) {
		this.respostaCa = _respostaCa_;
	};

	public java.lang.String getRespostaEs() {
		return(respostaEs);
	};
	public void setRespostaEs(java.lang.String _respostaEs_) {
		this.respostaEs = _respostaEs_;
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
    @JoinColumn(name = "enunciatid", referencedColumnName ="traduccioID", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="car_faq_traduccio_enun_fk"))
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


// IMP Field:entitatid | Table: car_entitat | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entitatid", referencedColumnName ="entitatID", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="car_faq_entitat_ent_fk"))
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
    __tmp.setOrdre(__bean.getOrdre());
    __tmp.setEntitatID(__bean.getEntitatID());
    __tmp.setRespostaCa(__bean.getRespostaCa());
    __tmp.setRespostaEs(__bean.getRespostaEs());
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
