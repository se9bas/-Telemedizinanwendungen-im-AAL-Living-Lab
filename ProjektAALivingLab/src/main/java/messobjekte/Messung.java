package messobjekte;

public abstract class Messung implements InterpretationMesswerte {
	public static String LOINC;
	
	
	protected String quelle;
	protected String interpretation;
	
	private String datum;
	private int id;
	
	
	/**
	 * Namen der Instanzklasse z.B. "Blutdruck"
	 * @return name of the class which invokes this method. 
	 */
	public String getClassName() {
		//getName
		String result= this.getClass().getName();
		//cuts off package name
		result=result.substring(result.indexOf(".")+1);
		return result;
	}
	
	//get set
	
	public String getInterpretation() {
		setInterpretation();
		return interpretation;
	}
	
	public String getDatum() {
		return datum;
	}
	public int getId() {
		return id;
	}
	
	
	public void setDatum(String Datum) {
		this.datum=Datum;
	}
	
	//umwandlung vom datumstring aus history.db zu dateTime fhir Konform
	public String getDateTime() {
		String datum=this.datum;
		datum=datum.replaceAll(" ","");
		String[]tmp=datum.split("-");
		tmp[0]=tmp[0].replace(".","-");
		String[]a=tmp[0].split("-");
		String date=String.join("-",a[2],a[1],a[0]);
			
		//Zeitzone: ausrechnen der Diff zu Standartzeit in Std
		java.util.TimeZone tz = java.util.TimeZone.getDefault();
		String TimeZoneID = tz.getID();
		tz = java.util.TimeZone.getTimeZone(TimeZoneID);
		int offset = tz.getOffset(System.currentTimeMillis());
		//millisec to hour :: entweder 1 oder 2 (sommerzeit, winterzeit/normalzeit)
		offset = offset/3600000;
		String zz = "+0"+offset+":00";
				
		return date+"T"+tmp[1]+zz;
	}
	
	
	public void setId(int id) {
		this.id = id;
	}
	
	//constr
	public Messung(int id, String datum) {
		setId(id);
		setDatum(datum);
	}
	
	Messung(){
		
	}
	
	
	
}
