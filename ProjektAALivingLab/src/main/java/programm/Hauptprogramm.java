package programm;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.hl7.fhir.instance.model.api.IIdType;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.Quantity;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
import messobjekte.*;
import sqlite.SQLite;

public class Hauptprogramm {

	public static void main(String[] args) {
		//System.out.println(System.getProperty("java.runtime.version"));
		
				//ein konkretes Objekt
				Blutdruck blutdruck=new Blutdruck();
//					Puls puls = new Puls(blutdruck);
				Blutzucker blutzucker=new Blutzucker();
				Gewicht gewicht = new Gewicht();
				Urin urin = new Urin();
//					UrinGlucose uglucose =new UrinGlucose(urin);
//					UrinHarnstoffe uharnstoffe = new UrinHarnstoffe(urin);
//					UrinProteine uproteine = new UrinProteine(urin);
				Pulsox pulsox = new Pulsox();
				Temperatur temperatur = new Temperatur();
				
				
				
				
				//Belegung mit akutellsten Datensatz aus DB
				belegungBlutdruck(blutdruck);
				belegungBlutzucker(blutzucker);
				belegungGewicht(gewicht);
				belegungUrin(urin);
				belegungPulsox(pulsox);
				belegungTemperatur(temperatur);
				
				//Test output
//				System.out.println("Blutdruck " + blutdruck.getId());
//				System.out.println("Blutzucker " + blutzucker.getId());
//				System.out.println("Gewicht "+gewicht.getId());
//				System.out.println("Urin "+urin.getId()+" D: "+urin.getDatum()+" P1 "+urin.getProteine()+" P2 "+urin.getHarnstoffe() +" P3 "+urin.getGlucose());
//				System.out.println("Pulsox "+pulsox.getId());
//				System.out.println("Temperatur "+temperatur.getId());
	
//------------------------------------------------------------------------------------------------------------------------------------------------				
//----------------------Fhir Observation Posts----------------------------------------------------------------------------------------------------
//------------------------------------------------------------------------------------------------------------------------------------------------				
				
				//default 
				FhirContext ctx = FhirContext.forR4();
			    IGenericClient client = ctx.newRestfulGenericClient("http://193.174.205.90:8080/fhir");
			    
			    //der einfachheitshalber ein schon existierender Patient
			    Patient patient;
				try {
					// Patient/14
					patient = client.read().resource(Patient.class).withId("14").execute();
				} catch (ResourceNotFoundException e) {
					System.out.println("Resource not found!");
					return;
				}

//				String string = ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(patient);
//				System.out.println(string);
				
//				System.out.println(patient.getIdElement().getIdPart()); // 14
//				System.out.println(patient.getIdElement().getValue()); // http://193.174.205.90:8080/fhir/Patient/14/_history/1
//				System.out.println("Name: "+ patient.getName().get(0).getGivenAsSingleString() +" "+ patient.getName().get(0).getFamily()); //Max Mustermann
				
				MethodOutcome bz = newEntryBlutdruck(patient, blutdruck, ctx, client);
				IIdType id = bz.getId();
				String baseUrl = id.getIdPart();
				//id der neuen Observation
				System.out.println("Observation mit ID: "+ baseUrl);
				
				
				
				
			    
				
					

	}
	
	//Methodensammlung
	
	public static void belegungBlutdruck(Blutdruck i) {
		
		ResultSet rs = SQLite.selectLastEntry(i.getClassName());
		try {
			if(!rs.next()) {
				System.out.println("No Data found" + " Tabelle: "+i.getClassName());
			}else {
				do {
					i.setId(rs.getInt("id"));
					i.setDatum(rs.getString("datum"));
					i.setSys(rs.getString("sys"));
					i.setDia(rs.getString("dia"));
					i.setPuls(rs.getString("puls"));
					
				}while(rs.next());
			}
		rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
	}
	public static void belegungBlutzucker(Blutzucker i) {
		
		ResultSet rs = SQLite.selectLastEntry(i.getClassName());
		try {
			if(!rs.next()) {
				System.out.println("No Data found" + " Tabelle: "+i.getClassName());
			}else {
				do {
					i.setId(rs.getInt("id"));
					i.setDatum(rs.getString("datum"));
					i.setBlutzucker(rs.getString("blutzucker"));
				}while(rs.next());
			}
		rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void belegungGewicht(Gewicht i) {
		
		ResultSet rs = SQLite.selectLastEntry(i.getClassName());
		try {
			if(!rs.next()) {
				System.out.println("No Data found" + " Tabelle: "+i.getClassName());
			}else {
				do {
					i.setId(rs.getInt("id"));
					i.setDatum(rs.getString("datum"));
					i.setMasse(rs.getString("masse"));
				}while(rs.next());
			}
		rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	
	public static void belegungUrin(Urin i) {
		
		ResultSet rs = SQLite.selectLastEntry(i.getClassName());
		try {
			if(!rs.next()) {
				System.out.println("No Data found" + " Tabelle: "+i.getClassName());
			}else {
				do {
					i.setId(rs.getInt("id"));
					i.setDatum(rs.getString("datum"));
					i.setP1(rs.getString("p1"));
					i.setP2(rs.getString("p2"));
					i.setP3(rs.getString("p3"));
				}while(rs.next());
			}
		rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void belegungPulsox(Pulsox i) {
		
		ResultSet rs = SQLite.selectLastEntry(i.getClassName());
		try {
			if(!rs.next()) {
				System.out.println("No Data found" + " Tabelle: "+i.getClassName());
			}else {
				do {
					i.setId(rs.getInt("id"));
					i.setDatum(rs.getString("datum"));
					i.setSaettigung(rs.getString("saettigung"));
					
				}while(rs.next());
			}
		rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void belegungTemperatur(Temperatur i) {
		
		ResultSet rs = SQLite.selectLastEntry(i.getClassName());
		try {
			if(!rs.next()) {
				System.out.println("No Data found" + " Tabelle: "+i.getClassName());
			}else {
				do {
					i.setId(rs.getInt("id"));
					i.setDatum(rs.getString("datum"));
					i.setTemp(rs.getString("temp"));
					
				}while(rs.next());
			}
		rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//Blaupausen
	
	
	public static MethodOutcome newEntryBlutzucker(Patient patient, Blutzucker b, FhirContext ctx, IGenericClient client) {
		double value = Double.parseDouble(b.getBlutzucker().replaceAll(",",".")); 
		String display = patient.getName().get(0).getGivenAsSingleString() +" "+ patient.getName().get(0).getFamily();// "Vorname"[leer]"Familienname"
		String reference = "Patient/"+patient.getIdElement().getIdPart();
		
		Observation observation = new Observation();
		
		observation.addIdentifier()
		.setSystem("All-In-One-Gerät")
		.setValue(String.valueOf(b.getId()));
		
		observation.addCategory()
		.addCoding()
			.setSystem("http://terminology.hl7.org/CodeSystem/observation-category")
			.setCode("vital-signs")
			.setDisplay("Vital Signs");
	
	observation.setStatus(Observation.ObservationStatus.FINAL);
	observation
		.getCode()
			.addCoding()
				.setSystem("http:loinc.org")
				.setCode(b.LOINC)
				.setDisplay(b.LOINC_DEF);
	
	observation.setValue(new Quantity()
			.setValue(value)
			.setUnit ("mmol/l")
			.setSystem("http://unitsofmeasure.org")
			.setCode("mmol/L"));
	
	
	observation.getSubject()
			.setReference(reference) //Patient/14
			.setDisplay(display);
	
	observation.getEffectiveDateTimeType()
		.setValueAsString(b.getDateTime());
	
	observation.addInterpretation()
			.addCoding()
				.setSystem(b.getQuelle())
				.setCode(b.getInterpretation())
				.setDisplay(b.getInterpretation());
	
	observation.addReferenceRange() // hardcodiert bezieht sich aber auf interpretaton vgl. setInterpretation() in Klasse
		.setLow(new Quantity()
					.setValue(5.6)
					.setUnit ("mmol/l")
					.setSystem("http://unitsofmeasure.org")
					.setCode("mmol/L"))
		.setHigh(new Quantity()
				.setValue(6.4)
				.setUnit ("mmol/l")
				.setSystem("http://unitsofmeasure.org")
				.setCode("mmol/L"));
			
		
		MethodOutcome outcome = client
				.create()
				.resource(observation)
				.execute();
		return outcome;
		
	}
	
	public static MethodOutcome newEntryBlutdruck(Patient patient, Blutdruck b, FhirContext ctx, IGenericClient client) {
		//https://www.hl7.org/fhir/observation-example-bloodpressure.json.html
		int sys = Integer.parseInt(b.getSys()); 
		int dia = Integer.parseInt(b.getDia()); 

		String display = patient.getName().get(0).getGivenAsSingleString() +" "+ patient.getName().get(0).getFamily();// "Vorname"[leer]"Familienname"
		String reference = "Patient/"+patient.getIdElement().getIdPart();
		
		Observation observation = new Observation();
		
		observation.addIdentifier()
			.setSystem("All-In-One-Gerät")
			.setValue(String.valueOf(b.getId()));
	
	observation.setStatus(Observation.ObservationStatus.FINAL);
	
	observation.addCategory()
		.addCoding()
			.setSystem("http://terminology.hl7.org/CodeSystem/observation-category")
			.setCode("vital-signs")
			.setDisplay("Vital Signs");
		
	//
	observation
		.getCode()
			.addCoding()
				.setSystem("http:loinc.org")
				.setCode(b.LOINC)
				.setDisplay(b.LOINC_DEF);
//systolische	
	observation.addComponent()
		.getCode()
			.addCoding()
				.setSystem("http://loinc.org")
				.setCode("8480-6")
				.setDisplay("Systolic blood pressure");
			
	observation.setValue(new Quantity()
			.setValue(sys)
			.setUnit ("mmHg")
			.setSystem("http://unitsofmeasure.org")
			.setCode("mm[Hg]"));
	
//diastolische
	observation
		.getCode()
			.addCoding()
			.setSystem("http://loinc.org")
			.setCode("8462-4")
			.setDisplay("Diastolic blood pressure");
	
	observation.setValue(new Quantity()
			.setValue(dia)
			.setUnit ("mmHg")
			.setSystem("http://unitsofmeasure.org")
			.setCode("mm[Hg]"));

	observation.getSubject()
			.setReference(reference) //Patient/14
			.setDisplay(display);
	
	observation.getEffectiveDateTimeType()
		.setValueAsString(b.getDateTime());
	
	observation.addInterpretation()
			.addCoding()
				.setSystem(b.getQuelle())
				.setCode(b.getInterpretation())
				.setDisplay(b.getInterpretation());
	
	//end of declaration:
		//send to server
	
	MethodOutcome outcome = client
				.create()
				.resource(observation)
				.execute();
		return outcome;
		
	}
	
	public static MethodOutcome newEntryGewicht(Patient patient, Gewicht b, FhirContext ctx, IGenericClient client) {
		double value = Double.parseDouble(b.getMasse().replaceAll(",",".")); 
		
		String display = patient.getName().get(0).getGivenAsSingleString() +" "+ patient.getName().get(0).getFamily();// "Vorname"[leer]"Familienname"
		String reference = "Patient/"+patient.getIdElement().getIdPart();
		
		Observation observation = new Observation();
		
		observation.addIdentifier()
			.setSystem("All-In-One-Gerät")
			.setValue(String.valueOf(b.getId()));
	
	observation.setStatus(Observation.ObservationStatus.FINAL);
	
	observation.addCategory()
		.addCoding()
			.setSystem("http://terminology.hl7.org/CodeSystem/observation-category")
			.setCode("vital-signs")
			.setDisplay("Vital Signs");
		
	//
	observation
		.getCode()
			.addCoding()
				.setSystem("http:loinc.org")
				.setCode(b.LOINC)
				.setDisplay(b.LOINC_DEF);
	observation	
		.getCode()	
			.addCoding()
				.setSystem("http://loinc.org")
				.setCode("3141-9")
				.setDisplay("Body weight Measured");	

			
	observation.setValue(new Quantity()
			.setValue(value)
			.setUnit ("kg")
			.setSystem("SI unit")
			.setCode("m"));
	
	observation.getSubject()
			.setReference(reference) //Patient/14
			.setDisplay(display);
	
	observation.getEffectiveDateTimeType()
		.setValueAsString(b.getDateTime());
	/*
	observation.addInterpretation()
			.addCoding()
				.setSystem(b.getQuelle())
				.setCode(b.getInterpretation())
				.setDisplay(b.getInterpretation());
	*/
	//end of declaration:
		//send to server
	
	MethodOutcome outcome = client
				.create()
				.resource(observation)
				.execute();
		return outcome;
		
	}
	
	public static MethodOutcome newEntryHautleitwert(Patient patient, Hautleitwert b, FhirContext ctx, IGenericClient client) {
		int value = Integer.parseInt(b.getHautleitwert());
		
		String display = patient.getName().get(0).getGivenAsSingleString() +" "+ patient.getName().get(0).getFamily();// "Vorname"[leer]"Familienname"
		String reference = "Patient/"+patient.getIdElement().getIdPart();
		
		Observation observation = new Observation();
		
		observation.addIdentifier()
			.setSystem("All-In-One-Gerät")
			.setValue(String.valueOf(b.getId()));
	
	observation.setStatus(Observation.ObservationStatus.FINAL);
	
	observation.addCategory()
		.addCoding()
			.setSystem("http://terminology.hl7.org/CodeSystem/observation-category")
			.setCode("vital-signs")
			.setDisplay("Vital Signs");
		
	/*
	observation
		.getCode()
			.addCoding()
				.setSystem("http:loinc.org")
				.setCode(b.LOINC)
				.setDisplay(b.LOINC_DEF);
			*/
	observation	
		.getCode()	
			.addCoding()
				.setSystem("none")
				.setCode("none")
				.setDisplay("Skin resistance");	

			
	observation.setValue(new Quantity()
			.setValue(value)
			.setUnit ("uS")
			.setSystem("SI unit")
			.setCode("S"));
	
	observation.getSubject()
			.setReference(reference) //Patient/14
			.setDisplay(display);
	
	observation.getEffectiveDateTimeType()
		.setValueAsString(b.getDateTime());
	/*
	observation.addInterpretation()
			.addCoding()
				.setSystem(b.getQuelle())
				.setCode(b.getInterpretation())
				.setDisplay(b.getInterpretation());
	*/
	//end of declaration:
		//send to server
	
	MethodOutcome outcome = client
				.create()
				.resource(observation)
				.execute();
		return outcome;
		
	}
	
	public static MethodOutcome newEntryPuls(Patient patient, Blutdruck blutdruck, FhirContext ctx, IGenericClient client) {
		Puls puls = new Puls(blutdruck);
		
		int value = Integer.parseInt(blutdruck.getPuls());
		
		
		String display = patient.getName().get(0).getGivenAsSingleString() +" "+ patient.getName().get(0).getFamily();// "Vorname"[leer]"Familienname"
		String reference = "Patient/"+patient.getIdElement().getIdPart();
		
		Observation observation = new Observation();
		
		observation.addIdentifier()
			.setSystem("All-In-One-Gerät")
			.setValue(String.valueOf(blutdruck.getId()));
	
	observation.setStatus(Observation.ObservationStatus.FINAL);
	
	observation.addCategory()
		.addCoding()
			.setSystem("http://terminology.hl7.org/CodeSystem/observation-category")
			.setCode("vital-signs")
			.setDisplay("Vital Signs");
		
	
	observation
		.getCode()
			.addCoding()
				.setSystem("http:loinc.org")
				.setCode(puls.LOINC)
				.setDisplay(puls.LOINC_DEF);
			

			
	observation.setValue(new Quantity()
			.setValue(value)
			.setUnit ("/min")
			.setSystem("http://unitsofmeasure.org")
			.setCode("beats / minute"));
	
	observation.getSubject()
			.setReference(reference) //Patient/14
			.setDisplay(display);
	
	observation.getEffectiveDateTimeType()
		.setValueAsString(blutdruck.getDateTime());
	
	observation.addInterpretation()
			.addCoding()
				.setSystem(puls.getQuelle())
				.setCode(puls.getInterpretation())
				.setDisplay(puls.getInterpretation());
	
	//end of declaration:
		//send to server
	
	MethodOutcome outcome = client
				.create()
				.resource(observation)
				.execute();
		return outcome;
		
	}
	
	
	public static MethodOutcome newEntryPulsox(Patient patient, Pulsox b, FhirContext ctx, IGenericClient client) {
		int value = Integer.parseInt(b.getSaettigung());	
		
		String display = patient.getName().get(0).getGivenAsSingleString() +" "+ patient.getName().get(0).getFamily();// "Vorname"[leer]"Familienname"
		String reference = "Patient/"+patient.getIdElement().getIdPart();
		
		Observation observation = new Observation();
		
	observation.addIdentifier()
		.setSystem("All-In-One-Gerät")
		.setValue(String.valueOf(b.getId()));
	
	observation.setStatus(Observation.ObservationStatus.FINAL);
	
	observation
	.getCode()
		.addCoding()
			.setSystem("http:loinc.org")
			.setCode("2708-6")
			.setDisplay("Oxygen saturation in Arterial blood");
	
	observation
		.getCode()
			.addCoding()
				.setSystem("http:loinc.org")
				.setCode(b.LOINC)
				.setDisplay(b.LOINC_DEF);
	
	observation.setValue(new Quantity()
			.setValue(value)
			.setUnit ("%")
			.setSystem("http://unitsofmeasure.org")
			.setCode("%"));
	
	
	observation.getSubject()
			.setReference(reference) //Patient/14
			.setDisplay(display);
	
	observation.getEffectiveDateTimeType()
		.setValueAsString(b.getDateTime());
	
	observation.addInterpretation()
			.addCoding()
				.setSystem(b.getQuelle())
				.setCode(b.getInterpretation())
				.setDisplay(b.getInterpretation());
	
	observation.addReferenceRange() // hardcodiert bezieht sich aber auf interpretaton vgl. setInterpretation() in Klasse
		.setLow(new Quantity()
					.setValue(90)
					.setUnit ("%")
					.setSystem("http://unitsofmeasure.org")
					.setCode("%"))
		.setHigh(new Quantity()
				.setValue(97)
				.setUnit ("%")
				.setSystem("http://unitsofmeasure.org")
				.setCode("%"));
			
		
		MethodOutcome outcome = client
				.create()
				.resource(observation)
				.execute();
		return outcome;
		
	}
	
	public static MethodOutcome newEntryTemperatur(Patient patient, Temperatur b, FhirContext ctx, IGenericClient client) {
		double value = Double.parseDouble(b.getTemp().replaceAll(",","."));	
		
		String display = patient.getName().get(0).getGivenAsSingleString() +" "+ patient.getName().get(0).getFamily();// "Vorname"[leer]"Familienname"
		String reference = "Patient/"+patient.getIdElement().getIdPart();
		
		Observation observation = new Observation();
		
	observation.addIdentifier()
		.setSystem("All-In-One-Gerät")
		.setValue(String.valueOf(b.getId()));
	
	observation.setStatus(Observation.ObservationStatus.FINAL);
		
	observation
		.getCode()
			.addCoding()
				.setSystem("http:loinc.org")
				.setCode(b.LOINC)
				.setDisplay(b.LOINC_DEF);
	
	observation.setValue(new Quantity()
			.setValue(value)
			.setUnit ("C")
			.setSystem("http://unitsofmeasure.org")
			.setCode("Cel"));
	
	
	observation.getSubject()
			.setReference(reference) //Patient/14
			.setDisplay(display);
	
	observation.getEffectiveDateTimeType()
		.setValueAsString(b.getDateTime());
	
	observation.addInterpretation()
			.addCoding()
				.setSystem(b.getQuelle())
				.setCode(b.getInterpretation())
				.setDisplay(b.getInterpretation());
	
	observation.addReferenceRange() // hardcodiert bezieht sich aber auf interpretaton vgl. setInterpretation() in Klasse
		.setLow(new Quantity()
					.setValue(35.0)
					.setUnit ("C")
					.setSystem("http://unitsofmeasure.org")
					.setCode("Cel"))
		.setHigh(new Quantity()
				.setValue(38.0)
				.setUnit ("C")
				.setSystem("http://unitsofmeasure.org")
				.setCode("Cel"));
			
		
		MethodOutcome outcome = client
				.create()
				.resource(observation)
				.execute();
		return outcome;
		
	}
	
	public static MethodOutcome newEntryUrin(Patient patient, Urin urin, FhirContext ctx, IGenericClient client) {
		UrinGlucose glucose = new UrinGlucose(urin);
		UrinProteine proteine = new UrinProteine(urin);
		UrinHarnstoffe harnstoffe = new UrinHarnstoffe(urin);
		
		int wg = Integer.parseInt(urin.getGlucose());
		int wp = Integer.parseInt(urin.getProteine());
		int wh = Integer.parseInt(urin.getHarnstoffe());
		
		//Werte
		
		

		String display = patient.getName().get(0).getGivenAsSingleString() +" "+ patient.getName().get(0).getFamily();// "Vorname"[leer]"Familienname"
		String reference = "Patient/"+patient.getIdElement().getIdPart();
		
		Observation observation = new Observation();
		
		observation.addIdentifier()
			.setSystem("All-In-One-Gerät")
			.setValue(String.valueOf(urin.getId()));
	
	observation.setStatus(Observation.ObservationStatus.FINAL);
	
	observation.addCategory()
		.addCoding()
			.setSystem("http://terminology.hl7.org/CodeSystem/observation-category")
			.setCode("vital-signs")
			.setDisplay("Vital Signs");
		
	//
	observation
		.getCode()
			.addCoding()
				.setSystem("http:loinc.org")
				.setCode(urin.LOINC)
				.setDisplay(urin.LOINC_DEF);
//Glucose--------------------------------------------
	observation.addComponent() 
		.getCode()
			.addCoding()
				.setSystem("http://loinc.org")
				.setCode(glucose.LOINC)
				.setDisplay(glucose.LOINC_DEF);
			
	observation.setValue(new Quantity()
			.setValue(wg)
			.setUnit ("mg/dl")
			.setSystem("http://unitsofmeasure.org")
			.setCode("mg/dl"));
	
	observation.addInterpretation()
	.addCoding()
		.setSystem(glucose.getQuelle())
		.setCode(glucose.getInterpretation())
		.setDisplay(glucose.getInterpretation());
	
//Proteine----------------------------------------------------
	observation.addComponent() 
	.getCode()
		.addCoding()
			.setSystem("http://loinc.org")
			.setCode(proteine.LOINC)
			.setDisplay(proteine.LOINC_DEF);
		
	observation.setValue(new Quantity()
			.setValue(wp)
			.setUnit ("mg/dl")
			.setSystem("http://unitsofmeasure.org")
			.setCode("mg/dl"));
	
	observation.addInterpretation()
	.addCoding()
		.setSystem(glucose.getQuelle())
		.setCode(glucose.getInterpretation())
		.setDisplay(glucose.getInterpretation());

//Harnstoffe---------------------------------------------------------
	observation.addComponent() 
	.getCode()
		.addCoding()
			.setSystem("http://loinc.org")
			.setCode(harnstoffe.LOINC)
			.setDisplay(harnstoffe.LOINC_DEF);
		
	observation.setValue(new Quantity()
		.setValue(wh)
		.setUnit ("mg/dl")
		.setSystem("http://unitsofmeasure.org")
		.setCode("mg/dl"));
	
	observation.addInterpretation()
		.addCoding()
			.setSystem(harnstoffe.getQuelle())
			.setCode(harnstoffe.getInterpretation())
			.setDisplay(harnstoffe.getInterpretation());
//------------------------------------------------------------------
	observation.getSubject()
			.setReference(reference) //Patient/14
			.setDisplay(display);
	
	observation.getEffectiveDateTimeType()
		.setValueAsString(urin.getDateTime());

	//end of declaration:
		//send to server
	
	MethodOutcome outcome = client
				.create()
				.resource(observation)
				.execute();
		return outcome;
		
	}
	
	
	
	

	
}

