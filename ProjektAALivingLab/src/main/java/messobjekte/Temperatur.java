package messobjekte;
/**
 * 
 * @author sebastian
 * 
 * diese Klasse Repräsentiert die Tabelle "Temperatur" aus der Datenbank history.db (Stand: Juli 2021).
 *
 */

public class Temperatur extends Messung {
	public static final String LOINC="8310-5";
	public static final String LOINC_DEF="Body temperatur";
	
	private String temp;
	
	//get set
	public String getTemp() {
		return temp;
	}

	public void setTemp(String temp) {
		this.temp = temp;
	}
	
	//constr

	public Temperatur(int id, String datum, String temp) {
		super(id, datum);
		setTemp(temp);
	}
	
	

	public Temperatur() {
		super();
	}

	@Override
	public String getQuelle() {
		// TODO Auto-generated method stub
		return "https://flexikon.doccheck.com/de/K%C3%B6rpertemperatur#K.C3.B6rpertemperatur_im_Alter";
	}

	@Override
	public void setInterpretation() {
		double t = Double.parseDouble(temp.replaceAll(",","."));
		
		if(t<35.0)interpretation="Hypothermie";
		if(t>35 && t<36.8) interpretation="normal";
		if(t==36.8) interpretation="optimal";
		if(t>36.8 && t<38.0)interpretation="erhöht/normal";
		if(t>38.0)interpretation="Fieber";
		
	}
	
	

}
