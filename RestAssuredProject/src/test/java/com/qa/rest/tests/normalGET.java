package com.qa.rest.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class normalGET {
	
	@Test
    public void get() {
		
		RestAssured.baseURI = "https://demoqa.com/utilities/weather/city";
		RequestSpecification req = RestAssured.given();
		Response resp = req.request(Method.GET,"Bangalore");
		int statusCode = resp.getStatusCode();
		Assert.assertEquals(statusCode, 200);
		String respBody = resp.getBody().asString();
		System.out.println(respBody);
		System.out.println(resp.contentType());
		Assert.assertEquals(resp.contentType(),"application/json; charset=utf-8");
			
	    }
}
