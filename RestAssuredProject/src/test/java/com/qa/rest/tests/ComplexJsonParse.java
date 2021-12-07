package com.qa.rest.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import files.payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {
    @Test
	public void complex() {
		JsonPath js = new JsonPath(payload.coursePrice());
		System.out.println("----------------------------------");
		//Print No:of course returned by API
		System.out.println("Print No:of course returned by API"); 
		int count = js.getInt("courses.size()");
		System.out.println(count);
		System.out.println("----------------------------------");
		
		//Print Purchase Amount
		System.out.println("Print Purchase Amount");
		int PurchaseAmount= js.getInt("dashboard.purchaseAmount");
		System.out.println(PurchaseAmount);
		System.out.println("----------------------------------");
		
		//Print Title of the first course
		System.out.println("Print Title of the first course");
		String Title = js.get("courses[0].title");
		System.out.println(Title);
		System.out.println("----------------------------------");
		
		//Print all course title and their respective price
		System.out.println("Print all course title and their respective price");
		for(int i=0; i<count; i++){
			System.out.println( js.get("courses["+i+"].title"));
			System.out.println( js.get("courses["+i+"].price"));
					
		}
		System.out.println("----------------------------------");
		
		//Print no:of copies sold by RPA course
		System.out.println("Print no:of copies sold by RPA course");
		for(int i=0; i<count; i++){
			String courseTitle = ( js.get("courses["+i+"].title"));
			if(courseTitle.equalsIgnoreCase("RPA")) {
				System.out.println( js.get("courses["+i+"].copies"));
				break;
			}
					
		}
		System.out.println("-------------------------------------");
		
		//Verify if sum of all course prices matches with Purchase Amount
		System.out.println("Verify if sum of all course prices matches with Purchase Amount");
		int sum =0;
		for(int i=0; i<count; i++){
				int price= js.get("courses["+i+"].price");
				int copies = js.get("courses["+i+"].copies");
				
				int Amt = price*copies;
				sum = sum +  Amt;
			}
		
		System.out.println(sum);
		int purchaseAmount = js.get("dashboard.purchaseAmount");
		Assert.assertEquals(sum, purchaseAmount);
		
	}
	
}
