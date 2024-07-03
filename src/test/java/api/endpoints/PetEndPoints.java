package api.endpoints;

import static io.restassured.RestAssured.given;

import api.payload.Pet;
import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


//PetEndPints.java
// Created for perform Create, Read, Update, Delete requests t the user API.

public class PetEndPoints {

		public static Response createPet(Pet petPayload)
		{
			Response response=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(petPayload)
			.when()
				.post(Routes.petpost_url);
				
			return response;
		}
		
		
		public static Response readPet(int i)
		{
			Response response=given()
							.pathParam("petname",i)
			.when()
				.get(Routes.petget_url);
				
			return response;
		}
		
		
		public static Response updatePet(String petName, Pet petPayload)
		{
			Response response=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("petname", petName)
				.body(petPayload)
			.when()
				.put(Routes.petupdate_url);
				
			return response;
		}
		
		
		public static Response deletePet(int i)
		{
			Response response=given()
							.pathParam("petname",i)
			.when()
				.delete(Routes.petdelete_url);
				
			return response;
		}	
				
		
}