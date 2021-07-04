package messobjekte;

public interface InterpretationMesswerte {
	/**
	 * medizinisch verl�ssliche Quelle welche der Messinterpretation zugrunde liegt
	 */
	public String getQuelle();
	
	/**
	 * jeder konkrete Messwert muss eine Regel implementieren die die Interpretation seiner Messwerte realisiert 
	 * f�r jede Instanz eines Messwertes muss die Variable Messung.interpretation gem�� seiner Werte belegt sein.
	 */
	public void setInterpretation();
	
	
	

}
