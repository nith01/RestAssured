package com.qa.rest.tests;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import io.restassured.authentication.AuthenticationScheme;

public class AuthenticationUtil {
	
	@Test
	public void setup() {
		
		//RestAssured.authentication = (AuthenticathonScheme) RestAssured.preemptive().basic("ToolsQA", "TestPassword");
	}
}
