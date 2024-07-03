package api.test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class OpenWeatherMapAPI {

    private static final String API_KEY = "70af440f44ae7f9e8869bcd8b2537329";
    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5";

    @Test
    public void testWeatherDetailsOfHyderabad() {
        // Step 1: Get the current weather details of Hyderabad
        Response response =
        		given()
                .queryParam("q", "hyderabad")
                .queryParam("appid", API_KEY)
                .get(BASE_URL + "/weather")
                .then()
	                .statusCode(200)
	                .contentType(ContentType.JSON)
	                .extract()
	                .response();

        // Extract latitude and longitude
        float latitude = response.jsonPath().getFloat("coord.lat");
        float longitude = response.jsonPath().getFloat("coord.lon");

        System.out.println("Latitude: " + latitude);
        System.out.println("Longitude: " + longitude);

        // Step 2: Use the coordinates to get the weather details
        Response coordResponse =
        		given()
	                .queryParam("lat", latitude)
	                .queryParam("lon", longitude)
	                .queryParam("appid", API_KEY)
                .get(BASE_URL + "/weather")
                .then()
	                .statusCode(200)
	                .contentType(ContentType.JSON)
	                .extract()
	                .response();

        // Verify the required fields in the response
        String cityName = coordResponse.jsonPath().getString("name");
        String country = coordResponse.jsonPath().getString("sys.country");
        float tempMin = coordResponse.jsonPath().getFloat("main.temp_min");
        float temp = coordResponse.jsonPath().getFloat("main.temp");

        assertEquals(cityName, "Hyderabad", "City name does not match");
        assertEquals(country, "IN", "Country code does not match");
        assertTrue(tempMin > 0, "Minimum temperature is not greater than 0");
        assertTrue(temp > 0, "Temperature is not greater than 0");

        System.out.println("City Name: " + cityName);
        System.out.println("Country: " + country);
        System.out.println("Minimum Temperature: " + tempMin);
        System.out.println("Temperature: " + temp);
    }
}


