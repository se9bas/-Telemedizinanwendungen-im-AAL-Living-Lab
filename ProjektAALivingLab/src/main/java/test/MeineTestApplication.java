package test;



import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.gclient.IQuery;

import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Observation.ObservationComponentComponent;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.Quantity;

import com.fasterxml.jackson.databind.JsonMappingException.Reference;

import org.fhir.ucum.Value;
import org.hl7.fhir.instance.model.api.IBaseBundle;
import org.hl7.fhir.instance.model.api.IIdType;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;

public class MeineTestApplication {
	
	

   /**
    * This is the Java main method, which gets executed
    */
   public static void main(String[] args) {
/*
      // Create a context
     FhirContext ctx = FhirContext.forR4();

      // Create a client
      //IGenericClient client = ctx.newRestfulGenericClient("https://hapi.fhir.org/baseR4");
    IGenericClient client = ctx.newRestfulGenericClient("http://193.174.205.90:8080/fhir");

      // Read a patient with the given ID
      //Patient patient = client.read().resource(Patient.class).withId("example").execute();
     Patient patient = client.read().resource(Patient.class).withId((long)3).execute();
      
     

      // Print the output
      String string = ctx.newJsonParser()
    		  .setPrettyPrint(true)
   		  .encodeResourceToString(patient);
      System.out.println(string);
  */    
      
      
	    FhirContext ctx = FhirContext.forR4();
		IGenericClient client = ctx.newRestfulGenericClient("http://193.174.205.90:8080/fhir");

		Bundle results = client
			.search()
			.forResource(Patient.class)
			.where(Patient.NAME.contains().values("rames", "sid"))
			.returnBundle(Bundle.class)
			.execute();

		System.out.println("First page: ");
		System.out.println(ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(results));
	

		// Load the next page
//		org.hl7.fhir.r4.model.Bundle nextPage = client
//			.loadPage()
//			.next(results)
//			.execute();
//
//		System.out.println("Next page: ");
//		System.out.println(ctx.newJsonParser().encodeResourceToString(nextPage));

	Observation ob = new Observation();
	ob.setStatus(Observation.ObservationStatus.FINAL);
//	ob
//		.addCode()
//			.addCoding()
//				.addSystem().setValue("http://loinc.org")
//				.addCode().setValue("15074-8")
//				.addDisplay().setValue("Glucose");
	
/*	
	
	//neues Coding
	Coding coding = new Coding("http://loinc.org", "15074-8", "Glucose [Moles/volume] in Blood");
	//kommt ins codeable concept rein??
	CodeableConcept cc = new CodeableConcept(coding);
	//?? ist der "code"
	ObservationComponentComponent code = new ObservationComponentComponent(cc);
	//??
	code.setCode(cc);
	
	ob.setCode(cc);
	
	//valueQuanity
	
	ob.addChild("valueQuantity");
		ob.setValue("6.3");
	
	
	
	
	// Subjekt der Observation
	Patient ram = new Patient();
	
	ram = client.read().resource(Patient.class).withId("3").execute();
	ob.setSubjectTarget(ram);
	
*/
	//Observation
	
	Observation observation	= new Observation();
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
	
	Patient patient = new Patient();
	patient = client.read().resource(Patient.class).withId("12").execute();
	
	ob.setSubjectTarget(patient);
	
//	MethodOutcome outcome = client
//			.create()
//			.resource(observation)
//			.execute();
//
//		// Log the ID that the server assigned
//		IIdType id = outcome.getId();
//		System.out.println("Created Observation, got ID: " + id);
	
	//copy paste test
/*	
	Bundle bundle = new Bundle();
	bundle.setType(Bundle.BundleType.TRANSACTION);

	// Add the patient as an entry. This entry is a POST with an 
	// If-None-Exist header (conditional create) meaning that it
	// will only be created if there isn't already a Patient with
	// the identifier 12345
	bundle.addEntry()
	   .setFullUrl(patient.getIdElement().getValue())
	   .setResource(patient)
	   .getRequest()
	      .setUrl("Patient")
	      .setIfNoneExist("identifier=http://acme.org/mrns|12345")
	      .setMethod(Bundle.HTTPVerb.POST);

	// Add the observation. This entry is a POST with no header
	// (normal create) meaning that it will be created even if
	// a similar resource already exists.
	bundle.addEntry()
	   .setResource(observation)
	   .getRequest()
	      .setUrl("Observation")
	      .setMethod(Bundle.HTTPVerb.POST);

	// Log the request
//	FhirContext ctx = FhirContext.forR4();
//	System.out.println(ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(bundle));
//
//	// Create a client and post the transaction to the server
//	IGenericClient client = ctx.newRestfulGenericClient("http://hapi.fhir.org/baseR4");
	Bundle resp = client.transaction().withBundle(bundle).execute();

	// Log the response
	System.out.println(ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(resp));
*/	
	
      
     
      		
      
   }

}
