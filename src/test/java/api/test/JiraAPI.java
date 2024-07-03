package api.test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import java.io.File;

public class JiraAPI {

	private static final String BASE_URL = "https://maktheyashwanthrao.atlassian.net/rest/api/3";
	private static final String USERNAME = "maktheyashwanthrao@gmail.com";
	private static final String API_TOKEN = "ATATT3xFfGF0ahLshV5jU8zp5jpitQZ43Z3zlv5Hzo7AJa6lPROyKd_Rkazv-B1KZqfUbeqK0DCr8k_thNf6yZ6tdQKRyBfzFmWjNRUc4cufjEnDITeW9hyMerNehVPri_wmwtby764Bzh-t-IrT8RVH57M1CbqKzjQkeZMEo40UsUS3ph61WSU=A1CDD2B5";
	private static final String PROJECT_KEY = "KAN";

	public static void main(String[] args) {
		RestAssured.baseURI = BASE_URL;

		String issueId = createDefect();
		updateDefect(issueId);
		searchDefect(issueId);
		addAttachment(issueId);
		 deleteDefect(issueId);
	}

	// Create a defect in Jira
	public static String createDefect() {
		String requestBody = "{\r\n"
				+ "\"fields\": {\r\n"
				+ "	\"assignee\": {\r\n"
				+ "	 \"id\": \"62cd862b6eba71983721cdad\"\r\n"
				+ "	},\r\n"
				+ "\"issuetype\": {\r\n"
				+ "  \"name\":\"Bug\"\r\n"
				+ "},\r\n"
				+ "\"project\":{\r\n"
				+ "  \"key\": \"KAN\"\r\n"
				+ "},\r\n"
				+ "\"reporter\": {\r\n"
				+ "  \"id\": \"62cd862b6eba71983721cdad\"\r\n"
				+ "},\r\n"
				+ "\"summary\": \"Main order flow broken\"\r\n"
				+ "},\r\n"
				+ "\"update\": {}\r\n"
				+ "}";		

		Response response =
				given()
				.auth().preemptive().basic(USERNAME, API_TOKEN).contentType(ContentType.JSON)
				.body(requestBody)
				.post("/issue");

		response.then().statusCode(201);
		String issueId = response.jsonPath().getString("id");
		System.out.println("Issue created with ID: " + issueId);
		return issueId;
	}

	// Update the defect using defect ID
	public static void updateDefect(String issueId) {
		String requestBody = "{\r\n"
				+ "  \"fields\": {\r\n"
				+ "  },\r\n"
				+ "  \"update\": {\r\n"
				+ "    \"labels\": [\r\n"
				+ "      {\r\n"
				+ "        \"add\": \"label1\"\r\n"
				+ "      }\r\n"
				+ "    ],\r\n"
				+ "    \"summary\": [\r\n"
				+ "      {\r\n"
				+ "        \"set\": \"Update first defect using API\"\r\n"
				+ "      }\r\n"
				+ "    ]\r\n"
				+ "  }\r\n"
				+ "}\r\n"
			;

		Response response = given().auth().preemptive().basic(USERNAME, API_TOKEN).contentType(ContentType.JSON)
				.body(requestBody).put("/issue/" + issueId);

		response.then().statusCode(204);
		System.out.println("Issue updated with ID: " + issueId);
	}

	// Search the defect created in step 1
	public static void searchDefect(String issueId) {
		Response response = given().auth().preemptive().basic(USERNAME, API_TOKEN).contentType(ContentType.JSON)
				.get("/issue/" + issueId);

		response.then().statusCode(200);
		System.out.println("Issue details: " + response.asString());
	}

	// Add an attachment to the issue
	public static void addAttachment(String issueId) {
		//File file = new File("C:\\Users\\yashwanth_makthe\\Desktop\\YashwanthProjects\\APIHomeTask\\src\\test\\resources\\CreateIssue.json");
		File file = new File("C:\\Users\\yashwanth_makthe\\Desktop\\YashwanthProjects\\APIHomeTask\\src\\test\\resources\\CreateIssue.json");

		Response response = given().auth().preemptive().basic(USERNAME, API_TOKEN)
				.header("X-Atlassian-Token", "no-check").contentType(ContentType.MULTIPART).multiPart("file", file)
				.post("/issue/" + issueId + "/attachments");

		response.then().statusCode(200);
		System.out.println("Attachment added to issue ID: " + issueId);
	}

	// Delete the defect created in step 1
	public static void deleteDefect(String issueId) {
		Response response = given().auth().preemptive().basic(USERNAME, API_TOKEN)
				.delete("/issue/" + issueId);

		response.then().statusCode(204);
		System.out.println("Issue deleted with ID: " + issueId);
	}
}
