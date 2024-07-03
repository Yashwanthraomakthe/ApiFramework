package api.test;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import api.endpoints.PetEndPoints;
import api.payload.Pet;
import api.payload.PetCategory;
import api.payload.PetTag;
import io.restassured.response.Response;

public class PetTests {

	Pet petPayload;

	public Logger logger;

	@BeforeClass
	public void setup() {

		petPayload = new Pet();

		PetCategory category = new PetCategory();
		category.setId(1);
		category.setName("dog");

		PetTag tag = new PetTag();
		tag.setId(0);
		tag.setName("string");

		petPayload.setId(12345);
		petPayload.setCategory(category);
		petPayload.setName("snoopie");
		petPayload.setPhotoUrls(Arrays.asList("string"));
		petPayload.setTags(Arrays.asList(tag));
		petPayload.setStatus("pending");

		// logs
		logger = LogManager.getLogger(this.getClass());

		logger.debug("debugging.....");

	}

	@Test(priority = 1, enabled = true)
	public void testPostPet() {
		logger.info("********** Creating pet  ***************");
		Response response = PetEndPoints.createPet(petPayload);
		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(), 200);

		logger.info("**********pet is creatged  ***************");

	}

	@Test(priority = 2, enabled = true)
	public void testGetPetByID() {
		logger.info("********** Reading pet Info ***************");

		Response response = PetEndPoints.readPet(this.petPayload.getId());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.contentType(), "application/json");
		Assert.assertEquals(response.path("name"), "snoopie");
		Assert.assertEquals(response.path("status"), "pending");

		logger.info("**********pet info  is displayed ***************");

	}

}