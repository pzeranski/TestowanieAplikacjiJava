package com.example.restservicedemo;

import com.example.restservicedemo.domain.Train;
import com.example.restservicedemo.domain.TDriver;
import com.jayway.restassured.RestAssured;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.List;

import static com.jayway.restassured.RestAssured.delete;
import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.path.json.JsonPath.from;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;


public class TDriverServiceTest {
	
	private static TDriver td1;
	private static TDriver td2;
	private static TDriver td3;

    @BeforeClass
    public static void setUp(){
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
        RestAssured.basePath = "/restservicedemo/api";
		td1 = new TDriver("Janusz Moks", 1950);
		td2 = new TDriver("Mariusz Kobza", 1985);
		td3 = new TDriver("Czesio Nowy", 1965);
    }
    
	@Before
	@After
	public void clearDB(){
		delete("/train/").then().assertThat().statusCode(200);
		delete("/tdriver/").then().assertThat().statusCode(200);
	}
	
    @Test
    public void addTDrivers() throws JSONException {


	delete("/tdriver/").then().assertThat().statusCode(200);

	given().
       contentType(MediaType.APPLICATION_JSON).
       body(td1).
    when().	     
    post("/tdriver/add").then().assertThat().statusCode(201);

	String tdrivers = get("/tdriver/all").asString();

	HashMap<Train, TDriver> returnedTrains = from(tdrivers).get("tDriver");

	JSONObject json = new JSONObject(returnedTrains);


	TDriver rTDriver = get("/tdriver/" + json.get("id")).as(TDriver.class);
	
	assertThat(td1.getFirstName(), equalToIgnoringCase(rTDriver.getFirstName()));
    }

	@Test
	public void getAllTDrivers(){

		given().
				contentType(MediaType.APPLICATION_JSON).
				body(td1).
				when().
				post("/tdriver/add").then().assertThat().statusCode(201);
		given().
				contentType(MediaType.APPLICATION_JSON).
				body(td2).
				when().
				post("/tdriver/add").then().assertThat().statusCode(201);
		given().
				contentType(MediaType.APPLICATION_JSON).
				body(td3).
				when().
				post("/tdriver/add").then().assertThat().statusCode(201);

		String result = get("/tdriver/all/").asString();

		List<Train> tdrivers = from(result).get("tDriver");

		assertNotNull(tdrivers);
		assertEquals(3, tdrivers.size());
	}
}