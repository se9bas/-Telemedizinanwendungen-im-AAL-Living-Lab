package messobjekte;
/**
 * 
 * @author sebastian
 * 
 * diese Klasse Repräsentiert die Tabelle "Blutzucker" aus der Datenbank history.db (Stand: Juli 2021).
 *
 */


public class Blutzucker extends Messung{
	//LOINC 2339-0 — Glucose [Mass/volume] in Blood
	public static final String LOINC="2339-0";
	public static final String LOINC_DEF="Glucose [Mass/volume] in Blood";
	
	private String blutzucker;
		
	//get set
		
	public String getBlutzucker() {
		return blutzucker;
	}
	public void setBlutzucker(String blutzucker) {
		this.blutzucker = blutzucker;
	}
	
			
	//constr
	
	public Blutzucker() {
		super();
	}
	
	public Blutzucker(int id, String datum, String blutzucker) {
		super(id, datum);
		setBlutzucker(blutzucker);		
	}
	@Override
	public String getQuelle() {
		// TODO Auto-generated method stub
		return "https://www.praktischarzt.de/untersuchungen/blutzucker-messen/blutzuckerwerte/";
	}
	@Override
	public void setInterpretation() {
		double b = Double.parseDouble(blutzucker.replaceAll(",","."));
		//nach Tabelle in mg/dl HbA1c
		if(b<5.7)interpretation="normal";
		if(b<=5.7 && b<=6.4)interpretation="verdacht";
		if(b>6.4)interpretation="Diabetes";
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
