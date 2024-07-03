package api.test;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests {

	Faker faker;
	User userPayload;
	int noOfInitialUser;

	public Logger logger; // for logs

	@BeforeClass
	public void setup() {
		faker = new Faker();
		userPayload = new User();

		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());

		// logs
		logger = LogManager.getLogger(this.getClass());

		logger.debug("debugging.....");

	}

	@Test(priority = 1, enabled = true)
	public void testPostUser() {
		logger.info("********** Creating user  ***************");
		Response response = UserEndPoints.createUser(userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		System.out.println(response.body());		
		

		logger.info("**********User is creatged  ***************");

	}

	@Test(priority = 2, enabled = true)
	public void testGetUserByName() {
		logger.info("********** Reading User Info ***************");

		Response response = UserEndPoints.readUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);

		logger.info("**********User info  is displayed ***************");

	}

	@Test(priority = 3, enabled = true)
	public void testUpdateUserByName() {
		logger.info("********** Updating User ***************");

		// update data using payload
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());

		Response response = UserEndPoints.updateUser(this.userPayload.getUsername(), userPayload);
		response.then().log().body();

		Assert.assertEquals(response.getStatusCode(), 200);

		logger.info("********** User updated ***************");
		// Checking data after update
		Response responseAfterupdate = UserEndPoints.readUser(this.userPayload.getUsername());
		Assert.assertEquals(responseAfterupdate.getStatusCode(), 200);

	}

	@Test(priority = 4, enabled = true)
	public void testDeleteUserByName() {
		logger.info("**********   Deleting User  ***************");

		Response response = UserEndPoints.deleteUser(this.userPayload.getUsername());
		Assert.assertEquals(response.getStatusCode(), 200);

		logger.info("********** User deleted ***************");
	}

	@Test(priority = 5, enabled = false)
	public void testGetUsersTask03() {
		logger.info("********** Reading User Info ***************");

		Response response = UserEndPoints.readUsers();
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		List<String> names = response.jsonPath().getList("name");
		System.out.println("Number of users : " + names.size());

		// Collect the number of Users
		noOfInitialUser = names.size();
		System.out.println("Employee count: " + noOfInitialUser);

		for (String name : names) {
			boolean flag = false;
			if (name.equals("Ervin Howell")) {
				flag = true;
				break;
			} else {
				flag = true;
			}
			if (flag = true) {
				System.out.println("Ervin Howell is Present in list");
			} else {
				System.out.println("Ervin Howell is not Present in list");
			}
		}

		logger.info("**********User info  is displayed ***************");

	}

}