package messobjekte;

public interface InterpretationMesswerte {
	/**
	 * medizinisch verlässliche Quelle welche der Messinterpretation zugrunde liegt
	 */
	public String getQuelle();
	
	/**
	 * jeder konkrete Messwert muss eine Regel implementieren die die Interpretation seiner Messwerte realisiert 
	 * für jede Instanz eines Messwertes muss die Variable Messung.interpretation gemäß seiner Werte belegt sein.
	 */
	public void setInterpretation();
	
	
	

}
