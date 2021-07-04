package messobjekte;

public class MessInterpretation {
	double valueLow;
	double valueHigh;
	double value;
	
	String interpretation;
//	String shortcut = interpretation.substring(0,1);
	
	
	public String getInterpretation() {
		return interpretation;
	}
	
	public void setValue(String input) {
		String pars = input.replaceAll(",",".");
		value = Double.parseDouble(pars);
	}

//	private String setInterpretation() {
//		String interpretation;
//		
//		if(value>valueHigh) interpretation="High";
//		if(value<valueLow) interpretation="Low";
//		else interpretation = "Normal";
//		
//		return interpretation;
//	}

	public double getValueLow() {
		return valueLow;
	}

	public double getValueHigh() {
		return valueHigh;
	}

//	public String getShortcut() {
//		return shortcut;
//	}

	public void setValueLow(double valueLow) {
		this.valueLow = valueLow;
	}

	public void setValueHigh(double valueHigh) {
		this.valueHigh = valueHigh;
	}
	
	

	
	
	

}
