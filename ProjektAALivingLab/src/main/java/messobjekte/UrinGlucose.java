package messobjekte;
/**
 * 
 * @author sebastian
 * Diese Klasse spezialisert den Messwert p3 (höchstwahrscheinlich ist p3 Glucose). Dieser sollte auch seperat interpretiert werden können.
 * TODO fertig spezifizieren. Über die Urinmessung ist kaum etwas bekannt, da die Toilette defekt ist und keine Dokumentation existiert.
 * Das Messgerät ermittelt unter Umstände Prozentwerte (vgl. screen Messwerte.pdf). Glucose im Urin sollte in Masse/Volumen (mg/dl, mmol/l)spezifiert werden. 
 *
 */
public class UrinGlucose extends Urin {
	//Glucose [Mass/volume] in Urine
	public static final String LOINC="2350-7";
	public static final String LOINC_DEF="Glucose [Mass/volume] in Urine";
	
	Urin urin;
			
	
	@Override
	public void setInterpretation() {
		//mg/dl
		int i=Integer.parseInt(urin.getGlucose());
		
		if(i<=40)interpretation="Urintest negativ";
		if(i<40)interpretation="Zucker im Urin";

	}

	@Override
	public String getQuelle() {
		return "https://www.apotheken-umschau.de/diagnose/laborwerte/harnzucker-glukosurie-zucker-im-urin-742339.html";

	}
	
	public UrinGlucose(Urin n) {
		urin=n;
	}

}
