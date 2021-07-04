package messobjekte;
/**
 * 
 * @author sebastian
 * Diese Klasse spezialisert den Messwert p1 (höchstwahrscheinlich ist p1 Proteine)
 * TODO fertig spezifizieren. Über die Urinmessung ist kaum etwas bekannt, da die Toilette defekt ist und keine Dokumentation existiert.
 * Das Messgerät ermittelt unter Umstände Prozentwerte (vgl. screen Messwerte.pdf). Proteine im Urin sollte in Masse/Volumen spezifiert werden. 
 * es ist eine Unterscheidung von Morgenurin notwendig. es wird Plasmaproteine (Albumin) und Glykoproteine (Immunglobuli G)unterschieden.
 * der Sammelurin von 24std liefert verlässliche Ergebnisse.
 */
public class UrinProteine extends Urin {
	//2888-6Protein [Mass/volume] in Urine
	public static final String LOINC="2888-6";
	public static final String LOINC_DEF="Protein [Mass/volume] in Urine";
	
	Urin urin;

	@Override
	public void setInterpretation() {
		int i = Integer.parseInt(urin.getProteine());
		//mg/dl Urinstreifentest
		if(i<8)interpretation = "normal";
		if(i>=8) interpretation="erhöht";

	}

	public String getQuelle() {
		return "https://www.onmeda.de/symptome/eiweiss-im-urin.html";
		/*
		 * hier eine Tabelle: 
		 * https://www.netdoktor.de/laborwerte/eiweiss-im-urin/
		 * 					
		 * https://med4you.at/laborbefunde/lbef3/lbef_eiweiss_im_harn_dt.htm
		 */
	}
	
	public UrinProteine(Urin n) {
		urin=n;
	}

}
