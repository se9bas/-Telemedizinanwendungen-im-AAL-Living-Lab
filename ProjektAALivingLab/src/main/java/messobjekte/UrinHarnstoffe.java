package messobjekte;
/**
 * 
 * @author sebastian
 * Diese Klasse spezialisert den Messwert p2 (höchstwahrscheinlich ist p2 Harnstoffe) Dieser sollte auch seperat interpretiert werden können.
 * TODO fertig spezifizieren. Über die Urinmessung ist kaum etwas bekannt, da die Toilette defekt ist und keine Dokumentation existiert.
 * Das Messgerät ermittelt unter Umstände Prozentwerte (vgl. screen Messwerte.pdf). Harnstoffe im Urin sollte in Masse/Volumen spezifiert werden. 
 *	Für die Messung ist eine Beobachtung von 24-48 Std. relevant
 */
public class UrinHarnstoffe extends Urin {

	// Urea [Mass/volume] in Urine
	public static final String LOINC = "3092-4";
	public static final String LOINC_DEF="Urea [Mass/volume] in Urine";
	
	private Urin urin;

	@Override
	public void setInterpretation() {
		int i = Integer.parseInt(urin.getHarnstoffe());
		//Urinsammlung in g/24 h
		if(i<20) interpretation="gering";
		if(i>=20 && i<=35) interpretation="normal";
		if(i>35) interpretation = "erhöht";
	}

	@Override
	public String getQuelle() {
		return "https://www.netdoktor.at/laborwerte/harnstoff-8474";

	}

	public UrinHarnstoffe(Urin n) {
		urin=n;
	}

}
