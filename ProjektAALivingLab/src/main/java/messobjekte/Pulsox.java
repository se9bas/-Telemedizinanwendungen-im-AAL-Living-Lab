package messobjekte;
/**
 * 
 * @author sebastian
 * 
 * diese Klasse Repräsentiert die Tabelle "Pulsox" aus der Datenbank history.db (Stand: Juli 2021).
 *
 */
public class Pulsox extends Messung{
	public static final String LOINC="59408-5";
	public static final String LOINC_DEF="Oxygen saturation in Arterial blood by Pulse oximetry";
	
	
	private String saettigung;
	
	//get set
	public String getSaettigung() {
		return saettigung;
	}

	public void setSaettigung(String saettigung) {
		this.saettigung = saettigung;
	}
	
	//constr
	public Pulsox(int id, String datum, String saettigung) {
		super(id, datum);
		setSaettigung(saettigung);
	}
	
	public Pulsox() {
		super();
	}

	@Override
	public String getQuelle() {
		// TODO Auto-generated method stub
		return "https://flexikon.doccheck.com/de/Pulsoximetrie";
	}

	@Override
	public void setInterpretation() {
		int p = Integer.parseInt(saettigung);
		
		if(p<85)interpretation="kritisch";
		if(p>84 && p<=90)interpretation="behandlungsbedürftig";
		if(p>90 && p<97) interpretation="niedrig nicht behandlungsbedürftig";
		if(p>=97)interpretation="normal";
		
	}
	

	
	
	

}
