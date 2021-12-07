import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;

public class myFirstRestAssuredClass {
	final static String url = "http://demo.guru99.com/V4/sinkministatement.php?CUSTOMER_ID=68195&PASSWORD=1234!&Account_No=1";
	@Test
	public static void getResponseBody(){
		//		   given().when().get(url).then().log()
		//		  .all();
		//		 
		given().queryParam("CUSTOMER_ID","68195")
		.queryParam("PASSWORD","1234!")
		.queryParam("Account_No","1")
		.when().get("http://demo.guru99.com/V4/sinkministatement.php").then().log()
		.body();

	}
	@Test
	public static void getResponseStatus(){
		   int statusCode= given().queryParam("CUSTOMER_ID","68195")
		           .queryParam("PASSWORD","1234!")
		           .queryParam("Account_No","1") .when().get("http://demo.guru99.com/V4/sinkministatement.php").getStatusCode();
		   System.out.println("The response status is "+statusCode);

		   given().when().get(url).then().assertThat().statusCode(200);
		}
	@Test
	public static void getResponseHeaders(){
		   System.out.println("The headers in the response "+
		                   get(url).then().extract()
		           .headers());
		}
	@Test
	public static void getResponseTime(){
		  System.out.println("The time taken to fetch the response "+get(url)
		         .timeIn(TimeUnit.MILLISECONDS) + " milliseconds");
		}
	@Test
	public static void getResponseContentType(){
		   System.out.println("The content type of response "+
		           get(url).then().extract()
		              .contentType());
		}
	@Test
	public static void getSpecificPartOfResponseBody(){

		ArrayList<String> amounts = when().get(url).then().extract().path("result.statements.AMOUNT") ;
		int sumOfAll=0;
		for(String a:amounts){

		    System.out.println("The amount value fetched is "+a);
		    sumOfAll=sumOfAll+Integer.valueOf(a);
		}
		System.out.println("The total amount is "+sumOfAll);

		}
}
