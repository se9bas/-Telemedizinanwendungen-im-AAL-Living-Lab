package messobjekte;
/**
 * 
 * @author sebastian
 * 
 * diese Klasse Repräsentiert die Tabelle "Gewicht" aus der Datenbank history.db (Stand: Juli 2021).
 *
 */
public class Gewicht extends Messung {
	public static final String LOINC="29463-7";
	public static final String LOINC_DEF="Body Weight";
	
	private String masse;
	
	//get set

	public String getMasse() {
		return masse;
	}

	public void setMasse(String masse) {
		this.masse = masse;
	}
	
	//constr
	public Gewicht(int id, String datum, String masse) {
		super(id, datum);
		
		setMasse(masse);
	}
	
	public Gewicht() {
		super();
	}

	@Override
	public String getQuelle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	//kann nicht pauschal interpretiert werden. es gibt möglichkeiten in fhir bei plänen wie z.B. Diät Gewicht zu interpretieren...
	public void setInterpretation() {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
