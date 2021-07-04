package messobjekte;
/**
 * 
 * @author sebastian
 * 
 * diese Klasse Repräsentiert die Tabelle "Urin" aus der Datenbank history.db (Stand: Juli 2021).
 *
 */
public class Urin extends Messung{
	//28009-9Volume of Urine
	public static final String LOINC="28009-9";
	public static final String LOINC_DEF="Volume of Urine";
	
	
	//Attribute nach SQL

	private String p1;
	private String p2;
	private String p3;
	
	//logische Zuordnung nach: screen Messwerte.pdf
	private String proteine = this.getP1();
	private String harnstoffe = this.getP2();
	private String glucose = this.getP3();
	
	//get set
	
	public String getP1() {
		return p1;
	}

	public String getP2() {
		return p2;
	}

	public String getP3() {
		return p3;
	}

	public String getProteine() {
		return p1;
	}

	public String getHarnstoffe() {
		return p2;
	}

	public String getGlucose() {
		return p3;
	}

	public void setP1(String p1) {
		this.p1 = p1;
	}

	public void setP2(String p2) {
		this.p2 = p2;
	}

	public void setP3(String p3) {
		this.p3 = p3;
	}

	public void setProteine(String proteine) {
		this.proteine = proteine;
	}

	public void setHarnstoffe(String harnstoffe) {
		this.harnstoffe = harnstoffe;
	}

	public void setGlucose(String glucose) {
		this.glucose = glucose;
	}

	//constr
	public Urin() {
		super();
	}
	
	public Urin(int id, String datum, String p1, String p2, String p3) {
		super(id, datum);
		setP1(p1);
		setP2(p2);
		setP3(p3);
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
