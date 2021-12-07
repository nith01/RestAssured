package com.qa.rest.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.ReUsableMethods;
import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class DynamicJson {

	@Test(dataProvider = "BookData")
	public void addBook(String idbn, String aisle) {
		
		RestAssured.baseURI = "http://216.10.245.166";
		String response = given().log().all().header("content-type","application/json;charset=UTF-8")
				.body(payload.Library(idbn,aisle))//change the values each time you run the test
		.when().post("Library/Addbook.php")                                               //either validate here or.....
		.then().log().all().assertThat().statusCode(200).extract().response().asString();//.body("ID",equalTo("bcd227as"))
		//....use JSon to validate
		JsonPath js = ReUsableMethods.rawToJson(response);
		String id = js.get("ID");
		System.out.println(id);
		
	}
	
	@Test(dataProvider = "BookData")
	public void deleteBook(String idbn, String aisle) {
		RestAssured.baseURI = "http://216.10.245.166";
		String response = given().log().all().header("content-type","application/json;charset=UTF-8")
		.body(payload.Library(idbn,aisle))
		.when().delete("Library/Deletebook.php")                                             
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
	}
	
	@DataProvider(name= "BookData")
	public Object[][] getData() {
		//array = collection of elements
		//Multidimentional array = collection of arrays
		return new Object[][]  {{"1234","qwe"},{"2345","wer"},{"3456","ert"}};
		
	}
}
