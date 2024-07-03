package api.endpoints;

import static io.restassured.RestAssured.given;

import api.payload.Employee;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


//EmployeeEndPints.java
// Created for perform Create, Read, Update, Delete requests t the user API.

public class EmployeeEndPoints {

		public static Response createEmployee(Employee employeePayload)
		{
			Response response=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(employeePayload)
			.when()
				.post(Routes.employeepost_url);
				
			return response;
		}
		
		
		public static Response readEmployee(String string)
		{
			Response response=given()
							.pathParam("employeeName",string)
			.when()
				.get(Routes.employeeget_url);
				
			return response;
		}
		
		public static Response readEmployees()
		{
			Response response=given()							
			.when()
				.get(Routes.employeesget_url);
				
			return response;
		}
		
		public static Response updateEmployee(String employeeName, Employee payload)
		{
			Response response=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("employeename", employeeName)
				.body(payload)
			.when()
				.put(Routes.employeeupdate_url);
				
			return response;
		}
		
		
		public static Response deleteEmployee(String employeeName)
		{
			Response response=given()
							.pathParam("employeename",employeeName)
			.when()
				.delete(Routes.employeedelete_url);
				
			return response;
		}	
				
		
}