package com.example.restservicedemo;

import static com.jayway.restassured.RestAssured.given;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.ws.rs.core.MediaType;

import org.dbunit.Assertion;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.example.restservicedemo.domain.Train;
import com.example.restservicedemo.domain.TDriver;
import com.jayway.restassured.RestAssured;

public class TDriverServiceRESTDBTest {
	
	private static IDatabaseConnection connection ;
	private static IDatabaseTester databaseTester;
	
	TDriver aTDriver = new TDriver("Jan Nowy", 2001);
	Train aTrain = new Train("Dart S-123", 1901, aTDriver);
	
	@BeforeClass
	public static void setUp() throws Exception
	{
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
		RestAssured.basePath = "/restservicedemo/api";
		
		Connection jdbcConnection;
		jdbcConnection = DriverManager.getConnection(
				"jdbc:hsqldb:hsql://localhost/workdb", "sa", "");
		connection = new DatabaseConnection(jdbcConnection);
		
		databaseTester = new JdbcDatabaseTester(
				"org.hsqldb.jdbcDriver", "jdbc:hsqldb:hsql://localhost/workdb", "sa", "");
		IDataSet dataSet = new FlatXmlDataSetBuilder().build(
				new FileInputStream(new File("src/test/resources/fullData.xml")));
		databaseTester.setDataSet(dataSet);
		databaseTester.onSetup();
	}

	@Test
	public void addTDriver() throws Exception
	{
		given().contentType(MediaType.APPLICATION_JSON).body(aTDriver)
				.when().post("/tdriver/add").then().assertThat().statusCode(201);
		
		IDataSet dbDataSet = connection.createDataSet();
		ITable actualTable = dbDataSet.getTable("TDRIVER");
		ITable filteredTable = DefaultColumnFilter.excludedColumnsTable
				(actualTable, new String[]{"TD_ID"});
		
		IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(
				new File("src/test/resources/tdriverData.xml"));
		ITable expectedTable = expectedDataSet.getTable("TDRIVER");
		
		Assertion.assertEquals(expectedTable, filteredTable);
	}
	@Test
	public void addTrain() throws Exception
	{
		given().contentType(MediaType.APPLICATION_JSON).body(aTrain)
				.when().post("/train/add").then().assertThat().statusCode(201);
		
		IDataSet dbDataSet = connection.createDataSet();
		ITable actualTable = dbDataSet.getTable("TRAIN");
		ITable filteredTable = DefaultColumnFilter.excludedColumnsTable
				(actualTable, new String[]{"T_ID"});
		
		IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(
				new File("src/test/resources/trainData.xml"));
		ITable expectedTable = expectedDataSet.getTable("TRAIN");
		
		Assertion.assertEquals(expectedTable, filteredTable);
	}
	@AfterClass
	public static void tearDown() throws Exception{
		databaseTester.onTearDown();
	}

}
