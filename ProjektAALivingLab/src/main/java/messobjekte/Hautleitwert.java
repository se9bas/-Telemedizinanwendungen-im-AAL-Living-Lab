package messobjekte;
/**
 * 
 * @author sebastian
 * 
 * Diese Klasse repräentiert die Tabelle Hautleitwert aus der Datenbank history.db
 * @TODO : es gibt kaum medizinische fundiertes Material über den einsatz von Hautleitwert in der Medizin. es gibt keinen loinc code dazu.
 */
public class Hautleitwert extends Messung implements InterpretationMesswerte {
	public static final String LOINC="";
	public static final String LOINC_DEF="";
	
	private String hautleitwert;
	
		
	public String getHautleitwert() {
		return hautleitwert;
	}

	public void setHautleitwert(String hautleitwert) {
		this.hautleitwert = hautleitwert;
	}

	@Override
	public String getQuelle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setInterpretation() {
		// TODO Auto-generated method stub

	}

}
