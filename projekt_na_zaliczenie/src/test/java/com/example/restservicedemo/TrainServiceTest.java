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


public class TrainServiceTest {
	
	private static Train t1;
	private static Train t2;
	private static Train t3;

    @BeforeClass
    public static void setUp(){
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
        RestAssured.basePath = "/restservicedemo/api";
		t1 = new Train("Dart S-123", 1901);
		t2 = new Train("Pendolino P-123", 2010);
		t3 = new Train("SU45-168", 1920);
    }
    
	@Before
	@After
	public void clearDB(){
		delete("/train/").then().assertThat().statusCode(200);
		delete("/tdriver/").then().assertThat().statusCode(200);
	}
	
    @Test
    public void addTrains() throws JSONException {

        given().
                contentType(MediaType.APPLICATION_JSON).
                body(t1).
                when().
                post("/train/add").then().assertThat().statusCode(201);


        String trains = get("/train/all").asString();

        HashMap<Train, TDriver> returnedTrains = from(trains).get("train");

        JSONObject json = new JSONObject(returnedTrains);

        Train rTrain = get("/train/" + json.get("id")).as(Train.class);

        assertThat(t1.getNumber(), equalToIgnoringCase(rTrain.getNumber()));
    }

	@Test
	public void getAllTrains(){

		given().
				contentType(MediaType.APPLICATION_JSON).
				body(t1).
				when().
				post("/train/add").then().assertThat().statusCode(201);
		given().
				contentType(MediaType.APPLICATION_JSON).
				body(t2).
				when().
				post("/train/add").then().assertThat().statusCode(201);
		given().
				contentType(MediaType.APPLICATION_JSON).
				body(t3).
				when().
				post("/train/add").then().assertThat().statusCode(201);

		String result = get("/train/all/").asString();

		List<Train> trains = from(result).get("train");

		assertNotNull(trains);
		assertEquals(3, trains.size());

	}

}