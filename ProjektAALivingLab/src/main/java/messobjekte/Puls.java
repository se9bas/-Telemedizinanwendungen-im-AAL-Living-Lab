package messobjekte;
/**
 * 
 * @author sebastian
 * Kindklasse Puls zur Spezialisierung der Interpretation des Pulses. 
 */
public class Puls extends Blutdruck {
	public final static String LOINC="8867-4" ;
	public static final String LOINC_DEF="Heart Rate";
	
	private Blutdruck blutdruck;
	
	public void setInterpretation() {
		int p=Integer.parseInt(blutdruck.getPuls());
		if(p<60)this.interpretation="Bradykardie";
		if(p>=60 && p<=80)this.interpretation="ruhig/normal";
		if (p>80 && p<100)this.interpretation="Schlecht";
		if(p>100)this.interpretation="Tachykardie";
	}

	
	public String getQuelle() {
		return "https://flexikon.doccheck.com/de/Herzfrequenz";
	}
	
	public Puls(Blutdruck blutdruck) {
		this.blutdruck=blutdruck;
	}
	
	
	
	
}
