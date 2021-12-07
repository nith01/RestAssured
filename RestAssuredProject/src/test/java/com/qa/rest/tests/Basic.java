package com.qa.rest.tests;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import files.ReUsableMethods;
import files.payload;

public class Basic {
	//public static void main(String[] args) {
	@Test		
	public void test() {
		    RestAssured.baseURI= "https://rahulshettyacademy.com";
		    //given - all input details 
			//when - Submit the API -resource(POST,GET,PUT,DELETE) and http method
			//then - validate the response, validate if Add Place API is working as expected
			//Add place-> Update Place with New Address -> Get Place to validate if New address is present in response	
            
		    //Create Place		    
			String response=given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
			.body(payload.AddPlace())
			.when().post("maps/api/place/add/json")
			.then().assertThat().statusCode(200).body("scope", equalTo("APP"))
			.header("server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();
			System.out.println(response);
			JsonPath js=new JsonPath(response); //for parsing Json
			String placeId=js.getString("place_id");
			System.out.println(placeId);
			System.out.println("-------------------------------------------------------------");
			
			//Update Place
			String newAddress = "Summer Walk, Africa";
			
			given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
			.body("{\r\n" + 
					"\"place_id\":\""+placeId+"\",\r\n" + 
					"\"address\":\""+newAddress+"\",\r\n" + 
					"\"key\":\"qaclick123\"\r\n" + 
					"}").
			when().put("maps/api/place/update/json")
			.then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));
			System.out.println("-------------------------------------------------------------");
			//Get Place
			
		    String getPlaceResponse=	given().log().all().queryParam("key", "qaclick123")
			.queryParam("place_id",placeId)
			.when().get("maps/api/place/get/json")
			.then().assertThat().log().all().statusCode(200).extract().response().asString();
		   JsonPath js1=ReUsableMethods.rawToJson(getPlaceResponse);
		   String actualAddress =js1.getString("address");
		   System.out.println(actualAddress);
		   Assert.assertEquals(actualAddress, newAddress);
		   System.out.println("-------------------------------------------------------------");
			
		   //delete Place
		     given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
			.body(payload.AddPlace()).when().delete("maps/api/place/delete/json")
			.then().assertThat().statusCode(200);
		     System.out.println("-------------------------------------------------------------");
				
		   //get Place
		     given().log().all().queryParam("key", "qaclick123")
				.queryParam("place_id",placeId)
				.when().get("maps/api/place/get/json")
				.then().assertThat().log().all().statusCode(200);
				}
	}


