import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Tests_POST {

	@Test
	public void test_1_post() {
		
		Map<String, Object> map = new HashMap<String,Object>();
		
		map.put("name", "Nitha");
		map.put("job", "TestEngineer");
		System.out.println(map);
		
		JSONObject request = new JSONObject();
		
		request.put("name", "Nitha");
		request.put("job", "TestEngineer");
		System.out.println(request);
		
		System.out.println(request.toJSONString());
		
		given().
		  header("Content-Type","application/json; charset=utf-8").
		  contentType(ContentType.JSON).accept(ContentType.JSON).
		  body(request.toJSONString()).
		when().
		  post("https://reqres.in/api/users").
		then().
		  statusCode(201);
		
	}

//	@Test
//	public void test_2_put() {
//		
//		JSONObject request = new JSONObject();
//		
//		request.put("name", "Nitha");
//		request.put("job", "TestEngineer");
//		System.out.println(request);
//		
//		System.out.println(request.toJSONString());
//		
//		given().
//		  header("Content-Type","application/json; charset=utf-8").
//		  contentType(ContentType.JSON).accept(ContentType.JSON).
//		  body(request.toJSONString()).
//		when().
//		  put("https://reqres.in/api/users/2").
//		then().
//		  statusCode(201).
//		  log().all();
//		
//	}
	
//	@Test
//	public void test_3_patch() {
//		
//		JSONObject request = new JSONObject();
//		
//		request.put("name", "Nitha");
//		request.put("job", "TestEngineer");
//		System.out.println(request);
//		
//		System.out.println(request.toJSONString());
//		
//		given().
//		  header("Content-Type","application/json; charset=utf-8").
//		  contentType(ContentType.JSON).accept(ContentType.JSON).
//		  body(request.toJSONString()).
//		when().
//		  patch("https://reqres.in/api/users/2").
//		then().
//		  statusCode(200).
//		  log().all();
//		
//	}
	
//	@Test
//	public void test_4_delete() {
//		
//		
//		when().
//		  delete("https://reqres.in/api/users/2").
//		then().
//		  statusCode(204).
//		  log().all();
//		
//	}
}
