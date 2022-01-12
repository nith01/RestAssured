package com.qa.rest.tests;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;

public class JiraTest {

	public static void main(String[] args) {
		RestAssured.baseURI = "http://localhost:8081";
		//Login
		SessionFilter session = new SessionFilter();
	    String response = given().header("Content-Type","application/json")
		.body("{ \"username\": \"nitha01sa\", \"password\": \"******\" }").log().all().filter(session)
		.when().post("/rest/auth/1/session").then().log().all().extract().response().asString();

		
		
		given().pathParam("key","10003").log().all().header("Content-Type","application/json").body("{\r\n"
				+ "    \"body\": \"This is my first comment\",\r\n"
				+ "    \"visibility\": {\r\n"
				+ "        \"type\": \"role\",\r\n"
				+ "        \"value\": \"Administrators\"\r\n"
				+ "    }\r\n"
				+ "}").filter(session)
		.when().post("/rest/issue/{key}/comment").then().log().all().assertThat().statusCode(201);

	}

}
