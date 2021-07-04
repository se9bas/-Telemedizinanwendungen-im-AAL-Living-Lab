package test;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.gclient.IQuery;

import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Observation.ObservationComponentComponent;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.Period;
import org.hl7.fhir.r4.model.Quantity;
import org.hl7.fhir.r4.model.Resource;

import com.fasterxml.jackson.databind.JsonMappingException.Reference;

import org.fhir.ucum.Value;
import org.hl7.fhir.instance.model.api.IBaseBundle;
import org.hl7.fhir.instance.model.api.IIdType;
import org.hl7.fhir.r4.model.BaseResource;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.DateType;
import org.hl7.fhir.r4.model.Enumerations;
import org.hl7.fhir.r4.model.Enumerations.AdministrativeGender;

public class MeineTests {
	
	private static AdministrativeGender geschlecht(String gender) {
		AdministrativeGender out = AdministrativeGender.NULL;		
		
		switch(gender) {
		case("m"): out = AdministrativeGender.MALE; break;
		case("f"): out = AdministrativeGender.FEMALE; break;
		case("d"): out = AdministrativeGender.OTHER; break;
		}
		return out;
	}
	
	
	public static String createPatient(String name, String vorname, String gebdat, String gender) {
		// Create a patient
		Patient newPatient = new Patient();

		// Populate the patient with fake information
		newPatient
			.addName()
				.setFamily(name)
				.addGiven(vorname);
				
		
		newPatient.setGender(geschlecht(gender));
		newPatient.setBirthDateElement(new DateType(gebdat));

		// Create a client
		FhirContext ctx = FhirContext.forR4();
		IGenericClient client = ctx.newRestfulGenericClient("http://193.174.205.90:8080/fhir");
		//IGenericClient client = ctx.newRestfulGenericClient("https://hapi.fhir.org/baseR4");

		// Create the resource on the server
		MethodOutcome outcome = client
			.create()
			.resource(newPatient)
			.execute();

		// Log the ID that the server assigned
		IIdType id = outcome.getId();
		String baseUrl = id.getIdPart();
		
		return baseUrl;
		
	}
	
	//main------------------------------------------------------------------------------------------------------------

	public static void main(String[] args) {
	
	      // Create a context
	     FhirContext ctx = FhirContext.forR4();
	     IGenericClient client = ctx.newRestfulGenericClient("http://193.174.205.90:8080/fhir");
/*	     Patient patient = client.read().resource(Patient.class).withId((long)3).execute();
	          
	     // Print the output
	      String string = ctx.newJsonParser()
	    		  .setPrettyPrint(true)
	   		  .encodeResourceToString(patient);
//	      System.out.println(string);
*/
	     
	//createPatient("Müller","Manfred", "1970-01-01", "m");	
		
	//Observation
		
	Observation observation	= new Observation();
	
	observation.addIdentifier()
		.setSystem("All-In-One-Gerät")
		.setValue("44");
	
	observation.setStatus(Observation.ObservationStatus.FINAL);
	observation
		.getCode()
			.addCoding()
				.setSystem("http:loinc.org")
				.setCode("1507-8")
				.setDisplay("Glucose [Moles/volume] in Blood");
	
	observation.setValue(new Quantity()
			.setValue(6.3)
			.setUnit ("mmol/l")
			.setSystem("http://unitsofmeasure.org")
			.setCode("mmol/L"));
	
	
	observation.getSubject()
			.setReference("Patient/14")
			.setDisplay("Max Mustermann");
	
	observation.getEffectiveDateTimeType()
		.setValueAsString("2002-02-02T00:00:00+00:00");
	
	observation.addInterpretation()
			.addCoding()
				.setSystem("https://www.praktischarzt.de/untersuchungen/blutzucker-messen/blutzuckerwerte")
				.setCode("H")
				.setDisplay("High");
	
	observation.addReferenceRange()
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

		// Log the ID that the server assigned
		IIdType id = outcome.getId();
		String baseUrl = id.getIdPart();
		
		System.out.println(baseUrl);
				
	
	
		
	}


	

}
