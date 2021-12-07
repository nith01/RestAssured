package com.qa.rest.tests;

import org.testng.annotations.Test;

//import io.restassured.matcher.ResponseAwareMatcher;
//import io.restassured.response.Response;

import static io.restassured.RestAssured.*;


public class Demo_get_request {

	@Test
	public void getWeatherDetails() {
	given().
	when().get("http://ergast.com/api/f1/2020/circuits.json").                                                      //.get("https://demoqa.com/utilities/weather/city").
	then().
	    statusCode(200)
	                                                                                                             //.statusLine("HTTP/1.1 200 OK")
	    .header("content-type","application/json; charset=utf-8")                                                             //.header("content-type","text/html; charset=UTF-8")
	    .log().headers();
	  
	    
	    
	}

	
}
