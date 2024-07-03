package api.test;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.EmployeeEndPoints;
import api.endpoints.UserEndPoints;
import api.payload.Employee;
import api.payload.User;
import io.restassured.response.Response;

public class EmployeeTest {

	Faker faker;
	Employee employeePayload;
	int employeeIntialCount;
	
	public Logger logger; 
	
	@BeforeClass
	public void setup()
	{
		faker=new Faker();
		employeePayload=new Employee();		
		
		employeePayload.setEmployee_name(faker.name().firstName());
		employeePayload.setEmployee_salary("50000");
		employeePayload.setEmployee_age("25");
		employeePayload.setProfile_image("");
		
		
		//logs
		logger= LogManager.getLogger(this.getClass());	
		
		
	}
	
	@Test(priority=1, enabled =true)
	public void testGetEmployees()
	{
		logger.info("********** Reading Employee Info ***************");
		
		Response response=EmployeeEndPoints.readEmployees();
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
		Assert.assertEquals(response.contentType(), "application/json");
		Assert.assertEquals(response.statusLine(), "HTTP/1.1 200 OK");
		
	
		// Verify the count of employees
       employeeIntialCount = response.jsonPath().getList("data").size();
        System.out.println("Employee count: " + employeeIntialCount);
	

		
		logger.info("**********Employee info  is displayed ***************");
		
	}
	
	@Test(priority=2, enabled =true)
	public void testPostEmployee()
	{
		logger.info("********** Creating Employee  ***************");
		Response response=EmployeeEndPoints.createEmployee(employeePayload);
		response.then().log().all();		
		
		Assert.assertEquals(response.getStatusCode(),200);
		// Verify the count of employees increased by one
        int employeeCountAfterPost = response.jsonPath().getList("data").size();
        System.out.println("Employee count: " + employeeCountAfterPost);
        
        Assert.assertEquals(employeeCountAfterPost, employeeIntialCount+1);
		
		logger.info("**********Employee is creatged  ***************");
			
	}
	
	@Test(priority=2, enabled =false)
	public void testGetEmployeeByName()
	{
		logger.info("********** Reading Employee Info ***************");
		
		Response response=EmployeeEndPoints.readEmployee(this.employeePayload.getEmployee_name());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
		
		logger.info("**********Employee info  is displayed ***************");
		
	}
	
	@Test(priority=3, enabled =false)
	public void testUpdateUserByName()
	{
		logger.info("********** Updating Employee ***************");
		
		//update data using payload
		employeePayload.setEmployee_name(faker.name().firstName());
		employeePayload.setEmployee_salary(faker.name().lastName());
		employeePayload.setEmployee_age(faker.internet().safeEmailAddress());
		
		//Response response=UserEndPoints.updateEmployee(this.employeePayload.setEmployee_name(null)),employeePayload);
		//response.then().log().body();
				
		//Assert.assertEquals(response.getStatusCode(),200);
		
		logger.info("********** User updated ***************");
		//Checking data after update
		//Response responseAfterupdate=UserEndPoints.readUser(this.userPayload.getUsername());
		//Assert.assertEquals(responseAfterupdate.getStatusCode(),200);
			
	}
	
	@Test(priority=4, enabled =false)
	public void testDeleteUserByName()
	{
		logger.info("**********   Deleting User  ***************");
		
		//Response response=UserEndPoints.deleteUser(this.userPayload.getUsername());
		//Assert.assertEquals(response.getStatusCode(),200);
		
		logger.info("********** User deleted ***************");
	}
	
	
	
	
}