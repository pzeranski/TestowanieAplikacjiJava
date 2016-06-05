package com.example.restservicedemo;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.example.restservicedemo.domain.Train;
import com.example.restservicedemo.domain.TDriver;
import com.example.restservicedemo.service.TrainManager;
import com.example.restservicedemo.service.TDriverManager;

public class BLTest {
	private static TDriver t1;
	private static TDriver t2;
	private static TDriver t3;
	private static Train td1;
	private static Train td2;
	private static Train td3;
	
	private static TrainManager tm = new TrainManager();
	private static TDriverManager tdm = new TDriverManager();
	@BeforeClass
	public static void setUp() {
		t1 = new TDriver("Janusz Moks", 1950);
		t2 = new TDriver("Mariusz Kobza", 1985);
		t3 = new TDriver("Czesio Nowy", 1965);
		td1 = new Train("Dart S-123", 1901);
		td2 = new Train("Pendolino P-123", 2010);
		td3 = new Train("SU45-168", 1920);
	}
	@Before
	@After
	public void cleanTables() {
		tm.clearTrains();
		tdm.clearTDrivers();
	}
	@Test
	public void addTDriver() {
		assertEquals(1, tdm.addTDriver(t1));
		
	}	
	@Test
	public void getAllTDrivers() {

		tdm.addTDriver(t1);
		tdm.addTDriver(t2);
		tdm.addTDriver(t3);

		List<TDriver> tdrivers = new ArrayList<>();

		tdrivers = tdm.getAllTDrivers();

		assertEquals(3, tdrivers.size());
		assertNotEquals(tdrivers.get(0), tdrivers.get(1));
		assertNotEquals(tdrivers.get(1), tdrivers.get(2));

		assertEquals(t1.getFirstName(), tdrivers.get(0).getFirstName());
		assertEquals(t2.getFirstName(), tdrivers.get(1).getFirstName());
		assertEquals(t3.getFirstName(), tdrivers.get(2).getFirstName());
	}
	@Test
	public void getTDriverByFirstName() {
		tdm.addTDriver(t1);
		TDriver answer = tdm.getTDriverFromFirstName(t1.getFirstName());
		assertEquals(true, answer.hasId());
		assertEquals(t1.getFirstName(), answer.getFirstName());
		assertEquals(t1.getStartWork(), answer.getStartWork());
	}
	@Test
	public void getTDriverById() {
		tdm.addTDriver(t1);
		TDriver answer2 = tdm.getTDriverFromFirstName(t1.getFirstName());
		assertEquals(true, answer2.hasId());

		TDriver answer = tdm.getTDriver(answer2.getId());

		assertEquals(t1.getFirstName(), answer.getFirstName());
		assertEquals(t1.getStartWork(), answer.getStartWork());
	}	
	@Test
	public void deleteTDriver() {
		tdm.addTDriver(t1);
		TDriver answer2 = tdm.getTDriverFromFirstName(t1.getFirstName());
		assertEquals(true, answer2.hasId());

		TDriver answer = tdm.getTDriver(answer2.getId());

		List<TDriver> tdrivers;
		tdrivers = tdm.getAllTDrivers();
		assertEquals(1, tdrivers.size());

		tdm.removeTDriver(answer.getId());
		tdrivers = tdm.getAllTDrivers();
		assertEquals(0, tdrivers.size());

		tdm.addTDriver(t1);
		tdm.addTDriver(t2);
		tdm.addTDriver(t3);
		tdrivers = tdm.getAllTDrivers();
		assertEquals(3, tdrivers.size());

		answer = tdm.getTDriverFromFirstName(t1.getFirstName());
		tdm.removeTDriver(answer.getId());
		tdrivers = tdm.getAllTDrivers();
		assertEquals(2, tdrivers.size());
	}
	@Test
	public void addTrain() {
		assertEquals(1, tm.addTrain(td1));
	}

	@Test
	public void getAllTrains() {

		tm.addTrain(td1);
		tm.addTrain(td2);
		tm.addTrain(td3);

		List<Train> trains;

		trains = tm.getAllTrains();

		assertEquals(3, trains.size());
		assertNotEquals(trains.get(0), trains.get(1));
		assertNotEquals(trains.get(1), trains.get(2));

		assertEquals(td1.getNumber(), trains.get(0).getNumber());
		assertEquals(td2.getNumber(), trains.get(1).getNumber());
		assertEquals(td3.getNumber(), trains.get(2).getNumber());
	}
	@Test
	public void changeTrainAndGetTrainWithDriver() {

		tdm.addTDriver(t1);
		tdm.addTDriver(t2);
		tdm.addTDriver(t3);
		tm.addTrain(td1);
		tm.addTrain(td2);

		List<TDriver> tdrivers = tdm.getAllTDrivers();
		assertEquals(true, tdrivers.get(0).hasId());
		assertEquals(true, tdrivers.get(1).hasId());
		assertEquals(true, tdrivers.get(2).hasId());

		List<Train> trains = tm.getAllTrains();
		assertEquals(true, trains.get(0).hasId());
		assertEquals(true, trains.get(1).hasId());


		assertEquals(1, tm.changeTrain(trains.get(0), tdrivers.get(0)));

		Train answer = tm.getTrainWithOwner(trains.get(0));

		assertEquals(tdrivers.get(0).getFirstName(), answer.getDriver().getFirstName());
		assertEquals(tdrivers.get(0).getStartWork(), answer.getDriver().getStartWork());

	}	
	@Test
	public void getTDriversWithTrains() {

		tdm.addTDriver(t1);
		tdm.addTDriver(t2);
		tdm.addTDriver(t3);
		tm.addTrain(td1);
		tm.addTrain(td2);

		
		List<TDriver> tdrivers = tdm.getAllTDrivers();

		List<Train> trains = tm.getAllTrains();


		assertEquals(1, tm.changeTrain(trains.get(0), tdrivers.get(0)));
		assertEquals(1, tm.changeTrain(trains.get(1), tdrivers.get(1)));

		Map<TDriver, List<Train>> answer = tdm.getTDriverWithTrain();

		assertTrue(answer.size() == 2);
	}	
}
