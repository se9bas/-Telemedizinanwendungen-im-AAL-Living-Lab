package messobjekte;
/**
 * 
 * @author sebastian
 * 
 * diese Klasse Repräsentiert die Tabelle "Blutdruck" aus der Datenbank history.db (Stand: Juli 2021).
 *
 */
public class Blutdruck extends Messung {
	public static final String LOINC="55284-4";	
	public static final String LOINC_DEF="Blood pressure systolic and diastolic";
	
	private String sys;
	private String dia;
	protected String puls;
	
	//get set
	
	public String getSys() {
		return sys;
	}
	public String getDia() {
		return dia;
	}
	public String getPuls() {
		return puls;
	}

	public void setSys(String sys) {
		this.sys = sys;
	}
	public void setDia(String dia) {
		this.dia = dia;
	}
	public void setPuls(String puls) {
		this.puls = puls;
	}
	
	//constr
	
	public Blutdruck(int id, String datum, String sys, String dia, String puls) {
		super(id, datum);
		setSys(sys);
		setDia(dia);
		setPuls(puls);
	}
	
	public Blutdruck() {
		super();
	}
	
	
	@Override
	public String toString() {
		return "Blutdruck [sys=" + sys + ", dia=" + dia + ", puls=" + puls + "]";
	}
	
	@Override
	public String getQuelle() {
		return "https://www.blutdruckdaten.de/lexikon/blutdruck-normalwerte.html";
	}
	
	@Override
	public void setInterpretation() {
		int sys=Integer.parseInt(this.sys);
		int dia=Integer.parseInt(this.dia);
		
		if(sys<120 && dia <80) interpretation="optimal";
		if(120<=sys && sys<=129 && 80<=dia && dia<=84) interpretation="normal";
		if(130<=sys && sys<=139 && 85<=dia && dia<=89) interpretation="hochnormal";
		if(140<=sys && sys<=159 && 90<=dia && dia<=99) interpretation="Hypertonie Grad 1";
		if(160<=sys && sys<=179 && 100<=dia && dia<=109) interpretation="Hypertonie Grad 2";
		if(180<=sys && 110<=dia) interpretation="Hypertonie Grad 3";
		if(140<=sys && 90>dia) {
			interpretation="Isolierte systolische Hypertonie";
		}
//		else{
//			interpretation="keine Interpretation";
//		};	
	}

	
	
	
	
	
	
	
	
	
	

}
