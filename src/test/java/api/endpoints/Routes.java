package api.endpoints;

/* 
Swagger URI --> https://petstore.swagger.io

Create user(Post) : https://petstore.swagger.io/v2/user
Get user (Get): https://petstore.swagger.io/v2/user/{username}
Update user (Put) : https://petstore.swagger.io/v2/user/{username}
Delete user (Delete) : https://petstore.swagger.io/v2/user/{username}

*/

public class Routes {

	public static String base_url="https://petstore.swagger.io/v2" ;
	
	//User module
	
	public static String post_url=base_url+"/user";
    public static String get_url=base_url+"/user/{username}";
    public static String get_usersurl="https://jsonplaceholder.typicode.com/users";
    public static String update_url=base_url+"/user/{username}";
    public static String delete_url=base_url+"/user/{username}";    
   

    //Pet module
public static String petbase_url="https://petstore.swagger.io/v2" ;
	
	//Pet module
	
	public static String petpost_url=petbase_url+"/pet";
    public static String petget_url=petbase_url+"/pet/{petname}";
    public static String petupdate_url=petbase_url+"/pet/{petname}";
    public static String petdelete_url=petbase_url+"/pet/{petname}";
    
    
    
    //Employee  module
public static String employeebase_url="https://dummy.restapiexample.com/api/v1" ;
	
	//Pet module
	
	public static String employeepost_url=employeebase_url+"/create";
    public static String employeeget_url=employeebase_url+"/employee/{employeeID}";
    public static String employeesget_url="https://dummy.restapiexample.com/api/v1/employees";
    public static String employeeupdate_url=employeebase_url+"/employee/{employeename}";
    public static String employeedelete_url=employeebase_url+"/employee/{employeename}";
}