package com.qa.rest.tests;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class BasicAuthentication extends AuthenticationUtil{
	
	@Test
	public void test1() {
		RestAssured.given().when().get("http://restapi.demoqa.com/authentication/CheckForAuthentication/")
		.then().assertThat().statusCode(200);
		
	}

	
}
